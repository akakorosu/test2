package com.bonc.system.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.bonc.common.cst.CST;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.config.Constant;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.system.dao.entity.SysOrg;
import com.bonc.system.dao.entity.SysUser;
import com.bonc.system.dao.mapper.SysOrgMapper;
import com.bonc.system.service.i.SysOrgServiceI;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysOrgService implements SysOrgServiceI {

	@Resource
	private SysOrgMapper sysOrgMapper;
	
	
	public Page<SysOrg> selectPageList(SysOrg sysOrg, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<SysOrg> pageList = (Page<SysOrg>) this.sysOrgMapper.selectList(sysOrg);
		return pageList;
	}
	
	public SysOrg selectSysOrgById(String orgId) {
		SysOrg sysOrg = this.sysOrgMapper.selectSysOrgById(orgId);
		return sysOrg;
	}
	
	public SysOrg insertSysOrg(SysOrg sysOrg) {
		String orgId = UUIDUtil.createUUID();
		sysOrg.setOrgId(orgId);
		if(StringUtils.isBlank(sysOrg.getPid())) {
			sysOrg.setTreeCode("/" + orgId);
		} else {
			SysOrg parent = this.selectSysOrgById(sysOrg.getPid());
			sysOrg.setTreeCode(parent.getTreeCode() + "/" + orgId);
		}
		Boolean bl = this.sysOrgMapper.insertSysOrg(sysOrg);
		return sysOrg;
	}
	
	public SysOrg updateSysOrg(SysOrg sysOrg) {
		Boolean bl = this.sysOrgMapper.updateSysOrg(sysOrg);
		return sysOrg;
	}
	
	public Boolean deleteSysOrgByTreeCode(String treeCode) {
		Boolean bl = this.sysOrgMapper.deleteSysOrgByTreeCode(treeCode);
		return bl;
	}

	public List<SysOrg> selectTree(String orgId) {
		SysOrg sysOrg = new SysOrg();
		if(StringUtils.isBlank(orgId)) {
			sysOrg.setOrgLevel("1");
		} else {
			sysOrg.setOrgId(orgId);
		}
		List<SysOrg> list = this.sysOrgMapper.selectList(sysOrg);
		for (SysOrg temp : list) {
			this.selectTreeChildren(temp);
		}
		return list;
	}
	
	private void selectTreeChildren(SysOrg sysOrg) {
		SysOrg parent = new SysOrg();
		parent.setPid(sysOrg.getOrgId());
		List<SysOrg> list = this.sysOrgMapper.selectList(parent);
		if(list.size() == 0) {
			return;
		} else {
			sysOrg.setChildren(list);
			for (SysOrg temp : list) {
				this.selectTreeChildren(temp);
			}
		}
	}
	
	/**
	 * 获取用户orgId
	 * @param session
	 * @return
	 */
	public String getUserOrgId(HttpSession session) {
		String orgId = Constant.ORG_CODE_PROVINCE;
		try {
			SysUser user = (SysUser) session.getAttribute(CST.SESSION_SYS_USER_INFO);
			if(user != null) {
				String userOrgId = user.getOrgId();
				if(!Constant.ORG_CODE_PROVINCE.equals(userOrgId)) {
					if(userOrgId.length() == Constant.ORG_LENGTH_CITY) {
						orgId = userOrgId;
					}else {
						orgId = userOrgId.substring(0, Constant.ORG_LENGTH_CITY);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orgId;
	}
	
	/**
	 * 获取city下拉框
	 * @param orgId
	 * @return
	 */
	public List<SysOrg> getCityOption(String orgId) {
		String paramOrgId = orgId;
		if(Constant.ORG_CODE_PROVINCE.equals(orgId)) {
			paramOrgId = "";
		}
		SysOrg orgParam = new SysOrg();
		orgParam.setOrgId(paramOrgId);
		List<SysOrg> orgs = this.sysOrgMapper.getCityOption(orgParam);
		
		List<SysOrg> results = new ArrayList<>();
		// 如果用户是999，增加一条辽宁省数据
		if(Constant.ORG_CODE_PROVINCE.equals(orgId)) {
			SysOrg orgProv = new SysOrg();
			orgProv.setOrgId(Constant.ORG_CODE_PROVINCE);
			orgProv.setName(Constant.ORG_NAME_PROVINCE);
			results.add(orgProv);
		}
		results.addAll(orgs);
		
		return results;
	}
	
}
