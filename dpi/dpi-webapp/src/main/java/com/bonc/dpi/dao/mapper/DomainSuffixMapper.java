package com.bonc.dpi.dao.mapper;

import java.util.List;

import com.bonc.dpi.dao.entity.DomainSuffix;

public interface DomainSuffixMapper {
	
	List<DomainSuffix> selectList (DomainSuffix df);
	
	void insert(DomainSuffix df);
	
	DomainSuffix selectById(DomainSuffix df);
	
	void delete(DomainSuffix df);
	
	void update(DomainSuffix df);
}
