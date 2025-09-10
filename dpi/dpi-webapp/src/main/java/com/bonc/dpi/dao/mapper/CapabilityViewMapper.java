package com.bonc.dpi.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.CapabilityView;
import com.bonc.dpi.dao.entity.ProductFbDescInfo;
import com.bonc.dpi.dao.entity.ProductInfo;


public interface CapabilityViewMapper {
	/**
	 * 查询分类信息
	 * @return
	 */
	Map<String, String> getLabelNum();
	
	
	List<String> getLabelLvl1();
	
	List<String> getModelWorkLvl1();
	
	List<CapabilityView> getLabelLvl2(String type);
	
	List<CapabilityView> selectList(CapabilityView vo);
	
	List<CapabilityView> getAllAct();
	
	List<CapabilityView> getAllLabelIn(String code);
	
	int countAllDir(CapabilityView vo);
	
	CapabilityView getDireDetail(CapabilityView vo);
	
	
	List<CapabilityView> getListByPage(CapabilityView vo);
	
	
	List<CapabilityView> updateAct(CapabilityView vo);
	
	List<CapabilityView> getWorkLvl2(@Param(value = "type")String type);
	
	List<CapabilityView> getChild();
	
	List<CapabilityView> getLabelLvl3(@Param(value = "type") String type,@Param(value = "type2")String type2);
	
	List<CapabilityView> getContentKuLabel();
	
	//List<CapabilityView> getContentKuLabel();
	
	List<CapabilityView> getContentKuProdName(@Param(value = "groupType") String groupType);
	
	List<CapabilityView> getContentKuAttr(@Param(value = "contentType") String contentType);
	
	List<CapabilityView> getModelInfo(@Param(value = "type") String type);
	
	List<CapabilityView> getLabelProd(CapabilityView vo);
	
	List<CapabilityView> getLoadProd();
	
	List<ProductFbDescInfo> getProductFbDescInfo(ProductFbDescInfo param);
	
	Boolean publishById(ProductFbDescInfo vo);

	int publishAuth(String loginId);
	
}
