package com.born.bc.subject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;
import com.born.bc.subject.entity.Subject;
import com.born.bc.subject.service.SubjectService;

/**
 * 科目信息controller
 * @author wenxuan.yang
 *
 */
@RequestMapping(value="/subject")
@RestController
public class SubjectController {

	@Autowired
	private SubjectService subjectService;


	/**
	 * 查询科目List
	 * @param subject
	 * @return
	 */
	@RequestMapping(value="/list")
	@ResponseBody
	public ResultJson list(Subject subject){

		return subjectService.getSubjectList(subject,"tree");
	}
	
	@RequestMapping(value="/subjectList")
	@ResponseBody
	public ResultJson subjectList(Subject subject){

		return subjectService.getSubjectList(subject,"");
	}
	
	/**
	 * 查询所有末级科目
	 * @return
	 */
	@RequestMapping(value="/getSubjectMj")
	@ResponseBody
	public ResultJson getSubjectMj(){
		return subjectService.getSubjectMj();
	}
	
	
	/**
	 * 查询科目修改历史
	 * @return
	 */
	@RequestMapping(value="/isChange")
	@ResponseBody
	public ResultJson isChange(String subjectCode){
		return subjectService.isChange(subjectCode);
	}
	
	
	/**
	 * 新增
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResultJson add(@RequestBody Subject subject) {
		try {
			return subjectService.add(subject);
		} catch (BussinessException e) {
			e.printStackTrace();
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			return ResultJson.buildError("新增失败！");
		}
	}

	/**
	 * 修改
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ResultJson edit(@RequestBody Subject subject) {
		try {
			return subjectService.update(subject);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("编辑失败！");
		}
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public ResultJson del(@RequestBody Subject subject) {
		try {
			return subjectService.del(subject.getId());
		} catch (BussinessException e) {
			e.printStackTrace();
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			return ResultJson.buildError("删除失败！");
		}
	}

}
