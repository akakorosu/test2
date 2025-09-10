package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import com.bonc.dpi.dao.entity.DimIaIpPortDynamic;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.ProductInfo;


public interface DimIaIpPortDynamicMapper {
	
	Integer selectDataNum(DimIaIpPortDynamic obj);
	
	List<DimIaIpPortDynamic> selectList (DimIaIpPortDynamic bl);
	
	List<DimIaIpPortDynamic> selectListByPar (DimIaIpPortDynamic bl);
	
	DimIaIpPortDynamic selectById(String id);
	
	void delete(DimIaIpPortDynamic bl);
	
	void insert(DimIaIpPortDynamic bl);
	
	void update(DimIaIpPortDynamic bl);

	Integer check(DimIaIpPortDynamic bl);
	
	Integer checkId(DimIaIpPortDynamic bl);
	
    Boolean batchInsert( List<OperationFlow> bl);

    /**
	 * 根据产品Id模糊查询
	 * @param prodName
	 * @return
	 */
	List<ProductInfo> getProdIdListByProdId (DimIaIpPortDynamic ip);
    
	/**
	 * 根据产品名称模糊查询
	 * @param prodName
	 * @return
	 */
	List<ProductInfo> getProdIdListByProdName (DimIaIpPortDynamic ip);
	
	/**
	 * 查询VO(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = DimIaIpPortDynamicMapperSql.class, method = "selectVoByPrimaryKey")
	DimIaIpPortDynamic selectVoByPrimaryKey(DimIaIpPortDynamic vo);
	
	/**
	 * 删除VO(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = DimIaIpPortDynamicMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(DimIaIpPortDynamic vo);

	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = DimIaIpPortDynamicMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("list") List<OperationFlow> list);

}
