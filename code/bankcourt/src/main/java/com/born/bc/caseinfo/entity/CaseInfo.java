package com.born.bc.caseinfo.entity;

import java.math.BigDecimal;

import com.born.bc.caseinfo.entity.cons.CaseInfoStatusEnum;
import com.born.bc.commons.entity.BaseEntity;
/**
 * 案件实体
 * @author wenxuan.yang
 *
 */
public class CaseInfo extends BaseEntity{
	/** 案号 */
    private String caseNumber;
    /**款项性质Id*/
    private String moneyNatureId;
    /**款项性质编码*/
    private String moneyNatureCode;
	/**款项性质Name*/
    private String moneyNatureName;
    /**应收金额*/
    private BigDecimal receivableAmount;
    /**实收金额*/
    private BigDecimal actualAmount;
    /**差额*/
    private BigDecimal diffAmount;
    /**处理状态*/
    private Integer processStatus;
    /**处理状态中文字符*/
    private String processStatusStr;
    /**创建途径：0手工创建，1POS创建，2网银创建*/
    private Integer createChannel;

	public String getCaseNumber() {
		return caseNumber;
	}

	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}

	public String getMoneyNatureId() {
		return moneyNatureId;
	}

	public void setMoneyNatureId(String moneyNatureId) {
		this.moneyNatureId = moneyNatureId;
	}

	public String getMoneyNatureCode() {
		return moneyNatureCode;
	}

	public void setMoneyNatureCode(String moneyNatureCode) {
		this.moneyNatureCode = moneyNatureCode;
	}

	public String getMoneyNatureName() {
		return moneyNatureName;
	}

	public void setMoneyNatureName(String moneyNatureName) {
		this.moneyNatureName = moneyNatureName;
	}

	public BigDecimal getReceivableAmount() {
		return receivableAmount;
	}

	public void setReceivableAmount(BigDecimal receivableAmount) {
		this.receivableAmount = receivableAmount;
	}

	public BigDecimal getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}

	public BigDecimal getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(BigDecimal diffAmount) {
		this.diffAmount = diffAmount;
	}
    public Integer getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Integer processStatus) {
		this.processStatus = processStatus;
		if(processStatus != null){
			setProcessStatusStr(CaseInfoStatusEnum.getLabel(processStatus));
		}
	}

	public String getProcessStatusStr() {
		return processStatusStr;
	}

	public void setProcessStatusStr(String processStatusStr) {
		this.processStatusStr = processStatusStr;
	}

	public Integer getCreateChannel() {
		return createChannel;
	}

	public void setCreateChannel(Integer createChannel) {
		this.createChannel = createChannel;
	}

}