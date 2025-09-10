package com.bonc.dpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.DmDevGoodsInfo;
import com.bonc.dpi.dao.mapper.DmDevGoodsInfoMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class DmDevGoodsInfoService {

	@Resource
	private DmDevGoodsInfoMapper dmDevGoodsInfoMapper;
	
	
	public List<DmDevGoodsInfo> selectList(DmDevGoodsInfo vo) {
		List<DmDevGoodsInfo> list = this.dmDevGoodsInfoMapper.selectList(vo);
		return list;
	}
}
