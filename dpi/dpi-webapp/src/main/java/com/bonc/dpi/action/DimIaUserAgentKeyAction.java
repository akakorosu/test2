package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import com.bonc.dpi.dao.entity.DimIaUserAgentKey;
import com.bonc.dpi.dao.entity.GroupClass;
import com.bonc.dpi.service.DimIaUserAgentKeyService;
import com.bonc.dpi.service.GroupClassService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value = "/uakey")
public class DimIaUserAgentKeyAction {
	
	@Resource
	private DimIaUserAgentKeyService dimIaUserAgentKeyService;
	@Resource
	private GroupClassService gcService;
	private final SimpleDateFormat optTime = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 分页查询
	 * @param sysUser
	 * @param page
	 * @param rows
	 * @return
	*/
	@RequestMapping(value = "/keyselectpagelist")
	@ResponseBody
	public PageJqGrid<DimIaUserAgentKey> selectPageList(DimIaUserAgentKey dimIaUserAgentKey, Integer page, Integer rows) throws Exception{
		
		// 查询条件处理prod_name
		String prodName = dimIaUserAgentKey.getProdName();
		if (prodName != null && !"".equals(prodName)) {
			String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
			dimIaUserAgentKey.setProdName(prodName_res);
		}
		Page<DimIaUserAgentKey> pageList = this.dimIaUserAgentKeyService.selectPageList(dimIaUserAgentKey, page, rows);
		PageJqGrid<DimIaUserAgentKey> pageJqGrid = new PageJqGrid<DimIaUserAgentKey>(pageList);
		
		
		
		
		return pageJqGrid;
	}
	
	/**
	 * 删除
	 * @param example
	 * @return
	 */
	@RequestMapping(value = "/deleteUaKey")
	@ResponseBody
	public DimIaUserAgentKey deleteUaKeyById(String id) throws Exception{
		DimIaUserAgentKey bl = new DimIaUserAgentKey();
		bl.setId(id);
		dimIaUserAgentKeyService.deleteUaKeyById(bl);
		return bl;
	}
	
	/**
	 * 表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/showKeyForm")
	public String showKeyForm(HttpServletRequest request, String id,String uaKey) throws Exception{
		if(!StringUtils.isBlank(id)) {
			DimIaUserAgentKey dimIaUserAgentKey = this.dimIaUserAgentKeyService.selectKeyById(id);
			dimIaUserAgentKey.setProdName(DpiUtils.strDecrypt(dimIaUserAgentKey.getProdName()));			
			request.setAttribute("vo", dimIaUserAgentKey);
		}
		if(!StringUtils.isBlank(uaKey)) {
			DimIaUserAgentKey dimIaUserAgentKey =new DimIaUserAgentKey();
			dimIaUserAgentKey.setUaKey(uaKey);
			request.setAttribute("vo", dimIaUserAgentKey);
		}
		return "pages/jsp/dpi/dimIaUserAgentKey/dimIaUserAgentKeyForm";
	}
	@RequestMapping(value = "/showKeyForm1")
	public String showKeyForm1(HttpServletRequest request, String id,String uaKey) throws Exception{
		if(!StringUtils.isBlank(id)) {
			DimIaUserAgentKey dimIaUserAgentKey = this.dimIaUserAgentKeyService.selectKeyById(id);
			dimIaUserAgentKey.setProdName(DpiUtils.strDecrypt(dimIaUserAgentKey.getProdName()));			
			request.setAttribute("vo", dimIaUserAgentKey);
		}
		if(!StringUtils.isBlank(uaKey)) {
			DimIaUserAgentKey dimIaUserAgentKey =new DimIaUserAgentKey();
			dimIaUserAgentKey.setUaKey(uaKey);
			request.setAttribute("vo", dimIaUserAgentKey);
		}
		return "pages/jsp/dpi/dimIaUserAgentKey/dimIaUserAgentKeyFormInsert";
	}
	
	/**
	 * 新增修改
	 * @param dimIaUserAgentKey
	 * @return
	 */
	
	@RequestMapping(value = "/insertorupdatekey")
	@ResponseBody
	public DimIaUserAgentKey insertOrUpdateKey(HttpSession session,DimIaUserAgentKey dimIaUserAgentKey) throws NoSuchAlgorithmException {
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = dimIaUserAgentKey.getId();
		if(!StringUtils.isBlank(id)) {
			dimIaUserAgentKey.setAuthor(user.getLoginId());
			dimIaUserAgentKey.setOpTime(optTime.format(new Date()));
			/*
			String name = dimIaUserAgentKey.getProdName();
			dimIaUserAgentKey.setProdName(DpiUtils.strEncrypt(name));
			*/
			dimIaUserAgentKey = this.dimIaUserAgentKeyService.updateKey(dimIaUserAgentKey);
		} else {
			dimIaUserAgentKey.setAuthor(user.getLoginId());
			dimIaUserAgentKey.setOpTime(optTime.format(new Date()));
			dimIaUserAgentKey.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			/*
			String name = dimIaUserAgentKey.getProdName();
			dimIaUserAgentKey.setProdName(DpiUtils.strEncrypt(name));
			*/
			dimIaUserAgentKey = this.dimIaUserAgentKeyService.insertKey(dimIaUserAgentKey);
		}
		return dimIaUserAgentKey;
	}
	@RequestMapping(value = "/insertKeyAndUpdateUa")
	@ResponseBody
	public DimIaUserAgentKey insertKeyAndUpdateUa(HttpSession session,DimIaUserAgentKey dimIaUserAgentKey) throws NoSuchAlgorithmException {
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = dimIaUserAgentKey.getId();
		if(!StringUtils.isBlank(id)) {
			dimIaUserAgentKey.setAuthor(user.getLoginId());
			dimIaUserAgentKey.setOpTime(optTime.format(new Date()));
			/*
			String name = dimIaUserAgentKey.getProdName();
			dimIaUserAgentKey.setProdName(DpiUtils.strEncrypt(name));
			*/
			dimIaUserAgentKey = this.dimIaUserAgentKeyService.updateKey(dimIaUserAgentKey);
		} else {
			dimIaUserAgentKey.setAuthor(user.getLoginId());
			dimIaUserAgentKey.setOpTime(optTime.format(new Date()));
			dimIaUserAgentKey.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			/*
			String name = dimIaUserAgentKey.getProdName();
			dimIaUserAgentKey.setProdName(DpiUtils.strEncrypt(name));
			*/
			dimIaUserAgentKey = this.dimIaUserAgentKeyService.insertKey(dimIaUserAgentKey);
			this.dimIaUserAgentKeyService.updateUa(dimIaUserAgentKey);
		}
		return dimIaUserAgentKey;
	}
	/**
	 * 校验重复
	 * @param dimIaUserAgentKey
	 * @return
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean check(DimIaUserAgentKey dimIaUserAgentKey) throws Exception{
		Boolean bl = this.dimIaUserAgentKeyService.check(dimIaUserAgentKey);
		return bl;
	}
	/**
	 * 校验id
	 * @param dimIaUserAgentKey
	 * @return
	 */
	@RequestMapping(value = "/checkId")
	@ResponseBody
	public Boolean checkId(DimIaUserAgentKey dimIaUserAgentKey) throws Exception{
		Boolean bl = this.dimIaUserAgentKeyService.checkId(dimIaUserAgentKey);
		return bl;
	}
	/**
	 * 增加修改表单弹出（url规则管理）
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/urlFuzzyRecognition")
	public String urlFuzzyRecognition(HttpServletRequest request, String prodId, String prodName) throws Exception {

		if (prodId != null && !"".equals(prodId)) {
			// 1根据prodId查询
			List<GroupClass> list = gcService.getProdIdListByProdId(prodId);
			for (GroupClass productInfo : list) {
				String prodName_decrypt = productInfo.getProdName();
				prodName_decrypt = DpiUtils.strDecrypt(prodName_decrypt);// 解密
				productInfo.setProdName(prodName_decrypt);
			}
			request.setAttribute("list", list);
		} else {
			// 2根据prodName查询
			String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
			if (!StringUtils.isBlank(prodName)) {
				List<GroupClass> list = gcService.getProdIdListByProdName(prodName_res);
				for (GroupClass productInfo : list) {
					String prodName_decrypt = productInfo.getProdName();
					prodName_decrypt = DpiUtils.strDecrypt(prodName_decrypt);// 解密
					productInfo.setProdName(prodName_decrypt);
				}
				request.setAttribute("list", list);
			}
		}

		return "pages/jsp/dpi/dimIaUserAgentKey/getProdIdListKey";
	}
	/**
	 * 增加修改表单弹出(添加ua规则)
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getIdNameList")
	public String getIdNameList(HttpServletRequest request, String prodId, String prodName) throws Exception {

		if (prodId != null && !"".equals(prodId)) {
			// 1根据prodId查询
			List<GroupClass> list = gcService.getProdIdListByProdId(prodId);
			for (GroupClass productInfo : list) {
				String prodName_decrypt = productInfo.getProdName();
				prodName_decrypt = DpiUtils.strDecrypt(prodName_decrypt);// 解密
				productInfo.setProdName(prodName_decrypt);
			}
			request.setAttribute("list", list);
		} else {
			// 2根据prodName查询
			String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
			if (!StringUtils.isBlank(prodName)) {
				List<GroupClass> list = gcService.getProdIdListByProdName(prodName_res);
				for (GroupClass productInfo : list) {
					String prodName_decrypt = productInfo.getProdName();
					prodName_decrypt = DpiUtils.strDecrypt(prodName_decrypt);// 解密
					productInfo.setProdName(prodName_decrypt);
				}
				request.setAttribute("list", list);
			}
		}

		return "pages/jsp/dpi/dimIaUserAgentKey/getProdIdListKey";
	}
	@RequestMapping(value = "/getIdNameListInsert")
	public String getIdNameListInsert(HttpServletRequest request, String prodId, String prodName) throws Exception {

		if (prodId != null && !"".equals(prodId)) {
			// 1根据prodId查询
			List<GroupClass> list = gcService.getProdIdListByProdId(prodId);
			for (GroupClass productInfo : list) {
				String prodName_decrypt = productInfo.getProdName();
				prodName_decrypt = DpiUtils.strDecrypt(prodName_decrypt);// 解密
				productInfo.setProdName(prodName_decrypt);
			}
			request.setAttribute("list", list);
		} else {
			// 2根据prodName查询
			String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
			if (!StringUtils.isBlank(prodName)) {
				List<GroupClass> list = gcService.getProdIdListByProdName(prodName_res);
				for (GroupClass productInfo : list) {
					String prodName_decrypt = productInfo.getProdName();
					prodName_decrypt = DpiUtils.strDecrypt(prodName_decrypt);// 解密
					productInfo.setProdName(prodName_decrypt);
				}
				request.setAttribute("list", list);
			}
		}

		return "pages/jsp/dpi/dimIaUserAgentKey/getProdIdListKeyInsert";
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", dimIaUserAgentKeyService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", dimIaUserAgentKeyService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", dimIaUserAgentKeyService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", dimIaUserAgentKeyService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", dimIaUserAgentKeyService.list_false_cache);//excle中与库中主键过长
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/dimIaUserAgentKey/falseDataViewFromExcle";
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
		Boolean bl = dimIaUserAgentKeyService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
}