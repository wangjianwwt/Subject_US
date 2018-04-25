package com.born.bc.basicdata.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.born.bc.basicdata.dao.CourtPayOrderMapper;
import com.born.bc.basicdata.entity.CourtPayOrder;
import com.born.bc.basicdata.entity.CourtPayOrderParamDto;
import com.born.bc.basicdata.entity.cons.CourtPayOrderStatusEnum;
import com.born.bc.basicdata.service.api.CourtPayOrderServiceApi;
import com.born.bc.caseinfo.dao.CaseInfoMapper;
import com.born.bc.caseinfo.entity.CaseInfo;
import com.born.bc.caseinfo.entity.cons.CaseInfoCons;
import com.born.bc.caseinfo.entity.cons.CaseInfoStatusEnum;
import com.born.bc.commons.entity.CurrentLoginUser;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.DateFormartUtil;
import com.born.bc.commons.utils.DateUtils;
import com.born.bc.commons.utils.IdGenerator;
import com.born.bc.commons.utils.LoginUserInfoUtil;
import com.born.bc.commons.utils.ResultJson;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

@Service
public class CourtPayOrderServiceImpl implements CourtPayOrderServiceApi {

	@Autowired
	private CourtPayOrderMapper mapper;
	@Autowired
	private CaseInfoMapper caseMapper;

	@Value("${sheet.header}")
	private String sheetHeader;

	@Override
	public ResultJson list(CourtPayOrderParamDto dto, PageParamVO page) {
		try {
			if (!StringUtils.isEmpty(dto.getStartDateStr())) {
				Date startDate = DateFormartUtil.getDateTime(dto.getStartDateStr() + " 00:00:00");
				dto.setStartDate(startDate);
			}
			if (!StringUtils.isEmpty(dto.getEndDateStr())) {
				Date endDate = DateFormartUtil.getDateTime(dto.getEndDateStr() + " 00:00:00");
				dto.setEndDate(endDate);
			}

			PageHelper.startPage(page.getPage(), page.getLimit());
			List<CourtPayOrder> list = mapper.selectByList(dto);
			// 设置单据状态
			setStatus(list);
			PageInfo<CourtPayOrder> pageInfo = new PageInfo<CourtPayOrder>(list);
			return ResultJson.buildSuccess("查询成功！", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误！");
		}
	}

	/**
	 * 计算并设置单据状态
	 * 
	 * @param list
	 */
	private void setStatus(List<CourtPayOrder> list) {

		if (CollectionUtils.isEmpty(list)) {
			return;
		}

		for (CourtPayOrder order : list) {

			BigDecimal amount = order.getAmount();
			BigDecimal interAmount = order.getInterAmount();

			// 网银没有发生金额的时候，默认正在支付
			if (interAmount.doubleValue() != 0) {
				// 如果 支付单金额为： 10
				// 网银金额（借方金额-贷方金额）为： -10

				BigDecimal tpPrice = amount.subtract(interAmount);
				// 相减等于0的情况，表示退款
				if (tpPrice.compareTo(BigDecimal.ZERO) == 0) {
					// 金额比较相同时，表示已经退款
					order.setStatus(CourtPayOrderStatusEnum.Status_Return.getValue());
				} else {

					BigDecimal dividePrice = tpPrice.divide(amount);
					if (dividePrice.doubleValue() == 2) {
						// 相减为2倍的情况就是付款成功
						order.setStatus(CourtPayOrderStatusEnum.Status_Finish.getValue());
					} else {
						// 设置正在支付
						order.setStatus(CourtPayOrderStatusEnum.Status_Paiing.getValue());
					}
				}
			} else {
				// 设置正在支付
				order.setStatus(CourtPayOrderStatusEnum.Status_Paiing.getValue());
			}

		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson edit(CourtPayOrder entity) throws BussinessException {

		if (StringUtils.isEmpty(entity.getId())) {
			return ResultJson.buildError("缺乏主键！");
		}
		// 校验数据
		validateCourtPayOrder(entity);

		// 校验金额是否有效
		entity.setAmount(checkAmountEffective(entity.getAmount()));
		
		// 检查case
		checkCaseInfoHaving(entity);

		mapper.updateByPrimaryKeySelective(entity);
		return ResultJson.buildSuccess("修改成功!");
	}

	/**
	 * 检查case
	 * <p>
	 * Title: checkCaseInfoHaving
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param entity
	 */
	private void checkCaseInfoHaving(CourtPayOrder entity) {
		if (!StringUtil.isEmpty(entity.getCaseNumber())) {
			List<String> list = new ArrayList<>(1);
			list.add(entity.getCaseNumber());
			List<CaseInfo> resultList = caseMapper.getCaseInfoByCaseNumbers(list);
			if (CollectionUtils.isEmpty(resultList)) {
				String id = IdGenerator.getUUID();
				CaseInfo caseInfo = new CaseInfo();
				caseInfo.setId(id);
				caseInfo.setCaseNumber(entity.getCaseNumber());
				caseInfo.setReceivableAmount(BigDecimal.ZERO);
				caseInfo.setProcessStatus(CaseInfoStatusEnum.ProcessStatus_Receivables.getValue());
				caseInfo.setCreateChannel(CaseInfoCons.create_for_pay);
				caseInfo.setUserToken(entity.getUserToken());

				// 设置登录用户
				CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(entity.getUserToken());
				caseInfo.setFormmakerId(loginUser.getUserId());
				caseInfo.setFormmakerName(loginUser.getLoginName());
				caseMapper.insertSelective(caseInfo);
				entity.setCaseId(id);
			} else {
				CaseInfo caseInfo = resultList.get(0);
				entity.setCaseId(caseInfo.getId());
			}
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson add(CourtPayOrder entity) throws BussinessException {

		// 校验数据
		validateCourtPayOrder(entity);
		// 校验金额是否有效
		entity.setAmount(checkAmountEffective(entity.getAmount()));

		// 检查case
		checkCaseInfoHaving(entity);

		// 设置登录用户
		CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(entity.getUserToken());

		entity.setFormmakerId(loginUser.getUserId());
		entity.setFormmakerName(loginUser.getLoginName());
		entity.setId(IdGenerator.getUUID());

		mapper.insertSelective(entity);
		return ResultJson.buildSuccess("新增成功!");
	}

	/**
	 * 检查金额是否有效
	 * 
	 * @param amount
	 * @return
	 */
	private BigDecimal checkAmountEffective(BigDecimal amount) {
		// 将金额字符串转换为BigDecimal
		BigDecimal money = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
		// 设置单笔金额上限为固定的 60万
		BigDecimal upperMoney = BigDecimal.valueOf(600000.00);
		if (money.compareTo(upperMoney) > 0) {
			throw new BussinessException("单笔支付金额不得超过60万元!");
		}
		return money;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson delete(List<String> ids) throws BussinessException {
		if (ids == null || ids.size() < 1) {
			return ResultJson.buildParamError("参数异常!");
		}
		mapper.updateDelStatusByPrimaryKey(ids);
		return ResultJson.buildSuccess("删除成功!");
	}

	@Override
	public ResultJson getById(String id) {
		try {
			CourtPayOrder entity = mapper.selectByPrimaryKey(id);
			return ResultJson.buildSuccess("查询成功！", entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败！");
		}
	}

	@Override
	public ResultJson export(CourtPayOrderParamDto dto, HttpServletResponse response) {

		boolean isSuccess = false;
		try {
			// 处理日期
			if (!StringUtils.isEmpty(dto.getEndDateStr())) {
				Date endDate = DateFormartUtil.getDateTime(dto.getEndDateStr() + " 00:00:00");
				dto.setEndDate(DateUtils.tomorrowStartTime(endDate));
			}
			if (!StringUtils.isEmpty(dto.getStartDateStr())) {
				Date startDate = DateFormartUtil.getDateTime(dto.getStartDateStr() + " 00:00:00");
				dto.setStartDate(DateUtils.todayStartTime(startDate));
			}

			List<CourtPayOrder> list = mapper.selectByList(dto);
			// 生成文件
			HSSFWorkbook workbook = createXlsBook(list);

			String fileName = "网银支付数据";
			response.setContentType("text/html;charset=UTF-8");
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1") + ".xls");
			OutputStream ouputStream = response.getOutputStream();
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
			isSuccess = true;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return ResultJson.buildError(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// lock.unlock();
		}
		if (isSuccess) {
			return ResultJson.buildSuccess("导出成功!");
		}

		return ResultJson.buildError("导出错误!");
	}

	/**
	 * 生成Excel文件（法院支付导出专用）
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public HSSFWorkbook createXlsBook(List<CourtPayOrder> list) throws Exception {

		// 判断空
		if (list == null || list.size() < 1)
			return null;
		// // 这里设置一万条导出数据为上限，超过就抛出异常
		// if (list.size() > 10000){
		// throw new RuntimeException("请求导出的数据量过大，将日期范围缩小再进行导出!");
		// }
		// 生成book
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一页表格
		HSSFSheet sheet = workbook.createSheet();

		String sheetHeaderStr = new String(sheetHeader.getBytes("ISO-8859-1"), "UTF-8");

		// 设置表头
		String[] headers = sheetHeaderStr.split(",");
		// 创建表头行
		HSSFRow headerRow = sheet.createRow(0);
		// 给row设置值
		for (int i = 0; i < headers.length; i++) {
			// 创建单元格并且给单元格设置对应的值
			headerRow.createCell(i).setCellValue(headers[i]);
			// 设置每列的长度
			sheet.setColumnWidth(i, 100 * 28);
			headerRow.setHeightInPoints(20);
		}

		// 设置内容
		for (int i = 0; i < list.size(); i++) {
			// 该行数据
			CourtPayOrder entity = list.get(i);
			// 创建行
			HSSFRow row = sheet.createRow(i + 1);
			// 设置币种
			row.createCell(0).setCellValue(entity.getCurrency());
			// 设置日期
			if (entity.getDate() == null) {
				row.createCell(1).setCellValue("");
			} else {
				String dateStr = DateFormartUtil.getDateStr(entity.getDate());
				row.createCell(1).setCellValue(dateStr);
			}
			// 设置标志明细
			row.createCell(2).setCellValue(entity.getDetailTitle());
			// 设置顺序号，行号
			row.createCell(3).setCellValue(i + 1);
			// 设置付款账号开户行
			row.createCell(4).setCellValue(entity.getPaymentDepositBank());
			// 付款账号/卡号
			row.createCell(5).setCellValue(entity.getPaymentNumber());
			// 付款账号名称/卡名称
			row.createCell(6).setCellValue(entity.getPaymentName());
			// 收款账号开户行
			row.createCell(7).setCellValue(entity.getReceivablesDepositBank());
			// 收款账号省份
			row.createCell(8).setCellValue(entity.getReceivablesProvince());
			// 收款账号地市
			row.createCell(9).setCellValue(entity.getReceivablesCity());
			// 收款账号地区码
			row.createCell(10).setCellValue(entity.getReceivablesAreaCode());
			// 收款账号
			row.createCell(11).setCellValue(entity.getReceivablesNumber());
			// 收款账号名称
			row.createCell(12).setCellValue(entity.getReceivablesAccountName());
			// 金额
			row.createCell(13).setCellValue(entity.getAmount().doubleValue());
			// 汇款用途
			row.createCell(14).setCellValue(entity.getRemittancePurpose());

			// 组装备注， 备注 = （备注+案号）
			String remarks = (StringUtils.isEmpty(entity.getRemarks()) ? entity.getCaseNumber()
					: entity.getRemarks() + "#&" + entity.getCaseNumber());
			// 备注信息
			row.createCell(15).setCellValue(remarks);

			// 设置汇款方式，金额小于等于50000元为“0”，其他为“1”
			// 取金额字段，保留两位小数，四舍五入。
			BigDecimal money = entity.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
			// 定额50000
			BigDecimal upperMoney = BigDecimal.valueOf(50000);
			if (money.compareTo(upperMoney) > 0) {
				// 汇款方式
				row.createCell(16).setCellValue(1);
			} else {
				// 汇款方式
				row.createCell(16).setCellValue(0);
			}

			// 收款账户短信通知手机号码
			row.createCell(17).setCellValue(entity.getReceivablesPhone());
			// 自定义序号
			row.createCell(18).setCellValue(i + 1);
		}
		return workbook;
	}

	/**
	 * 校验法院支付单非空
	 * 
	 * @param entity
	 */
	private void validateCourtPayOrder(CourtPayOrder entity) {
		if (StringUtils.isEmpty(entity.getCaseNumber())) {
			throw new BussinessException("未关联案号！");
		}
		if (entity.getDate() == null) {
			throw new BussinessException("未填写日期！");
		}
		if (StringUtils.isEmpty(entity.getReceivablesDepositBank())) {
			throw new BussinessException("未收写款账号开户行！");
		}
		// if (StringUtils.isEmpty(entity.getReceivablesProvince())) {
		// throw new BussinessException("未填写收款账号省份！");
		// }
		// if (StringUtils.isEmpty(entity.getReceivablesCity())) {
		// throw new BussinessException("未填写收款账号地市！");
		// }
		if (StringUtils.isEmpty(entity.getReceivablesAreaCode())) {
			throw new BussinessException("未填写收款账号地区码！");
		}
		if (StringUtils.isEmpty(entity.getReceivablesNumber())) {
			throw new BussinessException("未填写收款账号！");
		}
		if (StringUtils.isEmpty(entity.getReceivablesAccountName())) {
			throw new BussinessException("未填写收款账号名称！");
		}
		if (entity.getAmount() == null) {
			throw new BussinessException("未填写金额！");
		}
		if (StringUtils.isEmpty(entity.getRemittancePurpose())) {
			throw new BussinessException("未填写汇款用途！");
		}
		if (StringUtils.isEmpty(entity.getPaymentDepositBank())) {
			throw new BussinessException("未填写付款账号开户行");
		}
		if (StringUtils.isEmpty(entity.getPaymentNumber())) {
			throw new BussinessException("未填写付款账号/卡号");
		}
		if (StringUtils.isEmpty(entity.getPaymentName())) {
			throw new BussinessException("付款账号名称/卡名称");
		}

	}

}
