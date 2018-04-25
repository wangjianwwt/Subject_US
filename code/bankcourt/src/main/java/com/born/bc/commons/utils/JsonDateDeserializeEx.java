package com.born.bc.commons.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateDeserializeEx extends JsonDeserializer<Date> {
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext dc)
			throws IOException, JsonProcessingException {
		Date date = null;  
        try {  
        	String dateStr = jp.getText();
            if(!StringUtils.isEmpty(dateStr)){
            	date = sdf.parse(dateStr);  
            }
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return date;  
	}

}
