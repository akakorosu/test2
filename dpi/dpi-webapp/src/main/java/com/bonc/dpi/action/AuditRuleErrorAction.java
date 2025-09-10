package com.bonc.dpi.action;

import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.AuditRuleError;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.service.AuditRuleErrorService;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 稽核规则错误管理
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/auditRuleError")
public class AuditRuleErrorAction {
	@Autowired
	private AuditRuleErrorService auditRuleErrorService;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	/**
	 * 获取一级域名识别信息
	 * @param time
	 * @param area
	 * @param gender
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<AuditRuleError> selectPageList(AuditRuleError vo, Integer page, Integer rows) throws Exception{
		Page<AuditRuleError> pageList = auditRuleErrorService.selectList(vo, page, rows);
		PageJqGrid<AuditRuleError> pageJqGrid = new PageJqGrid<AuditRuleError>(pageList);
		return pageJqGrid;
	}
	/**
	 * 获取表名
	 */
	@RequestMapping(value = "/getTableName",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getTableName() throws Exception{
		try {
			List<Map<String,String>> result=auditRuleErrorService.getTableName();
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
	/**
	 * 验证Excel的条数
	 */
	@RequestMapping(value = "/getExcelList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getExcelList(AuditRuleError vo) throws Exception{
		try {
			List<AuditRuleError> list =auditRuleErrorService.getExportList(vo); 
			return Ajax.responseString(CST.RES_SECCESS, "",list.size());
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
	@RequestMapping(value = "/exportData")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response,AuditRuleError vo) throws Exception {
		String name=URLDecoder.decode(vo.getTableName(),"UTF-8");
		vo.setTableName(name);
		List<AuditRuleError> list =auditRuleErrorService.getExportList(vo); 
		if(list.size()<20000){
			OperationFlowExportReturn ofer=new OperationFlowExportReturn();
			ofer.setList(list);
			ofer.setExcelName("data_error"+sdf.format(new Date())+".xlsx");
			OperationFlowAction.doExportExcel(request, response, "data_error.xlsx", "0,1,7,2,6", ofer);
		}
		
		
		
	}
}
