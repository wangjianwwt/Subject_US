package com.born.bc.subject.entity;

/**
 * 科目变更历史实体类
 * @author wenxuan.yang
 */

public class ChangeHistory {
	
	private String id;
	
	//变更前名称
	private String before_name;
	
	//变更后名称
	private String after_name;
	
	//变更时间
	private String changeDate;
	
	//年初数
	private String ncs;
	
	//期末数
	private String qms;
	
	//期初数
	private String qcs;
	
	private String subjectCode;
	
	
	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBefore_name() {
		return before_name;
	}

	public void setBefore_name(String before_name) {
		this.before_name = before_name;
	}

	public String getAfter_name() {
		return after_name;
	}

	public void setAfter_name(String after_name) {
		this.after_name = after_name;
	}

	public String getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(String changeDate) {
		this.changeDate = changeDate;
	}

	public String getNcs() {
		return ncs;
	}

	public void setNcs(String ncs) {
		this.ncs = ncs;
	}

	public String getQms() {
		return qms;
	}

	public void setQms(String qms) {
		this.qms = qms;
	}

	public String getQcs() {
		return qcs;
	}

	public void setQcs(String qcs) {
		this.qcs = qcs;
	}
	
	
}
