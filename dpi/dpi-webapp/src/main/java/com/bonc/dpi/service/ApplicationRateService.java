package com.bonc.dpi.service;

import java.util.Date;
import java.util.List;

import com.bonc.dpi.action.OperationFlowAction;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bonc.dpi.dao.entity.ApplicationRate;
import com.bonc.dpi.dao.mapper.ApplicationRateMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional(rollbackFor = Exception.class)
public class ApplicationRateService {
	@Autowired
	private ApplicationRateMapper applicationRateMapper;
	
	public List<ApplicationRate> getApplicationData(ApplicationRate vo) throws Exception{	
		return applicationRateMapper.selectApplicationData(vo);
	}
	public Page<ApplicationRate> selectList(ApplicationRate vo, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<ApplicationRate> pageList = (Page<ApplicationRate>) applicationRateMapper.selectList(vo);
		return pageList;
	}

	public List<ApplicationRate> getExportList(ApplicationRate  vo) {
		return applicationRateMapper.selectExportList(vo);
	}
}
