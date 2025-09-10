package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.IndustryOverview;

public interface IndustryOverviewMapper {
	/**
	 * 查询分类信息
	 * @return
	 */
	
	List<IndustryOverview> selectCategoricalData(IndustryOverview obj);
	/**
	 * 查询top10APP
	 * @param obj
	 * @return
	 */
	List<IndustryOverview> selectTopList(IndustryOverview obj);
	
	List<IndustryOverview> selectTopListChoose(IndustryOverview obj);
	/**
	 * 查询APP的总人数和总流量
	 * @param obj
	 * @return
	 */
	IndustryOverview selectTopTotal(IndustryOverview obj);
	/**
	 * 查询所有类APP的总人数和总流量
	 * @param monthId
	 * @param list
	 * @return
	 */
	List<IndustryOverview> selectTopTotalByLabelList(@Param("monthId")String monthId,@Param("list")List<String> list);
	/**
	 * 获取地图数据
	 * @param obj
	 * @return
	 */
	List<IndustryOverview> selectMapDataList(IndustryOverview obj);
	/**
	 * 获取地图总数据
	 * @param obj
	 * @return
	 */
	List<IndustryOverview> selectMapAllData(IndustryOverview obj);
	/**
	 * 查询人数和流量时段分布数据
	 * @param obj
	 * @return
	 */
	List<IndustryOverview> selectLineChartList(IndustryOverview obj);
	List<IndustryOverview> getInitMaxValue(IndustryOverview obj);
	List<IndustryOverview> getMoreAppList (IndustryOverview vo);
}
