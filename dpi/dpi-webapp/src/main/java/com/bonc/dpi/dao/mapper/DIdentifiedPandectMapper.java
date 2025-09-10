package com.bonc.dpi.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.bonc.dpi.dao.entity.DIdentifiedPandect;
import com.bonc.dpi.dao.entity.DmDContentInfo;
import com.bonc.dpi.dao.entity.DmDLexiconInfo;
import com.bonc.dpi.dao.entity.DpiCommon;

/**
 * 知识库监控
 * 数据识别情况总览
 * dm_d_identified_pandect
 * @author BONC-XUXL
 *
 */
public interface DIdentifiedPandectMapper {

	/**
	 * 数据识别情况总览
	 * @param id
	 * @return
	 */
	@SelectProvider(type = DIdentifiedPandectMapperSql.class, method = "selectDIdentifiedPandect")
	DIdentifiedPandect selectDIdentifiedPandect(String dateId);
	
	/**
	 * 规则库--->新增，活跃总数
	 * @param type(活跃,新增)
	 * @return
	 */
	@SelectProvider(type = DIdentifiedPandectMapperSql.class, method = "selectGzkData_Total")
	List<DpiCommon> selectGzkData_Total(DpiCommon dc);
	
	/**
	 * echarts_rule
	 * 标签域名分布
	 * @param type
	 * @return
	 */
	@SelectProvider(type = DIdentifiedPandectMapperSql.class, method = "selectDomainRule")
	List<DpiCommon> selectDomainRule(DpiCommon dc);
	
	/**
	 * echarts_rule
	 * 标签产品分布
	 * @param type
	 * @return
	 */
	@SelectProvider(type = DIdentifiedPandectMapperSql.class, method = "selectProductInfo")
	List<DpiCommon> selectProductInfo(DpiCommon dc);
	
	/**
	 * echarts_label
	 * 标签层级分布
	 * @param id
	 * @return
	 */
	@SelectProvider(type = DIdentifiedPandectMapperSql.class, method = "selectLabelNum")
	List<DpiCommon> selectLabelNum(DpiCommon dc);
	
	/**
	 * echarts_rule
	 * 深度规则分布
	 * @param type
	 * @return
	 */
	@SelectProvider(type = DIdentifiedPandectMapperSql.class, method = "selectDeepRule")
	List<DpiCommon> selectDeepRule(DpiCommon dc);
	
	
	/**
	 * 根据账期查找(内容库识别情况)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = DIdentifiedPandectMapperSql.class, method = "selectDmDContentInfo")
	List<DmDContentInfo> selectDmDContentInfo(DmDContentInfo vo);
	
	/**
	 * 分词库--->(敏感词,分词库,分类词库)
	 * @param id
	 * @return
	 */
	@SelectProvider(type = DIdentifiedPandectMapperSql.class, method = "selectDmDLexiconInfo")
	DmDLexiconInfo selectDmDLexiconInfo(String dateId);

}
