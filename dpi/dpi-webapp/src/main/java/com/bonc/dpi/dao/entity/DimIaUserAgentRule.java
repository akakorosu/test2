package com.bonc.dpi.dao.entity;

/**
 * 
 * @author dragon
 *
 */
public class DimIaUserAgentRule extends OperationFlow {
	private String id = null;
	private String prodId = null;
	private String uaKeyRule = null;
	private String getIndex = null;
	private String uaExample = null;
	private String comments = null;
	private String author = null;
	private String opTime = null;

	private String prodName = null;
	private String ua = null;
	private String uaKey = null;
	private String dateId = null;
	private String startTime = null;
	private String endTime = null;
	private String uaKeyRuleParam = null;
	private String prodIdParam;

	public String getUaKeyRuleParam() {
		return uaKeyRuleParam;
	}

	public void setUaKeyRuleParam(String uaKeyRuleParam) {
		this.uaKeyRuleParam = uaKeyRuleParam;
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

	public String getUaKey() {
		return uaKey;
	}

	public void setUaKey(String uaKey) {
		this.uaKey = uaKey;
	}

	public String getDateId() {
		return dateId;
	}

	public void setDateId(String dateId) {
		this.dateId = dateId;
	}

	public String getUaKeyRule() {
		return uaKeyRule;
	}

	public void setUaKeyRule(String uaKeyRule) {
		this.uaKeyRule = uaKeyRule;
	}

	public String getGetIndex() {
		return getIndex;
	}

	public void setGetIndex(String getIndex) {
		this.getIndex = getIndex;
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

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdIdParam() {
		return prodIdParam;
	}

	public void setProdIdParam(String prodIdParam) {
		this.prodIdParam = prodIdParam;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	@Override
	public String toString() {
		return "DimIaUserAgentRule [id=" + id + ", prodId=" + prodId + ", uaKeyRule=" + uaKeyRule + ", getIndex="
				+ getIndex + ", uaExample=" + uaExample + ", comments=" + comments + ", author=" + author + ", opTime="
				+ opTime + ", prodName=" + prodName + ", ua=" + ua + ", uaKey=" + uaKey + ", dateId=" + dateId
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", uaKeyRuleParam=" + uaKeyRuleParam
				+ ", prodIdParam=" + prodIdParam + "]";
	}

}