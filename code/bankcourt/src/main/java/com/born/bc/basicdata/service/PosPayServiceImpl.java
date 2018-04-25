package com.born.bc.basicdata.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.born.bc.basicdata.dao.PosPayDtoMapper;
import com.born.bc.basicdata.entity.PosPay;
import com.born.bc.basicdata.entity.PosPayDto;
import com.born.bc.basicdata.entity.PosPayParamDto;
import com.born.bc.basicdata.entity.ZoomPosParamDto;
import com.born.bc.basicdata.service.api.PosPayServiceApi;
import com.born.bc.basicdata.util.BatchNumberUtil;
import com.born.bc.caseinfo.dao.CaseInfoMapper;
import com.born.bc.caseinfo.entity.CaseInfo;
import com.born.bc.caseinfo.entity.cons.CaseInfoCons;
import com.born.bc.caseinfo.entity.cons.CaseInfoStatusEnum;
import com.born.bc.commons.entity.CommonCons;
import com.born.bc.commons.entity.CurrentLoginUser;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.DateUtils;
import com.born.bc.commons.utils.IdGenerator;
import com.born.bc.commons.utils.LoginUserInfoUtil;
import com.born.bc.commons.utils.ResultJson;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * pos网银
 * 
 * @author wangjian
 */
@Service
public class PosPayServiceImpl implements PosPayServiceApi {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private PosPayDtoMapper mapper;

	@Autowired
	private CaseInfoMapper caseMapper;

	@Override
	public ResultJson list(PosPayParamDto dto, PageParamVO page) {
		try {
			if (!StringUtils.isEmpty(dto.getStartDateStr())) {
				dto.setStartDate(sdf.parse(dto.getStartDateStr() + " 00:00:00"));
			}
			if (!StringUtils.isEmpty(dto.getEndDateStr())) {
				dto.setEndDate(sdf.parse(dto.getEndDateStr() + " 00:00:00"));
			}
			dto.setEndDate(DateUtils.tomorrowStartTime(dto.getEndDate()));
			dto.setStartDate(DateUtils.todayStartTime(dto.getStartDate()));
			PageHelper.startPage(page.getPage(), page.getLimit());
			List<PosPay> list = mapper.selectByList(dto);
			PageInfo<PosPay> pageInfo = new PageInfo<PosPay>(list);
			return ResultJson.buildSuccess("查询成功！", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误！");
		}
	}

	@Override
	public ResultJson getSumDataByListParam(PosPayParamDto dto) {
		try {
			if (!StringUtils.isEmpty(dto.getStartDateStr())) {
				dto.setStartDate(sdf.parse(dto.getStartDateStr() + " 00:00:00"));
			}
			if (!StringUtils.isEmpty(dto.getEndDateStr())) {
				dto.setEndDate(sdf.parse(dto.getEndDateStr() + " 00:00:00"));
			}
			dto.setEndDate(DateUtils.tomorrowStartTime(dto.getEndDate()));
			dto.setStartDate(DateUtils.todayStartTime(dto.getStartDate()));
			ZoomPosParamDto resultDto = mapper.getSumDataByListParam(dto);
			return ResultJson.buildSuccess("查询成功", resultDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询统计错误，请稍后再试！");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson update(PosPayDto entity) throws Exception {

		// 检查是否结算（上锁）
		PosPayDto posPayDto = mapper.selectByPrimaryKey(entity.getId());
		if (CommonCons.yes.equals(posPayDto.getIsLock())) {
			return ResultJson.buildError("结算后的数据不能被编辑");
		}

		// 检查案号
		checkCaseHaving(entity);

		// 设置是否编辑标识
		setIsEdit(entity);

		entity.setIsEdit(CommonCons.yes);
		mapper.updateByPrimaryKeySelective(entity);
		return ResultJson.buildSuccess("修改成功!");
	}

	/**
	 * 检查案号是否存在
	 * <p>
	 * Title: checkCaseHaving
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @param entity
	 */
	public void checkCaseHaving(PosPayDto entity) {
		// 用案号判断案号是否存在数据库中
		if (!StringUtils.isEmpty(entity.getCaseNumber())) {
			List<String> list = new ArrayList<>(1);
			list.add(entity.getCaseNumber());
			List<CaseInfo> resultList = caseMapper.getCaseInfoByCaseNumbers(list);
			if (CollectionUtils.isEmpty(resultList)) {
				// 案号不存在，将自动生成案号信息插入案号表
				CaseInfo caseInfo = new CaseInfo();
				caseInfo.setId(IdGenerator.getUUID());
				caseInfo.setCaseNumber(entity.getCaseNumber());
				caseInfo.setMoneyNatureId(entity.getSubjectId());
				caseInfo.setMoneyNatureName(entity.getSubjectName());
				caseInfo.setReceivableAmount(BigDecimal.ZERO);
				caseInfo.setProcessStatus(CaseInfoStatusEnum.ProcessStatus_Receivables.getValue());
				caseInfo.setCreateChannel(CaseInfoCons.create_for_pos);
				// 开始执行添加案号
				caseMapper.insertSelective(caseInfo);
				entity.setCaseId(caseInfo.getId());
			} else {
				CaseInfo caseInfo = resultList.get(0);
				entity.setCaseId(caseInfo.getId());
			}
		}
	}

	/**
	 * 设置是否编辑标识
	 * 
	 * @param entity
	 */
	private void setIsEdit(PosPayDto entity) {

		// 判断是否编辑
		if (StringUtils.isEmpty(entity.getCaseId()) || StringUtils.isEmpty(entity.getCaseNumber())
				|| StringUtils.isEmpty(entity.getSubjectId()) || StringUtils.isEmpty(entity.getSubjectName())
				|| StringUtils.isEmpty(entity.getChikar())) {
			entity.setIsEdit(CommonCons.no);
		} else {
			entity.setIsEdit(CommonCons.yes);
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson add(PosPayDto entity) throws Exception {

		// 设置登录用户
		CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(entity.getUserToken());
		entity.setFormmakerId(loginUser.getUserId());
		entity.setFormmakerName(loginUser.getLoginName());
		entity.setId(IdGenerator.getUUID());

		// 生成凭证号字符串
		String voucherNo = BatchNumberUtil.createVoucherNo(entity.getTransactionTime());
		entity.setVoucherNo(voucherNo);

		// 检查案号
		checkCaseHaving(entity);

		// 设置是否编辑标识
		setIsEdit(entity);

		List<PosPayDto> list = new ArrayList<PosPayDto>();
		list.add(entity);
		mapper.batchInsert(list);
		return ResultJson.buildSuccess("新增成功!");
	}

	@Override
	public ResultJson getById(String id) {
		try {
			PosPayDto entity = mapper.selectByPrimaryKey(id);
			return ResultJson.buildSuccess("查询成功", entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败！");
		}
	}

}
