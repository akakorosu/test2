package com.bonc.dpi.service;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bonc.dpi.dao.mapper.ContentProjectRankMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.util.NumberUtil;

import com.bonc.dpi.cache.CacheService;
import com.bonc.dpi.config.Constant;
import com.bonc.dpi.dao.entity.ContentProjectRank;

@Service
public class ContentProjectRankService {
	
	protected final Logger logger = LoggerFactory.getLogger(ContentProjectRankService.class);
	
	@Autowired
	ContentProjectRankMapper contentProjectRankMapper;
	
	@Resource
	private CacheService cacheService;
	/**
	 * 获取分类App top10
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public Page<ContentProjectRank> selectCategoricalAppList(ContentProjectRank obj) throws Exception{
		Page<ContentProjectRank> topList = null;
		String tableKey = cacheService.obtainKey(Constant.CACHE_KEY_PREFIX, Constant._CACHE_KEY_CONTENTPROJECTRANK_APPTOP, 
				obj.getMonthId(), obj.getLabelName1());
		String conditionKey = obj.getCityId();
		if(cacheService.hasHashCache(tableKey, conditionKey)) {
			topList = cacheService.getHashCache(tableKey, conditionKey);
			logger.info("缓存命中[" +tableKey + "," + conditionKey + "]：size=" + (topList == null ? 0 : topList.size()));
			return topList;
		}
		
		PageHelper.startPage(0, 10);
		topList = (Page<ContentProjectRank>) contentProjectRankMapper.selectCategoricalAppList(obj);
		if (topList != null && topList.size() > 0) {
			int i = 1;
			for (ContentProjectRank content : topList) {
				content.setId(i++);
			}
			cacheService.setHashCacheWithExpireTime(tableKey, conditionKey, topList, CacheService.COMMON_TIMEOUTSECOND);
			logger.info("缓存数据[" +tableKey + "," + conditionKey + "]：size=" + (topList == null ? 0 : topList.size()));
		}
		return topList;
	}
	/**
	 * 查询用户统计数据
	 * @param obj
	 * @return
	 */
	public ContentProjectRank selectUserData(ContentProjectRank obj){
		ContentProjectRank data=contentProjectRankMapper.selectUserData(obj);
		ContentProjectRank allData=contentProjectRankMapper.selectAllUserData(obj);
		
		String content = "";
		try {
			String prodId = obj.getProdId();
			if(com.bonc.dpi.utils.StringUtils.isNull(prodId)) {
				prodId = "all";
			}
			String cacheKey = cacheService.obtainKey(Constant.CACHE_KEY_PREFIX, Constant._CACHE_KEY_CONTENTPROJECTRANK_CONTENT, 
					obj.getMonthId(), obj.getCityId(), obj.getLabelName1(), prodId);
			String cacheValue = cacheService.getStrCache(cacheKey);
			if (com.bonc.dpi.utils.StringUtils.isNull(cacheValue) || "0".equals(cacheValue)) {
				content = contentProjectRankMapper.selectContentCount(obj);
			}else {
				content = cacheValue;
			}
			if(!com.bonc.dpi.utils.StringUtils.isNull(content) && !"0".equals(content)) {
				cacheService.setStrCacheWithExpireTime(cacheKey, content, CacheService.COMMON_TIMEOUTSECOND);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DecimalFormat df = new DecimalFormat("0.00");
		if(data!=null&&allData!=null) {
			data.setUserTotalPercent(calculate(data.getUserTotal(),allData.getUserTotal(),df));
			data.setUserCountPercent(calculate(data.getUserCount(),allData.getUserCount(),df));
			data.setTotalFlowPercent(calculate(data.getTotalFlow(),allData.getTotalFlow(),df));
			if(StringUtils.isBlank(content)) {
				data.setTotalCentent("0");
			}else {
				data.setTotalCentent(content);
			}
			return data;
		}else {
			ContentProjectRank d=new ContentProjectRank();
			if(StringUtils.isBlank(content)) {
				d.setTotalCentent("0");
			}else {
				d.setTotalCentent(content);
			}
			return d;
		}
	}
	private String calculate(double data1,double data2,DecimalFormat df) {
		if(data2==0) {
			return "0";
		}
		String str="";
		if(data1>0) {
			str=df.format((data1/data2)*100)+"%";
		}else {
			str="0";
		}
		return str;
	}
	/**
	 * 获取内容 top10
	 * @param obj
	 * @return
	 */
	public Page<ContentProjectRank> selectContentTopList(ContentProjectRank obj){
		PageHelper.startPage(0,10);
		Page<ContentProjectRank> topList=null;
		if("影视".equals(obj.getLabelName1())) {		
			topList=(Page<ContentProjectRank>)contentProjectRankMapper.selectVideoTopList(obj);
		}else if("小说阅读".equals(obj.getLabelName1())) {			
			topList=(Page<ContentProjectRank>)contentProjectRankMapper.selectNovelTopList(obj);
		}else if("网上购物".equals(obj.getLabelName1())) {
		}else if("音乐".equals(obj.getLabelName1())){
		}
		if(topList!=null&&topList.size()>0) {
			int i=1;
			for(ContentProjectRank content:topList) {
				content.setId(i++);
			}
		}
		return topList;
	}
	/**
	 * 获取内容的用户数和流量
	 * @param obj
	 * @return
	 */
	public List<ContentProjectRank> selectContentCountList(ContentProjectRank obj){
		return contentProjectRankMapper.selectContentCountList(obj);
	}	
	/**
	 * 获取分类用户数据
	 * @param obj
	 * @return
	 */
	public List<ContentProjectRank> selectCatagoryCountList(ContentProjectRank obj){
		PageHelper.startPage(0,20);
		Page<ContentProjectRank> list=(Page<ContentProjectRank>)contentProjectRankMapper.selectCatagoryCountList(obj);
		return list;
	}
	/**
	 * 获取折线图数据
	 * @param obj
	 * @return
	 */
	public List<ContentProjectRank> selectLineChartList(ContentProjectRank obj){
		List<ContentProjectRank> list=contentProjectRankMapper.selectLineChartList(obj);;
		if(list!=null&&list.size()>=2) {
			Collections.sort(list, new Comparator<ContentProjectRank>() {
				@Override
				public int compare(ContentProjectRank o1, ContentProjectRank o2) {
					int i=Integer.parseInt(o1.getHourId());
					int j=Integer.parseInt(o2.getHourId());
					return i-j;
				}
			});
		}
		return list;
	}
	/**
	 * 获取地图数据
	 */
	public List<ContentProjectRank> selectMapDataList(ContentProjectRank obj){
		if(StringUtils.isBlank(obj.getProdId())) {
			return contentProjectRankMapper.selectMapDataList(obj);
		}
		return contentProjectRankMapper.selectMapDataListById(obj);
	}
	/**
	 * 行业画像性别年龄查询
	 * @param industryPic
	 */
	public ContentProjectRank selectAgeAndSex(ContentProjectRank obj) {
		List<ContentProjectRank> ageList=null;
		List<ContentProjectRank> sexList=null;
		if(StringUtils.isBlank(obj.getProdId())) {
			ageList=contentProjectRankMapper.selectAge(obj);
			sexList=contentProjectRankMapper.selectSex(obj);
		}else {
			ageList=contentProjectRankMapper.selectAgeById(obj);
			sexList=contentProjectRankMapper.selectSexById(obj);
		}
		ContentProjectRank content=new ContentProjectRank();
		if(sexList!=null&&sexList.size()>0) {
			for(ContentProjectRank sex:sexList) {
				if("1".equals(sex.getSex())) {
					content.setManPercent(sex.getUserPercent()+"%");
				}
				if("2".equals(sex.getSex())) {
					content.setWomanPercent(sex.getUserPercent()+"%");
				}
			}
		}
		for(ContentProjectRank age:ageList) {
			if("0".equals(age.getAge())) {
				content.setAge0(age.getUserPercent());
			}else if("1".equals(age.getAge())) {
				content.setAge1(age.getUserPercent());
			}else if("2".equals(age.getAge())) {
				content.setAge2(age.getUserPercent());
			}else if("3".equals(age.getAge())) {
				content.setAge3(age.getUserPercent());
			}else if("4".equals(age.getAge())) {
				content.setAge4(age.getUserPercent());
			}else if("5".equals(age.getAge())) {
				content.setAge5(age.getUserPercent());
			}else if("6".equals(age.getAge())) {
				content.setAge6(age.getUserPercent());
			}
		}
		return content; 
	}
	/**
	 * 行业画像APP排行
	 * @param obj
	 */
	public Page<ContentProjectRank> select(ContentProjectRank obj) {
		PageHelper.startPage(0,10);
		Page<ContentProjectRank> topList=(Page<ContentProjectRank>)contentProjectRankMapper.selectReleVanceAPP(obj);
		ContentProjectRank toatl=contentProjectRankMapper.selectTopTotal(obj);
		if(toatl==null) {
			return null;
		}
		if ("影视".equals(obj.getLabelName1())) {
			topList = calculateNew("影视", topList, toatl.getTotalFlow());
		} else {
			topList = calculateNew("", topList, toatl.getUserTotal());
		}
		return topList;
	}
	
	@SuppressWarnings("unused")
	@Deprecated
	private Page<ContentProjectRank> calculate1(String label,Page<ContentProjectRank> topList,DecimalFormat df,double data){
		if(topList==null||topList.size()==0||data==0) {
			return null;
		}
		int i=1;
		for(ContentProjectRank top:topList) {
			top.setId(i++);
			if("影视".equals(label)) {
				String la=df.format(top.getTotalFlow()*100/data); 
				top.setUserTotalPercent(la);
			}else {
				String la=df.format(top.getUserTotal()*100/data); 
				top.setUserTotalPercent(la);
			}
			
		}
		return topList;
	}
	
	private Page<ContentProjectRank> calculateNew(String label, Page<ContentProjectRank> topList, double data) {
		if (topList == null || topList.size() == 0 || data == 0) {
			return null;
		}
		int i = 1;
		for (ContentProjectRank top : topList) {
			top.setId(i++);
			if ("影视".equals(label)) {
				String la = NumberUtil.div(NumberUtil.mul(String.valueOf(top.getTotalFlow()), "100").toString(),
						String.valueOf(data), 2).toString();
				top.setUserTotalPercent(la);
			} else {
				String la = NumberUtil.div(NumberUtil.mul(String.valueOf(top.getUserTotal()), "100").toString(),
						String.valueOf(data), 2).toString();
				top.setUserTotalPercent(la);
			}
		}
		return topList;
	}
	
}
