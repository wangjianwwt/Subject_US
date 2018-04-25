package com.born.bc.cwtz.service;

import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;
import com.born.bc.cwtz.entity.CwtzParamDto;

/**
 * 财务台账统计接口
 * @author wenxuan.yang
 *
 */
public interface CwtzService {

	/**
	 * 科目明细统计
	 * @param paramDto
	 * @return
	 */
	public ResultJson getSubjectCount(CwtzParamDto cwtzParamDto, PageParamVO page);
	
	/**
	 * 科目月收入统计
	 * @param paramDto
	 * @return
	 */
	public ResultJson getsrList(CwtzParamDto cwtzParamDto, PageParamVO page);
	
	/**
	 * 资产负债情况
	 * @param paramDto
	 * @return
	 */
	public ResultJson getzcfzList(CwtzParamDto cwtzParamDto, PageParamVO page);
	
	
	public ResultJson updateSfjs(String transactionTime);

}
