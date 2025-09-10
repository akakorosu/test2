package com.bonc.dpi.action;

import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.tools.ant.taskdefs.Get.VerboseProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.KeywordGroup;
import com.bonc.dpi.dao.entity.KeywordSearchRule;
import com.bonc.dpi.dao.entity.PositionRule;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.service.KeywordGroupService;
import com.bonc.dpi.service.PositionRuleService;
import com.bonc.dpi.service.ProductInfoService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 
 * 位置信息规则
 * dim_ia_position_rule
 *
 * @author zhouping
 * @since 2019年11月13日
 */

@Controller
@RequestMapping(value = "/positionRule")
public class PositionRuleAction {

	@Resource
	private PositionRuleService positionRuleService;
	
	@Autowired
	private KeywordGroupService kgService;
	
	@RequestMapping(value = "/selectlistsimple")
	@ResponseBody
	public List<PositionRule> selectListSimple(PositionRule vo) throws Exception{
		List<PositionRule> list = this.positionRuleService.selectListSimple(vo);
		return list;
	}

	/**
	 * 分页查询
	 * @param ksr
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<PositionRule> selectPageList(HttpServletRequest request,PositionRule vo, Integer page, Integer rows) throws Exception{
		Page<PositionRule> pageList = this.positionRuleService.selectList(vo, page, rows);
		PageJqGrid<PositionRule> pageJqGrid = new PageJqGrid<PositionRule>(pageList);
		return pageJqGrid;
	}

	/**
	 * 增加修改表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showForm")
	public String showForm(HttpServletRequest request, String id) throws Exception{
		if(!StringUtils.isBlank(id)) {
			PositionRule vo = this.positionRuleService.selectVoById(id);
						
			KeywordGroup kgParam = new KeywordGroup();
			kgParam.setGroupType(vo.getGroupType());
			KeywordGroup voKg = kgService.selectVoByPrimaryKey(kgParam);
			if(voKg!=null){
				vo.setGroupName(voKg.getGroupName());
			}
			if(!StringUtils.isEmpty(vo.getLongitudeRule())){
				vo.setLongitudeRule(vo.getLongitudeRule().replaceAll("\"", "&quot;"));
			}
			if(!StringUtils.isEmpty(vo.getLatitudeRule())){
				vo.setLatitudeRule(vo.getLatitudeRule().replaceAll("\"", "&quot;"));
			}
			if(!StringUtils.isEmpty(vo.getSystemRule())){
				vo.setSystemRule(vo.getSystemRule().replaceAll("\"", "&quot;"));
			}
			
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/positionRule/positionRuleForm";
	}
	
	/**
	 * 查看页面表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/viewVoById")
	public String viewVoById(HttpServletRequest request, String id) throws Exception{
		if(!StringUtils.isBlank(id)) {
			PositionRule vo = this.positionRuleService.selectVoById(id);
					
			KeywordGroup kgParam = new KeywordGroup();
			kgParam.setGroupType(vo.getGroupType());
			KeywordGroup voKg = kgService.selectVoByPrimaryKey(kgParam);
			if(voKg!=null){
				vo.setGroupName(voKg.getGroupName());
			}
			if(!StringUtils.isEmpty(vo.getLongitudeRule())){
				vo.setLongitudeRule(vo.getLongitudeRule().replaceAll("\"", "&quot;"));
			}
			if(!StringUtils.isEmpty(vo.getLatitudeRule())){
				vo.setLatitudeRule(vo.getLatitudeRule().replaceAll("\"", "&quot;"));
			}
			if(!StringUtils.isEmpty(vo.getSystemRule())){
				vo.setSystemRule(vo.getSystemRule().replaceAll("\"", "&quot;"));
			}
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/positionRule/positionRuleView";
	}
	
	/**
	 * 校验规则页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/checkRuleForm")
	public String getCheckRuleForm(HttpServletRequest request, String type,String rule) throws Exception{
		request.setAttribute("rule", rule);
		request.setAttribute("type", type);
		return "pages/jsp/dpi/positionRule/checkRuleForm";
	}
	
	/**
	 * 校验规则
	 * @param request
	 * @param rule：正则
	 * @param url：url
	 * @param num：分组group位置
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkRule")
	@ResponseBody
	public String checkRule(String rule,String url,Integer num) throws Exception{
		
//		rule = "/.*&_iwt_vid=([0-9a-z]+)";
//		url = "qq.irs01.com/irt?_iwt_UA=UA-qq-120001&jsonp=SetIDA0&_iwt_vid=cq88y54kd9gkiyb&_iwt_muid=1272BE17D87E4AF6&_iwt_reqid=0OFoJGYAWzYK0wt00QDZqWJfAeh0&os=0&imei=1272BE17D87E4AF608A9FBAA8BEE41B6&mac=B345CE0F66F952B17EB19ED79D847278&idfa=&ip=36.104.225.208&useragent=[UA]&ts=[timestampe]&androidid=8AD72D99010D56DE5CE62221D17CC8A8&openudid=4538397881e90c1c95339f7c95802d40&_z=m&_iwt_vid1=v0026nvfau0&requestid=null";
//		num = 1;
		String result = "";
		rule = URLDecoder.decode(rule, "UTF-8");
		url = URLDecoder.decode(url, "UTF-8");
		Pattern p = Pattern.compile(rule);
		Matcher m = p.matcher(url);
		if(m.find()){
			try {
				result = m.group(num);
			} catch (Exception e) {
				result = "输入规则或者url异常";
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			result = "无结果";
		}
		return result;
	}
	
	/**
	 * 添加或者修改
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateVo")
	@ResponseBody
	public PositionRule insertVo(HttpSession session,PositionRule vo) throws NoSuchAlgorithmException {
		
		String UUID = UUIDUtil.createUUID();//
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = vo.getId();
		String host = vo.getHostParam();
		if(StringUtils.isNotEmpty(host)) {
			vo.setHostParam(host.replaceAll("&amp;", "&"));
		}
		String url = vo.getUrl();
		if(StringUtils.isNotEmpty(url)) {
			vo.setUrl(url.replaceAll("&amp;", "&"));
		}
		String longitudeRule = vo.getLongitudeRule();
		if(StringUtils.isNotEmpty(longitudeRule)) {
			vo.setLongitudeRule(longitudeRule.replaceAll("&amp;", "&"));
		}
		String latitudeRule = vo.getLatitudeRule();
		if(StringUtils.isNotEmpty(latitudeRule)) {
			vo.setLatitudeRule(latitudeRule.replaceAll("&amp;", "&"));
		}
		String systemRule = vo.getSystemRule();
		if(StringUtils.isNotEmpty(systemRule)) {
			vo.setSystemRule(systemRule.replaceAll("&amp;", "&"));
		}
		if(!StringUtils.isBlank(id)) {
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			this.positionRuleService.updateVo(vo);
		} else {
			vo.setId(UUID);
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo.setIsValid("1");
			this.positionRuleService.insertVo(vo);
		}
		
		return vo;
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteVoById")
	@ResponseBody
	public Boolean deleteVoById(String id) throws Exception{
		Boolean bl = this.positionRuleService.deleteVoById(id);
		return bl;
	}
	
	/**
	 * 校验重复
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkRepeat")
	@ResponseBody
	public Boolean checkRepeat(PositionRule vo) throws Exception{
		Boolean bl = this.positionRuleService.checkRepeat(vo);
		return bl;
	}

	/**
	 * 校验GroupType
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkGroupType")
	@ResponseBody
	public Boolean checkGroupType(PositionRule vo) throws Exception{
		Boolean bl = this.positionRuleService.checkGroupType(vo);
		return bl;
	}
	
	/**
	 * 校验GroupType
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkGroupName")
	@ResponseBody
	public Boolean checkGroupName(PositionRule vo) throws Exception{
		Boolean bl = this.positionRuleService.checkGroupName(vo);
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", positionRuleService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", positionRuleService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", positionRuleService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", positionRuleService.invalidDataInExcle_notTrue_prodId);//业务验证,产品id
		request.setAttribute("invalidDataInExcle_notTrue_groupType", positionRuleService.invalidDataInExcle_notTrue_groupType);//业务验证,应用分组编码   
		request.setAttribute("list_false_cache", positionRuleService.list_false_cache);//excle中与库中主键过长
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/positionRule/falseDataViewFromExcle";
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
		Boolean bl = positionRuleService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
}
