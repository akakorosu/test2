package com.bonc.workbench.dao.mapper;

import java.util.List;
import java.util.Map;

import com.bonc.workbench.bean.WorkOrderInfo;

public interface WorkOrderInfoMapper {

	WorkOrderInfo selectByPrimaryKey(String rectifyId);

	void insert(WorkOrderInfo workOrderInfo);

	List<WorkOrderInfo> selectPageList(Map<String, String> paramMap);

	int updateState(WorkOrderInfo prInfo);

	List<Map<String, String>> selectHiActinsts(String procId);

	String selectTaskIdByProcIdAndAssign(Map<String, String> map);

	String selectTaskNameById(String taskId);

	int selectRunTasksCountByprocId(String taskId);

	List<Map<String, String>> selectAssignByAssignId(String assignId);
	List<Map<String, String>> selectOrgNameByAssignId(String assignId);

	List<String> getStartTimeByActIdAndProcId(Map<String, String> map);

	List<String> selectAssignsByProcIdfromRunTask(String procId);

	List<String> getTaskDefKeyByProcIdandAssignfromRunTask(Map<String, String> map);

	List<Map<String, String>> getProcInfofromHiProc(Map<String, String> map);

	public int queryAuditState(Map<String, String> param);
	Integer selectBackLogCount(String userId);

	List<WorkOrderInfo> selectSendPageList(Map<String, String> paramMap);

	List<WorkOrderInfo> selectHandlePageList(Map<String, String> paramMap);

	List<WorkOrderInfo> selectParticipatePageList(Map<String, String> paramMap);

	List<WorkOrderInfo> selectHistoryPageList(Map<String, String> paramMap);

	void deleteInfo(String id);

	List<Map<String, String>> selectOrgNameByOrgId(String orgId);

	List<WorkOrderInfo> selectAll(Map<String, String> paramMap);

	void update(WorkOrderInfo woInfo);
}