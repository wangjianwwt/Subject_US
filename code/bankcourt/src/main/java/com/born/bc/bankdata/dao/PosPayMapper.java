package com.born.bc.bankdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.born.bc.bankdata.entity.PosPay;
import com.born.bc.bankdata.entity.PosPayParamDto;

public interface PosPayMapper {

    PosPay selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PosPay record);

	void batchInsertSelective(@Param("list") List<PosPay> list);
	
	List<PosPay> selectByList(PosPayParamDto dto);
	
}