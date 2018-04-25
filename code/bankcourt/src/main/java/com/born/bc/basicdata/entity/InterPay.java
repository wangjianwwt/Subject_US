package com.born.bc.basicdata.entity;

import com.born.bc.commons.entity.BaseEntity;

/**
 * 网银导入数据
 */
public class InterPay extends BaseEntity{
	/** 凭证号 */
	private String voucherNo;
	/** 本方账号 */
    private String localNumber;
    /** 对方账号 */
    private String partyNumber;
    /** 交易时间 */
    private String transactionTime;
    /** 借/贷 */
    private String borrowOrLend;
    /** 借方发生额 */
    private String debitPrice;
    /** 贷方发生额 */
    private String creditPrice;
    /** 对方行号 */
    private String partyLineNumber;
    /** 摘要 */
    private String abstractInfo;
    /** 用途  */
    private String purpose;
    /** 对方单位名称 */
    private String corporateName;
    /** 余额 */
    private String balance;
    /** 个性化信息 */
    private String individuation;
    /** 个案ID */
    private String caseId;
    /** 个案号 */
    private String caseNumber;
    /** 科目ID */
    private String subjectId;
    /** 科目名称 */
    private String subjectName;
    /** 部门名称 */
    private String departmentName;
    /** 承办人名 */
    private String undertaker;
    /** 是否被编辑,1是0否 */
    private Integer isEdit;
    /** 批次号 */
    private String batchNumber;
    /**是否加锁*/
    private Integer isLock;

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo == null ? null : voucherNo.trim();
	}

	public String getLocalNumber() {
		return localNumber;
	}

	public void setLocalNumber(String localNumber) {
		this.localNumber = localNumber == null ? null : localNumber.trim();
	}

	public String getPartyNumber() {
		return partyNumber;
	}

	public void setPartyNumber(String partyNumber) {
		this.partyNumber = partyNumber == null ? null : partyNumber.trim();
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime == null ? null : transactionTime.trim();
	}

	public String getBorrowOrLend() {
		return borrowOrLend;
	}

	public void setBorrowOrLend(String borrowOrLend) {
		this.borrowOrLend = borrowOrLend == null ? null : borrowOrLend.trim();
	}

	public String getDebitPrice() {
		return debitPrice;
	}

	public void setDebitPrice(String debitPrice) {
		this.debitPrice = debitPrice == null ? null : debitPrice.trim();
	}

	public String getCreditPrice() {
		return creditPrice;
	}

	public void setCreditPrice(String creditPrice) {
		this.creditPrice = creditPrice == null ? null : creditPrice.trim();
	}

	public String getPartyLineNumber() {
		return partyLineNumber;
	}

	public void setPartyLineNumber(String partyLineNumber) {
		this.partyLineNumber = partyLineNumber ==null ? null : partyLineNumber.trim();
	}

	public String getAbstractInfo() {
		return abstractInfo;
	}

	public void setAbstractInfo(String abstractInfo) {
		this.abstractInfo = abstractInfo == null ? null : abstractInfo.trim();
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose == null ? null : purpose.trim();
	}

	public String getCorporateName() {
		return corporateName;
	}

	public void setCorporateName(String corporateName) {
		this.corporateName = corporateName == null ? null : corporateName.trim();
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance == null ? null : balance.trim();
	}

	public String getIndividuation() {
		return individuation;
	}

	public void setIndividuation(String individuation) {
		this.individuation = individuation == null ? null : individuation.trim();
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId == null ? null : caseId.trim();
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber == null ? null : caseNumber.trim();
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId == null ? null : subjectId.trim();
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName == null ? null : subjectName.trim();
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName == null ? null : departmentName.trim();
	}

	public String getUndertaker() {
		return undertaker;
	}

	public void setUndertaker(String undertaker) {
		this.undertaker = undertaker == null ? null : undertaker.trim();
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
		this.batchNumber = batchNumber == null ? null : batchNumber.trim();
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	@Override
	public String toString() {
		return "{\"voucherNo\":\"" + voucherNo + "\", \"localNumber\":\"" + localNumber + "\", \"partyNumber\":\""
				+ partyNumber + "\", \"transactionTime\":\"" + transactionTime + "\", \"borrowOrLend\":\""
				+ borrowOrLend + "\", \"debitPrice\":\"" + debitPrice + "\", \"creditPrice\":\"" + creditPrice
				+ "\", \"partyLineNumber\":\"" + partyLineNumber + "\", \"abstractInfo\":\"" + abstractInfo
				+ "\", \"purpose\":\"" + purpose + "\", \"corporateName\":\"" + corporateName + "\", \"balance\":\""
				+ balance + "\", \"individuation\":\"" + individuation + "\", \"caseId\":\"" + caseId
				+ "\", \"caseNumber\":\"" + caseNumber + "\", \"subjectId\":\"" + subjectId + "\", \"subjectName\":\""
				+ subjectName + "\", \"departmentName\":\"" + departmentName + "\", \"undertaker\":\"" + undertaker
				+ "\", \"isEdit\":\"" + isEdit + "\", \"batchNumber\":\"" + batchNumber + "\", \"isLock\":\"" + isLock
				+ "\"}";
	}



  
}