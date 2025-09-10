package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
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
import com.bonc.dpi.dao.entity.SegWordLtb;
import com.bonc.dpi.service.SegWordLtbService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 分词词库管理
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/segWordLtb")
public class SegWordLtbAction {
	
	@Resource
	private SegWordLtbService segWordLtbService;
	private final SimpleDateFormat sfd = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 分页查询
	 * @param ksr
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<SegWordLtb> selectPageList(SegWordLtb vo, Integer page, Integer rows) throws Exception{
		Page<SegWordLtb> pageList = this.segWordLtbService.selectList(vo, page, rows);
		PageJqGrid<SegWordLtb> pageJqGrid = new PageJqGrid<SegWordLtb>(pageList);
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
			SegWordLtb vo = this.segWordLtbService.selectVoById(id);
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/segWordLtb/segWordLtbForm";
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
			SegWordLtb vo = this.segWordLtbService.selectVoById(id);
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/segWordLtb/segWordLtbView";
	}
	
	/**
	 * 添加或者修改
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateVo")
	@ResponseBody
	public SegWordLtb insertVo(HttpSession session,SegWordLtb vo) throws NoSuchAlgorithmException {
		
		String UUID = UUIDUtil.createUUID();//
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = vo.getId();
		if(!StringUtils.isBlank(id)) {
			vo = this.segWordLtbService.updateVo(vo);
		} else {
			vo.setId(UUID);
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(sfd.format(new Date()));
			vo = this.segWordLtbService.insertVo(vo);
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
		Boolean bl = this.segWordLtbService.deleteVoById(id);
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
	public Boolean check(SegWordLtb vo) throws Exception{
		Boolean bl = this.segWordLtbService.selectCheck(vo);
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", segWordLtbService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", segWordLtbService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", segWordLtbService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", segWordLtbService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", segWordLtbService.list_false_cache);//excle中与库中主键过长
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/segWordLtb/falseDataViewFromExcle";
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
		Boolean bl = segWordLtbService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
}
