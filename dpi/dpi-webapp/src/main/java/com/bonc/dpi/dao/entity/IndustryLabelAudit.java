package com.bonc.dpi.dao.entity;

import java.math.BigInteger;

public class IndustryLabelAudit extends OperationFlow{
	//
	private String prodName;
	private String labelName1;
	private String labelCode1;
	private String labelCode2;
	private String labelId1;
	private String labelName2;
	private String labelId2;
	private BigInteger userTotal;
	private BigInteger userCount;
	private String flow;
	private String monthId;
	private Integer rowNums;
	private String prodId;
	private String prodType;
	private String prodTypeName;
	private Integer prodRnk;
	private String isCheck;
	private String labelName;
	private String sidx;
	private String sord;
	
	public BigInteger getUserTotal() {
		return userTotal;
	}
	public void setUserTotal(BigInteger userTotal) {
		this.userTotal = userTotal;
	}
	public BigInteger getUserCount() {
		return userCount;
	}
	public void setUserCount(BigInteger userCount) {
		this.userCount = userCount;
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
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getLabelName1() {
		return labelName1;
	}
	public void setLabelName1(String labelName1) {
		this.labelName1 = labelName1;
	}
	public String getLabelId1() {
		return labelId1;
	}
	public void setLabelId1(String labelId1) {
		this.labelId1 = labelId1;
	}
	public String getLabelName2() {
		return labelName2;
	}
	public void setLabelName2(String labelName2) {
		this.labelName2 = labelName2;
	}
	public String getLabelId2() {
		return labelId2;
	}
	public void setLabelId2(String labelId2) {
		this.labelId2 = labelId2;
	}
	
	
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public String getMonthId() {
		return monthId;
	}
	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}
	public Integer getRowNums() {
		return rowNums;
	}
	public void setRowNums(Integer rowNums) {
		this.rowNums = rowNums;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdType() {
		return prodType;
	}
	public void setProdType(String prodType) {
		this.prodType = prodType;
	}
	public String getProdTypeName() {
		return prodTypeName;
	}
	public void setProdTypeName(String prodTypeName) {
		this.prodTypeName = prodTypeName;
	}
	public Integer getProdRnk() {
		return prodRnk;
	}
	public void setProdRnk(Integer prodRnk) {
		this.prodRnk = prodRnk;
	}
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
	
	
}
