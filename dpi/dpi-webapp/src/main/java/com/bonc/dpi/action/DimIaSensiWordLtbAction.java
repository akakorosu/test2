package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.dpi.dao.entity.DimIaSensiWordLtb;

import com.bonc.dpi.service.DimIaSensiWordLtbService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value = "/sensi")
public class DimIaSensiWordLtbAction {
	
	@Resource
	private DimIaSensiWordLtbService dimIaSensiWordLtbService;
	private final SimpleDateFormat optTime = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 分页查询
	 * @param dimIaSensiWordLtb
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/sensiselectpagelist")
	@ResponseBody
	public PageJqGrid<DimIaSensiWordLtb> selectPageList(DimIaSensiWordLtb simIaSensiWordLtb, Integer page, Integer rows) throws Exception {
		Page<DimIaSensiWordLtb> pageList = this.dimIaSensiWordLtbService.selectPageList(simIaSensiWordLtb, page, rows);
		PageJqGrid<DimIaSensiWordLtb> pageJqGrid = new PageJqGrid<DimIaSensiWordLtb>(pageList);
		return pageJqGrid;
	}
	
	/**
	 * 表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showsensiform")
	public String shownoiseform(HttpServletRequest request, String id) throws Exception {
		if(!StringUtils.isBlank(id)) {
			DimIaSensiWordLtb dimIaSensiWordLtb = this.dimIaSensiWordLtbService.selectSensiByKey(id);
			request.setAttribute("vo", dimIaSensiWordLtb);
		}
		return "pages/jsp/dpi/dimIaSensiWordLtb/dimIaSensiWordLtbForm";
	}

	/**
	 * 新增修改
	 * @param dimIaSensiWordLtb
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	
	@RequestMapping(value = "/insertorupdatesensi")
	@ResponseBody
	public DimIaSensiWordLtb insertorupdateSensi(HttpServletRequest session, DimIaSensiWordLtb dimIaSensiWordLtb)  throws NoSuchAlgorithmException{
		String id = dimIaSensiWordLtb.getId();
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		if(!StringUtils.isBlank(id)) {
			dimIaSensiWordLtb.setAuthor("admin");
			dimIaSensiWordLtb.setId(id);
			dimIaSensiWordLtb.setOpTime(optTime.format(new Date()));
			dimIaSensiWordLtb = this.dimIaSensiWordLtbService.updateSensi(dimIaSensiWordLtb);
		} else {
			dimIaSensiWordLtb.setAuthor("admin");
			dimIaSensiWordLtb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			dimIaSensiWordLtb.setOpTime(optTime.format(new Date()));
			dimIaSensiWordLtb = this.dimIaSensiWordLtbService.insertSensi(dimIaSensiWordLtb);
			
		}
		return dimIaSensiWordLtb;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/deletesensibyid")
	@ResponseBody
	public DimIaSensiWordLtb deleteSensiById(String id) throws Exception {
		DimIaSensiWordLtb dimIaSensiWordLtb = new DimIaSensiWordLtb();
		dimIaSensiWordLtb.setId(id);
		dimIaSensiWordLtbService.deleteSensiById(dimIaSensiWordLtb);
		return dimIaSensiWordLtb;
	}
	/**
	 * 校验重复
	 * @param dimIaUserAgentNoise
	 * @return
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean check(DimIaSensiWordLtb dimIaSensiWordLtb) throws Exception {
		Boolean bl = this.dimIaSensiWordLtbService.selectCheck(dimIaSensiWordLtb);
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", dimIaSensiWordLtbService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", dimIaSensiWordLtbService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", dimIaSensiWordLtbService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", dimIaSensiWordLtbService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", dimIaSensiWordLtbService.list_false_cache);//excle中与库中主键过长
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/dimIaSensiWordLtb/falseDataViewFromExcle";
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
		Boolean bl = dimIaSensiWordLtbService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
	

}