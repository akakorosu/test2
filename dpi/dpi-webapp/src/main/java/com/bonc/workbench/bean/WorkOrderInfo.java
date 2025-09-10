package com.bonc.workbench.bean;

import java.io.Serializable;

public class WorkOrderInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 工单ID
	 */
	private String id;
	/**
	 * 工单名称
	 */
	private String workOrderName;
	/**
	 * 工单类型
	 */
	private String workOrderType;
	/**
	 * 关联模型
	 */
	private String model;
	/**
	 * 结论
	 */
	private String conclusion;
	/**
	 * 接收部门
	 */
	private String org;
	/**
	 * 接收部门id
	 */
	private String orgId;
	/**
	 * 接收部门
	 */
	private String cuOrg;
	/**
	 * 接收部门id
	 */
	private String cuOrgId;
	/**
	 * 接收人
	 */
	private String assignee;
	/**
	 * 接收人id
	 */
	private String assigneeId;
	/**
	 * 当前办理人
	 */
	private String curAssignee;

	/**
	 * 当前办理人id
	 */
	private String curAssigneeId;
	/**
	 * 整改要求
	 */
	private String rectifyRequire;
	/**
	 * 整改期限
	 */
	private String rectifyDeadline;
	
	/**
	 * 审计周期
	 */
	private String auditCycle;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 报告名字
	 */
	private String reportName;

	/**
	 * 主数据id
	 */
	private String dataId;
	
	/**
	 * 合同名称
	 */
	private String contractName;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 创建者id
	 */
	private String creatorId;
	
	/**
	 * 创建者名字
	 */
	private String creatorName;
	
	/**
	 * 创建者组织id
	 */
	private String creatorOrgId;
	
	/**
	 * 创建者组织id
	 */
	private String dataAreaId;
	
	/**
	 * 流程实例id
	 */
	private String procId;
	
	/**
	 * 当前任务开始时间
	 */
	private String startTime;
	
	/**
	 * 当前任务持续时间
	 */
	private String duration;
	
	/**
	 * 任务类型
	 */
	private String taskType;
	
	/**
	 * 备注
	 */
	private String comments;
	
	/**
	 * 工单附件
	 */
	private String files;
	
	public String getCreateTime() {
		return createTime;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCurAssignee() {
		return curAssignee;
	}

	public void setCurAssignee(String curAssignee) {
		this.curAssignee = curAssignee;
	}

	public String getCurAssigneeId() {
		return curAssigneeId;
	}

	public void setCurAssigneeId(String curAssigneeId) {
		this.curAssigneeId = curAssigneeId;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCuOrg() {
		return cuOrg;
	}

	public void setCuOrg(String cuOrg) {
		this.cuOrg = cuOrg;
	}

	public String getCuOrgId() {
		return cuOrgId;
	}

	public void setCuOrgId(String cuOrgId) {
		this.cuOrgId = cuOrgId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getDataAreaId() {
		return dataAreaId;
	}

	public void setDataAreaId(String dataAreaId) {
		this.dataAreaId = dataAreaId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getProcId() {
		return procId;
	}

	public void setProcId(String procId) {
		this.procId = procId;
	}

	public String getWorkOrderType() {
		return workOrderType;
	}

	public void setWorkOrderType(String workOrderType) {
		this.workOrderType = workOrderType;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatorOrgId() {
		return creatorOrgId;
	}

	public void setCreatorOrgId(String creatorOrgId) {
		this.creatorOrgId = creatorOrgId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getRectifyRequire() {
		return rectifyRequire;
	}

	public void setRectifyRequire(String rectifyRequire) {
		this.rectifyRequire = rectifyRequire;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWorkOrderName() {
		return workOrderName;
	}

	public void setWorkOrderName(String workOrderName) {
		this.workOrderName = workOrderName;
	}

	public String getRectifyDeadline() {
		return rectifyDeadline;
	}

	public void setRectifyDeadline(String rectifyDeadline) {
		this.rectifyDeadline = rectifyDeadline;
	}

	public String getAuditCycle() {
		return auditCycle;
	}

	public void setAuditCycle(String auditCycle) {
		this.auditCycle = auditCycle;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
}
