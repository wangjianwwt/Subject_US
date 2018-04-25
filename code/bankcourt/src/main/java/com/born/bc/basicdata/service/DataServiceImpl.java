package com.born.bc.basicdata.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.born.bc.basicdata.common.ParseUtil;
import com.born.bc.basicdata.dao.InterPayDtoMapper;
import com.born.bc.basicdata.dao.PosPayDtoMapper;
import com.born.bc.basicdata.entity.HomeEntity;
import com.born.bc.basicdata.entity.InterPay;
import com.born.bc.basicdata.entity.InterPayDto;
import com.born.bc.basicdata.entity.PosPay;
import com.born.bc.basicdata.entity.PosPayDto;
import com.born.bc.basicdata.entity.cons.DataCons;
import com.born.bc.basicdata.service.api.CasePerfectApi;
import com.born.bc.basicdata.service.api.DataServiceApi;
import com.born.bc.basicdata.util.BatchNumberUtil;
import com.born.bc.caseinfo.entity.CaseInfo;
import com.born.bc.caseinfo.service.api.CaseServiceApi;
import com.born.bc.commons.entity.CommonCons;
import com.born.bc.commons.entity.CurrentLoginUser;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.DateUtils;
import com.born.bc.commons.utils.ExcelUtil;
import com.born.bc.commons.utils.IdGenerator;
import com.born.bc.commons.utils.LogUtils;
import com.born.bc.commons.utils.LoginUserInfoUtil;
import com.born.bc.commons.utils.ResultJson;

/**
 * 导入导出Service
 */
@Service
public class DataServiceImpl<T> implements DataServiceApi {

	@Autowired
	private CasePerfectApi cpApi;

	@Autowired
	private InterPayDtoMapper interPayDtoMapper;
	@Autowired
	private PosPayDtoMapper posPayDtoMapper;

	@Autowired
	private CaseServiceApi caseApi;

	// 申明网银锁
	private Lock interLock = new ReentrantLock();
	// 申明pos锁
	private Lock posLock = new ReentrantLock();

	@Value("${inter}")
	private String sourceInterStr;

	@Value("${pos}")
	private String sourcePosStr;

	// 申明日期格式
	private final SimpleDateFormat sdfSp = new SimpleDateFormat("yyyy-MM");
	// 申明日期格式
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	// 申明日期格式
	private final SimpleDateFormat sdfEx = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	@Override
	public ResultJson guideResp(String dateStr, String sysClick) {
		try {
			if(StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(sysClick)){
				return ResultJson.buildError("参数异常");
			}
			// 匹配操作
			if (DataCons.SOURCE_INTER.equals(sysClick)) {
				// 要求格式yyyy-MM
				Set<String> dateSet = interPayDtoMapper.guideResp(dateStr);
				return ResultJson.buildSuccess("查询成功", dateSet);
			} else if (DataCons.SOURCE_POS.equals(sysClick)) {
				// 要求格式yyyy-MM
				Set<String> dateSet = posPayDtoMapper.guideResp(dateStr);
				return ResultJson.buildSuccess("查询成功", dateSet);
			} else {
				return ResultJson.buildParamError("未知操作，系统无法处理: other");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("系统错误");
		}
	}
	
	@Override
	public ResultJson lockData(String dateStr) {
		try {
			Set<String> paramSet = new HashSet<>(1);
			paramSet.add(dateStr);
			Set<String> dateSet = posPayDtoMapper.lockData(dateStr);
			String[] dates = null;
			// 拼接月日期开始到结束
			if(!CollectionUtils.isEmpty(dateSet)){
				String dateString = new ArrayList<>(dateSet).get(0);
				Date first = sdfSp.parse(dateString);
				Calendar cal = Calendar.getInstance();
				cal.setTime(first);
				int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				dates = new String[maxDate];
				for (int i = 1; i <= maxDate; i++) {
					if(i < 10){
						dates[i-1] = dateString + "-0"+i;
					}else{
						dates[i-1] = dateString + "-"+i;
					}
				}
			}
			
			return ResultJson.buildSuccess("查询成功", dates);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败");
		}
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson importExcel(HttpServletRequest request) throws Exception {
		// 获取触点
		String sysClick = request.getParameter("sys_click");
		// 用户Token
		String userToken = request.getParameter("userToken");

		LogUtils.debug("--触点:" + sysClick + ",用户token:" + userToken);

		if (StringUtils.isEmpty(userToken)) {
			return ResultJson.buildParamError("用户token为空!");
		}
		if (StringUtils.isEmpty(sysClick)) {
			return ResultJson.buildParamError("未知导入，系统无法处理: null");
		}

		if (DataCons.SOURCE_INTER.equals(sysClick)) {
			// 网银导入
			String temple = new String(sourceInterStr.getBytes("ISO-8859-1"), "UTF-8");
			LogUtils.debug("--temple:" + temple);
			return excutorInterImport(userToken, temple, request);
		} else if (DataCons.SOURCE_POS.equals(sysClick)) {
			String temple = new String(sourcePosStr.getBytes("ISO-8859-1"), "UTF-8");
			LogUtils.debug("--temple:" + temple);
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
				// 从request中拿到上传的文件对象
				MultipartFile multipartFile = getFileFormReq(request);
				// 获取解析后的数据
				Map<String, Object> resolveMap = ExcelUtil.fileDecompose(temple, multipartFile);
				// 将解析后的数据转成list
				List<Map<String, Object>> fileData = (List<Map<String, Object>>) resolveMap.get("data");
				// 判空
				if (CollectionUtils.isEmpty(fileData)) {
					return ResultJson.buildError("文件中不存在可导入的数据，请检查文件是否正常后再次进行导入！");
				}

				// 转化泛型格式
				List<InterPay> defectiveList = ParseUtil.parseAboutList(fileData, InterPay.class);
				// 将上传的数据defectiveList转成List<InterPayDto>
				List<InterPayDto> dtoList = convertIntoInterDto(defectiveList);

				// 获取批次号
				String batchNumber = BatchNumberUtil.createInterPayBatchNumber();

				// 获取当前登录的用户信息
				CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(userToken);

				LogUtils.debug("--登录的用户信息:" + loginUser);

				Set<String> dateSet = new HashSet<String>(3);
				
				// 检查结算Set
				Set<String> dateSpSet = new HashSet<String>(3);

				// 设置用户信息
				for (InterPayDto dto : dtoList) {
					dto.setId(IdGenerator.getUUID());
					dto.setBatchNumber(batchNumber);
					dto.setFormmakerId(loginUser.getUserId());
					dto.setFormmakerName(loginUser.getLoginName());

					// 导入预判 日期重复。--时间粒度（天）
					if (dto.getTransactionTime() != null) {
						String dateStr = subDateStr(dto.getTransactionTime());
						String dateSpStr = subDateSpStr(dto.getTransactionTime());
						dateSet.add(dateStr);
						dateSpSet.add(dateSpStr);
					}
				}
				
				// 检查交易日期是否冻结
				Set<String> lockDateSet = posPayDtoMapper.lockDataByParam(dateSpSet);
				if(!CollectionUtils.isEmpty(lockDateSet)){
					throw new BussinessException("交易日期已经被结算冻结，无法继续执行导入");
				}

				LogUtils.debug("本次网银导入数据日期范围：" + dateSet);
				// 记录上传总条数
				int total = dtoList.size();
				LogUtils.debug("--网银交易数据总数:" + dtoList.size());

				// 过滤掉已导入的日期
				Set<String> resultDate = filterExcutedInterDate(dateSet, dtoList);

				LogUtils.debug("本次已经执行过导入的日期："+resultDate);
				LogUtils.debug("--网银交易数据可执行操作总条数:" + dtoList.size());

				// 记录执行成功总条数
				int excuteLine = 0;
				// 批量往数据库中插入导入的网银交易数据
				// 50条数据进行一次操作
				List<InterPayDto> excuteList = null;
				for (int i = 0; i < dtoList.size(); i++) {
					// 每50条数据执行一次插入
					if (i % 50 == 0) {
						if (excuteList != null && excuteList.size() > 0) {
							excuteLine += interPayDtoMapper.batchInsert(excuteList);
						}

						// 设置数组初始长度
						int initLength = 0;
						if ((i + 1 + 50) > (dtoList.size() - i)) {
							initLength = dtoList.size() - i;
						} else {
							initLength = 50;
						}
						excuteList = new ArrayList<>(initLength);
					}
					excuteList.add(dtoList.get(i));
				}
				// 执行剩下的部分未插入数据
				if (excuteList != null && excuteList.size() > 0) {
					excuteLine += interPayDtoMapper.batchInsert(excuteList);
				}

				LogUtils.debug("--插入网银交易数据成功，开始执行网银数据的案号补全任务");

				// 创建一个线程执行网银数据的案号补全任务
				newThreadExcutorCompletionCaseNumberTaskForInter();
				
				// 组装返回提示消息
				String msg ="导入操作成功!"
						+ "<br />本次导入数据的交易日期为："+dateSet+"，历史导入数据中已经存在的交易日期有："+((CollectionUtils.isEmpty(resultDate)) ? "无" : resultDate) +"。"
						+ "<br />本次一共导入：" + total + "条数据，因交易日期重复被过滤掉：" + (total - dtoList.size())+ "条数据。"
				        + "<br />成功导入数据库：" + excuteLine + "条数据，失败了：" + (dtoList.size() - excuteLine) + "条数据。";
				return ResultJson.buildSuccess(msg);
			} catch (BussinessException e) {
				return ResultJson.buildError(e.getMessage());
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
	 * 转换日期格式“yyyy-MM”
	 * @param transactionTime
	 * @return
	 */
	private String subDateSpStr(Date transactionTime) {
		return sdf.format(transactionTime);
	}

	/**
	 * 将上传的数据List<InterPay>转成List<InterPayDto>
	 * 
	 * @return
	 * @throws ParseException
	 */
	private List<InterPayDto> convertIntoInterDto(List<InterPay> defectiveList) throws ParseException {

		// 判断是否为空
		if (CollectionUtils.isEmpty(defectiveList)) {
			return null;
		}

		// 申明定长返回数组
		List<InterPayDto> resultList = new ArrayList<>(defectiveList.size());
		// 循环转换
		for (InterPay def : defectiveList) {
			InterPayDto dto = new InterPayDto();
			// 本方账号
			dto.setLocalNumber(def.getLocalNumber());
			// 对方账号
			dto.setPartyNumber(def.getPartyNumber());
			// 交易时间
			{
				if (!StringUtils.isEmpty(def.getTransactionTime())) {
					// 处理后的格式为： yyyy-MM-dd HH:mm:ss
					String thisDateStr = def.getTransactionTime().replace("/", "-");
					// 错误格式处理
					String errorFmt = "yyyy-MM-dd HH:mm";
					if (thisDateStr.length() == errorFmt.length()) {
						// 导入格式为：yyyy-MM-dd HH:mm
						// 需要转成： yyyy-MM-dd HH:mm:ss
						thisDateStr = thisDateStr + ":00";
					}

					// 最终格式是 yyyy-MM-dd HH:mm:ss
					Date transactionTime = sdfEx.parse(thisDateStr);
					dto.setTransactionTime(transactionTime);
				}
			}
			// 借/贷
			{
				// 借贷互换
				String borrowOrLend = null;
				if("借".equals(def.getBorrowOrLend())){
					borrowOrLend = "贷";
				}else{
					borrowOrLend = "借";
				}
				dto.setBorrowOrLend(borrowOrLend);
			}

			// 导入数据将做借贷转换，将导入数据的借方转成贷方，贷方转成借方
			// 借方发生额
			dto.setDebitPrice(makeMoney(def.getCreditPrice()));
			// 贷方发生额
			dto.setCreditPrice(makeMoney(def.getDebitPrice()));
			// 对方行号
			dto.setPartyLineNumber(def.getPartyLineNumber());
			// 摘要
			dto.setAbstractInfo(def.getAbstractInfo());
			// 用途
			dto.setPurpose(def.getPurpose());
			// 对方单位名称
			dto.setCorporateName(def.getCorporateName());
			// 余额
			dto.setBalance(makeMoney(def.getBalance()));
			// 个性化信息
			dto.setIndividuation(def.getIndividuation());
			// 个案ID
			dto.setCaseId(def.getCaseId());
			// 个案号
			dto.setCaseNumber(def.getCaseNumber());
			// 科目ID
			dto.setSubjectId(def.getSubjectId());
			// 科目名称
			dto.setSubjectName(def.getSubjectName());
			// 部门名称
			dto.setDepartmentName(def.getDepartmentName());
			// 承办人名
			dto.setUndertaker(def.getUndertaker());

			// 生成凭证号字符串
			String voucherNo = BatchNumberUtil.createVoucherNo(dto.getTransactionTime());
			// 设置凭证号
			dto.setVoucherNo(voucherNo);

			resultList.add(dto);
		}

		return resultList;
	}

	/**
	 * 截取日期字符串
	 * 
	 * @param transactionTime
	 * @return
	 * @throws ParseException
	 */
	private String subDateStr(Date transactionTime) throws ParseException {
		String dateStr = sdf.format(transactionTime);
		return dateStr;
	}

	/**
	 * 过滤已经执行过导入操作的网银数据
	 * 
	 * @param dateSet
	 *            本次导入的日期范围
	 * @param list
	 *            本次导入的数据
	 * @return 本次导入日期中已经执行过导入操作的日期
	 * @throws ParseException
	 */
	private Set<String> filterExcutedInterDate(Set<String> dateSet, List<InterPayDto> list) throws ParseException {
		LogUtils.debug("开始过滤已经导入过的网银数据...");
		if (CollectionUtils.isEmpty(dateSet)) {
			throw new BussinessException("本次导入的网银数据中没有解析到交易日期！");
		}
		if (CollectionUtils.isEmpty(list)) {
			throw new BussinessException("未解析到导入网银数据！");
		}
		// 获取已经执行过导入的日期
		Set<String> resultDateSet = interPayDtoMapper.selectDateExcutoed(dateSet);
		if (!CollectionUtils.isEmpty(resultDateSet)) {
			for (int i = 0; i < list.size(); i++) {
				InterPayDto dto = list.get(i);
				String subDate = subDateStr(dto.getTransactionTime());
				for (String thisDate : resultDateSet) {
					if (subDate.equals(thisDate)) {
						// 删除第i个元素
						list.remove(i);
						// 将循环指向上一个元素
						i--;
						break;
					}
				}
			}
		}
		
		return resultDateSet;

	}

	/**
	 * 创建一个线程执行网银数据的案号补全任务
	 */
	void newThreadExcutorCompletionCaseNumberTaskForInter() {
		try {
			// 暂停5s，充分等待事务提交
			Thread.sleep(5000);
			// 开始执行案号补全
			cpApi.casePerfectByInter();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 创建一个线程执行POS数据的案号补全任务
	 */
	void newThreadExcutorCompletionCaseNumberTaskForPOS() {
		
		try {
			// 暂停5s，充分等待事务提交
			Thread.sleep(5000);
			// 开始执行案号补全
			cpApi.casePerfectByPos();
		} catch (InterruptedException e) {
			e.printStackTrace();
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

				Map<String, Object> resolveMap = ExcelUtil.fileDecompose(temple, multipartFile);

				List<Map<String, Object>> fileData = (List<Map<String, Object>>) resolveMap.get("data");

				// 转化泛型格式
				List<PosPay> list = ParseUtil.parseAboutList(fileData, PosPay.class);

				// // 从第一行获取部门编号
				// String [] fristRow = (String[]) resolveMap.get("fristRow");
				// String departmentNumber = fristRow[1];

				for (int i = 0; i < list.size(); i++) {
					PosPay posPay = list.get(i);
					// 使用正则匹配序号列的每一个值是否为数字，如果不为数字，那么就直接跳过
					boolean door = (posPay.getNo().trim()).matches("[0-9]+");
					if (!door) {
						// 如果序号不是数字，那么这条数据将是无效数据，直接过滤
						list.remove(i);
						// 循环的小标倒退一格
						i--;
						continue;
					}
				}

				// 记录所有的案号
				Set<String> caseNumberSet = new HashSet<>();
				// 查询记录本次导入的日期范围
				Set<String> paramDateSet = new HashSet<>(3);
				// 获取批次号
				String batchNumber = BatchNumberUtil.createPosPayBatchNumber();
				// 获取当前登录的用户信息
				CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(userToken);

				// 截取有效的B1-B2-B3以及其他的特殊字段, 以及添加一些特定属性
				for (PosPay posPay : list) {
					// 对终端编号进行特定的处理
					posPay.setTerminalNumber(kacaStringfristAndEndStr(posPay.getTerminalNumber()));
					// 对卡号进行特定的处理
					posPay.setBankNumber(kacaStringfristAndEndStr(posPay.getBankNumber()));
					// 对交易解锁号进行特定的处理
					posPay.setTransactionSearcheNumber(kacaStringfristAndEndStr(posPay.getTransactionSearcheNumber()));
					// 对款项性质进行特定的处理
					posPay.setMoneyNature(kacaStringfristAndEndStrPlus(posPay.getMoneyNature()));
					// 对收款账号进行特定的处理
					posPay.setReceivablesNumber(kacaStringfristAndEndStrPlus(posPay.getReceivablesNumber()));
					// 对案号进行特定的处理
					posPay.setCaseNumber(kacaStringfristAndEndStrPlus(posPay.getCaseNumber()));
					// 设置主键Id
					posPay.setId(IdGenerator.getUUID());
					// 设置批次号
					posPay.setBatchNumber(batchNumber);
					// 设置登录用户ID
					posPay.setFormmakerId(loginUser.getUserId());
					// 设置登录用户名称
					posPay.setFormmakerName(loginUser.getLoginName());
					// 对POS数据的交易日期进行处理，将源数据日期往后推一天：如2017-12-12 需要处理为：2017-12-13
					posPay.setTransactionDate(getNextDayStr(posPay.getTransactionDate()));

					// 设置案号
					caseNumberSet.add(posPay.getCaseNumber());

					// 记录交易日期
					paramDateSet.add(posPay.getTransactionDate());
				}

				// 记录本次导入的总条数
				int total = list.size();
				LogUtils.debug("导入数据总条数：" + total);
				
				// 过滤已经导入的数据
				Set<String> resultDate = filterExcutePosData(paramDateSet, list);

				LogUtils.debug("过滤已经导入过得数据后剩余条数：：" + list.size());

				// 将案号ID设置到源数据行上
				setCaseIdToSourceData(list, caseNumberSet);

				// 将PosPay对象转化为PosPayDto
				List<PosPayDto> posPayDtoList = convertPosPayToPosPayDto(list);
				
				// 检查交易日期是否冻结
				Set<String> dateSpSet = new HashSet<>(3);
				for(PosPayDto ppd : posPayDtoList){
					String dateSpStr = subDateSpStr(ppd.getTransactionTime());
					dateSpSet.add(dateSpStr);
				}
				Set<String> lockDateSet = posPayDtoMapper.lockDataByParam(dateSpSet);
				if(!CollectionUtils.isEmpty(lockDateSet)){
					throw new BussinessException("交易日期已经被结算冻结，无法继续执行导入");
				}

				// 判断是否被过滤完
				if (!CollectionUtils.isEmpty(posPayDtoList)) {
					// 记录执行成功条数
					int excuteLine = 0;

					// 批量往数据库中插入导入的网友交易数据
					// 50条数据进行一次操作
					List<PosPayDto> excuteList = null;
					for (int i = 0; i < posPayDtoList.size(); i++) {
						// 每50条数据执行一次插入
						if (i % 50 == 0) {
							if (excuteList != null && excuteList.size() > 0) {
								excuteLine += posPayDtoMapper.batchInsert(excuteList);
							}
							// 设置数组初始长度
							int initLength = 0;
							if ((i + 1 + 50) > (posPayDtoList.size() - i)) {
								initLength = posPayDtoList.size() - i;
							} else {
								initLength = 50;
							}
							// 创建定长的ArrayList集合
							excuteList = new ArrayList<>(initLength);
						}
						excuteList.add(posPayDtoList.get(i));
					}
					// 执行剩下的部分未插入数据
					if (excuteList != null && excuteList.size() > 0) {
						excuteLine += posPayDtoMapper.batchInsert(excuteList);
					}
					// 创建新的个案信息
					createCaseInfo(posPayDtoList);
					
					LogUtils.debug("--创建个案信息成功后，开始执行POS数据的案号补全任务");
					// 创建一个线程执行Pos数据的案号补全任务
					newThreadExcutorCompletionCaseNumberTaskForPOS();
					//組裝提示消息
					String msg = "导入操作成功!<br />"
							+ "本次导入数据的交易日期为："+paramDateSet+"，历史导入数据中已经存在的交易日期有："+resultDate+"。"
							+ "<br />本次一共导入："+total+"条数据，因交易日期重复被过滤掉："+(total - posPayDtoList.size())+"条数据。"
							+ "<br />成功导入数据库："+excuteLine+"条数据，失败了："+(posPayDtoList.size() - excuteLine)+"条数据。";
					
					return ResultJson.buildSuccess(msg);
				}
				
				// 組裝提示消息
				String msg = "导入操作成功!<br />"
						+ "本次导入数据的交易日期为："+paramDateSet+"，历史导入数据中已经存在的交易日期有："+resultDate+"。"
						+ "<br />本次一共导入："+total+"条数据，因交易日期重复被过滤掉："+total+"条数据。"
						+ "<br />成功导入数据库：0条数据，失败了：0条数据。";
				return ResultJson.buildSuccess(msg);

			} catch (BussinessException e) {
				return ResultJson.buildError(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				return ResultJson.buildError("上传文件在解析过程中出现异常，请联系統管理人員及时处理!");
			} finally {
				// 释放锁
				posLock.unlock();
			}
		} else {
			return ResultJson.buildError("POS数据导入通道被占用，请先休息片刻再来尝试上传!");
		}

	}

	/**
	 * 创建个案
	 * 
	 * @param posPayDtoList
	 */
	private void createCaseInfo(List<PosPayDto> posPayDtoList) {
		// 文件导入数据成功导入数据库后,收集案号和款项性质
		// 去重案号和款项性质
		Set<String> setDatas = new HashSet<>();
		for (PosPayDto dto : posPayDtoList) {
			JSONObject obj = new JSONObject(2);
			obj.put("caseNumber", dto.getCaseNumber());
			obj.put("moneyNature", dto.getMoneyNature());
			setDatas.add(obj.toString());
		}

		List<Map<String, String>> otherList = new ArrayList<>(setDatas.size());
		for (String jsonStr : setDatas) {
			Map<String, String> map = (Map<String, String>) JSONObject.parse(jsonStr);
			otherList.add(map);
		}
		// 过滤已经存在的案号和款项性质
		Set<String> caseHave = caseApi.checkCaseHave(otherList);
		for (int i = 0; i < otherList.size(); i++) {
			Map<String, String> map = otherList.get(i);
			for (String hCase : caseHave) {
				if (map.get("caseNumber").equals(hCase)) {
					// 清除当前元素
					otherList.remove(i);
					// 指针回调一格
					i--;
					break;
				}
			}
		}
		
		// 过滤完成以后，将没有被过滤的插入到个案表中
		caseApi.createMore(otherList);
		

	}

	/**
	 * 将List<PosPay> 转化为 List<PosPayDto>
	 * 
	 * @param freeDataList
	 * @return List<PosPayDto>
	 * @throws ParseException
	 */
	private List<PosPayDto> convertPosPayToPosPayDto(List<PosPay> freeDataList) throws ParseException {
		// 校验是否为空
		if (CollectionUtils.isEmpty(freeDataList)) {
			return null;
		}

		// 创建相同大小的容器集合
		List<PosPayDto> resultList = new ArrayList<>(freeDataList.size());
		for (PosPay pp : freeDataList) {
			PosPayDto dto = new PosPayDto();
			// 设置终端编号
			dto.setTerminalNumber(pp.getTerminalNumber());
			// 设置交易时间
			{
				String date = pp.getTransactionDate();
				String time = pp.getTransactionTime();
				if (StringUtils.isEmpty(date)) {
					throw new BussinessException("交易日期存在为空值");
				}
				if (StringUtils.isEmpty(time)) {
					throw new BussinessException("交易时间存在空值");
				}
				String dateTime = date + " " + time;
				// 日期设置
				dto.setTransactionTime(sdfEx.parse(dateTime));
			}
			// 银行卡号
			dto.setBankNumber(pp.getBankNumber());
			// 交易金额
			dto.setTransationPrice(makeMoney(pp.getTransationPrice()));
			// 红利收入
			dto.setDividendIncome(makeMoney(pp.getDividendIncome()));
			// 红利支出
			dto.setDividendExpenditure(makeMoney(pp.getDividendExpenditure()));
			// 小费
			dto.setDumbo(makeMoney(pp.getDumbo()));
			// 手续费
			dto.setServiceCharge(makeMoney(pp.getServiceCharge()));
			// 分期付款手续费
			dto.setInstallmentFee(makeMoney(pp.getInstallmentFee()));
			// 净收金额
			dto.setNetProceeds(makeMoney(pp.getNetProceeds()));
			// 交易检索号
			dto.setTransactionSearcheNumber(pp.getTransactionSearcheNumber());
			// 交易类型
			dto.setTransactionType(pp.getTransactionType());
			// 款项性质
			dto.setMoneyNature(pp.getMoneyNature());
			// 收款账号
			dto.setReceivablesNumber(pp.getReceivablesNumber());
			// 案号ID
			dto.setCaseId(pp.getCaseId());
			// 案号
			dto.setCaseNumber(pp.getCaseNumber());
			// 批次号
			dto.setBatchNumber(pp.getBatchNumber());
			// ID
			dto.setId(pp.getId());
			// 创建人
			dto.setFormmakerId(pp.getFormmakerId());
			// 创建人名
			dto.setFormmakerName(pp.getFormmakerName());

			// 生成凭证号字符串
			String voucherNo = BatchNumberUtil.createVoucherNo(dto.getTransactionTime());
			// 设置凭证号
			dto.setVoucherNo(voucherNo);

			resultList.add(dto);
		}

		return resultList;
	}

	/**
	 * 将中文字符的金额转成 BigDecimal
	 * 
	 * @param priceStr
	 * @return
	 */
	private BigDecimal makeMoney(String priceStr) throws ParseException {
		if (StringUtils.isEmpty(priceStr)) {
			return BigDecimal.ZERO;
		} else {
			// 处理可能包含的金额分隔符
			priceStr = priceStr.replace(",", "").replace("，", "");
			return new BigDecimal(priceStr);
		}
	}

	/**
	 * 过滤掉已经导入过得pos数据
	 * 
	 * @param paramDateSet
	 *            本次导入的时间范围
	 * @param list
	 *            本次导入的pos数据
	 */
	private Set<String> filterExcutePosData(Set<String> paramDateSet, List<PosPay> list) {

		LogUtils.debug("开始过滤导入的pos数据中已经被导入的数据...");

		if (CollectionUtils.isEmpty(paramDateSet)) {
			throw new BussinessException("本次导入的POS数据中没有解析到交易日期！");
		}
		if (CollectionUtils.isEmpty(list)) {
			throw new BussinessException("未解析到本次导入的pos数据");
		}

		// 查询本次导入的时间范围中有哪些日期已经被导入
		Set<String> resultDateSet = posPayDtoMapper.selectExcutedDate(paramDateSet);
		if (!CollectionUtils.isEmpty(resultDateSet)) {
			for (int i = 0; i < list.size(); i++) {
				PosPay pos = list.get(i);
				String thisDate = pos.getTransactionDate();
				for (String date : resultDateSet) {
					if (thisDate.equals(date)) {
						// 删除第i个元素
						list.remove(i);
						// 下标回滚
						i--;
						break;
					}
				}
			}
		}
		
		return resultDateSet;
	}

	/**
	 * 对字符串进行特定的处理(去掉[ ="" ]) 针对： （1）：="627067******6170" 进行处理
	 * 
	 * @param strPlus
	 * @return
	 */
	private String kacaStringfristAndEndStr(String strPlus) {
		// 如果是“=”开始
		if (strPlus != null && strPlus.trim().startsWith("=")) {
			String tmpStr = strPlus.trim().substring(1);
			// 替换前后的双引号
			return tmpStr.replace("\"", "").trim();
		}
		return strPlus;
	}

	/**
	 * 对字符串进行特定的处理(去掉[ ="BN=" ]) 针对： (1): ="B1=诉讼款 (2):B2=9558854402005419158
	 * (3):B3=(2017)川0112财保89号 以上三种情况进行处理
	 * 
	 * @param strPlus
	 * @param blur
	 * @return
	 */
	private String kacaStringfristAndEndStrPlus(String strPlus) {
		// 如果是“=”开始
		if (strPlus != null && strPlus.trim().startsWith("=")) {
			String tmpStr = strPlus.trim().substring(1);
			// 替换前后的双引号
			String thisPlus = tmpStr.replace("\"", "").trim();
			// 如果是B开头
			if (thisPlus.startsWith("B")) {
				return thisPlus.substring(3);
			}
			return thisPlus;
		} else if (strPlus != null && strPlus.trim().startsWith("B")) {
			// 替换前后的双引号
			return strPlus.replace("\"", "").trim().substring(3);
		}
		return strPlus;
	}

	/**
	 * 将案号ID设置到源数据行上
	 * 
	 * @param freeDataList
	 * @param caseNumberList
	 */
	private void setCaseIdToSourceData(List<PosPay> freeDataList, Set<String> caseNumberSet) {

		LogUtils.debug("开始将案号ID设置到源数据行上");

		// 判断空
		if (freeDataList.isEmpty() || caseNumberSet.isEmpty()) {
			return;
		}

		// 案号列表
		List<String> caseNumbers = new ArrayList<>(caseNumberSet);
		List<CaseInfo> caseInfos = caseApi.getCaseInfoByCaseNumbers(caseNumbers);

		// 判断空
		if (caseInfos.isEmpty()) {
			return;
		}

		// 循环匹配补全个案Id
		for (PosPay entity : freeDataList) {
			String caseNumber = entity.getCaseNumber();
			for (CaseInfo caseEntity : caseInfos) {
				// 判断案号是否匹配
				if (caseNumber.equals(caseEntity.getCaseNumber())) {
					// 补全pos数据中的案号Id
					entity.setCaseId(caseEntity.getId());
					break;
				}
			}
		}
	}

	/**
	 * 获取指定时间字符串的前一天日期字符串
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	private String getNextDayStr(String dateStr) throws ParseException {
		if (StringUtils.isEmpty(dateStr)) {
			return null;
		}

		dateStr = dateStr.replace("/", "-");

		// 将String转Date
		Date date = sdf.parse(dateStr + " 00:00:00");
		// 获取date的下一天日期
		Date nextDay = DateUtils.someDaysAgo(date, 1);
		return sdf.format(nextDay);
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

	@Override
	public ResultJson isEdit(String dateStr, String sysClick) {
		try {
			List<Map<String, Object>> dataList = null;
			if (DataCons.SOURCE_INTER.equals(sysClick)) {
				// 网银数据
				dataList = interPayDtoMapper.isEditData(dateStr);
			}else{
				// POS数据
				dataList = posPayDtoMapper.isEditData(dateStr);
			}
			Set<String> dateSet = new HashSet<>(dataList.size());
			for(Map<String, Object> map : dataList){
				dateSet.add(String.valueOf(map.get("dateStr")));
			}
			
			List<Map<String, Object>> resultList = new ArrayList<>(dateSet.size());
			for(String ds : dateSet){
				Map<String,Object> thisMap = new HashMap<String, Object>(3);
				thisMap.put("dateStr", ds);
				for(Map<String, Object> map : dataList){
					String thisDateStr = String.valueOf(map.get("dateStr"));
					Integer num = Integer.parseInt(String.valueOf(map.get("num")));
					Integer flag = Integer.parseInt(String.valueOf(map.get("flag")));
					
					if(ds.equals(thisDateStr)){
						
						if(flag.equals(CommonCons.yes)){
							thisMap.put("isEdit", num);
						}else{
							thisMap.put("notEdit", num);
						}
					}
				}
				
				resultList.add(thisMap);
			}
			return ResultJson.buildSuccess("查询成功", resultList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误");
		}
	}

	@Override
	public ResultJson homeData(String dateStr) throws Exception {
		if(StringUtils.isEmpty(dateStr)){
			return ResultJson.buildError("参数异常");
		}
		
		// 生成当月的日期数组
		Date first = sdfSp.parse(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(first);
		int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String [] dates = new String[maxDate];
		for (int i = 1; i <= maxDate; i++) {
			if(i < 10){
				dates[i-1] = dateStr + "-0"+i;
			}else{
				dates[i-1] = dateStr + "-"+i;
			}
		}
		
		// pos导入
		Set<String> posDateSet = posPayDtoMapper.guideResp(dateStr);
		// 网银导入
		Set<String> interDateSet = interPayDtoMapper.guideResp(dateStr);
		// 结算
		Set<String> dateSet = posPayDtoMapper.lockData(dateStr);
		// pos补录
		Set<String> posSupplementDateSet = posSupplement(dateStr);
		// 网银补录
		Set<String> interSupplementDateSet = interSupplement(dateStr); 
		
		List<HomeEntity> list = new ArrayList<>();
		for(String dtStr : dates){
			HomeEntity entity = new HomeEntity();
			entity.setDateStr(dtStr);
			// pos导入
			for(String pos : posDateSet){
				if(dtStr.equals(pos)){
					entity.setPosImport(true);
				}
			}
			// 网银导入
			for(String inter : interDateSet){
				if(dtStr.equalsIgnoreCase(inter)){
					entity.setInterImport(true);
				}
			}
			// 结算
			for(String dateC : dateSet){
				if(dtStr.equals(dateC)){
					entity.setLockInfo(true);
				}
			}
			// pos补录
			for(String pos : posSupplementDateSet){
				if(dtStr.equals(pos)){
					entity.setPosSupplement(true);
				}
			}
			// 网银补录
			for(String inter : interSupplementDateSet){
				if(dtStr.equals(inter)){
					entity.setInterSupplement(true);
				}
			}
			
			list.add(entity);
		}
		
		List<Object[]> resultData = new ArrayList<>(list.size());
		for(HomeEntity entity : list){
			Object [] data = new Object[6];
			data[0] = entity.getDateStr();
			data[1] = entity.isPosImport();
			data[2] = entity.isInterImport();
			data[3] = entity.isLockInfo();	
			data[4]	= entity.isPosSupplement();		
			data[5] = entity.isInterSupplement();
			resultData.add(data);
		}
		
		
		return ResultJson.buildSuccess("查询成功", resultData);
	}

	private Set<String> interSupplement(String dateStr) {
		List<String> dateList = interPayDtoMapper.supplement(dateStr);
		
		Set<String> dateSet = new HashSet<>(dateList.size());
		for(String date :dateList){
			dateSet.add(date);
		}
		
		Set<String> resultSet = new HashSet<>(dateList.size());
		
		for(String date : dateSet){
			int size = 0;
			for(String ds :dateList){
				if(date.equals(ds)){
					size++;
				}
			}
			// 数据条数
			Integer dates = interPayDtoMapper.supplementLength(date);
			if(size == dates.intValue()){
				resultSet.add(date);
			}
		}
		
		return resultSet;
	}

	private Set<String> posSupplement(String dateStr) {
		// 拿到所有补录过得日期
		List<String> dateList = posPayDtoMapper.supplement(dateStr); 
		Set<String> dateSet = new HashSet<>(dateList.size());
		for(String date :dateList){
			dateSet.add(date);
		}
		
		Set<String> resultSet = new HashSet<>(dateList.size());
		
		for(String date : dateSet){
			int size = 0;
			for(String ds :dateList){
				if(date.equals(ds)){
					size++;
				}
			}
			// 数据条数
			Integer dates = posPayDtoMapper.supplementLength(date);
			if(size == dates.intValue()){
				resultSet.add(date);
			}
			
		}
		
		return resultSet;
	}


}
