package com.bonc.common.service.activity.listener.task;


import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserListener implements TaskListener {
	
	@Resource
	private ProcessEngine processEngine;
	
	/** serial id **/  
	private static final long serialVersionUID = 5066169200784365548L;

	@Override
	public void notify(DelegateTask delegateTask) {
		if("create".equals(delegateTask.getEventName())){			
		    delegateTask.setAssignee("0");
		}
	}
}
