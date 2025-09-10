package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.bonc.dpi.dao.entity.DmDtarRuleInfo;
import com.bonc.dpi.service.DmDtarRuleInfoService;

@Controller
@RequestMapping(value = "/dmdtarruleinfo")
public class DmDtarRuleInfoAction {
	
	@Resource
	private DmDtarRuleInfoService dmDtarRuleInfoService;

	@RequestMapping(value = "/showform")
	public String showForm(HttpServletRequest request, String tarBaseId) {
		if(!StringUtils.isBlank(tarBaseId)) {
			// 查询
			DmDtarRuleInfo dmDtarRuleInfo = new DmDtarRuleInfo();
			dmDtarRuleInfo.setTarBaseId(tarBaseId);
			List<DmDtarRuleInfo> list = this.dmDtarRuleInfoService.selectList(dmDtarRuleInfo);
			request.setAttribute("list", JSONArray.toJSONString(list));
		}
		return "pages/jsp/dpi/dmDtarRuleInfo/dmDtarRuleInfoForm";
	}
	
	@RequestMapping(value = "/insert")
	@ResponseBody
	public Boolean insert(String tarBaseId, String listStr) throws NoSuchAlgorithmException {
		List<DmDtarRuleInfo> list = JSONArray.parseArray(listStr, DmDtarRuleInfo.class);
		this.dmDtarRuleInfoService.insert(list, tarBaseId);
		return true;
	}
}