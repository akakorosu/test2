package com.bonc.dpi.dao.mapper;

import java.util.List;
import com.bonc.dpi.dao.entity.ContentProjectRank;

public interface ContentProjectRankMapper {
	/**
	 * 查询top10APP
	 * @param obj
	 * @return
	 */
	List<ContentProjectRank> selectCategoricalAppList(ContentProjectRank obj);
	/**
	 * 查询用户统计用户数，流量数，次数
	 */
	ContentProjectRank selectUserData(ContentProjectRank obj);
	/**
	 * 查询用户统计总用户数，总流量数，总次数 
	 */
	ContentProjectRank selectAllUserData(ContentProjectRank obj);
	/**
	 * 查询单表用户统计内容数
	 * @return
	 */
	String selectContentCount(ContentProjectRank obj);
	/**
	 * 查询影视top10
	 * @param obj
	 * @return
	 */
	List<ContentProjectRank> selectVideoTopList(ContentProjectRank obj);
	/**
	 * 查询小说top10
	 * @param obj
	 * @return
	 */
	List<ContentProjectRank> selectNovelTopList(ContentProjectRank obj);
	/**
	 * 查询内容分类用户数,流量
	 * @return
	 */
	List<ContentProjectRank> selectContentCountList(ContentProjectRank obj);
	/**
	 * 查询分类用户数据
	 * @return
	 */
	List<ContentProjectRank> selectCatagoryCountList(ContentProjectRank obj);
	/**
	 * 查询次数和流量时段分布数据(折线图)
	 * @param obj
	 * @return
	 */
	List<ContentProjectRank> selectLineChartList(ContentProjectRank obj);
	/**
	 * 获取地图数据
	 * @param obj
	 * @return
	 */
	List<ContentProjectRank> selectMapDataList(ContentProjectRank obj);
	/**
	 * 查询某应用map数据
	 * @param obj
	 * @return
	 */
	List<ContentProjectRank> selectMapDataListById(ContentProjectRank obj);
	/**
	 * 行业画像性别年龄查询
	 * @param industryPic
	 */
	List<ContentProjectRank> selectSex (ContentProjectRank obj);
	List<ContentProjectRank> selectSexById(ContentProjectRank obj);
	List<ContentProjectRank> selectAge (ContentProjectRank obj);
	List<ContentProjectRank> selectAgeById(ContentProjectRank obj);
	/**
	 * 行业画像APP排行
	 * @param industryPic
	 */
	List<ContentProjectRank> selectReleVanceAPP (ContentProjectRank obj);
	ContentProjectRank selectTopTotal(ContentProjectRank obj);
}
