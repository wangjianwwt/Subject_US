package com.born.bc.commons.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>DateUtils</p>
 * 时间处理工具类
 * @author wangjian
 */
public class DateUtils {
	
	/** 
	 * <p>获取传入时间的00:00:00</p>
	 * @param date
	 * @return
	 */
	public static Date todayStartTime(Date date){
		if(date==null){
			return null;
		}
		Calendar calendar = new GregorianCalendar(); 
	    calendar.setTime(date); 
	    calendar.set(Calendar.HOUR_OF_DAY, 00);
	    calendar.set(Calendar.MINUTE, 00);
	    calendar.set(Calendar.SECOND, 00);
	    date=calendar.getTime();
	    return date;
	}
	
	/** 
	 * <p>获取传入时间的第二天的的00:00:00</p>
	 * @param date
	 * @return
	 */
	public static Date tomorrowStartTime(Date date){
		if(date==null){
			return null;
		}
		Calendar calendar = new GregorianCalendar(); 
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE, 1);
	    calendar.set(Calendar.HOUR_OF_DAY, 00);
	    calendar.set(Calendar.MINUTE, 00);
	    calendar.set(Calendar.SECOND, 00);
	    date=calendar.getTime();
	    return date;
	}
	
	/** 
	 * <p>获取传入时间的23:59:59</p>
	 * @param date
	 * @return
	 */
	public static Date todayEndTime(Date date){
		if(date==null){
			return null;
		}
		Calendar calendar = new GregorianCalendar(); 
	    calendar.setTime(date); 
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    calendar.set(Calendar.MINUTE, 59);
	    calendar.set(Calendar.SECOND, 59);
	    date=calendar.getTime();
	    return date;
	}
	
	/** 
	* <p>时间加减</p>
	* @param date
	* @param i
	* @return
	*/
	public static Date someDaysAgo(Date date, Integer i){
		if(date==null){
			return null;
		}
		Calendar calendar = new GregorianCalendar(); 
		if(i == null){
		    calendar.setTime(date);
		    calendar.set(Calendar.HOUR_OF_DAY, 00);
		    calendar.set(Calendar.MINUTE, 00);
		    calendar.set(Calendar.SECOND, 00);
		    date=calendar.getTime();
		    return date;
		}else{
		    calendar.setTime(date);
		    calendar.add(Calendar.DATE, i);
		    calendar.set(Calendar.HOUR_OF_DAY, 00);
		    calendar.set(Calendar.MINUTE, 00);
		    calendar.set(Calendar.SECOND, 00);
		    date=calendar.getTime();
		    return date;
		}
	}
	
	/** 
	* <p>当前月的开始时间</p>
	* @return
	*/
	public static Date getMonthStart() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}
	
	/** 
	* <p>获取本月最后时间</p>
	* @return
	*/
	public static Date getMonthEnd() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return cal.getTime();
		}
}