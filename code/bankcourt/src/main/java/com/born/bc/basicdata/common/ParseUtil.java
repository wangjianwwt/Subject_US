package com.born.bc.basicdata.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 转化类
 * @author wangjian
 * @param <T>
 */
public class ParseUtil<T> {

    private ParseUtil(){
    }

    /**
     * 将未知泛型集合转换为指定泛型集合
     * @param fileData
     * @param javaClass
     * @return
     */
    public static <T> List<T> parseAboutList(List<Map<String, Object>> fileData, Class<T> javaClass) {

        List<T> resultList = new ArrayList<T>();
        for(Map<String, Object> map : fileData){
            // 恶心的强转
            JSONObject json = (JSONObject) JSONObject.toJSON(map);
            T javaObject = JSONObject.toJavaObject(json, javaClass);
            resultList.add(javaObject);
        }

        return resultList;

    }
}
