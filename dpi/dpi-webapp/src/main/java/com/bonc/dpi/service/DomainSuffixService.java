package com.bonc.dpi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bonc.dpi.dao.entity.DomainSuffix;
import com.bonc.dpi.dao.mapper.DomainSuffixMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class DomainSuffixService {
	
	@Autowired
	private DomainSuffixMapper mapper;
	
	public Page<DomainSuffix> selectList(DomainSuffix df, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<DomainSuffix> pageList = (Page<DomainSuffix>) this.mapper.selectList(df);
		return pageList;
	}
	
	public void insert(DomainSuffix df){
		mapper.insert(df);
	}
	
	public DomainSuffix selectById(DomainSuffix df){
		return mapper.selectById(df);
	}
	
	public void delete(DomainSuffix df){
		mapper.delete(df);
	}
	
	public void update(DomainSuffix df){
		mapper.update(df);
	}
	
}
