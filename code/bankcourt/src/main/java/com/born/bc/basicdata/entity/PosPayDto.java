package com.born.bc.basicdata.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.born.bc.commons.entity.BaseEntity;
import com.born.bc.commons.utils.JsonDateDeserializeEx;
import com.born.bc.commons.utils.JsonDateSerializeEx;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * POS数据
 * @author wangjian
 *
 */
public class PosPayDto extends BaseEntity{

	/**终端编号*/
    private String terminalNumber;
    /**交易时间*/
    @JsonDeserialize(using = JsonDateDeserializeEx.class)
    @JsonSerialize(using = JsonDateSerializeEx.class)
    private Date transactionTime;
    /**银行卡号*/
    private String bankNumber;
    /**交易金额*/
    private BigDecimal transationPrice;
    /**红利收入*/
    private BigDecimal dividendIncome;
    /**红利支出*/
    private BigDecimal dividendExpenditure;
    /**小费*/
    private BigDecimal dumbo;
    /**手续费*/
    private BigDecimal serviceCharge;
    /**分期付款手续费*/
    private BigDecimal installmentFee;
    /**净收金额*/
    private BigDecimal netProceeds;
    /**交易检索号*/
    private String transactionSearcheNumber;
    /**交易类型*/
    private String transactionType;
    /**款项性质*/
    private String moneyNature;
    /**收款账号*/
    private String receivablesNumber;
    /**案号ID*/
    private String caseId;
    /**案号*/
    private String caseNumber;
    /**是否被编辑*/
    private Integer isEdit;
    /**批次号*/
    private String batchNumber;
    /**持卡人*/
    private String chikar;
    /**科目ID*/
    private String subjectId;
    /**科目名称*/
    private String subjectName;
    /**部门名称*/
    private String departmentName;
    /**承办人名*/
    private String undertaker;
    /**摘要*/
    private String abstractInfo;
    /**凭证号*/
    private String voucherNo;
    /**是否加锁*/
    private Integer isLock;

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber == null ? null : terminalNumber.trim();
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber == null ? null : bankNumber.trim();
    }

    public BigDecimal getTransationPrice() {
        return transationPrice;
    }

    public void setTransationPrice(BigDecimal transationPrice) {
        this.transationPrice = transationPrice;
    }

    public BigDecimal getDividendIncome() {
        return dividendIncome;
    }

    public void setDividendIncome(BigDecimal dividendIncome) {
        this.dividendIncome = dividendIncome;
    }

    public BigDecimal getDividendExpenditure() {
        return dividendExpenditure;
    }

    public void setDividendExpenditure(BigDecimal dividendExpenditure) {
        this.dividendExpenditure = dividendExpenditure;
    }

    public BigDecimal getDumbo() {
        return dumbo;
    }

    public void setDumbo(BigDecimal dumbo) {
        this.dumbo = dumbo;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public BigDecimal getInstallmentFee() {
        return installmentFee;
    }

    public void setInstallmentFee(BigDecimal installmentFee) {
        this.installmentFee = installmentFee;
    }

    public BigDecimal getNetProceeds() {
        return netProceeds;
    }

    public void setNetProceeds(BigDecimal netProceeds) {
        this.netProceeds = netProceeds;
    }
    
    public String getTransactionSearcheNumber() {
        return transactionSearcheNumber;
    }

    public void setTransactionSearcheNumber(String transactionSearcheNumber) {
        this.transactionSearcheNumber = transactionSearcheNumber == null ? null : transactionSearcheNumber.trim();
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType == null ? null : transactionType.trim();
    }

    public String getMoneyNature() {
        return moneyNature;
    }

    public void setMoneyNature(String moneyNature) {
        this.moneyNature = moneyNature == null ? null : moneyNature.trim();
    }

    public String getReceivablesNumber() {
        return receivablesNumber;
    }

    public void setReceivablesNumber(String receivablesNumber) {
        this.receivablesNumber = receivablesNumber == null ? null : receivablesNumber.trim();
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

    public String getChikar() {
        return chikar;
    }

    public void setChikar(String chikar) {
        this.chikar = chikar == null ? null : chikar.trim();
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

    public String getAbstractInfo() {
        return abstractInfo;
    }

    public void setAbstractInfo(String abstractInfo) {
        this.abstractInfo = abstractInfo == null ? null : abstractInfo.trim();
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo == null ? null : voucherNo.trim();
    }

	public Integer getIsLock() {
		return isLock;
	}

	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}

	@Override
	public String toString() {
		return "{\"terminalNumber\":\"" + terminalNumber + "\", \"transactionTime\":\"" + transactionTime
				+ "\", \"bankNumber\":\"" + bankNumber + "\", \"transationPrice\":\"" + transationPrice
				+ "\", \"dividendIncome\":\"" + dividendIncome + "\", \"dividendExpenditure\":\"" + dividendExpenditure
				+ "\", \"dumbo\":\"" + dumbo + "\", \"serviceCharge\":\"" + serviceCharge + "\", \"installmentFee\":\""
				+ installmentFee + "\", \"netProceeds\":\"" + netProceeds + "\", \"transactionSearcheNumber\":\""
				+ transactionSearcheNumber + "\", \"transactionType\":\"" + transactionType + "\", \"moneyNature\":\""
				+ moneyNature + "\", \"receivablesNumber\":\"" + receivablesNumber + "\", \"caseId\":\"" + caseId
				+ "\", \"caseNumber\":\"" + caseNumber + "\", \"isEdit\":\"" + isEdit + "\", \"batchNumber\":\""
				+ batchNumber + "\", \"chikar\":\"" + chikar + "\", \"subjectId\":\"" + subjectId
				+ "\", \"subjectName\":\"" + subjectName + "\", \"departmentName\":\"" + departmentName
				+ "\", \"undertaker\":\"" + undertaker + "\", \"abstractInfo\":\"" + abstractInfo
				+ "\", \"voucherNo\":\"" + voucherNo + "\", \"isLock\":\"" + isLock + "\"}";
	}

}