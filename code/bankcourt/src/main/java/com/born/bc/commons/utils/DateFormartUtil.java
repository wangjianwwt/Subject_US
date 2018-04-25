package com.born.bc.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormartUtil {

	static final SimpleDateFormat datetime_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static final SimpleDateFormat date_sdf = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 得到datetime字符串
	 * @param date
	 * @return
	 */
	public static String getDateTimeStr(Date date){
		return datetime_sdf.format(date);
	}
	
	/**
	 * 得到date字符串
	 * @param date
	 * @return
	 */
	public static String getDateStr(Date date){
		return date_sdf.format(date);
	}
	
	/**
	 * 时间字符串转日期
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date getDateTime(String dateStr) throws ParseException{
		return datetime_sdf.parse(dateStr);
	}
	
	
	
}
