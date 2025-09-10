package com.bonc.dpi.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.dpi.dao.entity.IncreaseProdName;
import com.bonc.dpi.dao.entity.OperationsCenter;
import com.bonc.dpi.dao.mapper.OperationsCenterMapper;

@Service
public class OperationsCenterService {
	@Autowired
	private OperationsCenterMapper operationsCenterMapper;

	/**
	 * 查询稽核错误数量
	 * 
	 * @return
	 */
	public Map<String, String> getCount(String time) {
		Map<String, String> map = new HashMap<String, String>();
		String auditErrorCount = operationsCenterMapper.selectAuditErrorCount(time);
		String unidentifiedUaCount = operationsCenterMapper.selectUnidentifiedUaCount(time);
		String topLevelDomainCount = operationsCenterMapper.selectTopLevelDomainCount(time);
		map.put("auditErrorCount", auditErrorCount);
		map.put("unidentifiedUaCount", unidentifiedUaCount);
		map.put("topLevelDomainCount", topLevelDomainCount);
		return map;
	}
	// /**
	// * 查询稽核错误数量
	// * @return
	// */
	// public String getUnidentifiedUaCount(String time) {
	// return operationsCenterMapper.selectUnidentifiedUaCount(time);
	// }

	/**
	 * 获取记录和流量相关数据
	 * 
	 * @param time
	 * @param area
	 * @param gender
	 * @return
	 */
	public OperationsCenter getRecordAndFlow(String time) {
		// OperationsCenter recordAndFlowTotal =
		// operationsCenterMapper.selectRecordAndFlowTotal(time);
		OperationsCenter recordAndFlow = operationsCenterMapper.selectRecordAndFlow(time);
		// Map<String, Object> recordAndFlow =
		// operationsCenterMapper.selectRecordAndFlow(time);
		// DecimalFormat df = new DecimalFormat("#0.00%");
		// 记录数占比
		/*
		 * double totalPercent = 0; double flowPercent = 0; OperationsCenter result=new
		 * OperationsCenter(); if
		 * (recordAndFlowTotal!=null&&Double.valueOf(recordAndFlowTotal.getNumberTotal()
		 * ) > 0) {
		 * totalPercent=Double.valueOf(recordAndFlow.getCount())/Double.valueOf(
		 * recordAndFlowTotal.getNumberTotal()); } if
		 * (recordAndFlowTotal!=null&&Double.valueOf(recordAndFlowTotal.getFlowTotal())
		 * > 0) { flowPercent=Double.valueOf(recordAndFlow.getFlow())/Double.valueOf(
		 * recordAndFlowTotal.getFlowTotal()); }
		 * result.setTotalPercent(df.format(totalPercent));
		 * result.setFlowPercent(df.format(flowPercent)); if(recordAndFlowTotal!=null) {
		 * result.setNumberTotal(recordAndFlowTotal.getNumberTotal());
		 * result.setFlowTotal(recordAndFlowTotal.getFlowTotal()); }else{
		 * result.setNumberTotal("0"); result.setFlowTotal("0"); }
		 * if(recordAndFlow!=null) { result.setCount(recordAndFlow.getCount());
		 * if(recordAndFlow.getFlow()!=null) { result.setFlow(recordAndFlow.getFlow());
		 * }else { result.setFlow("0"); } }else { result.setCount("0");
		 * result.setFlow("0"); }
		 */
		return recordAndFlow;
	}

	/**
	 * 获取自动匹配和需要新增信息
	 * 
	 * @param time
	 * @param area
	 * @param gender
	 * @return
	 */
	public Map<String, List<OperationsCenter>> getMatchAndAdd(String time) {
		List<String> typeList = new ArrayList<String>();
		typeList.add("0");
		typeList.add("1");
		typeList.add("2");
		List<OperationsCenter> autoList = operationsCenterMapper.selectAutomaticMatching(time, typeList);
		List<OperationsCenter> needList = operationsCenterMapper.selectNeedAdd(time, typeList);
		Map<String, List<OperationsCenter>> result = new HashMap<String, List<OperationsCenter>>();
		result.put("auto", autoList);
		result.put("need", needList);
		return result;
	}

	/**
	 * 获取行业稽核数量
	 * 
	 * @return
	 */
	public Map<String, Object> getInNum(String time) {

		Map<String, Object> result = operationsCenterMapper.getInNum(time);

		return result;
	}

	/**
	 * 获取URL模糊识别数量
	 * 
	 * @return
	 */
	public Map<String, Object> getUrlNum(String date_id) {

		Map<String, Object> result = operationsCenterMapper.getUrlNum(date_id);

		return result;
	}
	/**
	 * 获取内容标签稽核数量
	 * 
	 * @return
	 */
	public Map<String, Object> substanceNum(String time) {

		Map<String, Object> result = operationsCenterMapper.substanceNum(time);

		return result;
	}

	public int updateExport() {

		return operationsCenterMapper.updateExport();
	}
	public List<IncreaseProdName> getExportList() {

		return operationsCenterMapper.getExportList();
	}
}
