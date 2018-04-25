package com.born.bc.bankmanager.service;

import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.born.bc.bankmanager.dao.BankManagerMapper;
import com.born.bc.bankmanager.entity.BankManager;
import com.born.bc.bankmanager.service.api.BankManagerServiceApi;
import com.born.bc.commons.entity.CurrentLoginUser;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.IdGenerator;
import com.born.bc.commons.utils.LoginUserInfoUtil;
import com.born.bc.commons.utils.ResultJson;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 银行维护业务实现
 * 
 * @author wangjian
 *
 */
@Service
public class BankManagerServiceImpl implements BankManagerServiceApi {

	@Autowired
	private BankManagerMapper mapper;

	@Override
	public ResultJson list(BankManager entity, PageParamVO page) {

		try {
			PageHelper.startPage(page.getPage(), page.getLimit());
			List<BankManager> list = mapper.selectByList(entity);
			PageInfo<BankManager> pageInfo = new PageInfo<BankManager>(list);
			return ResultJson.buildSuccess("查询成功！", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("系统错误");
		}

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson add(BankManager entity) {
		ResultJson result = validateParam(entity);
		if (result != null) {
			return result;
		}

		// 检查银行账号是否存在，银行账号唯一
		List<BankManager> bankList = mapper.selectByBanckAccount(entity);
		if (!CollectionUtils.isEmpty(bankList)) {
			return ResultJson.buildError("银行账号已经存在");
		}

		// 设置登录用户
		CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(entity.getUserToken());
		entity.setFormmakerId(loginUser.getUserId());
		entity.setFormmakerName(loginUser.getLoginName());
		entity.setId(IdGenerator.getUUID());
		mapper.insertSelective(entity);
		return ResultJson.buildSuccess("新增成功");
	}

	/**
	 * 校验参数
	 * 
	 * @param entity
	 * @return
	 */
	private ResultJson validateParam(BankManager entity) {
		// 校验参数
		if (StringUtils.isEmpty(entity.getBankAccount()) || StringUtils.isEmpty(entity.getBankName())) {
			return ResultJson.buildParamError("参数异常");
		}
		return null;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson edit(BankManager entity) {
		// 校验参数
		if (StringUtils.isEmpty(entity.getId())) {
			return ResultJson.buildError("缺乏主键");
		}
		ResultJson resultJson = validateParam(entity);
		if (resultJson != null) {
			return resultJson;
		}

		// 检查银行账号是否存在，银行账号唯一
		List<BankManager> bankList = mapper.selectByBanckAccount(entity);
		if (!CollectionUtils.isEmpty(bankList)) {
			return ResultJson.buildError("银行账号已经存在");
		}
		
		mapper.updateByPrimaryKeySelective(entity);
		return ResultJson.buildSuccess("修改成功");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson delete(List<String> ids) {

		if (CollectionUtils.isEmpty(ids)) {
			return ResultJson.buildError("参数异常");
		}

		mapper.updateDelStatus(ids);
		return ResultJson.buildSuccess("删除成功");
	}

}
