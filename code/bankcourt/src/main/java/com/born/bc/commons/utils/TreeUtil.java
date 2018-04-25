package com.born.bc.commons.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.born.bc.subject.entity.Subject;

public class TreeUtil {

	public static Map<String,Object> mapArray = new LinkedHashMap<String, Object>(); 
	  public List<Subject> menuCommon; 
	  public List<Object> list = new ArrayList<Object>(); 
	    
	  public List<Object> TreeList(List<Subject> subjects){   
	    this.menuCommon = subjects; 
	    for (Subject s : subjects) {   
	      Map<String,Object> mapArr = new LinkedHashMap<String, Object>(); 
	      if(null == s.getParent_id() || s.getParent_id().equals("")){ 
	        mapArr.put("id", s.getId()); 
	        mapArr.put("name", s.getS_name());  
	        mapArr.put("pid", s.getParent_id());
	        mapArr.put("pCode", s.getS_code()); 
	        mapArr.put("children", menuChild(s.getS_code()));  
	        list.add(mapArr); 
	      } 
	    }   
	    return list; 
	  } 
	   
	  public List<?> menuChild(String code){ 
	    List<Object> lists = new ArrayList<Object>(); 
	    for(Subject a:menuCommon){ 
	      Map<String,Object> childArray = new LinkedHashMap<String, Object>(); 
	      if(null != a.getParent_id() && a.getParent_id().equals(code)){ 
	        childArray.put("id", a.getId()); 
	        childArray.put("name", a.getS_name()); 
	        childArray.put("pid", a.getParent_id()); 
	        childArray.put("pCode", a.getS_code()); 
	        childArray.put("children", menuChild(a.getS_code()));
	        lists.add(childArray); 
	      } 
	    } 
	    return lists; 
	  } 
}
