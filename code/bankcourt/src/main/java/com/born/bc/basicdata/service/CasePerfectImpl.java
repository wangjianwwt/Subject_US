package com.born.bc.basicdata.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.born.bc.basicdata.service.api.CasePerfectApi;
import com.born.bc.commons.utils.LogUtils;
import com.born.bc.task.dao.TaskMapper;
import com.born.bc.task.entity.CompletionCaseNumberDto;
import com.born.bc.task.entity.CompletionCaseNumberPosDto;

@Service
public class CasePerfectImpl implements CasePerfectApi {

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private TaskMapper mapper;
	
	@Override
	public void casePerfectByInter() {
		LogUtils.debug("---casePerfectByInter:" + sdf.format(new Date()));
		List<CompletionCaseNumberDto> list = mapper.findNotCompleteInterPay();
		if (list != null && list.size() > 0) {
			mapper.updateInterPayCaseInfo(list);
		}
	}

	@Override
	public void casePerfectByPos() {
		LogUtils.debug("---casePerfectByPos:" + sdf.format(new Date()));
		List<CompletionCaseNumberPosDto> pList = mapper.findNotCompletePosPay();
		if(!CollectionUtils.isEmpty(pList)){
			mapper.updatePosPayCaseInfo(pList);
		}

	}

}
