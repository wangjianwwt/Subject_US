package com.born.bc.cwtz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.born.bc.commons.entity.PageParamVO;
import com.born.bc.commons.utils.ResultJson;
import com.born.bc.cwtz.dao.CwtzMapper;
import com.born.bc.cwtz.entity.CwtzParamDto;
import com.born.bc.cwtz.entity.ExcelBean;
import com.born.bc.cwtz.entity.ExcelView;
import com.born.bc.cwtz.service.CwtzService;

/**
 * 财务台账controller
 * @author wenxuan.yang
 *
 */
@RequestMapping(value="/cwtz")
@RestController
public class CwtzController {

	@Autowired
	private CwtzService cwtzService;
	
	
	@Autowired
	private CwtzMapper cwtzMapper;


	/**
	 * 科目明细统计
	 * @param cwtzParamDto
	 * @return
	 */
	@RequestMapping(value="/subjectList")
	@ResponseBody
	public ResultJson Subjectlist(CwtzParamDto cwtzParamDto, PageParamVO page){

		return cwtzService.getSubjectCount(cwtzParamDto,page);
	}
	
	
	/**
	 * 科目月收入统计
	 * @param cwtzParamDto
	 * @return
	 */
	@PostMapping(value="/srList")
	@ResponseBody
	public ResultJson srList(@RequestBody CwtzParamDto cwtzParamDto, PageParamVO page){

		return cwtzService.getsrList(cwtzParamDto,page);
	}
	
	
	/**
	 * 资产负债情况
	 * @param cwtzParamDto
	 * @return
	 */
	@PostMapping(value="/zcfzList")
	@ResponseBody
	public ResultJson zcfzList(@RequestBody CwtzParamDto cwtzParamDto, PageParamVO page){

		return cwtzService.getzcfzList(cwtzParamDto,page);
	}
	
	
	@RequestMapping(value="/updateSfjs")
	@ResponseBody
	public ResultJson updateSfjs(String transactionTime){
		try {
			return cwtzService.updateSfjs(transactionTime);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("操作错误");
		}
	}
	
	
	@RequestMapping(value = "/Expexcel")
	@ResponseBody
	public ModelAndView Expexcel(CwtzParamDto cwtzParamDto,PageParamVO page,Model model,String[] fieldName) {
		List<CwtzParamDto> list = cwtzMapper.getSubjectCount(cwtzParamDto);
		List<ExcelBean> excelBeans= new ArrayList<ExcelBean>();
		for(CwtzParamDto cwtzp : list){
			ExcelBean e = new ExcelBean();
			e.setCell1(cwtzp.getSubjectName());
			e.setCell2(cwtzp.getCaseNumber());
			e.setCell3(cwtzp.getAbstractInfo());
			e.setCell4(cwtzp.getDebitPrice());
			e.setCell5(cwtzp.getCreditPrice());
			e.setCell6(cwtzp.getBalance());
			excelBeans.add(e);
		}
		
		return  new ModelAndView(new ExcelView(excelBeans,"科目明细统计",fieldName), (Map<String, ?>) model);
	}

}
