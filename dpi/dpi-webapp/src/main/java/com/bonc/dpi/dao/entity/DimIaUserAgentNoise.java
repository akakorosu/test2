package com.bonc.dpi.dao.entity;
/**
 * 
 * @author dragon
 *
 */
public class DimIaUserAgentNoise  extends OperationFlow{
	private String id = null;
	private String noiseKey = null;
    private String author = null;
	private String opTime = null;

	private String uaKey = null;
	private String ua = null;
	private String dateId = null;
	private String startTime = null;
	private String endTime = null;
	
	
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
	public String getUaKey() {
		return uaKey;
	}
	public void setUaKey(String uaKey) {
		this.uaKey = uaKey;
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
	public String getNoiseKey() {
		return noiseKey;
	}
	public void setNoiseKey(String noiseKey) {
		this.noiseKey = noiseKey;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "DimIaUserAgentNoise [noiseKey=" + noiseKey + "]";		
	}

	
}
