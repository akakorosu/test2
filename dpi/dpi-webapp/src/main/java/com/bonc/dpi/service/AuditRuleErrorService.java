package com.bonc.dpi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.AuditRuleError;
import com.bonc.dpi.dao.entity.IncreaseProdName;
import com.bonc.dpi.dao.mapper.AuditRuleErrorMapper;
import com.bonc.dpi.utils.DpiUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuditRuleErrorService {
	@Autowired
	private AuditRuleErrorMapper mapper;
	

	public Page<AuditRuleError> selectList(AuditRuleError vo, Integer page, Integer row) throws Exception{
		PageHelper.startPage(page, row);
		Page<AuditRuleError> pageList = (Page<AuditRuleError>) mapper.selectList(vo);
		int num = 1 +(page-1)* row;
		for (AuditRuleError rowNum :pageList) {
			rowNum.setProdName(DpiUtils.strDecrypt(rowNum.getProdName()));
			rowNum.setRowNums(num++);
		}
		return pageList;
	}
	public List<Map<String,String>> getTableName() throws Exception{
		
		return mapper.getTableName();
	}
	public List<AuditRuleError> getExportList(AuditRuleError  vo) {
		List<AuditRuleError> list=mapper.getExportList(vo);
		for (AuditRuleError rowNum :list) {
			rowNum.setProdName(DpiUtils.strDecrypt(rowNum.getProdName()));
			
		}
		return list;
	}
}
