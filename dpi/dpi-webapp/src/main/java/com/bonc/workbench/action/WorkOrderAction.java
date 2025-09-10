package com.bonc.workbench.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bonc.workbench.service.WorkOrderService;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.bonc.activiti.ProcessManager;
import com.bonc.activiti.dao.entity.FlowResource;
import com.bonc.activiti.dao.mapper.FlowLogMapper;
import com.bonc.activiti.dao.mapper.FlowResourceMapper;
import com.bonc.activiti.entity.LocalProcessVariables;
import com.bonc.activiti.entity.LocalTaskVariables;
import com.bonc.workbench.bean.WorkOrderInfo;
import com.bonc.workbench.dao.mapper.WorkOrderInfoMapper;
import com.bonc.common.annotation.ArchivesLog;
import com.bonc.common.cst.CST;
import com.bonc.common.utils.Ajax;
import com.bonc.common.utils.DateUtil;
import com.bonc.common.utils.JsonUtils;
import com.bonc.common.utils.PageJqGrid;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.system.dao.entity.SysAttachment;
import com.bonc.system.dao.entity.SysUser;
import com.bonc.system.dao.mapper.SysAttachmentMapper;
import com.bonc.system.dao.mapper.SysUserMapper;
import com.bonc.system.service.SysAttachmentService;
import com.github.pagehelper.Page;

/**
 * 
 * ClassName: WorkOrderAction <br/>
 * Reason: 工单管理. <br/>
 * date: 2017年1月18日 下午4:44:37 <br/>
 * 
 * @author wangyanbo@bonc.com.cn
 * @version
 * @since JDK 1.8
 */

@Controller
@RequestMapping(value = "/workOrder")
public class WorkOrderAction {

	@Resource
	private ProcessManager processManager;

	@Resource
	private FlowLogMapper flowLogMapper;

	@Resource
	private FlowResourceMapper flowResourceMapper;

	@Resource
	private WorkOrderInfoMapper workOrderInfoMapper;

	@Resource
	private WorkOrderService workOrderService;

	@Resource
	private SysAttachmentService sysAttachmentService;

	@Resource
	private SysAttachmentMapper sysAttachmentMapper;

	@Resource
	private SysUserMapper sysUserMapper;
	
	@Resource
	private SysAttachmentService attchmentService;
	
	/**
	 * 工单弹出
	 * @author WYB
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/showWorkOrderForm")
	public String showWorkOrderForm(HttpServletRequest request, String id) {
		WorkOrderInfo woInfo = this.workOrderInfoMapper.selectByPrimaryKey(id);
		request.setAttribute("vo", woInfo);
		return "pages/jsp/workbench/workorder/myWorkOrderForm";
	}
	
	/**
	 * 工单明细弹出
	 * @author WYB
	 * @param id
	 * @param procId
	 * @param pageType
	 * @return
	 * @since JDK 1.8
	 */
	@RequestMapping(value = "/showDetailForm")
	public String showDetailForm(HttpServletRequest request, String id, String procId, String pageType) {
		WorkOrderInfo woInfo = this.workOrderInfoMapper.selectByPrimaryKey(id);
		// 用于显示流程表格
		woInfo.setProcId(procId);
		// 判断是否需要显示办理工单模块
		request.setAttribute("pageType", pageType);
		request.setAttribute("vo", woInfo);
		return "pages/jsp/workbench/workorder/workOrderDetailForm";
	}
	
	/**
	 * 获取用户列表
	 * @author WYB
	 * @return
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "获取用户列表")
	@RequestMapping(value = "/getAssignList")
	@ResponseBody
	public String getAssignList() {
		SysUser sysUser = new SysUser();
		List<SysUser> list = this.sysUserMapper.selectList(sysUser);
		return Ajax.responseString(CST.RES_SECCESS, null, list);
	}
	
	/**
	 * 获取文件
	 * @author WYB
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "获取文件")
	@RequestMapping(value = "/getFileByTableId")
	@ResponseBody
	public String getFileByTableId(String id) {
		List<SysAttachment> list = this.sysAttachmentMapper.selectAttachByTableId(id+"_send");
		return Ajax.responseString(CST.RES_SECCESS, null, list);
	}
	
	/**
	 * 获取办理人（多条）
	 * @author WYB
	 * @param assigneeIds
	 * @return
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "获取办理人（多条）")
	@RequestMapping(value = "/getAssigneeByIds")
	@ResponseBody
	public String getAssigneeByIds(String assigneeIds) {
		List<SysUser> sysUserList = this.sysUserMapper.selectSysUserByIds(assigneeIds);
		return Ajax.responseString(CST.RES_SECCESS, null, sysUserList);
	}

	/**
	 * 
	 * insertOrUpdate:新增/修改工单.
	 * 
	 * @author wangyb
	 * @param file
	 * @param json
	 * @param session
	 * @param request
	 * @return
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "新增/修改工单")
	@RequestMapping(value = "/insertOrUpdate")
	@ResponseBody
	public WorkOrderInfo insertOrUpdate(@RequestParam(value = "file", required = false) MultipartFile file, String json,
			HttpSession session, HttpServletRequest request) throws Exception {
		SysUser user = (SysUser) session.getAttribute("sysUserInfo");
		WorkOrderInfo woInfo = JSON.parseObject(json, WorkOrderInfo.class);
		if (StringUtils.isEmpty(woInfo.getId())) {
			// 封装当前用户信息
			this.workOrderService.insert(woInfo,user,file);
		}
		else {
			this.workOrderService.update(woInfo,user,file);
		}
		return woInfo;
	}
	
	/**
	 * 
	 * download:下载文件
	 * 
	 * @author wangyb
	 * @param attchId
	 * @param id
	 * @param response
	 * @param request
	 * @return
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName="审计报告类",option="下载报告")
	@RequestMapping(value="/download", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String download(String attchId, String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			if (attchId == null) {
				List<SysAttachment> list = sysAttachmentMapper.selectAttachByTableId(id);
				attchId=list.get(0).getId();
			}
			attchmentService.fileDown(attchId, request, response);
			return Ajax.responseString(CST.RES_SECCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}


	/**
	 * 
	 * getDuration:获得当前业务阶段用时.
	 * 
	 * @author wangyb
	 * @param actId
	 * @param procId
	 * @return
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "获取当前业务阶段用时")
	@RequestMapping(value = "/getDuration")
	@ResponseBody
	public String getDuration(String actId, String procId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("actId", actId);
		map.put("procId", procId);
		List<String> startTime = workOrderInfoMapper.getStartTimeByActIdAndProcId(map);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (startTime.size() > 0) {
			Date startDate = sdf.parse(startTime.get(0));
			Date date = new Date();
			long ms = date.getTime() - startDate.getTime();
			String duration = DateUtil.formatTime(ms);
			return Ajax.responseString(CST.RES_SECCESS, null, duration);
		} else {
			return Ajax.responseString(CST.RES_SECCESS, null, "无");
		}
	}

	/**
	 * 
	 * selectPageList:分页查询
	 * 
	 * @author wangyb
	 * @param pageType
	 * @param page
	 * @param rows
	 * @param session
	 * @return
	 * @throws Exception 
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "分页查询")
	@RequestMapping(value = "/selectPageList")
	@ResponseBody
	public PageJqGrid<WorkOrderInfo> selectPageList(String pageType, WorkOrderInfo woInfo, int page, int rows, HttpSession session) throws Exception {
			SysUser user = (SysUser) session.getAttribute("sysUserInfo");
			Map<String, String> paramMap = new HashMap<String, String>();
			if (woInfo!=null) {
				// 查询条件
				paramMap.put("workOrderName", woInfo.getWorkOrderName());
			}
			if (!StringUtils.isEmpty(pageType)) {
				// 页面类型
				paramMap.put("pageType", pageType);
				paramMap.put("userId", user.getUserId());
				// 备用字段
				paramMap.put("orgId", user.getOrgId());
			}
			Page<WorkOrderInfo> pageList = this.workOrderService.selectPageList(paramMap, page, rows);
			PageJqGrid<WorkOrderInfo> pageJqGrid = new PageJqGrid<WorkOrderInfo>(pageList);
			
			return pageJqGrid;
	}

	/**
	 * 
	 * getWorkOrderData:获得工单数据
	 * 
	 * @author wangyb
	 * @param json
	 * @return
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "获取工单数据")
	@RequestMapping(value = "/getWorkOrderData")
	@ResponseBody
	public String getWorkOrderData(String json) {
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!"".equals(json) && null != json) {
			paramMap = (Map<String, String>) JsonUtils.json2Java(json);
		}
		// 执行检索
		List<WorkOrderInfo> list = workOrderInfoMapper.selectAll(paramMap);
		// 将list转换成map
		List<Map<String, String>> listN = new ArrayList<Map<String, String>>();
		for (WorkOrderInfo workOrderInfo : list) {
			String org = "";
			if (workOrderInfo.getOrgId() != null && !"".equals(workOrderInfo.getOrgId())) {
				for (String orgId : workOrderInfo.getOrgId().split("\\|")) {
					if (orgId != null && !"".equals(orgId)) {
						List<Map<String, String>> orgList = workOrderInfoMapper.selectOrgNameByOrgId(orgId);
						if (orgList.size() > 0 && orgList != null) {
							org += orgList.get(0).get("NAME") + "|";
						}
					}
				}
			}

			workOrderInfo.setOrg("".equals(org) ? "无" : org.substring(0, org.length() - 1));
			listN.add(new BeanMap(workOrderInfo));
		}
		return Ajax.responseString(CST.RES_SECCESS, listN);
	}

	/**
	 * 
	 * deleteInfo:删除工单数据
	 * 
	 * @author wangyb
	 * @param
	 * @return
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "删除工单数据")
	@RequestMapping(value = "/deleteInfo")
	@ResponseBody
	public String deleteInfo(String id) {
		try {
			workOrderService.deleteInfo(id);
			return Ajax.responseString(CST.RES_SECCESS, "工单删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_EXCEPTION, "工单删除失败！");
		}
	}

	/**
	 * 
	 * getFileCount:获得待办事项数量
	 * 
	 * @author wangyb
	 * @param json
	 * @return
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "获得待办事项数量")
	@RequestMapping(value = "/getFileCount")
	@ResponseBody
	public String getFileCount(String json) {
		Map<String, String> paramMap = new HashMap<String, String>();
		if (!"".equals(json) && null != json) {
			paramMap = (Map<String, String>) JsonUtils.json2Java(json);
		}
		List<SysAttachment> attachs = sysAttachmentMapper.selectAttachByTableId(paramMap.get("workOrderId"));
		return Ajax.responseString(CST.RES_SECCESS, attachs.size());
	}
	
	/**
	 * 
	 * getFileName:获得文件名称
	 * 
	 * @author wangyb
	 * @param id
	 * @return
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "获得文件名称")
	@RequestMapping(value = "/getFileName")
	@ResponseBody
	public String getFileName(String id) {
		List<SysAttachment> files = this.sysAttachmentMapper.selectAttachByTableId(id+"_send");
		return files.get(0).getRealName();
	}

	/**
	 * 
	 * proc:分页查询（流程历史表）
	 * 
	 * @author wangyb
	 * @param procId
	 * @return
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "分页查询（流程历史表）")
	@RequestMapping(value = "/proc")
	@ResponseBody
	public List<Map<String, String>> proc(String procId) {
		List<Map<String, String>> list = this.workOrderService.selectHiActinsts(procId);
		return list;
	}

	/**
	 * 
	 * startSendFlow:发起工单申请.
	 * 
	 * @author wangyb
	 * @param id
	 * @param session
	 * @return procId
	 * @since JDK 1.8
	 */
	@ArchivesLog(actionName = "工单管理类", option = "发起工单申请")
	@RequestMapping(value = "/startSendFlow")
	@ResponseBody
	public String startSendFlow(String id, HttpSession session) throws Exception {
		SysUser user = (SysUser) session.getAttribute("sysUserInfo");
		// 通过业务名称查找流程实例ID
		FlowResource flowResource = flowResourceMapper
				.selectByAppClassName(CST.WORKORDER_FLOW_WORKORDERSEND);
		// 封装多实例业务数据
		List<WorkOrderInfo> workOrderInfos = new ArrayList<WorkOrderInfo>();
		WorkOrderInfo info = workOrderInfoMapper.selectByPrimaryKey(id);
		String[] assignIds = info.getAssigneeId().split("\\|");
		for (String assignId : assignIds) {
			WorkOrderInfo infoN = new WorkOrderInfo();
			infoN.setAssigneeId(assignId);
			infoN.setId(id);
			workOrderInfos.add(infoN);
		}
		// 生成流程表（act_ru_execution）中的业务ID
		String localProcessId = UUIDUtil.createUUID();
		// 工作流相关的业务参数
		LocalProcessVariables localProcessVariables = new LocalProcessVariables();
		localProcessVariables.setId(id);
		localProcessVariables.setClassPath(CST.WORKORDER_IMPL_PATH);
		localProcessVariables.setClassName(CST.WORKORDER_FLOW_WORKORDERSEND);
		localProcessVariables.setCreatorId(user.getUserId());
		localProcessVariables.setCreatorName(user.getUserName());
		localProcessVariables.setLocalProcessId(localProcessId);
		localProcessVariables.setProcessName("工单整改");
		localProcessVariables.setBusinessInfo(workOrderInfos);
		// 启动工作流
		return processManager.startWorkFlow(localProcessId, flowResource.getProcessid(),
				localProcessVariables);
	}

	/**
	 * 
	 * approvalTask:完成任务. <br/>
	 * 
	 * @author wangyb
	 * @param file
	 * @param json
	 * @param session
	 * @param request
	 * @return
	 * @since JDK 1.6
	 */
	@ArchivesLog(actionName = "工单管理类", option = "完成任务")
	@RequestMapping(value = "/approvalTask", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String approvalTask(@RequestParam(value = "file", required = false) MultipartFile file, String json,
			HttpSession session, HttpServletRequest request) {
		try {
			LocalTaskVariables localTaskVariables = JSON.parseObject(json, LocalTaskVariables.class);
			SysUser user = (SysUser) session.getAttribute("sysUserInfo");
			localTaskVariables.setUserId(user.getUserId());
			localTaskVariables.setUserName(user.getUserName());
			// 如果新增信息包含文件，则上传文件
			if (file != null) {
				String attachId = sysAttachmentService.uploadFile(localTaskVariables.getWorkOrderId()+"_handle",
						CST.WORKORDER_TABLE_NAME, file.getOriginalFilename(), file.getInputStream(), user);
				localTaskVariables.setUrl(attachId);
				localTaskVariables.setFileName(file.getOriginalFilename());
			}
			Map<String, String> map = new HashMap<String, String>();
			// 测试
			map.put("userId", user.getUserId());
			map.put("procId", localTaskVariables.getProcId());
			String taskId = workOrderInfoMapper.selectTaskIdByProcIdAndAssign(map);
			// 1.工单整改（待办人员），2.工单退回（待办人员），3.工单归档（审核人员），0.工单退回（审核人员）
			String msg = "工单处理成功！";
			if (CST.WORKORDER_ASSIGNEE_COMPLETE.equals(localTaskVariables.getApproval())) {
				processManager.completeTask(taskId, localTaskVariables);
			} else {// 回退到上一节点
				boolean result = processManager.rollBack(taskId, localTaskVariables);
				msg = "工单退回成功！";
				if (!result) {
					return Ajax.responseString(CST.RES_EXCEPTION, "不能退回到开始任务!");
				}
			}
			return Ajax.responseString(CST.RES_SECCESS, msg);
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_EXCEPTION, "工单处理异常，请重新添加！");
		}
	}
}
