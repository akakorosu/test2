package com.bonc.dpi.dao.entity;

public class OperationsCenter {
	private String monthId;
	private String flow;
	private String flowTotal;
	private String typeId;
	private String numberTotal;
	private String count;
	private String totalPercent;//总数占比
	private String flowPercent;//流量占比
	public String getMonthId() {
		return monthId;
	}
	public void setMonthId(String monthId) {
		this.monthId = monthId;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public String getFlowTotal() {
		return flowTotal;
	}
	public void setFlowTotal(String flowTotal) {
		this.flowTotal = flowTotal;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getNumberTotal() {
		return numberTotal;
	}
	public void setNumberTotal(String numberTotal) {
		this.numberTotal = numberTotal;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getTotalPercent() {
		return totalPercent;
	}
	public void setTotalPercent(String totalPercent) {
		this.totalPercent = totalPercent;
	}
	public String getFlowPercent() {
		return flowPercent;
	}
	public void setFlowPercent(String flowPercent) {
		this.flowPercent = flowPercent;
	}
	
}
