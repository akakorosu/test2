package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.DimIaIpPortDynamic;
import com.bonc.dpi.dao.entity.GroupClass;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.ProductInfo;


public interface GroupClassMapper {
	
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = GroupClassMapperSql.class, method = "selectDataNum")
	Integer selectDataNum (GroupClass vo);
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = GroupClassMapperSql.class, method = "selectList")
	List<GroupClass> selectList (GroupClass vo);
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	@SelectProvider(type = GroupClassMapperSql.class, method = "selectVoById")
	GroupClass selectVoById(String id);
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	@SelectProvider(type = GroupClassMapperSql.class, method = "selectVoByIdWithProdName")
	GroupClass selectVoByIdWithProdName(String id);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	@InsertProvider(type = GroupClassMapperSql.class, method = "insertVo")
	Boolean insertVo(GroupClass vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = GroupClassMapperSql.class, method = "updateVo")
	Boolean updateVo(GroupClass vo);
	/**
	 * 验证数据的唯一性
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = GroupClassMapperSql.class, method = "checkUnique")
	Integer checkUnique(GroupClass vo);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = GroupClassMapperSql.class, method = "deleteVoById")
	Boolean deleteVoById(String id);
	
	/**
	 * 重复校验
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = GroupClassMapperSql.class, method = "selectCheck")
	Integer selectCheck(GroupClass vo);
	/**
	 * 查询VO(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = GroupClassMapperSql.class, method = "selectVoByPrimaryKey")
	GroupClass selectVoByPrimaryKey(GroupClass vo);
	
	/**
	 * 删除VO(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = GroupClassMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(GroupClass vo);

	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = GroupClassMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("list") List<OperationFlow> list);
    /**
	 * 根据产品Id模糊查询
	 * @param prodName
	 * @return
	 */
	@SelectProvider(type = GroupClassMapperSql.class, method = "getProdIdListByProdId")
	List<GroupClass> getProdIdListByProdId (GroupClass vo);
	/**
	 * 根据产品名称模糊查询
	 * @param prodName
	 * @return
	 */
	@SelectProvider(type = GroupClassMapperSql.class, method = "getProdIdListByProdName")
	List<GroupClass> getProdIdListByProdName (GroupClass vo);
}
