package com.bonc.dpi.dao.entity;

public class DomainRule extends OperationFlow{
	private String id;  //
	private String domainCode;  //domain_code域名编码
	private String prodId;  //prod_id产品ID
	private String author;  //操作人
	private String opTime;  //操作时间
	private String startTime;
	private String endTime;
	

	

	//**********************非库中字段************************
	private String domainCodeOld;  //重复校验用
	private String prodName; //产品名称
	
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
	public String getDomainCode() {
		return domainCode;
	}
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getDomainCodeOld() {
		return domainCodeOld;
	}
	public void setDomainCodeOld(String domainCodeOld) {
		this.domainCodeOld = domainCodeOld;
	}
	
	@Override
	public String toString() {
		return "DomainRule [domainCode=" + domainCode + ", prodId=" + prodId + ", author=" + author + ", opTime="
				+ opTime + ", prodName=" + prodName + "]";
	}
}
