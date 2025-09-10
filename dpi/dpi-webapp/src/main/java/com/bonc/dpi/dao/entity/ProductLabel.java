package com.bonc.dpi.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class ProductLabel extends OperationFlow{
	private String id;
	private String lableType;
	private String labelName;
	private String labelCode1;
	private String labelName1;
	private String labelCode2;
	private String labelName2;
	private String labelCode;
	private String joinLabelName;
	private String author;
	private String opTime;	
	private String startTime;
	private String endTime;    
	private String labelNameLike;
	private List<ProductLabel> children = new ArrayList<ProductLabel>();
	private String labelName1Param;
	private String labelName2Param;
	
	public String getLabelName1Param() {
		return labelName1Param;
	}
	public void setLabelName1Param(String labelName1Param) {
		this.labelName1Param = labelName1Param;
	}
	public String getLabelName2Param() {
		return labelName2Param;
	}
	public void setLabelName2Param(String labelName2Param) {
		this.labelName2Param = labelName2Param;
	}
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
	public String getJoinLabelName() {
		return joinLabelName;
	}
	public void setJoinLabelName(String joinLabelName) {
		this.joinLabelName = joinLabelName;
	}
	public String getLabelCode() {
		return labelCode;
	}
	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLableType() {
		return lableType;
	}
	public void setLableType(String lableType) {
		this.lableType = lableType;
	}
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	public String getLabelCode1() {
		return labelCode1;
	}
	public void setLabelCode1(String labelCode1) {
		this.labelCode1 = labelCode1;
	}
	public String getLabelName1() {
		return labelName1;
	}
	public void setLabelName1(String labelName1) {
		this.labelName1 = labelName1;
	}
	public String getLabelCode2() {
		return labelCode2;
	}
	public void setLabelCode2(String labelCode2) {
		this.labelCode2 = labelCode2;
	}
	public String getLabelName2() {
		return labelName2;
	}
	public void setLabelName2(String labelName2) {
		this.labelName2 = labelName2;
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
	public List<ProductLabel> getChildren() {
		return children;
	}
	public void setChildren(List<ProductLabel> children) {
		this.children = children;
	}
	public String getLabelNameLike() {
		return labelNameLike;
	}
	public void setLabelNameLike(String labelNameLike) {
		this.labelNameLike = labelNameLike;
	}
}
