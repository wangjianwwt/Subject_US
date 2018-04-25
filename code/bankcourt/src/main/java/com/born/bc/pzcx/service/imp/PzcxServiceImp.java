package com.born.bc.pzcx.service.imp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.born.bc.commons.utils.ResultJson;
import com.born.bc.cwtz.entity.CwtzParamDto;
import com.born.bc.pzcx.dao.PzcxMapper;
import com.born.bc.pzcx.entity.PzcxParamDto;
import com.born.bc.pzcx.service.PzcxService;
import com.born.bc.subject.dao.SubjectMapper;
import com.born.bc.subject.entity.Subject;

/**
 * 财务台账实现类
 * @author wenxuan.yang
 *
 */
@Service
public class PzcxServiceImp implements PzcxService {

	@Autowired
	private PzcxMapper pzcxMapper;
	
	@Autowired
	private SubjectMapper subjectMapper;

	@Override
	public ResultJson getPzcxList(PzcxParamDto pzcxParamDto) {
		try {
			List<PzcxParamDto> list = pzcxMapper.getPzcxList(pzcxParamDto);
			for(PzcxParamDto pzcx : list){
				Subject subject = new Subject();
				subject.setS_code(pzcx.getParentId());
				pzcx.setSubjectName(subjectMapper.getSubjectBycode(subject).getS_name()+"→"+pzcx.getSubjectName());
			}
			
			List<PzcxParamDto> list2 = pzcxMapper.getSumList(pzcxParamDto);
			
			if(null != list2 && list2.size()>0){
				for(int i = 0;i<list2.size();i++){
					PzcxParamDto Psum = list2.get(i);
					PzcxParamDto dto = new PzcxParamDto();
					String sj = pzcxParamDto.getTransactionTime().replace("-", "");
					dto.setCorporateName(sj+Psum.getSubjectName()+"收入");
					dto.setCreditPrice(Psum.getSrSum());
					if(Double.valueOf(dto.getCreditPrice())>0){
						list.add(dto);
					}
					
					PzcxParamDto dto1 = new PzcxParamDto();
					dto1.setCorporateName(sj+Psum.getSubjectName()+"支出");
					dto1.setDebitPrice(Psum.getZcSum());
					
					if(Double.valueOf(dto1.getDebitPrice())>0){
						list.add(dto1);
					}
				}
				
			}
			
			
			return ResultJson.buildSuccess("查询成功！", list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询失败!");
		}
	}

}
