package com.bonc.dpi.service;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.bonc.dpi.dao.entity.ContentProjectRank;
import com.bonc.dpi.dao.entity.IndustryOverview;
import com.bonc.dpi.dao.entity.IndustryPic;

import com.bonc.dpi.dao.mapper.IndustryPicMapper;
import com.bonc.dpi.utils.DpiUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class IndustryPicService{
	
	@Resource
	private IndustryPicMapper mapper;
	/**
	 * 行业画像APP排行
	 * @param obj
	 */
	public Page<IndustryPic> select(IndustryPic obj) {
		PageHelper.startPage(0,10);
		Page<IndustryPic> topList=(Page<IndustryPic>)mapper.select(obj);
		if(!StringUtils.isBlank(obj.getLabelName2())) {
			obj.setLabelName1(obj.getLabelName2());
		}
		String toatl=mapper.selectTopTotal(obj);
		if(topList==null||topList.size()==0||toatl==null||Integer.parseInt(toatl)==0) {
			return null;
		}
		DecimalFormat df = new DecimalFormat("0.00");			
		for(IndustryPic top:topList) {
			top.setProdName(DpiUtils.strDecrypt(top.getProdName()));
			String la=df.format(Double.parseDouble(top.getUserTotal())*100/Double.parseDouble(toatl)); 
			top.setUserTotalPercent(la);
		}
		return topList;
	}	
	
	/**
	 * 行业画像性别年龄查询
	 * @param industryPic
	 */
	public IndustryPic selectAgeAndSex(String num,IndustryPic industryPic) {
		List<IndustryPic> ageList=null;
		List<IndustryPic> sexList=null;
		if("0".equals(num)) {
			ageList=mapper.selectAllAge(industryPic);
			sexList=mapper.selectAllSex(industryPic);
		}else {
			ageList=mapper.selectAge(industryPic);
			sexList=mapper.selectSex(industryPic);
		}
		IndustryPic pi=new IndustryPic();
		if(sexList!=null&&sexList.size()>0) {
			for(IndustryPic sex:sexList) {
				if("1".equals(sex.getSex())) {
					pi.setManPer(sex.getUserTotalPer()+"%");
				}
				if("2".equals(sex.getSex())) {
					pi.setWomanPer(sex.getUserTotalPer()+"%");
				}
			}
		}
		if(ageList!=null&&ageList.size()>0) {
			for(IndustryPic age:ageList) {
				if("0".equals(age.getAge())) {
					pi.setAge1(age.getUserTotalPer());
				}else if("1".equals(age.getAge())) {
					pi.setAge2(age.getUserTotalPer());
				}else if("2".equals(age.getAge())) {
					pi.setAge3(age.getUserTotalPer());
				}else if("3".equals(age.getAge())) {
					pi.setAge4(age.getUserTotalPer());
				}else if("4".equals(age.getAge())) {
					pi.setAge5(age.getUserTotalPer());
				}else if("5".equals(age.getAge())) {
					pi.setAge6(age.getUserTotalPer());
				}else if("6".equals(age.getAge())) {
					pi.setAge7(age.getUserTotalPer());
				}
			}
		}
		return pi;	
	}
	
	
	/**
	 * 获取地图数据
	 */
	public List<IndustryPic> selectMapDataList(String num,IndustryPic obj){
		List<IndustryPic> result=null;
		if("0".equals(num)) {
			result=mapper.selectMapAllData(obj);
		}else {
			result=mapper.selectMapDataList(obj);
		}
		return result;
	}
	
}
