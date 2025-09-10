package com.bonc.dpi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.UnidentifiedUa;
import com.bonc.dpi.dao.mapper.UnidentifiedUaMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class UnidentifiedUaService {
	@Autowired
	private UnidentifiedUaMapper unidentifiedUaMapper;

	public Page<UnidentifiedUa> selectList(UnidentifiedUa vo, Integer page, Integer row) throws Exception{
		PageHelper.startPage(page, row);
		
		Page<UnidentifiedUa> pageList = (Page<UnidentifiedUa>) unidentifiedUaMapper.selectList(vo);
		return pageList;
	}
}
