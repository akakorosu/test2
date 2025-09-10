package com.bonc.datasource.mpp.system;

import com.bonc.system.dao.entity.LogEntity;

public interface SystemDao {
	
	public int insertLog(LogEntity entity);
	
	public int insertLogReportSecond(LogEntity entity);
	
}
