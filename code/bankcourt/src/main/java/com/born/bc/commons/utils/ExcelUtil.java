package com.born.bc.commons.utils;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

/**
 * excel工具类
 * 
 * @author wangjian
 *
 */
public class ExcelUtil {

	/**
	 * 解析模板Excel
	 * 
	 * @param importfile
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> fileDecompose(String templeStr, MultipartFile importfile)
			throws Exception {
		
		// 将文件头字符串信息转成json对象
		JSONObject temple = JSONObject.parseObject(templeStr);
		
		// 得到文件名及后缀
		String fileName = importfile.getOriginalFilename();
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);

		boolean isCsvFile = false;

		Workbook wb = null;
		if ("xlsx".equals(fileType)) {
			wb = new XSSFWorkbook(importfile.getInputStream());
		} else if ("xls".equals(fileType)) {
			wb = new HSSFWorkbook(importfile.getInputStream());
		} else if ("csv".equals(fileType)) {
			isCsvFile = true;
		} else {
			return null;
		}

		Map<String, Object> resolveList = null;
		if (isCsvFile) {
			// 是CSV文件
			resolveList = resolveCsv(importfile.getInputStream(), temple);
		} else {
			resolveList = resolveExcel(wb, temple);
		}

		return resolveList;
	}

	/**
	 * 解析Csv
	 * 
	 * @param inputStream
	 * @param temple
	 * @return
	 */
	private static Map<String, Object> resolveCsv(InputStream inputStream, JSONObject temple) {
		ArrayList<String[]> csvList = JavaCsvOptionUtil.readCsv(inputStream);
		if (csvList == null) {
			return null;
		}
		// 拿到表头
		String[] hearder = csvList.get(1);
		String[] codes = new String[hearder.length];
		for (int i = 0; i < hearder.length; i++) {
			String item = hearder[i].trim();
			String code = temple.getString(item).trim();
			if (StringUtils.isEmpty(code)) {
				throw new RuntimeException("上传的文件和配置不对应，请检查文件表头和配置是否一致!");
			}
			// 按照下标设置code
			codes[i] = code;
		}

		List<Map<String, Object>> resolveList = new ArrayList<>();
		
		for (int i = 2; i < csvList.size(); i++) {
			int noValueNum = 0;
			String[] items = csvList.get(i);
			Map<String, Object> map = new HashMap<>();
			for (int j = 0; j < items.length; j++) {
				//得到当前元素的值
				String value = (StringUtils.isEmpty(items[j]) ? "" : items[j].trim());
				if (j >= codes.length) {
					// 目前只有POS数据会存在越表头长度的情况
					if(value.indexOf("B2") > -1){
						// B2为收款账号
						map.put("receivablesNumber", value);
					}
					if(value.indexOf("B3") > -1){
						// B3为案号
						map.put("caseNumber", value);
					}
				}else{
					map.put(codes[j], value);
					if(StringUtils.isEmpty(value)){
						// 记录空值数量
						noValueNum++;
					}
				}
			}
			// 判断该行是否都为空，都为空就不存入resolveList中
			if(noValueNum >= items.length){
				continue;
			}
			
			resolveList.add(map);
		}

		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("data", resolveList);
		resultMap.put("fristRow", csvList.get(0));
		
		return resultMap;

	}

	/**
	 * 解析Excel
	 * 
	 * @param wb
	 * @param temple
	 * @return
	 */
	private static Map<String, Object> resolveExcel(Workbook wb, JSONObject temple) {
		// 解析sheet 其中不解析表头，下标0：excel的说明，系统不作解析
		Sheet sheet = wb.getSheetAt(0);

		List<Map<String, Object>> sheetData = new ArrayList<Map<String, Object>>();

		// 取得column值，作为实体对象的key，中文对应英文
		// 最终实现一个行转列
		Row headRows = sheet.getRow(0);

		String[] keys = new String[headRows.getLastCellNum()];

		// 设置指定长度的key表，set会重复覆盖
		Set<String> keySets = new HashSet<String>(headRows.getLastCellNum());

		for (int i = 0; i < headRows.getLastCellNum(); i++) {
			Cell cell = headRows.getCell(i);
			String cellStr = cell.getStringCellValue().trim();
			// 充配置值获取文件中的中文表头对应的英文key
			String sourceKey = temple.getString(cellStr);
			if (StringUtils.isEmpty(sourceKey)) {
				throw new RuntimeException("导入的文件表头并未匹配到对应的键,请检查文件与配置是否一致！");
			}
			keys[i] = sourceKey;
			keySets.add(sourceKey);
		}

		// 判断list中的元素数量是否和POI解析出来的row量相等，如果不想等，那么就存在问题，可能是重复，可能是解析异常
		if (keys.length != headRows.getLastCellNum() || keySets.size() != headRows.getLastCellNum()) {
			throw new RuntimeException("请详细检查导入文件的表头是否存在重复!");
		}

		// 读取真实导入数据从下标为3开始
		for (int i = 2; i < (sheet.getLastRowNum() + 1); i++) {
			Row row = sheet.getRow(i);
			Map<String, Object> rowMap = new HashMap<String, Object>();
			int countCellIsNull = 0;
			// 循环keys， 确保key-value的一一对应
			for (int j = 0; j < keys.length; j++) {
				// 读取第j个cell
				Cell cell = row.getCell(j);
				// 将读取到cell转成String
				String cellStr = parseExcel(cell);
				// 将对应的值处理处理一下
				rowMap.put(keys[j], (StringUtils.isEmpty(cellStr)) ? "" : cellStr.trim());

				if (StringUtils.isEmpty(cellStr)) {
					countCellIsNull++;
				}
			}

			// 如果一整行都是null 不继续循环了
			if (countCellIsNull >= keys.length) {
				break;
			}

			// 将当前行添加到 sheetData中
			sheetData.add(rowMap);
		}

		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("data", sheetData);
		resultMap.put("fristRow", null);
		
		return resultMap;

	}

	/**
	 * 根据excel单元格类型获取excel单元格值
	 * 
	 * @param cell
	 * @return
	 */
	private static String parseExcel(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC: {
				short format = cell.getCellStyle().getDataFormat();
				if (format == 14 || format == 31 || format == 57 || format == 58) { // excel中的时间格式
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					double value = cell.getNumericCellValue();
					Date date = DateUtil.getJavaDate(value);
					cellvalue = sdf.format(date);
				}
				// 判断当前的cell是否为Date
				// 先注释日期类型的转换，在实际测试中发现HSSFDateUtil.isCellDateFormatted(cell)只识别2014/02/02这种格式。
				else if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，取得该Cell的Date值 // 对2014-02-02格式识别不出是日期格式
					Date date = cell.getDateCellValue();
					DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = formater.format(date);
				} else { // 如果是纯数字
					// 取得当前Cell的数值
					cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());

				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getStringCellValue().replaceAll("'", "''");
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				cellvalue = null;
				break;
			// 默认的Cell值
			default: {
				cellvalue = " ";
			}
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

}
