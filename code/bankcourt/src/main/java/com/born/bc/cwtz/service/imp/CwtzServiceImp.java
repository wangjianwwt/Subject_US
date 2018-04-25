package com.born.bc.cwtz.service.imp;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;
import com.born.bc.commons.utils.TotalUtil;
import com.born.bc.cwtz.dao.CwtzMapper;
import com.born.bc.cwtz.entity.CwtzParamDto;
import com.born.bc.cwtz.service.CwtzService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 财务台账实现类
 * @author wenxuan.yang
 *
 */
@Service
public class CwtzServiceImp implements CwtzService {

	@Autowired
	private CwtzMapper cwtzMapper;

	@Override
	public ResultJson getSubjectCount(CwtzParamDto cwtzParamDto, PageParamVO page) {
		try {

			PageHelper.startPage(page.getPage(), page.getLimit());
			List<CwtzParamDto> list = cwtzMapper.getSubjectCount(cwtzParamDto);
			if(null != list && list.size()>0){
				BigDecimal totalSr = BigDecimal.ZERO;
				BigDecimal totalZc = BigDecimal.ZERO;
				BigDecimal totalYe = BigDecimal.ZERO;
				for(CwtzParamDto d : list){
					totalSr = totalSr.add(new BigDecimal(d.getDebitPrice()));
					totalZc = totalZc.add(new BigDecimal(d.getCreditPrice()));
					totalYe = totalYe.add(new BigDecimal(d.getBalance()));
				}
				CwtzParamDto dd = new CwtzParamDto();
				dd.setCorporateName("合计");
				dd.setDebitPrice(totalSr.toString());
				dd.setCreditPrice(totalZc.toString());
				dd.setBalance(totalYe.toString());
				list.add(dd);
			}
			PageInfo<CwtzParamDto> pageInfo = new PageInfo<CwtzParamDto>(list);
			return ResultJson.buildSuccess("查询成功！", pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败!");
		}
	}

	@Override
	public ResultJson getsrList(CwtzParamDto cwtzParamDto, PageParamVO page) {
		try {

//			PageHelper.startPage(page.getPage(), page.getLimit());
			List<CwtzParamDto> list = new TotalUtil().subjectSum(cwtzMapper.getsrList(cwtzParamDto));
//			PageInfo<CwtzParamDto> pageInfo = new PageInfo<CwtzParamDto>(list);
			return ResultJson.buildSuccess("查询成功！", list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败!");
		}
	}

	@Override
	public ResultJson getzcfzList(CwtzParamDto cwtzParamDto, PageParamVO page) {
		try {

//			PageHelper.startPage(page.getPage(), page.getLimit());
			List<CwtzParamDto> list = new TotalUtil().subjectSum(cwtzMapper.getzcfzList(cwtzParamDto));
//			PageInfo<CwtzParamDto> pageInfo = new PageInfo<CwtzParamDto>(list);
			return ResultJson.buildSuccess("查询成功！", list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败!");
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ResultJson updateSfjs(String transactionTime) {
		cwtzMapper.updateSfjsI(transactionTime);
		cwtzMapper.updateSfjsP(transactionTime);
		
		return ResultJson.buildSuccess("success");
	}

}
