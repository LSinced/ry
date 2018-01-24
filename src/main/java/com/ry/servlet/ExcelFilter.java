/**
 * 
 */
package com.ry.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ry.model.Record;
import com.ry.util.ExportRecordUtil;

/**
 * @author wanglei
 * 下午3:05:41
 */
public class ExcelFilter extends HttpServlet {
	private Logger logger=LoggerFactory.getLogger(ExcelFilter.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {				
		String zhi=null;
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List  items = upload.parseRequest(request);
            InputStream is = null;
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
                    is = item.getInputStream();
                }else{
                    if(item.getFieldName().equals("qwe")){
                    zhi=item.getString("gb2312");
                    System.out.println(zhi);
                  }
                }
            }
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            int rows=sheet.getLastRowNum();
            List<Record> list=new ArrayList<Record>();
            for (int i = 2; i <=rows; i++) {
            	Row row=sheet.getRow(i);
            	if (row==null) {
					break;
				}
            	Cell zhuantai=row.getCell(10);
            	String status=zhuantai.getStringCellValue();
            	if (status.equals("充值成功")) {
            		Cell daili=row.getCell(3);
                	Cell tongdao=row.getCell(5);
                	Cell liuliang=row.getCell(8);           	
                	Cell jiage=row.getCell(19);
                	String agent=daili.getStringCellValue();
                    String tunnel=tongdao.getStringCellValue();
                    String product=liuliang.getStringCellValue();               
                    String price=jiage.getStringCellValue();
                	Record r=new Record();
                	r.setAgent(agent);
                	r.setTunnel(tunnel);
                	r.setProduct(product);
                	r.setPrice(Float.parseFloat(price));
                	list.add(r);
				}            	                               
			}
            is.close();
            Collections.sort(list,new Comparator<Record>() {
				@Override
				public int compare(Record o1, Record o2) {
					return o1.getAgent().compareTo(o2.getAgent());					
				}
			});
            Map<Record, Integer> map=new HashMap<Record, Integer>();
            for (Record record : list) {
				map.put(record, (map.get(record)==null?0:map.get(record))+1);
			} 
            ExportRecordUtil.creatCheckUpSheet(response, map);
        } catch (FileUploadException e) {
        	StringWriter sw=new StringWriter();
        	e.printStackTrace(new PrintWriter(sw, true));
        	logger.error("文件上传失败！"+sw.toString());          
        } catch (Exception e) {
        	StringWriter sw=new StringWriter();
        	e.printStackTrace(new PrintWriter(sw, true));
        	logger.error("文件格式不符"+sw.toString());
        }
	}
}
