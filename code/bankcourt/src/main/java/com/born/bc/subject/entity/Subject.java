package com.born.bc.subject.entity;

/**
 * 科目实体类
 * @author wenxuan.yang
 */

public class Subject {
	
	private String id;
	
	//科目编码
	private String s_code;
	
	//科目名称
	private String s_name;
	
	//上级编码
	private String parent_id;
	
	//类型
	private String type;
	
	//期初数
	private String qcs;
	
	public String getQcs() {
		return qcs;
	}

	public void setQcs(String qcs) {
		this.qcs = qcs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getS_code() {
		return s_code;
	}

	public void setS_code(String s_code) {
		this.s_code = s_code;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	
	
}
