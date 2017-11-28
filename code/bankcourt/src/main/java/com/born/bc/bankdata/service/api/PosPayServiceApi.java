package com.born.bc.bankdata.service.api;

import com.born.bc.bankdata.entity.PosPay;
import com.born.bc.bankdata.entity.PosPayParamDto;
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
	public ResultJson update(PosPay entity) throws Exception;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public ResultJson add(PosPay entity) throws Exception;
	
	/**
	 * 获取详情
	 * @param id
	 * @return
	 */
	public ResultJson getById(String id);
	
}
