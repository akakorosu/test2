package com.bonc.dpi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.AuditRuleError;
import com.bonc.dpi.dao.entity.UrlFuzzyRecognition;
import com.bonc.dpi.dao.mapper.AuditRuleErrorMapper;
import com.bonc.dpi.dao.mapper.UrlFuzzyRecognitionMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class UrlFuzzyRecognitionService {
	@Autowired
	private UrlFuzzyRecognitionMapper mapper;
	

	public Page<UrlFuzzyRecognition> selectList(UrlFuzzyRecognition vo, Integer page, Integer row) throws Exception {
		PageHelper.startPage(page, row);
		Page<UrlFuzzyRecognition> pageList = (Page<UrlFuzzyRecognition>) mapper.selectList(vo);
		int num = 1 + (page - 1) * row;
		for (UrlFuzzyRecognition rowNum : pageList) {
			rowNum.setRowNum(num++);
		}
		return pageList;
	}

	public List<Map<String, String>> getTableName() throws Exception {

		return mapper.getTableName();
	}

	public List<Map<String, Object>> checkDomainRule(String domainCode) throws Exception {

		return mapper.checkDomainRule(domainCode);
	}

	public int updateUrl(UrlFuzzyRecognition vo) throws Exception {

		return mapper.updateUrl(vo);
	}
	public int insertIntoExportTable(UrlFuzzyRecognition vo) throws Exception {

		return mapper.insertIntoExportTable(vo);
	}
}
