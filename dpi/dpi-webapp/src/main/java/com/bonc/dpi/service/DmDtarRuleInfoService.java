package com.bonc.dpi.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.DmDtarRuleInfo;
import com.bonc.dpi.dao.mapper.DmDtarRuleInfoMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional(rollbackFor = Exception.class)
public class DmDtarRuleInfoService {

	@Resource
	private DmDtarRuleInfoMapper dmDtarRuleInfoMapper;
	
	
	public Page<DmDtarRuleInfo> selectPageList(DmDtarRuleInfo dmDtarRuleInfo, Integer page, Integer row) {
		PageHelper.startPage(page, row);
		Page<DmDtarRuleInfo> pageList = (Page<DmDtarRuleInfo>) this.dmDtarRuleInfoMapper.selectList(dmDtarRuleInfo);
		return pageList;
	}
	
	public void insert(List<DmDtarRuleInfo> list, String tarBaseId) {
		this.deleteByTarBaseId(tarBaseId);
		int order = list.size() - 1;
		for (int i = 0; i < list.size(); i ++) {
			DmDtarRuleInfo dmDtarRuleInfo = list.get(i);
			dmDtarRuleInfo.setTarBaseId(tarBaseId);
			dmDtarRuleInfo.setTarOrder(order + "");
			dmDtarRuleInfo.setId(UUID.randomUUID().toString());
			this.dmDtarRuleInfoMapper.insert(dmDtarRuleInfo);
			order --;
		}
	}
	
	public List<DmDtarRuleInfo> selectList(DmDtarRuleInfo dmDtarRuleInfo) {
		List<DmDtarRuleInfo> list = this.dmDtarRuleInfoMapper.selectList(dmDtarRuleInfo);
		return list;
	}
	
	public void deleteByTarBaseId(String tarBaseId) {
		DmDtarRuleInfo dmDtarRuleInfo = new DmDtarRuleInfo();
		dmDtarRuleInfo.setTarBaseId(tarBaseId);
		List<DmDtarRuleInfo> list = this.dmDtarRuleInfoMapper.selectList(dmDtarRuleInfo);
		for (DmDtarRuleInfo temp : list) {
			this.dmDtarRuleInfoMapper.deleteById(temp.getId());
		}
	}
}
