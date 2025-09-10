package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.dpi.dao.entity.DimIaUserAgentKey;
import com.bonc.dpi.dao.entity.DimIaUserAgentNoise;
import com.bonc.dpi.dao.entity.DimIaUserAgentRule;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.service.DimIaUserAgentNoiseService;
import com.bonc.dpi.service.DimIaUserAgentRuleService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value = "/uarule")
public class DimIaUserAgentRuleAction {
	
	@Resource
	private DimIaUserAgentRuleService dimIaUserAgentRuleService;
	private final SimpleDateFormat optTime = new SimpleDateFormat("yyyyMMdd");
	/**
	 * 分页查询
	 * @param sysUser
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/ruleselectpagelist")
	@ResponseBody
	public PageJqGrid<DimIaUserAgentRule> selectPageList(DimIaUserAgentRule dimIaUserAgentRule, Integer page, Integer rows) throws Exception {
		

		// 查询条件处理prod_name
		String prodName = dimIaUserAgentRule.getProdName();
		if (prodName != null && !"".equals(prodName)) {
			String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
			dimIaUserAgentRule.setProdName(prodName_res);
		}
		Page<DimIaUserAgentRule> pageList = this.dimIaUserAgentRuleService.selectPageList(dimIaUserAgentRule, page, rows);
		PageJqGrid<DimIaUserAgentRule> pageJqGrid = new PageJqGrid<DimIaUserAgentRule>(pageList);
		return pageJqGrid;
	}
	
	/**
	 * 表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/showruleform")
	public String showruleform(HttpServletRequest request, String id) throws Exception {
		if(!StringUtils.isBlank(id)) {
			DimIaUserAgentRule dimIaUserAgentRule = this.dimIaUserAgentRuleService.selectRuleById(id);
			/*
			String name = dimIaUserAgentRule.getProdName();
			dimIaUserAgentRule.setProdName(DpiUtils.strDecrypt(name));
			*/
			request.setAttribute("vo", dimIaUserAgentRule);
		}
		return "pages/jsp/dpi/dimIaUserAgentRule/dimIaUserAgentRuleForm";
	}
	@RequestMapping(value = "/showRuleFormInsert")
	public String showRuleFormInsert(HttpServletRequest request, String id,String ua) throws Exception {
		DimIaUserAgentRule vo =new DimIaUserAgentRule();
		vo.setUaKeyRule(id);
		vo.setUaExample(ua);  //ua 作为UaExample
		request.setAttribute("vo", vo);
		return "pages/jsp/dpi/dimIaUserAgentRule/dimIaUserAgentRuleFormInsert";
	}
	@RequestMapping(value = "/showRuleFormCheck")
	public String showRuleFormCheck(HttpServletRequest request,DimIaUserAgentRule vo) throws Exception {
		request.setAttribute("vo", vo);
		return "pages/jsp/dpi/dimIaUserAgentRule/checkRuleFormInsert";
	}
	
	/**
	 * 新增修改
	 * @param sysUser
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	
	@RequestMapping(value = "/insertorupdaterule")
	@ResponseBody
	public DimIaUserAgentRule insertOrUpdateSysuser(HttpSession session,DimIaUserAgentRule dimIaUserAgentRule) throws NoSuchAlgorithmException {
		String id = dimIaUserAgentRule.getId();
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		if(!StringUtils.isBlank(id)) {
			dimIaUserAgentRule.setAuthor(user.getLoginId());
			dimIaUserAgentRule.setOpTime(optTime.format(new Date()));
			
			/*
			String name = dimIaUserAgentRule.getProdName();
			dimIaUserAgentRule.setProdName(DpiUtils.strDecrypt(name));
			*/
			dimIaUserAgentRule = this.dimIaUserAgentRuleService.updateRule(dimIaUserAgentRule);
		} else {
			dimIaUserAgentRule.setAuthor(user.getLoginId());
			dimIaUserAgentRule.setOpTime(optTime.format(new Date()));

			dimIaUserAgentRule.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			/*
			String name = dimIaUserAgentRule.getProdName();
			dimIaUserAgentRule.setProdName(DpiUtils.strEncrypt(name));
			*/
			dimIaUserAgentRule = this.dimIaUserAgentRuleService.insertRule(dimIaUserAgentRule);
		}
		return dimIaUserAgentRule;
	}
	/**
	 * 新增修改
	 * @param sysUser
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value = "/insertRuleAndUpdateUa")
	@ResponseBody
	public DimIaUserAgentRule insertRuleAndUpdateUa(HttpSession session,DimIaUserAgentRule dimIaUserAgentRule) throws NoSuchAlgorithmException {
		String id = dimIaUserAgentRule.getId();
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		if(!StringUtils.isBlank(id)) {
			dimIaUserAgentRule.setAuthor(user.getLoginId());
			dimIaUserAgentRule.setOpTime(optTime.format(new Date()));
			
			/*
			String name = dimIaUserAgentRule.getProdName();
			dimIaUserAgentRule.setProdName(DpiUtils.strDecrypt(name));
			*/
			dimIaUserAgentRule = this.dimIaUserAgentRuleService.updateRule(dimIaUserAgentRule);
		} else {
			dimIaUserAgentRule.setAuthor(user.getLoginId());
			dimIaUserAgentRule.setOpTime(optTime.format(new Date()));

			dimIaUserAgentRule.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			/*
			String name = dimIaUserAgentRule.getProdName();
			dimIaUserAgentRule.setProdName(DpiUtils.strEncrypt(name));
			*/
			this.dimIaUserAgentRuleService.updateUaRule(dimIaUserAgentRule);
			dimIaUserAgentRule = this.dimIaUserAgentRuleService.insertRule(dimIaUserAgentRule);
		}
		return dimIaUserAgentRule;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/deleteRuleById")
	@ResponseBody
	public DimIaUserAgentRule deleteRuleByProdId(String id) throws Exception {
		DimIaUserAgentRule bl = new DimIaUserAgentRule();
		bl.setId(id);
		dimIaUserAgentRuleService.deleteRuleById(bl);
		return bl; 
	}
	/**
	 * 校验重复
	 * @param dimIaUserAgentRule
	 * @return
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean check(DimIaUserAgentRule dimIaUserAgentRule) throws Exception {
		
		Boolean bl = this.dimIaUserAgentRuleService.check(dimIaUserAgentRule);
		return bl;	
	}
	
	/**
	 * 校验产品Id
	 * @param dimIaUserAgentRule
	 * @return
	 */
	@RequestMapping(value = "/checkId")
	@ResponseBody
	public Boolean checkId(DimIaUserAgentRule dimIaUserAgentRule) throws Exception {
		
		Boolean bl = this.dimIaUserAgentRuleService.checkId(dimIaUserAgentRule);
		return bl;	
	}
	/**
	 * 问题数据查看页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/falseDataViewFromExcle")
	public String falseDataViewFromExcle(HttpServletRequest request) throws Exception{
		
		//列表信息
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", dimIaUserAgentRuleService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", dimIaUserAgentRuleService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", dimIaUserAgentRuleService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", dimIaUserAgentRuleService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", dimIaUserAgentRuleService.list_false_cache);//excle中与库中主键过长
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/dimIaUserAgentRule/falseDataViewFromExcle";
	}
	
	/**
	 * 问题数据插入库(将excle中在库中重复的数据)
	 * @param request
	 * @param type  1:舍弃，2：更新
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/falseDataFromExcleUpdate")
	@ResponseBody
	public String falseDataFromExcleUpdate(HttpServletRequest request,String type)throws Exception{
		String result="0";
		Boolean bl = dimIaUserAgentRuleService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
}



