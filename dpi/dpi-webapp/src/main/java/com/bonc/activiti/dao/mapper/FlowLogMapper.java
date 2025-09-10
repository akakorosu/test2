package com.bonc.activiti.dao.mapper;

import java.util.List;

import com.bonc.activiti.dao.entity.FlowLog;

public interface FlowLogMapper {
	
    int insert(FlowLog record);

    int deleteByPrimaryKey(FlowLog record);
    
    int deleteByIdFlowId(FlowLog record);
    
    List<FlowLog> selectByPrimaryKey(FlowLog record);
    
    List<FlowLog> selectByPrimaryKeyUserId(FlowLog record);
    
    int updateApiFlowState(FlowLog record);
}