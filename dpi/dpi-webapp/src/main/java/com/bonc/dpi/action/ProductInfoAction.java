package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.entity.ProductLabel;
import com.bonc.dpi.service.DimIaProductInfoService;
import com.bonc.dpi.service.ProductInfoService;
import com.bonc.dpi.service.ProductLabelService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

@Controller
@RequestMapping(value = "/productInfo")
@Transactional()
public class ProductInfoAction {

	@Resource
	private ProductInfoService piService;

	@Resource
	private DimIaProductInfoService dipiService;

	@Resource
	private ProductLabelService productLabelService;

	@RequestMapping(value = "/selectlist")
	@ResponseBody
	public List<ProductInfo> selectList(ProductInfo vo) throws Exception {
		// 查询条件处理prod_name
		String prodName = vo.getProdName();
		if (prodName != null && !"".equals(prodName)) {
			String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
			vo.setProdName(prodName_res);
		}
		List<ProductInfo> list = this.piService.selectList(vo);
		return list;
	}

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
	public PageJqGrid<ProductInfo> selectPageList(ProductInfo vo, Integer page, Integer rows) throws Exception {

		// 查询条件处理prod_name
		String prodName = vo.getProdName();
		if (prodName != null && !"".equals(prodName)) {
			// 多prodName条件查询
			if (prodName.contains("~")) {
				String[] prodNameArray = prodName.split("~");
				String prodName_res = "";
				for (String prodNameParamOne : prodNameArray) {
					prodName_res = prodName_res + "|" + DpiUtils.strEncrypt(prodNameParamOne);// 字符串分词加密
				}
				if (prodName_res.length() > 1) {
					prodName_res = prodName_res.substring(1);
				}
				vo.setProdName(prodName_res);
			}
			// 单prodName条件查询
			else {
				String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
				vo.setProdName(prodName_res);
			}
		}
		String iosProdName = vo.getIosProdName();
		String iosProdName_res = DpiUtils.strDecrypt(iosProdName);
		vo.setIosProdName(iosProdName_res);
		
		Page<ProductInfo> pageList = this.piService.selectList(vo, page, rows);
		PageJqGrid<ProductInfo> pageJqGrid = new PageJqGrid<ProductInfo>(pageList);
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
	public String showForm(HttpServletRequest request, String prodId) throws Exception {
		// 修改
		if (!StringUtils.isBlank(prodId)) {
			ProductInfo voParam = new ProductInfo();
			voParam.setProdId(prodId);
			ProductInfo vo = this.piService.selectVoByPrimaryKey(voParam);
			String prodName = vo.getProdName();
			String prodName_res = DpiUtils.strDecrypt(prodName);// 解密
			vo.setProdName(prodName_res);
			
			String iosProdName = vo.getIosProdName();
			String iosProdName_res = DpiUtils.strDecrypt(iosProdName);// 解密
			vo.setIosProdName(iosProdName_res);
			
			vo.setProdIdOld(prodId);// 校验主键用
			request.setAttribute("vo", vo);

			// 查询productLabel
			String labelCode = vo.getProdLabelCode();
			ProductLabel pl = productLabelService.selectVoByLabelCode(labelCode);
			if (pl != null) {
				String labelName1 = pl.getLabelName1();
				String labelName2 = pl.getLabelName2();
				if (labelName2 != null && !"".equals(labelName2)) {
					request.setAttribute("labelName", labelName2);// 有二级标签用二级
				} else {
					request.setAttribute("labelName", labelName1);// 没有有二级标签用一级
				}
			}
			// 查询产品类型labelname
			String labelCodeType = vo.getProdCatagory();
			ProductLabel plType = productLabelService.selectVoByLabelCode(labelCodeType);
			if (plType != null) {
				String labelName1 = plType.getLabelName1();
				String labelName2 = plType.getLabelName2();
				if (labelName2 != null && !"".equals(labelName2)) {
					request.setAttribute("labelNameType", labelName2);// 有二级标签用二级
				} else {
					request.setAttribute("labelNameType", labelName1);// 没有有二级标签用一级
				}
			}
		}
		// 添加
		else {
			ProductInfo vo = new ProductInfo();
			String prodId_max = piService.getProdId_Max(DpiUtils.PRODID_HEAD);// PRODID_HEAD是产品ID首字母
			String prodIdNew = DpiUtils.getProdId_next(prodId_max);// 自动生成新产品ID
			vo.setProdId(prodIdNew);
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/productInfo/productInfoForm";
	}

	/**
	 * 查看页面表单弹出
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/viewVoById")
	public String viewVoById(HttpServletRequest request, String prodId) throws Exception {
		// if(!StringUtils.isBlank(prodId)) {
		// ProductInfo voParam = new ProductInfo();
		// voParam.setProdId(prodId);
		// ProductInfo vo = this.piService.selectVoByPrimaryKey(voParam);
		//
		// String prodName = vo.getProdName();
		// String prodName_res = DpiUtils.strDecrypt(prodName);//解密
		// vo.setProdName(prodName_res);
		// request.setAttribute("vo", vo);
		// }
		ProductInfo voParam = new ProductInfo();
		voParam.setProdId(prodId);
		List<ProductInfo> vo = this.piService.selectRuleList(voParam);
		for (ProductInfo b : vo) {
			String name = b.getTableName();
			if ("1".equals(name)) {
				b.setTableNameEn("dim_ia_url_path");
				b.setTableName("URL路径表");
			}
			if ("2".equals(name)) {
				b.setTableNameEn("dim_ia_url_parameter");
				b.setTableName("URL参数表");
			}
			if ("3".equals(name)) {
				b.setTableNameEn("dim_ia_url_fuzzy_rule");
				b.setTableName("URL规则表");
			}
			if ("4".equals(name)) {
				b.setTableNameEn("dim_ia_domain_rule");
				b.setTableName("域名表");
			}
			if ("5".equals(name)) {
				b.setTableNameEn("dim_ia_ip_port_dynamic");
				b.setTableName("IP端口表");
			}
			if ("6".equals(name)) {
				b.setTableNameEn("dim_ia_group_class");
				b.setTableName("集团大小类表");
			}
			if ("7".equals(name)) {
				b.setTableNameEn("dim_ia_useragent_key");
				b.setTableName("UA关键字表");
			}
			if ("8".equals(name)) {
				b.setTableNameEn("dim_ia_useragent_rule");
				b.setTableName("UA规则表");
			}
			if ("9".equals(name)) {
				b.setTableNameEn("dim_ia_keyword_search_rule");
				b.setTableName("深度规则表");
			}

		}
		request.setAttribute("list", vo);
		return "pages/jsp/dpi/productInfo/productInfoView";
	}

	/**
	 * 查看页面表单弹出
	 * 
	 * @param request
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/updateAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String updateAll(HttpServletRequest request, ProductInfo vo) throws Exception {

		this.piService.updateUrlPath(vo);
		this.piService.updateUrlParameter(vo);
		this.piService.updateUrlFuzzyRule(vo);

		this.piService.updateDomainRule(vo);
		this.piService.updateIpPort(vo);
		this.piService.updateGroupClass(vo);
		this.piService.updateUseragentKey(vo);
		this.piService.updateUseragentRule(vo);
		this.piService.updateDimIaKeywordSearchRule(vo);

		return "";
	}
	@RequestMapping(value = "/tongBuBiao", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	@Transactional()
	public String tongBuBiao(HttpServletRequest request) throws Exception {

		List<ProductInfo> allProd = this.piService.getAllProd();
		for(ProductInfo prod:allProd){
			prod.setProdName(DpiUtils.strDecrypt(prod.getProdName()));//strDecrypt
			prod.setIosProdName(DpiUtils.strDecrypt(prod.getIosProdName()));
		}
		this.piService.delAllProd();//insertVoPlJm
		piService.insertListCacheJm(allProd);
		
		
		

		return "";
	}

	/**
	 * 获取产品id列表
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getProdIdList")
	public String getProdIdList(HttpServletRequest request, String prodId, String prodName) throws Exception {

		if (prodId != null && !"".equals(prodId)) {
			// 1根据prodId查询
			List<ProductInfo> list = piService.getProdIdListByProdId(prodId);
			for (ProductInfo productInfo : list) {
				String prodName_decrypt = productInfo.getProdName();
				prodName_decrypt = DpiUtils.strDecrypt(prodName_decrypt);// 解密
				productInfo.setProdName(prodName_decrypt);
			}
			request.setAttribute("list", list);
		} else {
			// 2根据prodName查询
			String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
			if (!StringUtils.isBlank(prodName)) {
				List<ProductInfo> list = piService.getProdIdListByProdName(prodName_res);
				for (ProductInfo productInfo : list) {
					String prodName_decrypt = productInfo.getProdName();
					prodName_decrypt = DpiUtils.strDecrypt(prodName_decrypt);// 解密
					productInfo.setProdName(prodName_decrypt);
				}
				request.setAttribute("list", list);
			}
		}

		return "pages/jsp/dpi/productInfo/getProdIdList";
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
	public ProductInfo insertVo(HttpSession session, ProductInfo vo) throws NoSuchAlgorithmException {

		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String prodIdOld = vo.getProdIdOld();// 校验主键用

		String prodName = vo.getProdName();
		String prodName_res = DpiUtils.strEncrypt(prodName);// 字符串分词加密
		String iosProdName = vo.getIosProdName();
		String iosProdName_res = DpiUtils.strEncrypt(iosProdName);// 字符串分词加密
		if (!StringUtils.isBlank(prodIdOld)) {
			vo.setAuthor(user.getLoginId());
			vo.setProdName(prodName_res);// 加密
			vo.setIosProdName(iosProdName_res);
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo.setLabelCheck("1");// 修改时稽核状态改为1
			this.piService.updateVo(vo);
//			ProductInfo voJm=new ProductInfo();
//			voJm=vo;
//			voJm.setProdName(DpiUtils.strDecrypt(voJm.getProdName()));//strDecrypt
//			
//			this.piService.updateVoJm(voJm);
		} else {
			String UUID = UUIDUtil.createUUID();// uuid
			vo.setId(UUID);
			vo.setProdName(prodName_res);// 加密
			vo.setIosProdName(iosProdName_res);
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo.setLabelCheck("0");// 新增时稽核状态改为0
			this.piService.insertVo(vo);
//			ProductInfo voJm=new ProductInfo();
//			voJm=vo;
//			voJm.setProdName(DpiUtils.strDecrypt(voJm.getProdName()));//strDecrypt
//			
//			this.piService.insertVoJm(voJm);
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
	public Boolean deleteVoById(String prodId) throws Exception {

		ProductInfo voParam = new ProductInfo();
		voParam.setProdId(prodId);

		Boolean bl = this.piService.deleteVoByPrimaryKey(voParam);
		//this.piService.deleteVoByPrimaryKeyJm(voParam);
//		b.setTableNameEn("dim_ia_url_path");
//
//		b.setTableNameEn("dim_ia_url_parameter");
//
//		b.setTableNameEn("dim_ia_url_fuzzy_rule");
//
//		b.setTableNameEn("dim_ia_domain_rule");
//
//		b.setTableNameEn("dim_ia_ip_port_dynamic");
//
//		b.setTableNameEn("dim_ia_group_class");
//
//		b.setTableNameEn("dim_ia_useragent_key");
//
//		b.setTableNameEn("dim_ia_useragent_rule");
        this.piService.deleteVoByUrlPath(voParam);
		this.piService.deleteVoByUrlParameter(voParam);
		this.piService.deleteVoByFuzzyRule(voParam);
		this.piService.deleteVoByDomainRule(voParam);
		this.piService.deleteVoByIpPort(voParam);
		this.piService.deleteVoByGroupClass(voParam);
		this.piService.deleteVoByUseragentKey(voParam);
		this.piService.deleteVoByUseragentRule(voParam);
		return bl;
	}

	/**
	 * 校验
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean check(ProductInfo vo) throws Exception {
		Boolean bl = true;
		String prodIdOld = vo.getProdIdOld();// 改之前的
		String prodIdNew = vo.getProdId();// 新的prodId

		// 修改页面
		if (!"".equals(prodIdOld) && prodIdOld != null) {
			// 如果prodIdNew变化了
			if (!prodIdOld.equals(prodIdNew)) {
				bl = !checkPrimaryKey(prodIdNew);
			}
		}
		// 新增页面
		else {
			bl = !checkPrimaryKey(prodIdNew);
		}
		return bl;
	}

	/**
	 * 校验重复
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkRepeat")
	@ResponseBody
	public Boolean checkRepeat(ProductInfo vo) throws Exception {

		String id = vo.getId();
		String prodName = vo.getProdName();
		ProductInfo voParam = new ProductInfo();
		voParam.setProdName(DpiUtils.strEncrypt(prodName));// 加密
		ProductInfo voCheck = this.piService.selectVoByPrimaryKey(voParam);
		if (voCheck != null) {
            // 存在,重复
            return id != null && id.equals(voCheck.getId());// 不存在,不重复)(修改自身)
		}
		return true;// 不存在,不重复
	}

	/**
	 * 判断主键是否存在
	 * 
	 * @return
	 */
	public Boolean checkPrimaryKey(String prodIdNew) {

		ProductInfo voParam = new ProductInfo();
		voParam.setProdId(prodIdNew);
		ProductInfo voCheck = this.piService.selectVoByPrimaryKey(voParam);
        return voCheck != null;// 存在
// 不存在
    }

	/**
	 * 不可为空，prod_id必须在产品表里
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/prodIdCheck")
	@ResponseBody
	public Boolean prodIdCheck(ProductInfo vo) throws Exception {
		String prodId = vo.getProdId();
		// 如果产品id不存在则返回false
		if (prodId == null || "".equals(prodId)) {
			return false;
		}
		Boolean bl = this.piService.prodIdCheck(vo);
		return bl;
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", piService.invalidDataInExcle_repeatPrimaryKey);// 主键验证
		request.setAttribute("invalidDataInExcle_empty", piService.invalidDataInExcle_empty);// 非空验证
		request.setAttribute("invalidDataInExcle_tooLong", piService.invalidDataInExcle_tooLong);// 属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodLable", piService.invalidDataInExcle_notTrue_prodLable);// 业务验证

		List<OperationFlow> list_false_cache = piService.list_false_cache;
		for (OperationFlow operationFlow : list_false_cache) {
			ProductInfo vo = (ProductInfo) operationFlow;
			String prodName = vo.getProdName();
			vo.setProdName(DpiUtils.strDecrypt(prodName));// 解密
		}
		request.setAttribute("list_false_cache", list_false_cache);// excle中与库中主键过长

		DpiUtils.excleFalseData_Info(request);// 提示信息
		DpiUtils.excleFalseData_TabName(request);// tab页名称

		return "pages/jsp/dpi/productInfo/falseDataViewFromExcle";
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
		Boolean bl = piService.insertListFalseCache(type);
		if (bl) {
			result = "1";
		}

		return result;
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
	@RequestMapping(value = "/falseDataFromExcleUpdateDomain")
	@ResponseBody
	public String falseDataFromExcleUpdateDomain(HttpServletRequest request, String type) throws Exception {
		String result = "0";
		Boolean bl = dipiService.insertListFalseCacheDomain(type);
		if (bl) {
			result = "1";
		}

		return result;
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
	@RequestMapping(value = "/falseDataFromExcleUpdateIp")
	@ResponseBody
	public String falseDataFromExcleUpdateIp(HttpServletRequest request, String type) throws Exception {
		String result = "0";
		Boolean bl = dipiService.insertListFalseCacheIp(type);
		if (bl) {
			result = "1";
		}

		return result;
	}

	/**
	 * 问题数据查看页面
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/falseDataViewFromExcle_2")
	public String falseDataViewFromExcle_2(HttpServletRequest request) throws Exception {

		// 列表信息
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey_domainCode",
				dipiService.invalidDataInExcle_repeatPrimaryKey_domainCode);// 主键验证domainCode
		request.setAttribute("invalidDataInExcle_empty", dipiService.invalidDataInExcle_empty);// 非空验证
		request.setAttribute("invalidDataInExcle_tooLong", dipiService.invalidDataInExcle_tooLong);// 属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodLabelCode",
				dipiService.invalidDataInExcle_notTrue_prodLabelCode);// 业务验证

		List<OperationFlow> list_false_cache_productInfo = dipiService.list_false_cache_productInfo;
		for (OperationFlow operationFlow : list_false_cache_productInfo) {
			ProductInfo vo = (ProductInfo) operationFlow;
			String prodName = vo.getProdName();
			vo.setProdName(DpiUtils.strDecrypt(prodName));// 解密
		}
		request.setAttribute("list_false_cache_productInfo", list_false_cache_productInfo);//

		List<OperationFlow> list_false_cache_domainRule = dipiService.list_false_cache_domainRule;
		request.setAttribute("list_false_cache_domainRule", list_false_cache_domainRule);// 域名

		List<OperationFlow> list_false_cache_ipPort = dipiService.list_false_cache_ipPort;
		request.setAttribute("list_false_cache_ipPort", list_false_cache_ipPort);// ip

		DpiUtils.excleFalseData_Info(request);// 提示信息
		DpiUtils.excleFalseData_TabName(request);// tab页名称

		return "pages/jsp/dpi/productInfo/falseDataViewFromExcle_2";
	}

}
