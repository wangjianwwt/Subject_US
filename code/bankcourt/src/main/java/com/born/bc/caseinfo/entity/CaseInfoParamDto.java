package com.born.bc.caseinfo.entity;

import com.born.bc.commons.entity.BaseParamDto;

/**
 * 个案请求参数Dto
 * @author wenxuan.yang
 *
 */
public class CaseInfoParamDto extends BaseParamDto{
	// 案号
	private String caseNumber;
	// 处理方式
    private Integer processStatus;

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public Integer getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
	}


}
