package com.born.bc.basicdata.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.bc.basicdata.entity.PosPay;
import com.born.bc.basicdata.entity.PosPayDto;
import com.born.bc.basicdata.entity.PosPayParamDto;
import com.born.bc.basicdata.entity.ZoomPosParamDto;

@Repository
public interface PosPayDtoMapper {
    int deleteByPrimaryKey(String id);

    PosPayDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PosPayDto record);

    int updateByPrimaryKey(PosPayDto record);
    
    /**
     * 查询网银列表
     * @param dto
     * @return
     */
    List<PosPay> selectByList(PosPayParamDto dto);

    /**
     * 批量插入pos数据
     * @param excuteList
     * @return
     */
	int batchInsert(@Param("list") List<PosPayDto> excuteList);

	/**
	 * 查询已经执行过导入的日期
	 * @param paramDateSet
	 * @return
	 */
	Set<String> selectExcutedDate(@Param("sets") Set<String> paramDateSet);

	/**
	 * 查询统计
	 * @param dto
	 * @return
	 */
	ZoomPosParamDto getSumDataByListParam(PosPayParamDto dto);
	
	/**
	 * 查询指定月份导入的交易日期
	 * @param dateStr
	 * @return
	 */
	Set<String> guideResp(@Param("dateStr") String dateStr);
	
	/**
	 * 根据个案ID清楚案号信息
	 * @param caseId
	 * @return
	 */
	int cleanCaseInfoByCaseId(@Param("caseId") String caseId);

	/**
	 * 查询已经结算的日期集合
	 * @param dateSet
	 * @return
	 */
	Set<String> lockData(@Param("dateStr") String dateStr);

	/**
	 * pos补录数据
	 * @param dateStr
	 * @param isEdit
	 * @return
	 */
	List<Map<String, Object>> isEditData(@Param("dateStr") String dateStr);

	/**
	 * 补录数据查询
	 * @param dateStr
	 * @return
	 */
	List<String> supplement(@Param("dateStr") String dateStr);

	/**
	 * 查询已经结算的日期
	 * @param dateSpSet
	 * @return
	 */
	Set<String> lockDataByParam(@Param("sets") Set<String> dateSpSet);

	/**
	 * 查询当月一共有多少数据
	 * @param dateStr
	 * @return
	 */
	Integer supplementLength(@Param("dateStr") String dateStr);
}