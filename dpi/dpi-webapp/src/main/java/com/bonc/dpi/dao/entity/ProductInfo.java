package com.bonc.dpi.dao.entity;

/**
 * 产品表
 * dim_ia_product_info
 * @author BONC-XUXL
 *
 */
public class ProductInfo extends OperationFlow {
	private String id;//id
	private String prodId; //prod_id,产品ID
	private String prodName; //prod_name,产品名称
	private String prodLabelCode; //prod_label_code,标签ID
	private String addLabelList; //add_label_list,附属标签列表
	private String fatherProdId;//father_prod_id,父产品ID
	
	private String prodTypeId; //prod_type_id,产品归类ID 同产品ID
	private String prodTypeName; //prod_type_name,产品归类名称  默认归类为产品名称 建立产品间的关系 归属、关联性，默认归类为自己，有特殊需求的手工处理，如果微信公众平台、微信支付归类应该都为微信
	private String prodCatagory;//prod_catagory,产品类别
	private String isMainProd;//is_main_prod,是否主产品
	private String isApp; //is_app,是否APP
	
	private String isOperator; //is_operator,是否运营商自有业务：0-非运营商 1-移动自有 2-联通自有 3-电信自有
	private String spId; //sp_id,spid
	private String spName; //sp_name,spname
	private String shortName; //short_name,公司简称
	private String labelCheck;//label_check，标签是否稽核
	
	private String author; //操作人
	private String opTime; //操作时间
	private String prodDesc;  //产品描述IOS_PROD_NAME
	private String iosProdName; //苹果产品名称IS_BOCE
	private String isBoce;  //是否拨测    0 没有拨测  1 拨测  BOCE_DATE
	private String boceDate;
	
	private String labelName1;//一级标签名称
	private String labelName2;//二级标签名称
	
	//校验主键
	private String prodIdOld;//校验主键
	private String startTime;
	private String endTime; 
	private String batchFlag; 
	private String regRule;
	private String tableName; 
	private String host;
	private String tableNameEn;
	
	  //拨测时间
	
	
	public String getIsBonc() {
		return isBoce;
	}
	public void setIsBonc(String isBonc) {
		this.isBoce = isBonc;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getIosProdName() {
		return iosProdName;
	}
	public void setIosProdName(String iosProdName) {
		this.iosProdName = iosProdName;
	}
	
	public String getBoceDate() {
		return boceDate;
	}
	public void setBoceDate(String boceDate) {
		this.boceDate = boceDate;
	}
	public String getTableNameEn() {
		return tableNameEn;
	}
	public void setTableNameEn(String tableNameEn) {
		this.tableNameEn = tableNameEn;
	}
	public String getRegRule() {
		return regRule;
	}
	public void setRegRule(String regRule) {
		this.regRule = regRule;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getBatchFlag() {
		return batchFlag;
	}
	public void setBatchFlag(String batchFlag) {
		this.batchFlag = batchFlag;
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
	public String getProdIdOld() {
		return prodIdOld;
	}
	public void setProdIdOld(String prodIdOld) {
		this.prodIdOld = prodIdOld;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdLabelCode() {
		return prodLabelCode;
	}
	public void setProdLabelCode(String prodLabelCode) {
		this.prodLabelCode = prodLabelCode;
	}
	public String getAddLabelList() {
		return addLabelList;
	}
	public void setAddLabelList(String addLabelList) {
		this.addLabelList = addLabelList;
	}
	public String getFatherProdId() {
		return fatherProdId;
	}
	public void setFatherProdId(String fatherProdId) {
		this.fatherProdId = fatherProdId;
	}
	public String getProdTypeId() {
		return prodTypeId;
	}
	public void setProdTypeId(String prodTypeId) {
		this.prodTypeId = prodTypeId;
	}
	public String getProdTypeName() {
		return prodTypeName;
	}
	public void setProdTypeName(String prodTypeName) {
		this.prodTypeName = prodTypeName;
	}
	public String getProdCatagory() {
		return prodCatagory;
	}
	public void setProdCatagory(String prodCatagory) {
		this.prodCatagory = prodCatagory;
	}
	public String getIsMainProd() {
		return isMainProd;
	}
	public void setIsMainProd(String isMainProd) {
		this.isMainProd = isMainProd;
	}
	public String getIsApp() {
		return isApp;
	}
	public void setIsApp(String isApp) {
		this.isApp = isApp;
	}
	public String getIsOperator() {
		return isOperator;
	}
	public void setIsOperator(String isOperator) {
		this.isOperator = isOperator;
	}
	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getLabelCheck() {
		return labelCheck;
	}
	public void setLabelCheck(String labelCheck) {
		this.labelCheck = labelCheck;
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
	
	public String getLabelName1() {
		return labelName1;
	}
	public void setLabelName1(String labelName1) {
		this.labelName1 = labelName1;
	}
	public String getLabelName2() {
		return labelName2;
	}
	public void setLabelName2(String labelName2) {
		this.labelName2 = labelName2;
	}
}
