package com.born.bc.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;

import com.csvreader.CsvReader;

/**
 * 读取Csv文件工具类
 * @author wangjian
 *
 */
public class JavaCsvOptionUtil {
	
	private JavaCsvOptionUtil(){}
	
	private static String encode = "GBK";
	
	
	public static ArrayList<String[]> readCsv(InputStream inputStream){
		
		try {
			CsvReader reader = new CsvReader(inputStream, Charset.forName(encode));
			
			ArrayList<String[]> csvList = new ArrayList<String[]>(); //用来保存数据  
			while(reader.readRecord()){ //逐行读入除表头的数据      
			    csvList.add(reader.getValues());   
			}
			reader.close();  
			return csvList;
		} catch (IOException e) {
			e.printStackTrace();
		}   
		return null;
         
	}
	
	
}
