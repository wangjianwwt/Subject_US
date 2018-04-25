package com.born.bc.basicdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.born.bc.basicdata.service.api.AreaCodeServiceApi;
import com.born.bc.commons.utils.ResultJson;

/**
 * 地区码Controller
 * @author wangjian
 *
 */
@RestController 
@RequestMapping("/areaCode")
public class AreaCodeController {

	@Autowired
	private AreaCodeServiceApi api;
	
	/**
	 * 地区码列表
	 * @param province
	 * @param searchName
	 * @return
	 */
	@RequestMapping("/list")
	public ResultJson list(@RequestParam("province") String province){
		return api.searchByProvince(province);
	}
	
}
