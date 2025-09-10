package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import com.bonc.dpi.dao.entity.DimIaUserAgentRule;
import com.bonc.dpi.dao.entity.DomainFuzzyRule;
import com.bonc.dpi.dao.entity.OperationFlow;

public interface DomainFuzzyRuleMapper {
	
	Integer selectDataNum(DomainFuzzyRule df);
	
	List<DomainFuzzyRule> selectList(DomainFuzzyRule df);
	
	List<DomainFuzzyRule> selectListByPar (DomainFuzzyRule df);
	
	Boolean insert(DomainFuzzyRule df);
	
	Boolean insertUuid(DomainFuzzyRule df);
	
	int insertDomainRule(DomainFuzzyRule df);
	
	int checkData(DomainFuzzyRule df);
	
	int checkDomainRuleData(DomainFuzzyRule df);
	
	int checkDomainFuzzyRule(DomainFuzzyRule df);
	
	int updateUrlStu(DomainFuzzyRule df);
	
	DomainFuzzyRule selectById(DomainFuzzyRule df);
	
	Boolean delete(DomainFuzzyRule df);
	
	Boolean update(DomainFuzzyRule df);
	
	Integer check(DomainFuzzyRule bl);
	
	DomainFuzzyRule getLabelById(DomainFuzzyRule bl);
	
	DomainFuzzyRule getLabelByCode(DomainFuzzyRule bl);
	
	int checkProdIdAndProdName(DomainFuzzyRule bl);
	/**
	 * 批量导入
	 * @param list
	 * @return
	 */
    Boolean insertVoPl(@Param("list")List<OperationFlow> list,@Param("database_type")String database_type);
    
    DomainFuzzyRule selectVoByPrimaryKey(DomainFuzzyRule voParam);
    DomainFuzzyRule deleteVoByPrimaryKey(DomainFuzzyRule voParam);
}
