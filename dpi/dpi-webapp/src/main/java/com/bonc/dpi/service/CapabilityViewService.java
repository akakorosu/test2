package com.bonc.dpi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.mapper.CapabilityViewMapper;
import com.bonc.dpi.utils.DpiUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.bonc.dpi.cache.CacheService;
import com.bonc.dpi.config.Constant;
import com.bonc.dpi.dao.entity.CapabilityView;
import com.bonc.dpi.dao.entity.ProductFbDescInfo;

@Service
@Transactional(rollbackFor = Exception.class)

public class CapabilityViewService {
	@Autowired
	CapabilityViewMapper capabilityViewMapper;
	
	@Resource
	private CacheService cacheService;
	
	public boolean isCacheProd() {
		return cacheService.hasKey(getCacheProdKey());
	}
	
	private String getCacheProdKey() {
		try {
			return cacheService.obtainKey(Constant.CACHE_KEY_PREFIX, Constant._CACHE_KEY_CAPABILITYVIEW_PROD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-1";
	}
	
	public synchronized void initProdCache() {
		cacheService.delKey(getCacheProdKey());
		Map<String, String> prodMap = new HashMap<>();
		List<CapabilityView> mapping = getLoadProd();
		for (CapabilityView item : mapping) {
			String prodLabelCode = item.getProdLabelCode();
			String prodName = item.getProdName();
			if (prodMap.containsKey(prodLabelCode)) {
				prodName = DpiUtils.strDecrypt(item.getProdName());
				prodName = prodName.replaceAll("<", "");
				prodName = prodName.replaceAll(">", "");
				String kk = prodMap.get(prodLabelCode) + "~1~" + prodName;
				prodMap.put(prodLabelCode, kk);
			} else {
				prodName = DpiUtils.strDecrypt(item.getProdName());
				prodName = prodName.replaceAll("<", "");
				prodName = prodName.replaceAll(">", "");
				prodMap.put(prodLabelCode, prodName);
			}
		}
		
		for(Entry<String, String> entry : prodMap.entrySet()) {
			cacheService.setHashCache(getCacheProdKey(), entry.getKey(), entry.getValue());
		}
	}
	
	public String getProdCacheValue(String fieldKey) {
		String result = cacheService.getHashCache(getCacheProdKey(), fieldKey);
		return result;
	}
	
	/**
	 * 分页查询
	 * @param
	 * @param page
	 * @param row
	 * @return
	 */
	public Page<ProductFbDescInfo> selectProductFbDescInfoList(ProductFbDescInfo param, Integer page, Integer row, String sord, String sidx) {
		String labelLvl = param.getQueryLabelLvl();
		if("1".equals(labelLvl) || "2".equals(labelLvl)){
			param.setProdLabelCode("%"+param.getQueryLabelName()+"%");
		}
		PageHelper.startPage(page, row);
		Page<ProductFbDescInfo> pageList = (Page<ProductFbDescInfo>) this.capabilityViewMapper.getProductFbDescInfo(param);
		return pageList;
	}
	
	/**
	 * 获取分类数据
	 * @param obj
	 * @return
	 */
	public Page<CapabilityView> selectList(CapabilityView vo, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<CapabilityView> pageList = (Page<CapabilityView>) this.capabilityViewMapper.selectList(vo);
	
		
		return pageList;
	}
	public Map<String, String> getLabelNum(){
		return capabilityViewMapper.getLabelNum();
	}
	
	public List<String> getLabelLvl1(){
		return capabilityViewMapper.getLabelLvl1();
	}
	public List<String> getModelWorkLvl1(){
		return capabilityViewMapper.getModelWorkLvl1();
	}
	
	public List<CapabilityView> getLabelLvl2(String type){
		return capabilityViewMapper.getLabelLvl2(type);
	}
	public List<CapabilityView> getAllAct(){
		return capabilityViewMapper.getAllAct();
	}
	public List<CapabilityView> getAllLabelIn(String code){
		return capabilityViewMapper.getAllLabelIn(code);
	}
	public int  countAllDir(CapabilityView vo){
		return capabilityViewMapper.countAllDir(vo);
		
	}
	public CapabilityView  getDireDetail(CapabilityView vo){
		return capabilityViewMapper.getDireDetail(vo);
		
	}
	public List<CapabilityView> getListByPage(CapabilityView vo ){
		String databaseType = DpiUtils.DATABASE_TYPE;//oracle,mysql
		if("mysql".equals(databaseType)){
			int page=Integer.parseInt(vo.getNowPage());
			int pageSize=Integer.parseInt(vo.getPageSize());
			vo.setStartNum(""+(page-1)*pageSize);
		}else{
			int page=Integer.parseInt(vo.getNowPage());
			int pageSize=Integer.parseInt(vo.getPageSize());
			int a=(page-1)*pageSize+1;
			vo.setStartNum(""+a);
			vo.setEndNum(""+(a+pageSize-1));
			
			
		}
		return capabilityViewMapper.getListByPage(vo);
	}
	public List<CapabilityView> updateAct(CapabilityView vo){
		return capabilityViewMapper.updateAct(vo);
	}
	public List<CapabilityView> getWorkLvl2(String type){
		return capabilityViewMapper.getWorkLvl2(type);
	}
	public List<CapabilityView> getChild(){
		return capabilityViewMapper.getChild();
	}
	public List<CapabilityView> getLoadProd(){
		return capabilityViewMapper.getLoadProd();
	}
	
	public List<CapabilityView> getLabelLvl3(String type,String type2){
		return capabilityViewMapper.getLabelLvl3(type,type2);
	}
	public List<CapabilityView> getContentKuLabel(){
		return capabilityViewMapper.getContentKuLabel();
	}
	public List<CapabilityView> getContentKuProdName(String groupType){
		return capabilityViewMapper.getContentKuProdName(groupType);
	}
	public List<CapabilityView> getContentKuAttr(String contentType){
		return capabilityViewMapper.getContentKuAttr(contentType);
	}
	public List<CapabilityView> getModelInfo(String type){
		return capabilityViewMapper.getModelInfo(type);
	}
	public List<CapabilityView> getLabelProd(CapabilityView vo){
		return capabilityViewMapper.getLabelProd(vo);
	}
	
	public Boolean publishById(ProductFbDescInfo voParam){
		return capabilityViewMapper.publishById(voParam);
	}
	
	public int getpublishAuth(String loginId) {
		return capabilityViewMapper.publishAuth(loginId);
	}
	
	
}
