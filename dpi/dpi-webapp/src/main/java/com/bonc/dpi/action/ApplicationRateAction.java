package com.bonc.dpi.action;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.dpi.dao.entity.IncreaseProdName;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.service.PublicCPeriodService;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bonc.dpi.dao.entity.ApplicationRate;
import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.dpi.service.ApplicationRateService;

/**
 * 产品应用识别
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/applicationRate")
public class ApplicationRateAction {
	@Autowired
	private ApplicationRateService applicationRateService;
	@Autowired
	PublicCPeriodService publicCPeriodService;
	private static final Logger logger=LoggerFactory.getLogger(ApplicationRateAction.class);
	private static List<ApplicationRate> list;
	/**
	 * 获取时间
	 * @return
	 */
	@RequestMapping(value = "/getTime",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getTime(String code) {
		try {
			String result=publicCPeriodService.selectDateId(code);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}

	@RequestMapping(value = "/getApplicationData",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getApplicationData(ApplicationRate vo) throws Exception{
		try {
			list=applicationRateService.getApplicationData(vo);
			return Ajax.responseString(CST.RES_SECCESS, list);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}

	@RequestMapping(value = "/showForm",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String viewById(HttpServletRequest request,ApplicationRate vo) throws Exception{
		request.setAttribute("type",vo.getType());
		request.setAttribute("dateId",vo.getDateId());
		return "pages/jsp/dpi/applicationRate/applicationRateView";
	}

	@RequestMapping(value = "/selectPageList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<ApplicationRate> selectPageList(ApplicationRate vo, Integer page, Integer rows) throws Exception{
		Page<ApplicationRate> pageList = applicationRateService.selectList(vo, page, rows);
		PageJqGrid<ApplicationRate> pageJqGrid = new PageJqGrid<ApplicationRate>(pageList);
		return pageJqGrid;
	}


	@RequestMapping(value = "/exportData")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,ApplicationRate vo) throws Exception {
		List<ApplicationRate> list=applicationRateService.getExportList(vo);
		if(list!=null&&list.size()>0){
			for(ApplicationRate obj:list){
				obj.setYyjlSblPer(obj.getYyjlSblPer()+"%");
				obj.setYyllSblPer(obj.getYyllSblPer()+"%");
			}
		}
		OperationFlowExportReturn ofer=new OperationFlowExportReturn();
		ofer.setList(list);
		ofer.setExcelName("application_rate.xlsx");
		OperationFlowAction.doExportExcel(request, response, "application_rate.xlsx", "0,1,2,3,4,5,6,7", ofer);
	}
}
