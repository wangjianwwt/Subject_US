package com.born.bc.pzcx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.born.bc.commons.utils.ResultJson;
import com.born.bc.pzcx.entity.PzcxParamDto;
import com.born.bc.pzcx.service.PzcxService;

/**
 * 凭证查询controller
 * @author wenxuan.yang
 *
 */
@RequestMapping(value="/pzcx")
@RestController
public class PzcxController {

	@Autowired
	private PzcxService pzcxService;


	/**
	 * 凭证查询
	 * @param cwtzParamDto
	 * @return
	 */
	@RequestMapping(value="/getPzcxList")
	@ResponseBody
	public ResultJson getPzcxList(PzcxParamDto pzcxParamDto){

		return pzcxService.getPzcxList(pzcxParamDto);
	}
	

}
