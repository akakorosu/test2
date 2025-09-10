package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.dpi.dao.entity.DimIaUserAgentNoise;
import com.bonc.dpi.service.DimIaUserAgentNoiseService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value = "/uanoise")
public class DimIaUserAgentNoiseAction {
	
	@Resource
	private DimIaUserAgentNoiseService dimIaUserAgentNoiseService;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	/**
	 * 分页查询
	 * @param dimIaUserAgentNoise
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/noiseselectpagelist")
	@ResponseBody
	public PageJqGrid<DimIaUserAgentNoise> selectPageList(DimIaUserAgentNoise dimIaUserAgentNoise, Integer page, Integer rows) throws Exception {
		Page<DimIaUserAgentNoise> pageList = this.dimIaUserAgentNoiseService.selectPageList(dimIaUserAgentNoise, page, rows);
		PageJqGrid<DimIaUserAgentNoise> pageJqGrid = new PageJqGrid<DimIaUserAgentNoise>(pageList);
		return pageJqGrid;
	}
	
	/**
	 * 表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/shownoiseform")
	public String shownoiseform(HttpServletRequest request, String id,String uaKey) throws Exception {
		if(!StringUtils.isBlank(id)) {
			DimIaUserAgentNoise dimIaUserAgentNoise = this.dimIaUserAgentNoiseService.selectNoiseByKey(id);
			request.setAttribute("vo", dimIaUserAgentNoise);
		}
		if(!StringUtils.isBlank(uaKey)) {
			DimIaUserAgentNoise dimIaUserAgentNoise=new DimIaUserAgentNoise();
			dimIaUserAgentNoise.setNoiseKey(uaKey);
			request.setAttribute("vo", dimIaUserAgentNoise);
		}
		return "pages/jsp/dpi/dimIaUserAgentNoise/dimIaUserAgentNoiseForm";
	}

	/**
	 * 新增修改
	 * @param sysUser
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	
	@RequestMapping(value = "/insertorupdatenoise")
	@ResponseBody
	public DimIaUserAgentNoise insertorupdatenoise(HttpSession session,DimIaUserAgentNoise dimIaUserAgentNoise)  throws NoSuchAlgorithmException{
		String id = dimIaUserAgentNoise.getId();
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		if(!StringUtils.isBlank(id)) {
			dimIaUserAgentNoise.setAuthor(user.getLoginId());
			dimIaUserAgentNoise.setOpTime(sdf.format(new Date()));
			dimIaUserAgentNoise = this.dimIaUserAgentNoiseService.updateNoise(dimIaUserAgentNoise);
		} else {
			
			dimIaUserAgentNoise.setAuthor(user.getLoginId());
			dimIaUserAgentNoise.setOpTime(sdf.format(new Date()));
			dimIaUserAgentNoise.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			dimIaUserAgentNoise = this.dimIaUserAgentNoiseService.insertNoise(dimIaUserAgentNoise);
		}
		return dimIaUserAgentNoise;
	}
	/**
	 * 新增修改(ua)
	 * @param sysUser
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	@RequestMapping(value = "/insertUaNoiseAndupdateUa")
	@ResponseBody
	public DimIaUserAgentNoise insertUaNoiseAndupdateUa(HttpSession session,DimIaUserAgentNoise dimIaUserAgentNoise)  throws NoSuchAlgorithmException{
		String id = dimIaUserAgentNoise.getId();
		if(!StringUtils.isBlank(id)) {		
			dimIaUserAgentNoise = this.dimIaUserAgentNoiseService.updateNoise(dimIaUserAgentNoise);
		} else {
			SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
			dimIaUserAgentNoise.setAuthor(user.getLoginId());
			dimIaUserAgentNoise.setOpTime(sdf.format(new Date()));
			dimIaUserAgentNoise.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			this.dimIaUserAgentNoiseService.updateNoiseUa(dimIaUserAgentNoise);
			dimIaUserAgentNoise = this.dimIaUserAgentNoiseService.insertNoise(dimIaUserAgentNoise);
		}
		return dimIaUserAgentNoise;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/deletenoisebyid")
	@ResponseBody
	public DimIaUserAgentNoise deleteNoiseById(String id) throws Exception {
		DimIaUserAgentNoise dimIaUserAgentNoise = new DimIaUserAgentNoise();
		dimIaUserAgentNoise.setId(id);	
		dimIaUserAgentNoiseService.deleteNoiseById(dimIaUserAgentNoise);
		return dimIaUserAgentNoise;
	}
	/**
	 * 校验重复
	 * @param dimIaUserAgentNoise
	 * @return
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean check(DimIaUserAgentNoise dimIaUserAgentNoise) throws Exception {
		Boolean bl = this.dimIaUserAgentNoiseService.selectCheck(dimIaUserAgentNoise);
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", dimIaUserAgentNoiseService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", dimIaUserAgentNoiseService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", dimIaUserAgentNoiseService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", dimIaUserAgentNoiseService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", dimIaUserAgentNoiseService.list_false_cache);//excle中与库中主键过长
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/dimIaUserAgentNoise/falseDataViewFromExcle";
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
		Boolean bl = dimIaUserAgentNoiseService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
	

}