package com.bonc.dpi.dao.entity;

/**
 * 标签描述及发布状态
 * dw_sig_user_ev_label_desc_mm
 * @author BONC-XUXL
 *
 */
public class ProductFbDescInfo extends ProductInfo {
	private String queryLabelLvl;
	private String queryLabelName;
	private String queryProdName;
	
	private String dateId;
	private String cityId;
	private String labelDesc;
	private String fbFlag;
	private String userTotal;
	private String flow;
	private String joinLabelName;
	
	private String sord;
	private String sidx;
	
	public String getJoinLabelName() {
		return joinLabelName;
	}
	public void setJoinLabelName(String joinLabelName) {
		this.joinLabelName = joinLabelName;
	}
	public String getQueryLabelLvl() {
		return queryLabelLvl;
	}
	public void setQueryLabelLvl(String queryLabelLvl) {
		this.queryLabelLvl = queryLabelLvl;
	}
	public String getQueryLabelName() {
		return queryLabelName;
	}
	public void setQueryLabelName(String queryLabelName) {
		this.queryLabelName = queryLabelName;
	}
	public String getQueryProdName() {
		return queryProdName;
	}
	public void setQueryProdName(String queryProdName) {
		this.queryProdName = queryProdName;
	}
	public String getDateId() {
		return dateId;
	}
	public void setDateId(String dateId) {
		this.dateId = dateId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getLabelDesc() {
		return labelDesc;
	}
	public void setLabelDesc(String labelDesc) {
		this.labelDesc = labelDesc;
	}
	public String getFbFlag() {
		return fbFlag;
	}
	public void setFbFlag(String fbFlag) {
		this.fbFlag = fbFlag;
	}
	public String getUserTotal() {
		return userTotal;
	}
	public void setUserTotal(String userTotal) {
		this.userTotal = userTotal;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	
}
