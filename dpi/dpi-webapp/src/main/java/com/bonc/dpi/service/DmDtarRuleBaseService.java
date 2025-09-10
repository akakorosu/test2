package com.bonc.dpi.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.common.utils.DateUtil;
import com.bonc.dpi.dao.entity.DmDtarRuleBase;
import com.bonc.dpi.dao.entity.DmDtarRuleInfo;
import com.bonc.dpi.dao.mapper.DmDtarRuleBaseMapper;
import com.bonc.login.action.LoginAction;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class DmDtarRuleBaseService {

	@Resource
	private DmDtarRuleBaseMapper dmDtarRuleBaseMapper;
	@Resource
	private DmDtarRuleInfoService dmDtarRuleInfoService;
	
	public Page<DmDtarRuleBase> selectPageList(DmDtarRuleBase dmDtarRuleBase, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<DmDtarRuleBase> pageList = (Page<DmDtarRuleBase>) this.dmDtarRuleBaseMapper.selectList(dmDtarRuleBase);
		for (DmDtarRuleBase temp : pageList) {
			DmDtarRuleInfo dmDtarRuleInfo = new DmDtarRuleInfo();
			dmDtarRuleInfo.setTarBaseId(temp.getId());
			List<DmDtarRuleInfo> list = this.dmDtarRuleInfoService.selectList(dmDtarRuleInfo );
			temp.setChildren(list);
		}
		return pageList;
	}


	public void insert(DmDtarRuleBase dmDtarRuleBase) {
		dmDtarRuleBase.setId(UUID.randomUUID().toString());
		dmDtarRuleBase.setCreateTime(DateUtil.formatDate(System.currentTimeMillis()));
		dmDtarRuleBase.setCreateUser(LoginAction.getLoginUser().getUserId());
		this.dmDtarRuleBaseMapper.insert(dmDtarRuleBase);
	}


	public DmDtarRuleBase selectById(String id) {
		DmDtarRuleBase dmDtarRuleBase = this.dmDtarRuleBaseMapper.selectById(id);
		return dmDtarRuleBase;
	}


	public void update(DmDtarRuleBase dmDtarRuleBase) {
		this.dmDtarRuleBaseMapper.update(dmDtarRuleBase);
	}


	public void deleteById(String id) {
		// 删除dm_d_tar_rule_base
		this.dmDtarRuleBaseMapper.deleteById(id);
		// 删除dm_d_tar_rule_info
		this.dmDtarRuleInfoService.deleteByTarBaseId(id);
	}


	public Boolean selectCheck(DmDtarRuleBase dmDtarRuleBase) {
		List<DmDtarRuleBase> list = this.dmDtarRuleBaseMapper.selectList(dmDtarRuleBase);
		if(list.size() == 0) {
			return true;
		}
		return false;
	}


	public List<DmDtarRuleBase> selectList(DmDtarRuleBase dmDtarRuleBase) {
		List<DmDtarRuleBase> list = this.dmDtarRuleBaseMapper.selectList(dmDtarRuleBase);
		return list;
	}
}
