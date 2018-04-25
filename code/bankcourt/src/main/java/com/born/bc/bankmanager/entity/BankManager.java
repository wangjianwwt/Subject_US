package com.born.bc.bankmanager.entity;

import com.born.bc.commons.entity.BaseEntity;

/**
 * 银行管理
 * @author wangjian
 *
 */
public class BankManager extends BaseEntity{

	/**
	 * 银行名称
	 */
    private String bankName;
    /**
     * 银行账号
     */
    private String bankAccount;
    /**
     * 备注信息
     */
    private String comments;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "{\"bankName\":\"" + bankName + "\", \"bankAccount\":\"" + bankAccount + "\", \"comments\":\"" + comments
				+ "\"}";
	}



}