package com.bonc.dpi.dao.entity;

/**
 * 内容库识别情况
 * dm_d_content_info
 * @author BONC-XUXL
 *
 */
public class DmDContentInfo {
	
	private String dateId ;//month_id 帐期
	private String contentType ;//content_type 内容类型
	private String recongnition ;//recongnition 识别率
	private String activeProd ;//active_prod 活跃产品
	private String activeLabel ;//active_label 活跃标签
	private String activeContent ;//active_content 活跃内容
	private String addContent ;//add_content 新增内容
	private String useCnt ;//use_cnt 使用率
	private String errorCnt ;//error_cnt 错误率
	
	public String getDateId() {
		return dateId;
	}
	public void setDateId(String dateId) {
		this.dateId = dateId;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getRecongnition() {
		return recongnition;
	}
	public void setRecongnition(String recongnition) {
		this.recongnition = recongnition;
	}
	public String getActiveProd() {
		return activeProd;
	}
	public void setActiveProd(String activeProd) {
		this.activeProd = activeProd;
	}
	public String getActiveLabel() {
		return activeLabel;
	}
	public void setActiveLabel(String activeLabel) {
		this.activeLabel = activeLabel;
	}
	public String getActiveContent() {
		return activeContent;
	}
	public void setActiveContent(String activeContent) {
		this.activeContent = activeContent;
	}
	public String getAddContent() {
		return addContent;
	}
	public void setAddContent(String addContent) {
		this.addContent = addContent;
	}
	public String getUseCnt() {
		return useCnt;
	}
	public void setUseCnt(String useCnt) {
		this.useCnt = useCnt;
	}
	public String getErrorCnt() {
		return errorCnt;
	}
	public void setErrorCnt(String errorCnt) {
		this.errorCnt = errorCnt;
	}

}
