package com.bonc.dpi.dao.entity;

public class UnidentifiedUrl extends OperationFlow{
	private String uuid;
	private String host;
	private String title;
	private String prodId;
	private String prodName;
	private String prodLabel;
	private String labelName;
	private String monthId;
	private String typeId;
	private String author;
	private String opTime;
	private String isValid;
	private String totalCount;
	private String auditStatus;
	private Integer rowNums;
	private String prodLabelCode;
	private String prodLabelName;
	private String userTotal;//人数
	private String userCount;//次数
	private String flow;
	private String clashLabel;
	private String isDiscern;
	private String isCheck;
	private String flowCount;//流量除以次数
	private String orgLabelCode;
	private String sidx;  //列名
	private String sord; //排序
	private String prodCatagoryName;
	private String prodCatagory;
	private String labelCode1;
	private String labelCode2;
	private String labelName1;
	private String labelName2;
	
	public String getLabelCode1() {
		return labelCode1;
	}
	public void setLabelCode1(String labelCode1) {
		this.labelCode1 = labelCode1;
	}
	public String getLabelCode2() {
		return labelCode2;
	}
	public void setLabelCode2(String labelCode2) {
		this.labelCode2 = labelCode2;
	}
	public String getLabelName1() {
		return labelName1;
	}
	public void setLabelName1(String labelName1) {
		this.labelName1 = labelName1;
	}
	public String getLabelName2() {
		return labelName2;
	}
	public void setLabelName2(String labelName2) {
		this.labelName2 = labelName2;
	}
	public String getProdCatagoryName() {
		return prodCatagoryName;
	}
	public void setProdCatagoryName(String prodCatagoryName) {
		this.prodCatagoryName = prodCatagoryName;
	}
	public String getProdCatagory() {
		return prodCatagory;
	}
	public void setProdCatagory(String prodCatagory) {
		this.prodCatagory = prodCatagory;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getOrgLabelCode() {
		return orgLabelCode;
	}
	public void setOrgLabelCode(String orgLabelCode) {
		this.orgLabelCode = orgLabelCode;
	}
	public String getIsDiscern() {
		return isDiscern;
	}
	public void setIsDiscern(String isDiscern) {
		this.isDiscern = isDiscern;
	}
	public String getFlowCount() {
		return flowCount;
	}
	public void setFlowCount(String flowCount) {
		this.flowCount = flowCount;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getProdLabelCode() {
		return prodLabelCode;
	}
	public void setProdLabelCode(String prodLabelCode) {
		this.prodLabelCode = prodLabelCode;
	}
	public String getProdLabelName() {
		return prodLabelName;
	}
	public void setProdLabelName(String prodLabelName) {
		this.prodLabelName = prodLabelName;
	}
	public String getUserTotal() {
		return userTotal;
	}
	public void setUserTotal(String userTotal) {
		this.userTotal = userTotal;
	}
	public String getUserCount() {
		return userCount;
	}
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public String getClashLabel() {
		return clashLabel;
	}
	public void setClashLabel(String clashLabel) {
		this.clashLabel = clashLabel;
	}
	
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	public Integer getRowNums() {
		return rowNums;
	}
	public void setRowNums(Integer rowNums) {
		this.rowNums = rowNums;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
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
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getMonthId() {
		return monthId;
	}
	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getProdLabel() {
		return prodLabel;
	}
	public void setProdLabel(String prodLabel) {
		this.prodLabel = prodLabel;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	@Override
	public String toString() {
		return "UnidentifiedUrl [ host=" + host + ", title=" + title + ", prodId=" + prodId
				+ ", prodName=" + prodName + ", prodLabel=" + prodLabel + ", labelName=" + labelName + ", monthId="
				+ monthId + ", typeId=" + typeId + ", author=" + author + ", opTime=" + opTime + ", isValid=" + isValid
				+ ", totalCount=" + totalCount + "]";
	}
	
}
