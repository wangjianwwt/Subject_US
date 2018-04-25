package com.born.bc.basicdata.service.api;

import com.born.bc.basicdata.entity.InterPayDto;
import com.born.bc.basicdata.entity.InterPayParamDto;
import com.born.bc.basicdata.entity.SplitVO;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;

/**
  * 网银展示API
  * @author wangjian
  *
  */
public interface InterPayServiceApi {

	/**
	 * 网银列表
	 * @param entity
	 * @param page
	 * @return
	 */
	public ResultJson list(InterPayParamDto dto, PageParamVO page);
	
	/**
	 * 获取详情
	 * @param id
	 * @return
	 */
	public ResultJson getById(String id);
	
	/**
	 * 修改网银数据
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public ResultJson update(InterPayDto entity) throws Exception;
	
	/**
	 * 添加网银数据
	 * @param entity
	 * @return
	 */
	public ResultJson add(InterPayDto entity) throws Exception;

	/**
	 * 根据列表查询条件获取查询统计数据
	 * @param dto
	 * @return
	 */
	public ResultJson getSumDataByListParam(InterPayParamDto dto);

	/**
	 * 数据拆分
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	public ResultJson split(SplitVO vo) throws Exception;
	
}
