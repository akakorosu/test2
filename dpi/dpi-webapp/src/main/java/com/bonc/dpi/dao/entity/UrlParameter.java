package com.bonc.dpi.dao.entity;


/**
 * url参数规则表
 * dim_ia_url_parameter
 * @author BONC-XUXL
 *
 */
public class UrlParameter extends OperationFlow {
	private String id;//序号
	private String host;//域名host
	private String parameterCode;//参数名PARAMETER_CODE
	private String parameterValue;//参数值PARAMETER_VALUE
	private String prodId;//产品IDPROD_ID
	private String regexpRule;//匹配规则REGEXP
	private String comments;//备注COMMENTS
	private String urlExample;//URL样例URL_EXAMPLE
	private String author;//作者
	private String opTime;//操作时间
	

	private String prodName; //产品名称
	private String startTime;//作者
	private String endTime;//操作时间
	private String hostParam;
	private String regexpRuleParam;//匹配规则REGEXP(用于插入)
	
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getParameterCode() {
		return parameterCode;
	}
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getRegexpRule() {
		return regexpRule;
	}
	public void setRegexpRule(String regexpRule) {
		this.regexpRule = regexpRule;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUrlExample() {
		return urlExample;
	}
	public void setUrlExample(String urlExample) {
		this.urlExample = urlExample;
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
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getRegexpRuleParam() {
		return regexpRuleParam;
	}
	public void setRegexpRuleParam(String regexpRuleParam) {
		this.regexpRuleParam = regexpRuleParam;
	}

}
