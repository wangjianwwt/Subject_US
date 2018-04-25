package com.born.bc.basicdata.service.api;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.born.bc.basicdata.entity.CourtPayOrder;
import com.born.bc.basicdata.entity.CourtPayOrderParamDto;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;
/**
 * 法院支付单业务API
 * @author wangjian
 *
 */
public interface CourtPayOrderServiceApi {

	/**
	 * 列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public ResultJson list(CourtPayOrderParamDto dto, PageParamVO page);
	
	/**
	 * 编辑
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public ResultJson edit(CourtPayOrder entity) throws BussinessException;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	public ResultJson add(CourtPayOrder entity) throws BussinessException;
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public ResultJson delete(List<String> ids) throws BussinessException;
	
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	public ResultJson getById(String id);
	
	/**
	 * 导出
	 * @return 
	 * @throws Exception 
	 */
	public ResultJson export(CourtPayOrderParamDto dto, HttpServletResponse response);
	
}
