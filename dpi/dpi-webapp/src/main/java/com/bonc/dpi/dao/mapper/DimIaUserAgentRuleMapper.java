package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.DimIaUserAgentKey;
import com.bonc.dpi.dao.entity.DimIaUserAgentNoise;
import com.bonc.dpi.dao.entity.DimIaUserAgentRule;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.system.dao.entity.SysUser;


public interface DimIaUserAgentRuleMapper {
	
	Integer selectDataNum(DimIaUserAgentRule rule);

	List<DimIaUserAgentRule> selectList (DimIaUserAgentRule rule);
	
	List<DimIaUserAgentRule> selectListByPar (DimIaUserAgentRule df);
	
	DimIaUserAgentRule selectById(String id);
	
	void delete(DimIaUserAgentRule bl); 
	
	void insert(DimIaUserAgentRule bl);
	
	int  updateUaRule(DimIaUserAgentRule bl);
	
	void update(DimIaUserAgentRule bl);
	
	Integer check(DimIaUserAgentRule bl);
	
	Integer checkId(DimIaUserAgentRule bl);
	
	DimIaUserAgentRule selectVoByPrimaryKey(DimIaUserAgentRule voParam);
	
	DimIaUserAgentRule deleteVoByPrimaryKey(DimIaUserAgentRule voParam);
	
	Boolean batchInsert(@Param("list")List<OperationFlow> bl,@Param("database_type")String database_type);
}
