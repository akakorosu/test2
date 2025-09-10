package com.bonc.activiti.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bonc.activiti.dao.entity.FlowResource;
import com.bonc.activiti.service.FlowManagerService;
import com.bonc.common.page.Page;
/**
 * 
 * ClassName: FlowManagerAction <br/>  
 * Reason: 用来配置流程与资源的映射关系. <br/> 
 * date: 2017年12月21日 上午9:53:38 <br/> 
 * 
 * @author WYB 
 * @version  
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/flowManager")
public class FlowManagerAction {
	
	@Resource
	private FlowManagerService flowManagerService;

	/**
	 * 
	 * 业务关联一览数据（分页）
	 * @author WYB
	 * @param flow
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception 
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/selectPageList")
	@ResponseBody
	public Page<FlowResource> selectPageList(FlowResource flow, Integer page, Integer rows) throws Exception {
		Page<FlowResource> pageBean = this.flowManagerService.selectPageList(flow,page,rows);
		return pageBean;
	}
	
	
	/**
	 * 表单弹出
	 * @author WYB
	 * @param request
	 * @param appid
	 * @return
	 * @throws Exception 
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/showFlowManagerForm")
	public String showFlowManagerForm(HttpServletRequest request, String appid) throws Exception {
		FlowResource flow = this.flowManagerService.getFlowManagerForm(appid);
		if(flow!=null) {
			request.setAttribute("vo", flow);
		}
		return "pages/jsp/activiti/flowManagerForm";
	}
	
	/**
	 * 新增或修改
	 * @author WYB
	 * @param flow
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/doSaveOrUpdate")
	@ResponseBody
	public String doSaveOrUpdate(FlowResource flow) throws Exception {
		//主键为空执行插入操作
		if(null==flow.getAppid()||"".equals(flow.getAppid())){
			this.flowManagerService.insert(flow);
		}else{//主键不为空，执行修改操作
			this.flowManagerService.update(flow);
		}
		return JSON.toJSONString(flow.getAppid());
	}
	
	/**
	 * 
	 * 删除业务关联
	 * 
	 * @author WYB
	 * @param request
	 * @return
	 * @throws Exception 
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public String delete(HttpServletRequest request) throws Exception {
		String appid = request.getParameter("appid");
		Boolean result = this.flowManagerService.delete(appid);
		return JSON.toJSONString(result);
	}
	
	/**
	 * 流程主键存在性校验
	 * @author WYB
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/check")
	@ResponseBody
	public Boolean check(FlowResource flow) throws Exception{
		Boolean bl = this.flowManagerService.isFlowNotExists(flow);
		return bl;
	}
}
