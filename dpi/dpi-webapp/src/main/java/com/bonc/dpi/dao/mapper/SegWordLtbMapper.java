package com.bonc.dpi.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.GroupClass;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.SegWordLtb;


public interface SegWordLtbMapper {

	/**
	 * 查询数量
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = SegWordLtbMapperSql.class, method = "selectDataNum")
	Integer selectDataNum (SegWordLtb vo);
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = SegWordLtbMapperSql.class, method = "selectList")
	List<SegWordLtb> selectList (SegWordLtb vo);
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	@SelectProvider(type = SegWordLtbMapperSql.class, method = "selectVoById")
	SegWordLtb selectVoById(String id);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	@InsertProvider(type = SegWordLtbMapperSql.class, method = "insertVo")
	Boolean insertVo(SegWordLtb vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = SegWordLtbMapperSql.class, method = "updateVo")
	Boolean updateVo(SegWordLtb vo);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = SegWordLtbMapperSql.class, method = "deleteVoById")
	Boolean deleteVoById(String id);
	
	/**
	 * 重复校验
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = SegWordLtbMapperSql.class, method = "selectCheck")
	Integer selectCheck(SegWordLtb vo);
	/**
	 * 查询VO(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = SegWordLtbMapperSql.class, method = "selectVoByPrimaryKey")
	SegWordLtb selectVoByPrimaryKey(SegWordLtb vo);
	
	/**
	 * 删除VO(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = SegWordLtbMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(SegWordLtb vo);
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = SegWordLtbMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("list") List<OperationFlow> list);
}
