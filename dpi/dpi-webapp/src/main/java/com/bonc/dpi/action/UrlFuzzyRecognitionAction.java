package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.AuditRuleError;
import com.bonc.dpi.dao.entity.IndustryLabelAudit;
import com.bonc.dpi.dao.entity.UrlFuzzyRecognition;
import com.bonc.dpi.service.AuditRuleErrorService;
import com.bonc.dpi.service.UrlFuzzyRecognitionService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 稽核规则错误管理
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/urlFuzzyRecognition")
public class UrlFuzzyRecognitionAction {
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	@Autowired
	private UrlFuzzyRecognitionService urlFuzzyRecognitionService;
	
	/**
	 * 获取一级域名识别信息
	 * @param time
	 * @param area
	 * @param gender
	 * @return
	 * 
	 */
	
	@RequestMapping(value = "/selectpagelist",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<UrlFuzzyRecognition> selectPageList(UrlFuzzyRecognition vo, Integer page, Integer rows) throws Exception{
		Page<UrlFuzzyRecognition> pageList = urlFuzzyRecognitionService.selectList(vo, page, rows);
		PageJqGrid<UrlFuzzyRecognition> pageJqGrid = new PageJqGrid<UrlFuzzyRecognition>(pageList);
		return pageJqGrid;
	}
	/**
	 * 获取表名
	 */
	@RequestMapping(value = "/getTableName",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getTableName() throws Exception{
		try {
			List<Map<String,String>> result=urlFuzzyRecognitionService.getTableName();
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
	/**
	 * 增加修改表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showForm")
	public String showForm(HttpServletRequest request, String url) throws Exception{
		request.setAttribute("url", url);
		return "pages/jsp/dpi/urlFuzzyRecognition/domainFuzzyRuleFormInsert";
		
		
	}
	/**
	 * 增加修改表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showPiFormYuMing")
	public String showPiFormYuMing(HttpServletRequest request, String url) throws Exception{
		request.setAttribute("url", url);
		return "pages/jsp/dpi/urlFuzzyRecognition/domainRuleFormYuMing";
		
		
	}
	/**
	 * 查询域名表insertIntoExportTable
	 */
	@RequestMapping(value = "/checkDomainRule",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String checkDomainRule(String domainCode) throws Exception{
		try {
			List<Map<String,Object>> result=urlFuzzyRecognitionService.checkDomainRule(domainCode);
			if(result.size()>0) {
				for(int i=0;i<result.size();i++ ) {
					Map<String,Object> map=result.get(i);
					map.put("PROD_NAME", DpiUtils.strDecrypt(map.get("PROD_NAME").toString()));
				}
			}
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
	/**
	 * 插入导出表并更新 DM_D_IA_UNIDENTIFIED_URL 表的状态   insertIntoExportTable
	 */
	@Transactional()
	@RequestMapping(value = "/insertIntoExportTable",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String insertIntoExportTable(HttpSession session,UrlFuzzyRecognition vo) throws Exception{
		try {
			SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
			vo.setOpTime(sdf.format(new Date()));
			vo.setAuthor(user.getLoginId());
			int result1=urlFuzzyRecognitionService.updateUrl(vo);
			int result=urlFuzzyRecognitionService.insertIntoExportTable(vo);
			
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
}
