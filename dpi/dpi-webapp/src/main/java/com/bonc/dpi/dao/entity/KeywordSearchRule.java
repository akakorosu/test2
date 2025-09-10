package com.bonc.dpi.dao.entity;


/**
 * 关键字规则表
 * dim_ia_keyword_search_rule
 * @author BONC-XUXL
 *
 */
public class KeywordSearchRule extends OperationFlow {
	private String id;//序号
	private String host;//域名
	private String matchType;//匹配类型
	private String prodid;//产品ID
	private String ruleType;//规则匹配类型
	private String channelName;//动作目录名称
	private String parseRuleAndriod;//安卓截取规则
	private String getIndexAndriod;//获取安卓截取结果分组序号
	private String parseRuleIos;//苹果截取规则
	private String getIndexIos;//获取苹果截取结果分组序号
	private String groupType;//应用分组编码
	private String contentLabelCode;//标签ID
	private String attrId;//属性ID
	private String author;//作者
	private String opTime;//操作时间
	private String isValid;//是否有效
	private String num;
	
	private String bak;// 备份
	//private String parseRuleAdd;//add截取规则
	//private String getIndexAdd;// 获取add截取结果分组序号
	private String urlExample;// url实例
	
	//展示用
	
	private String prodName;//产品名称
	private String groupName;//应用分组名称
	private String contentLabelName;//标签名称
	private String noRuleType;
	private String startTime;
	private String endTime;
	private String hostParam;
	private String groupNameParam;
	private String batchFlag;// 单条件和多条件的切换标识

	public String getGroupNameParam() {
		return groupNameParam;
	}

	public void setGroupNameParam(String groupNameParam) {
		this.groupNameParam = groupNameParam;
	}

	public String getHostParam() {
		return hostParam;
	}

	public void setHostParam(String hostParam) {
		this.hostParam = hostParam;
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
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getParseRuleAndriod() {
		return parseRuleAndriod;
	}
	public void setParseRuleAndriod(String parseRuleAndriod) {
		this.parseRuleAndriod = parseRuleAndriod;
	}
	public String getGetIndexAndriod() {
		return getIndexAndriod;
	}
	public void setGetIndexAndriod(String getIndexAndriod) {
		this.getIndexAndriod = getIndexAndriod;
	}
	public String getParseRuleIos() {
		return parseRuleIos;
	}
	public void setParseRuleIos(String parseRuleIos) {
		this.parseRuleIos = parseRuleIos;
	}
	public String getGetIndexIos() {
		return getIndexIos;
	}
	public void setGetIndexIos(String getIndexIos) {
		this.getIndexIos = getIndexIos;
	}
	public String getGroupType() {
		return groupType;
	}
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	public String getContentLabelCode() {
		return contentLabelCode;
	}
	public void setContentLabelCode(String contentLabelCode) {
		this.contentLabelCode = contentLabelCode;
	}
	public String getAttrId() {
		return attrId;
	}
	public void setAttrId(String attrId) {
		this.attrId = attrId;
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getContentLabelName() {
		return contentLabelName;
	}
	public void setContentLabelName(String contentLabelName) {
		this.contentLabelName = contentLabelName;
	}
	public String getNoRuleType() {
		return noRuleType;
	}
	public void setNoRuleType(String noRuleType) {
		this.noRuleType = noRuleType;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getBak() {
		return bak;
	}

	public void setBak(String bak) {
		this.bak = bak;
	}

	public String getUrlExample() {
		return urlExample;
	}

	public void setUrlExamle(String urlExample) {
		this.urlExample = urlExample;
	}

	public String getBatchFlag() {
		return batchFlag;
	}

	public void setBatchFlag(String batchFlag) {
		this.batchFlag = batchFlag;
	}

	public void setUrlExample(String urlExample) {
		this.urlExample = urlExample;
	}


	
}
