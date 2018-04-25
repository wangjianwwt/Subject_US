package com.born.bc.basicdata.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.born.bc.commons.exception.BussinessException;

/**
 * 批次号生成工具
 * @author wangjian
 *
 */
public class BatchNumberUtil {
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static final SimpleDateFormat vSdf = new SimpleDateFormat("yyyyMMdd");
	
	private static final String interPay_fix = "INTER_";
	
	private static final String posPay_fix = "POS_";
	
	private BatchNumberUtil(){}
	
	/**
	 * 生成网银导入批次号（规则：固定首字母+时间戳）
	 * @return
	 */
	public static String createInterPayBatchNumber(){
		return interPay_fix + sdf.format(new Date());
	}
	
	/**
	 * 生成POS导入批次号（规则：固定首字母+时间戳）
	 * @return
	 */
	public static String createPosPayBatchNumber(){
		return posPay_fix + sdf.format(new Date());
	}
	
	/**
	 * 生成凭证号
	 * @Param transactionTime 交易时间
	 * @return
	 */
	public static String createVoucherNo(Date transactionTime) {
		if(transactionTime == null){
			throw new BussinessException("交易时间不能为空");
		}
		// 获取当前时间
		return vSdf.format(transactionTime);
	}
	
	
}
