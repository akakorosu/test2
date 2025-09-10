package com.bonc.dpi.dao.entity;

/**
 * 知识库监控
 * 数据识别情况总览
 * dm_d_identified_pandect
 * @author BONC-XUXL
 *
 */
public class DIdentifiedPandect {
	
	private String dateId;//date_Id,日期
	
	private Long yyjlSblFz;//应用记录数识别数---分子
	private Long yyjlSblFm;//应用记录数识别数---分母
	private Long yyjlSblNoipFm;//应用记录数识别数---分母---不含ip
//	private Long yyjlIpSblYes;//ip识别数---含ip
//	private Long yyjlIpSblNo;//ip识别数---不含ip
	
	private Long yyllSblFz;//应用流量识别数---分子
	private Long yyllSblFm;//应用流量识别数---分母
	private Long yyllSblNoipFm;//应用流量识别数---分母---不含ip
//	private Long yyllIpSblYes;//ip识别数---含ip
//	private Long yyllIpSblNo;//ip识别数---不含ip
	
	public String getDateId() {
		return dateId;
	}
	public void setDateId(String dateId) {
		this.dateId = dateId;
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
	public Long getYyjlSblNoipFm() {
		return yyjlSblNoipFm;
	}
	public void setYyjlSblNoipFm(Long yyjlSblNoipFm) {
		this.yyjlSblNoipFm = yyjlSblNoipFm;
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
	public Long getYyllSblNoipFm() {
		return yyllSblNoipFm;
	}
	public void setYyllSblNoipFm(Long yyllSblNoipFm) {
		this.yyllSblNoipFm = yyllSblNoipFm;
	}

}
