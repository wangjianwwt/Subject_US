package com.born.bc.caseinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.born.bc.caseinfo.entity.CaseInfo;
import com.born.bc.caseinfo.entity.CaseInfoParamDto;
import com.born.bc.caseinfo.service.api.CaseServiceApi;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;

/**
 * 个案档案Controller
 * 
 * @author wenxuan.yang
 *
 */
@RequestMapping(value = "/case")
@RestController
public class CaseController {

	@Autowired
	private CaseServiceApi api;

	/**
	 * 列表
	 * 
	 * @param dto
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ResultJson list(CaseInfoParamDto dto, PageParamVO page) {
		return api.list(dto, page);
	}

	/**
	 * 新增
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultJson add(@RequestBody CaseInfo entity) {
		try {
			return api.add(entity);
		} catch (BussinessException e) {
			e.printStackTrace();
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			return ResultJson.buildError("新增失败！");
		}
	}

	/**
	 * 修改
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResultJson edit(@RequestBody CaseInfo entity) {
		try {
			return api.update(entity);
		} catch (BussinessException e) {
			e.printStackTrace();
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			return ResultJson.buildError("编辑失败！");
		}
	}

	/**
	 * 删除
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public ResultJson del(@RequestBody CaseInfo entity) {
		try {
			return api.del(entity.getId());
		} catch (BussinessException e) {
			e.printStackTrace();
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			return ResultJson.buildError("删除失败！");
		}
	}

	/**
	 * 个案参照
	 * 
	 * @param
	 * @return
	 */
	@GetMapping(value = "/api")
	public ResultJson api() {
		return api.getCaseLoser();
	}
	
	
	/**
	 * 个案参照（包含余额）
	 * 
	 * @param
	 * @return
	 */
	@GetMapping(value = "/havingBalance")
	public ResultJson havingBalance() {
		return api.havingBalance();
	}
	
	/**
	 * 个案明细
	 * @param id
	 * @return
	 */
	@GetMapping(value= "/caseDetail")
	public ResultJson caseDetail(@RequestParam("id") String id){
		return api.caseDetail(id);
	}
	

}
