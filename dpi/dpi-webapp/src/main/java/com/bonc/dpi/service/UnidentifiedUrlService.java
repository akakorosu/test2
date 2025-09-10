package com.bonc.dpi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.entity.UnidentifiedUrl;
import com.bonc.dpi.dao.mapper.UnidentifiedUrlMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class UnidentifiedUrlService {
	@Autowired
	private UnidentifiedUrlMapper mapper;
	

	public Page<UnidentifiedUrl> selectList(UnidentifiedUrl vo, Integer page, Integer row) throws Exception{
		PageHelper.startPage(page, row);
		Page<UnidentifiedUrl> pageList = (Page<UnidentifiedUrl>) mapper.selectList(vo);
		int num = 1 +(page-1)* row;
		for (UnidentifiedUrl rowNum :pageList) {
		    //double a=Double.parseDouble(rowNum.getFlow())/Integer.parseInt(rowNum.getUserCount());
		    //rowNum.setFlowCount(String.format("%.2f", a));
			rowNum.setRowNums(num++);
		}
		return pageList;
	}
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 */
	public UnidentifiedUrl selectVoById(String host1,String time){
		return mapper.selectVoById(host1,time);
	}
	
	/**
	 * 插入域名表
	 * @param vo
	 */
	public UnidentifiedUrl insertDomainRule(UnidentifiedUrl vo){
		Boolean bl = mapper.insertDomainRule(vo);
		mapper.updateAuditStatus(vo);
		return vo;
	}
	
	
	/**
	 * 修改
	 * @param vo
	 * @return 
	 */
	public UnidentifiedUrl updateVo(UnidentifiedUrl vo){
		Boolean bl = mapper.updateVo(vo);
		return vo;
	}
	
	public List<Map<String, Object>> getprodId(String  prodName) {
		return mapper.selectProdId(prodName);
	}
	public UnidentifiedUrl getInfoByProdId(String  prodId) {
		return mapper.selectInfoByProdId(prodId);
	}
	public UnidentifiedUrl getprodCatagoryName(String  prodCatagory) {
		return mapper.getprodCatagoryName(prodCatagory);
	}
	public ProductInfo getInfoByProdName(String  prodName) {
		return mapper.selectInfoByProdName(prodName);
	}
	public int onlyUpdateCheck(String  host) {
		return mapper.onlyUpdateCheck(host);
	}
	public int insertDomainRuleNew(UnidentifiedUrl  vo) {
		return mapper.insertDomainRuleNew(vo);
	}
	public int updateDomainRuleNew(UnidentifiedUrl  vo) {
		return mapper.updateDomainRuleNew(vo);
	}
	public int checkData(UnidentifiedUrl  vo) {
		return mapper.checkData(vo);
	}
	public int batchUpdateEmptyCheck(Map<String, Object> params) {
		return mapper.batchUpdateEmptyCheck(params);
	}
	public int batchUpdateCheck(Map<String, Object> params) {
		return mapper.batchUpdateCheck(params);
	}
	public int batchInsertDomainRule(Map<String, Object> params) {
		return mapper.batchInsertDomainRule(params);
	}
	public List<Map<String, Object>> getAllProdName() {
		return mapper.getAllProdName();
	}
	public List<Map<String, Object>> getAllProdId() {
		return mapper.getAllProdId();
	}
	public int alterAndInsert(UnidentifiedUrl vo) {
		return mapper.alterAndInsert(vo);
	}
	public int alterInsertDomain(UnidentifiedUrl vo) {
		return mapper.alterInsertDomain(vo);
	}
	public int alterProdInfo(UnidentifiedUrl vo) {
		return mapper.alterProdInfo(vo);
	}
	public int alterOnlyUpdate(UnidentifiedUrl vo) {
		vo.setIsCheck("1");
		return mapper.alterOnlyUpdate(vo);
	}
	public int insertExport(UnidentifiedUrl vo) {
		return mapper.insertExport(vo);
	}
	public List<UnidentifiedUrl> comLikeSearch(String prodName) {
		return mapper.comLikeSearch(prodName);
	}
}
