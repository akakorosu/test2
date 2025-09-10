package com.bonc.dpi.dao.entity;

public class PositionRule extends OperationFlow {
	
	private String id;// 序号
	private String url;// 地址
	private String host;// 域名
	private String matchType;// 匹配类型
	private String longitudeRule;// 经度截取规则
	private String getIndexLong;// 经度截取序号
	private String latitudeRule;// 纬度截取规则
	private String getIndexLati;// 纬度截取序号
	private String systemRule;// 系统截取规则
	private String getIndexSys;// 系统截取序号
	private String system;// 定位系统
	private String groupType;// 应用分组编码
	private String author;// 作者
	private String opTime;// 操作时间
	private String isValid;// 是否有效
	private String num;

	// 展示用

	private String groupName;// 应用分组名称
	private String startTime;
	private String endTime;
	private String hostParam;
	private String urlParam;
	private String groupNameParam;
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
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getMatchType() {
		return matchType;
	}
	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}
	public String getLongitudeRule() {
		return longitudeRule;
	}
	public void setLongitudeRule(String longitudeRule) {
		this.longitudeRule = longitudeRule;
	}
	public String getGetIndexLong() {
		return getIndexLong;
	}
	public void setGetIndexLong(String getIndexLong) {
		this.getIndexLong = getIndexLong;
	}
	public String getLatitudeRule() {
		return latitudeRule;
	}
	public void setLatitudeRule(String latitudeRule) {
		this.latitudeRule = latitudeRule;
	}
	public String getGetIndexLati() {
		return getIndexLati;
	}
	public void setGetIndexLati(String getIndexLati) {
		this.getIndexLati = getIndexLati;
	}
	public String getSystemRule() {
		return systemRule;
	}
	public void setSystemRule(String systemRule) {
		this.systemRule = systemRule;
	}
	public String getGetIndexSys() {
		return getIndexSys;
	}
	public void setGetIndexSys(String getIndexSys) {
		this.getIndexSys = getIndexSys;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
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
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	public String getHostParam() {
		return hostParam;
	}
	public void setHostParam(String hostParam) {
		this.hostParam = hostParam;
	}
	public String getUrlParam() {
		return urlParam;
	}
	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}
	public String getGroupNameParam() {
		return groupNameParam;
	}
	public void setGroupNameParam(String groupNameParam) {
		this.groupNameParam = groupNameParam;
	}
	
	
	

}
