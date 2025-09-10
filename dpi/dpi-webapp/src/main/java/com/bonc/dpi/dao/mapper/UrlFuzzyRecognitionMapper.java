package com.bonc.dpi.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.AuditRuleError;
import com.bonc.dpi.dao.entity.UrlFuzzyRecognition;

public interface UrlFuzzyRecognitionMapper {

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<UrlFuzzyRecognition> selectList (UrlFuzzyRecognition vo);
	
	
	/**
	 *查询tablename
	 */
	List<Map<String,String>> getTableName ();
	
	List<Map<String,Object>> checkDomainRule (@Param(value = "domainCode")String domainCode);
	
	int updateUrl (UrlFuzzyRecognition vo);
	
	int insertIntoExportTable (UrlFuzzyRecognition vo);
	
}
