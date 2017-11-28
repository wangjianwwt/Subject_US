package com.born.bc.bankdata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.born.bc.bankdata.dao.InterPayMapper;
import com.born.bc.bankdata.entity.InterPay;
import com.born.bc.bankdata.entity.InterPayParamDto;
import com.born.bc.bankdata.service.api.InterPayServiceApi;
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
 * @author wangjian
 *
 */
@Service
public class InterPayServiceImpl implements InterPayServiceApi{
	@Autowired
	private InterPayMapper mapper;

	@Override
	public ResultJson list(InterPayParamDto dto, PageParamVO page) {
		try {
			dto.setEndDate(DateUtils.tomorrowStartTime(dto.getEndDate()));
			dto.setStartDate(DateUtils.todayStartTime(dto.getStartDate()));
			PageHelper.startPage(page.getPageIndex(), page.getPageSize());
			List<InterPay> list = mapper.selectByPage(dto);
			PageInfo<InterPay> pageInfo = new PageInfo<InterPay>(list);
			return ResultJson.buildSuccess("查询成功！", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误！");
		}
	}

	@Override
	public ResultJson getById(String id) {
		try {
			InterPay entity = mapper.selectByPrimaryKey(id);
			return ResultJson.buildSuccess("查询成功！", entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败！");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson update(InterPay entity) throws Exception{
		mapper.updateByPrimaryKeySelective(entity);
		return ResultJson.buildSuccess("修改成功!");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson add(InterPay entity) throws Exception{
		// 设置登录用户
		CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(entity.getUserToken());
		entity.setFormmakerId(loginUser.getUserId());
		entity.setFormmakerName(loginUser.getLoginName());
		entity.setId(IdGenerator.getUUID());
		
		List<InterPay> list = new ArrayList<>();
		list.add(entity);
		mapper.batchInsertSelective(list);
		return ResultJson.buildSuccess("新增成功!");
	}

}
