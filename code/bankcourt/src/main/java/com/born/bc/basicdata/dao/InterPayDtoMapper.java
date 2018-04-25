package com.born.bc.basicdata.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.bc.basicdata.entity.InterPayDto;
import com.born.bc.basicdata.entity.InterPayParamDto;
import com.born.bc.basicdata.entity.ZoomInterResultDto;

/**
 * 网银mapper
 * @author wangjian
 *
 */
@Repository
public interface InterPayDtoMapper {
    int deleteByPrimaryKey(String id);

    int insert(InterPayDto record);

    int insertSelective(InterPayDto record);

    InterPayDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InterPayDto record);

    int updateByPrimaryKey(InterPayDto record);

    /**
     * 批量插入网银数据
     * @param excuteList
     * @return
     */
	int batchInsert(@Param("list") List<InterPayDto> excuteList);

	/**
	 * 获取已经执行过导入的日期
	 * @param dateSet
	 * @return
	 */
	Set<String> selectDateExcutoed(@Param("sets") Set<String> dateSet);

	/**
	 * 查询网银列表
	 * @param dto
	 * @return
	 */
	List<InterPayDto> selectByList(@Param("dto") InterPayParamDto dto);

	/**
	 * 根据列表查询参数获取查询结果统计
	 * @param dto
	 * @return
	 */
	ZoomInterResultDto getSumDataByListParam(@Param("dto") InterPayParamDto dto);

	/**
	 * 查询指定月份导入的交易日期
	 * @param dateStr
	 * @return
	 */
	Set<String> guideResp(@Param("dateStr") String dateStr);

	/**
	 * 根据Id更新是否拆分字段
	 * @param id
	 * @return
	 */
	int changeIsSplitById(String id);
	
	/**
	 * 根据个案ID清楚案号信息
	 * @param caseId
	 * @return
	 */
	int cleanCaseInfoByCaseId(@Param("caseId") String caseId);

	/**
	 * 网银补录数据
	 * @param dateStr
	 * @return
	 */
	List<Map<String, Object>> isEditData(@Param("dateStr")String dateStr);

	/**
	 * 网银补录
	 * @param dateStr
	 * @return
	 */
	List<String> supplement(@Param("dateStr") String dateStr);

	/**
	 * 导入数量
	 * @param dateStr
	 * @return
	 */
	Integer supplementLength(@Param("dateStr") String dateStr);
}