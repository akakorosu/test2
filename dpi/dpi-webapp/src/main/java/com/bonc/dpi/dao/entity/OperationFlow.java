package com.bonc.dpi.dao.entity;

import java.util.List;

public class OperationFlow {

	private List<String> list;
	
	private String rowNum;
	
	private String errorInfo;
	
	private String exportTypeProdExcel;
	
	

	public String getExportTypeProdExcel() {
		return exportTypeProdExcel;
	}

	public void setExportTypeProdExcel(String exportTypeProdExcel) {
		this.exportTypeProdExcel = exportTypeProdExcel;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}
}
