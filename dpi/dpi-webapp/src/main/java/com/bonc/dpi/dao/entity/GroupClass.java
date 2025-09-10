package com.bonc.dpi.dao.entity;

public class GroupClass extends OperationFlow{
	private String id;
	private String classCode;
	private String className;
	private String subClassCode;
	private String subClassName;
	private String prodid;
	private String author;
	private String opTime;
	
	private String prodName;
	private String flag;//1为正常数据  2为相同联合主键  不正常的数据
	private String startTime;
	private String endTime;
//	private String prodId;
	
//	public String getProdId() {
//		return prodId;
//	}
//	public void setProdId(String prodId) {
//		this.prodId = prodId;
//	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSubClassCode() {
		return subClassCode;
	}
	public void setSubClassCode(String subClassCode) {
		this.subClassCode = subClassCode;
	}
	public String getSubClassName() {
		return subClassName;
	}
	public void setSubClassName(String subClassName) {
		this.subClassName = subClassName;
	}
	
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	
	
}
