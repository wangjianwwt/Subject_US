package com.born.bc.subject.service;

import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;
import com.born.bc.subject.entity.Subject;

/**
 * 科目业务接口
 * @author wenxuan.yang
 *
 */
public interface SubjectService {

	/**
	 * 获取科目信息
	 * @param subject
	 * @return
	 */
	public ResultJson getSubjectList(Subject subject,String s);

	/**
	 * 查询所有末级科目
	 * @return
	 */
	public ResultJson getSubjectMj();
	
	
	/**
	 * 查询科目改变历史
	 * @return
	 */
	public ResultJson isChange(String subjectCode);
	
	
	/**
	 * 新增
	 * @param subject
	 * @return
	 */
	public ResultJson add(Subject subject) throws BussinessException;
	
	/**
	 * 修改
	 * @param subject
	 * @return
	 */
	public ResultJson update(Subject subject) throws BussinessException;
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public ResultJson del(String id) throws BussinessException;

}
