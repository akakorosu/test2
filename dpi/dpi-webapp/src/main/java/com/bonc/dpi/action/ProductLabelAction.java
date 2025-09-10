package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import com.bonc.dpi.dao.entity.ContentLabel;
import com.bonc.dpi.dao.entity.ProductLabel;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
import com.bonc.dpi.service.ProductLabelService;
import com.bonc.dpi.utils.DpiUtils;

/**
 * 标签管理
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/productLabel")
public class ProductLabelAction {
	
	@Autowired
	ProductLabelService productLabelService;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	@RequestMapping(value = "/showLabelTree")
	public String showLabelTree() throws Exception{
		return "pages/jsp/dpi/productLabel/productLabelTree";
	}
	
	@RequestMapping(value = "/selectlabeltree")
	@ResponseBody
	public List<ProductLabel> selectLabelTree(ProductLabel vo, Integer page, Integer rows) throws Exception{
		List<ProductLabel> list = productLabelService.selectLabelTree();
		return list;
	}
	
	@RequestMapping(value = "/selectlist")
	@ResponseBody
	public List<ProductLabel> selectList(ProductLabel vo) throws Exception{
		List<ProductLabel> list = productLabelService.selectList(vo);
		return list;
	}
	
	/**
	 * 分页查询
	 * @param ksr
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist")
	@ResponseBody
	public PageJqGrid<ProductLabel> selectPageList(ProductLabel vo, Integer page, Integer rows) throws Exception{
		Page<ProductLabel> pageList = productLabelService.selectList(vo, page, rows);
		PageJqGrid<ProductLabel> pageJqGrid = new PageJqGrid<ProductLabel>(pageList);
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
			ProductLabel vo = productLabelService.selectVoById(id);
			request.setAttribute("vo", vo);
			return "pages/jsp/dpi/productLabel/productLabelFormChange";
		}
		return "pages/jsp/dpi/productLabel/productLabelForm";
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
			ProductLabel vo = productLabelService.selectVoById(id);
			request.setAttribute("vo", vo);
		}
		return "pages/jsp/dpi/productLabel/productLabelView";
	}
	
	/**
	 * 添加或者修改
	 * @param session
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdateVo")
	@ResponseBody
	public ProductLabel insertVo(HttpSession session,ProductLabel vo) throws NoSuchAlgorithmException {
		
		String UUID = UUIDUtil.createUUID();//
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = vo.getId();
		if(!StringUtils.isBlank(id)) {
			vo.setOpTime(DpiUtils.sdf.format(new Date()));
			vo = productLabelService.updateVo(vo);
		} else {
			char[] arr=new char[26];
			for(int i=0;i<26;i++) {
				arr[i]=(char)(65+i);
			}
			Random random = new Random();
			if(StringUtils.isBlank(vo.getLabelName2Param())) {
				String code1="";
				for(char c:arr){
					String letter = productLabelService.selectLetter(c+"%");
					int num = letter.getBytes()[0] - 65;
					if(num==25){
						continue;
					}else{
						code1 = c +""+ (char) (65 + num + 1) + "00";
						break;
					}
				}
				vo.setLabelCode(code1);
				vo.setLabelCode1(code1);
				vo.setJoinLabelName(vo.getLabelName1Param());

				vo.setId(UUID);
				vo.setAuthor(user.getLoginId());
				vo.setOpTime(sdf.format(new Date()));
				productLabelService.insertVo(vo);
			}else {
				//查询一级标签在数据库中是否存在
				ProductLabel obj=productLabelService.selectLabelName1(vo);
				if(obj!=null) {
					vo.setLabelCode1(obj.getLabelCode1());
					//查询最大值
					String max=productLabelService.selectMax(vo);
					if(StringUtils.isNotBlank(max)){
						Integer m=Integer.parseInt(max)+1;
						if(m<10){
							vo.setLabelCode2(obj.getLabelCode()+obj.getLabelCode().substring(0,2)+"0"+m);
						}else{
							vo.setLabelCode2(obj.getLabelCode()+obj.getLabelCode().substring(0,2)+m);
						}
					}else{
						vo.setLabelCode2(obj.getLabelCode()+obj.getLabelCode().substring(0,2)+"00");
					}
					vo.setLabelCode(vo.getLabelCode2());
					vo.setJoinLabelName(vo.getLabelName2Param());
					vo.setId(UUID);
					vo.setAuthor(user.getLoginId());
					vo.setOpTime(sdf.format(new Date()));
					productLabelService.insertVo(vo);
				}else {
					String code1="";
					for(char c:arr){
						String letter = productLabelService.selectLetter(c+"%");
						int num = letter.getBytes()[0] - 65;
						if(num==25){
							continue;
						}else{
							code1 = c +""+ (char) (65 + num + 1) + "00";
							break;
						}
					}
					String labelName2=vo.getLabelName2Param();
					vo.setLabelCode(code1);
					vo.setLabelCode1(code1);
					vo.setLabelName2Param("");
					vo.setJoinLabelName(vo.getLabelName1Param());
					vo.setId(UUID);
					vo.setAuthor(user.getLoginId());
					vo.setOpTime(sdf.format(new Date()));
					productLabelService.insertVo(vo);

					vo.setId(UUIDUtil.createUUID());
					vo.setLabelName2Param(labelName2);
					vo.setLabelCode(code1+code1.substring(0,2)+"00");
					vo.setLabelCode2(code1+code1.substring(0,2)+"00");
					vo.setJoinLabelName(vo.getLabelName2Param());
					vo = productLabelService.insertVo(vo);
				}			
			}

		}
		
		return vo;
	}
	private String generatedContentLabelCode(char[] arr,Random random,String code){					
		return code+arr[random.nextInt(26)]+arr[random.nextInt(26)]+random.nextInt(10)+random.nextInt(10);
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteVoById")
	@ResponseBody
	public Boolean deleteVoById(ProductLabel vo) throws Exception{
		Boolean bl = productLabelService.deleteVoById(vo);
		return bl;
	}
	
	/**
	 * 校验重复
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check")
	@ResponseBody
	public Boolean check(ProductLabel vo) throws Exception{
		Boolean bl = productLabelService.selectCheck(vo);
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", productLabelService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", productLabelService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", productLabelService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", productLabelService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", productLabelService.list_false_cache);//excle中与库中主键过长
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/productLabel/falseDataViewFromExcle";
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
		Boolean bl = productLabelService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
}
