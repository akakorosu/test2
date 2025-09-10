package com.bonc.dpi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.bonc.dpi.dao.entity.SubstanceLabel;

import com.bonc.dpi.dao.mapper.SubstanceLabelMapper;
import com.bonc.dpi.utils.DpiUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class SubstanceLabelService {
	@Autowired
	private SubstanceLabelMapper mapper;
	

	public Page<SubstanceLabel> selectList(SubstanceLabel vo, Integer page, Integer row) throws Exception{
		PageHelper.startPage(page, row);
		Page<SubstanceLabel> pageList = (Page<SubstanceLabel>) mapper.selectList(vo);
		int num = 1 +(page-1)* row;
		for (SubstanceLabel rowNum :pageList) {
			SubstanceLabel entity =mapper.getProdNameById(rowNum);
			
			rowNum.setProdName(DpiUtils.strDecrypt(entity.getProdName()));
			rowNum.setRowNum(num++);
		}
		return pageList;
	}
	public List<Map<String,String>> getGroupType() throws Exception{
		
		return mapper.getGroupType();
	}
    public List<SubstanceLabel> getProdIdAndName(SubstanceLabel vo) throws Exception{
    	
    	List<SubstanceLabel> result =mapper.getProdIdAndName(vo);
    	for (SubstanceLabel rowNum :result) {
    		SubstanceLabel entity =mapper.getProdNameById(rowNum);
    		
			rowNum.setProdName(DpiUtils.strDecrypt(entity.getProdName()));
			
		}
		return result;
	}
    public List<SubstanceLabel> getInitProdName(SubstanceLabel vo) throws Exception{
    	
    	List<SubstanceLabel> result =mapper.getInitProdName(vo);
    	for (SubstanceLabel rowNum :result) {
    		//SubstanceLabel entity =mapper.getProdNameById(rowNum);
			rowNum.setProdName(DpiUtils.strDecrypt(rowNum.getProdName()));
			
		}
		return result;
	}
   public List<SubstanceLabel> getCatContentType(SubstanceLabel vo) throws Exception{
    	
    	List<SubstanceLabel> result =mapper.getCatContentType(vo);
    	
		return result;
	}
   public int delContent(SubstanceLabel vo) throws Exception{
    	
	   int result =mapper.delContent(vo);
    	
		return result;
	}
   public int countData(SubstanceLabel vo) throws Exception{
   	
	   int result =mapper.countData(vo);
    	
		return result;
	}
   public int updateContentLabelCode(SubstanceLabel vo) throws Exception{
	   	
	   int result =mapper.updateContentLabelCode(vo);
    	
		return result;
	}
   public int insertContentLabelCode(SubstanceLabel vo) throws Exception{
	   	
	   int result =mapper.insertContentLabelCode(vo);
    	
		return result;
	}
   public SubstanceLabel selectVoById(SubstanceLabel vo) throws Exception{
   	
   	SubstanceLabel result =mapper.selectVoById(vo);
   	SubstanceLabel entity =mapper.getProdNameById(result);
   	result.setProdName(DpiUtils.strDecrypt(entity.getProdName()));
			
	return result;
	}
   public List<SubstanceLabel> comLikeSearch(SubstanceLabel vo) {
		return mapper.comLikeSearch(vo);
	}
   public int checkContent(SubstanceLabel vo) throws Exception{
	   	
	   int result =mapper.checkContent(vo);
    	
		return result;
	}
   public SubstanceLabel getProdNameById(SubstanceLabel vo) throws Exception{
	   	
	   SubstanceLabel result =mapper.getProdNameById(vo);
    	
		return result;
	}
    
}
