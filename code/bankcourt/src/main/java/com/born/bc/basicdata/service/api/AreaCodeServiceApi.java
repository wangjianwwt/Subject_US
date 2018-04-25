package com.born.bc.basicdata.service.api;

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
	public ResultJson searchByProvince(String province);
	
}
