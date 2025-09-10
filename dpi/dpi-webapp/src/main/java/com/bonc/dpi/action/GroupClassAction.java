package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
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
import com.bonc.dpi.dao.entity.GroupClass;
import com.bonc.dpi.service.GroupClassService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 集团下发大小类规则管理
 * 
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/groupClass")
public class GroupClassAction {

	@Resource
	private GroupClassService gcService;
//	private SimpleDateFormat sfd = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 分页查询
	 * 
	 * @param ksr
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<GroupClass> selectPageList(GroupClass vo, Integer page, Integer rows) throws Exception {
		// 查询条件处理prod_name
		String prodName = vo.getProdName();
		if (prodName != null && !"".equals(prodName)) {
			String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
			vo.setProdName(prodName_res);
		}
		Page<GroupClass> pageList = this.gcService.selectList(vo, page, rows);
		PageJqGrid<GroupClass> pageJqGrid = new PageJqGrid<GroupClass>(pageList);
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
	public String showForm(HttpServletRequest request, String id) throws Exception {
		if (!StringUtils.isBlank(id)) {
			GroupClass vo = this.gcService.selectVoByIdWithProdName(id);
			vo.setProdName(DpiUtils.strDecrypt(vo.getProdName()));// 解密
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/groupClass/groupClassForm";
	}

	/**
	 * 查看页面表单弹出
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/viewVoById")
	public String viewVoById(HttpServletRequest request, String id) throws Exception {
		if (!StringUtils.isBlank(id)) {
			GroupClass vo = this.gcService.selectVoById(id);
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/groupClass/groupClassView";
	}

	/**
	 * 添加或者修改
	 * 
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateVo")
	@ResponseBody
	public GroupClass insertVo(HttpSession session, GroupClass vo) throws NoSuchAlgorithmException {

		String UUID = UUIDUtil.createUUID();//
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = vo.getId();
		if (!StringUtils.isBlank(id)) {
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo = this.gcService.updateVo(vo);
			
		} else {
			vo.setId(UUID);
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo = this.gcService.insertVo(vo);		
		}

		return vo;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteVoById")
	@ResponseBody
	public Boolean deleteVoById(String id) throws Exception {
		Boolean bl = this.gcService.deleteVoById(id);
		return bl;
	}

	/**
	 * 校验重复
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean check(GroupClass vo) throws Exception {
		Boolean bl = this.gcService.selectCheck(vo);
		return bl;
	}

	/**
	 * 增加修改表单弹出
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getProdIdList")
	public String getProdIdList(HttpServletRequest request, String prodId, String prodName) throws Exception {

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

		return "pages/jsp/dpi/groupClass/getProdIdList";
	}


/**
 * 增加修改表单弹出（url规则管理）
 * 
 * @param request
 * @param id
 * @return
 */
@RequestMapping(value = "/getProdIdListFuzzy")
public String getProdIdList1(HttpServletRequest request, String prodId, String prodName) throws Exception {

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

	return "pages/jsp/dpi/domainFuzzyRule/getProdIdList";
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

	return "pages/jsp/dpi/urlFuzzyRecognition/getProdIdList";
}
/**
 * 增加修改表单弹出（url规则管理）
 * 
 * @param request
 * @param id
 * @return
 */
@RequestMapping(value = "/getProdIdListYuMing")
public String getProdIdListYuMing(HttpServletRequest request, String prodId, String prodName) throws Exception {

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

	return "pages/jsp/dpi/urlFuzzyRecognition/getProdIdListYuMing";
	}

	/**
	 * 问题数据查看页面
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/falseDataViewFromExcle")
	public String falseDataViewFromExcle(HttpServletRequest request) throws Exception {

		// 列表信息
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey",
				gcService.invalidDataInExcle_repeatPrimaryKey);// 主键验证
		request.setAttribute("invalidDataInExcle_empty", gcService.invalidDataInExcle_empty);// 非空验证
		request.setAttribute("invalidDataInExcle_tooLong", gcService.invalidDataInExcle_tooLong);// 属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId",
				gcService.invalidDataInExcle_notTrue_prodId);// 业务验证
		request.setAttribute("list_false_cache", gcService.list_false_cache);// excle中与库中主键过长

		DpiUtils.excleFalseData_Info(request);// 提示信息
		DpiUtils.excleFalseData_TabName(request);// tab页名称

		return "pages/jsp/dpi/groupClass/falseDataViewFromExcle";
	}

	/**
	 * 问题数据插入库(将excle中在库中重复的数据)
	 * 
	 * @param request
	 * @param type
	 *            1:舍弃，2：更新
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/falseDataFromExcleUpdate")
	@ResponseBody
	public String falseDataFromExcleUpdate(HttpServletRequest request, String type) throws Exception {
		String result = "0";
		Boolean bl = gcService.insertListFalseCache(type);
		if (bl) {
			result = "1";
		}

		return result;
	}
}
