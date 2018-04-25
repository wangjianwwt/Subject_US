package com.born.bc.basicdata.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.born.bc.basicdata.entity.CourtPayOrder;
import com.born.bc.basicdata.entity.CourtPayOrderParamDto;
import com.born.bc.basicdata.service.api.CourtPayOrderServiceApi;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;

/**
 * 法院支付单Controller
 * 
 * @author wangjian
 *
 */
@Controller
@RequestMapping(value = "/courtPayOrder")
public class CourtPayOrderController {

	@Autowired
	private CourtPayOrderServiceApi api;
	
	/**
	 * 列表
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public ResultJson list(CourtPayOrderParamDto dto, PageParamVO page) {
		return api.list(dto, page);
	}

	/**
	 * 编辑
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public ResultJson edit(@RequestBody CourtPayOrder entity) {
		try {
			return api.edit(entity);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("编辑数据错误！");
		}
	}

	/**
	 * 新增
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultJson add(@RequestBody CourtPayOrder entity) {
		try {
			return api.add(entity);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("新增数据错误！");
		}
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ResultJson delete(@RequestBody CourtPayOrder entity) {
		try {
			if(StringUtils.isEmpty(entity.getId())){
				return ResultJson.buildParamError("参数异常");
			}
			List<String> ids = new ArrayList<>(1);
			ids.add(entity.getId());
			return api.delete(ids);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("删除数据错误！");
		}
	}

	/**
	 * 详情
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResultJson getById(@PathVariable("id") String id) {
		return api.getById(id);
	}

	/**
	 * 导出
	 * 
	 * @param dto
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ResponseBody
	public ResultJson export(CourtPayOrderParamDto dto, HttpServletResponse response) {
		return api.export(dto, response);
	}

}
