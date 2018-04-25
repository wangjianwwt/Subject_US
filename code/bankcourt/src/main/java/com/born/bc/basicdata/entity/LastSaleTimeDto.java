package com.born.bc.basicdata.entity;

import java.util.Date;

/**
 * 获取最后一次上传的交易时段
 * @author wangjian
 */
public class LastSaleTimeDto {


    /**最近一次导入的交易最大时间*/
    private Date maxTime;
    /**最近一次导入的交易最小时间*/
    private Date minTime;

    public Date getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Date maxTime) {
        this.maxTime = maxTime;
    }

    public Date getMinTime() {
        return minTime;
    }

    public void setMinTime(Date minTime) {
        this.minTime = minTime;
    }

	@Override
	public String toString() {
		return "{\"maxTime\":\"" + maxTime + "\", \"minTime\":\"" + minTime + "\"}";
	}

    
}
