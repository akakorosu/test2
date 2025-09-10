package com.bonc.dpi.action;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.DepthRuleCheck;
import com.bonc.dpi.dao.entity.PositionCheck;
import com.bonc.dpi.dao.entity.DepthRuleCheck;
import com.bonc.dpi.dao.entity.ProductLabel;
import com.bonc.dpi.service.DepthRuleCheckService;
import com.bonc.dpi.service.PositionCheckService;
import com.bonc.dpi.service.DepthRuleCheckService;
import com.bonc.dpi.service.ProductLabelService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


/**
 * 深度规则校验
 */
@Controller
@RequestMapping(value = "/positionCheck")
public class PositionCheckAction {

	@Resource
	private PositionCheckService cwlService;


	/**
	 * 分页查询
	 * @param vo
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<PositionCheck> selectPageList(PositionCheck vo, Integer page, Integer rows) throws Exception{
		Page<PositionCheck> pageList = this.cwlService.selectList(vo, page, rows);
		PageJqGrid<PositionCheck> pageJqGrid = new PageJqGrid<PositionCheck>(pageList);
		return pageJqGrid;
	}

	/**
	 * 稽核
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteUrl")
	@ResponseBody
	public Boolean audit(@RequestParam String host) throws Exception{
		return cwlService.audit(host);
	}
	@RequestMapping(value = "/audit")
	@ResponseBody
	public Boolean audit1(@RequestParam String host) throws Exception{
		return cwlService.audit1(host);
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", cwlService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", cwlService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", cwlService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("list_false_cache", cwlService.list_false_cache);//excle中与库中主键重复
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/depthRuleCheck/falseDataViewFromExcle";
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
		Boolean bl = cwlService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
}
