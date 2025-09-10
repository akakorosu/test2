package com.bonc.common.service.activity.impl;

import java.util.HashMap;
import java.util.Map;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.HttpInvoker;
import com.bonc.activiti.entity.LocalProcessVariables;
import com.bonc.activiti.service.IProcessInterface;
/**
 * 用户资产取消工作流
 * @author 周平
 *
 */
public class UserAssetCancelServiceImpl implements IProcessInterface {

	
	
	@Override
	public void complete(LocalProcessVariables localProcessVariables) {
		// 回调用户资产删除接口
		try {
			String url = CST.NEW_SANDBOX + "/activiti/complete/cancelAsset";
			Map<String, String> requestParam = new HashMap<String, String>();
			// 配置参数
			requestParam.put("userId",localProcessVariables.getCreatorId());
			requestParam.put("id", localProcessVariables.getId());
			// 发送请求回调函数
			HttpInvoker invoker = new HttpInvoker();
			invoker.post(url, requestParam);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void start(LocalProcessVariables localProcessVariables) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectStart(LocalProcessVariables localProcessVariables) {
		// TODO Auto-generated method stub
		// 将资产状态改回成功
		try {
			String url = CST.NEW_SANDBOX + "/activiti/rejectStart/cancelAsset";
			Map<String, String> requestParam = new HashMap<String, String>();
			// 配置参数
			requestParam.put("userId",localProcessVariables.getCreatorId());
			requestParam.put("id", localProcessVariables.getId());
			// 发送请求回调函数
			HttpInvoker invoker = new HttpInvoker();
			invoker.post(url, requestParam);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void end(LocalProcessVariables localProcessVariables) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectPre(LocalProcessVariables localProcessVariables) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handling(LocalProcessVariables localProcessVariables) {
		// TODO Auto-generated method stub
		
	}

}
