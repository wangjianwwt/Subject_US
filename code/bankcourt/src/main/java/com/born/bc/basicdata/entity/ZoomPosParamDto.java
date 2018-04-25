package com.born.bc.basicdata.entity;

import java.math.BigDecimal;
/**
 * POS查询统计
 * @author wangjian
 *
 */
public class ZoomPosParamDto {

	/**交易金额*/
	private BigDecimal transationPriceSum;
	/**红利收入*/
	private BigDecimal dividendIncomeSum;
	/**红利支出*/
    private BigDecimal dividendExpenditureSum;
	 /**小费*/
    private BigDecimal dumboSum;
    /**手续费*/
    private BigDecimal serviceChargeSum;
    /**分期付款手续费*/
    private BigDecimal installmentFeeSum;
    /**净收金额*/
    private BigDecimal netProceedsSum;
	public BigDecimal getTransationPriceSum() {
		return transationPriceSum;
	}
	public void setTransationPriceSum(BigDecimal transationPriceSum) {
		this.transationPriceSum = transationPriceSum;
	}
	public BigDecimal getDividendIncomeSum() {
		return dividendIncomeSum;
	}
	public void setDividendIncomeSum(BigDecimal dividendIncomeSum) {
		this.dividendIncomeSum = dividendIncomeSum;
	}
	public BigDecimal getDividendExpenditureSum() {
		return dividendExpenditureSum;
	}
	public void setDividendExpenditureSum(BigDecimal dividendExpenditureSum) {
		this.dividendExpenditureSum = dividendExpenditureSum;
	}
	public BigDecimal getDumboSum() {
		return dumboSum;
	}
	public void setDumboSum(BigDecimal dumboSum) {
		this.dumboSum = dumboSum;
	}
	public BigDecimal getServiceChargeSum() {
		return serviceChargeSum;
	}
	public void setServiceChargeSum(BigDecimal serviceChargeSum) {
		this.serviceChargeSum = serviceChargeSum;
	}
	public BigDecimal getInstallmentFeeSum() {
		return installmentFeeSum;
	}
	public void setInstallmentFeeSum(BigDecimal installmentFeeSum) {
		this.installmentFeeSum = installmentFeeSum;
	}
	public BigDecimal getNetProceedsSum() {
		return netProceedsSum;
	}
	public void setNetProceedsSum(BigDecimal netProceedsSum) {
		this.netProceedsSum = netProceedsSum;
	}
	@Override
	public String toString() {
		return "{\"transationPriceSum\":\"" + transationPriceSum + "\", \"dividendIncomeSum\":\"" + dividendIncomeSum
				+ "\", \"dividendExpenditureSum\":\"" + dividendExpenditureSum + "\", \"dumboSum\":\"" + dumboSum
				+ "\", \"serviceChargeSum\":\"" + serviceChargeSum + "\", \"installmentFeeSum\":\"" + installmentFeeSum
				+ "\", \"netProceedsSum\":\"" + netProceedsSum + "\"}";
	}
    
    
}
