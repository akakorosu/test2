package com.bonc.dpi.action;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.bonc.dpi.dao.entity.IndustryLabelAudit;
import com.bonc.dpi.dao.entity.UnidentifiedUrl;
import com.bonc.dpi.service.IndustryLabelAuditService;
import com.bonc.dpi.utils.DpiUtils;
import com.github.pagehelper.Page;

/**
 * 行业标签稽核
 * 
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/industryLabelAudit")
public class IndustryLabelAuditAction {
	private static final Logger logger = LoggerFactory.getLogger(OperationsCenterAction.class);
	@Autowired
	private IndustryLabelAuditService industryLabelAuditService;

	/**
	 * 获取行业标签稽核信息
	 * 
	 * @param time
	 * @param area
	 * @param gender
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist1", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<IndustryLabelAudit> selectPageList(IndustryLabelAudit vo, Integer page, Integer rows)
			throws Exception {
		Page<IndustryLabelAudit> pageList = industryLabelAuditService.selectList(vo, page, rows);
		PageJqGrid<IndustryLabelAudit> pageJqGrid = new PageJqGrid<IndustryLabelAudit>(pageList);
		return pageJqGrid;
	}

	/**
	 * 稽核更新DM_D_EV_LABEL_CHECK和dim_ia_product_info
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateCheck", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateCheck(String prodId) {
		try {
			int result = industryLabelAuditService.updateLabelCheck(prodId);
			industryLabelAuditService.updateProdInfoCheck(prodId);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}

	/**
	 * 批量稽核更新DM_D_EV_LABEL_CHECK和dim_ia_product_info
	 * 
	 * @return
	 */
	@RequestMapping(value = "/batchCheck", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String batchCheck(String prodId) {
		try {
			Map<String, Object> params = new HashMap<>();
			List<String> a = Arrays.asList(prodId.split(","));
			params.put("prodIds", a);
			int result = industryLabelAuditService.batchUpdateLabelCheck(params);
			industryLabelAuditService.batchUpdateProdInfoCheck(params);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
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
	public String showForm(HttpServletRequest request, String prodId,String dateId) throws Exception{
		IndustryLabelAudit vo=null;
		if(!StringUtils.isBlank(prodId)) {
			vo = industryLabelAuditService.selectVoById(prodId,dateId);
			if(!StringUtils.isEmpty(vo.getProdName())) {
				vo.setProdName(DpiUtils.strDecrypt(vo.getProdName()));
			}
			
			if(!StringUtils.isEmpty(vo.getLabelName1())) {
				//vo.setLabelName1(DpiUtils.strDecrypt(vo.getLabelName1()));
			}
			if(!StringUtils.isEmpty(vo.getLabelId2())) {
				//当二级标签不为空时，前端页面用二级标签显示
				vo.setLabelId1(vo.getLabelId2());
				vo.setLabelName1(vo.getLabelName2());
			}
			request.setAttribute("vo", vo);
		}
		
		return "pages/jsp/dpi/industryLabelAudit/industryLabelAuditForm";
		
		
	}
	/**
	 * 二级标签不为空  更新一级标签和二级标签
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateLabel2", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateLabel2(IndustryLabelAudit vo) {
		try {
			IndustryLabelAudit label=industryLabelAuditService.getLabel1ByLabel2(vo);
			vo.setLabelCode1(label.getLabelCode1());
			vo.setLabelName1(label.getLabelName1());
			int result = industryLabelAuditService.updateLabel2(vo);
			industryLabelAuditService.updateLabel2ProdInfo(vo);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 二级标签为空  更新一级标签
	 * 
	 * @return
	 */
	@RequestMapping(value = "/updateLabel1", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateLabel1(IndustryLabelAudit vo) {
		try {
			int result = industryLabelAuditService.updateLabel1(vo);
			industryLabelAuditService.updateLabel1ProdInfo(vo);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
}
