package com.born.bc.bankdata.entity;

import com.born.bc.commons.entity.BaseEntity;

/**
 * 网银导入数据
 */
public class InterPay extends BaseEntity{

	private String voucherNo;

    private String localNumber;

    private String partyNumber;

    private String transactionTime;

    private String borrowOrLend;

    private String debitPrice;

    private String creditPrice;

    private String partyLineNumber;

    private String abstractInfo;

    private String purpose;

    private String corporateName;

    private String balance;

    private String individuation;

    private String caseId;

    private String caseNumber;

    private String subjectId;

    private String subjectName;

    private String departmentName;

    private String undertaker;

    private Integer isEdit;

    private String batchNumber;

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getLocalNumber() {
		return localNumber;
	}

	public void setLocalNumber(String localNumber) {
		this.localNumber = localNumber;
	}

	public String getPartyNumber() {
		return partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getBorrowOrLend() {
		return borrowOrLend;
	}

	public void setBorrowOrLend(String borrowOrLend) {
		this.borrowOrLend = borrowOrLend;
	}

	public String getDebitPrice() {
		return debitPrice;
	}

	public void setDebitPrice(String debitPrice) {
		this.debitPrice = debitPrice;
	}

	public String getCreditPrice() {
		return creditPrice;
	}

	public void setCreditPrice(String creditPrice) {
		this.creditPrice = creditPrice;
	}

	public String getPartyLineNumber() {
		return partyLineNumber;
	}

	public void setPartyLineNumber(String partyLineNumber) {
		this.partyLineNumber = partyLineNumber;
	}

	public String getAbstractInfo() {
		return abstractInfo;
	}

	public void setAbstractInfo(String abstractInfo) {
		this.abstractInfo = abstractInfo;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getIndividuation() {
		return individuation;
	}

	public void setIndividuation(String individuation) {
		this.individuation = individuation;
	}

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

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getUndertaker() {
		return undertaker;
	}

	public void setUndertaker(String undertaker) {
		this.undertaker = undertaker;
	}

	public Integer getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(Integer isEdit) {
		this.isEdit = isEdit;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

  
}