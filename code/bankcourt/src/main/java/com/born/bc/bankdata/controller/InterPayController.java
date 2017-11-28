package com.born.bc.bankdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.born.bc.bankdata.entity.InterPay;
import com.born.bc.bankdata.entity.InterPayParamDto;
import com.born.bc.bankdata.service.api.InterPayServiceApi;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;

/**
 * 网上银行数据导入
 */
@RequestMapping(value="/interPay")
@Controller
public class InterPayController {
	
	@Autowired
	private InterPayServiceApi service;

    /**
     * 查询列表
     * @param entity
     * @param page
     * @return
     */
    @RequestMapping(value="/list")
    @ResponseBody
    public ResultJson list(InterPayParamDto dto, PageParamVO page){
        return service.list(dto, page);
    }


    /**
     * 编辑
     * @param entity
     * @return
     */
    @RequestMapping(value="/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson edit(@RequestBody InterPay entity){
        try {
			return service.update(entity);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("编辑网银数据错误!");
		}
    }

    /**
     * 新增
     * @param entity
     * @return
     */
    @RequestMapping(value="/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultJson add(@RequestBody InterPay entity){
        try {
			return service.update(entity);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("编辑网银数据错误!");
		}
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @RequestMapping(value="/getById")
    @ResponseBody
    public ResultJson getById(@RequestParam("id") String id){
        return service.getById(id);
    }


}
