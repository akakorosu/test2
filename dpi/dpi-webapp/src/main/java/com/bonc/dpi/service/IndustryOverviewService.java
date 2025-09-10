package com.bonc.dpi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.bonc.dpi.dao.mapper.IndustryOverviewMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.bonc.dpi.dao.entity.IndustryOverview;

@Service
@Transactional(rollbackFor = Exception.class)

public class IndustryOverviewService {
	@Autowired
	IndustryOverviewMapper industryOverviewMapper;
	/**
	 * 获取分类数据
	 * @param obj
	 * @return
	 */
	public List<IndustryOverview> selectCategoricalData(IndustryOverview obj){
		return industryOverviewMapper.selectCategoricalData(obj);
	}
	/**
	 * 获取top10APP 未选择标签
	 * @param obj
	 * @return
	 */
	public Page<IndustryOverview> selectTopList(IndustryOverview obj){
		PageHelper.startPage(0,10);
		Page<IndustryOverview> topList=(Page<IndustryOverview>)industryOverviewMapper.selectTopList(obj);
		//IndustryOverview total=industryOverviewMapper.selectTopTotal(obj);
		return topList;
//		if(StringUtils.isBlank(obj.getLabelName())) {
//			//所有标签中top10
//			Page<IndustryOverview> topList=(Page<IndustryOverview>)industryOverviewMapper.selectTopList(obj);		
//			if(topList.size()>0) {
//				List<String> list=new ArrayList<String>();
//				for(IndustryOverview top:topList) {
//					if(!list.contains(top.getLabelName())) {
//						list.add(top.getLabelName());
//					}
//				}
//				List<IndustryOverview> totalList=industryOverviewMapper.selectTopTotalByLabelList(obj.getMonthId(),list);
//				for(IndustryOverview top:topList) {
//					for(IndustryOverview total:totalList) {
//						if(top.getLabelName().equals(total.getLabelName())) {
//							top.setAllUserCount(total.getAllUserCount());
//							top.setAllUserTotal(total.getAllUserTotal());
//							top.setAllFlow(total.getAllFlow());
//						}
//					}
//				}
//			}		
//			return topList;
//		}else {
//			//指定标签中top10
//			Page<IndustryOverview> topList=(Page<IndustryOverview>)industryOverviewMapper.selectTopList(obj);
//			IndustryOverview total=industryOverviewMapper.selectTopTotal(obj);
//			if(topList.size()>0) {
//				for(IndustryOverview top:topList) {
//					top.setAllUserCount(total.getAllUserCount());
//					top.setAllUserTotal(total.getAllUserTotal());
//					top.setAllFlow(total.getAllFlow());
//				}
//			}
//			return topList;
//		}
	}
	/**
	 * 获取top10APP  选择标签
	 * @param obj
	 * @return
	 */
	public Page<IndustryOverview> selectTopListChoose(IndustryOverview obj){
		PageHelper.startPage(0,10);
		Page<IndustryOverview> topList=(Page<IndustryOverview>)industryOverviewMapper.selectTopListChoose(obj);
		return topList;

	}
	/**
	 * 获取地图数据
	 */
	public List<IndustryOverview> selectMapDataList(IndustryOverview obj){
		List<IndustryOverview> list=null;
		if("0".equals(obj.getMapLabelFlag())) {
			list=(List<IndustryOverview>) industryOverviewMapper.selectMapAllData(obj);
			//System.out.println("---list--"+JSON.toJSONString(list));
		}else {
			 list=industryOverviewMapper.selectMapDataList(obj);
		}
		
		/*IndustryOverview data=industryOverviewMapper.selectMapAllData(obj);
		DecimalFormat df = new DecimalFormat("#0.00");
		for(IndustryOverview o:list) {
			//用户占比
			double userPro=Double.parseDouble(o.getUserTotal())/Double.parseDouble(data.getAllUserTotal());
			//流量占比
			double flowPro=Double.parseDouble(o.getFlow())/Double.parseDouble(data.getAllFlow());
			//人均流量
			double avg=Double.parseDouble(o.getFlow())/Double.parseDouble(o.getUserTotal());
			o.setUserPro(df.format(userPro*100)+"%");
			o.setFlowPro(df.format(flowPro*100)+"%");
			o.setPerCapitaFlow(df.format(avg));
		}
		return list;*/
		return list;
	}
	/**
	 * 获取折线图数据
	 * @param obj
	 * @return
	 */
	public List<IndustryOverview> selectLineChartList(IndustryOverview obj){
		List<IndustryOverview> list=industryOverviewMapper.selectLineChartList(obj);
//		List<IndustryOverview> list2=industryOverviewMapper.selectAll(obj);
//		Map<String,List<IndustryOverview>> map=new HashMap<String,List<IndustryOverview>>();
//		map.put("data", list1);
//		map.put("allData", list2);
		return list;
	}
	/**
	 * 获取柱形图数据
	 * @param obj
	 * @return
	 */
	public List<IndustryOverview> getInitMaxValue(IndustryOverview obj){
		List<IndustryOverview> list=industryOverviewMapper.getInitMaxValue(obj);
//		List<IndustryOverview> list2=industryOverviewMapper.selectAll(obj);
//		Map<String,List<IndustryOverview>> map=new HashMap<String,List<IndustryOverview>>();
//		map.put("data", list1);
//		map.put("allData", list2);
		return list;
	}
	
	/**
	 * 获得更多app
	 * @param obj
	 * @return
	 */
	public Page<IndustryOverview> getMoreAppList(IndustryOverview vo,Integer page, Integer row) throws Exception{
		PageHelper.startPage(page, row);
		Page<IndustryOverview> pageList = (Page<IndustryOverview>) industryOverviewMapper.getMoreAppList(vo);
		int num = 1 +(page-1)* row;
		for (IndustryOverview rowNum :pageList) {
			rowNum.setRowNums(num++);
		}
		return pageList;
	}
}
