package com.bonc.dpi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bonc.dpi.dao.entity.DIdentifiedPandect;
import com.bonc.dpi.dao.entity.DmDContentInfo;
import com.bonc.dpi.dao.entity.DmDLexiconInfo;
import com.bonc.dpi.dao.entity.DpiCommon;
import com.bonc.dpi.dao.mapper.DIdentifiedPandectMapper;

/**
 * 知识库监控
 * 数据识别情况总览
 * dm_d_identified_pandect
 * @author BONC-XUXL
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DIdentifiedPandectService {

	@Resource
	private DIdentifiedPandectMapper mapper;
	
	/**
	 * 数据识别情况总览
	 * @param id
	 * @return
	 */
	public DIdentifiedPandect selectDIdentifiedPandect(String dateId){
		DIdentifiedPandect dp = mapper.selectDIdentifiedPandect(dateId);
		Long zero = (long) 0.0001;
		//如果没有数据则初始化0
		if(dp==null){
			dp = new DIdentifiedPandect();
			dp.setYyjlSblFm(zero);
			dp.setYyjlSblFz(zero);
			dp.setYyjlSblNoipFm(zero);
			
			dp.setYyllSblFm(zero);
			dp.setYyllSblFz(zero);
			dp.setYyllSblNoipFm(zero);
		}
		return dp;
	}
	
	
	/**
	 * 规则库--->新增，活跃总数
	 * @param type(活跃,新增)
	 * @return
	 */
	public List<DpiCommon> selectGzkData_Total(String dateId){
		
		DpiCommon dcParam = new DpiCommon();
		dcParam.setName1(dateId);//时间
		return mapper.selectGzkData_Total(dcParam);
	}
	
	/**
	 * echarts_domain
	 * 标签域名分布
	 * @param type
	 * @return
	 */
	public List<DpiCommon> selectDomainRule(DpiCommon dc){
		return mapper.selectDomainRule(dc);
	}
	
	/**
	 * echarts_product
	 * 深度规则分布
	 * @param type
	 * @return
	 */
	public List<DpiCommon> selectProductInfo(DpiCommon dc){
		return mapper.selectProductInfo(dc);
	}
	
	/**
	 * echarts_label
	 * @return
	 */
	public List<DpiCommon> selectLabelNum(DpiCommon dc){
		return mapper.selectLabelNum(dc);
	}
	
	/**
	 * echarts_label
	 * 深度规则分布
	 * @param vo
	 * @return
	 */
	public List<DpiCommon> selectDeepRule(DpiCommon dc){
		return mapper.selectDeepRule(dc);
	}
	
	/**
	 * 内容库识别情况
	 * @param monthId 账期
	 * @param contentType 类别
	 * @return
	 */
	public List<DmDContentInfo> selectDmDContentInfoList(DmDContentInfo vo){
		return mapper.selectDmDContentInfo(vo);
	}
	
	/**
	 * 分词库--->(敏感词,分词库,分类词库,停用词)
	 * @return
	 */
	public DmDLexiconInfo selectDmDLexiconInfo(String dateId){
		return mapper.selectDmDLexiconInfo(dateId);
	}
	
}
