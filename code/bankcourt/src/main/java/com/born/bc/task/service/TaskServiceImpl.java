package com.born.bc.task.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.born.bc.commons.utils.LogUtils;
import com.born.bc.task.dao.TaskMapper;
import com.born.bc.task.entity.CompletionCaseNumberDto;
import com.born.bc.task.entity.CompletionCaseNumberPosDto;
import com.born.bc.task.service.api.TaskServiceApi;

/**
 * 补全案号所使用的任务
 * 
 * @author wangjian
 *
 */
@Component
public class TaskServiceImpl implements TaskServiceApi {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private TaskMapper mapper;

	/**
	 * 每天的1,3,5,7,9,19,21,23时执行
	 */
	@Scheduled(cron = "0 0 1,3,5,7,9,19,21,23 * * ? ")
	public void CompletionCaseNumber() {
		LogUtils.debug("---task start:" + sdf.format(new Date()));
		try {
			LogUtils.debug("---for InterPay:" + sdf.format(new Date()));
			List<CompletionCaseNumberDto> list = mapper.findNotCompleteInterPay();
			if (list != null && list.size() > 0) {
				mapper.updateInterPayCaseInfo(list);
			}
			LogUtils.debug("---for PosPay:" + sdf.format(new Date()));
			List<CompletionCaseNumberPosDto> pList = mapper.findNotCompletePosPay();
			if(!CollectionUtils.isEmpty(pList)){
				mapper.updatePosPayCaseInfo(pList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			LogUtils.debug("---task error ended :" + sdf.format(new Date()));
		} finally {
			LogUtils.debug("---task end:" + sdf.format(new Date()));
		}
	}

}
