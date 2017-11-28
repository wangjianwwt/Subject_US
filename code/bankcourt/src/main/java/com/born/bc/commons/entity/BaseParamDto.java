package com.born.bc.commons.entity;

import java.util.Date;

/**
 * 通用参数ParamDto
 * @author wangjian
 *
 */
public class BaseParamDto {
	private Date startDate;
	private Date endDate;
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
	
}
