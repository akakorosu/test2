package com.bonc.dpi.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bonc.dpi.service.i.FileImportServiceI;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileImportService implements FileImportServiceI {
	
	private XSSFWorkbook xssfWorkbook;
	private HSSFWorkbook hssfWorkbook;

	public List<Map<String,String>> readExcel(MultipartFile file,String name){
        String extString = name.substring(name.lastIndexOf("."));
        try {
            if(".xls".equals(extString)){
                return readXls(file);
            }else if(".xlsx".equals(extString)){
                return readXlsx(file);
            }else{
                return null;
            }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public List<Map<String,String>> readXlsx(MultipartFile file) throws IOException {
		 InputStream is = file.getInputStream();
		 List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		 xssfWorkbook = new XSSFWorkbook(is);
		 for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			 XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			 if (xssfSheet == null) {
			     continue;
			 }
			 XSSFRow xssfRowHead = xssfSheet.getRow(0);
			 int coloumNum=xssfSheet.getRow(0).getPhysicalNumberOfCells();
			 for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				  XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				  Map<String,String> map = new LinkedHashMap<String,String>();
			      if (xssfRow != null) {
			    	  for(int i = 0;i < coloumNum;i++){
			    		  map.put(xssfRowHead.getCell(i).toString(), getValue(xssfRow.getCell(i)));
			    	  }
		          }
			      list.add(map);
		      }
	      }
		 return list;
      }
	
	public List<Map<String,String>> readXls(MultipartFile file) throws IOException {
		 InputStream is = file.getInputStream();
		 List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		 hssfWorkbook = new HSSFWorkbook(is);
		 for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			 HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			 if (hssfSheet == null) {
			     continue;
			 }
			 HSSFRow hssfRowHead = hssfSheet.getRow(0);
			 int coloumNum=hssfSheet.getRow(0).getPhysicalNumberOfCells();
			 for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				  HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				  Map<String,String> map = new LinkedHashMap<String,String>();
			      if (hssfRow != null) {
			    	  for(int i = 0;i < coloumNum;i++){
			    		  map.put(hssfRowHead.getCell(i).toString(), getValue(hssfRow.getCell(i)));
			    	  }
		          }
			      list.add(map);
		      }
	      }
		 return list;
     }
	
	 @SuppressWarnings({ "static-access", "deprecation" })
     private String getValue(XSSFCell xssfCell) {
         if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
             return String.valueOf(xssfCell.getBooleanCellValue());
         } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
             return String.valueOf(xssfCell.getNumericCellValue());
         } else {
             return String.valueOf(xssfCell.getStringCellValue());
         }
     }
	 
	  @SuppressWarnings({ "static-access", "deprecation" })
      private String getValue(HSSFCell hssfCell) {
          if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
              return String.valueOf(hssfCell.getBooleanCellValue());
          } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
              return String.valueOf(hssfCell.getNumericCellValue());
          } else {
              return String.valueOf(hssfCell.getStringCellValue());
          }
      }
	
}
