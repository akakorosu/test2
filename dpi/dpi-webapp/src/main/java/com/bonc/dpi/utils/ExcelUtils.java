package com.bonc.dpi.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Workbook getWorkBook(InputStream is, String fileName) throws IOException {
        Workbook workbook = null;
        //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
        if(fileName.endsWith("xls")){
            //2003
            workbook = new HSSFWorkbook(is);
        }else if(fileName.endsWith("xlsx")){
            //2007 及2007以上
            workbook = new XSSFWorkbook(is);
        }
        return workbook;
    }
	
	public static byte[] getExcel(Workbook workbook, List<?> list, String[] nums) throws IOException, IllegalAccessException {
	    Sheet tempSheet = workbook.getSheetAt(0);
	    int currentLastRowIndex = 1;
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			Field[] fs = o.getClass().getDeclaredFields();
			int newRowIndex = currentLastRowIndex ++;
			XSSFRow newRow = (XSSFRow) tempSheet.createRow(newRowIndex);
			for(int j = 0; j < nums.length; j ++) {
				Field f = fs[Integer.valueOf(nums[j])];
				f.setAccessible(true);
				XSSFCell newNameCell = newRow.createCell(j, CellType.STRING);
				Object v = f.get(o);
				if(v == null) {
					newNameCell.setCellValue("");
				} else {
					newNameCell.setCellValue(String.valueOf(v));
				}
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
        byte[] content = os.toByteArray();
		return content;
	}

	public static List<List<String>> getListByRow(InputStream is, String fileName) throws IOException {
		Workbook workbook = ExcelUtils.getWorkBook(is, fileName);
		//创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<List<String>> list = new ArrayList<List<String>>();
        if(workbook != null){
//            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
        	//只看第一个sheet页面
        	for(int sheetNum = 0;sheetNum < 1;sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue; 
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                
                //****取得模板中列数start
                Row row0 = sheet.getRow(0);//第一行
                int firstRow_columnNumber = row0.getLastCellNum();//获得第一行的列数
                //****取得模板中列数end
                
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
//                    int firstCellNum = row.getFirstCellNum();//默认是从有值开始
                    int firstCellNum = 0;
                    if(firstCellNum != 0) {
                    	continue;
                    }
                    //获得当前行的列数
//                    int lastCellNum = row.getLastCellNum();
                    int lastCellNum = firstRow_columnNumber;
                    List<String> parameter = new ArrayList<String>();
                    //循环当前行
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        parameter.add(getCellValue(cell));
                    }
                    list.add(parameter);
                }
            }
        }
        return list;
	}
	
	@SuppressWarnings("deprecation")
	public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = stringDateProcess(cell);
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
	
	/**
     * 时间格式处理
     * @return
     * @author Liu Xin Nan
     * @data 2017年11月27日
     */
    public static String stringDateProcess(Cell cell){
        String result = new String();  
        if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式  
            SimpleDateFormat sdf = null;  
            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {  
                sdf = new SimpleDateFormat("HH:mm");  
            } else {// 日期  
                sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
            }  
            Date date = cell.getDateCellValue();  
            result = sdf.format(date);  
        } else if (cell.getCellStyle().getDataFormat() == 58) {  
            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)  
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
            double value = cell.getNumericCellValue();  
            Date date = org.apache.poi.ss.usermodel.DateUtil  
                    .getJavaDate(value);  
            result = sdf.format(date);  
        } else {  
            double value = cell.getNumericCellValue();  
            CellStyle style = cell.getCellStyle();  
            DecimalFormat format = new DecimalFormat();  
            String temp = style.getDataFormatString();  
            // 单元格设置成常规  
            if (temp.equals("General")) {  
                format.applyPattern("#.################################"); //只保留整数部分了  把经纬度都干掉了 
            }  
            result = format.format(value);  
            //result= value+"";
        }  
        
        return result;
    }
}
