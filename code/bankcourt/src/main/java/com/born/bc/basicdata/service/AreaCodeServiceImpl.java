package com.born.bc.basicdata.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.born.bc.basicdata.dao.AreaCodeMapper;
import com.born.bc.basicdata.service.api.AreaCodeServiceApi;
import com.born.bc.commons.exception.BussinessException;
import com.born.bc.commons.utils.ResultJson;


/**
 * 地区码API实现
 * @author wangjian
 *
 */
@Service
public class AreaCodeServiceImpl implements AreaCodeServiceApi {

	@Autowired
	private AreaCodeMapper mapper;
	
	@Override
	public ResultJson searchByProvince(String province) {
		try {
			// 拿到省
			HashSet<String> data = mapper.searchProvinceByParam(province);
			if(CollectionUtils.isEmpty(data)){
				return ResultJson.buildSuccess("查询无数据！");
			}
			// 根据省区查询地区码
			List<Map<String, String>> list = mapper.searchByProvince(province);
			
			// 申明返回集合
			List<Map<String,Object>> resultData = new ArrayList<>(data.size());
			
			if(! CollectionUtils.isEmpty(list)){
				int index = 1;
				for (String thisP : data) {
					// 用作province分组的list
					List<Map<String,String>> groupList = new ArrayList<>();
					for(Map<String, String> map : list){
						String thisProvince = map.get("province");
						String thisName = map.get("name");
						String thisAreaCode = map.get("areaCode");
					
						if(thisP.equals(thisProvince)){
							Map<String, String> nodeMap = new HashMap<>(2);
							nodeMap.put("name", thisName);
							nodeMap.put("areaCode", thisAreaCode);
							groupList.add(nodeMap);
						}
					}
					// 组装一个省下的所有地区码
					Map<String, Object> groupMap = new HashMap<>(3);
					groupMap.put("province", thisP);
					groupMap.put("citys", groupList);
					groupMap.put("id", index);
					// 加入返回结果List
					resultData.add(groupMap);
					//计数
					index++;
				}
			}else{
				return ResultJson.buildSuccess("查询成功,但是不存在地区码");
			}
			return ResultJson.buildSuccess("查询成功！", resultData);
		} catch (BussinessException e) {
			return ResultJson.buildError(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultJson.buildError("查询错误！");
		}
	}

}
