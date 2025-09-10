package com.bonc.dpi.dao.entity;

import com.bonc.dpi.cst.CST;
import com.bonc.system.service.SysCodeUtils;

public class DmDtarRuleInfo {

	private String id;
	private String tarName;
	private String tarCode;
	private String tarType;
	private String tarSign;
	private String tarValue;
	private String tarAndOr;
	private String tarOrder;
	private String tarBaseId;
	private String tarQuota;
	private String tarTypeText;
	
	private String tarQuotaText;
	private String tarSignText;
	
	public String getTarName() {
		return tarName;
	}
	public void setTarName(String tarName) {
		this.tarName = tarName;
	}
	public String getTarCode() {
		return tarCode;
	}
	public void setTarCode(String tarCode) {
		this.tarCode = tarCode;
	}
	public String getTarType() {
		return tarType;
	}
	public void setTarType(String tarType) {
		this.tarType = tarType;
	}
	public String getTarSign() {
		return tarSign;
	}
	public void setTarSign(String tarSign) {
		this.tarSign = tarSign;
		this.setTarSignText(SysCodeUtils.getSysCodeValue(CST.DM_D_TAR_RULE_INFO_PHFUHAO, tarSign));
	}
	public String getTarValue() {
		return tarValue;
	}
	public void setTarValue(String tarValue) {
		this.tarValue = tarValue;
	}
	public String getTarAndOr() {
		return tarAndOr;
	}
	public void setTarAndOr(String tarAndOr) {
		this.tarAndOr = tarAndOr;
	}
	public String getTarOrder() {
		return tarOrder;
	}
	public void setTarOrder(String tarOrder) {
		this.tarOrder = tarOrder;
	}
	public String getTarQuota() {
		return tarQuota;
	}
	public String getTarBaseId() {
		return tarBaseId;
	}
	public void setTarBaseId(String tarBaseId) {
		this.tarBaseId = tarBaseId;
	}
	public void setTarQuota(String tarQuota) {
		this.tarQuota = tarQuota;
		this.setTarQuotaText(SysCodeUtils.getSysCodeValue(CST.DM_D_TAR_RULE_INFO_PHZHIBIAO, tarQuota));
	}
	public String getTarTypeText() {
		return tarTypeText;
	}
	public void setTarTypeText(String tarTypeText) {
		this.tarTypeText = tarTypeText;
	}
	public String getTarQuotaText() {
		return tarQuotaText;
	}
	public void setTarQuotaText(String tarQuotaText) {
		this.tarQuotaText = tarQuotaText;
	}
	public String getTarSignText() {
		return tarSignText;
	}
	public void setTarSignText(String tarSignText) {
		this.tarSignText = tarSignText;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
