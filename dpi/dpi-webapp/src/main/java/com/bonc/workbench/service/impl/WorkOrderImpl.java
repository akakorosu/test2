package com.bonc.workbench.service.impl;

import javax.annotation.Resource;

import com.bonc.activiti.dao.entity.FlowLog;
import org.springframework.stereotype.Service;

import com.bonc.workbench.bean.WorkOrderInfo;
import com.bonc.activiti.dao.mapper.FlowLogMapper;
import com.bonc.workbench.dao.mapper.WorkOrderInfoMapper;
import com.bonc.common.cst.CST;
import com.bonc.activiti.entity.LocalProcessVariables;
import com.bonc.activiti.service.IProcessInterface;

@Service
public class WorkOrderImpl implements IProcessInterface{
	
	@Resource
	private FlowLogMapper flowLogMapper;
	
	@Resource
	private WorkOrderInfoMapper workOrderInfoMapper;

	@Override
	public void start(LocalProcessVariables localProcessVariables) {
		try {
			for (Object workOrderInfo:localProcessVariables.getBusinessInfo()) {
				WorkOrderInfo prInfo = (WorkOrderInfo) workOrderInfo;
				if(CST.WORKORDER_FLOW_WORKORDERSEND.equals(localProcessVariables.getClassName())){
					prInfo.setStatus(CST.FLOW_STATE_LOG_HANDLING);
					// SQL文未写(业务表明确以后打开)
					workOrderInfoMapper.updateState(prInfo);
				}
				FlowLog flowLog = new FlowLog();
				flowLog.setId(prInfo.getId());
				flowLog.setUserId(localProcessVariables.getCreatorId());
				flowLog.setState(CST.FLOW_STATE_LOG_HANDLING);//接入审批中
				flowLog.setClassname(localProcessVariables.getClassName());
				flowLog.setFlowId(localProcessVariables.getLocalProcessId());
				flowLogMapper.insert(flowLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void complete(LocalProcessVariables localProcessVariables) {
		try {
			for (Object workOrderInfo:localProcessVariables.getBusinessInfo()) {
				WorkOrderInfo prInfo = (WorkOrderInfo) workOrderInfo;
				if(CST.WORKORDER_FLOW_WORKORDERSEND.equals(localProcessVariables.getClassName())){
					prInfo.setStatus(CST.FLOW_STATE_LOG_FINISHED);
					// SQL文未写(业务表明确以后打开)
					workOrderInfoMapper.updateState(prInfo);
				}
				FlowLog flowLog = new FlowLog();
				flowLog.setId(prInfo.getId());
				flowLog.setUserId(localProcessVariables.getCreatorId());
				flowLog.setState(CST.FLOW_STATE_LOG_FINISHED);//接入审批中
				flowLog.setClassname(localProcessVariables.getClassName());
				flowLog.setFlowId(localProcessVariables.getLocalProcessId());
				flowLogMapper.insert(flowLog);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void rejectStart(LocalProcessVariables localProcessVariables) {
//		if(CST.API_FLOW_APIREGISTER.equals(localProcessVariables.getClassName())){
//			TrbacApiInfo trbacApiInfo = new TrbacApiInfo();
//			trbacApiInfo.setApiId(localProcessVariables.getId());
//			trbacApiInfo.setState(CST.API_STATE_UNREGISTER);
//			trbacApiInfoMapper.updateApiState(trbacApiInfo);
//		}
//		FlowLog flowLog = new FlowLog();
//		flowLog.setFlowId(localProcessVariables.getLocalProcessId());
//		flowLog.setId(localProcessVariables.getLocalProcessId());
//		flowLogMapper.deleteByPrimaryKey(flowLog);
		
	}

	@Override
	public void end(LocalProcessVariables localProcessVariables) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectPre(LocalProcessVariables localProcessVariables) {
		/*if(CST.API_FLOW_APIREGISTER.equals(localProcessVariables.getClassName())){
			TrbacApiInfo trbacApiInfo = new TrbacApiInfo();
			trbacApiInfo.setApiId(localProcessVariables.getId());
			trbacApiInfo.setState(CST.API_STATE_UNREGISTER);
			trbacApiInfoMapper.updateApiState(trbacApiInfo);
		}
		FlowLog flowLog = new FlowLog();
		flowLog.setFlowId(localProcessVariables.getLocalProcessId());
		flowLog.setId(localProcessVariables.getLocalProcessId());
		flowLogMapper.deleteByPrimaryKey(flowLog);*/
	}

	@Override
	public void handling(LocalProcessVariables localProcessVariables) {
		try {
			for (Object workOrderInfo:localProcessVariables.getBusinessInfo()) {
				WorkOrderInfo prInfo = (WorkOrderInfo) workOrderInfo;
				if(CST.WORKORDER_FLOW_WORKORDERSEND.equals(localProcessVariables.getClassName())){
					String taskName = workOrderInfoMapper.selectTaskNameById(localProcessVariables.getTaskId());
					if (taskName.contains("send")) {
						prInfo.setStatus(CST.FLOW_STATE_LOG_HANDLING);
						// SQL文未写(业务表明确以后打开)
						workOrderInfoMapper.updateState(prInfo);
						FlowLog flowLog = new FlowLog();
						flowLog.setId(prInfo.getId());
						flowLog.setUserId(localProcessVariables.getCreatorId());
						flowLog.setState(CST.FLOW_STATE_LOG_HANDLING);//接入审批中
						flowLog.setClassname(localProcessVariables.getClassName());
						flowLog.setFlowId(localProcessVariables.getLocalProcessId());
						flowLogMapper.insert(flowLog);
					}
					else if(taskName.contains("handl")) {
						int runTaskNum = workOrderInfoMapper.selectRunTasksCountByprocId(localProcessVariables.getTaskId());
						if (runTaskNum == 0) {
							prInfo.setStatus(CST.FLOW_STATE_LOG_AUDITING);
							// SQL文未写(业务表明确以后打开)
							workOrderInfoMapper.updateState(prInfo);
							FlowLog flowLog = new FlowLog();
							flowLog.setId(prInfo.getId());
							flowLog.setUserId(localProcessVariables.getCreatorId());
							flowLog.setState(CST.FLOW_STATE_LOG_AUDITING);//接入审批中
							flowLog.setClassname(localProcessVariables.getClassName());
							flowLog.setFlowId(localProcessVariables.getLocalProcessId());
							flowLogMapper.insert(flowLog);
						}
					}
					else {
						if ("0".equals(localProcessVariables.getApproval())) {
							prInfo.setStatus(CST.FLOW_STATE_LOG_HANDLING);
							// SQL文未写(业务表明确以后打开)
							workOrderInfoMapper.updateState(prInfo);
							FlowLog flowLog = new FlowLog();
							flowLog.setId(prInfo.getId());
							flowLog.setUserId(localProcessVariables.getCreatorId());
							flowLog.setState(CST.FLOW_STATE_LOG_HANDLING);//接入审批中
							flowLog.setClassname(localProcessVariables.getClassName());
							flowLog.setFlowId(localProcessVariables.getLocalProcessId());
							flowLogMapper.insert(flowLog);
						}
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
