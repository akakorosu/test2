package com.bonc.dpi.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.IncreaseProdName;
import com.bonc.dpi.dao.entity.OperationsCenter;

public interface OperationsCenterMapper {
	//查询稽核错误数量
	String selectAuditErrorCount(String time);
	//查询未识别UA数量
	String selectUnidentifiedUaCount(String time);
	//查询一级域名识别数量
	String selectTopLevelDomainCount(String time);
	//查询总记录和总流量 
	OperationsCenter selectRecordAndFlowTotal(String time);
	//查询记录和流量 
	OperationsCenter selectRecordAndFlow(String time);
	//查询自动匹配
	List<OperationsCenter> selectAutomaticMatching(@Param("time")String time,@Param("typeList")List<String> typeList);
	//查询需要新增
	List<OperationsCenter> selectNeedAdd(@Param("time")String time,@Param("typeList")List<String> typeList);
	//获取行业稽核数量
	Map<String,Object> getInNum(@Param("time")String time);
	//获取Url模糊识别数量
	Map<String,Object> getUrlNum(@Param("date_id")String date_id);
	//获取内容标签稽核数量
	Map<String,Object> substanceNum(@Param("time")String time);
	//更新导出表的状态
	int updateExport();
	//查数据
	List<IncreaseProdName> getExportList();
}
