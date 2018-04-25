package com.born.bc.commons.utils;

import java.math.BigDecimal;
import java.util.List;

import com.born.bc.cwtz.entity.CwtzParamDto;

public class TotalUtil {

	public List<CwtzParamDto> subjectSum; 

	public List<CwtzParamDto> subjectSum(List<CwtzParamDto> dtos){   
		this.subjectSum = dtos; 

		for (CwtzParamDto s : dtos) {   
			menuChild(s);  
		} 
		return dtos;
	}   


	public void menuChild(CwtzParamDto s){ 
		BigDecimal totalNcs = BigDecimal.ZERO;
		BigDecimal totalDysr = BigDecimal.ZERO;
		BigDecimal totalDyzc = BigDecimal.ZERO;
		BigDecimal totalDnsr = BigDecimal.ZERO;
		BigDecimal totalDnzc = BigDecimal.ZERO;
		BigDecimal totalQms = BigDecimal.ZERO;
		for(CwtzParamDto a:subjectSum){ 
			if(null != a.getParentId() && a.getParentId().equals(s.getSubjectCode())){ 
				menuChild(a); 
				totalNcs = totalNcs.add(a.getNcs());
				totalDysr = totalDysr.add(a.getDysr());
				totalDyzc = totalDyzc.add(a.getDyzc());
				totalDnsr = totalDnsr.add(a.getDnsr());
				totalDnzc = totalDnzc.add(a.getDnzc());
				totalQms = totalQms.add(a.getQms());
			} 
		}
		if(!totalNcs.equals(BigDecimal.ZERO)){
			s.setNcs(totalNcs);
		}
		if(!totalDysr.equals(BigDecimal.ZERO)){
			s.setDysr(totalDysr);
		}
		if(!totalDyzc.equals(BigDecimal.ZERO)){
			s.setDyzc(totalDyzc);
		}
		if(!totalDnsr.equals(BigDecimal.ZERO)){
			s.setDnsr(totalDnsr);
		}
		if(!totalDnzc.equals(BigDecimal.ZERO)){
			s.setDnzc(totalDnzc);
		}
		if(!totalQms.equals(BigDecimal.ZERO)){
			s.setQms(totalQms);
		}
	} 
}
