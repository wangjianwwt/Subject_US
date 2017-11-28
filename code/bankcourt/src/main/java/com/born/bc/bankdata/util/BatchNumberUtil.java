package com.born.bc.bankdata.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 批次号生成工具
 * @author wangjian
 *
 */
public class BatchNumberUtil {
	
	private BatchNumberUtil(){}
	
	public static String create(){
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	
	
}
