package com.bonc.dpi.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationFlowImportReturn;
import com.bonc.dpi.dao.entity.SegWordLtb;
import com.bonc.dpi.dao.entity.TopLevelDomain;
import com.bonc.dpi.dao.mapper.TopLevelDomainMapper;
import com.bonc.dpi.service.i.OperationFlowServiceI;
import com.bonc.dpi.utils.DpiUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class TopLevelDomainService implements OperationFlowServiceI{
	@Autowired
	private TopLevelDomainMapper mapper;
	
	public Page<TopLevelDomain> selectList(TopLevelDomain vo, Integer page, Integer row){
		PageHelper.startPage(page, row);
		Page<TopLevelDomain> pageList = (Page<TopLevelDomain>) mapper.selectList(vo);
		int num = 1 +(page-1)* row;
		for (TopLevelDomain rowNum :pageList) {
			/*if(rowNum!=null && rowNum.getFlow()!=null && rowNum.getUserCount()!=null && rowNum.getFlow()!="" && rowNum.getUserCount()!="") {
				double a=Double.parseDouble(rowNum.getFlow())/Integer.parseInt(rowNum.getUserCount());
			    rowNum.setFlowCount(String.format("%.2f", a));
			}*/
			rowNum.setProdName(DpiUtils.strDecrypt(rowNum.getProdName()));
			rowNum.setRowNums(num++);
		}
		return pageList;
	}
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public TopLevelDomain selectVoById(String host,String monthId){
		return mapper.selectVoById(host,monthId);
	}
	
	/**
	 * 添加
	 * @param vo
	 */
	public TopLevelDomain insertVo(TopLevelDomain vo){
		Boolean bl = mapper.insertVo(vo);
		return vo;
	}
	
	/**
	 * 修改
	 * @param vo
	 * @return 
	 */
	public TopLevelDomain updateVo(TopLevelDomain vo){
		Boolean bl = mapper.updateVo(vo);
		return vo;
	}
	public int updateTopDomain(TopLevelDomain vo){
	
		return mapper.updateTopDomain(vo);
	}
	public int topLvlDomainCheck2(TopLevelDomain vo){
		
		return mapper.topLvlDomainCheck2(vo);
	}
	public int updateDomainCode(TopLevelDomain vo){
		
		return mapper.updateDomainCode(vo);
	}
	public int checkDomainCode(TopLevelDomain vo){
		
		return mapper.checkDomainCode(vo);
	}
	public int updateOrInsertDomain(TopLevelDomain vo){
		
		return mapper.updateOrInsertDomain(vo);
	}
	/**
	 * 导出
	 */
	@Override
	public OperationFlowExportReturn doExport(OperationFlow o) throws Exception {
		TopLevelDomain vo = (TopLevelDomain) o;
		List<TopLevelDomain> list = this.mapper.selectListNotProdId(vo);
		OperationFlowExportReturn ofer = new OperationFlowExportReturn();
		ofer.setList(list);
		return ofer;
	}
	@Override
	public OperationFlowImportReturn doImport(List<OperationFlow> list,String type) throws Exception {	
		return null;
	}
	@Override
	public Integer exportDataNum(OperationFlow o,String type) throws Exception {
		TopLevelDomain vo = (TopLevelDomain) o;
		Integer num = mapper.selectDataNum(vo);
		
		return num;
	}
}
