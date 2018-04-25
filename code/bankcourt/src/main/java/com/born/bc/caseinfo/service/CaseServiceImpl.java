package com.born.bc.caseinfo.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.born.bc.basicdata.dao.CourtPayOrderMapper;
import com.born.bc.basicdata.dao.InterPayDtoMapper;
import com.born.bc.basicdata.dao.PosPayDtoMapper;
import com.born.bc.caseinfo.dao.CaseInfoMapper;
import com.born.bc.caseinfo.entity.CaseInfo;
import com.born.bc.caseinfo.entity.CaseInfoParamDto;
import com.born.bc.caseinfo.entity.cons.CaseInfoCons;
import com.born.bc.caseinfo.entity.cons.CaseInfoStatusEnum;
import com.born.bc.caseinfo.service.api.CaseServiceApi;
import com.born.bc.commons.entity.CurrentLoginUser;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.DateUtils;
import com.born.bc.commons.utils.IdGenerator;
import com.born.bc.commons.utils.LoginUserInfoUtil;
import com.born.bc.commons.utils.ResultJson;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 个案业务实现
 * 
 * @author wenxuan.yang
 *
 */
@Service
public class CaseServiceImpl implements CaseServiceApi {
	@Autowired
	private CaseInfoMapper mapper;
	@Autowired
	private InterPayDtoMapper interPayDtoMapper;
	@Autowired
	private PosPayDtoMapper posPayDtoMapper;
	@Autowired
	private CourtPayOrderMapper courtPayOrderMapper;

	@Override
	public ResultJson list(CaseInfoParamDto dto, PageParamVO page) {
		try {
			dto.setEndDate(DateUtils.tomorrowStartTime(dto.getEndDate()));
			dto.setStartDate(DateUtils.todayStartTime(dto.getStartDate()));
			PageHelper.startPage(page.getPage(), page.getLimit());
			List<CaseInfo> list = mapper.selectByList(dto);
			PageInfo<CaseInfo> pageInfo = new PageInfo<CaseInfo>(list);
			return ResultJson.buildSuccess("查询成功！", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误！");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson add(CaseInfo entity) throws BussinessException {
		// 校验数据
		ResultJson resultJson = validateCaseNotNull(entity);
		if (resultJson != null) {
			return resultJson;
		}

		// 校验个案唯一
		Integer rows = mapper.checkCaseNumberOnly(entity.getCaseNumber());
		if (rows != null && rows > 0) {
			return ResultJson.buildError("案号已经存在!");
		}
		// 设置登录用户
		CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(entity.getUserToken());
		entity.setFormmakerId(loginUser.getUserId());
		entity.setFormmakerName(loginUser.getLoginName());
		entity.setId(IdGenerator.getUUID());
		// 设置创建途径
		entity.setCreateChannel(CaseInfoCons.create_for_user);
		// 新增个案默认状态为 “正在收款”
		entity.setProcessStatus(CaseInfoStatusEnum.ProcessStatus_Receivables.getValue());
		mapper.insertSelective(entity);
		return ResultJson.buildSuccess("新增成功!");
	}

	/**
	 * 校验个案必填数据
	 * 
	 * @param entity
	 * @return
	 */
	private ResultJson validateCaseNotNull(CaseInfo entity) {

		if (StringUtils.isEmpty(entity.getMoneyNatureName())) {
			return ResultJson.buildError("款项性质不能为空");
		}
//		if (entity.getProcessStatus() == null) {
//			return ResultJson.buildError("个案状态不能为空");
//		}
		if (entity.getReceivableAmount() == null || (entity.getReceivableAmount().compareTo(BigDecimal.ZERO) < 0)) {
			return ResultJson.buildError("应收金额不能为空且必须大于0");
		}
		if (StringUtils.isEmpty(entity.getCaseNumber())) {
			return ResultJson.buildError("案号不能为空");
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson update(CaseInfo entity) throws BussinessException {
		// 判断Id是否存在
		if (StringUtils.isEmpty(entity.getId())) {
			return ResultJson.buildError("缺乏主键");
		}

//		CaseInfo sourceCase = mapper.selectByPrimaryKey(entity.getId());
//		if (sourceCase.getCreateChannel().equals(CaseInfoCons.create_for_pos)
//				|| sourceCase.getCreateChannel().equals(CaseInfoCons.create_for_inter)) {
//			// 校验数据
//			if (entity.getProcessStatus() == null) {
//				return ResultJson.buildError("个案状态不能为空");
//			}
//			if (entity.getReceivableAmount() == null || (entity.getReceivableAmount().compareTo(BigDecimal.ZERO) < 0)) {
//				return ResultJson.buildError("应收金额不能为空且必须大于0");
//			}
//			entity.setCaseNumber(null);
//		} else {
//			// 校验数据
//			ResultJson resultJson = validateCaseNotNull(entity);
//			if (resultJson != null) {
//				return resultJson;
//			}
//		}
//
//		// 处理状态
//		Integer status = entity.getProcessStatus();
//		if (status != null) {
//			// 修改个案状态必须是：催收、移送执行、坏账处理 中的一个
//			if (!(status.equals(CaseInfoStatusEnum.ProcessStatus_BadDebt)
//					|| status.equals(CaseInfoStatusEnum.ProcessStatus_TransferExcute)
//					|| status.equals(CaseInfoStatusEnum.ProcessStatus_Urge))) {
//				// 如果不是催收、移送执行、坏账处理中的一个，就还原个案状态，已原状态为主,设置为null将不会被更新
//				entity.setProcessStatus(null);
//			}
//		}
//		
//		entity.setCreateChannel(null);
		
		// 修改简单处理方式
		ResultJson resultJson = validateCaseNotNull(entity);
		if (resultJson != null) {
			return resultJson;
		}
		if (entity.getReceivableAmount() == null || (entity.getReceivableAmount().compareTo(BigDecimal.ZERO) < 0)) {
			return ResultJson.buildError("应收金额不能为空且必须大于0");
		}
		
		mapper.updateByPrimaryKeySelective(entity);
		return ResultJson.buildSuccess("修改成功!");
	}

	// 设置个案状态
	public ResultJson setStatus(CaseInfo entity) {
		CaseInfo sourceCaseInfo = mapper.selectByPrimaryKey(entity.getId());
		if (sourceCaseInfo == null) {
			return ResultJson.buildError("无效的个案ID");
		}
		// 当前个案的状态
		Integer status = sourceCaseInfo.getProcessStatus();
		// 系统自动维护个案状态将除开（催收、移送执行、坏账处理）三个状态
		if (!(status.equals(CaseInfoStatusEnum.ProcessStatus_BadDebt)
				|| status.equals(CaseInfoStatusEnum.ProcessStatus_TransferExcute)
				|| status.equals(CaseInfoStatusEnum.ProcessStatus_Urge))) {
			// 得到应收金额和实收金额
			BigDecimal recAmount = entity.getReceivableAmount() == null ? BigDecimal.ZERO
					: entity.getReceivableAmount();
			BigDecimal actAmount = entity.getActualAmount() == null ? BigDecimal.ZERO : entity.getActualAmount();
			// 应收实收一致，状态正常结算,不一致就是正在结算
			if ((recAmount.subtract(actAmount)).equals(BigDecimal.ZERO)) {
				entity.setProcessStatus(CaseInfoStatusEnum.ProcessStatu_Normal.getValue());
			} else {
				entity.setProcessStatus(CaseInfoStatusEnum.ProcessStatus_Receivables.getValue());
			}
		} else {
			// 不修改个案状态
			entity.setProcessStatus(status);
		}

		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson del(String id) throws BussinessException {
		if (StringUtils.isEmpty(id)) {
			return ResultJson.buildParamError("参数异常");
		}

		// 查询案号是否被关联，如果被关联，将无法进行删除
		Integer num = mapper.selectHaveCaseInDB(id);

		if(num == null || num.intValue() == 0){
			// 不存在关联的情况可继续往下执行
		}else{
			return ResultJson.buildError("案号已经被关联，不能被删除，如果需要删除，请先释放关联后再进行删除");
		}

		//删除个案表中的信息
		mapper.updateDelStatusByPrimaryKey(id);

//		// 清除与该案号相关的信息
//		interPayDtoMapper.cleanCaseInfoByCaseId(id);
//		posPayDtoMapper.cleanCaseInfoByCaseId(id);
//		// 支付录入
//		courtPayOrderMapper.cleanCaseInfoByCaseId(id);
		
		return ResultJson.buildSuccess("删除成功");
	}

	@Override
	public List<CaseInfo> getCaseInfoByCaseNumbers(List<String> caseNumbers) {
		try {
			List<CaseInfo> list = mapper.getCaseInfoByCaseNumbers(caseNumbers);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultJson getCaseLoser() {
		try {
			List<Map<String, Object>> apiList = mapper.getCaseLoser();
			return ResultJson.buildSuccess("查询成功", apiList);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误！");
		}
	}

	@Override
	public Set<String> checkCaseHave(List<Map<String, String>> otherList) {
		Set<String> caseSet = new HashSet<>(otherList.size());
		for (Map<String, String> map : otherList) {
			caseSet.add(map.get("caseNumber"));
		}
		return mapper.checkCaseHave(caseSet);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void createMore(List<Map<String, String>> otherList) {
		if (CollectionUtils.isEmpty(otherList)) {
			return;
		}
		List<CaseInfo> list = new ArrayList<>(otherList.size());
		for (Map<String, String> map : otherList) {
			CaseInfo caseInfo = new CaseInfo();
			caseInfo.setId(IdGenerator.getUUID());
			caseInfo.setCaseNumber(map.get("caseNumber"));
			caseInfo.setMoneyNatureName(map.get("moneyNature"));
			caseInfo.setReceivableAmount(BigDecimal.ZERO);
			caseInfo.setProcessStatus(CaseInfoStatusEnum.ProcessStatus_Receivables.getValue());
			caseInfo.setCreateChannel(CaseInfoCons.create_for_pos);
			list.add(caseInfo);
		}
		// 批量新增案号
		mapper.batchInsert(list);
	}

	@Override
	public ResultJson havingBalance() {
		try {
			List<Map<String, Object>> data = mapper.havingBalance();
			return ResultJson.buildSuccess("查询成功", data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误");
		}
	}

	@Override
	public ResultJson caseDetail(String id) {
		try {
			
			if(StringUtils.isEmpty(id)){
				return ResultJson.buildError("案号Id为空");
			}
			
			List<Map<String, Object>> data = mapper.caseDetail(id);
			return ResultJson.buildSuccess("查询成功", data);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误");
		}
	}

}
