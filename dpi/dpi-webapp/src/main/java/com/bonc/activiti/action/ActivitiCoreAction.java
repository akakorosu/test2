package com.bonc.activiti.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ModelEntity;
import org.activiti.engine.repository.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bonc.activiti.ProcessManager;
import com.bonc.activiti.dao.mapper.ActivitiMapperExtend;
import com.bonc.common.cst.CST;
import com.bonc.common.page.Page;
import com.bonc.common.utils.Ajax;
import com.bonc.common.utils.JsonUtils;
import com.bonc.common.utils.Session;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 
 * ClassName: ActivitiCoreAction <br/>  
 * Reason: 用来配置流程. <br/> 
 * date: 2017年12月21日 上午9:53:38 <br/> 
 * 
 * @author WYB 
 * @version  
 * @since JDK 1.8
 */
@Controller
@RequestMapping(value = "/ActivitiCore")
public class ActivitiCoreAction {

	@Resource
	private TaskService taskService;
	@Resource
	private ProcessEngine processEngine;
	@Resource
	private HistoryService historyService;
	@Resource
	private ProcessManager processManager;
	@Resource
	private RepositoryService repositoryService;
	@Resource
	private ActivitiMapperExtend activitiMapperExtend;

	/**
	 * 获取所有流程定义
	 * @author WYB
	 * @param request
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/getAllProcess")
	@ResponseBody
	public String getAllProcess(HttpServletRequest request) throws Exception {
		return Ajax.responseString(CST.RES_SECCESS, processManager.listAll());
	}

	/**
	 * 新增或修改
	 * @author WYB
	 * @param request
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/doSaveOrUpdate")
	@ResponseBody
	public String create(HttpServletRequest request) throws Exception {
		String prmJson = request.getParameter("json");
		Map<String, String> prmMap = (Map<String, String>) JsonUtils.json2Java(prmJson);
		return JSON.toJSONString(processManager.createModel(prmMap));
	}

	/**
	 * 获取BPMN的链接
	 * @author WYB
	 * @param request
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/getEditorUrl")
	@ResponseBody
	public String getEditorUrl(HttpServletRequest request) {
		return processManager.getEditorUrl(request.getServerName(), String.valueOf(request.getServerPort()), request.getContextPath());
	}
	
	/**
	 * 表单弹出
	 * @author WYB
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/showActivitiModelForm")
	public String showActivitiModelForm() {
		return "pages/jsp/activiti/activitiModelForm";
	}

	/**
	 * 查询全部流程定义（分页）
	 * @author WYB
	 * @param model
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/selectPageList")
	@ResponseBody
	public Page<Model> selectPageList(ModelEntity model, Integer page, Integer rows) throws Exception {
		PageHelper.startPage(page, rows);
		List<Model> list = this.processManager.selectAllModel(model);
		Page<Model> pageBean = new Page<Model>(new PageInfo<Model>(list));
		return pageBean;
	}

	/**
	 * 部署流程
	 * @author WYB
	 * @param modelId
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/deploy")
	@ResponseBody
	public String deploy(String modelId) throws Exception {
		return JSON.toJSONString(processManager.deploy(modelId));
	}

	/**
	 * 删除流程
	 * @author WYB
	 * @param modelId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doDelete")
	@ResponseBody
	public String doDelete(String modelId) throws Exception {
		processManager.doDelete(modelId);
		return Ajax.responseString(CST.RES_SECCESS);
	}
	
	
	/**
	 * 任务检查
	 * @author WYB
	 * @param taskId
	 * @param flowType
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value="/checkTask")
	@ResponseBody
	public String checkTask(String taskId,String flowType){
		Map<String,String> param = new HashMap<String,String>();
		param.put("taskId", taskId);
		if("我发起的".equals(flowType)){
			return Ajax.responseString("111111");//没有数据不可以审批
		}else{
			param.put("assignee", Session.getUserId());
		}
		List<Map<String,String>> list = activitiMapperExtend.checkTask(param);
		if(list.size()>0){
			return Ajax.responseString("000000");//有数据可以审批
		}else{
			return Ajax.responseString("111111");//没有数据不可以审批
		}
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
	public Boolean check(ModelEntity model) throws Exception{
		Boolean bl = this.processManager.isModelNotExists(model);
		return bl;
	}
}
