package com.bonc.dpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.DmDevVideoInfo;
import com.bonc.dpi.dao.mapper.DmDevVideoInfoMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class DmDevVideoInfoService {

	@Resource
	private DmDevVideoInfoMapper dmDevVideoInfoMapper;
	
	
	public List<DmDevVideoInfo> selectList(DmDevVideoInfo vo) {
		List<DmDevVideoInfo> list = this.dmDevVideoInfoMapper.selectList(vo);
		return list;
	}
}
