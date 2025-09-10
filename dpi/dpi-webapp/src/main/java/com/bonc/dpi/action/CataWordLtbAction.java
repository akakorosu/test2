package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
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
import com.bonc.dpi.dao.entity.CataWordLtb;
import com.bonc.dpi.dao.entity.ProductLabel;
import com.bonc.dpi.service.CataWordLtbService;
import com.bonc.dpi.service.ProductLabelService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 分类词库
 * dim_ia_cata_word_ltb
 * @author BONC-XUXL
 *
 */
@Controller
@RequestMapping(value = "/cataWordLtb")
public class CataWordLtbAction {

	@Resource
	private CataWordLtbService cwlService;
	
	@Resource
	private ProductLabelService productLabelService;

	/**
	 * 分页查询
	 * @param ksr
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<CataWordLtb> selectPageList(CataWordLtb vo, Integer page, Integer rows) throws Exception{
		Page<CataWordLtb> pageList = this.cwlService.selectList(vo, page, rows);
		PageJqGrid<CataWordLtb> pageJqGrid = new PageJqGrid<CataWordLtb>(pageList);
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
			CataWordLtb vo = this.cwlService.selectVoById(id);
			request.setAttribute("vo", vo);
			
			//查询productLabel
			String labelCode = vo.getCataLabel();
			ProductLabel pl = productLabelService.selectVoByLabelCode(labelCode);
			if(pl!=null){
				String labelName1 = pl.getLabelName1();
				String labelName2 = pl.getLabelName2();
				if(labelName2!=null&& !"".equals(labelName2)){
					request.setAttribute("labelName", labelName2);//有二级标签用二级
				}else{
					request.setAttribute("labelName", labelName1);//没有有二级标签用一级
				}
			}
		}
		return "pages/jsp/dpi/cataWordLtb/cataWordLtbForm";
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
			CataWordLtb vo = this.cwlService.selectVoById(id);
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/cataWordLtb/cataWordLtbView";
	}
	
	/**
	 * 添加或者修改
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateVo")
	@ResponseBody
	public CataWordLtb insertVo(HttpSession session,CataWordLtb vo) throws NoSuchAlgorithmException {
		
		
		String catalabel = vo.getCatalabelparam();
		vo.setCataLabel(catalabel);
		String cataWord = vo.getCatawordparam();
		vo.setCataWord(cataWord);
		
		String UUID = UUIDUtil.createUUID();//
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = vo.getId();
		if(!StringUtils.isBlank(id)) {
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo = this.cwlService.updateVo(vo);
		} else {
			vo.setId(UUID);
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo = this.cwlService.insertVo(vo);
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
		Boolean bl = this.cwlService.deleteVoById(id);
		return bl;
	}
	
	/**
	 * 校验主键重复
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean check(CataWordLtb vo) throws Exception{
		String catalabel = vo.getCatalabelparam();
		vo.setCataLabel(catalabel);
		String cataWord = vo.getCatawordparam();
		vo.setCataWord(cataWord);
		Boolean bl = this.cwlService.selectCheck(vo);
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", cwlService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", cwlService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", cwlService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("list_false_cache", cwlService.list_false_cache);//excle中与库中主键重复
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/cataWordLtb/falseDataViewFromExcle";
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
