package com.born.bc.basicdata.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.born.bc.basicdata.entity.CourtPayOrder;
import com.born.bc.basicdata.entity.CourtPayOrderParamDto;

/**
 * 法院支付单Mapper
 * @author wangjian
 *
 */
@Repository
public interface CourtPayOrderMapper {

    int insertSelective(CourtPayOrder record);

    CourtPayOrder selectByPrimaryKey(@Param("id") String id);

    int updateByPrimaryKeySelective(CourtPayOrder record);
    
    List<CourtPayOrder> selectByList(CourtPayOrderParamDto dto);
    
    int updateDelStatusByPrimaryKey(@Param("ids") List<String> ids);

	int cleanCaseInfoByCaseId(@Param("caseId") String caseId);

}