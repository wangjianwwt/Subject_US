package com.born.bc.basicdata.entity;

import com.born.bc.commons.entity.BaseParamDto;

/**
 * 查询网银列表数据参数实体
 * @author wangjian
 *
 */
public class InterPayParamDto extends BaseParamDto{
	private String startDateStr;
	private String endDateStr;
	private String caseNumber;
	public String getStartDateStr() {
		return startDateStr;
	}
	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	@Override
	public String toString() {
		return "{\"startDateStr\":\"" + startDateStr + "\", \"endDateStr\":\"" + endDateStr + "\", \"caseNumber\":\""
				+ caseNumber + "\"}";
	}
	
}
