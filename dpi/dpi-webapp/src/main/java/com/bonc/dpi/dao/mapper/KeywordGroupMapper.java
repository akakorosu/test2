package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.KeywordGroup;
import com.bonc.dpi.dao.entity.OperationFlow;

/**
 * 关键词规则分组
 * dim_ia_keyword_group
 * @author BONC-XUXL
 *
 */
public interface KeywordGroupMapper {
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = KeywordGroupMapperSql.class, method = "selectDataNum")
	Integer selectDataNum (KeywordGroup vo);

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = KeywordGroupMapperSql.class, method = "selectList")
	List<KeywordGroup> selectList (KeywordGroup vo);
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	@SelectProvider(type = KeywordGroupMapperSql.class, method = "selectVoById")
	KeywordGroup selectVoById(String id);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	@InsertProvider(type = KeywordGroupMapperSql.class, method = "insertVo")
	Boolean insertVo(KeywordGroup vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = KeywordGroupMapperSql.class, method = "updateVo")
	Boolean updateVo(KeywordGroup vo);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = KeywordGroupMapperSql.class, method = "deleteVoById")
	Boolean deleteVoById(String id);
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = KeywordGroupMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(KeywordGroup vo);
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = KeywordGroupMapperSql.class, method = "selectVoByPrimaryKey")
	KeywordGroup selectVoByPrimaryKey(KeywordGroup vo);
	
	
	/**
	 * 重复校验
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = KeywordGroupMapperSql.class, method = "selectCheck")
	Integer selectCheck(KeywordGroup vo);
	
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = KeywordGroupMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("list") List<OperationFlow> list);
    
	/**
	 * 根据分组编码模糊查询
	 * @param groupName
	 * @return
	 */
	@SelectProvider(type = KeywordGroupMapperSql.class, method = "getGroupTypeListByGroupType")
	List<KeywordGroup> getGroupTypeListByGroupType (String groupType);
    
	/**
	 * 根据分组名称模糊查询
	 * @param groupName
	 * @return
	 */
	@SelectProvider(type = KeywordGroupMapperSql.class, method = "getGroupTypeListByGroupName")
	List<KeywordGroup> getGroupTypeListByGroupName (String groupName);
}
