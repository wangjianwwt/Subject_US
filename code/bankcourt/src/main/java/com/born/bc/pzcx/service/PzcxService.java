package com.born.bc.pzcx.service;

import com.born.bc.commons.utils.ResultJson;
import com.born.bc.pzcx.entity.PzcxParamDto;

/**
 * 财务台账统计接口
 * @author wenxuan.yang
 *
 */
public interface PzcxService {

	/**
	 * 凭证查询
	 * @param paramDto
	 * @return
	 */
	public ResultJson getPzcxList(PzcxParamDto pzcxParamDto);
	
}
