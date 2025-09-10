package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jdo.annotations.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.OperationsCenter;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.entity.TopLevelDomain;
import com.bonc.dpi.dao.entity.UnidentifiedUrl;
import com.bonc.dpi.service.UnidentifiedUrlService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 未识别域名
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/unidentifiedUrl")
public class UnidentifiedUrlAction {
	private static final Logger logger = LoggerFactory.getLogger(OperationsCenterAction.class);
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	@Autowired
	private UnidentifiedUrlService unidentifiedUrlService;
	

	@RequestMapping(value = "/selectpagelist",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<UnidentifiedUrl> selectPageList(UnidentifiedUrl vo, Integer page, Integer rows) throws Exception{
		Page<UnidentifiedUrl> pageList = unidentifiedUrlService.selectList(vo, page, rows);
		PageJqGrid<UnidentifiedUrl> pageJqGrid = new PageJqGrid<UnidentifiedUrl>(pageList);
		return pageJqGrid;
	}
	/**
	 * 增加修改表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showForm")
	public String showForm(HttpServletRequest request, String host1,String time) throws Exception{
		UnidentifiedUrl vo=null;
		if(!StringUtils.isBlank(host1)) {
			vo = unidentifiedUrlService.selectVoById(host1,time);
			request.setAttribute("vo", vo);
		}
		
		return "pages/jsp/dpi/unidentifiedUrl/unidentifiedUrlForm2";
		
		
	}
	/**
	 * 添加或者修改
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateVo")
	@ResponseBody
	public UnidentifiedUrl insertVo(HttpSession session,UnidentifiedUrl vo) throws NoSuchAlgorithmException {
		vo = unidentifiedUrlService.updateVo(vo);
		return vo;
	}
	/**
	 * 插入域名表
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/insertDomainRule")
	@ResponseBody
	public UnidentifiedUrl insertDomainRule(HttpSession session,UnidentifiedUrl vo) throws NoSuchAlgorithmException {		
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		vo.setAuthor(user.getLoginId());
		vo.setOpTime(sdf.format(new Date()));
		vo.setIsValid("1");
		vo.setTotalCount("0");
		vo = unidentifiedUrlService.insertDomainRule(vo);
		
		return vo;
	}
	@RequestMapping(value = "/getProId",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getProId(String prodName) {
		try {
			String prodName1=DpiUtils.strEncrypt(prodName);
			List<Map<String, Object>> result=unidentifiedUrlService.getprodId(prodName1);
			for(int i=0;i<result.size();i++) {
				Map<String, Object> map=result.get(i);
				String name=DpiUtils.strDecrypt(map.get("PROD_NAME").toString());
				map.put("PROD_NAME", name);
			}
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/getInfoByProdId",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getInfoByProdId(String prodId) {
		try {
			UnidentifiedUrl result=unidentifiedUrlService.getInfoByProdId(prodId);
			if(result!=null&&result.getProdName()!=null) {
				String name=DpiUtils.strDecrypt(result.getProdName());
				result.setProdName(name);
				if(result.getProdCatagory()!=null){
					String prodCatagory=result.getProdCatagory();
					UnidentifiedUrl result1=unidentifiedUrlService.getprodCatagoryName(prodCatagory);
					if(result1!=null&&result1.getLabelCode2()!=null){
						result.setProdCatagoryName(result1.getLabelName2());
					}else{
						result.setProdCatagoryName(result1.getLabelName1());
					}
				}
				return Ajax.responseString(CST.RES_SECCESS, result);
			}else {
				UnidentifiedUrl result1=new UnidentifiedUrl();
				return Ajax.responseString(CST.RES_SECCESS, result1);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/getInfoByProdName",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getInfoByProdName(String prodName) {
		try {
			ProductInfo result=unidentifiedUrlService.getInfoByProdName(prodName);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/onlyUpdateCheck",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String onlyUpdateCheck(String host) {
		try {
			int result=unidentifiedUrlService.onlyUpdateCheck(host);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/updateCheckAndInsert",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String updateCheckAndInsert( HttpSession session,UnidentifiedUrl vo) {
		try {
			SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO); 
			int result=unidentifiedUrlService.onlyUpdateCheck(vo.getHost());
			String UUID = UUIDUtil.createUUID();
			vo.setUuid(UUID);
			vo.setOpTime(sdf.format(new Date()));
			vo.setAuthor(user.getLoginId());
		    int check=unidentifiedUrlService.checkData(vo);
		    if(check==0) {
		    	unidentifiedUrlService.insertDomainRuleNew(vo);
		    }else {
		    	if(vo.getHost()!=null) {
		    		unidentifiedUrlService.updateDomainRuleNew(vo);
		    	}
		    	
		    }
			
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/batchCheckOrInsert",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String batchCheckOrInsert(HttpSession session ,String emptyProdId,String prodId,String host) {
		try {
			SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
			Map<String, Object> params = new HashMap<>();
			if(!StringUtils.isEmpty(emptyProdId)) {
				List<String> a = Arrays.asList(emptyProdId.split(",,"));
				params.put("emptyIds", a);
				//id为空
				int result=unidentifiedUrlService.batchUpdateEmptyCheck(params);
			}
			if(!StringUtils.isEmpty(prodId)) {
				List<String> b = Arrays.asList(prodId.split(",,"));
				List<String> c = Arrays.asList(host.split(",,"));
				params.put("prodIds", c);
				List<UnidentifiedUrl> list=new ArrayList<>();
				for(int i=0;i<c.size();i++) {
					UnidentifiedUrl vo=new UnidentifiedUrl();
					vo.setHost(c.get(i));
					vo.setProdId(b.get(i));
					vo.setOpTime(sdf.format(new Date())); 
					vo.setAuthor(user.getLoginId());
					String UUID = UUIDUtil.createUUID();
					vo.setUuid(UUID);
					list.add(vo);
					int check=unidentifiedUrlService.checkData(vo);
				    if(check==0) {
				    	unidentifiedUrlService.insertDomainRuleNew(vo);
				    }else {
				    	unidentifiedUrlService.updateDomainRuleNew(vo);
				    }
					
				}
				params.put("objs", list);
				//id不为空
				int result1=unidentifiedUrlService.batchUpdateCheck(params);
				//id 不为空时插入数据
				
				//unidentifiedUrlService.batchInsertDomainRule(params);
			}
			
			return Ajax.responseString(CST.RES_SECCESS);
		} catch (Exception e) {
			//logger.error(e.getMessage());
			e.getMessage();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/getAllProdName",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getAllProdName() {
		try {
			List<Map<String, Object>> result=unidentifiedUrlService.getAllProdName();
			for(int i=0;i<result.size();i++) {
				Map<String, Object> map=result.get(i);
				String name=DpiUtils.strDecrypt(map.get("PROD_NAME").toString());
				map.put("PROD_NAME", name);
			}
			
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/getAllProdId",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getAllProdId() {
		try {
			List<Map<String, Object>> result=unidentifiedUrlService.getAllProdId();
			
			for(int i=0;i<result.size();i++) {
				Map<String, Object> map=result.get(i);
				String name=DpiUtils.strDecrypt(map.get("PROD_NAME").toString());
				map.put("PROD_NAME", name);
			}
			
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/alterAndInsert",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String alterAndInsert(HttpSession session,UnidentifiedUrl vo) {
		try {
			SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
			String UUID = UUIDUtil.createUUID();
			vo.setUuid(UUID);
			//修改状态url表
			int result=unidentifiedUrlService.alterAndInsert(vo);
			
			int result2=unidentifiedUrlService.alterProdInfo(vo);
			
			
			//插入数据domain
			vo.setOpTime(sdf.format(new Date())); 
			vo.setAuthor(user.getLoginId());
			int check=unidentifiedUrlService.checkData(vo);
			if(check==0) {
				int result1=unidentifiedUrlService.insertDomainRuleNew(vo);
			}else {
				unidentifiedUrlService.updateDomainRuleNew(vo);
			}
			
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@Transactional
	@RequestMapping(value = "/alterOnlyUpdate",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String alterOnlyUpdate(HttpSession session,UnidentifiedUrl vo) {
		try {
			SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
			vo.setOpTime(sdf.format(new Date()));
			vo.setAuthor(user.getLoginId());
			//修改状态
			int result=unidentifiedUrlService.alterOnlyUpdate(vo);
			//插入导出表
			unidentifiedUrlService.insertExport(vo);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	@RequestMapping(value = "/comLikeSearch",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String comLikeSearch(String prodName) {
		try {
			//修改状态			
			prodName= DpiUtils.strEncrypt(prodName);
			List<UnidentifiedUrl> result=unidentifiedUrlService.comLikeSearch(prodName);
			for(int i=0;i<result.size();i++) {
				UnidentifiedUrl p=result.get(i);
				p.setProdName(DpiUtils.strDecrypt(p.getProdName()));
			}
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
}
