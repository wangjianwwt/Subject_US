package com.born.bc.subject.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.born.bc.subject.entity.Subject;
import com.born.bc.subject.entity.ChangeHistory;
/**
 * 案件信息mapper
 * @author wenxuan.yang
 *
 */
@Repository
public interface SubjectMapper {

	
	public List<Subject> getSubjectList(Subject subject);
	
	/**
	 * 查询所有末级科目
	 * @return
	 */
	public List<Subject> getSubjectMj();
	
	public Subject getSubjectBycode(Subject subject);
	
	public List<Subject> getSubjectByParent(String id);
	
	public int addSubject(Subject subject);
	
	public int updateSubject(Subject subject);
	
	public int deleteSubject(String id);
	
	public int addChangeHistory(ChangeHistory changeHistory);
	
	public List<String> getListBySujectId(String id);
	
	public List<ChangeHistory> isChange(String subjectCode);
	
}
