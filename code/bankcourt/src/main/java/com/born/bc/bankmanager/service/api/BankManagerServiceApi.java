package com.born.bc.bankmanager.service.api;

import java.util.List;

import com.born.bc.bankmanager.entity.BankManager;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;

/**
 * 银行维护业务实现API
 * @author wangjian
 *
 */
public interface BankManagerServiceApi {

	/**
	 * 查询列表
	 * @param entity
	 * @param page
	 * @return
	 */
	ResultJson list(BankManager entity, PageParamVO page);
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	ResultJson add(BankManager entity);
	
	/**
	 * 编辑
	 * @param entity
	 * @return
	 */
	ResultJson edit(BankManager entity);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	ResultJson delete(List<String> ids);
	
	
}
