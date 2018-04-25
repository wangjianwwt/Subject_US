package com.born.bc.basicdata.entity;

import java.math.BigDecimal;

import com.born.bc.commons.entity.BaseParamDto;

/**
 * 查询法院支付单列表参数实体
 * 
 * @author wangjian
 *
 */
public class CourtPayOrderParamDto extends BaseParamDto {
	/**
	 * 案号
	 */
	private String caseNumber;

	/**
	 * 开始时间
	 */
	private String startDateStr;
	/**
	 * 结束时间
	 */
	private String endDateStr;
	/** 金额 */
	private BigDecimal amount;
	/** 收款账号名称 */
	private String receivablesAccountName;

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getReceivablesAccountName() {
		return receivablesAccountName;
	}

	public void setReceivablesAccountName(String receivablesAccountName) {
		this.receivablesAccountName = receivablesAccountName;
	}
	

}
