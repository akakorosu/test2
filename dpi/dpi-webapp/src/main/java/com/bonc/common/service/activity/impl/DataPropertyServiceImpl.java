
package com.bonc.common.service.activity.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.HttpInvoker;
import com.bonc.activiti.entity.LocalProcessVariables;
import com.bonc.activiti.service.IProcessInterface;

@Service
public class DataPropertyServiceImpl implements IProcessInterface{
	
	private HttpInvoker httpInvoker=new HttpInvoker();
	
	@Override
	public void start(LocalProcessVariables localProcessVariables) {
		try {
			Map<String,String> requestMap = new HashMap<String,String>();
			requestMap.put("event", "start");
			requestMap.put("processId", localProcessVariables.getId());
			requestMap.put("userId", localProcessVariables.getCreatorId());
			httpInvoker.post(CST.SANDBOX_NET+"/rest/process/start.do", requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void complete(LocalProcessVariables localProcessVariables) {
		try {
			Map<String,String> requestMap = new HashMap<String,String>();
			requestMap.put("event", "complete");
			requestMap.put("processId", localProcessVariables.getId());
			requestMap.put("userId", localProcessVariables.getCreatorId());
			httpInvoker.post(CST.SANDBOX_NET+"/rest/process/complete.do", requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

	@Override
	public void rejectStart(LocalProcessVariables localProcessVariables) {
		try {
			Map<String,String> requestMap = new HashMap<String,String>();
			requestMap.put("event", "rejectStart");
			requestMap.put("processId", localProcessVariables.getId());
			requestMap.put("userId", localProcessVariables.getCreatorId());
			httpInvoker.post(CST.SANDBOX_NET+"/rest/process/rejectStart.do", requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void end(LocalProcessVariables localProcessVariables) {
		try {
			Map<String,String> requestMap = new HashMap<String,String>();
			requestMap.put("event", "end");
			requestMap.put("processId", localProcessVariables.getId());
			requestMap.put("userId", localProcessVariables.getCreatorId());
			httpInvoker.post(CST.SANDBOX_NET+"/rest/process/end.do", requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rejectPre(LocalProcessVariables localProcessVariables) {
		try {
			Map<String,String> requestMap = new HashMap<String,String>();
			requestMap.put("event", "rejectPre");
			requestMap.put("processId", localProcessVariables.getId());
			requestMap.put("userId", localProcessVariables.getCreatorId());
			httpInvoker.post(CST.SANDBOX_NET+"/rest/process/rejectPre.do", requestMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void handling(LocalProcessVariables localProcessVariables) {
		// TODO Auto-generated method stub
		
	}

}
