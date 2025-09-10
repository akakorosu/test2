package com.bonc.dpi.dao.entity;

public class ContentLabel extends OperationFlow{
	private String id;
	private String contentLabelCode;
	private String contentLabelName;
	private String contentLabelLevel;
	private String contentLabelCode1;
	private String contentLabelName1;
	private String contentLabelCode2;
	private String contentLabelName2;
	private String contentLabelCode3;
	private String contentLabelName3;
	private String contentLabelCode4;
	private String contentLabelName4;
	private String contentLabelCode5;
	private String contentLabelName5;
	private String contentLabelCode6;
	private String contentLabelName6;
	private String author;
	private String opTime;
    private String contentLabelNameRelevance;
	private String label;
	private String startTime;
	private String endTime;
	private String contentLabelName1Param;

    public String getContentLabelNameRelevance() {
        return contentLabelNameRelevance;
    }

    public void setContentLabelNameRelevance(String contentLabelNameRelevance) {
        this.contentLabelNameRelevance = contentLabelNameRelevance;
    }

    public String getContentLabelName1Param() {
		return contentLabelName1Param;
	}
	public void setContentLabelName1Param(String contentLabelName1Param) {
		this.contentLabelName1Param = contentLabelName1Param;
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getContentLabelLevel() {
		return contentLabelLevel;
	}
	public void setContentLabelLevel(String contentLabelLevel) {
		this.contentLabelLevel = contentLabelLevel;
	}
	public String getContentLabelCode() {
		return contentLabelCode;
	}
	public void setContentLabelCode(String contentLabelCode) {
		this.contentLabelCode = contentLabelCode;
	}
	public String getContentLabelName() {
		return contentLabelName;
	}
	public void setContentLabelName(String contentLabelName) {
		this.contentLabelName = contentLabelName;
	}
	public String getContentLabelCode1() {
		return contentLabelCode1;
	}
	public void setContentLabelCode1(String contentLabelCode1) {
		this.contentLabelCode1 = contentLabelCode1;
	}
	
	public String getContentLabelCode2() {
		return contentLabelCode2;
	}
	public void setContentLabelCode2(String contentLabelCode2) {
		this.contentLabelCode2 = contentLabelCode2;
	}
	public String getContentLabelCode3() {
		return contentLabelCode3;
	}
	public void setContentLabelCode3(String contentLabelCode3) {
		this.contentLabelCode3 = contentLabelCode3;
	}
	public String getContentLabelCode4() {
		return contentLabelCode4;
	}
	public void setContentLabelCode4(String contentLabelCode4) {
		this.contentLabelCode4 = contentLabelCode4;
	}
	public String getContentLabelCode5() {
		return contentLabelCode5;
	}
	public void setContentLabelCode5(String contentLabelCode5) {
		this.contentLabelCode5 = contentLabelCode5;
	}
	public String getContentLabelCode6() {
		return contentLabelCode6;
	}
	public void setContentLabelCode6(String contentLabelCode6) {
		this.contentLabelCode6 = contentLabelCode6;
	}
	public String getContentLabelName1() {
		return contentLabelName1;
	}
	public void setContentLabelName1(String contentLabelName1) {
		this.contentLabelName1 = contentLabelName1;
	}
	public String getContentLabelName2() {
		return contentLabelName2;
	}
	public void setContentLabelName2(String contentLabelName2) {
		this.contentLabelName2 = contentLabelName2;
	}
	public String getContentLabelName3() {
		return contentLabelName3;
	}
	public void setContentLabelName3(String contentLabelName3) {
		this.contentLabelName3 = contentLabelName3;
	}
	public String getContentLabelName4() {
		return contentLabelName4;
	}
	public void setContentLabelName4(String contentLabelName4) {
		this.contentLabelName4 = contentLabelName4;
	}
	public String getContentLabelName5() {
		return contentLabelName5;
	}
	public void setContentLabelName5(String contentLabelName5) {
		this.contentLabelName5 = contentLabelName5;
	}
	public String getContentLabelName6() {
		return contentLabelName6;
	}
	public void setContentLabelName6(String contentLabelName6) {
		this.contentLabelName6 = contentLabelName6;
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


	@Override
	public String toString() {
		return "ContentLabel [contentLabelLevel=" + contentLabelLevel + ", contentLabelCode=" + contentLabelCode
				+ ", contentLabelName=" + contentLabelName + ", contentLabelCode1=" + contentLabelCode1
				+ ", contentLabelCode2=" + contentLabelCode2 + ", contentLabelCode3=" + contentLabelCode3
				+ ", contentLabelCode4=" + contentLabelCode4 + ", contentLabelCode5=" + contentLabelCode5
				+ ", contentLabelCode6=" + contentLabelCode6 + ", contentLabelName1=" + contentLabelName1
				+ ", contentLabelName2=" + contentLabelName2 + ", contentLabelName3=" + contentLabelName3
				+ ", contentLabelName4=" + contentLabelName4 + ", contentLabelName5=" + contentLabelName5
				+ ", contentLabelName6=" + contentLabelName6 + ", author=" + author + ", opTime=" + opTime + ", id="
				+ id + ", label=" + label + "]";
	}
	
}
