package com.born.bc.basicdata.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.born.bc.commons.entity.BaseEntity;
import com.born.bc.commons.utils.JsonDateDeserializeEx;
import com.born.bc.commons.utils.JsonDateSerializeEx;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 网银DTO
 * @author wangjian
 *
 */
public class InterPayDto extends BaseEntity implements Cloneable{
	/**凭证号*/
    private String voucherNo;
    /**本方账号*/
    private String localNumber;
    /**对方账号*/
    private String partyNumber;
    /**交易时间*/
    @JsonDeserialize(using = JsonDateDeserializeEx.class)
    @JsonSerialize(using = JsonDateSerializeEx.class)
    private Date transactionTime;
    /**借/贷*/
    private String borrowOrLend;
    /**借方发生额*/
    private BigDecimal debitPrice;
    /**贷方发生额*/
    private BigDecimal creditPrice;
    /**对方行号*/
    private String partyLineNumber;
    /**摘要*/
    private String abstractInfo;
    /**用途*/
    private String purpose;
    /**对方单位名称*/
    private String corporateName;
    /**余额*/
    private BigDecimal balance;
    /**个性化信息*/
    private String individuation;
    /**个案ID*/
    private String caseId;
    /**个案号*/
    private String caseNumber;
    /**科目ID*/
    private String subjectId;
    /**科目名称*/
    private String subjectName;
    /**部门名称*/
    private String departmentName;
    /**承办人名*/
    private String undertaker;
    /**是否被编辑：1是，0否*/
    private Integer isEdit;
    /**批次号*/
    private String batchNumber;
    /**是否加锁*/
    private Integer isLock;
    /**是否拆分*/
    private Integer isSplit;
    /**父级Id*/
    private String parentId;


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

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getBorrowOrLend() {
        return borrowOrLend;
    }

    public void setBorrowOrLend(String borrowOrLend) {
        this.borrowOrLend = borrowOrLend == null ? null : borrowOrLend.trim();
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

    public String getPartyLineNumber() {
        return partyLineNumber;
    }

    public void setPartyLineNumber(String partyLineNumber) {
        this.partyLineNumber = partyLineNumber == null ? null : partyLineNumber.trim();
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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
	
    public Integer getIsSplit() {
		return isSplit;
	}

	public void setIsSplit(Integer isSplit) {
		this.isSplit = isSplit;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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
				+ "\", \"isSplit\":\"" + isSplit + "\", \"parentId\":\"" + parentId + "\"}";
	}

	@Override
	public InterPayDto clone() throws CloneNotSupportedException {
		return (InterPayDto)super.clone();
	}

}