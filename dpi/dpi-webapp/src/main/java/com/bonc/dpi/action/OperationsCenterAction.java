package com.bonc.dpi.action;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.bonc.dpi.dao.entity.IncreaseProdName;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationsCenter;
import com.bonc.dpi.dao.entity.ProductLabel;
import com.bonc.dpi.dao.entity.SegWordLtb;
import com.bonc.dpi.service.OperationsCenterService;
import com.bonc.dpi.service.PublicCPeriodService;
import com.github.pagehelper.Page;

/**
 * 运维中心主页面处理
 * 
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/operationsCenter")
public class OperationsCenterAction {
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private static final Logger logger = LoggerFactory.getLogger(OperationsCenterAction.class);
	@Autowired
	private OperationsCenterService operationsCenterService;
	@Autowired
	PublicCPeriodService publicCPeriodService;

	/**
	 * 获取稽核错误,待补充UA，行业标签稽核，一级域名识别数量
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getCount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getCount(String time) {
		try {
			Map<String, String> result = operationsCenterService.getCount(time);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}

	// /**
	// * 获取未识别UA数量
	// * @return
	// */
	// @RequestMapping(value="/getUnidentifiedUaCount",method=RequestMethod.POST,
	// produces="application/json;charset=UTF-8")
	// @ResponseBody
	// public String getUnidentifiedUaCount(String time) {
	// try {
	// String result=operationsCenterService.getUnidentifiedUaCount(time);
	// return Ajax.responseString(CST.RES_SECCESS, result);
	// } catch (Exception e) {
	// logger.error(e.getMessage());
	// return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
	// }
	// }
	/**
	 * 获取记录和流量信息
	 * 
	 * @param time
	 * @param area
	 * @param gender
	 * @return
	 */
	@RequestMapping(value = "/getRecordAndFlow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getRecordAndFlow(String time) {
		try {
			OperationsCenter result = operationsCenterService.getRecordAndFlow(time);
			// Map<String, Object> result=operationsCenterService.getRecordAndFlow(time);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}

	/**
	 * 获取识别和需要新增信息
	 * 
	 * @param time
	 * @param area
	 * @param gender
	 * @return
	 */
	@RequestMapping(value = "/getMatchAndAdd", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getMatchAndAdd(String time) {
		try {
			Map<String, List<OperationsCenter>> result = operationsCenterService.getMatchAndAdd(time);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}

	/**
	 * 跳转一级域名识别页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showTopLevelDomain")
	public String showTopLevelDomain(HttpServletRequest request, String time) throws Exception {
		request.setAttribute("time", time);
		return "pages/jsp/dpi/topLevelDomain/topLevelDomainList";
	}

	/**
	 * 跳转URL模糊识别页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/urlFuzzyRecognition")
	public String urlFuzzyRecognition(HttpServletRequest request, String time) throws Exception {
		request.setAttribute("time", time);
		return "pages/jsp/dpi/urlFuzzyRecognition/urlFuzzyRecognition";
	}

	/**
	 * 跳转稽核规则错误页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showAuditRuleError")
	public String showAuditRuleError(HttpServletRequest request, String time) throws Exception {
		request.setAttribute("time", time);
		return "pages/jsp/dpi/auditRuleError/auditRuleErrorList";
	}
	/**
	 * 跳转内容标签稽核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/substanceLabelNum")
	public String substanceLabelNum(HttpServletRequest request, String time) throws Exception {
		request.setAttribute("time", time);
		return "pages/jsp/dpi/substanceLabel/substanceLabelList";
	}

	/**
	 * 跳转稽行业标签稽核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showIndustryLabelAudit")
	public String showIndustryLabelAudit(HttpServletRequest request, String time) throws Exception {
		request.setAttribute("time", time);
		return "pages/jsp/dpi/industryLabelAudit/industryLabelAuditList";
	}

	/**
	 * 跳转未识别UA页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showUnidentifiedUa")
	public String showUnidentifiedUa(HttpServletRequest request, String time) throws Exception {
		request.setAttribute("time", time);
		return "pages/jsp/dpi/unidentifiedUa/unidentifiedUaList";
	}

	/**
	 * 跳转未识别域名-智能识别页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showUnidentifiedUrl")
	public String showUnidentifiedUrl(HttpServletRequest request, String time, String typeId) throws Exception {
		request.setAttribute("time", time);
		request.setAttribute("typeId", typeId);
		return "pages/jsp/dpi/unidentifiedUrl/unidentifiedUrlList";
	}

	/**
	 * 获取行业稽核数量
	 * 
	 */
	@RequestMapping(value = "/getInNum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getInNum(String time) {
		try {
			Map<String, Object> result = operationsCenterService.getInNum(time);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}

	/**
	 * 获取URL模糊识别数量
	 * 
	 */
	@RequestMapping(value = "/getUrlNum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getUrlNum(String date_id) {
		try {
			Map<String, Object> result = operationsCenterService.getUrlNum(date_id);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 获取内容标签稽核数量
	 * 
	 */
	@RequestMapping(value = "/substanceNum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String substanceNum(String time) {
		try {
			Map<String, Object> result = operationsCenterService.substanceNum(time);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}


	/**
	 * 跳转未识别域名-智能识别页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/showLogInfo")
	public String showLogInfo() throws Exception {

		return "pages/jsp/dpi/operateCenter/operateLogInfo";
	}

	@RequestMapping(value = "/exportData")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<IncreaseProdName> list =operationsCenterService.getExportList(); 
		OperationFlowExportReturn ofer=new OperationFlowExportReturn();
		ofer.setList(list);
		ofer.setExcelName(sdf.format(new Date())+".xlsx");
		OperationFlowAction.doExportExcel(request, response, "export_data.xlsx", "0,1,2,5,6,7", ofer);
		operationsCenterService.updateExport(); 
		
	}
	/**
	 * 行业总览获取时间
	 * @return
	 */
	@RequestMapping(value = "/getOperationsCenterTime",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getOperationsCenterTime() {
		try {
			String time="";
			time=publicCPeriodService.selectDateId("1002");
			return Ajax.responseString(CST.RES_SECCESS, "",time);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
}
