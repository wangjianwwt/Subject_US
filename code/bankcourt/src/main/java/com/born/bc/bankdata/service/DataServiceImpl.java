package com.born.bc.bankdata.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.born.bc.bankdata.common.ParseUtil;
import com.born.bc.bankdata.dao.InterPayMapper;
import com.born.bc.bankdata.dao.PosPayMapper;
import com.born.bc.bankdata.entity.InterPay;
import com.born.bc.bankdata.entity.PosPay;
import com.born.bc.bankdata.entity.cons.DataCons;
import com.born.bc.bankdata.service.api.DataServiceApi;
import com.born.bc.bankdata.util.BatchNumberUtil;
import com.born.bc.commons.entity.CurrentLoginUser;
import com.born.bc.commons.utils.DateUtils;
import com.born.bc.commons.utils.ExcelUtil;
import com.born.bc.commons.utils.IdGenerator;
import com.born.bc.commons.utils.LoginUserInfoUtil;
import com.born.bc.commons.utils.ResultJson;

/**
 * 导入导出Service
 */
@Service
public class DataServiceImpl<T> implements DataServiceApi {

	@Autowired
	private InterPayMapper interPayMapper;
	@Autowired
	private PosPayMapper posPayMapper;

	// 申明网银锁
	private Lock interLock = new ReentrantLock();
	// 申明pos锁
	private Lock posLock = new ReentrantLock();

	@Value("${inter}")
	private String sourceInterStr;

	@Value("${pos}")
	private String sourcePosStr;
	
	// 申明日期格式
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson importExcel(HttpServletRequest request) throws Exception {
		// 获取触点
		String sysClick = request.getParameter("sys_click");
		// 用户Token
		String userToken = request.getParameter("userToken");
		if (StringUtils.isEmpty(userToken)) {
			return ResultJson.buildParamError("用户token为空!");
		}
		if (StringUtils.isEmpty(sysClick)) {
			return ResultJson.buildParamError("未知导入，系统无法处理: null");
		}

		if (DataCons.SOURCE_INTER.equals(sysClick)) {
			// 网银导入
			String temple = new String(sourceInterStr.getBytes("ISO-8859-1"), "UTF-8");
			return excutorInterImport(userToken, temple, request);
		} else if (DataCons.SOURCE_POS.equals(sysClick)) {
			String temple = new String(sourcePosStr.getBytes("ISO-8859-1"), "UTF-8");
			return excutorPosImport(userToken, temple, request);
		} else {
			return ResultJson.buildParamError("未知导入，系统无法处理: other");
		}

	}

	/**
	 * 执行网银导入
	 * 
	 * @param request
	 */
	private ResultJson excutorInterImport(String userToken, String temple, HttpServletRequest request) {
		// 获取锁，没拿到锁就立即返回false
		if (interLock.tryLock()) {
			try {
				MultipartFile multipartFile = getFileFormReq(request);
				// 解析Excel
				JSONObject templeJson = JSONObject.parseObject(temple);
				// 获取解析后的数据
				Map<String, Object> resolveMap = ExcelUtil.fileDecompose(templeJson, multipartFile);
				// 将解析后的数据转成list
				List<Map<String, Object>> fileData = (List<Map<String, Object>>) resolveMap.get("data");
				// 判空
				if (fileData == null || fileData.size() < 1) {
					return ResultJson.buildError("文件中不存在数据!");
				}
				// 转化泛型格式
				List<InterPay> list = ParseUtil.parseAboutList(fileData, InterPay.class);
				// 获取批次号
				String batchNumber = BatchNumberUtil.create();
				// 获取当前登录的用户信息
				CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(userToken);
				// 设置用户信息
				for (InterPay interPay : list) {
					interPay.setId(IdGenerator.getUUID());
					interPay.setBatchNumber(batchNumber);
					interPay.setFormmakerId(loginUser.getUserId());
					interPay.setFormmakerName(loginUser.getLoginName());
				}

				// 批量往数据库中插入导入的网友交易数据
				// 50条数据进行一次操作
				List<InterPay> excuteList = null;
				for (int i = 0; i < list.size(); i++) {
					// 每50条数据执行一次插入
					if (i % 50 == 0) {
						if (excuteList != null && excuteList.size() > 0) {
							interPayMapper.batchInsertSelective(excuteList);
						}
						excuteList = new ArrayList<>();
					}

					excuteList.add(list.get(i));
				}
				// 执行剩下的部分未插入数据
				if (excuteList != null && excuteList.size() > 0) {
					interPayMapper.batchInsertSelective(excuteList);
				}

				return ResultJson.buildSuccess("上传成功了");
			} catch (Exception e) {
				e.printStackTrace();
				return ResultJson.buildError("上传文件在解析过程中出现异常，请联系开发人员及时处理!");
			} finally {
				// 释放锁
				interLock.unlock();
			}
		} else {
			return ResultJson.buildError("网银上传通道被占用，请先喝杯茶后再来尝试上传!");
		}

	}

	/**
	 * 执行Pos导入
	 * 
	 * @param request
	 */
	private ResultJson excutorPosImport(String userToken, String temple, HttpServletRequest request) {
		// 获取锁，没拿到锁就立即返回false
		if (posLock.tryLock()) {
			try {
				MultipartFile multipartFile = getFileFormReq(request);
				// 解析Excel
				JSONObject templeJson = JSONObject.parseObject(temple);
				
				Map<String, Object> resolveMap = ExcelUtil.fileDecompose(templeJson, multipartFile);
				
				List<Map<String, Object>> fileData = (List<Map<String, Object>>) resolveMap.get("data");

				// 转化泛型格式
				List<PosPay> list = ParseUtil.parseAboutList(fileData, PosPay.class);
				
				// 获取批次号
				String batchNumber = BatchNumberUtil.create();
//				// 从第一行获取部门编号
//				String [] fristRow = (String[]) resolveMap.get("fristRow");
//				String departmentNumber = fristRow[1];
				
				// 获取当前登录的用户信息
				CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(userToken);
				int index = 0;
				
				List<PosPay> freeDataList = new ArrayList<>();
				
				List<Map<String, String>> caseNumberList = new ArrayList<>();
				
				for (PosPay posPay : list) {
					if((index + 3) < list.size()){
						String id = IdGenerator.getUUID();
						posPay.setId(id);
						posPay.setBatchNumber(batchNumber);
						posPay.setFormmakerId(loginUser.getUserId());
						posPay.setFormmakerName(loginUser.getLoginName());
						// 对POS数据的交易日期进行处理，将源数据日期往前推一天
						posPay.setTransactionDate(getYesterdayStr(posPay.getTransactionDate()));
						// 填充数据到list
						freeDataList.add(posPay);
						// 填充数据到list
						caseNumberList.add(getCaseNumberMap(id, posPay.getCaseNumber()));
					}
					index ++;
				}
				
				// 将案号ID设置到源数据行上
				setCaseIdToSourceData(freeDataList, caseNumberList);
				
				// 批量往数据库中插入导入的网友交易数据
				// 50条数据进行一次操作
				List<PosPay> excuteList = null;
				for (int i = 0; i < freeDataList.size(); i++) {
					// 每50条数据执行一次插入
					if (i % 50 == 0) {
						if (excuteList != null && excuteList.size() > 0) {
							posPayMapper.batchInsertSelective(excuteList);
						}
						excuteList = new ArrayList<>();
					}
					excuteList.add(list.get(i));
				}
				// 执行剩下的部分未插入数据
				if (excuteList != null && excuteList.size() > 0) {
					posPayMapper.batchInsertSelective(excuteList);
				}
				return ResultJson.buildSuccess("上传成功了");
			} catch (Exception e) {
				e.printStackTrace();
				return ResultJson.buildError("上传文件在解析过程中出现异常，请联系开发人员及时处理!");
			} finally {
				// 释放锁
				posLock.unlock();
			}
		} else {
			return ResultJson.buildError("POS数据导入通道被占用，请先喝杯茶后再来尝试上传!");
		}

	}

	/**
	 * 将案号ID设置到源数据行上
	 * @param freeDataList
	 * @param caseNumberList
	 */
	private void setCaseIdToSourceData(List<PosPay> freeDataList, List<Map<String, String>> caseNumberList) {
		// TODO 待实现 将案号ID设置到源数据行上
	}

	/**
	 * 记录一个ID对应一个caseNumber
	 * @param id
	 * @param caseNumber
	 * @return
	 */
	private Map<String, String> getCaseNumberMap(String id, String caseNumber) {
		
		Map<String, String> caseMap = new HashMap<>(1, 2);
		caseMap.put("key", id);
		caseMap.put("value", caseNumber);
		
		return caseMap;
	}

	/**
	 * 获取指定时间字符串的前一天日期字符串
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	private String getYesterdayStr(String dateStr) throws ParseException {
		if(StringUtils.isEmpty(dateStr)){
			return null;
		}
		// 将String转Date
		Date date = sdf.parse(dateStr);
		// 获取date的上一天日期
		Date yesterday = DateUtils.someDaysAgo(date, -1);
		return sdf.format(yesterday);
	}

	/**
	 * 获取导入的文件
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public MultipartFile getMultipartFile(HttpServletRequest request) throws Exception {
		ShiroHttpServletRequest shiroRequest = (ShiroHttpServletRequest) request;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		MultipartHttpServletRequest multipartRequest = commonsMultipartResolver
				.resolveMultipart((HttpServletRequest) shiroRequest.getRequest());
		Iterator<String> itr = multipartRequest.getFileNames();
		MultipartFile multipartFile = null;
		while (itr.hasNext()) {
			multipartFile = multipartRequest.getFile(itr.next());
		}
		return multipartFile;
	}

	/**
	 * 获取request中的file
	 * 
	 * @param req
	 * @return
	 */
	public MultipartFile getFileFormReq(HttpServletRequest req) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(req.getSession().getServletContext());
		MultipartFile file = null;
		if (multipartResolver.isMultipart(req)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) req;
			// 获取multiRequest 中所有的文件名
			Iterator<String> iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// 一次遍历所有文件
				file = multiRequest.getFile(iter.next().toString());
			}
		} else {
			file = null;
		}
		return file;
	}

}
