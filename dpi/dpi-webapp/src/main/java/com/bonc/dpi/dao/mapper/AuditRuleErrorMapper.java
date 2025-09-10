package com.bonc.dpi.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.AuditRuleError;
import com.bonc.dpi.dao.entity.IncreaseProdName;

public interface AuditRuleErrorMapper {

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<AuditRuleError> selectList (AuditRuleError vo);
	
	/**
	 *查询tablename
	 */
	List<Map<String,String>> getTableName ();
	List<AuditRuleError> getExportList(AuditRuleError vo);
	
}
