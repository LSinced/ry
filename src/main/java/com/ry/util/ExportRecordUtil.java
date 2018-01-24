/**
 * 
 */
package com.ry.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.ry.model.Record;


/**
 * @author Administrator
 *
 */
public class ExportRecordUtil {
	public static void creatCheckUpSheet(HttpServletResponse response,Map<Record,Integer> records) throws IOException{
		//创建HSSFWorkbook对象(excel的文档对象)  
		SXSSFWorkbook wb = new SXSSFWorkbook(100);  
	    //建立新的sheet对象（excel的表单）
		SXSSFSheet sheet=null;
		sheet=wb.createSheet("订单结报");		
		//设置缺省列高
		sheet.setDefaultRowHeightInPoints(10);
		//设置缺省列宽 
		sheet.setDefaultColumnWidth(10);		
		//设置单元格样式
		CellStyle style=wb.createCellStyle();
		// 设置单元格的横向和纵向对齐方式，具体参数就不列了，参考HSSFCellStyle 
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直    
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平 
        //生成一个字体
        Font font=wb.createFont();
        font.setColor(HSSFColor.BLACK.index);//HSSFColor.VIOLET.index字体颜色
        font.setFontHeightInPoints((short)22);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体增粗
       //把字体应用到当前的样式
        style.setFont(font);
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个  
		SXSSFRow row1=sheet.createRow(0);  
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个  
		SXSSFCell cell=row1.createCell(0);  
	    //设置单元格内容  
		cell.setCellValue("订单统计");
		cell.setCellStyle(style);
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列  
		sheet.addMergedRegion(new CellRangeAddress(0,3,0,5));  
		//在sheet里创建第二行  
		SXSSFRow row2=sheet.createRow(4);      
	    //创建单元格并设置单元格内容  
	    row2.createCell(0).setCellValue("代理");
	    row2.createCell(1).setCellValue("通道");	    		
	    row2.createCell(2).setCellValue("流量");
	    row2.createCell(3).setCellValue("充值成功数");
	    row2.createCell(4).setCellValue("代理折后价");
	    row2.createCell(5).setCellValue("销售总额");
	    int rownum=5;
	    Set<Record> set=records.keySet();
	    List<Record> list=new ArrayList<Record>();
	    list.addAll(set);
	    Collections.sort(list,new Comparator<Record>() {
			@Override
			public int compare(Record o1, Record o2) {
				// TODO Auto-generated method stub
				return o1.getAgent().compareTo(o2.getAgent());
			}
		});
	    DecimalFormat df = new DecimalFormat("#.00");
	    for(Record record:list){
	    	//在sheet里创建第三行  
		    SXSSFRow rown=sheet.createRow(rownum);  
		    rown.createCell(0).setCellValue(record.getAgent());
		    rown.createCell(1).setCellValue(record.getTunnel());  
		    rown.createCell(2).setCellValue(record.getProduct());      
		    rown.createCell(3).setCellValue(records.get(record));
		    rown.createCell(4).setCellValue(df.format(record.getPrice()));
		    rown.createCell(5).setCellValue(df.format(records.get(record)*record.getPrice()));
		    rownum++;
	    }
	    sheet.trackAllColumnsForAutoSizing();
	    sheet.autoSizeColumn(0); 
		sheet.autoSizeColumn(1);
	    OutputStream output=response.getOutputStream();  
	    response.reset();  
	    response.setHeader("Content-Disposition", "attachment; filename=tjjb.xls");  
	    response.setContentType("application/octet-stream;charset=UTF-8");          
	    wb.write(output);  
	    output.close(); 
	}
}
