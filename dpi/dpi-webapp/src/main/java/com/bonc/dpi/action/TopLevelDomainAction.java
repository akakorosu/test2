package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.SegWordLtb;
import com.bonc.dpi.dao.entity.TopLevelDomain;
import com.bonc.dpi.service.TopLevelDomainService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 一级域名识别管理
 * 
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/topLevelDomain")
public class TopLevelDomainAction {
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	@Autowired
	private TopLevelDomainService topLevelDomainService;

	/**
	 * 获取一级域名识别信息
	 * 
	 * @param time
	 * @param area
	 * @param gender
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<TopLevelDomain> selectPageList(TopLevelDomain vo, Integer page, Integer rows) throws Exception {
		Page<TopLevelDomain> pageList = topLevelDomainService.selectList(vo, page, rows);
		PageJqGrid<TopLevelDomain> pageJqGrid = new PageJqGrid<TopLevelDomain>(pageList);
		return pageJqGrid;
	}

	/**
	 * 增加修改表单弹出
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showForm")
	public String showForm(HttpServletRequest request, String host, String monthId) throws Exception {
		if (!StringUtils.isBlank(host)) {
			TopLevelDomain vo = this.topLevelDomainService.selectVoById(host, monthId);
			if(vo!=null&&vo.getProdName()!=null) {
				vo.setProdName(DpiUtils.strDecrypt(vo.getProdName()));
			}
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/topLevelDomain/topLevelDomainForm";
	}

	/**
	 * 添加或者修改
	 * 
	 * @param session
	 * @param vo
	 * @return
	 */
	/*@RequestMapping(value = "/insertOrUpdateVo")
	@ResponseBody
	public TopLevelDomain insertVo(HttpSession session, TopLevelDomain vo) throws NoSuchAlgorithmException {
		String UUID = UUIDUtil.createUUID();
		//String id = vo.getId();
		if (!StringUtils.isBlank(id)) {
			vo = this.topLevelDomainService.updateVo(vo);
		} else {
			//vo.setId(UUID);
			vo = this.topLevelDomainService.insertVo(vo);
		}

		return vo;
	}*/

	/**
	 * 添加或者修改
	 * 
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/updateTopDomain")
	@ResponseBody
	public String updateTopDomain(HttpSession session,TopLevelDomain vo) throws NoSuchAlgorithmException {
		
		try {
			String UUID = UUIDUtil.createUUID();
			vo.setUuid(UUID);
			SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
			vo.setProdName(DpiUtils.strEncrypt(vo.getProdName()));
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(sdf.format(new Date()));
			int result = this.topLevelDomainService.updateTopDomain(vo);
			int check = this.topLevelDomainService.checkDomainCode(vo);
			if(vo.getProdId()!=null) {
			if(check>0) {
				topLevelDomainService.updateDomainCode(vo);
			}else {
				this.topLevelDomainService.updateOrInsertDomain(vo); //只插入数据	
			}
			}
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}

	}
	/**
	 * 更改无需处理状态为2
	 * 
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/topLvlDomainCheck2")
	@ResponseBody
	public String topLvlDomainCheck2(TopLevelDomain vo) throws NoSuchAlgorithmException {
		
		try {
			
			int result = this.topLevelDomainService.topLvlDomainCheck2(vo);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}

	}

}
