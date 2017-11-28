package com.born.bc.bankdata.service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.born.bc.bankdata.dao.AreaCodeMapper;
import com.born.bc.bankdata.service.api.AreaCodeServiceApi;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;


/**
 * 地区码API实现
 * @author wangjian
 *
 */
@Service
public class AreaCodeServiceImpl implements AreaCodeServiceApi {

	@Autowired
	private AreaCodeMapper mapper;
	
	@Override
	public ResultJson searchByParam(String searchName) {
		try {
			List<Map<String, String>> list = mapper.searchByParam(searchName);
			return ResultJson.buildSuccess("查询成功！", list);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误！");
		}
	}

	@Override
	public ResultJson searchProvinceByParam(String province) {
		try {
			HashSet<String> data = mapper.searchProvinceByParam(province);
			return ResultJson.buildSuccess("查询成功！", data);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误！");
		}
	}

}
