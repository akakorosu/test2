package com.bonc.dpi.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.AuditRuleError;
import com.bonc.dpi.dao.entity.SubstanceLabel;

public interface SubstanceLabelMapper {

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<SubstanceLabel> selectList(SubstanceLabel vo);
	
	/**
	 *查询tablename
	 */
	List<Map<String,String>> getTableName();
	/**
	 *查询tablename
	 */
	List<Map<String,String>> getGroupType();
	List<SubstanceLabel> getProdIdAndName(SubstanceLabel vo);
	
	List<SubstanceLabel> getInitProdName(SubstanceLabel vo);
	
	List<SubstanceLabel> getCatContentType(SubstanceLabel vo);
	
	int delContent(SubstanceLabel vo);
	
	int checkContent(SubstanceLabel vo);
	
	SubstanceLabel getProdNameById(SubstanceLabel vo);
	
	int countData(SubstanceLabel vo);
	
	int updateContentLabelCode(SubstanceLabel vo);
	
	int insertContentLabelCode(SubstanceLabel vo);
	
	List<SubstanceLabel> comLikeSearch(SubstanceLabel vo);
	
	SubstanceLabel selectVoById(SubstanceLabel vo);
	
}
