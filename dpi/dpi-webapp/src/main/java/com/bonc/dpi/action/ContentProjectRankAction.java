package com.bonc.dpi.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.dpi.dao.entity.ContentProjectRank;
import com.bonc.dpi.service.ContentProjectRankService;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value = "/contentProjectRank")
public class ContentProjectRankAction {

	@Autowired
	ContentProjectRankService contentProjectRankService;

	private static final Logger logger=LoggerFactory.getLogger(ContentProjectRankAction.class);

	/**
	 * 获取分类App top10
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCategoricalAppList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<ContentProjectRank> getCategoricalAppList(ContentProjectRank obj) throws Exception {
		Page<ContentProjectRank> result=contentProjectRankService.selectCategoricalAppList(obj);
		PageJqGrid<ContentProjectRank> pageJqGrid = new PageJqGrid<ContentProjectRank>(result);
		return pageJqGrid;
	}
	/**
	 * 获取用户统计数据
	 * @param obj
	 * @return
	 */
	@RequestMapping(value = "/getUserData",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getUserData(ContentProjectRank obj) {
		try {
			ContentProjectRank result=contentProjectRankService.selectUserData(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 获取内容 top10
	 * @param obj
	 * @return
	 */
	@RequestMapping(value = "/getContentTopList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<ContentProjectRank> getContentTopList(ContentProjectRank obj) {
		Page<ContentProjectRank> result=contentProjectRankService.selectContentTopList(obj);
		PageJqGrid<ContentProjectRank> pageJqGrid = new PageJqGrid<ContentProjectRank>(result);
		return pageJqGrid;
	}
	/**
	 * 获取内容用户和流量
	 * @return
	 */
	@RequestMapping(value = "/getContentCountList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getContentCountList(ContentProjectRank obj) {
		try {
			List<ContentProjectRank> result=contentProjectRankService.selectContentCountList(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 获取分类用户数据
	 * @return
	 */
	@RequestMapping(value = "/getCatagoryCountList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getCatagoryCountList(ContentProjectRank obj) {
		try {
			List<ContentProjectRank> result=contentProjectRankService.selectCatagoryCountList(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 获取折线图数据
	 * @return
	 */
	@RequestMapping(value = "/getLineChartList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getLineChartList(ContentProjectRank obj) {
		try {
			List<ContentProjectRank> result=contentProjectRankService.selectLineChartList(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 获取地图数据(引用行业总览中方法)
	 * @return
	 */
	@RequestMapping(value = "/getMapDataList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getMapDataList(ContentProjectRank obj) {
		try {
			List<ContentProjectRank> result=contentProjectRankService.selectMapDataList(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 获取年龄性别比例
	 * @param obj
	 * @return
	 */
	@RequestMapping(value="/selectage",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String selectage(ContentProjectRank obj) {
		try {
			ContentProjectRank result=contentProjectRankService.selectAgeAndSex(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			Map<String,String> resultError = new HashMap<String, String>();
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, resultError);
		}
	}
	/**
	 * 获取APP排行
	 * @param obj
	 * @return
	 */
	@RequestMapping(value="/selectapptop10",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String selectapptop10(ContentProjectRank obj) {
		try {
			List<ContentProjectRank> result=contentProjectRankService.select(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
}
