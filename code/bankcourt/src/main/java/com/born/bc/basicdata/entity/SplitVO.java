package com.born.bc.basicdata.entity;

import java.util.List;

public class SplitVO {
	/**父级Id*/
	private String parentId;
	/**拆分明细*/
	List<SplitDetailVO> items;
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public List<SplitDetailVO> getItems() {
		return items;
	}
	public void setItems(List<SplitDetailVO> items) {
		this.items = items;
	}
	
}
