package com.born.bc.basicdata.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.born.bc.basicdata.dao.InterPayDtoMapper;
import com.born.bc.basicdata.entity.InterPayDto;
import com.born.bc.basicdata.entity.InterPayParamDto;
import com.born.bc.basicdata.entity.SplitDetailVO;
import com.born.bc.basicdata.entity.SplitVO;
import com.born.bc.basicdata.entity.ZoomInterResultDto;
import com.born.bc.basicdata.service.api.InterPayServiceApi;
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
 * 网银Service
 * 
 * @author wangjian
 *
 */
@Service
public class InterPayServiceImpl implements InterPayServiceApi {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private InterPayDtoMapper mapper;
	@Autowired
	private CaseInfoMapper caseMapper;

	@Override
	public ResultJson list(InterPayParamDto dto, PageParamVO page) {
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
			List<InterPayDto> list = mapper.selectByList(dto);
			PageInfo<InterPayDto> pageInfo = new PageInfo<InterPayDto>(list);
			return ResultJson.buildSuccess("查询成功！", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误！");
		}
	}

	@Override
	public ResultJson getSumDataByListParam(InterPayParamDto dto) {
		try {
			if (!StringUtils.isEmpty(dto.getStartDateStr())) {
				dto.setStartDate(sdf.parse(dto.getStartDateStr() + " 00:00:00"));
			}
			if (!StringUtils.isEmpty(dto.getEndDateStr())) {
				dto.setEndDate(sdf.parse(dto.getEndDateStr() + " 00:00:00"));
			}
			dto.setEndDate(DateUtils.tomorrowStartTime(dto.getEndDate()));
			dto.setStartDate(DateUtils.todayStartTime(dto.getStartDate()));
			ZoomInterResultDto resultDto = mapper.getSumDataByListParam(dto);
			return ResultJson.buildSuccess("查询成功", resultDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询统计出错,请稍后重试！");
		}
	}

	@Override
	public ResultJson getById(String id) {
		try {
			InterPayDto entity = mapper.selectByPrimaryKey(id);
			return ResultJson.buildSuccess("查询成功！", entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败！");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson update(InterPayDto entity) throws Exception {

		// 检查是否结算（上锁）
		InterPayDto interPayDto = mapper.selectByPrimaryKey(entity.getId());
		if (CommonCons.yes.equals(interPayDto.getIsLock())) {
			return ResultJson.buildError("结算后的数据不能被编辑");
		}

		// 检查案号
		checkCaseHaving(entity);

		// 设置是否编辑标识
		setIsEdit(entity);

		mapper.updateByPrimaryKeySelective(entity);
		return ResultJson.buildSuccess("修改成功!");
	}

	/**
	 * 设置是否编辑标识
	 * 
	 * @param entity
	 */
	private void setIsEdit(InterPayDto entity) {
		// 判断是否编辑
		if (StringUtils.isEmpty(entity.getCaseId()) || StringUtils.isEmpty(entity.getCaseNumber())
				|| StringUtils.isEmpty(entity.getSubjectId()) || StringUtils.isEmpty(entity.getSubjectName())
				|| StringUtils.isEmpty(entity.getCorporateName())) {
			entity.setIsEdit(CommonCons.no);
		} else {
			entity.setIsEdit(CommonCons.yes);
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson add(InterPayDto entity) throws Exception {
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

		List<InterPayDto> list = new ArrayList<>();
		list.add(entity);
		mapper.batchInsert(list);
		return ResultJson.buildSuccess("新增成功!");
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
	public void checkCaseHaving(InterPayDto entity) {
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

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson split(SplitVO vo) throws Exception {
		List<SplitDetailVO> items = vo.getItems();
		// 参数校验
		if (StringUtils.isEmpty(vo.getParentId()) || CollectionUtils.isEmpty(items)) {
			return ResultJson.buildError("参数异常");
		}
		// 根据父级Id查询父级数据
		InterPayDto dto = mapper.selectByPrimaryKey(vo.getParentId());
		if (dto == null) {
			return ResultJson.buildError("数据源不存在，无法进行数据拆分！");
		}

		BigDecimal spDebitPrice = BigDecimal.ZERO;
		BigDecimal spCreditPrice = BigDecimal.ZERO;

		// 数据库搜索个案是否存在
		Set<String> cnSet = new HashSet<>();
		for (SplitDetailVO item : items) {
			if (StringUtils.isEmpty(item.getCaseId())) {
				cnSet.add(item.getCaseNumber());
			}
			spDebitPrice = spDebitPrice.add(item.getDebitPrice());
			spCreditPrice = spCreditPrice.add(item.getCreditPrice());
		}
		// 校验金额（借方发生额和贷方发生额）
		BigDecimal debitPrice = dto.getDebitPrice();
		BigDecimal creditPrice = dto.getCreditPrice();
		if (debitPrice.compareTo(spDebitPrice) != 0 || creditPrice.compareTo(spCreditPrice) != 0) {
			return ResultJson.buildError("拆分后的借、贷方金额之和与源数据借、贷方金额之和不一致！");
		}

		List<CaseInfo> dbCaseList = null;
		if (!CollectionUtils.isEmpty(cnSet)) {
			dbCaseList = caseMapper.getCaseInfoByCaseNumbers(new ArrayList<>(cnSet));
		}

		List<CaseInfo> thisCaseList = new ArrayList<>(items.size());

		if (CollectionUtils.isEmpty(dbCaseList)) {
			// 无匹配的情况
			for (SplitDetailVO item : items) {
				if (StringUtils.isEmpty(item.getCaseId())) {

					// 案号Id为空时，自动根据案号生成案号信息,添加到案号表
					CaseInfo caseInfo = new CaseInfo();
					caseInfo.setId(IdGenerator.getUUID());
					caseInfo.setCaseNumber(item.getCaseNumber());
					caseInfo.setMoneyNatureId(dto.getSubjectId());
					caseInfo.setMoneyNatureName(dto.getSubjectName());
					caseInfo.setReceivableAmount(BigDecimal.ZERO);
					caseInfo.setProcessStatus(CaseInfoStatusEnum.ProcessStatus_Receivables.getValue());
					caseInfo.setCreateChannel(CaseInfoCons.create_for_pos);
					thisCaseList.add(caseInfo);

					// 添加案号到 网银数据中
					item.setCaseId(caseInfo.getId());
				}
			}
		} else {

			// 有匹配的情况
			for (SplitDetailVO item : items) {
				if (StringUtils.isEmpty(item.getCaseId())) {
					// 申明是否已经匹配变量
					boolean isBo = false;
					for (CaseInfo ci : dbCaseList) {
						if (ci.getCaseNumber().equals(item.getCaseNumber())) {
							item.setCaseId(ci.getId());
							isBo = true;
							break;
						}
					}
					// 判断是否已经匹配
					if (!isBo) {
						// 案号Id为空时，自动根据案号生成案号信息,添加到案号表
						CaseInfo caseInfo = new CaseInfo();
						caseInfo.setId(IdGenerator.getUUID());
						caseInfo.setCaseNumber(item.getCaseNumber());
						caseInfo.setMoneyNatureId(dto.getSubjectId());
						caseInfo.setMoneyNatureName(dto.getSubjectName());
						caseInfo.setReceivableAmount(BigDecimal.ZERO);
						caseInfo.setProcessStatus(CaseInfoStatusEnum.ProcessStatus_Receivables.getValue());
						caseInfo.setCreateChannel(CaseInfoCons.create_for_pos);
						thisCaseList.add(caseInfo);
					}
				}
			}
		}

		if (!CollectionUtils.isEmpty(thisCaseList)) {
			// 生成最新的个案信息
			caseMapper.batchInsert(thisCaseList);
		}

		List<InterPayDto> list = new ArrayList<>(items.size());
		// 插入拆分后的结果集
		for (SplitDetailVO item : items) {
			InterPayDto tempDto = dto.clone();
			tempDto.setId(IdGenerator.getUUID());
			tempDto.setCaseId(item.getCaseId());
			tempDto.setCaseNumber(item.getCaseNumber());
			tempDto.setDebitPrice(item.getDebitPrice());
			tempDto.setCreditPrice(item.getCreditPrice());
			tempDto.setIsSplit(CommonCons.no);
			tempDto.setParentId(dto.getId());
			list.add(tempDto);
		}
		// 执行批量更新拆分后的数据
		mapper.batchInsert(list);
		// 修改原数据的是否拆分标识
		mapper.changeIsSplitById(dto.getId());

		return ResultJson.buildSuccess("拆分成功");
	}

}
