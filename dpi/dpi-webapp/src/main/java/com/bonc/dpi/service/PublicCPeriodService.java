package com.bonc.dpi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.PublicCPeriod;
import com.bonc.dpi.dao.mapper.PublicCPeriodMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class PublicCPeriodService {
	@Autowired
	private PublicCPeriodMapper publicCPeriodMapper;
	
	public String selectDateId(String functionId) {
		PublicCPeriod pcp = this.publicCPeriodMapper.selectByFunctionId(functionId);
		if(pcp != null) {
			return pcp.getDateId();
		}
		return null;
	}
}
