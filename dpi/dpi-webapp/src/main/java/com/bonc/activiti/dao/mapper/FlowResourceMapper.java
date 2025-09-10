package com.bonc.activiti.dao.mapper;

import java.util.List;

import com.bonc.activiti.dao.entity.FlowResource;

public interface FlowResourceMapper {
    Boolean deleteByPrimaryKey(String appid);

    Boolean insert(FlowResource record);

    FlowResource selectByPrimaryKey(String appid);

    List<FlowResource> selectAll();

    Boolean updateByPrimaryKey(FlowResource record);
    
    FlowResource selectByAppClassName(String appClassName);
    
    List<FlowResource> selectListByCondition(FlowResource flow);

	int isFlowNotExists(FlowResource flow);
}