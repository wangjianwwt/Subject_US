package com.born.bc.bankdata.service.api;

import com.born.bc.bankdata.entity.InterPay;
import com.born.bc.bankdata.entity.InterPayParamDto;
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
	public ResultJson update(InterPay entity) throws Exception;
	
	/**
	 * 添加网银数据
	 * @param entity
	 * @return
	 */
	public ResultJson add(InterPay entity) throws Exception;
	
}
