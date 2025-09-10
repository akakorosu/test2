package com.bonc.dpi.dao.mapper;

import java.util.List;

import com.bonc.dpi.dao.entity.IndustryGroupPic;


public interface IndustryGroupPicMapper {
	
	List<IndustryGroupPic> selectAgeEcharts (IndustryGroupPic industryGroupPic);

	List<IndustryGroupPic> selectTopAppdate (IndustryGroupPic industryGroupPic);
	
	List<IndustryGroupPic> selectProdTypePer (IndustryGroupPic industryGroupPic);
	
	List<IndustryGroupPic> ageEchartsClickLeft (IndustryGroupPic industryGroupPic);
	
	List<IndustryGroupPic> ageEchartsClickRight (IndustryGroupPic industryGroupPic);
	
	int selectCount (IndustryGroupPic industryGroupPic);
}



