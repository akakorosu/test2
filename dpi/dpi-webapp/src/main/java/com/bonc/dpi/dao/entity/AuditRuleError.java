package com.bonc.dpi.dao.entity;

public class AuditRuleError extends OperationFlow{
	
	private String prodId;
	private String prodName;
	private String errorCode;
	private String monthId;
	private String tableName;
	private Integer rowNums;
	private String errorDesc;
	private String checkObject;
	private String checkColumn;
	
	public String getCheckObject() {
		return checkObject;
	}
	public void setCheckObject(String checkObject) {
		this.checkObject = checkObject;
	}
	public String getCheckColumn() {
		return checkColumn;
	}
	public void setCheckColumn(String checkColumn) {
		this.checkColumn = checkColumn;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	
	public Integer getRowNums() {
		return rowNums;
	}
	public void setRowNums(Integer rowNums) {
		this.rowNums = rowNums;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMonthId() {
		return monthId;
	}
	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}
	
	
}
