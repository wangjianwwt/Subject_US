package com.born.bc.bankdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.born.bc.bankdata.entity.PosPay;
import com.born.bc.bankdata.entity.PosPayParamDto;
import com.born.bc.bankdata.service.api.PosPayServiceApi;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;

/**
 * Pos数据Conotroller
 * @author wangjian
 *
 */
@Controller
@RequestMapping(value="/posPay")
public class PosPayController {
	
	@Autowired
	private PosPayServiceApi api;
	
	/**
	 * 查询列表
	 * @param dto
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public ResultJson list(PosPayParamDto dto, PageParamVO page){
		return api.list(dto, page);
	}
	
	/**
	 * 编辑
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	@ResponseBody
	public ResultJson edit(PosPay entity){
		try {
			return api.update(entity);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildSuccess("编辑数据错误！");
		}
	}
	
	/**
	 * 添加
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultJson add(PosPay entity){
		try {
			return api.add(entity);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildSuccess("新增数据错误！");
		}
	}
	
	/**
	 * 详情
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/getById/{id}")
	@ResponseBody
	public ResultJson getById(@PathVariable("id") String id){
		return api.getById(id);
	}
	
}
