package com.bonc.dpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.IndustryGroupPic;
import com.bonc.dpi.dao.mapper.IndustryGroupPicMapper;


@Service
@Transactional(rollbackFor = Exception.class)
public class IndustryGroupPicService {

	@Resource
	private IndustryGroupPicMapper mapper;

	/**
	 * 年龄分布echarts
	 * 
	 * @param industryPic
	 */
	public List<IndustryGroupPic> selectAgeEcharts(IndustryGroupPic industryGroupPic) {
		return mapper.selectAgeEcharts(industryGroupPic);
	}
	
	/**
	 * 上部 app 占比
	 * 
	 * @param industryPic
	 */
	public List<IndustryGroupPic> selectTopAppdate(IndustryGroupPic industryGroupPic) {
		return mapper.selectTopAppdate(industryGroupPic);
	}
	
	/**
	 * 上部 行业 占比 以及性别分布
	 * 
	 * @param industryPic
	 */
	public List<IndustryGroupPic> selectProdTypePer(IndustryGroupPic industryGroupPic) {
		return mapper.selectProdTypePer(industryGroupPic);
	}
	
	/**
	 * echarts 点击左侧
	 * 
	 * @param industryPic
	 */
	public List<IndustryGroupPic> ageEchartsClickLeft(IndustryGroupPic industryGroupPic) {
		return mapper.ageEchartsClickLeft(industryGroupPic);
	}
	/**
	 * echarts 点击右侧
	 * 
	 * @param industryPic
	 */
	public List<IndustryGroupPic> ageEchartsClickRight(IndustryGroupPic industryGroupPic) {
		return mapper.ageEchartsClickRight(industryGroupPic);
	}
	
	/**
	 * 男女比例
	 * 
	 * @param industryPic
	 */
	public int selectCount(IndustryGroupPic industryGroupPic) {
		return mapper.selectCount(industryGroupPic);
	}
}
