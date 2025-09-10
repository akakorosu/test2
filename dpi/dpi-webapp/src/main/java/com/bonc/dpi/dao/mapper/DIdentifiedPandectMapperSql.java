package com.bonc.dpi.dao.mapper;

import com.bonc.dpi.dao.entity.DmDContentInfo;
import com.bonc.dpi.dao.entity.DpiCommon;

/**
 * 知识库监控
 * 数据识别情况总览
 * dm_d_identified_pandect
 * @author BONC-XUXL
 *
 */
public class DIdentifiedPandectMapperSql {
	
//	private static String currentMonth = DpiUtils.getCurrentMonth();//201811,当前月
	
	
	/**
	 * 应用记录数识别，应用流量识别
	 * @param id
	 * @return
	 */
	public String selectDIdentifiedPandect(String dateId) {
		
		String sql = this.getSql_DIdentifiedPandect() + " and t.date_id ='"+dateId+"' group by t.date_id";
		return sql;
	}
//	/**
//	 * 基础sql语句(应用记录数识别，应用流量识别)
//	 * @return
//	 */+ "	(sum(number_total))  yyjl_sbl_fm," 
//	private String getSql_DIdentifiedPandect() {
//		String sql = "select "
//				+ "	t.date_id dateId," 
//				+ "	sum(case when identified_type='1' then number_total end) yyjl_sbl_fz," 
//				+ "	(sum(number_total)-sum(case when identified_type='0'  then number_ip_total end))  yyjl_sbl_fm," 
//				+ "	sum(case when identified_type='1' then number_ip_total end) yyjl_ipSbl_yes,"
//				+ "	sum(case when identified_type='1' then number_unip_total end)  yyjl_ipSbl_no,"
//				+ "	sum(case when identified_type='1' then flow_total end)  yyll_sbl_fz," 
//				+ "	(sum(flow_total)-sum(case when identified_type='0'  then flow_ip_total end))  yyll_sbl_fm," 
//				+ "	sum(case when identified_type='1' then flow_ip_total end)  yyll_ipSbl_yes," 
//				+ "	sum(case when identified_type='1' then flow_unip_total end)  yyll_ipSbl_no" 
//				+ " from "
//				+ " dm_d_identified_pandect t"
//				+ " where 1=1 ";
//		return sql;
//	}
	 
	 
		/**
		 * 基础sql语句(应用记录数识别，应用流量识别)
		 * @return
		 */
		private String getSql_DIdentifiedPandect() {
			String sql = "select "
					+ "	t.date_id dateId," 
					+ "	sum(case when identified_type='1' then number_total end) yyjl_sbl_fz," 
					+ "	(sum(number_total)-sum(case when identified_type='0'  then number_ip_total end))  yyjl_sbl_noip_fm," 
					+ "	(sum(number_total))  yyjl_sbl_fm," 
					+ "	sum(case when identified_type='1' then flow_total end)  yyll_sbl_fz," 
					+ "	(sum(flow_total)-sum(case when identified_type='0'  then flow_ip_total end))  yyll_sbl_noip_fm," 
					+ "	(sum(flow_total))  yyll_sbl_fm" 
					+ " from "
					+ " dm_d_identified_pandect t"
					+ " where 1=1 ";
			return sql;
		}
	
	
	/**
	 * 规则库---统计总数
	 * @param dc
	 * @return
	 */
	public String selectGzkData_Total(DpiCommon dc){
//		String dataId = dc.getName1();//时间
		String sql = "";
		sql = "select label_type name1,status name2, sum(t.total_nums) name3 from dm_d_ev_rule_base t  group by label_type,status";
		return sql;
	} 
	
	
	
	/**
	 * 规则库---标签域名分布
	 * DimIaDomainRule
	 * @param type
	 * 
	 * 
	 * select t3.label_name ,t0.total_nums from dm_d_ev_rule_base t0 left join (select t1.label_code ,t2.label_name, count(t1.label_code) total_nums from dm_d_ev_rule_base t1 left join dim_ia_product_label t2 on t1.label_code = t2.label_type where  t1.label_type='1' and  t1.status = '2'  group by t1.label_code ,t2.label_name order by t1.label_code) t3  on t0.label_code = t3.label_code  where  t0.label_type='1' and  t0.status = '1';
	 * @return
	 */
	public String selectDomainRule(DpiCommon dc){
		
//		String dateId = dc.getName1();
//		String status = dc.getName2();
//		String sql = "select t3.label_name name1,t0.total_nums name2 from dm_d_ev_rule_base t0 left join (select t1.label_code ,t2.label_name, count(t1.label_code) total_nums from dm_d_ev_rule_base t1 left join dim_ia_product_label t2 on t1.label_code = t2.label_type where  t1.label_type='1' and  t1.status = '"+status+"'  group by t1.label_code ,t2.label_name order by t1.label_code) t3  on t0.label_code = t3.label_code  where  t0.label_type='1' and  t0.status = '"+status+"'";		
		String sql = "select t0.status name1, t3.label_name name2,t0.total_nums name3 from dm_d_ev_rule_base t0 left join (select t1.label_code ,t2.label_name, count(t1.label_code) total_nums from dm_d_ev_rule_base t1 left join dim_ia_product_label t2 on t1.label_code = t2.label_type where  t1.label_type='1'  group by t1.label_code ,t2.label_name order by t1.label_code) t3  on t0.label_code = t3.label_code  where  t0.label_type='1' order by  t0.status,t3.label_code ";

		return sql;
	}
	
	
	/**
	 * 规则库--->标签产品分布
	 * ProductInfo
	 * @param type
	 * @return
	 */
	public String selectProductInfo(DpiCommon dc){
		
//		String dateId = dc.getName1();
//		String status = dc.getName2();
//		String sql = "select t3.label_name name1,t0.total_nums name2 from dm_d_ev_rule_base t0 left join (select t1.label_code ,t2.label_name, count(t1.label_code) total_nums from dm_d_ev_rule_base t1 left join dim_ia_product_label t2 on t1.label_code = t2.label_type where  t1.label_type='2' and  t1.status = '"+status+"'  group by t1.label_code ,t2.label_name order by t1.label_code) t3  on t0.label_code = t3.label_code  where  t0.label_type='2' and  t0.status = '"+status+"'";
		String sql = "select t0.status name1, t3.label_name name2,t0.total_nums name3 from dm_d_ev_rule_base t0 left join (select t1.label_code ,t2.label_name, count(t1.label_code) total_nums from dm_d_ev_rule_base t1 left join dim_ia_product_label t2 on t1.label_code = t2.label_type where  t1.label_type='2'  group by t1.label_code ,t2.label_name order by t1.label_code) t3  on t0.label_code = t3.label_code  where  t0.label_type='2' order by  t0.status,t3.label_code ";
		
		return sql;
	}
	
	
	/**
	 * echarts_label
	 * 规则库--->标签层级分布
	 * @param voParam
	 * @return
	 select 'hy' as name1, count(distinct t1.label_code1) name2 ,count(distinct t1.label_code2) name3 ,count(distinct t1.label_code) name4 from dim_ia_product_label t1 where t1.op_time like '%201811%' union all 
	 select 'xz' as name1, count(distinct t1.label_code1) name2 ,count(distinct t1.label_code2) name3 ,count(distinct t1.label_code) name4 from dim_ia_product_label t1;
	 */
	public String selectLabelNum(DpiCommon dc){
		
		String currentMonth = dc.getName9();//201811,当前月
		
		String sql = "";//第一行是新增,第二行是活跃
		String sql_xz = " select 'xz' as name1, count(distinct t1.label_code1) name2 ,count(distinct t1.label_code2) name3 ,count(distinct t1.label_code) name4 from dim_ia_product_label t1 where t1.op_time like '%"+currentMonth+"%' union all ";
		String sql_hy = " select 'hy' as name1, count(distinct t1.label_code1) name2 ,count(distinct t1.label_code2) name3 ,count(distinct t1.label_code) name4 from dim_ia_product_label t1 ";
		sql = sql_xz + sql_hy;
		return sql;
	}
	
	/**
	 * action,content,directory
	 * 规则库--->深度解析规则统计
	 * @return
	 */
	public String selectDeepRule(DpiCommon dc){
		String sql = "select t.status name1, t.total_nums name2 from dm_d_ev_rule_base t where t.label_type = '3' order by t.status, t.label_code ";
		return sql;
	}
	
	
	/**
	 * 内容库--->
	 * @param id
	 * @return
	 */
	public String selectDmDContentInfo(DmDContentInfo vo) {
		String sql = this.getSql_DmDContentInfo() + " and t.date_id=#{dateId}";
		return sql;
	}

	/**
	 * 内容库--->基础sql语句
	 * @return
	 */
	private String getSql_DmDContentInfo() {
		String sql = "select "
				+ "	t.date_id dateId,"
				+ "	t.content_type contentType," 
				+ "	t.recongnition recongnition," 
				+ "	t.active_prod activeProd," 
				+ "	t.active_label activeLabel," 
				+ "	t.active_content activeContent," 
				+ "	t.add_content addContent,"
				+ "	t.use_cnt useCnt,"
				+ "	t.error_cnt errorCnt"
				+ " from "
				+ " dm_d_content_info t"
				+ " where 1=1 ";
		return sql;
	}
	
	
	/**
	 * 分词库--->(敏感词,分词库,分类词库,停用词)
	 * @return
	 */
	public String selectDmDLexiconInfo(String dateId) {
		String sql = "select "
				+ "	t.active_sensitive activeSensitive," 
				+ "	t.add_sensitive addSensitive," 
				+ "	t.active_words activeWords," 
				+ "	t.add_words addWords," 
				+ "	t.active_words_type activeWordsType," 
				+ "	t.add_words_type addWordsType,"
				+ "	t.active_stop activeStop," 
				+ "	t.add_stop addStop"				
				+ " from "
				+ " dm_d_lexicon_info t ";
		return sql;
	}
}
