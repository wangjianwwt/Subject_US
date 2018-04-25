package com.born.bc.basicdata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.born.bc.basicdata.service.api.DataServiceApi;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;

/**
 * 导入导出打印Controller
 * 
 * @author wangjian
 */
@RestController
@RequestMapping(value = "/data")
public class DataController {

	@Autowired
	private DataServiceApi api;

	/**
	 * 导入文件
	 * 
	 * @return
	 */
	@PostMapping(value = "/import")
	public ResultJson importExcel(HttpServletRequest request) {
		try {
			return api.importExcel(request);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("导入错误，请联系开发人员进行系统维护!");
		}
	}
	
	/**
	 * 获取导入向导
	 * @return
	 */
	@GetMapping(value="/guideResp")
	public ResultJson guideResp(@RequestParam("dateStr") String dateStr, @RequestParam("sys_click") String sysClick){
		return api.guideResp(dateStr, sysClick);
	}
	
	/**
	 * 上锁数据（已结算数据）
	 * @param dateStr
	 * @return
	 */
	@GetMapping(value="/lockData")
	public ResultJson lockData(@RequestParam("dateStr") String dateStr){
		return api.lockData(dateStr);
	}
	
	/**
	 * 补录数据
	 * @param dateStr
	 * @param sysClick
	 * @return
	 */
	@GetMapping(value="/isEdit")
	public ResultJson isEdit(@RequestParam("dateStr") String dateStr, @RequestParam("sys_click") String sysClick){
		return api.isEdit(dateStr, sysClick);
	}

	/**
	 * 主页
	 * @param dateStr
	 * @return
	 */
	@GetMapping(value="/homeData")
	public ResultJson homeData(@RequestParam("dateStr") String dateStr){
		try {
			return api.homeData(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败");
		}
	}
	
	
}
