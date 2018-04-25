package com.born.bc.basicdata.entity;

import java.math.BigDecimal;

public class SplitDetailVO {

	/** 案号Id */
	private String caseId;
	/** 案号 */
	private String caseNumber;
	/** 借方发生额 */
	private BigDecimal debitPrice;
	/** 贷方发生额 */
	private BigDecimal creditPrice;

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public BigDecimal getDebitPrice() {
		return debitPrice;
	}

	public void setDebitPrice(BigDecimal debitPrice) {
		this.debitPrice = debitPrice;
	}

	public BigDecimal getCreditPrice() {
		return creditPrice;
	}

	public void setCreditPrice(BigDecimal creditPrice) {
		this.creditPrice = creditPrice;
	}

}
