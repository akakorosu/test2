package com.bonc.dpi.dao.entity;


/**
 * url路径规则表
 * dim_ia_url_path
 * @author BONC-XUXL
 *
 */
public class UrlPath extends OperationFlow {
	private String id;
	private String host;//域名host
	private String prodId;//产品IDPROD_ID
	private String regexpRule;//匹配规则REGEXP
	private String comments;//备注COMMENTS
	private String urlExample;//URL样例URL_EXAMPLE
	private String author;//作者
	private String opTime;//操作时间
	
	//******校验主键

	private String hostOld;//
	private String regexpRuleOld;//
	
	private String prodName; //产品名称
	private String startTime;//
	private String endTime;//
	private String hostParam;
	private String test;
	
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
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
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getHostOld() {
		return hostOld;
	}
	public void setHostOld(String hostOld) {
		this.hostOld = hostOld;
	}
	public String getRegexpRuleOld() {
		return regexpRuleOld;
	}
	public void setRegexpRuleOld(String regexpRuleOld) {
		this.regexpRuleOld = regexpRuleOld;
	}
	
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
}
