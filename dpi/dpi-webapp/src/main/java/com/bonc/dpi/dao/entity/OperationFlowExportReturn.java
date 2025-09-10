package com.bonc.dpi.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class OperationFlowExportReturn {

	private List<? extends OperationFlow> list = new ArrayList<OperationFlow>();

	private String excelName;
	
	private String txtName;

	private String newTxtName;

	public List<? extends OperationFlow> getList() {
		return list;
	}

	public void setList(List<? extends OperationFlow> list) {
		this.list = list;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public String getTxtName() {
		return txtName;
	}

	public void setTxtName(String txtName) {
		this.txtName = txtName;
	}

	public String getNewTxtName() {
		return newTxtName;
	}

	public void setNewTxtName(String newTxtName) {
		this.newTxtName = newTxtName;
	}
}
