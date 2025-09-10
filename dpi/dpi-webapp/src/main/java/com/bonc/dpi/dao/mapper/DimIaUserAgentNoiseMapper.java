package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.DimIaUserAgentKey;
import com.bonc.dpi.dao.entity.DimIaUserAgentNoise;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.system.dao.entity.SysUser;


public interface DimIaUserAgentNoiseMapper {
	
	Integer selectDataNum(DimIaUserAgentNoise noise);
	
	List<DimIaUserAgentNoise> selectList (DimIaUserAgentNoise noise);
	
	DimIaUserAgentNoise selectNoiseById(String id);
	
	void delete(DimIaUserAgentNoise noise);
	
	void insertNoise(DimIaUserAgentNoise noise);
	
	int updateNoiseUa(DimIaUserAgentNoise noise);
	
	void updateNoise(DimIaUserAgentNoise noise);

	Integer check(DimIaUserAgentNoise noise);
	
	DimIaUserAgentNoise selectVoByPrimaryKey(DimIaUserAgentNoise voParam);
	
	DimIaUserAgentNoise deleteVoByPrimaryKey(DimIaUserAgentNoise voParam);
	
    Boolean batchInsert( @Param("list")List<OperationFlow> noiseList,@Param("database_type")String database_type);
}
