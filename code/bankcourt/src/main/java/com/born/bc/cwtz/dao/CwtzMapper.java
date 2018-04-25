package com.born.bc.cwtz.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.born.bc.cwtz.entity.CwtzParamDto;
/**
 * 财务台账mapper
 * @author wenxuan.yang
 *
 */
@Repository
public interface CwtzMapper {

	/**
	 * 科目明细统计
	 * @param paramDto
	 * @return
	 */
	public List<CwtzParamDto> getSubjectCount(CwtzParamDto cwtzParamDto);
	
	/**
	 * 科目月收入统计
	 * @param paramDto
	 * @return
	 */
	public List<CwtzParamDto> getsrList(CwtzParamDto cwtzParamDto);
	
	
	/**
	 * 资产负债情况
	 * @param paramDto
	 * @return
	 */
	public List<CwtzParamDto> getzcfzList(CwtzParamDto cwtzParamDto);
	
	/**
	 * 数据结算
	 * @param transactionTime
	 */
	public void updateSfjsI(String transactionTime);
	
	public void updateSfjsP(String transactionTime);
	
	
}
