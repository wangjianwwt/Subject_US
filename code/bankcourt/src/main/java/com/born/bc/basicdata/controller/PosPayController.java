package com.born.bc.basicdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.born.bc.basicdata.entity.PosPayDto;
import com.born.bc.basicdata.entity.PosPayParamDto;
import com.born.bc.basicdata.service.api.PosPayServiceApi;
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
	 * 查询统计
	 * @param dto
	 * @return
	 */
    @GetMapping(value="/getSumDataByListParam")
    @ResponseBody
    public ResultJson getSumDataByListParam(PosPayParamDto dto){
    	return api.getSumDataByListParam(dto);
    }
	
	/**
	 * 编辑
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	@ResponseBody
	public ResultJson edit(@RequestBody PosPayDto entity){
		try {
			return api.update(entity);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("编辑数据错误！");
		}
	}
	
	/**
	 * 添加
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	@ResponseBody
	public ResultJson add(@RequestBody PosPayDto entity){
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
