package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.UrlPath;

/**
 * url路径规则表
 * dim_ia_url_path
 * @author BONC-XUXL
 *
 */
public interface UrlPathMapper {
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = UrlPathMapperSql.class, method = "selectDataNum")
	Integer selectDataNum (UrlPath vo);

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = UrlPathMapperSql.class, method = "selectList")
	List<UrlPath> selectList (UrlPath vo);
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	@SelectProvider(type = UrlPathMapperSql.class, method = "selectVoById")
	UrlPath selectVoById(String id);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = UrlPathMapperSql.class, method = "deleteVoById")
	Boolean deleteVoById(String id);
	
	/**
	 * 删除VO(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = UrlPathMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(UrlPath vo);

	/**
	 * 查询VO(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = UrlPathMapperSql.class, method = "selectVoByPrimaryKey")
	UrlPath selectVoByPrimaryKey(UrlPath vo);

	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	@InsertProvider(type = UrlPathMapperSql.class, method = "insertVo")
	Boolean insertVo(UrlPath vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = UrlPathMapperSql.class, method = "updateVo")
	Boolean updateVo(UrlPath vo);

	/**
	 * 重复校验
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = UrlPathMapperSql.class, method = "selectCheck")
	Integer selectCheck(UrlPath vo);
	
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = UrlPathMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("list") List<OperationFlow> list);
}
