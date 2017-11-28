package com.born.bc.bankdata.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 地区码Mapper
 * @author wangjian
 *
 */
@Repository
public interface AreaCodeMapper {
	
	/**
	 * 查询省
	 * @param province
	 * @return
	 */
	public HashSet<String> searchProvinceByParam(@Param("province") String province);
	
	/**
	 * 查询地区码
	 * @param province 省
	 * @param searchName 地区/地区码
	 * @return
	 */
	public List<Map<String, String>> searchByParam(@Param("searchName") String searchName);
}
