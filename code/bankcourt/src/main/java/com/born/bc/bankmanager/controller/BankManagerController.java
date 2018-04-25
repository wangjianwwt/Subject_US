package com.born.bc.bankmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.born.bc.bankmanager.entity.BankManager;
import com.born.bc.bankmanager.service.api.BankManagerServiceApi;
import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;

/**
 * 银行管理
 * @author wangjian
 *
 */
@RestController
@RequestMapping("/bankManager")
public class BankManagerController {
	
	@Autowired
	private BankManagerServiceApi api;

	/**
	 * 查询银行
	 * @return
	 */
	@GetMapping(value="/list")
	public ResultJson list(BankManager entity, PageParamVO page){
		return api.list(entity, page);
	}
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@PostMapping(value="/add")
	public ResultJson add(@RequestBody BankManager entity){
		try {
			return api.add(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("操作失败");
		}
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@PostMapping(value="/edit")
	public ResultJson edit(@RequestBody BankManager entity){
		try{
			return api.edit(entity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("操作失败");
		}
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@PostMapping(value="/delete")
	public ResultJson delete(@RequestBody List<String> ids){
		try{
			return api.delete(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("操作失败");
		}
		
	}
	
	
}
