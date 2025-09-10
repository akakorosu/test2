package com.bonc.dpi.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.KeywordSearchRule;
import com.bonc.dpi.dao.entity.OperationFlow;

/**
 * 关键字规则接口
 * dim_ia_keyword_search_rule
 * @author BONC-XUXL
 *
 */
public interface KeywordSearchRuleMapper {
	
	@SelectProvider(type = KeywordSearchRuleMapperSql.class, method = "selectListSimple")
	List<KeywordSearchRule> selectListSimple(KeywordSearchRule vo);
	
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = KeywordSearchRuleMapperSql.class, method = "selectDataNum")
	Integer selectDataNum (KeywordSearchRule vo);
	
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = KeywordSearchRuleMapperSql.class, method = "selectList")
	List<KeywordSearchRule> selectList (KeywordSearchRule vo);
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	@SelectProvider(type = KeywordSearchRuleMapperSql.class, method = "selectVoById")
	KeywordSearchRule selectVoById(String id);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	@InsertProvider(type = KeywordSearchRuleMapperSql.class, method = "insertVo")
	Boolean insertVo(KeywordSearchRule vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = KeywordSearchRuleMapperSql.class, method = "updateVo")
	Boolean updateVo(KeywordSearchRule vo);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = KeywordSearchRuleMapperSql.class, method = "deleteVoById")
	Boolean deleteVoById(String id);
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = KeywordSearchRuleMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(KeywordSearchRule vo);
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = KeywordSearchRuleMapperSql.class, method = "selectVoByPrimaryKey")
	KeywordSearchRule selectVoByPrimaryKey(KeywordSearchRule vo);
	
	
	/**
	 * 重复校验
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = KeywordSearchRuleMapperSql.class, method = "checkRepeat")
	Integer checkRepeat(KeywordSearchRule vo);
	
	/**
	 * 校验groupTypes
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = KeywordSearchRuleMapperSql.class, method = "checkGroupType")
	Integer checkGroupType(KeywordSearchRule vo);
	
	/**
	 * 校验groupName
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = KeywordSearchRuleMapperSql.class, method = "checkGroupName")
	Integer checkGroupName(KeywordSearchRule vo);
	
	/**
	 * 不可为空，prod_id必须在产品表里
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = KeywordSearchRuleMapperSql.class, method = "prodIdCheck")
	Integer prodIdCheck(KeywordSearchRule vo);
	
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = KeywordSearchRuleMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("map")Map map);

	/**
	 * 查询num最大值
	 * @return
	 */
	@SelectProvider(type = KeywordSearchRuleMapperSql.class, method = "selectMaxNum")
	Integer selectMaxNum();
    
	/**
	 * 根据rule_type统计个数
	 * @param list
	 * @return
	 */
	@SelectProvider(type = KeywordSearchRuleMapperSql.class, method = "getNumByRuleType")
    List<Integer> getNumByRuleType();
}
