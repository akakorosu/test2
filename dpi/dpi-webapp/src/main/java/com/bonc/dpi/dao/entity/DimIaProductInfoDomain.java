package com.bonc.dpi.dao.entity;


/**
 * 产品表---域名表，组合字段
 * DIM_IA_PRODUCT_INFO，DIM_IA_DOMAIN
 * @author BONC-XUXL
 *
 */
public class DimIaProductInfoDomain extends OperationFlow{
	
	private String domainCode; //DOMAIN_CODE,域名编码
	private String prodName; //PROD_NAME,产品名称
	private String prodLabelCode; //PROD_LABEL_CODE,标签id
	private String opTime; //操作时间
	private String author; //操作人
	private String id;//id
	private String prodNameOld; //域名表中产品名称
	private String prodCatagory;  //产品类别
	private String prodDesc;  //产品描述IOS_PROD_NAME
	private String iosProdName; //苹果产品名称IS_BOCE
	private String isBoce;  //是否拨测    0 没有拨测  1 拨测  BOCE_DATE
	private String boceDate;  //拨测时间
	
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getIosProdName() {
		return iosProdName;
	}
	public void setIosProdName(String iosProdName) {
		this.iosProdName = iosProdName;
	}
	public String getIsBoce() {
		return isBoce;
	}
	public void setIsBoce(String isBoce) {
		this.isBoce = isBoce;
	}
	public String getBoceDate() {
		return boceDate;
	}
	public void setBoceDate(String boceDate) {
		this.boceDate = boceDate;
	}
	public String getProdCatagory() {
		return prodCatagory;
	}
	public void setProdCatagory(String prodCatagory) {
		this.prodCatagory = prodCatagory;
	}
	public String getDomainCode() {
		return domainCode;
	}
	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdLabelCode() {
		return prodLabelCode;
	}
	public void setProdLabelCode(String prodLabelCode) {
		this.prodLabelCode = prodLabelCode;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProdNameOld() {
		return prodNameOld;
	}
	public void setProdNameOld(String prodNameOld) {
		this.prodNameOld = prodNameOld;
	}
}
