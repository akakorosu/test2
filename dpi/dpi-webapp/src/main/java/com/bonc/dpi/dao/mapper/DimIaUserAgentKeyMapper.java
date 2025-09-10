package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.DimIaUserAgentKey;
import com.bonc.dpi.dao.entity.OperationFlow;


public interface DimIaUserAgentKeyMapper {
	
	Integer selectDataNum(DimIaUserAgentKey key);
	
	List<DimIaUserAgentKey> selectList (DimIaUserAgentKey key);
	
	List<DimIaUserAgentKey> selectListByPar (DimIaUserAgentKey df);

	DimIaUserAgentKey selectKeyById(String id);
	
	void delete(DimIaUserAgentKey bl);
	
	void insertKey(DimIaUserAgentKey dimIaUserAgentKey);
	
	int updateUa(DimIaUserAgentKey dimIaUserAgentKey);
	
	void updateKey(DimIaUserAgentKey dimIaUserAgentKey);
	
	Integer check(DimIaUserAgentKey dimIaUserAgentKey);
	
	Integer checkId(DimIaUserAgentKey dimIaUserAgentKey);
	
	DimIaUserAgentKey selectVoByPrimaryKey(DimIaUserAgentKey voParam);
	
	DimIaUserAgentKey deleteVoByPrimaryKey(DimIaUserAgentKey voParam);
	
	Boolean batchInsert(@Param("list")List<OperationFlow> keyList,@Param("database_type")String database_type);
}
