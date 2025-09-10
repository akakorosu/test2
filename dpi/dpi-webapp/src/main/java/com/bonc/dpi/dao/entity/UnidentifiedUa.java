package com.bonc.dpi.dao.entity;

public class UnidentifiedUa extends OperationFlow{
	private String ua;
	private String uaKey;
	private String userTotal;
	private String userCount;
	private String flow;
	private String monthId;
	private String sidx;
	private String sord;
	private String isCheck;
	
	public String getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}
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
	public String getUserTotal() {
		return userTotal;
	}
	public void setUserTotal(String userTotal) {
		this.userTotal = userTotal;
	}
	public String getUserCount() {
		return userCount;
	}
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public String getMonthId() {
		return monthId;
	}
	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}
	@Override
	public String toString() {
		return "UnidentifiedUa [ua=" + ua + ", uaKey=" + uaKey + ", userTotal=" + userTotal + ", userCount=" + userCount
				+ ", flow=" + flow + ", monthId=" + monthId + "]";
	}
	
}
