package com.born.bc.bankdata.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.born.bc.bankdata.dao.PosPayMapper;
import com.born.bc.bankdata.entity.PosPay;
import com.born.bc.bankdata.entity.PosPayParamDto;
import com.born.bc.bankdata.service.api.PosPayServiceApi;
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

	@Autowired
	private PosPayMapper mapper;

	@Override
	public ResultJson list(PosPayParamDto dto, PageParamVO page) {
		try {
			dto.setEndDate(DateUtils.tomorrowStartTime(dto.getEndDate()));
			dto.setStartDate(DateUtils.todayStartTime(dto.getStartDate()));
			PageHelper.startPage(page.getPageIndex(), page.getPageSize());
			List<PosPay> list = mapper.selectByList(dto);
			PageInfo<PosPay> pageInfo = new PageInfo<PosPay>(list);
			return ResultJson.buildSuccess("查询成功！", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误！");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson update(PosPay entity) throws Exception {
		mapper.updateByPrimaryKeySelective(entity);
		return ResultJson.buildSuccess("修改成功!");
	}

	@Override
	public ResultJson add(PosPay entity) throws Exception {

		// 设置登录用户
		CurrentLoginUser loginUser = LoginUserInfoUtil.getCurrentLoginUser(entity.getUserToken());
		entity.setFormmakerId(loginUser.getUserId());
		entity.setFormmakerName(loginUser.getLoginName());
		entity.setId(IdGenerator.getUUID());

		List<PosPay> list = new ArrayList<PosPay>();
		list.add(entity);
		mapper.batchInsertSelective(list);
		return ResultJson.buildSuccess("新增成功!");
	}

	@Override
	public ResultJson getById(String id) {
		try {
			PosPay entity = mapper.selectByPrimaryKey(id);
			return ResultJson.buildSuccess("entity", entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败！");
		}
	}

}
