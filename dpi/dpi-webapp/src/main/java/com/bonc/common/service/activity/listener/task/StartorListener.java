package com.bonc.common.service.activity.listener.task;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

import com.bonc.activiti.ProcessManager;
import com.bonc.activiti.entity.LocalProcessVariables;
/**
 * 
 * ClassName: StartorListener <br/> 
 * Function: 在任务节点中设置流转到发起人 <br/> 
 * Reason: 适应于回退到发起人的任务节点. <br/> 
 * date: 2017年8月14日 下午3:54:14 <br/> 
 * 
 * @author sunce 
 * @version  
 * @since JDK 1.6
 */
@Service
public class StartorListener implements TaskListener {
	
	@Resource
	private ProcessEngine processEngine;
	
	@Resource
	private ProcessManager processManager;
	
	/** serial id **/  
	private static final long serialVersionUID = 5066169200784365548L;

	@Override
	public void notify(DelegateTask delegateTask) {
		if("create".equals(delegateTask.getEventName())){
			try {
				LocalProcessVariables localProcessVariables = processManager.getProcessVariablesByProcessInstanceId(delegateTask.getProcessInstanceId());
				delegateTask.setAssignee(localProcessVariables.getCreatorId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
