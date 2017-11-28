package com.born.bc.bankdata.service.api;

import com.born.bc.commons.utils.ResultJson;

/**
 * 查询地区码API
 * @author wangjian
 *
 */
public interface AreaCodeServiceApi {
	/**
	 * 查询地区码
	 * @param province 省
	 * @param searchName 地区码、地区
	 * @return
	 */
	public ResultJson searchByParam(String searchName);
	
	/**
	 * 查询省
	 * @param province
	 * @return
	 */
	public ResultJson searchProvinceByParam(String province);
}
