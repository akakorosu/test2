package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.bonc.dpi.dao.entity.PublicCPeriod;


public interface PublicCPeriodMapper {
	
	@SelectProvider(type = PublicCPeriodMapperSql.class, method = "selectList")
	List<PublicCPeriod> selectList();
	
	@SelectProvider(type = PublicCPeriodMapperSql.class, method = "selectByFunctionId")
	PublicCPeriod selectByFunctionId(String functionId);
}
