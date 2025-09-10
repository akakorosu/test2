package com.bonc.dpi.dao.entity;

public class ApplicationRate extends OperationFlow{
	private String dateId;//date_Id,日期
	private String hourId;//hour_Id,小时
	private Long yyjlSblFz;//应用记录数识别数---分子
	private Long yyjlSblFm;//应用记录数识别数---分母
	private String yyjlSblPer;//应用记录数识别率

	private Long yyllSblFz;//应用流量识别数---分子
	private Long yyllSblFm;//应用流量识别数---分母
	private String yyllSblPer;//应用流量识别率


	private Long yyjlSblNoipFm;//应用记录数识别数---分母---不含ip
	private Long yyllSblNoipFm;//应用流量识别数---分母---不含ip

	private String type;



	public String getYyllSblPer() {
		return yyllSblPer;
	}

	public void setYyllSblPer(String yyllSblPer) {
		this.yyllSblPer = yyllSblPer;
	}

	public String getYyjlSblPer() {
		return yyjlSblPer;
	}

	public void setYyjlSblPer(String yyjlSblPer) {
		this.yyjlSblPer = yyjlSblPer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getYyjlSblFz() {
		return yyjlSblFz;
	}
	public void setYyjlSblFz(Long yyjlSblFz) {
		this.yyjlSblFz = yyjlSblFz;
	}
	public Long getYyjlSblFm() {
		return yyjlSblFm;
	}
	public void setYyjlSblFm(Long yyjlSblFm) {
		this.yyjlSblFm = yyjlSblFm;
	}
	public Long getYyllSblFz() {
		return yyllSblFz;
	}
	public void setYyllSblFz(Long yyllSblFz) {
		this.yyllSblFz = yyllSblFz;
	}
	public Long getYyllSblFm() {
		return yyllSblFm;
	}
	public void setYyllSblFm(Long yyllSblFm) {
		this.yyllSblFm = yyllSblFm;
	}
	public String getDateId() {
		return dateId;
	}
	public void setDateId(String dateId) {
		this.dateId = dateId;
	}
	public String getHourId() {
		return hourId;
	}
	public void setHourId(String hourId) {
		this.hourId = hourId;
	}
	public Long getYyjlSblNoipFm() {
		return yyjlSblNoipFm;
	}
	public void setYyjlSblNoipFm(Long yyjlSblNoipFm) {
		this.yyjlSblNoipFm = yyjlSblNoipFm;
	}
	public Long getYyllSblNoipFm() {
		return yyllSblNoipFm;
	}
	public void setYyllSblNoipFm(Long yyllSblNoipFm) {
		this.yyllSblNoipFm = yyllSblNoipFm;
	}
	
}
