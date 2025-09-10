package com.bonc.dpi.dao.entity;

/**
 * @author dragon
 * 
 */
public class DimIaIpPortDynamic  extends OperationFlow{
	private String id = null;
	private String ipPort = null;
	private String ip = null;
	private String port = null;
	private String prodid = null;
	private String author = null;
	private String opTime = null;	
	private String prodName=null;
	private String startTime = null;	
	private String endTime=null;
	private String ipPortParam=null;
	
	public String getIpPortParam() {
		return ipPortParam;
	}

	public void setIpPortParam(String ipPortParam) {
		this.ipPortParam = ipPortParam;
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

	public String getIpPort() {
		return ipPort;
	}

	public void setIpPort(String ipPort) {
		this.ipPort = ipPort;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
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

	@Override
	public String toString() {
		return "DimIaIpPort[id=" + id + ", ipPort=" + ipPort + ", ip=" + ip + ", port=" + port + ", prodId=" + prodid + ", author=" + author + ", opTime=" + opTime + "]";		
	}

	
}
