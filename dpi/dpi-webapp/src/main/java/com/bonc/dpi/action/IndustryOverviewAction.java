package com.bonc.dpi.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

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
import com.bonc.dpi.dao.entity.IndustryOverview;
import com.bonc.dpi.service.IndustryOverviewService;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value = "/industryOverview")
public class IndustryOverviewAction {
	@Autowired
	IndustryOverviewService industryOverviewService;
	
	private static final Logger logger=LoggerFactory.getLogger(IndustryOverviewAction.class);
	/**
	 * 获取分类数据
	 * @param obj
	 * @return
	 */
	@RequestMapping(value = "/getCategoricalData",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getCategoricalData(IndustryOverview obj) {
		try {
			if(obj.getOrderFlag().length()>2){
				throw new Exception("Invalid Param");
			}
			List<IndustryOverview> result=industryOverviewService.selectCategoricalData(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 获取top10APP 没选择标签
	 * @return
	 */
	@RequestMapping(value = "/getTopList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getTopData(IndustryOverview obj) {
		try {
			Page<IndustryOverview> result = industryOverviewService.selectTopList(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 获取top10APP 选择标签
	 * @return
	 */
	@RequestMapping(value = "/selectTopListChoose",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String selectTopListChoose(IndustryOverview obj) {
		try {
			Page<IndustryOverview> result = industryOverviewService.selectTopListChoose(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 获取地图数据
	 * @return
	 */
	@RequestMapping(value = "/getMapDataList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getMapDataList(IndustryOverview obj) {
		try {
			List<IndustryOverview> result=industryOverviewService.selectMapDataList(obj);
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
	public String getLineChartList(IndustryOverview obj) {
		try {
			List<IndustryOverview> result=industryOverviewService.selectLineChartList(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	
	/**
	 * 更多app排名
	 * @return
	 */
	@RequestMapping(value = "/getMoreAppList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<IndustryOverview> getMoreAppList(IndustryOverview vo, Integer page, Integer rows)
			throws Exception {
		Page<IndustryOverview> pageList = industryOverviewService.getMoreAppList(vo, page, rows);
		PageJqGrid<IndustryOverview> pageJqGrid = new PageJqGrid<IndustryOverview>(pageList);
		return pageJqGrid;
	}
	/**
	 * 获取柱形图数据
	 * @return
	 */
	@RequestMapping(value = "/getInitMaxValue",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getInitMaxValue(IndustryOverview obj) {
		try {
			if(obj.getOrderFlag().length()>2){
				throw new Exception("Invalid Param");
			}
			List<IndustryOverview> result=industryOverviewService.getInitMaxValue(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/showForm")
	public String showForm(HttpServletRequest request ,IndustryOverview vo) throws Exception{
		request.setAttribute("vo", vo);
		return "pages/jsp/dpi/industryOverview/industryOverviewForm";
	}
}
