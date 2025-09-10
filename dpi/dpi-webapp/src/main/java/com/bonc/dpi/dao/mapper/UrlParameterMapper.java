package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.UrlParameter;

/**
 * url参数规则接口
 * dim_ia_url_parameter
 * @author BONC-XUXL
 *
 */
public interface UrlParameterMapper {
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = UrlParameterMapperSql.class, method = "selectDataNum")
	Integer selectDataNum (UrlParameter vo);
	
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = UrlParameterMapperSql.class, method = "selectList")
	List<UrlParameter> selectList (UrlParameter vo);
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	@SelectProvider(type = UrlParameterMapperSql.class, method = "selectVoById")
	UrlParameter selectVoById(String id);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	@InsertProvider(type = UrlParameterMapperSql.class, method = "insertVo")
	Boolean insertVo(UrlParameter vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = UrlParameterMapperSql.class, method = "updateVo")
	Boolean updateVo(UrlParameter vo);

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = UrlParameterMapperSql.class, method = "deleteVoById")
	Boolean deleteVoById(String id);
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = UrlParameterMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(UrlParameter vo);
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = UrlParameterMapperSql.class, method = "selectVoByPrimaryKey")
	UrlParameter selectVoByPrimaryKey(UrlParameter vo);
		
	
	/**
	 * 重复校验
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = UrlParameterMapperSql.class, method = "selectCheck")
	Integer selectCheck(UrlParameter vo);
	
	/**
	 * 不可为空，prod_id必须在产品表里
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = UrlParameterMapperSql.class, method = "prodIdCheck")
	Integer prodIdCheck(UrlParameter vo);
	
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = UrlParameterMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("list") List<OperationFlow> list);
}
