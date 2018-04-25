package com.born.bc.basicdata.entity;

import com.github.pagehelper.PageInfo;

/**
 * 作为网银列表查询统计数据
 * @author wangjian
 *
 */
public class MoreResultForInterPayDto {
	
	private PageInfo<InterPay> pageInfo;
	
	private ZoomInterResultDto zirDto;

	public PageInfo<InterPay> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<InterPay> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public ZoomInterResultDto getZirDto() {
		return zirDto;
	}

	public void setZirDto(ZoomInterResultDto zirDto) {
		this.zirDto = zirDto;
	}
	
	
}
