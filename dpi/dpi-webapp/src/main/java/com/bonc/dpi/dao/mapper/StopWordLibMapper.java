package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.StopWordLib;

/**
 * 分类词库
 * dim_ia_cata_word_ltb
 * @author BONC-XUXL
 *
 */
public interface StopWordLibMapper {
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = StopWordLibMapperSql.class, method = "selectDataNum")
	Integer selectDataNum (StopWordLib vo);

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = StopWordLibMapperSql.class, method = "selectList")
	List<StopWordLib> selectList (StopWordLib vo);
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	@SelectProvider(type = StopWordLibMapperSql.class, method = "selectVoById")
	StopWordLib selectVoById(String id);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	@InsertProvider(type = StopWordLibMapperSql.class, method = "insertVo")
	Boolean insertVo(StopWordLib vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = StopWordLibMapperSql.class, method = "updateVo")
	Boolean updateVo(StopWordLib vo);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = StopWordLibMapperSql.class, method = "deleteVoById")
	Boolean deleteVoById(String id);
	
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = StopWordLibMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(StopWordLib vo);
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = StopWordLibMapperSql.class, method = "selectVoByPrimaryKey")
	StopWordLib selectVoByPrimaryKey(StopWordLib vo);
	
	/**
	 * 重复校验
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = StopWordLibMapperSql.class, method = "selectCheck")
	Integer selectCheck(StopWordLib vo);
	
	
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = StopWordLibMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("list") List<OperationFlow> list);
}
