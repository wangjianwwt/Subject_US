package com.born.bc.basicdata.service.api;

import com.born.bc.basicdata.entity.PosPayDto;
import com.born.bc.basicdata.entity.PosPayParamDto;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;

public interface PosPayServiceApi {
	
	/**
	 * 列表
	 * @param entity
	 * @return
	 */
	public ResultJson list(PosPayParamDto dto, PageParamVO page);
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public ResultJson update(PosPayDto entity) throws Exception;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public ResultJson add(PosPayDto entity) throws Exception;
	
	/**
	 * 获取详情
	 * @param id
	 * @return
	 */
	public ResultJson getById(String id);

	/**
	 * 查询统计
	 * @param dto
	 * @return
	 */
	public ResultJson getSumDataByListParam(PosPayParamDto dto);
	
}
