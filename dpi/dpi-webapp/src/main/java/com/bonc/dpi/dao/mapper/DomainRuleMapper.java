package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.OperationFlow;


/**
 * 域名表
 * DIM_IA_DOMAIN_RULE
 * @author BONC-XUXL
 *
 */
public interface DomainRuleMapper {
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = DomainRuleMapperSql.class, method = "selectDataNum")
	Integer selectDataNum (DomainRule vo);
	
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = DomainRuleMapperSql.class, method = "selectList")
	List<DomainRule> selectList (DomainRule vo);
	
	
	@SelectProvider(type = DomainRuleMapperSql.class, method = "selectVoByProdId")
	List<DomainRule> selectVoByProdId (DomainRule vo);
	
	
	@SelectProvider(type = DomainRuleMapperSql.class, method = "selectListByProdName")
	List<DomainRule> selectListByProdName (DomainRule vo);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	@InsertProvider(type = DomainRuleMapperSql.class, method = "insertVo")
	Boolean insertVo(DomainRule vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = DomainRuleMapperSql.class, method = "updateVo")
	Boolean updateVo(DomainRule vo);

	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = DomainRuleMapperSql.class, method = "selectVoByPrimaryKey")
	DomainRule selectVoByPrimaryKey(DomainRule vo);
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = DomainRuleMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(DomainRule vo);
	
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = DomainRuleMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("list") List<OperationFlow> list);
}
