package com.bonc.dpi.dao.entity;

public class RuleCheck {
	private String id;
	private String url;
	private String keyWord;
	private String numTatal;
	private String dateId;
	private String sidx;
	private String sord;
	
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public String getNumTatal() {
		return numTatal;
	}
	public void setNumTatal(String numTatal) {
		this.numTatal = numTatal;
	}
	public String getDateId() {
		return dateId;
	}
	public void setDateId(String dateId) {
		this.dateId = dateId;
	}
	
}
