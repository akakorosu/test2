package com.bonc.dpi.dao.mapper;

import java.util.List;

import com.bonc.dpi.dao.entity.DmDtarRuleInfo;


public interface DmDtarRuleInfoMapper {
	
	List<DmDtarRuleInfo> selectList(DmDtarRuleInfo dmDtarRuleInfo);
	
	Boolean insert(DmDtarRuleInfo dmDtarRuleInfo);
	
	Boolean deleteById(String id);
	
	/*SysOrg selectSysOrgById(String orgId);
	
	Boolean updateSysOrg(SysOrg sysOrg);
	
	Boolean deleteSysOrgByTreeCode(String treeCode);
	
	Integer selectCheck(SysOrg sysOrg);*/
}
