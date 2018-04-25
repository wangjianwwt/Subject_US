package com.born.bc.basicdata.entity;

import java.math.BigDecimal;

/**
 * 小计数据
 * @author wangjian
 *
 */
public class ZoomInterResultDto {
	
	/**借方发生额*/
	private BigDecimal debitPriceSum;
	
	/**贷方发生额*/
	private BigDecimal creditPriceSum;
	/**余额*/
	private BigDecimal balanceSum;
	public BigDecimal getDebitPriceSum() {
		return debitPriceSum;
	}
	public void setDebitPriceSum(BigDecimal debitPriceSum) {
		this.debitPriceSum = debitPriceSum;
	}
	public BigDecimal getCreditPriceSum() {
		return creditPriceSum;
	}
	public void setCreditPriceSum(BigDecimal creditPriceSum) {
		this.creditPriceSum = creditPriceSum;
	}
	public BigDecimal getBalanceSum() {
		return balanceSum;
	}
	public void setBalanceSum(BigDecimal balanceSum) {
		this.balanceSum = balanceSum;
	}
	@Override
	public String toString() {
		return "{\"debitPriceSum\":\"" + debitPriceSum + "\", \"creditPriceSum\":\"" + creditPriceSum
				+ "\", \"balanceSum\":\"" + balanceSum + "\"}";
	}
	
}
