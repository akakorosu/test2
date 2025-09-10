package com.bonc.activiti.dao.mapper;

import java.util.List;
import java.util.Map;

public interface ActivitiMapperExtend {
	/**
	 * 
	 * @return
	 */
    List<Map<String,String>> selectAllApproval(Map<String,String> record);
    List<Map<String,String>> selectAllStartBy(Map<String,String> record);
    
    List<Map<String,String>> checkTask(Map<String,String> record);
}
