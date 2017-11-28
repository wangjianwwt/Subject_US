package com.born.bc.commons.utils;

/**
 * rest 返回参数枚举
 * @author wangjian
 */
public enum RequestStatusEnum {
	/**
	 * 1.成功
	 * 0.失败，系统错误
	 * 2.失败，参数异常
	 */
	SUCCESS(1),
	FAIL_FIELD(0),
	FAIL_PARAM(2);
	

	private Integer status;

	private RequestStatusEnum(int status) {
		this.status=status;
	}

	public Integer getStatus() {
		return status;
	}
	
	
}
