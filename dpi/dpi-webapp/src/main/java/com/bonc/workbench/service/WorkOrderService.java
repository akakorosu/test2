package com.bonc.workbench.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bonc.workbench.bean.WorkOrderInfo;
import com.bonc.workbench.dao.mapper.WorkOrderInfoMapper;
import com.bonc.common.cst.CST;
import com.bonc.common.utils.DateUtil;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.system.dao.entity.SysUser;
import com.bonc.system.service.SysAttachmentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class WorkOrderService {
	
	@Resource
	private SysAttachmentService sysAttachmentService;
	
	@Resource
	private WorkOrderInfoMapper workOrderInfoMapper;

	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteInfo(String id) throws Exception {
		workOrderInfoMapper.deleteInfo(id);
	}

	public void insert(WorkOrderInfo woInfo, SysUser user, MultipartFile file) throws Exception {
		// 封装woInfo数据
		String id = UUIDUtil.createUUID();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		
		woInfo.setCreatorId(user.getUserId());
		woInfo.setCreatorName(user.getUserName());
		woInfo.setId(id);
		woInfo.setCreateTime(dateStr);
		
		// 如果新增信息包含文件，则上传文件
		if (file != null) {
			sysAttachmentService.uploadFile(woInfo.getId()+"_send", CST.WORKORDER_TABLE_NAME, file.getOriginalFilename(),
					file.getInputStream(), user);
		}
		if (StringUtils.isEmpty(woInfo.getWorkOrderName())) {
			woInfo.setWorkOrderName(CST.WORKORDER_DEFAULT_NAME);
		}
		woInfo.setStatus(CST.FLOW_STATE_LOG_UNSENDED);
		this.workOrderInfoMapper.insert(woInfo);
	}

	public void update(WorkOrderInfo woInfo, SysUser user, MultipartFile file) throws Exception {
		if (file != null) {
			sysAttachmentService.updateFile(woInfo.getId()+"_send", CST.WORKORDER_TABLE_NAME, file.getOriginalFilename(),
					file.getInputStream());
		}
		this.workOrderInfoMapper.update(woInfo);
	}

	public Page<WorkOrderInfo> selectPageList(Map<String, String> paramMap, int page, int rows) throws Exception {
		// 开始分页
		PageHelper.startPage(page, rows);
		List<WorkOrderInfo> list = new ArrayList<WorkOrderInfo>();
		if (CST.WORKORDER_SEND_PAGE.equals(paramMap.get("pageType"))) {
			list = workOrderInfoMapper.selectSendPageList(paramMap);
		} else if (CST.WORKORDER_HANDLE_PAGE.equals(paramMap.get("pageType"))) {
			list = workOrderInfoMapper.selectHandlePageList(paramMap);
		} else if (CST.WORKORDER_PARTICIPATE_PAGE.equals(paramMap.get("pageType"))) {
			list = workOrderInfoMapper.selectParticipatePageList(paramMap);
		} else {
			list = workOrderInfoMapper.selectPageList(paramMap);
		}
		
		for (WorkOrderInfo workOrderInfo : list) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startTime = workOrderInfo.getStartTime();
			if (!StringUtils.isEmpty(startTime)) {
				Date startDate = sdf.parse(startTime);
				Date date = new Date();
				long ms = date.getTime() - startDate.getTime();
				String duration = DateUtil.formatTime(ms);
				workOrderInfo.setDuration(duration);
			}
			else {
				workOrderInfo.setDuration("无");
			}
		}
		Page<WorkOrderInfo> pageList = (Page<WorkOrderInfo>) list;
		return pageList;
	}

	public List<Map<String, String>> selectHiActinsts(String procId) {
		// 执行检索
		List<Map<String, String>> list = workOrderInfoMapper.selectHiActinsts(procId);		
		return list;
	}
}
