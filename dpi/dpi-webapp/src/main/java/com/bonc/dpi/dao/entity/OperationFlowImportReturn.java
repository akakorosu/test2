package com.bonc.dpi.dao.entity;

import java.util.ArrayList;
import java.util.List;

public class OperationFlowImportReturn {

	private String info = "";
	private String actionUrl;//action请求url
	private String pageUrl;//返回页面路径
	private String falseType;//错误类型，1：数据有问题，2：数据没问题，3：excle导入数据超出最大数
	private String maxDataNumForExcle;//excle导入最大数据条数
	private List<? extends OperationFlow> errorList = new ArrayList<OperationFlow>();

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	
	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	public String getFalseType() {
		return falseType;
	}

	public void setFalseType(String falseType) {
		this.falseType = falseType;
	}
	public String getMaxDataNumForExcle() {
		return maxDataNumForExcle;
	}

	public void setMaxDataNumForExcle(String maxDataNumForExcle) {
		this.maxDataNumForExcle = maxDataNumForExcle;
	}
	public List<? extends OperationFlow> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<? extends OperationFlow> errorList) {
		this.errorList = errorList;
	}
}
