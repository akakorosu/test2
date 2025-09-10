package com.bonc.activiti.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.activiti.dao.entity.FlowResource;
import com.bonc.activiti.dao.mapper.FlowResourceMapper;
import com.bonc.common.page.Page;
import com.bonc.common.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
@Transactional(rollbackFor = Exception.class)
public class FlowManagerService {
	@Resource
	private FlowResourceMapper flowResourceMapper;
	
	public Boolean isFlowNotExists(FlowResource flow) throws Exception {
		int result = this.flowResourceMapper.isFlowNotExists(flow);
		if (result>0) {
			return false;
		}
		return true;
	}

	public Page<FlowResource> selectPageList(FlowResource flow, Integer page, Integer rows) throws Exception {
		PageHelper.startPage(page, rows);
		List<FlowResource> list = this.flowResourceMapper.selectListByCondition(flow);
		Page<FlowResource> pageBean = new Page<FlowResource>(new PageInfo<FlowResource>(list));
		return pageBean;
	}

	public FlowResource getFlowManagerForm(String appid) throws Exception {
		if(!StringUtils.isBlank(appid)) {
			FlowResource flowResource = this.flowResourceMapper.selectByPrimaryKey(appid);
			return flowResource;
		}
		return null;
	}

	public Boolean insert(FlowResource flow) throws Exception {
		flow.setAppid(UUIDUtil.createUUID());
	    return flowResourceMapper.insert(flow);
	}

	public Boolean update(FlowResource flow) throws Exception {
		return flowResourceMapper.updateByPrimaryKey(flow);
	}

	public Boolean delete(String appid) throws Exception {
		Boolean result = flowResourceMapper.deleteByPrimaryKey(appid);
		return result;
	}
}
