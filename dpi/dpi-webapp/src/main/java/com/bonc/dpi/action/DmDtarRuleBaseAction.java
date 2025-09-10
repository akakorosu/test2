package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.utils.PageJqGrid;
import com.bonc.dpi.dao.entity.DmDtarRuleBase;
import com.bonc.dpi.service.DmDtarRuleBaseService;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value = "/dmdtarrulebase")
public class DmDtarRuleBaseAction {
	
	@Resource
	private DmDtarRuleBaseService dmDtarRuleBaseService;

	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<DmDtarRuleBase> selectPageList(DmDtarRuleBase dmDtarRuleBase, Integer page, Integer rows) {
		Page<DmDtarRuleBase> pageList = this.dmDtarRuleBaseService.selectPageList(dmDtarRuleBase, page, rows);
		PageJqGrid<DmDtarRuleBase> pageJqGrid = new PageJqGrid<DmDtarRuleBase>(pageList);
		return pageJqGrid;
	}
	
	@RequestMapping(value = "/selectlist")
	@ResponseBody
	public List<DmDtarRuleBase> selectList(DmDtarRuleBase dmDtarRuleBase) {
		List<DmDtarRuleBase> list = this.dmDtarRuleBaseService.selectList(dmDtarRuleBase);
		return list;
	}
	
	@RequestMapping(value = "/showform")
	public String showForm(HttpServletRequest request, String id) {
		if(!StringUtils.isBlank(id)) {
			DmDtarRuleBase dmDtarRuleBase = this.dmDtarRuleBaseService.selectById(id);
			request.setAttribute("vo", dmDtarRuleBase);
		}
		return "pages/jsp/dpi/dmDtarRuleBase/dmDtarRuleBaseForm";
	}
	
	@RequestMapping(value = "/insertorupdate")
	@ResponseBody
	public Boolean insertOrUpdate(DmDtarRuleBase dmDtarRuleBase) throws NoSuchAlgorithmException {
		String id = dmDtarRuleBase.getId();
		if(!StringUtils.isBlank(id)) {
			this.dmDtarRuleBaseService.update(dmDtarRuleBase);
		} else {
			this.dmDtarRuleBaseService.insert(dmDtarRuleBase);
		}
		return true;
	}
	
	@RequestMapping(value = "/deletebyid")
	@ResponseBody
	public Boolean deleteById(String id) {
		this.dmDtarRuleBaseService.deleteById(id);
		return true;
	}
	
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean check(DmDtarRuleBase dmDtarRuleBase) {
		Boolean bl = this.dmDtarRuleBaseService.selectCheck(dmDtarRuleBase);
		return bl;
	}
}