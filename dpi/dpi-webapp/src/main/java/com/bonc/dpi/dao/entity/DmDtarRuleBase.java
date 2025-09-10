package com.bonc.dpi.dao.entity;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.bonc.dpi.cst.CST;
import com.bonc.system.service.SysCodeUtils;

public class DmDtarRuleBase {

	private String id;
	private String tarBaseName;
	private String tarBaseDefine;
	private String createUser;
	private String createTime;
	private String tarBaseType;
	private String tarBaseClass;
	
	private List<DmDtarRuleInfo> children;
	private String childrenStr;
	private String likeTarBaseName;
	private String likeTarBaseClass;
	private String tarBaseTypeName;
	
	public String getTarBaseName() {
		return tarBaseName;
	}
	public void setTarBaseName(String tarBaseName) {
		this.tarBaseName = tarBaseName;
	}
	public String getTarBaseDefine() {
		return tarBaseDefine;
	}
	public void setTarBaseDefine(String tarBaseDefine) {
		this.tarBaseDefine = tarBaseDefine;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public List<DmDtarRuleInfo> getChildren() {
		return children;
	}
	public void setChildren(List<DmDtarRuleInfo> children) {
		this.children = children;
		this.setChildrenStr(JSONArray.toJSONString(children));
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChildrenStr() {
		return childrenStr;
	}
	public void setChildrenStr(String childrenStr) {
		this.childrenStr = childrenStr;
	}
	public String getLikeTarBaseName() {
		return likeTarBaseName;
	}
	public void setLikeTarBaseName(String likeTarBaseName) {
		if(StringUtils.isBlank(likeTarBaseName)) {
			return;
		}
		this.likeTarBaseName = "%" + likeTarBaseName + "%";
	}
	public String getTarBaseType() {
		return tarBaseType;
	}
	public void setTarBaseType(String tarBaseType) {
		this.tarBaseType = tarBaseType;
		this.setTarBaseTypeName(SysCodeUtils.getSysCodeValue(CST.DM_D_TAR_RULE_INFO_PHGZTYPE, tarBaseType));
	}
	public String getTarBaseTypeName() {
		return tarBaseTypeName;
	}
	public void setTarBaseTypeName(String tarBaseTypeName) {
		this.tarBaseTypeName = tarBaseTypeName;
	}
	public String getTarBaseClass() {
		return tarBaseClass;
	}
	public void setTarBaseClass(String tarBaseClass) {
		this.tarBaseClass = tarBaseClass;
	}
	public String getLikeTarBaseClass() {
		return likeTarBaseClass;
	}
	public void setLikeTarBaseClass(String likeTarBaseClass) {
		if(StringUtils.isBlank(likeTarBaseClass)) {
			return;
		}
		this.likeTarBaseClass = "%" + likeTarBaseClass + "%";
	}
}
