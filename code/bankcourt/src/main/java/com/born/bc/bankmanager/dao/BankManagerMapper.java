package com.born.bc.bankmanager.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.bc.bankmanager.entity.BankManager;

@Repository
public interface BankManagerMapper {
    int deleteByPrimaryKey(String id);

    int insertSelective(BankManager record);

    BankManager selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BankManager record);

    int updateByPrimaryKey(BankManager record);

	List<BankManager> selectByList(BankManager entity);
	
	int updateDelStatus(@Param("ids") List<String> ids);

	List<BankManager> selectByBanckAccount(BankManager entity);
}