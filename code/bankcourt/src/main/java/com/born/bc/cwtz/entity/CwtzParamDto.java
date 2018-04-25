package com.born.bc.cwtz.entity;

import java.math.BigDecimal;

import com.born.bc.basicdata.entity.InterPay;


/**
 * 财务台账ParamDto
 * @author wenxuan.yang
 *
 */
public class CwtzParamDto extends InterPay {
	
	private String subjectCode;
	private String parentId;
	private BigDecimal ncs;
	private BigDecimal dysr;
	private BigDecimal dyzc;
	private BigDecimal dnsr;
	private BigDecimal dnzc;
	private BigDecimal qms;
	
	public String getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public BigDecimal getNcs() {
		return ncs == null ? BigDecimal.ZERO : this.ncs;
	}
	public void setNcs(BigDecimal ncs) {
		this.ncs = ncs;
	}
	public BigDecimal getDysr() {
		return dysr == null ? BigDecimal.ZERO : this.dysr;
	}
	public void setDysr(BigDecimal dysr) {
		this.dysr = dysr;
	}
	public BigDecimal getDyzc() {
		return dyzc == null ? BigDecimal.ZERO : this.dyzc;
	}
	public void setDyzc(BigDecimal dyzc) {
		this.dyzc = dyzc;
	}
	public BigDecimal getDnsr() {
		return dnsr == null ? BigDecimal.ZERO : this.dnsr;
	}
	public void setDnsr(BigDecimal dnsr) {
		this.dnsr = dnsr;
	}
	public BigDecimal getDnzc() {
		return dnzc == null ? BigDecimal.ZERO : this.dnzc;
	}
	public void setDnzc(BigDecimal dnzc) {
		this.dnzc = dnzc;
	}
	public BigDecimal getQms() {
		return qms == null ? BigDecimal.ZERO : this.qms;
	}
	public void setQms(BigDecimal qms) {
		this.qms = qms;
	}
	
}
