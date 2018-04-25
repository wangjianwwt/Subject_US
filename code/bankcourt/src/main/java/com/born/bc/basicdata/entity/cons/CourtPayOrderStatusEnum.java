package com.born.bc.basicdata.entity.cons;

public enum CourtPayOrderStatusEnum {
	/**
	 * 正在付款
	 */
	Status_Paiing(1,"正在付款"),
	/**
	 * 支付完成
	 */
	Status_Finish(2,"支付完成"),
	/**
	 * 退款
	 */
	Status_Return(3,"退款");

	private Integer value;
	private String label;
	
	private CourtPayOrderStatusEnum(Integer value, String label){
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
		for (CourtPayOrderStatusEnum s : CourtPayOrderStatusEnum.values()) {
			if (s.getValue().equals(value)) {
				return s.getLabel();
			}
		}
		return "";
	}
	
}
