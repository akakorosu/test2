package com.bonc.dpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.DmDevNovelInfo;
import com.bonc.dpi.dao.mapper.DmDevNovelInfoMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class DmDevNovelInfoService {

	@Resource
	private DmDevNovelInfoMapper dmDevNovelInfoMapper;
	
	
	public List<DmDevNovelInfo> selectList(DmDevNovelInfo vo) {
		List<DmDevNovelInfo> list = this.dmDevNovelInfoMapper.selectList(vo);
		return list;
	}
}
