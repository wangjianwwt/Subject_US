package com.born.bc.bankdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.bc.bankdata.entity.InterPay;
import com.born.bc.bankdata.entity.InterPayParamDto;
import com.born.bc.bankdata.entity.LastSaleTimeDto;

/**
 * 网上银行数据Dao
 * @author wangjian
 */
@Repository
public interface InterPayMapper {
	
	/**
	 * 查询网银数据列表
	 * @param entity
	 * @return
	 */
	List<InterPay> selectByPage(InterPayParamDto dto);
	

    /**
     * 选择性插入
     * @param list
     * @return
     */
    int batchInsertSelective(@Param("list") List<InterPay> list);

    /**
     * 根据主键查数据
     * @param id
     * @return
     */
    InterPay selectByPrimaryKey(@Param("id") String id);

    /**
     * 选择性修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(InterPay record);

    /**
     * 获取最近一次导入数据的交易时段
     * @return
     */
    LastSaleTimeDto getlastSaleTime();
}