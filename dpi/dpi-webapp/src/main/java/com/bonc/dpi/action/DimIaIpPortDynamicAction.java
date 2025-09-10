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
import com.bonc.dpi.dao.entity.DimIaIpPortDynamic;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.service.DimIaIpPortDynamicService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value = "/ipport")
public class DimIaIpPortDynamicAction {
	
	@Resource
	private DimIaIpPortDynamicService dimIaIpPortDynamicService;
	private final SimpleDateFormat optTime = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * 分页查询
	 * @param sysUser
	 * @param page
	 * @param rows
	 * @return
	*/
	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<DimIaIpPortDynamic> selectPageList(DimIaIpPortDynamic dimIaIpPortDynamic, Integer page, Integer rows) throws Exception{
		//查询条件处理prod_name
		String prodName = dimIaIpPortDynamic.getProdName();
		if(prodName!=null && !"".equals(prodName)){
			String prodName_res = DpiUtils.strEncrypt(prodName);//字符串分词加密
			dimIaIpPortDynamic.setProdName(prodName_res);
		}
		Page<DimIaIpPortDynamic> pageList = this.dimIaIpPortDynamicService.selectPageList(dimIaIpPortDynamic, page, rows);
		PageJqGrid<DimIaIpPortDynamic> pageJqGrid = new PageJqGrid<DimIaIpPortDynamic>(pageList);
		return pageJqGrid;
	}
	
	/**
	 * 删除
	 * @param example
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public DimIaIpPortDynamic deleteById(String id) throws Exception{
		DimIaIpPortDynamic bl = new DimIaIpPortDynamic();
		bl.setId(id);
		dimIaIpPortDynamicService.delete(bl);
		return bl;
	}
	
	/**
	 * 表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	
	@RequestMapping(value = "/showform")
	public String showForm(HttpServletRequest request, String id) throws Exception{
		if(!StringUtils.isBlank(id)) {
			DimIaIpPortDynamic dimIaIpPortDynamic = this.dimIaIpPortDynamicService.selectById(id);
			request.setAttribute("vo", dimIaIpPortDynamic);
		}
		return "pages/jsp/dpi/dimIaIpPortDynamic/dimIaIpPortDynamicForm";
	}
	
	/**
	 * 新增修改
	 * @param dimIaUserAgentKey
	 * @return
	 */
	
	@RequestMapping(value = "/insertorupdate")
	@ResponseBody
	public DimIaIpPortDynamic insertOrUpdate(HttpSession session,DimIaIpPortDynamic dimIaIpPortDynamic) throws NoSuchAlgorithmException {
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = dimIaIpPortDynamic.getId();
		if(!StringUtils.isBlank(id)) {
			String ip = dimIaIpPortDynamic.getIpPortParam().split(":")[0];
			String port = dimIaIpPortDynamic.getIpPortParam().split(":")[1];
			dimIaIpPortDynamic.setIp(ip);
			dimIaIpPortDynamic.setPort(port); 
			dimIaIpPortDynamic.setAuthor(user.getLoginId());
			dimIaIpPortDynamic.setOpTime(optTime.format(new Date()));
			dimIaIpPortDynamic = this.dimIaIpPortDynamicService.update(dimIaIpPortDynamic);
		} else {
			String ip = dimIaIpPortDynamic.getIpPortParam().split(":")[0];
			String port = dimIaIpPortDynamic.getIpPortParam().split(":")[1];
			dimIaIpPortDynamic.setIp(ip);
			dimIaIpPortDynamic.setPort(port);
			dimIaIpPortDynamic.setAuthor(user.getLoginId());
			dimIaIpPortDynamic.setOpTime(optTime.format(new Date()));
			dimIaIpPortDynamic.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			dimIaIpPortDynamic = this.dimIaIpPortDynamicService.insert(dimIaIpPortDynamic);
		}
		return dimIaIpPortDynamic;
	}
	
	/**
	 * 获取产品id列表
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getProdIdList")
	public String getProdIdList(HttpServletRequest request,String prodId,String prodName) throws Exception{
		
		if(prodId!=null && !"".equals(prodId)){
			//1根据prodId查询
			List<ProductInfo> list = dimIaIpPortDynamicService.getProdIdListByProdId(prodId);
			for (ProductInfo productInfo : list) {
				String prodName_decrypt = productInfo.getProdName();
				prodName_decrypt = DpiUtils.strDecrypt(prodName_decrypt);//解密
				productInfo.setProdName(prodName_decrypt);
			}
			request.setAttribute("list", list);
		}else{
			//2根据prodName查询
			String prodName_res = DpiUtils.strEncrypt(prodName);//字符串分词加密
			if(!StringUtils.isBlank(prodName)) {
				List<ProductInfo> list = dimIaIpPortDynamicService.getProdIdListByProdName(prodName_res);
				for (ProductInfo productInfo : list) {
					String prodName_decrypt = productInfo.getProdName();
					prodName_decrypt = DpiUtils.strDecrypt(prodName_decrypt);//解密
					productInfo.setProdName(prodName_decrypt);
				}
				request.setAttribute("list", list);
			}
		}

		return "pages/jsp/dpi/dimIaIpPortDynamic/getProdIdList";
	}
	/**
	 * 校验重复
	 * @param DimIaIpPortDynamic
	 * @return
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean check(DimIaIpPortDynamic dimIaIpPortDynamic) throws Exception{
		String ipPortParam = dimIaIpPortDynamic.getIpPortParam();
		if(!ipPortParam.contains(":")) {
			return false; 
		}
		Boolean bl = this.dimIaIpPortDynamicService.check(dimIaIpPortDynamic);
		return bl;
	}
	/**
	 * 校验重复
	 * @param DimIaIpPortDynamic
	 * @return
	 */
	@RequestMapping(value = "/checkId")
	@ResponseBody
	public Boolean checkId(DimIaIpPortDynamic dimIaIpPortDynamic) throws Exception{		
		Boolean bl = this.dimIaIpPortDynamicService.checkId(dimIaIpPortDynamic);
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", dimIaIpPortDynamicService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", dimIaIpPortDynamicService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", dimIaIpPortDynamicService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", dimIaIpPortDynamicService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", dimIaIpPortDynamicService.list_false_cache);//excle中与库中主键过长
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/dimIaIpPortDynamic/falseDataViewFromExcle";
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
		Boolean bl = dimIaIpPortDynamicService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
}