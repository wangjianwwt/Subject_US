package com.born.bc.commons.entity;

import java.util.Date;

import com.born.bc.commons.utils.JsonDateDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 通用参数ParamDto
 * @author wangjian
 *
 */
public class BaseParamDto {
	/**
	 * 开始时间
	 */
	@JsonDeserialize(using = JsonDateDeserialize.class)
	private Date startDate;
	/**
	 * 结束时间
	 */
	@JsonDeserialize(using = JsonDateDeserialize.class)
	private Date endDate;
	/**
	 * 查询参数
	 */
	private String searchParam;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getSearchParam() {
		return searchParam;
	}
	public void setSearchParam(String searchParam) {
		this.searchParam = searchParam;
	}
	
}
