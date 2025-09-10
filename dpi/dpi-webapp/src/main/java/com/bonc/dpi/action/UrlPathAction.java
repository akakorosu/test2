package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.entity.UrlPath;
import com.bonc.dpi.service.ProductInfoService;
import com.bonc.dpi.service.UrlPathService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * url路径规则表Action
 * dim_ia_url_path
 * @author BONC-XUXL
 *
 */
@Controller
@RequestMapping(value = "/urlPath")
public class UrlPathAction {

	@Resource
	private UrlPathService upService;
	
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
	public PageJqGrid<UrlPath> selectPageList(UrlPath vo, Integer page, Integer rows) throws Exception{
		
		//查询条件处理prod_name
		String prodName = vo.getProdName();
		if(prodName!=null && !"".equals(prodName)){
			String prodName_res = DpiUtils.strEncrypt(prodName);//字符串分词加密
			vo.setProdName(prodName_res);
		}
		
		Page<UrlPath> pageList = this.upService.selectList(vo, page, rows);
		PageJqGrid<UrlPath> pageJqGrid = new PageJqGrid<UrlPath>(pageList);
		return pageJqGrid;
	}

	/**
	 * 增加修改表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showForm")
	public String showForm(HttpServletRequest request,String id) throws Exception{
		
		if(!(StringUtils.isBlank(id))) {
			UrlPath vo = this.upService.selectVoById(id);
			
			ProductInfo piParam = new ProductInfo();
			piParam.setProdId(vo.getProdId());
			ProductInfo pi = piService.selectVoByPrimaryKey(piParam);
			String prodName = pi.getProdName();
			//查询条件处理prod_name
			String prodName_res = DpiUtils.strDecrypt(prodName);//字符串分词解密
			vo.setProdName(prodName_res);
			
			if(!StringUtils.isEmpty(vo.getRegexpRule())){
				vo.setRegexpRule(vo.getRegexpRule().replaceAll("\"", "&quot;"));
			}
			if(!StringUtils.isEmpty(vo.getUrlExample())){
				vo.setUrlExample(vo.getUrlExample().replaceAll("\"", "&quot;"));
			}
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/urlPath/urlPathForm";
	}
	
	/**
	 * 查看页面表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/viewVoById")
	public String viewVoById(HttpServletRequest request, String id) throws Exception{
		if(!(StringUtils.isBlank(id))) {
			UrlPath vo = this.upService.selectVoById(id);

			ProductInfo piParam = new ProductInfo();
			piParam.setProdId(vo.getProdId());
			ProductInfo pi = piService.selectVoByPrimaryKey(piParam);
			String prodName = pi.getProdName();
			//查询条件处理prod_name
			String prodName_res = DpiUtils.strDecrypt(prodName);//字符串分词解密
			vo.setProdName(prodName_res);
			
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/urlPath/urlPathView";
	}
	
	/**
	 * 添加或者修改
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateVo")
	@ResponseBody
	public UrlPath insertVo(HttpSession session,UrlPath vo) throws NoSuchAlgorithmException {
		
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = vo.getId();//校验主键用
		String UUID = UUIDUtil.createUUID();//
		if(!(StringUtils.isBlank(id))) {
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			this.upService.updateVo(vo);
		} else {
			vo.setId(UUID);
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			
			this.upService.insertVo(vo);
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
		Boolean bl = this.upService.deleteVoById(id);
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
	public Boolean check(UrlPath vo) throws Exception{
		Boolean bl = this.upService.selectCheck(vo);
		return bl;
	}
	
	/**
	 * 判断主键是否存在
	 * @return
	 */
	public Boolean checkPrimaryKey(String hostNew,String regexpRuleNew){
		
		UrlPath voParam = new UrlPath();
		voParam.setHost(hostNew);
		voParam.setRegexpRule(regexpRuleNew);
		UrlPath voCheck= this.upService.selectVoByPrimaryKey(voParam);
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", upService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", upService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", upService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", upService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", upService.list_false_cache);//excle中与库中主键过长
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/urlPath/falseDataViewFromExcle";
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
		Boolean bl = upService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
}
