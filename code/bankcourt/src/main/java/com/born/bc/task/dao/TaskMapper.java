package com.born.bc.task.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.bc.task.entity.CompletionCaseNumberDto;
import com.born.bc.task.entity.CompletionCaseNumberPosDto;

/**
 * taskMapper
 * @author wangjian
 *
 */
@Repository
public interface TaskMapper {
	
	/**
	 * 查询未补全案号的网银数据
	 * @return
	 */
	public List<CompletionCaseNumberDto> findNotCompleteInterPay();
	
	/**
	 * 查询可以匹配成功的pos案号信息
	 * @return
	 */
	public List<CompletionCaseNumberPosDto> findNotCompletePosPay();
	
	
	/**
	 * 补全案号信息(网银)
	 * @param list
	 * @return
	 */
	public int updateInterPayCaseInfo(@Param("list") List<CompletionCaseNumberDto> list);

	/**
	 * 补全案号信息(POS)
	 * @param list
	 * @return
	 */
	public int updatePosPayCaseInfo(@Param("list") List<CompletionCaseNumberPosDto> list);
	
	
}
