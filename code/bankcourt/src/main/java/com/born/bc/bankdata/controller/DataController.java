package com.born.bc.bankdata.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.born.bc.bankdata.service.api.DataServiceApi;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;

/**
 * 导入导出打印Controller
 * 
 * @author wangjian
 */
@Controller
@RequestMapping(value = "/data")
public class DataController {

	@Autowired
	private DataServiceApi api;

	/**
	 * 跳转至jsp
	 * 
	 * @return
	 */
	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public String goImportJsp() {
		return "upload";
	}

	/**
	 * 导入文件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	@ResponseBody
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
	 * 导出文件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/export")
	public ResultJson exportExcel() {
		return null;
	}

	/**
	 * 打印
	 * 
	 * @return
	 */
	@RequestMapping(value = "/print")
	public ResultJson print() {
		return null;
	}

}
