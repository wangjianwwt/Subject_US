package com.born.bc.caseinfo.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.bc.caseinfo.entity.CaseInfo;
import com.born.bc.caseinfo.entity.CaseInfoParamDto;

/**
 * 个案Mapper
 * @author wenxuan.yang
 *
 */
@Repository
public interface CaseInfoMapper {
	/**
	 * 查询个案列表
	 * @param dto
	 * @return
	 */
	List<CaseInfo> selectByList(CaseInfoParamDto dto);
	
	/**
	 * 根据ID进行逻辑删除
	 * @param id
	 * @return
	 */
    int updateDelStatusByPrimaryKey(String id);

    /**
     * 插入个案
     * @param record
     * @return
     */
    int insertSelective(CaseInfo record);
    
    /**
     * 根据ID查询个案
     * @param id
     * @return
     */
    CaseInfo selectByPrimaryKey(String id);

    /**
     * 修改个案
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(CaseInfo record);

    /**
     * 根据案号获取个案信息
     * @param caseNumbers
     * @return
     */
	List<CaseInfo> getCaseInfoByCaseNumbers(@Param("list") List<String> list);

	/**
	 * 案号唯一检查
	 * @param caseNumber
	 * @return
	 */
	Integer checkCaseNumberOnly(@Param("caseNumber") String caseNumber);

	/**
	 * 参照
	 * @return
	 */
	List<Map<String, Object>> getCaseLoser();

	/**
	 * 检查案号是否存在
	 * @param otherList
	 * @return
	 */
	Set<String> checkCaseHave(@Param("sets") Set<String> sets);
	
	/**
	 * 批量插入案号
	 * @param cases
	 * @return
	 */
	int batchInsert(@Param("cases") List<CaseInfo> cases);

	/**
	 * 参照（带余额）
	 * @return
	 */
	List<Map<String, Object>> havingBalance();

	/**
	 * 根据caseId查询收支明细
	 * @param id
	 * @return
	 */
	List<Map<String, Object>> caseDetail(@Param("id") String id);

	/**
	 * 查看案号关联
	 * @param id
	 * @return
	 */
    Integer selectHaveCaseInDB(@Param("caseId") String id);
}