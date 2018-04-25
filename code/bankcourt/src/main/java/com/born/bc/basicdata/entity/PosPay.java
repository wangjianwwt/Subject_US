package com.born.bc.basicdata.entity;

import com.born.bc.commons.entity.BaseEntity;

/**
 * POS数据实体
 * @author wangjian
 *
 */
public class PosPay extends BaseEntity{
	/** 序号，打酱油字段*/
	private String no;
	/** 终端编号 */
	private String terminalNumber;
	/** 交易时间  */
    private String transactionTime;
    /** 银行卡号  */
    private String bankNumber;
    /** 交易金额  */
    private String transationPrice;
    /** 红利收入  */
    private String dividendIncome;
    /** 红利支出  */
    private String dividendExpenditure;
    /** 小费  */
    private String dumbo;
    /** 手续费  */
    private String serviceCharge;
    /** 分期付款手续费  */
    private String installmentFee;
    /** 净收金额  */
    private String netProceeds;
    /** 交易检索号  */
    private String transactionSearcheNumber;
    /** 交易类型  */
    private String transactionType;
    /** 交易日期  */
    private String transactionDate;
    /** 款项性质  */
    private String moneyNature;
    /** 收款账号  */
    private String receivablesNumber;
    /** 案号ID  */
    private String caseId;
    /** 案号  */
    private String caseNumber;
    /** 是否被编辑,1是0否  */
    private Integer isEdit;
    /** 批次号  */
    private String batchNumber;
    /** 持卡人  */
    private String chikar;
    /** 科目ID  */
    private String subjectId;
    /** 科目名称  */
    private String subjectName;
    /** 部门名称  */
    private String departmentName;
    /** 承办人名  */
    private String undertaker;
    /** 摘要 */
    private String abstractInfo;
    /**是否加锁*/
    private Integer isLock;
    
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTerminalNumber() {
		return terminalNumber;
	}

	public void setTerminalNumber(String terminalNumber) {
		this.terminalNumber = terminalNumber;
	}

	public String getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getTransationPrice() {
		return transationPrice;
	}

	public void setTransationPrice(String transationPrice) {
		this.transationPrice = transationPrice;
	}

	public String getDividendIncome() {
		return dividendIncome;
	}

	public void setDividendIncome(String dividendIncome) {
		this.dividendIncome = dividendIncome;
	}

	public String getDividendExpenditure() {
		return dividendExpenditure;
	}

	public void setDividendExpenditure(String dividendExpenditure) {
		this.dividendExpenditure = dividendExpenditure;
	}

	public String getDumbo() {
		return dumbo;
	}

	public void setDumbo(String dumbo) {
		this.dumbo = dumbo;
	}

	public String getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getInstallmentFee() {
		return installmentFee;
	}

	public void setInstallmentFee(String installmentFee) {
		this.installmentFee = installmentFee;
	}

	public String getNetProceeds() {
		return netProceeds;
	}

	public void setNetProceeds(String netProceeds) {
		this.netProceeds = netProceeds;
	}

	public String getTransactionSearcheNumber() {
		return transactionSearcheNumber;
	}

	public void setTransactionSearcheNumber(String transactionSearcheNumber) {
		this.transactionSearcheNumber = transactionSearcheNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getMoneyNature() {
		return moneyNature;
	}

	public void setMoneyNature(String moneyNature) {
		this.moneyNature = moneyNature;
	}

	public String getReceivablesNumber() {
		return receivablesNumber;
	}

	public void setReceivablesNumber(String receivablesNumber) {
		this.receivablesNumber = receivablesNumber;
	}

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
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

	public String getChikar() {
		return chikar;
	}

	public void setChikar(String chikar) {
		this.chikar = chikar;
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

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getAbstractInfo() {
		return abstractInfo;
	}

	public void setAbstractInfo(String abstractInfo) {
		this.abstractInfo = abstractInfo;
	}

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	@Override
	public String toString() {
		return "{\"no\":\"" + no + "\", \"terminalNumber\":\"" + terminalNumber + "\", \"transactionTime\":\""
				+ transactionTime + "\", \"bankNumber\":\"" + bankNumber + "\", \"transationPrice\":\""
				+ transationPrice + "\", \"dividendIncome\":\"" + dividendIncome + "\", \"dividendExpenditure\":\""
				+ dividendExpenditure + "\", \"dumbo\":\"" + dumbo + "\", \"serviceCharge\":\"" + serviceCharge
				+ "\", \"installmentFee\":\"" + installmentFee + "\", \"netProceeds\":\"" + netProceeds
				+ "\", \"transactionSearcheNumber\":\"" + transactionSearcheNumber + "\", \"transactionType\":\""
				+ transactionType + "\", \"transactionDate\":\"" + transactionDate + "\", \"moneyNature\":\""
				+ moneyNature + "\", \"receivablesNumber\":\"" + receivablesNumber + "\", \"caseId\":\"" + caseId
				+ "\", \"caseNumber\":\"" + caseNumber + "\", \"isEdit\":\"" + isEdit + "\", \"batchNumber\":\""
				+ batchNumber + "\", \"chikar\":\"" + chikar + "\", \"subjectId\":\"" + subjectId
				+ "\", \"subjectName\":\"" + subjectName + "\", \"departmentName\":\"" + departmentName
				+ "\", \"undertaker\":\"" + undertaker + "\", \"abstractInfo\":\"" + abstractInfo + "\", \"isLock\":\""
				+ isLock + "\"}";
	}
    
	
	
}