package com.bonc.dpi.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.IndustryLabelAudit;
import com.bonc.dpi.dao.mapper.IndustryLabelAuditMapper;
import com.bonc.dpi.utils.DpiUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class IndustryLabelAuditService {
	@Autowired
	private IndustryLabelAuditMapper mapper;
	

	public Page<IndustryLabelAudit> selectList(IndustryLabelAudit vo,Integer page, Integer row) throws Exception{
		PageHelper.startPage(page, row);
		Page<IndustryLabelAudit> pageList = (Page<IndustryLabelAudit>) mapper.selectList(vo);
		int num = 1 +(page-1)* row;
		for (IndustryLabelAudit rowNum :pageList) {
			 rowNum.setProdName(DpiUtils.strDecrypt(rowNum.getProdName()));
			rowNum.setRowNums(num++);
		}
		return pageList;
	}
	public int updateLabelCheck(String  prodId) {
		return mapper.updateLabelCheck(prodId);
	}
	public int updateLabel2(IndustryLabelAudit vo) {
		return mapper.updateLabel2(vo);
	}
	public IndustryLabelAudit getLabel1ByLabel2(IndustryLabelAudit vo) {
		return mapper.getLabel1ByLabel2(vo);
	}
	public int updateLabel1(IndustryLabelAudit vo) {
		return mapper.updateLabel1(vo);
	}
	public int updateLabel2ProdInfo(IndustryLabelAudit vo) {
		return mapper.updateLabel2ProdInfo(vo);
	}
	public int updateLabel1ProdInfo(IndustryLabelAudit vo) {
		return mapper.updateLabel1ProdInfo(vo);
	}
	public int updateProdInfoCheck(String  prodId) {
		return mapper.updateProdInfoCheck(prodId);
	}
	public int batchUpdateLabelCheck(Map<String, Object> params ) {
		return mapper.batchUpdateLabelCheck(params);
	}
	public int batchUpdateProdInfoCheck(Map<String, Object> params ) {
		return mapper.batchUpdateProdInfoCheck(params);
	}
	public IndustryLabelAudit selectVoById(String prodId ,String monthId) {
		return mapper.selectVoById(prodId,monthId);
	}
	
}
