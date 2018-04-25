package com.born.bc.basicdata.service.api;

import com.born.bc.commons.utils.ResultJson;

import javax.servlet.http.HttpServletRequest;

/**
 * 数据源API
 * @author wangjian
 *
 */
public interface DataServiceApi {

    /**
     * 导入excel
     * @param request
     * @return
     * @throws Exception 
     */
    public ResultJson importExcel(HttpServletRequest request) throws Exception;

    /**
     * 获取导入向导
     * @param sysClick 
     * @return
     */
	public ResultJson guideResp(String dateStr, String sysClick);

	/**
	 * 上锁数据（已结算数据）
	 * @param dateStr
	 * @return
	 */
	public ResultJson lockData(String dateStr);

	/**
	 * 补录数据
	 * @param dateStr
	 * @param sysClick
	 * @return
	 */
	public ResultJson isEdit(String dateStr, String sysClick);

	/**
	 * 首页展示数据
	 * @return
	 * @throws Exception 
	 */
	public ResultJson homeData(String dateStr) throws Exception;
	

}
