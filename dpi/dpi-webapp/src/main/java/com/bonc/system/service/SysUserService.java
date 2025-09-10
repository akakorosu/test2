package com.bonc.system.service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.common.utils.DateUtil;
import com.bonc.common.utils.MD5Util;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.OperationFlowExportReturn;
import com.bonc.dpi.dao.entity.OperationFlowImportReturn;
import com.bonc.dpi.service.i.OperationFlowServiceI;
import com.bonc.system.dao.entity.SysUser;
import com.bonc.system.dao.mapper.SysUserMapper;
import com.bonc.system.service.i.SysUserServiceI;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class SysUserService implements SysUserServiceI, OperationFlowServiceI {

	@Resource
	private SysUserMapper sysUserMapper;
	
	public SysUser selectListByLogin(String loginId) {
		SysUser po = new SysUser();
		po.setLoginId(loginId);
		List<SysUser> list = this.sysUserMapper.selectList(po);
		if(list.size() == 1) {
			return list.get(0);
		}
		return null;
	}
	
	public Page<SysUser> selectPageList(SysUser sysUser, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<SysUser> pageList = (Page<SysUser>) this.sysUserMapper.selectList(sysUser);
		return pageList;
	}
	
	public SysUser selectSysUserById(String id) {
		SysUser sysUser = this.sysUserMapper.selectSysUserById(id);
		return sysUser;
	}

	public SysUser insertSysUser(SysUser sysUser) throws NoSuchAlgorithmException {
		sysUser.setUserId(UUIDUtil.createUUID());
		sysUser.setCreateTime(DateUtil.formatDate(new Date().getTime()));
		sysUser.setPassword(MD5Util.getHash(sysUser.getPassword()).toLowerCase());
		this.sysUserMapper.insertSysUser(sysUser);
		return sysUser;
	}
	
	public SysUser updateSysUser(SysUser sysUser) {
		this.sysUserMapper.updateSysUser(sysUser);
		return sysUser;
	}
	
	public Boolean deleteSysUserById(String id) {
		Boolean bl = this.sysUserMapper.deleteSysUserById(id);
		return bl;
	}

	public Boolean selectCheck(SysUser sysUser) {
		Integer i = this.sysUserMapper.selectCheck(sysUser);
		if(i > 0) {
			return false;
		}
		return true;
	}

	@Override
	public OperationFlowImportReturn doImport(List<OperationFlow> list,String type) throws Exception {
		System.out.println(list.size());
		OperationFlowImportReturn ofb = new OperationFlowImportReturn();
		ofb.setInfo("导入成功：" + list.size());
		return ofb;
	}

	@Override
	public OperationFlowExportReturn doExport(OperationFlow o) throws Exception {
		SysUser su = (SysUser) o;
		List<SysUser> list = this.sysUserMapper.selectList(su);
		OperationFlowExportReturn ofer = new OperationFlowExportReturn();
		ofer.setList(list);
		ofer.setExcelName("sys_user.xlsx");
		ofer.setTxtName("sys_user.txt");
		return ofer;
	}
	/**
	 * 批量导出数据条数
	 */
	@Override
	public Integer exportDataNum(OperationFlow o,String type) throws Exception {
		DomainRule vo = (DomainRule) o;
//		Integer num = this.mapper.selectDataNum(vo);
		
		return 0;
	}
}
