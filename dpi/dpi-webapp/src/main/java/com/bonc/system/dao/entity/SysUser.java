package com.bonc.system.dao.entity;

import java.util.List;

import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.system.service.SysCodeUtils;

public class SysUser extends OperationFlow {

	private String loginId;
	private String userName;
	private String userMobile;
	private String userMail;
	
	private String userId;
	private String createId;
	private String password;
	private String orgId;
	private String userSex;
	private String userSexName;
	private String userTelephone;
	private String pwdState;
	private String createTime;
	private String updateTime;
	private String userLevel;
	private String memo;
	private String empNo;
	private String userState;
	private String orgName;
	private List<SysRoleUser> sysRoleUserList;
	private SysRoleUser loginSysRoleUser;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
		this.setUserSexName(SysCodeUtils.getSysCodeValue(SysCodeUtils.SEX, userSex));
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserTelephone() {
		return userTelephone;
	}
	public void setUserTelephone(String userTelephone) {
		this.userTelephone = userTelephone;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getPwdState() {
		return pwdState;
	}
	public void setPwdState(String pwdState) {
		this.pwdState = pwdState;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getUserSexName() {
		return userSexName;
	}
	public void setUserSexName(String userSexName) {
		this.userSexName = userSexName;
	}
	public List<SysRoleUser> getSysRoleUserList() {
		return sysRoleUserList;
	}
	public void setSysRoleUserList(List<SysRoleUser> sysRoleUserList) {
		this.sysRoleUserList = sysRoleUserList;
	}
	public SysRoleUser getLoginSysRoleUser() {
		return loginSysRoleUser;
	}
	public void setLoginSysRoleUser(SysRoleUser loginSysRoleUser) {
		this.loginSysRoleUser = loginSysRoleUser;
	}
}
