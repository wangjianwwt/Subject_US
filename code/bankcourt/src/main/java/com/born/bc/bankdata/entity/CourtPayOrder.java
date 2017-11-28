package com.born.bc.bankdata.entity;

import com.born.bc.commons.entity.BaseEntity;

/**
 * 法院支付单
 * @author wangjian
 *
 */
public class CourtPayOrder extends BaseEntity{

    private String caseId;

    private String caseNumber;

    private String currency;

    private String date;

    private String detailTitle;

    private String paymentDepositBank;

    private String paymentNumber;

    private String paymentName;

    private String receivablesDepositBank;

    private String receivablesProvince;

    private String receivablesCity;

    private String receivablesAreaCode;

    private String receivablesNumber;

    private String receivablesAccountName;

    private String amount;

    private String remittancePurpose;

    private String remarks;

    private String remittanceMode;

    private String receivablesPhone;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
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

   
}