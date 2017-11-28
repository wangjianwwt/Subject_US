package com.born.bc.bankdata.service.api;

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


}
