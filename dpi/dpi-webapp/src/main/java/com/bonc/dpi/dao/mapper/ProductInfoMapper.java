package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.ProductInfo;

/**
 * 产品表
 * dim_ia_product_info
 * @author BONC-XUXL
 *
 */
public interface ProductInfoMapper {
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = ProductInfoMapperSql.class, method = "selectDataNum")
	Integer selectDataNum (ProductInfo vo);

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = ProductInfoMapperSql.class, method = "selectList")
	List<ProductInfo> selectList (ProductInfo vo);
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	@InsertProvider(type = ProductInfoMapperSql.class, method = "insertVo")
	Boolean insertVo(ProductInfo vo);
	
	@InsertProvider(type = ProductInfoMapperSql.class, method = "insertVoJm")
	Boolean insertVoJm(ProductInfo vo);

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = ProductInfoMapperSql.class, method = "updateVo")
	Boolean updateVo(ProductInfo vo);
	
	@UpdateProvider(type = ProductInfoMapperSql.class, method = "updateVoJm")
	Boolean updateVoJm(ProductInfo vo);
	
	/**
	 * 批量修改
	 * @param vo
	 * @return
	 */
	@UpdateProvider(type = ProductInfoMapperSql.class, method = "updateVoPl")
	Boolean updateVoPl(@Param("list") List<ProductInfo> list);

	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByPrimaryKey")
	Boolean deleteVoByPrimaryKey(ProductInfo vo);
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByPrimaryKeyJm")
	Boolean deleteVoByPrimaryKeyJm(ProductInfo vo);
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByUrlPath")
	Boolean deleteVoByUrlPath(ProductInfo vo);
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByUrlParameter")
	Boolean deleteVoByUrlParameter(ProductInfo vo);
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByFuzzyRule")
	Boolean deleteVoByFuzzyRule(ProductInfo vo);
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByDomainRule")
	Boolean deleteVoByDomainRule(ProductInfo vo);
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByIpPort")
	Boolean deleteVoByIpPort(ProductInfo vo);
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByGroupClass")
	Boolean deleteVoByGroupClass(ProductInfo vo);
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByUseragentKey")
	Boolean deleteVoByUseragentKey(ProductInfo vo);
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByUseragentRule")
	Boolean deleteVoByUseragentRule(ProductInfo vo);
	/**
	 * 删除(根据主键) 域名表 
	 * @param id
	 * @return
	 */
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByPrimaryKeyDomain")
	Boolean deleteVoByPrimaryKeyDomain(String domainCode);
	
	@InsertProvider(type = ProductInfoMapperSql.class, method = "saveVoByPrimaryKeyDomain")
	Boolean saveVoByPrimaryKeyDomain(String domainCode);
	
	@DeleteProvider(type = ProductInfoMapperSql.class, method = "deleteVoByPrimaryKeyIp")
	Boolean deleteVoByPrimaryKeyIp(String domainCode);
	
	@InsertProvider(type = ProductInfoMapperSql.class, method = "saveVoByPrimaryKeyIp")
	Boolean saveVoByPrimaryKeyIp(String domainCode);
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = ProductInfoMapperSql.class, method = "selectVoByPrimaryKey")
	ProductInfo selectVoByPrimaryKey(ProductInfo vo);
	
	@SelectProvider(type = ProductInfoMapperSql.class, method = "selectRuleList")
	List<ProductInfo> selectRuleList(ProductInfo vo);
	
	@SelectProvider(type = ProductInfoMapperSql.class, method = "updateUrlPath")
	Object updateUrlPath(ProductInfo vo);
	
	@SelectProvider(type = ProductInfoMapperSql.class, method = "getAllProd")
	List<ProductInfo> getAllProd();
	@SelectProvider(type = ProductInfoMapperSql.class, method = "delAllProd")
	Integer delAllProd();
	
	@SelectProvider(type = ProductInfoMapperSql.class, method = "updateUrlParameter")
	Object updateUrlParameter(ProductInfo vo);
	@SelectProvider(type = ProductInfoMapperSql.class, method = "updateUrlFuzzyRule")
	Object updateUrlFuzzyRule(ProductInfo vo);
	@SelectProvider(type = ProductInfoMapperSql.class, method = "updateDomainRule")
	Object updateDomainRule(ProductInfo vo);
	@SelectProvider(type = ProductInfoMapperSql.class, method = "updateIpPort")
	Object updateIpPort(ProductInfo vo);
	@SelectProvider(type = ProductInfoMapperSql.class, method = "updateGroupClass")
	Object updateGroupClass(ProductInfo vo);
	@SelectProvider(type = ProductInfoMapperSql.class, method = "updateUseragentKey")
	Object updateUseragentKey(ProductInfo vo);
	@SelectProvider(type = ProductInfoMapperSql.class, method = "updateUseragentRule")
	Object updateUseragentRule(ProductInfo vo);
	@SelectProvider(type = ProductInfoMapperSql.class, method = "updateDimIaKeywordSearchRule")
	Object updateDimIaKeywordSearchRule(ProductInfo vo);
	
	/**
	 * 重复校验
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = ProductInfoMapperSql.class, method = "selectCheck")
	Integer selectCheck(ProductInfo vo);
	
	/**
	 * 不可为空，prod_id必须在产品表里
	 * @param vo
	 * @return
	 */
	@SelectProvider(type = ProductInfoMapperSql.class, method = "prodIdCheck")
	Integer prodIdCheck(ProductInfo vo);
	
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    @InsertProvider(type = ProductInfoMapperSql.class, method = "insertVoPl")
    Boolean insertVoPl(@Param("list") List<OperationFlow> list);
    
    @InsertProvider(type = ProductInfoMapperSql.class, method = "insertVoPlJm")
    Boolean insertVoPlJm(@Param("list") List<ProductInfo> list);
    
    
    @InsertProvider(type = ProductInfoMapperSql.class, method = "insertVoPlJmByone")
    Boolean insertVoPlJmByone(ProductInfo vo);
	/**
	 * 根据产品Id模糊查询
	 * @param prodName
	 * @return
	 */
	@SelectProvider(type = ProductInfoMapperSql.class, method = "getProdIdListByProdId")
	List<ProductInfo> getProdIdListByProdId (String prodId);
    
	/**
	 * 根据产品名称模糊查询
	 * @param prodName
	 * @return
	 */
	@SelectProvider(type = ProductInfoMapperSql.class, method = "getProdIdListByProdName")
	List<ProductInfo> getProdIdListByProdName (String prodName);
	
	/**
	 * 获取产品id最大的
	 * @param prodId_head :例如prodId='W12233' 则prodId_head='W'
	 * @return
	 */
	@SelectProvider(type = ProductInfoMapperSql.class, method = "getProdId_Max")
	String getProdId_Max(String prodId_head);
}
