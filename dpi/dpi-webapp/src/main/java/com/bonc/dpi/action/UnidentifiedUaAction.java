package com.bonc.dpi.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.bonc.dpi.dao.entity.AuditRuleError;
import com.bonc.dpi.dao.entity.OperationsCenter;
import com.bonc.dpi.dao.entity.ProductLabel;
import com.bonc.dpi.dao.entity.UnidentifiedUa;
import com.bonc.dpi.service.UnidentifiedUaService;
import com.github.pagehelper.Page;

/**
 * 未识别UA
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/unidentifiedUa")
public class UnidentifiedUaAction {
	@Autowired
	private UnidentifiedUaService unidentifiedUaService;
	private final SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 获取未识别UA信息
	 * @param time
	 * @param area
	 * @param gender
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<UnidentifiedUa> selectPageList(UnidentifiedUa vo, Integer page, Integer rows) throws Exception{
		Page<UnidentifiedUa> pageList = unidentifiedUaService.selectList(vo, page, rows);
		PageJqGrid<UnidentifiedUa> pageJqGrid = new PageJqGrid<UnidentifiedUa>(pageList);
		return pageJqGrid;
	}
	@RequestMapping(value = "/showUaKey")
	public String showUaKey(HttpServletRequest request,String uaKey,String ua,String dateId) throws Exception{
		request.setAttribute("uaKey", uaKey);
		request.setAttribute("ua", ua);
		request.setAttribute("dateId", dateId);
		return "pages/jsp/dpi/dimIaUserAgentKey/dimIaUserAgentKey";
	}
	@RequestMapping(value = "/showUa")
	public String showUa(HttpServletRequest request,String uaKey,String ua,String dateId) throws Exception{
		request.setAttribute("uaKey", uaKey);
		request.setAttribute("ua", ua);
		request.setAttribute("dateId", dateId);
		return "pages/jsp/dpi/dimIaUserAgentNoise/dimIaUserAgentNoise";
	}
	@RequestMapping(value = "/showRule")
	public String showRule(HttpServletRequest request,String uaKey,String ua,String dateId) throws Exception{
		request.setAttribute("uaKey", uaKey);
		request.setAttribute("ua", ua);
		request.setAttribute("dateId", dateId);
		return "pages/jsp/dpi/dimIaUserAgentRule/dimIaUserAgentRule";
	}
}
