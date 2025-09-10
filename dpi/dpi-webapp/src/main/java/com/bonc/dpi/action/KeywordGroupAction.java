package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

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
import com.bonc.dpi.dao.entity.KeywordGroup;
import com.bonc.dpi.service.KeywordGroupService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 关键词规则分组
 * dim_ia_keyword_group
 * @author BONC-XUXL
 *
 */
@Controller
@RequestMapping(value = "/keywordGroup")
public class KeywordGroupAction {


	@Resource
	private KeywordGroupService kgService;

	/**
	 * 分页查询
	 * @param ksr
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<KeywordGroup> selectPageList(KeywordGroup vo, Integer page, Integer rows) throws Exception{
		Page<KeywordGroup> pageList = this.kgService.selectList(vo, page, rows);
		PageJqGrid<KeywordGroup> pageJqGrid = new PageJqGrid<KeywordGroup>(pageList);
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
			KeywordGroup vo = this.kgService.selectVoById(id);
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/keywordGroup/keywordGroupForm";
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
			KeywordGroup vo = this.kgService.selectVoById(id);
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/keywordGroup/keywordGroupView";
	}
	
	/**
	 * 根据分组名称模糊查询
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getGroupTypeList")
	public String getGroupTypeList(HttpServletRequest request,String groupType, String groupName) throws Exception{
		
		if(!StringUtils.isBlank(groupType)) {
			List<KeywordGroup> list = kgService.getGroupTypeListByGroupType(groupType);
			request.setAttribute("list", list);
		}
		if(!StringUtils.isBlank(groupName)) {
			List<KeywordGroup> list = kgService.getGroupTypeListByGroupName(groupName);
			request.setAttribute("list", list);
		}
		return "pages/jsp/dpi/keywordGroup/getGroupTypeList";
	}
	
	/**
	 * 添加或者修改
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateVo")
	@ResponseBody
	public KeywordGroup insertVo(HttpSession session,KeywordGroup vo) throws NoSuchAlgorithmException {
		
		String UUID = UUIDUtil.createUUID();//
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = vo.getId();
		if(!StringUtils.isBlank(id)) {
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo.setAuthor(user.getLoginId());
			vo = this.kgService.updateVo(vo);
		} else {
			vo.setId(UUID);
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			this.kgService.insertVo(vo);
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
		Boolean bl = this.kgService.deleteVoById(id);
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
	public Boolean check(KeywordGroup vo) throws Exception{
		Boolean bl = this.kgService.selectCheck(vo);
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", kgService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", kgService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", kgService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("list_false_cache", kgService.list_false_cache);//excle中与库中主键重复
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/keywordGroup/falseDataViewFromExcle";
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
		Boolean bl = kgService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
}
