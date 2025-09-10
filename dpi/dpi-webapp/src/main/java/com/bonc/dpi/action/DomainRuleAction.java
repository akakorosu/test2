package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.service.DomainRuleService;
import com.bonc.dpi.service.ProductInfoService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 域名表
 * dim_ia_domain_rule
 * @author BONC-XUXL
 *
 */
@Controller
@RequestMapping(value = "/domainRule")
public class DomainRuleAction {
	
	@Resource
	private DomainRuleService drService;
	
	@Resource
	private ProductInfoService piService;
	
	/**
	 * 分页查询
	 * @param ksr
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<DomainRule> selectPageList(DomainRule vo, Integer page, Integer rows) throws Exception{
		
		//查询条件处理prod_name
		String prodName = vo.getProdName();
		if(prodName!=null && !"".equals(prodName)){
			String prodName_res = DpiUtils.strEncrypt(prodName);//字符串分词加密
			vo.setProdName(prodName_res);
		}
		
		Page<DomainRule> pageList = this.drService.selectList(vo, page, rows);
		PageJqGrid<DomainRule> pageJqGrid = new PageJqGrid<DomainRule>(pageList);
		return pageJqGrid;
	}

	/**
	 * 增加修改表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showForm")
	public String showForm(HttpServletRequest request, String domainCode) throws Exception{
		
		if(!StringUtils.isBlank(domainCode)) {
			DomainRule voParam = new DomainRule();
			voParam.setDomainCode(domainCode);
			DomainRule vo = this.drService.selectVoByPrimaryKey(voParam);
			
			vo.setDomainCodeOld(domainCode);//校验主键用
			
			ProductInfo piParam = new ProductInfo();
			piParam.setProdId(vo.getProdId());
			ProductInfo pi = piService.selectVoByPrimaryKey(piParam);
			String prodName = "";
			if(pi!=null && !"".equals(pi)){
				prodName = pi.getProdName();
			}
			//查询条件处理prod_name
			String prodName_res = DpiUtils.strDecrypt(prodName);//字符串分词解密
			vo.setProdName(prodName_res);
			
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/domainRule/domainRuleForm";
	}
	
	/**
	 * 查看页面表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/viewVoById")
	public String viewVoById(HttpServletRequest request, String domainCode) throws Exception{
		
		if(!StringUtils.isBlank(domainCode)) {
			DomainRule voParam = new DomainRule();
			voParam.setDomainCode(domainCode);
			DomainRule vo = this.drService.selectVoByPrimaryKey(voParam);
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/domainRule/domainRuleView";
	}
	
	/**
	 * 添加或者修改
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateVo")
	@ResponseBody
	public DomainRule insertVo(HttpSession session,DomainRule vo) throws NoSuchAlgorithmException {
		
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String domainCodeOLd = vo.getDomainCodeOld();//校验主键用
		String UUID = UUIDUtil.createUUID();//
		if(!StringUtils.isBlank(domainCodeOLd)) {
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			this.drService.updateVo(vo);
		} else {
			vo.setId(UUID);
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			this.drService.insertVo(vo);
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
	public Boolean deleteVoById(String domainCode) throws Exception{
		
		DomainRule voParam = new DomainRule();
		voParam.setDomainCode(domainCode);
		
		Boolean bl = this.drService.deleteVoByPrimaryKey(voParam);
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
	public Boolean check(DomainRule vo) throws Exception{
		Boolean bl = true;
		String domainCodeOld = vo.getDomainCodeOld();//改之前的
		String domainCodeNew = vo.getDomainCode();//新的domainCode
		
		//修改页面
		if(!"".equals(domainCodeOld) && domainCodeOld!=null){
			//如果domainCode变化了
			if(!domainCodeOld.equals(domainCodeNew)){
				bl = !checkPrimaryKey(domainCodeNew);
			}
		}
		//新增页面
		else{
			bl = !checkPrimaryKey(domainCodeNew);
		}

		return bl;
	}
	
	/**
	 * 判断主键是否存在
	 * @return
	 */
	public Boolean checkPrimaryKey(String domainCodeNew){
		
		DomainRule voParam = new DomainRule();
		voParam.setDomainCode(domainCodeNew);
		DomainRule voCheck = this.drService.selectVoByPrimaryKey(voParam);
        return voCheck != null;//存在
//不存在
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", drService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", drService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", drService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", drService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", drService.list_false_cache);//excle中与库中主键重复
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/domainRule/falseDataViewFromExcle";
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
		Boolean bl = drService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}

}
