package com.bonc.dpi.dao.mapper;

import com.bonc.system.dao.entity.SysUser;


public class PublicCPeriodMapperSql {
	
	public String selectList(SysUser sysUser) {
		String sql = this.getSql();
		return sql;
	}
	
	public String selectByFunctionId(String functionId) {
		String sql = this.getSql() + " and function_id=#{functionId} ";
		return sql;
	}
	
	private String getSql() {
		String sql = "select t.function_id functionId, t.function_name functionName, t.date_id dateId from PUBLIC_C_PERIOD t where 1=1 ";
		return sql;
	}
}
