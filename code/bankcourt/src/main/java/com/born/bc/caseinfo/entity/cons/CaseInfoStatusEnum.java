package com.born.bc.caseinfo.entity.cons;

/**
 * 个案枚举
 * @author wangjian
 *
 */
public enum CaseInfoStatusEnum {
	/**
	 * 催收
	 */
	ProcessStatus_Urge(1,"催收"),
	/**
	 * 正在收款
	 */
	ProcessStatus_Receivables(2,"正在收款"),
	/**
	 * 移送执行
	 */
	ProcessStatus_TransferExcute(3,"移送执行"),
	/**
	 * 坏账处理
	 */
	ProcessStatus_BadDebt(4,"坏账处理"),
	/**
	 * 正常结算
	 */
	ProcessStatu_Normal(5,"正常结算");
	
	private Integer value;
	private String label;
	
	private CaseInfoStatusEnum(Integer value, String label){
		this.label = label;
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	/**
	 * 根据value得到对应的中文字符串
	 * @param value
	 * @return
	 */
	public static String getLabel(Integer value) {
		for (CaseInfoStatusEnum s : CaseInfoStatusEnum.values()) {
			if (s.getValue().equals(value)) {
				return s.getLabel();
			}
		}
		return "";
	}
	
}
