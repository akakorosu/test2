package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
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
import com.bonc.dpi.dao.entity.SubstanceLabel;
import com.bonc.dpi.dao.entity.TopLevelDomain;
import com.bonc.dpi.service.AuditRuleErrorService;
import com.bonc.dpi.service.SubstanceLabelService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 稽核规则错误管理
 * 
 *
 */
@Controller
@RequestMapping(value = "/substanceLabel")
public class SubstanceLabelAction {
	@Autowired
	private SubstanceLabelService substanceLabelService;
	
	/**
	 * 获取一级域名识别信息
	 * @param time
	 * @param area
	 * @param gender
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<SubstanceLabel> selectPageList(SubstanceLabel vo, Integer page, Integer rows) throws Exception{
		Page<SubstanceLabel> pageList = substanceLabelService.selectList(vo, page, rows);
		PageJqGrid<SubstanceLabel> pageJqGrid = new PageJqGrid<SubstanceLabel>(pageList);
		return pageJqGrid;
	}
	/**
	 * 获取类别名
	 */
	@RequestMapping(value = "/getGroupType",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getGroupType() throws Exception{
		try {
			List<Map<String,String>> result=substanceLabelService.getGroupType();
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
	/**
	 * 获取prodId prodName
	 */
	@RequestMapping(value = "/getProdIdAndName",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getProdIdAndName(SubstanceLabel vo) throws Exception{
		try {
			List<SubstanceLabel> result=substanceLabelService.getProdIdAndName(vo);
			
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
	/**
	 * 获取prodId prodName
	 */
	@RequestMapping(value = "/initProdName",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String initProdName(SubstanceLabel vo) throws Exception{
		try {
			List<SubstanceLabel> result=substanceLabelService.getInitProdName(vo);
			
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
	@RequestMapping(value = "/initCatContentType",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String initCatContentType(SubstanceLabel vo) throws Exception{
		try {
			List<SubstanceLabel> result=substanceLabelService.getCatContentType(vo);
			
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
	/**
	 * 删除content
	 */
	@RequestMapping(value = "/delContent",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String delContent(SubstanceLabel vo) throws Exception{
		try {
			int result=substanceLabelService.delContent(vo);
			
			return Ajax.responseString(CST.RES_SECCESS, "",result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
	/**
	 * 更新content
	 */
	@Transactional
	@RequestMapping(value = "/checkContent",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String checkContent(SubstanceLabel vo) throws Exception{
		try {
			
			int result=substanceLabelService.checkContent(vo);
			String name=vo.getTableName();
            if(name.contains("map")){
				if(name.endsWith("video")){
					vo.setTableName("dim_ia_content_label_video");
				}
				if(name.endsWith("goods")){
					vo.setTableName("dim_ia_content_label_goods");
				}
				if(name.endsWith("novel")){
					vo.setTableName("dim_ia_content_label_novel");
				}
				if(name.endsWith("music")){
					vo.setTableName("dim_ia_content_label_music");
				}
			}else{
				if(name.endsWith("video")){
					vo.setTableName("dim_ia_map_content_label_video");
				}
				if(name.endsWith("goods")){
					vo.setTableName("dim_ia_map_content_label_goods");
				}
				if(name.endsWith("novel")){
					vo.setTableName("dim_ia_map_content_label_novel");
				}
				if(name.endsWith("music")){
					vo.setTableName("dim_ia_map_content_label_music");
				}
			}
           substanceLabelService.checkContent(vo);
			return Ajax.responseString(CST.RES_SECCESS, "",result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
	@RequestMapping(value = "/showForm")
	public String showForm(HttpServletRequest request, SubstanceLabel entity) throws Exception {
		
		SubstanceLabel vo = substanceLabelService.selectVoById(entity);
			
		request.setAttribute("vo", vo);
		
		return "pages/jsp/dpi/substanceLabel/substanceLabelForm";
	}
	@RequestMapping(value = "/showPiFormInsert")
	public String showPiFormInsert(HttpServletRequest request, SubstanceLabel entity) throws Exception {
		
		return "pages/jsp/dpi/substanceLabel/substanceLabelFormInsert";
	}
	@RequestMapping(value = "/comLikeSearch",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String comLikeSearch(String prodName ,String contentLabelName1) {
		try {
			//修改状态
			
			SubstanceLabel vo=new SubstanceLabel();
			vo.setProdName(prodName);
			vo.setContentLabelName1(contentLabelName1);
			List<SubstanceLabel> result=substanceLabelService.comLikeSearch(vo);
			for(int i=0;i<result.size();i++) {
				String html="";
				SubstanceLabel p=result.get(i);
				if(!StringUtils.isBlank(p.getContentLabelName1())){
					html+=p.getContentLabelName1()+">";
				}
				if(!StringUtils.isBlank(p.getContentLabelName2())){
					html+=p.getContentLabelName2()+">";
				}
				if(!StringUtils.isBlank(p.getContentLabelName3())){
					html+=p.getContentLabelName3()+">";
				}
				if(!StringUtils.isBlank(p.getContentLabelName4())){
					html+=p.getContentLabelName4()+">";
				}
				if(!StringUtils.isBlank(p.getContentLabelName5())){
					html+=p.getContentLabelName5()+">";
				}
				if(!StringUtils.isBlank(p.getContentLabelName6())){
					html+=p.getContentLabelName6()+">";
				}
				html=html.substring(0,html.length()-1);
				p.setLikeSearchString(html);
			}
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 更新content
	 */
	@RequestMapping(value = "/updateContentLabelCode",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateContentLabelCode(SubstanceLabel vo) throws Exception{
		try {
			int result=substanceLabelService.countData(vo);
			if (result==0){
				//更新
				String name=vo.getTableName();
	            if(name.contains("map")){
					
				}else{
					if(name.endsWith("video")){
						vo.setTableName("dim_ia_map_content_label_video");
					}
					if(name.endsWith("goods")){
						vo.setTableName("dim_ia_map_content_label_goods");
					}
					if(name.endsWith("novel")){
						vo.setTableName("dim_ia_map_content_label_novel");
					}
					if(name.endsWith("music")){
						vo.setTableName("dim_ia_map_content_label_music");
					}
				}
			    substanceLabelService.updateContentLabelCode(vo);
				
			}
			return Ajax.responseString(CST.RES_SECCESS, "",result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
	/**
	 * 更新content
	 */
	@RequestMapping(value = "/insertContentLabelCode",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String insertContentLabelCode(SubstanceLabel vo) throws Exception{
		try {
			int result=substanceLabelService.countData(vo);
			if (result==0){
				//插入
				 vo.setIsCheck("0");
				 vo.setProdName(DpiUtils.strEncrypt(vo.getProdName()));
				 substanceLabelService.insertContentLabelCode(vo);
				
			}
			return Ajax.responseString(CST.RES_SECCESS, "",result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
		
	}
}
