package com.bonc.dpi.action;

import java.util.Date;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.dpi.dao.entity.RuleCheck;
import com.bonc.dpi.service.RuleCheckService;
import com.bonc.dpi.utils.DpiUtils;
import com.github.pagehelper.Page;

/**
 * 规则校验
 * @author wh
 *
 */
@Controller
@RequestMapping(value = "/ruleCheck")
public class RuleCheckAction {
	
	@Resource
	private RuleCheckService ruleCheckService;
	
	/**
	 * 分页查询
	 * @param ksr
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<RuleCheck> selectPageList(RuleCheck vo, Integer page, Integer rows) throws Exception{
		Page<RuleCheck> pageList = ruleCheckService.selectList(vo, page, rows);
		PageJqGrid<RuleCheck> pageJqGrid = new PageJqGrid<RuleCheck>(pageList);
		return pageJqGrid;
	}

}
