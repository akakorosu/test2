package com.bonc.dpi.dao.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.IndustryLabelAudit;

public interface IndustryLabelAuditMapper {

	/**
	 * 分页查询
	 * @param vo
	 * @return
	 */
	List<IndustryLabelAudit> selectList (IndustryLabelAudit vo);
	
	int updateLabelCheck (String prodId);
	
	int updateLabel2 (IndustryLabelAudit vo);
	
	IndustryLabelAudit getLabel1ByLabel2 (IndustryLabelAudit vo);
	
	int updateLabel1 (IndustryLabelAudit vo);
	
	int updateLabel2ProdInfo (IndustryLabelAudit vo);
	
	int updateLabel1ProdInfo (IndustryLabelAudit vo);
	
	int updateProdInfoCheck (String prodId);
	
	int batchUpdateLabelCheck (Map<String, Object> params);
	
	int batchUpdateProdInfoCheck (Map<String, Object> params);
	
	IndustryLabelAudit selectVoById (@Param(value = "prodId")String prodId,@Param(value = "monthId") String monthId);
	

}
