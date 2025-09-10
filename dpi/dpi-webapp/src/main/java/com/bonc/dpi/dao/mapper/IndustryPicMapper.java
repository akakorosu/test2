package com.bonc.dpi.dao.mapper;

import java.util.List;

import com.bonc.dpi.dao.entity.IndustryOverview;
import com.bonc.dpi.dao.entity.IndustryPic;



public interface IndustryPicMapper {

	List<IndustryPic> select (IndustryPic industryPic);
	String selectTopTotal(IndustryPic obj);
	List<IndustryPic> selectSex (IndustryPic industryPic);
	List<IndustryPic> selectAllSex(IndustryPic industryPic);
	List<IndustryPic> selectAge (IndustryPic industryPic);
	List<IndustryPic> selectAllAge (IndustryPic industryPic);
	/**
	 * 地图某标签数据
	 * @param obj
	 * @return
	 */
	List<IndustryPic> selectMapDataList(IndustryPic obj);
	/**
	 * 地图所有标签的数据
	 * @param obj
	 * @return
	 */
	List<IndustryPic> selectMapAllData(IndustryPic obj);
	
}
