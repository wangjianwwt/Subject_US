package com.born.bc.pzcx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.born.bc.pzcx.entity.PzcxParamDto;
/**
 * 凭证查询mapper
 * @author wenxuan.yang
 *
 */
@Repository
public interface PzcxMapper {

	/**
	 * 凭证查询
	 * @param paramDto
	 * @return
	 */
	public List<PzcxParamDto> getPzcxList(PzcxParamDto pzcxParamDto);
	
	/**
	 * 凭证金额累计
	 * @param paramDto
	 * @return
	 */
	public List<PzcxParamDto> getSumList(PzcxParamDto pzcxParamDto);
	
	
}
