package com.born.bc.caseinfo.entity.cons;

public class CaseInfoCons {

	private CaseInfoCons(){}
	
	/**
	 * 创建途径：用户创建
	 */
	public static final Integer create_for_user = 0;
	
	/**
	 * 创建途径：POS生成
	 */
	public static final Integer create_for_pos = 1;
	
	/**
	 * 创建途径：网银生成
	 */
	public static final Integer create_for_inter = 2;
	
	/**
	 * 创建途径：支付录入
	 */
	public static final Integer create_for_pay = 3;
	
}
