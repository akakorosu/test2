package com.bonc.dpi.dao.mapper;

import java.util.List;

import com.bonc.dpi.dao.entity.DmDtarRuleBase;


public interface DmDtarRuleBaseMapper {
	
	List<DmDtarRuleBase> selectList(DmDtarRuleBase dmDtarRuleBase);
	
	Boolean insert(DmDtarRuleBase dmDtarRuleBase);
	
	DmDtarRuleBase selectById(String id);
	
	Boolean update(DmDtarRuleBase dmDtarRuleBase);
	
	Boolean deleteById(String id);
	
	/*SysOrg selectSysOrgById(String orgId);
	
	Boolean insertSysOrg(SysOrg sysOrg);
	
	Integer selectCheck(SysOrg sysOrg);*/
}
