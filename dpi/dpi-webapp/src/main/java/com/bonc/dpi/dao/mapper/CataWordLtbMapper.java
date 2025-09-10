package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.CataWordLtb;
import com.bonc.dpi.dao.entity.OperationFlow;

/**
 * 分类词库
 * dim_ia_cata_word_ltb
 * @author BONC-XUXL
 *
 */
public interface CataWordLtbMapper {
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = CataWordLtbMapperSql.class, method = "selectDataNum")
	Integer selectDataNum (CataWordLtb vo);

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = CataWordLtbMapperSql.class, method = "selectList")
	List<CataWordLtb> selectList (CataWordLtb vo);
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	@SelectProvider(type = CataWordLtbMapperSql.class, method = "selectVoById")
	CataWordLtb selectVoById(String id);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	@InsertProvider(type = CataWordLtbMapperSql.class, method = "insertVo")
	Boolean insertVo(CataWordLtb vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = CataWordLtbMapperSql.class, method = "updateVo")
	Boolean updateVo(CataWordLtb vo);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = CataWordLtbMapperSql.class, method = "deleteVoById")
	Boolean deleteVoById(String id);
	
	/**
	 *联合主键校验
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = CataWordLtbMapperSql.class, method = "selectCheck")
	Integer selectCheck(CataWordLtb vo);
	
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = CataWordLtbMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(CataWordLtb vo);
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = CataWordLtbMapperSql.class, method = "selectVoByPrimaryKey")
	CataWordLtb selectVoByPrimaryKey(CataWordLtb vo);
	
	
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = CataWordLtbMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("list") List<OperationFlow> list);
}
