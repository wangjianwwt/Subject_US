package com.born.bc.subject.service.imp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.IdGenerator;
import com.born.bc.commons.utils.LogUtils;
import com.born.bc.commons.utils.ResultJson;
import com.born.bc.commons.utils.TreeUtil;
import com.born.bc.subject.dao.SubjectMapper;
import com.born.bc.subject.entity.Subject;
import com.born.bc.subject.entity.ChangeHistory;
import com.born.bc.subject.service.SubjectService;

/**
 * 科目业务实现类
 * @author wenxuan.yang
 *
 */
@Service
public class SubjectServiceImp implements SubjectService {

	@Autowired
	private SubjectMapper subjectMapper;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public ResultJson getSubjectList(Subject subject,String s) {
		try {
			if("tree".equals(s)){
				List<Object> list = new TreeUtil().TreeList(subjectMapper.getSubjectList(subject));
				return ResultJson.buildSuccess("查询成功!", list);
			}
			if(null != subject && !"".equals(subject.getParent_id()) && null != subject.getParent_id()){
				subject.setS_code(subject.getParent_id());
				Subject ss = subjectMapper.getSubjectBycode(subject);
				return ResultJson.buildSuccess("查询成功!", ss);
			}
			
			List<Subject> subjects = subjectMapper.getSubjectList(subject);
			return ResultJson.buildSuccess("查询成功!", subjects);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败!");
		}
	}

	@Override
	public ResultJson getSubjectMj() {
		try {
			List<Subject> subjectMjs = subjectMapper.getSubjectMj();
			return ResultJson.buildSuccess("查询成功！", subjectMjs);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败!");
		}
	}
	
	
	@Override
	public ResultJson isChange(String subjectCode) {
		try {
			List<ChangeHistory> changes = subjectMapper.isChange(subjectCode);
			return ResultJson.buildSuccess("查询成功！", changes);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败!");
		}
	}
	

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson add(Subject subject) throws BussinessException {
		// 校验个案唯一
		Subject s = subjectMapper.getSubjectBycode(subject);
		if (null != s) {
			return ResultJson.buildError("科目编码已经存在!");
		}
		subject.setId(IdGenerator.getUUID());
		subjectMapper.addSubject(subject);
		return ResultJson.buildSuccess("新增成功!");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson update(Subject subject) throws BussinessException {
		if (StringUtils.isEmpty(subject.getId())) {
			return ResultJson.buildError("缺乏主键");
		}
		Subject s = subjectMapper.getSubjectBycode(subject);
		if(s != null && (!subject.getS_name().equals(s.getS_name()))){
			ChangeHistory c = new ChangeHistory();
			c.setId(IdGenerator.getUUID());
			c.setBefore_name(s.getS_name());
			c.setAfter_name(subject.getS_name());
			c.setChangeDate(sdf.format(new Date()));
			c.setQcs(s.getQcs());
			c.setNcs("55");
			c.setQms("66");
			c.setSubjectCode(subject.getS_code());
			subjectMapper.addChangeHistory(c);
		}
		
		subjectMapper.updateSubject(subject);
		return ResultJson.buildSuccess("修改成功!");
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson del(String id) throws BussinessException {
		if (StringUtils.isEmpty(id)) {
			return ResultJson.buildParamError("参数异常");
		}
		List<Subject> list = subjectMapper.getSubjectByParent(id);
		if(null != list && list.size()>0){
			return ResultJson.buildParamError("不能直接删除父级，先删除子级。");
		}
		List<String> result = subjectMapper.getListBySujectId(id);
		if(null != result && result.size()>0){
			return ResultJson.buildParamError("该科目已有基础数据关联，不可删除。");
		}
		subjectMapper.deleteSubject(id);
		
		return ResultJson.buildSuccess("删除成功");
	}

}
