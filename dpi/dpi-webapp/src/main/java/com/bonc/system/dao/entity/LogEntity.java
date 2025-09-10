package com.bonc.system.dao.entity;

public class LogEntity {

	private String user_id;
	private String clazz_name;
	private String method_name;
	private String method_name_list;
	private String method_data_list;

	private String func_code;
	private String func_name;
	private String point_code;
	private String log_start_date;
	private String log_end_date;

	// add new log start
	private String logDate;
	private String operateId;
	private String userOrg;
	private String mainAccount;
	private String subAccount;
	private String subType;
	private String subIfSensitive;
	private String sysId;
	private String visitWay;
	private String serverIp;
	private String clientIp;
	private String operName;
	private String ifsensitive;
	private String fuzzify;
	private String batchOper;
	private String senID;
	private String involvePhone;
	private String operTypeNum;
	private String operDate;
	private String operDescribe;
	private String serverTypeNum;
	private String goldbank;
	private String goldbankId;
	private String ifAuth;
	private String authType;
	private String userauthId;
	private String server_port;
	private String endOperTime;
	private int op_time;
	// add new log end


	public String getServer_port() {
		return server_port;
	}

	public void setServer_port(String server_port) {
		this.server_port = server_port;
	}

	public String getUser_id() {
		return user_id;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getClazz_name() {
		return clazz_name;
	}
	public void setClazz_name(String clazz_name) {
		this.clazz_name = clazz_name;
	}
	public String getMethod_name() {
		return method_name;
	}
	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}
	public String getMethod_name_list() {
		return method_name_list;
	}
	public void setMethod_name_list(String method_name_list) {
		this.method_name_list = method_name_list;
	}
	public String getMethod_data_list() {
		return method_data_list;
	}
	public void setMethod_data_list(String method_data_list) {
		this.method_data_list = method_data_list;
	}
	public String getFunc_code() {
		return func_code;
	}
	public void setFunc_code(String func_code) {
		this.func_code = func_code;
	}
	public String getFunc_name() {
		return func_name;
	}
	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}
	public String getPoint_code() {
		return point_code;
	}
	public void setPoint_code(String point_code) {
		this.point_code = point_code;
	}
	public String getLog_start_date() {
		return log_start_date;
	}
	public void setLog_start_date(String log_start_date) {
		this.log_start_date = log_start_date;
	}
	public String getLog_end_date() {
		return log_end_date;
	}
	public void setLog_end_date(String log_end_date) {
		this.log_end_date = log_end_date;
	}
	public String getOperateId() {
		return operateId;
	}
	public void setOperateId(String operateId) {
		this.operateId = operateId;
	}
	public String getUserOrg() {
		return userOrg;
	}
	public void setUserOrg(String userOrg) {
		this.userOrg = userOrg;
	}
	public String getMainAccount() {
		return mainAccount;
	}
	public void setMainAccount(String mainAccount) {
		this.mainAccount = mainAccount;
	}
	public String getSubAccount() {
		return subAccount;
	}
	public void setSubAccount(String subAccount) {
		this.subAccount = subAccount;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getSubIfSensitive() {
		return subIfSensitive;
	}
	public void setSubIfSensitive(String subIfSensitive) {
		this.subIfSensitive = subIfSensitive;
	}
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getVisitWay() {
		return visitWay;
	}
	public void setVisitWay(String visitWay) {
		this.visitWay = visitWay;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public String getIfsensitive() {
		return ifsensitive;
	}
	public void setIfsensitive(String ifsensitive) {
		this.ifsensitive = ifsensitive;
	}
	public String getFuzzify() {
		return fuzzify;
	}
	public void setFuzzify(String fuzzify) {
		this.fuzzify = fuzzify;
	}
	public String getBatchOper() {
		return batchOper;
	}
	public void setBatchOper(String batchOper) {
		this.batchOper = batchOper;
	}
	public String getSenID() {
		return senID;
	}
	public void setSenID(String senID) {
		this.senID = senID;
	}
	public String getInvolvePhone() {
		return involvePhone;
	}
	public void setInvolvePhone(String involvePhone) {
		this.involvePhone = involvePhone;
	}
	public String getOperTypeNum() {
		return operTypeNum;
	}
	public void setOperTypeNum(String operTypeNum) {
		this.operTypeNum = operTypeNum;
	}
	public String getOperDate() {
		return operDate;
	}
	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}
	public String getOperDescribe() {
		return operDescribe;
	}
	public void setOperDescribe(String operDescribe) {
		this.operDescribe = operDescribe;
	}
	public String getServerTypeNum() {
		return serverTypeNum;
	}
	public void setServerTypeNum(String serverTypeNum) {
		this.serverTypeNum = serverTypeNum;
	}
	public String getGoldbank() {
		return goldbank;
	}
	public void setGoldbank(String goldbank) {
		this.goldbank = goldbank;
	}
	public String getGoldbankId() {
		return goldbankId;
	}
	public void setGoldbankId(String goldbankId) {
		this.goldbankId = goldbankId;
	}
	public String getIfAuth() {
		return ifAuth;
	}
	public void setIfAuth(String ifAuth) {
		this.ifAuth = ifAuth;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getUserauthId() {
		return userauthId;
	}
	public void setUserauthId(String userauthId) {
		this.userauthId = userauthId;
	}
	public String getEndOperTime() {
		return endOperTime;
	}
	public void setEndOperTime(String endOperTime) {
		this.endOperTime = endOperTime;
	}
	public int getOp_time() {
		return op_time;
	}
	public void setOp_time(int op_time) {
		this.op_time = op_time;
	}

	@Override
	public String toString() {
		return "LogEntity [user_id=" + user_id + ", clazz_name=" + clazz_name + ", method_name=" + method_name
				+ ", method_name_list=" + method_name_list + ", method_data_list=" + method_data_list + ", func_code="
				+ func_code + ", func_name=" + func_name + ", point_code=" + point_code + ", log_start_date="
				+ log_start_date + ", log_end_date=" + log_end_date + ", logDate=" + logDate + ", operateId="
				+ operateId + ", userOrg=" + userOrg + ", mainAccount=" + mainAccount + ", subAccount=" + subAccount
				+ ", subType=" + subType + ", subIfSensitive=" + subIfSensitive + ", sysId=" + sysId + ", visitWay="
				+ visitWay + ", serverIp=" + serverIp + ", clientIp=" + clientIp + ", operName=" + operName
				+ ", ifsensitive=" + ifsensitive + ", fuzzify=" + fuzzify + ", batchOper=" + batchOper + ", senID="
				+ senID + ", involvePhone=" + involvePhone + ", operTypeNum=" + operTypeNum + ", operDate=" + operDate
				+ ", operDescribe=" + operDescribe + ", serverTypeNum=" + serverTypeNum + ", goldbank=" + goldbank
				+ ", goldbankId=" + goldbankId + ", ifAuth=" + ifAuth + ", authType=" + authType + ", userauthId="
				+ userauthId + ", endOperTime=" + endOperTime + ", op_time=" + op_time + ", server_port=" + server_port + "]";
	}


}
