package com.born.bc.caseinfo.service.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.born.bc.caseinfo.entity.CaseInfo;
import com.born.bc.caseinfo.entity.CaseInfoParamDto;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;

/**
 * 个案业务Api
 * @author wenxuan.yang
 *
 */
public interface CaseServiceApi {
	
	/**
	 *  列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public ResultJson list(CaseInfoParamDto dto, PageParamVO page);
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	public ResultJson add(CaseInfo entity) throws BussinessException;
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	public ResultJson update(CaseInfo entity) throws BussinessException;
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public ResultJson del(String id) throws BussinessException;
	
	/**
	 * 根据案号获取个案信息
	 * @param caseNumbers
	 * @return
	 */
	public List<CaseInfo> getCaseInfoByCaseNumbers(List<String> caseNumbers);

	/**
	 * 查询个案参照
	 * @return
	 */
	public ResultJson getCaseLoser();

	/**
	 * 查询列表中不存在的案号
	 * @param otherList
	 * @return
	 */
	public Set<String> checkCaseHave(List<Map<String, String>> otherList);

	/**
	 * 创建多个个案档案信息
	 * @param otherList
	 */
	public void createMore(List<Map<String, String>> otherList);

	/**
	 * 个案参照，包含余额
	 * @return
	 */
	public ResultJson havingBalance();

	/**
	 * 案件收支明细
	 * @param id
	 * @return
	 */
	public ResultJson caseDetail(String id);
	
	
}
