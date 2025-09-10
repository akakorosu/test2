package com.bonc.dpi.action;

import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.KeywordGroup;
import com.bonc.dpi.dao.entity.KeywordSearchRule;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.service.KeywordGroupService;
import com.bonc.dpi.service.KeywordSearchRuleService;
import com.bonc.dpi.service.ProductInfoService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;

/**
 * 关键字规则Action dim_ia_keyword_search_rule
 * 
 * @author BONC-XUXL
 *
 */

@Controller
@RequestMapping(value = "/keywordSearchRule")
public class KeywordSearchRuleAction {

	@Resource
	private KeywordSearchRuleService ksrService;

	@Autowired
	private KeywordGroupService kgService;

	@Autowired
	private ProductInfoService piService;

	@RequestMapping(value = "/selectlistsimple")
	@ResponseBody
	public List<KeywordSearchRule> selectListSimple(KeywordSearchRule vo) throws Exception {
		List<KeywordSearchRule> list = this.ksrService.selectListSimple(vo);
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
	public PageJqGrid<KeywordSearchRule> selectPageList(HttpServletRequest request, KeywordSearchRule vo, Integer page,
			Integer rows) throws Exception {

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

		Page<KeywordSearchRule> pageList = this.ksrService.selectList(vo, page, rows);
		PageJqGrid<KeywordSearchRule> pageJqGrid = new PageJqGrid<KeywordSearchRule>(pageList);
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
			KeywordSearchRule vo = this.ksrService.selectVoById(id);

			ProductInfo piParam = new ProductInfo();
			String prodId = vo.getProdid();
			piParam.setProdId(prodId);
			ProductInfo voPi = piService.selectVoByPrimaryKey(piParam);
			if (voPi != null) {
				vo.setProdName(DpiUtils.strDecrypt(voPi.getProdName()));
			}

			KeywordGroup kgParam = new KeywordGroup();
			kgParam.setGroupType(vo.getGroupType());
			KeywordGroup voKg = kgService.selectVoByPrimaryKey(kgParam);
			if (voKg != null) {
				vo.setGroupName(voKg.getGroupName());
			}
			if (!StringUtils.isEmpty(vo.getParseRuleAndriod())) {
				vo.setParseRuleAndriod(vo.getParseRuleAndriod().replaceAll("\"", "&quot;"));
			}
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/keywordSearchRule/keywordSearchRuleForm";
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
			KeywordSearchRule vo = this.ksrService.selectVoById(id);

			ProductInfo piParam = new ProductInfo();
			String prodId = vo.getProdid();
			piParam.setProdId(prodId);
			ProductInfo voPi = piService.selectVoByPrimaryKey(piParam);
			if (voPi != null) {
				vo.setProdName(DpiUtils.strDecrypt(voPi.getProdName()));
			}

			KeywordGroup kgParam = new KeywordGroup();
			kgParam.setGroupType(vo.getGroupType());
			KeywordGroup voKg = kgService.selectVoByPrimaryKey(kgParam);
			if (voKg != null) {
				vo.setGroupName(voKg.getGroupName());
			}
			if (!StringUtils.isEmpty(vo.getParseRuleAndriod())) {
				vo.setParseRuleAndriod(vo.getParseRuleAndriod().replaceAll("\"", "&quot;"));
			}
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/keywordSearchRule/keywordSearchRuleView";
	}

	/**
	 * 校验规则页面
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/checkRuleForm")
	public String getCheckRuleForm(HttpServletRequest request, String type, String rule) throws Exception {
		request.setAttribute("rule", rule);
		request.setAttribute("type", type);
		return "pages/jsp/dpi/keywordSearchRule/checkRuleForm";
	}

	/**
	 * 校验规则
	 * 
	 * @param request
	 * @param rule：正则
	 * @param url：url
	 * @param num：分组group位置
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkRule")
	@ResponseBody
	public String checkRule(String rule, String url, Integer num) throws Exception {

		// rule = "/.*&_iwt_vid=([0-9a-z]+)";
		// url =
		// "qq.irs01.com/irt?_iwt_UA=UA-qq-120001&jsonp=SetIDA0&_iwt_vid=cq88y54kd9gkiyb&_iwt_muid=1272BE17D87E4AF6&_iwt_reqid=0OFoJGYAWzYK0wt00QDZqWJfAeh0&os=0&imei=1272BE17D87E4AF608A9FBAA8BEE41B6&mac=B345CE0F66F952B17EB19ED79D847278&idfa=&ip=36.104.225.208&useragent=[UA]&ts=[timestampe]&androidid=8AD72D99010D56DE5CE62221D17CC8A8&openudid=4538397881e90c1c95339f7c95802d40&_z=m&_iwt_vid1=v0026nvfau0&requestid=null";
		// num = 1;
		String result = "";
		rule = URLDecoder.decode(rule, "UTF-8");
		url = URLDecoder.decode(url, "UTF-8");
		Pattern p = Pattern.compile(rule);
		Matcher m = p.matcher(url);
		if (m.find()) {
			try {
				result = m.group(num);
			} catch (Exception e) {
				result = "输入规则或者url异常";
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			result = "无结果";
		}
		return result;
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
	public KeywordSearchRule insertVo(HttpSession session, KeywordSearchRule vo) throws NoSuchAlgorithmException {

		String UUID = UUIDUtil.createUUID();//
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = vo.getId();
		if (!StringUtils.isBlank(id)) {
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			this.ksrService.updateVo(vo);
		} else {
			vo.setId(UUID);
			vo.setAuthor(user.getLoginId());
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo.setIsValid("1");
			this.ksrService.insertVo(vo);
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
		Boolean bl = this.ksrService.deleteVoById(id);
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
	public Boolean checkRepeat(KeywordSearchRule vo) throws Exception {
		// String parseRuleAndriod = vo.getParseRuleAndriod();//安卓截取规则
		// String parseRuleIos = vo.getParseRuleIos();//苹果截取规则
		// //如果安卓截取规则和苹果截取规则同时为空，则返回false
		// if(("".equals(parseRuleAndriod)|| parseRuleAndriod==null) &&
		// ("".equals(parseRuleIos)|| parseRuleIos==null)){
		// return false;
		// }
		Boolean bl = this.ksrService.checkRepeat(vo);
		return bl;
	}

	/**
	 * 校验GroupType
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkGroupType")
	@ResponseBody
	public Boolean checkGroupType(KeywordSearchRule vo) throws Exception {
		Boolean bl = this.ksrService.checkGroupType(vo);
		return bl;
	}

	/**
	 * 校验GroupType
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkGroupName")
	@ResponseBody
	public Boolean checkGroupName(KeywordSearchRule vo) throws Exception {
		Boolean bl = this.ksrService.checkGroupName(vo);
		return bl;
	}

	/**
	 * 校验num
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkNum")
	@ResponseBody
	public Boolean checkNum(KeywordSearchRule vo) throws Exception {

		KeywordSearchRule voParam = new KeywordSearchRule();
		if (vo != null) {
			voParam.setNum(vo.getNum());
			voParam.setId(vo.getId());
		}
		Boolean bl = this.ksrService.checkNum(voParam);
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", ksrService.invalidDataInExcle_repeatPrimaryKey);// 主键验证
		request.setAttribute("invalidDataInExcle_empty", ksrService.invalidDataInExcle_empty);// 非空验证
		request.setAttribute("invalidDataInExcle_tooLong", ksrService.invalidDataInExcle_tooLong);// 属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", ksrService.invalidDataInExcle_notTrue_prodId);// 业务验证,产品id
		request.setAttribute("invalidDataInExcle_notTrue_groupType", ksrService.invalidDataInExcle_notTrue_groupType);// 业务验证,应用分组编码
		request.setAttribute("list_false_cache", ksrService.list_false_cache);// excle中与库中主键过长

		DpiUtils.excleFalseData_Info(request);// 提示信息
		DpiUtils.excleFalseData_TabName(request);// tab页名称

		return "pages/jsp/dpi/keywordSearchRule/falseDataViewFromExcle";
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
		Boolean bl = ksrService.insertListFalseCache(type);
		if (bl) {
			result = "1";
		}

		return result;
	}
}
