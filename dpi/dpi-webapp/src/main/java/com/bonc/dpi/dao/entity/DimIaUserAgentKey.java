package com.bonc.dpi.dao.entity;

import com.bonc.dpi.dao.entity.OperationFlow;

/**
 * @author dragon
 * 
 */
public class DimIaUserAgentKey extends OperationFlow {
	private String id;
	private String uaKey;
	private String prodId;
	private String uaExample;
	private String comments;
	private String author;
	private String opTime;

	private String prodName;
	private String prodIdParam;
	private String ua;
	private String dateId;
	private String startTime;
	private String endTime;
	private String uaKeyParam;

	public String getUaKeyParam() {
		return uaKeyParam;
	}

	public void setUaKeyParam(String uaKeyParam) {
		this.uaKeyParam = uaKeyParam;
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

	public String getUa() {
		return ua;
	}

	public void setUa(String ua) {
		this.ua = ua;
	}

	public String getDateId() {
		return dateId;
	}

	public void setDateId(String dateId) {
		this.dateId = dateId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getUaKey() {
		return uaKey;
	}

	public void setUaKey(String uaKey) {
		this.uaKey = uaKey;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdIdParam() {
		return prodIdParam;
	}

	public void setProdIdParam(String prodIdParam) {
		this.prodIdParam = prodIdParam;
	}

	public String getUaExample() {
		return uaExample;
	}

	public void setUaExample(String uaExample) {
		this.uaExample = uaExample;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
		return "DimIaUserAgentKey [id=" + id + ", uaKey=" + uaKey + ", prodId=" + prodId + ", uaExample=" + uaExample
				+ ", comments=" + comments + ", author=" + author + ", opTime=" + opTime + ", prodName=" + prodName
				+ ", prodIdParam=" + prodIdParam + ", ua=" + ua + ", dateId=" + dateId + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", uaKeyParam=" + uaKeyParam + "]";
	}

}
