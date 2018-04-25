package com.born.bc.basicdata.entity;

public class HomeEntity {
	
	private String dateStr;
	private boolean posImport;
	private boolean interImport;
	private boolean posSupplement;
	private boolean interSupplement;
	private boolean lockInfo;
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public boolean isPosImport() {
		return posImport;
	}
	public void setPosImport(boolean posImport) {
		this.posImport = posImport;
	}
	public boolean isInterImport() {
		return interImport;
	}
	public void setInterImport(boolean interImport) {
		this.interImport = interImport;
	}
	public boolean isPosSupplement() {
		return posSupplement;
	}
	public void setPosSupplement(boolean posSupplement) {
		this.posSupplement = posSupplement;
	}
	public boolean isInterSupplement() {
		return interSupplement;
	}
	public void setInterSupplement(boolean interSupplement) {
		this.interSupplement = interSupplement;
	}
	public boolean isLockInfo() {
		return lockInfo;
	}
	public void setLockInfo(boolean lockInfo) {
		this.lockInfo = lockInfo;
	}
	
	
	
}
