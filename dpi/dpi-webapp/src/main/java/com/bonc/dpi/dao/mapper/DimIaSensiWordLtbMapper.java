package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.DimIaSensiWordLtb;
import com.bonc.dpi.dao.entity.DimIaUserAgentKey;
import com.bonc.dpi.dao.entity.DimIaUserAgentNoise;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;


public interface DimIaSensiWordLtbMapper {
	Integer selectDataNum(DimIaSensiWordLtb sensi);
	
	List<DimIaSensiWordLtb> selectList (DimIaSensiWordLtb sensi);
	
	DimIaSensiWordLtb selectSensiById(String id);
	
	void delete(DimIaSensiWordLtb sensi);
	
	void insertSensi(DimIaSensiWordLtb sensi);
	
	void updateSensi(DimIaSensiWordLtb sensi);

	Integer check(DimIaSensiWordLtb sensi);
	
	DimIaSensiWordLtb selectVoByPrimaryKey(DimIaSensiWordLtb voParam);
	
	DimIaSensiWordLtb deleteVoByPrimaryKey(DimIaSensiWordLtb voParam);

	Boolean batchInsert( @Param("list")List<OperationFlow> listInsert,@Param("database_type")String database_type);
}
