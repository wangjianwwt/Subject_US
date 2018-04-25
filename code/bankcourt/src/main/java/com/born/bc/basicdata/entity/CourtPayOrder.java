package com.born.bc.basicdata.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.born.bc.basicdata.entity.cons.CourtPayOrderStatusEnum;
import com.born.bc.commons.entity.BaseEntity;
import com.born.bc.commons.utils.JsonDateDeserialize;
import com.born.bc.commons.utils.JsonDateSerialize;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 法院支付单
 * @author wangjian
 *
 */
public class CourtPayOrder extends BaseEntity{
	/** 案号ID */
    private String caseId;
    /** 案号 */
    private String caseNumber;
    /** 币种 */
    private String currency;
    /** 日期 */
    @JsonDeserialize(using = JsonDateDeserialize.class)
    @JsonSerialize(using = JsonDateSerialize.class)
    private Date date;
    /** 明细标志 */
    private String detailTitle;
    /** 付款账号开户行 */
    private String paymentDepositBank;
    /**付款账号/卡号*/
    private String paymentNumber;
    /**付款账号名称/卡名称*/
    private String paymentName;
    /**收款账号开户行*/
    private String receivablesDepositBank;
    /**收款账号省份*/
    private String receivablesProvince;
    /**收款账号地市*/
    private String receivablesCity;
    /**收款账号地区码*/
    private String receivablesAreaCode;
    /**收款账号*/
    private String receivablesNumber;
    /**收款账号名称*/
    private String receivablesAccountName;
    /**金额*/
    private BigDecimal amount;
    /**网银统计金额*/
    private BigDecimal interAmount;
    /**汇款用途*/
    private String remittancePurpose;
    /**备注信息*/
    private String remarks;
    /**汇款方式*/
    private String remittanceMode;
    /**收款账户短信通知手机号码*/
    private String receivablesPhone;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 状态中文值
     */
    private String statusStr;

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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetailTitle() {
		return detailTitle;
	}

	public void setDetailTitle(String detailTitle) {
		this.detailTitle = detailTitle;
	}

	public String getPaymentDepositBank() {
		return paymentDepositBank;
	}

	public void setPaymentDepositBank(String paymentDepositBank) {
		this.paymentDepositBank = paymentDepositBank;
	}

	public String getPaymentNumber() {
		return paymentNumber;
	}

	public void setPaymentNumber(String paymentNumber) {
		this.paymentNumber = paymentNumber;
	}

	public String getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

	public String getReceivablesDepositBank() {
		return receivablesDepositBank;
	}

	public void setReceivablesDepositBank(String receivablesDepositBank) {
		this.receivablesDepositBank = receivablesDepositBank;
	}

	public String getReceivablesProvince() {
		return receivablesProvince;
	}

	public void setReceivablesProvince(String receivablesProvince) {
		this.receivablesProvince = receivablesProvince;
	}

	public String getReceivablesCity() {
		return receivablesCity;
	}

	public void setReceivablesCity(String receivablesCity) {
		this.receivablesCity = receivablesCity;
	}

	public String getReceivablesAreaCode() {
		return receivablesAreaCode;
	}

	public void setReceivablesAreaCode(String receivablesAreaCode) {
		this.receivablesAreaCode = receivablesAreaCode;
	}

	public String getReceivablesNumber() {
		return receivablesNumber;
	}

	public void setReceivablesNumber(String receivablesNumber) {
		this.receivablesNumber = receivablesNumber;
	}

	public String getReceivablesAccountName() {
		return receivablesAccountName;
	}

	public void setReceivablesAccountName(String receivablesAccountName) {
		this.receivablesAccountName = receivablesAccountName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRemittancePurpose() {
		return remittancePurpose;
	}

	public void setRemittancePurpose(String remittancePurpose) {
		this.remittancePurpose = remittancePurpose;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemittanceMode() {
		return remittanceMode;
	}

	public void setRemittanceMode(String remittanceMode) {
		this.remittanceMode = remittanceMode;
	}

	public String getReceivablesPhone() {
		return receivablesPhone;
	}

	public void setReceivablesPhone(String receivablesPhone) {
		this.receivablesPhone = receivablesPhone;
	}

	public BigDecimal getInterAmount() {
		return interAmount;
	}

	public void setInterAmount(BigDecimal interAmount) {
		this.interAmount = interAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusStr() {
		if(status != null){
			return CourtPayOrderStatusEnum.getLabel(status);
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	@Override
	public String toString() {
		return "{\"caseId\":\"" + caseId + "\", \"caseNumber\":\"" + caseNumber + "\", \"currency\":\"" + currency
				+ "\", \"date\":\"" + date + "\", \"detailTitle\":\"" + detailTitle + "\", \"paymentDepositBank\":\""
				+ paymentDepositBank + "\", \"paymentNumber\":\"" + paymentNumber + "\", \"paymentName\":\""
				+ paymentName + "\", \"receivablesDepositBank\":\"" + receivablesDepositBank
				+ "\", \"receivablesProvince\":\"" + receivablesProvince + "\", \"receivablesCity\":\""
				+ receivablesCity + "\", \"receivablesAreaCode\":\"" + receivablesAreaCode
				+ "\", \"receivablesNumber\":\"" + receivablesNumber + "\", \"receivablesAccountName\":\""
				+ receivablesAccountName + "\", \"amount\":\"" + amount + "\", \"interAmount\":\"" + interAmount
				+ "\", \"remittancePurpose\":\"" + remittancePurpose + "\", \"remarks\":\"" + remarks
				+ "\", \"remittanceMode\":\"" + remittanceMode + "\", \"receivablesPhone\":\"" + receivablesPhone
				+ "\", \"status\":\"" + status + "\", \"statusStr\":\"" + statusStr + "\"}";
	}

   
}