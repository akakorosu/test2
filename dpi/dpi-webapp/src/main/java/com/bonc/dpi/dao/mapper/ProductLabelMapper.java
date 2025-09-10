package com.bonc.dpi.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.ProductLabel;

public interface ProductLabelMapper {
	
	@SelectProvider(type = ProductLabelMapperSql.class, method = "selectListDistinct")
	List<ProductLabel> selectListDistinct ();
		
	/**
	 * 查询数量
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = ProductLabelMapperSql.class, method = "selectDataNum")
	Integer selectDataNum (ProductLabel vo);
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = ProductLabelMapperSql.class, method = "selectList")
	List<ProductLabel> selectList (ProductLabel vo);
	
	/**
	 * 根据LabelCode查对象
	 * @param id
	 * @return
	 */
	@SelectProvider(type = ProductLabelMapperSql.class, method = "selectVoByLabelCode")
	ProductLabel selectVoByLabelCode(String labelCode);
	
	/**
	 * 根据id查对象
	 * @param id
	 * @return
	 */
	@SelectProvider(type = ProductLabelMapperSql.class, method = "selectVoById")
	ProductLabel selectVoById(String labelCode);
	
	/**
	 * 检查数据唯一
	 * @param id
	 * @return
	 */
	@SelectProvider(type = ProductLabelMapperSql.class, method = "checkData")
	Integer checkData(ProductLabel vo);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	@InsertProvider(type = ProductLabelMapperSql.class, method = "insertVo")
	Boolean insertVo(ProductLabel vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = ProductLabelMapperSql.class, method = "updateVo")
	Boolean updateVo(ProductLabel vo);
	
	@UpdateProvider(type = ProductLabelMapperSql.class, method = "updateContentLabelName")
	Boolean updateContentLabelName(ProductLabel vo);
	
	@SelectProvider(type = ProductLabelMapperSql.class, method = "selectLabelName1")
	ProductLabel selectLabelName1(ProductLabel vo);

	@SelectProvider(type = ProductLabelMapperSql.class, method = "selectMax")
	String selectMax(ProductLabel vo);


	@SelectProvider(type = ProductLabelMapperSql.class, method = "selectLetter")
	String selectLetter(String letter);

	@SelectProvider(type = ProductLabelMapperSql.class, method = "checkLabelCode")
	Integer checkLabelCode(ProductLabel vo);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = ProductLabelMapperSql.class, method = "deleteVoById")
	Boolean deleteVoById(ProductLabel vo);
	
	@DeleteProvider(type = ProductLabelMapperSql.class, method = "deleteVoByContentLabelName")
	Boolean deleteVoByContentLabelName(ProductLabel vo);
	
	/**
	 * 重复校验
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = ProductLabelMapperSql.class, method = "selectCheck")
	Integer selectCheck(ProductLabel vo);

	@SelectProvider(type = ProductLabelMapperSql.class, method = "selectCheckLabel1")
	List<ProductLabel> selectCheckLabel1(ProductLabel vo);
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = ProductLabelMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("list") List<OperationFlow> list);
    
    
    /**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = ProductLabelMapperSql.class, method = "selectVoByPrimaryKey")
	ProductLabel selectVoByPrimaryKey(ProductLabel vo);
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = ProductLabelMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(ProductLabel vo);
}
