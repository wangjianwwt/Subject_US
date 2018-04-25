package com.born.bc.pzcx.entity;

import com.born.bc.basicdata.entity.InterPay;


/**
 * 财务台账ParamDto
 * @author wenxuan.yang
 *
 */
public class PzcxParamDto extends InterPay {
	
	
	private String subjectCode;
	private String srSum;
	private String zcSum;
	private String parentId;
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getSrSum() {
		return srSum;
	}
	public void setSrSum(String srSum) {
		this.srSum = srSum;
	}
	public String getZcSum() {
		return zcSum;
	}
	public void setZcSum(String zcSum) {
		this.zcSum = zcSum;
	}
	
	
	
	
}
