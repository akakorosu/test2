package com.bonc.dpi.action;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.bonc.dpi.dao.entity.ContentLabel;
import com.bonc.dpi.service.ContentLabelService;
import com.bonc.dpi.utils.DpiUtils;
import com.bonc.system.dao.entity.SysUser;
import com.github.pagehelper.Page;
/**
 * 内容标签管理
 * @author wanghao
 *
 */
@Controller
@RequestMapping(value = "/content")
public class ContentLabelAction {
	@Autowired
	ContentLabelService contentLabelService;
	private static final Logger logger=LoggerFactory.getLogger(IndustryOverviewAction.class);
	
	@RequestMapping(value = "/selectlist")
	@ResponseBody
	public List<ContentLabel> selectList(ContentLabel obj) {
		List<ContentLabel> list = contentLabelService.selectList(obj);
		return list;
	}
	

	@RequestMapping(value = "/selectContentLabelName1",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String selectContentLabelName1(ContentLabel obj) throws Exception{
		try {
			List<String> result=contentLabelService.selectContentLabelName1();
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 分页
	 * @param df
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(value = "/selectpagelist",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public PageJqGrid<ContentLabel> selectPageList(ContentLabel obj, Integer page, Integer rows) {
		Page<ContentLabel> pageList = contentLabelService.selectList(obj, page, rows);
		PageJqGrid<ContentLabel> pageJqGrid = new PageJqGrid<ContentLabel>(pageList);
		return pageJqGrid;
	}
	/**
	 * 查看表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/viewById",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String viewById(HttpServletRequest request, String id) throws Exception{
		if(!StringUtils.isBlank(id)) {
			ContentLabel obj = contentLabelService.selectById(id);
			request.setAttribute("obj", obj);
		} 
		return "pages/jsp/dpi/contentLabel/contentLabelView";
	}
	
	/**
	 * 增加修改表单弹出
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/showForm",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String showForm(HttpServletRequest request, String id) throws Exception{
		if(!StringUtils.isBlank(id)) {
			ContentLabel obj = contentLabelService.selectById(id);
			request.setAttribute("obj", obj);
			return "pages/jsp/dpi/contentLabel/contentLabelFormChange";
		}
		return "pages/jsp/dpi/contentLabel/contentLabelFormInsert";
	}
	
	/**
	 * 添加或者修改
	 * @param session
	 * @param obj
	 * @return
	 */
	@RequestMapping(value = "/insertOrUpdate",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public ContentLabel insert(HttpSession session,ContentLabel obj) throws NoSuchAlgorithmException {	
		String UUID = UUIDUtil.createUUID();
		SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
		String id = obj.getId();
		if(!StringUtils.isBlank(id)) {
			obj.setOpTime(DpiUtils.sdf.format(new Date()));
			obj = contentLabelService.update(obj);								
		} else {
			char[] arr=new char[26];
			for(int i=0;i<26;i++) {
				arr[i]=(char)(65+i);
			}
			Random random = new Random();
			int level=Integer.parseInt(obj.getContentLabelLevel());
			do {
				if(level==1) {
                    String contentLabelCode1=generatedContentLabelCode(obj,arr,random,"");
                    obj.setContentLabelCode1(contentLabelCode1);
                    obj.setContentLabelCode(contentLabelCode1);
                    obj.setContentLabelName(obj.getContentLabelName1Param());
                }else {
                    String code="";
                    if(level==2) {
                        code=obj.getContentLabelCode1();
                        String contentLabelCode2=generatedContentLabelCode(obj,arr,random,code);
                        obj.setContentLabelCode2(contentLabelCode2);
                        obj.setContentLabelCode(contentLabelCode2);
                        obj.setContentLabelName(obj.getContentLabelName2());
                    }
                    if(level==3) {
                        code=obj.getContentLabelCode2();
                        String contentLabelCode3=generatedContentLabelCode(obj,arr,random,code);
                        obj.setContentLabelCode3(contentLabelCode3);
                        obj.setContentLabelCode(contentLabelCode3);
                        obj.setContentLabelName(obj.getContentLabelName3());
                    }
                    if(level==4) {
                        code=obj.getContentLabelCode3();
                        String contentLabelCode4=generatedContentLabelCode(obj,arr,random,code);
                        obj.setContentLabelCode4(contentLabelCode4);
                        obj.setContentLabelCode(contentLabelCode4);
                        obj.setContentLabelName(obj.getContentLabelName4());
                    }
                    if(level==5) {
                        code=obj.getContentLabelCode4();
                        String contentLabelCode5=generatedContentLabelCode(obj,arr,random,code);
                        obj.setContentLabelCode5(contentLabelCode5);
                        obj.setContentLabelCode(contentLabelCode5);
                        obj.setContentLabelName(obj.getContentLabelName5());
                    }
                    if(level==6) {
                        code=obj.getContentLabelCode5();
                        String contentLabelCode6=generatedContentLabelCode(obj,arr,random,code);
                        obj.setContentLabelCode6(contentLabelCode6);
                        obj.setContentLabelCode(contentLabelCode6);
                        obj.setContentLabelName(obj.getContentLabelName6());
                    }
				}
			}while(contentLabelService.checkData(obj)>0);
			obj.setId(UUID);
			obj.setAuthor(user.getLoginId());
			obj.setOpTime(DpiUtils.sdf.format(new Date()));
			obj = contentLabelService.insert(obj);
		}		
		return obj;
	}
	/**
	 * 生成标签编码
	 * @param labelName
	 * @param level
	 * @param obj
	 * @param arr
	 * @param random
	 * @param builder
	 * @param code
	 * @return
	 */
	private String generatedContentLabelCode(ContentLabel obj,char[] arr,Random random,String code){					
		return code+arr[random.nextInt(26)]+arr[random.nextInt(26)]+random.nextInt(10)+random.nextInt(10);
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteById",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Boolean deleteById(ContentLabel obj) throws Exception{
		Boolean bl = contentLabelService.deleteById(obj);
		return bl;
	}
	
	/**
	 * 校验重复
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/check",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public Boolean check(ContentLabel obj) throws Exception{
		Boolean bl = contentLabelService.check(obj);
		return bl;
	}
	
	/**
	 * 通过标签分类模糊查询
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectByLabelCode",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public String selectByLabelCode(HttpServletRequest request,String  contentLabelName) throws Exception{
		List<ContentLabel> list = contentLabelService.selectByLabelCode(contentLabelName);
		request.setAttribute("list", list);
		return "pages/jsp/dpi/contentLabel/getContenLabelList";
	}
	/**
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectLabelList",method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String selectLabelList(ContentLabel obj) throws Exception{
		try {
			List<ContentLabel> result=contentLabelService.selectLabelList(obj);
			return Ajax.responseString(CST.RES_SECCESS, result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
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
		request.setAttribute("invalidDataInExcle_repeatPrimaryKey", contentLabelService.invalidDataInExcle_repeatPrimaryKey);//主键验证
		request.setAttribute("invalidDataInExcle_empty", contentLabelService.invalidDataInExcle_empty);//非空验证
		request.setAttribute("invalidDataInExcle_tooLong", contentLabelService.invalidDataInExcle_tooLong);//属性过长验证
		request.setAttribute("invalidDataInExcle_notTrue_prodId", contentLabelService.invalidDataInExcle_notTrue_prodId);//业务验证
		request.setAttribute("list_false_cache", contentLabelService.list_false_cache);//excle中与库中主键过长
		
		DpiUtils.excleFalseData_Info(request);//提示信息
		DpiUtils.excleFalseData_TabName(request);//tab页名称
		
		return "pages/jsp/dpi/contentLabel/falseDataViewFromExcle";
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
		Boolean bl = contentLabelService.insertListFalseCache(type);
		if(bl){
			result = "1";
		}
		
		return result;
	}
}
