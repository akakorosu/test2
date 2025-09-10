package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.apache.derby.tools.sysinfo;
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
import com.bonc.dpi.dao.entity.DomainFuzzyRule;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.UrlParameter;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import com.bonc.dpi.service.DomainFuzzyRuleService;
import com.bonc.dpi.utils.DpiUtils;

/**
 * 域名模糊识别规则管理
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/domainFuzzyRule")
public class DomainFuzzyRuleAction {
	@Autowired
	DomainFuzzyRuleService domainFuzzyRuleService;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	/**
	 * 分页查询
	 * @param df
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<DomainFuzzyRule> selectPageList(DomainFuzzyRule df, Integer page, Integer rows) {
		//查询条件处理prod_name
		String prodName = df.getProdName();
		if (prodName != null && !"".equals(prodName)) {
			String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
			df.setProdName(prodName_res);
		}
		Page<DomainFuzzyRule> pageList = domainFuzzyRuleService.selectList(df, page, rows);
		PageJqGrid<DomainFuzzyRule> pageJqGrid = new PageJqGrid<DomainFuzzyRule>(pageList);
		return pageJqGrid;
	}
	/**
	 * 增加修改表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/viewById")
	public String viewById(HttpServletRequest request, String id) throws Exception{
		if(!StringUtils.isBlank(id)) {
			DomainFuzzyRule df=new DomainFuzzyRule();
			df.setId(id);
			DomainFuzzyRule vo = domainFuzzyRuleService.selectById(df);
			request.setAttribute("vo", vo);
		} 
		return "pages/jsp/dpi/domainFuzzyRule/domainFuzzyRuleView";
	}
	
	/**
	 * 查看页面表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showForm")
	public String showForm(HttpServletRequest request, String id) throws Exception{
		if(!StringUtils.isBlank(id)) {
			DomainFuzzyRule df=new DomainFuzzyRule();
			df.setId(id);
			DomainFuzzyRule vo = domainFuzzyRuleService.selectById(df);
			if(!StringUtils.isEmpty(vo.getParseRule())){
				vo.setParseRuleParam(vo.getParseRule().replaceAll("\"", "&quot;"));
			}
			if(!StringUtils.isEmpty(vo.getUrlExample())){
				vo.setUrlExample(vo.getUrlExample().replaceAll("\"", "&quot;"));
			}
			request.setAttribute("vo", vo);
			return "pages/jsp/dpi/domainFuzzyRule/domainFuzzyRuleFormInsert";
		}else {
			return "pages/jsp/dpi/domainFuzzyRule/domainFuzzyRuleFormInsert";
		}
		
	}
	
	/**
	 * 添加或者修改
	 * @param session
	 * @param vo
	 * @return
	 */
	@Transactional()
	@RequestMapping(value = "/insertOrUpdateVo")
	@ResponseBody
	public DomainFuzzyRule insert(HttpSession session,DomainFuzzyRule vo) throws NoSuchAlgorithmException {
		
		String UUID = UUIDUtil.createUUID();//
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = vo.getId();
		if (!StringUtils.isBlank(id)) {
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo.setAuthor(user.getLoginId());
			//String aaa=vo.getParseRuleParam();
			vo = domainFuzzyRuleService.update(vo);
			
		} else {
			vo.setId(UUID);
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo = domainFuzzyRuleService.insert(vo);		
		}
		
		return vo;
	}
	@Transactional()
	@RequestMapping(value = "/insertAndUpdateVo")
	@ResponseBody
	public String insertAndUpdateVo(HttpSession session,DomainFuzzyRule vo) throws NoSuchAlgorithmException {
		
		String UUID = UUIDUtil.createUUID();//
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = vo.getId();
		
		vo.setUUID(UUID);
		vo.setAuthor(user.getLoginId());
		vo.setOpTime(DpiUtils.sdf.format(new Date()));
		
		int check= this.domainFuzzyRuleService.checkDomainFuzzyRule(vo);
		if(check==0) {
			this.domainFuzzyRuleService.insertUuid(vo); //id自己生成
			domainFuzzyRuleService.updateUrlStu(vo);  //id 前台带的
		}else {
			
		}
		
		return Ajax.responseString(CST.RES_SECCESS,"",check);
	}
	/**
	 * 添加或者修改
	 * @param session
	 * @param vo
	 * @return
	 */
	@Transactional()
	@RequestMapping(value = "/insertOrNotDomainRule")
	@ResponseBody
	public String insertOrNotDomainRule(HttpSession session,DomainFuzzyRule vo) throws NoSuchAlgorithmException {
		
		String UUID = UUIDUtil.createUUID();
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		vo.setUUID(UUID);
		vo.setAuthor(user.getLoginId());
		vo.setOpTime(sdf.format(new Date()));
		int check= this.domainFuzzyRuleService.checkDomainRuleData(vo);
		if(check==0) {
			int a = this.domainFuzzyRuleService.insertDomainRule(vo); //id自己生成
			domainFuzzyRuleService.updateUrlStu(vo);  //id 前台带的
		}else {
			
		}
		
		return Ajax.responseString(CST.RES_SECCESS,"",check);
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById")
	@ResponseBody
	public Boolean deleteById(String id) throws Exception{
		DomainFuzzyRule df=new DomainFuzzyRule();
		df.setId(id);
		
		Boolean bl = domainFuzzyRuleService.deleteById(df);
		return bl;
	}
	
	/**
	 * 校验重复
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean check(DomainFuzzyRule vo) throws Exception{
		Boolean bl = domainFuzzyRuleService.check(vo);
		return bl;
	}
	/**
	 * 根据prodid 查询标签id
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getLabelById")
	@ResponseBody
	public String getLabelById(DomainFuzzyRule vo) throws Exception{
		

		DomainFuzzyRule dfr = domainFuzzyRuleService.getLabelById(vo);//查标签labelcode和prodCatagory  code
		if(dfr!=null) {
			if(dfr.getLabelCode()!=null){
				//String labelCode  = dfr.getLabelCode();
				DomainFuzzyRule label = domainFuzzyRuleService.getLabelByCode(dfr);//根据code查name
				if(StringUtils.isNotEmpty(label.getLabelCode2())){ //前台取code1
					dfr.setLabelCode1(label.getLabelCode2());
					dfr.setLabelName1(label.getLabelName2());
				}else{
					dfr.setLabelCode1(label.getLabelCode1());
					dfr.setLabelName1(label.getLabelName1());
				}
				DomainFuzzyRule labelPar=new DomainFuzzyRule();
				if(StringUtils.isNotEmpty(dfr.getProdCatagory())){
					
					labelPar.setLabelCode(dfr.getProdCatagory());
					DomainFuzzyRule labelReturn = domainFuzzyRuleService.getLabelByCode(labelPar);
					if(StringUtils.isNotEmpty(labelReturn.getLabelCode2())){ //前台取code1
						dfr.setProdCatagory(labelReturn.getLabelCode2());
						dfr.setProdCatagoryName(labelReturn.getLabelName2());
					}else{
						dfr.setProdCatagory(labelReturn.getLabelCode1());
						dfr.setProdCatagoryName(labelReturn.getLabelName1());
					}
				}
				
			}
			
			
			
			
		}
		return Ajax.responseString(CST.RES_SECCESS,"", dfr);
	}	
	/**
	 * 根据prodid 查询标签id
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkProdIdAndProdName")
	@ResponseBody
	public String checkProdIdAndProdName(DomainFuzzyRule vo) throws Exception{
		try {
			if(!StringUtils.isBlank(vo.getProdName())) {
				vo.setProdName( DpiUtils.strEncrypt(vo.getProdName()));// 字符串分词加密
			}
			int a=domainFuzzyRuleService.checkProdIdAndProdName(vo);
			return Ajax.responseString(CST.RES_SECCESS, a);
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_EXCEPTION, "签约失败！");
		}
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", domainFuzzyRuleService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", domainFuzzyRuleService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", domainFuzzyRuleService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", domainFuzzyRuleService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", domainFuzzyRuleService.list_false_cache);//excle中与库中主键过长
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/domainFuzzyRule/falseDataViewFromExcle";
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
		Boolean bl = domainFuzzyRuleService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
}
