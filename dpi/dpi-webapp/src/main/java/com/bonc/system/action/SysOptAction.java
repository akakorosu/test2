package com.bonc.system.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.dpi.config.Constant;
import com.bonc.system.service.SysOptService;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

@Controller
@RequestMapping(value = "/sysopt")
public class SysOptAction {
	
	private static final Logger logger=LoggerFactory.getLogger(SysOptAction.class);
	
	@Resource
	SysOptService sysOptService;
	
	@RequestMapping(value = "/insert_rd_vos")
	@ResponseBody
	public Object insertRdVos() {
		return this.sysOptService.insertRdVos();
	}
	
	@RequestMapping(value = "/getSysMonth",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getSysMonth() {
		try {
			String lastMonth = DateUtil.lastMonth().toString(Constant.DATEPATTERN_YYYYMM);
			return Ajax.responseString(CST.RES_SECCESS, "", lastMonth);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/getSysDay",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getSysDay() {
		try {
			String yesterday = DateUtil.yesterday().toString(DatePattern.PURE_DATE_PATTERN);
			return Ajax.responseString(CST.RES_SECCESS, "", yesterday);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	
}
