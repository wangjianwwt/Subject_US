package  com.born.bc.cwtz.entity;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * <h3> </h3>
 * 创建日期： 2017年11月21日
 * @author 杨文轩
 */
public class ExcelView extends AbstractExcelView {

	private List<ExcelBean> list;
	private String[]  tableTop;
	private String excelName;


	public ExcelView(List<ExcelBean> list,String excelName,String... tableTop){
		this.list = list;
		this.excelName = excelName;
		this.tableTop = tableTop;
	}


	@Override
	protected void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest req,
			HttpServletResponse response) throws Exception {


		System.out.println(list);


		System.out.println(tableTop);

		String fileName =excelName+".xls";  
		response.setCharacterEncoding("UTF-8");  
		response.setContentType("application/ms-excel");  
		response.setHeader("Content-Disposition", "inline; filename="+new String(fileName.getBytes(),"iso8859-1"));
		OutputStream outputStream = response.getOutputStream();



		HSSFSheet sheet = workbook.createSheet(excelName);
		for(int i=1;i<tableTop.length;i++){
			sheet.setColumnWidth(i, 40 * 256);//设置列单元格的宽度
		}


		/*************设置标题字体开始**************/
		HSSFCellStyle cellStyle = workbook.createCellStyle();

		cellStyle.setBorderBottom(CellStyle.BORDER_THIN); 
		cellStyle.setBorderTop(CellStyle.BORDER_THIN); 
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN); 
		cellStyle.setBorderRight(CellStyle.BORDER_THIN); 

		cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);//设置居中
		HSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight((short) 300);
		cellStyle.setFont(font);//把字体应用到当前的样式
		/*************设置标题字体结束**************/
		/*************设置内容字体开始**************/
		HSSFCellStyle cellStyle2 = workbook.createCellStyle();

		cellStyle2.setBorderBottom(CellStyle.BORDER_THIN); 
		cellStyle2.setBorderTop(CellStyle.BORDER_THIN); 
		cellStyle2.setBorderLeft(CellStyle.BORDER_THIN); 
		cellStyle2.setBorderRight(CellStyle.BORDER_THIN); 

		HSSFFont font2=workbook.createFont();
		font2.setFontHeight((short) 200);
		cellStyle2.setFont(font2);//把字体应用到当前的样式


		/*************设置内容字体结束**************/

		HSSFRow row0 = sheet.createRow(0);
		row0.setHeight((short) 700);
		Cell cell00 = row0.createCell(0);
		cell00.setCellValue(excelName);
		cell00.setCellStyle(cellStyle);

		CellRangeAddress cRangeAddress = new CellRangeAddress(0,0,0,5);
		sheet.addMergedRegion(cRangeAddress);


		HSSFRow row1 = sheet.createRow(1);
		row1.setHeight((short) 600);

		for(int i=0;i<tableTop.length;i++){
			Cell celli = row1.createCell(i);
			celli.setCellValue(tableTop[i]);
			celli.setCellStyle(cellStyle);

		}

		int rowNumber = 2;
		for (ExcelBean excelBean : list) {
			HSSFRow row = sheet.createRow(rowNumber);
			row.setHeight((short) 600);

			Cell c0 = row.createCell(0);
			c0.setCellValue(rowNumber-1);
			c0.setCellStyle(cellStyle2);

			Cell c1 = row.createCell(1);
			c1.setCellValue(excelBean.getCell1());
			c1.setCellStyle(cellStyle2);

			Cell c2 = row.createCell(2);
			c2.setCellValue(excelBean.getCell2());
			c2.setCellStyle(cellStyle2);

			Cell c3 = row.createCell(3);
			c3.setCellValue(excelBean.getCell3());
			c3.setCellStyle(cellStyle2);

			Cell c4 = row.createCell(4);
			c4.setCellValue(excelBean.getCell4());
			c4.setCellStyle(cellStyle2);

			if(tableTop.length > 5){
				Cell c5 = row.createCell(5);
				c5.setCellValue(excelBean.getCell5());
				c5.setCellStyle(cellStyle2);
				
				Cell c6 = row.createCell(6);
				c6.setCellValue(excelBean.getCell6());
				c6.setCellStyle(cellStyle2);
				
				if(tableTop.length > 7){
					Cell c7 = row.createCell(7);
					c7.setCellValue(excelBean.getCell7());
					c7.setCellStyle(cellStyle2);
				}
				
			}
			
			rowNumber++;
		}
		workbook.write(outputStream);
		outputStream.flush();
		outputStream.close();
	}
}
