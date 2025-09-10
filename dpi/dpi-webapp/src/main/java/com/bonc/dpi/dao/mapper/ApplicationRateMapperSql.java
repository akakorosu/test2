package com.bonc.dpi.dao.mapper;

import com.bonc.dpi.dao.entity.ApplicationRate;
import com.bonc.dpi.dao.entity.DmDContentInfo;
import com.bonc.dpi.dao.entity.DpiCommon;

public class ApplicationRateMapperSql {
	
	/**
	 * 基础sql语句(应用记录数识别，应用流量识别)
	 * 
	 * @return
	 */
	public String selectApplicationData(ApplicationRate vo) {
		String dateId=vo.getDateId();
		String sql = "select  t.hour_id hourId,"
				+" sum(case when identified_type='1' then number_total end) yyjlSblFz,"
				+" (sum(number_total)-sum(case when identified_type='0'  then number_ip_total end))  yyjlSblNoipFm,"
				+" (sum(number_total))  yyjlSblFm,"
				+" sum(case when identified_type='1' then flow_total end)  yyllSblFz,"
				+" (sum(flow_total)-sum(case when identified_type='0'  then flow_ip_total end))  yyllSblNoipFm,"
				+" (sum(flow_total))  yyllSblFm from   dm_d_identified_pandect t where t.date_id='"+dateId+"'  group by t.hour_id";
		return sql+" order by t.hour_id asc";
	}
	public String selectList(ApplicationRate vo) {
		String type=vo.getType();
		String dateId=vo.getDateId();
		if("1".equals(type)){
			//记录数
			String sql = "select  t.hour_id hourId,"
					+" sum(case when identified_type='1' then number_total end) yyjlSblFz,"
					+" (sum(number_total)-sum(case when identified_type='0'  then number_ip_total end))  yyjlSblNoipFm,"
					+" (sum(number_total))  yyjlSblFm,"
					+" round((sum(case when identified_type='1' then number_total end)*100/sum(number_total)),2) yyjlSblPer "
					+" from   dm_d_identified_pandect t where t.date_id='"+dateId+"'  group by t.hour_id";
			return sql+" order by t.hour_id asc";
		}else{
			//流量数
			String sql = "select  t.hour_id hourId,"
					+" sum(case when identified_type='1' then flow_total end)  yyllSblFz,"
					+" (sum(flow_total)-sum(case when identified_type='0'  then flow_ip_total end))  yyllSblNoipFm,"
					+" (sum(flow_total))  yyllSblFm,"
					+" round((sum(case when identified_type='1' then flow_total end)*100/sum(flow_total)),2) yyllSblPer "
					+" from   dm_d_identified_pandect t where t.date_id='"+dateId+"'  group by t.hour_id";
			return sql+" order by t.hour_id asc";
		}

	}
	public String selectExportList(ApplicationRate vo) {
		String dateId=vo.getDateId();
		String sql = "select  t.date_id dateId, t.hour_id hourId,"
				+" sum(case when identified_type='1' then number_total end) yyjlSblFz,"
				+" (sum(number_total)-sum(case when identified_type='0'  then number_ip_total end))  yyjlSblNoipFm,"
				+" (sum(number_total))  yyjlSblFm,"
				+" round((sum(case when identified_type='1' then number_total end)*100/sum(number_total)),2) yyjlSblPer, "
				+" sum(case when identified_type='1' then flow_total end)  yyllSblFz,"
				+" (sum(flow_total)-sum(case when identified_type='0'  then flow_ip_total end))  yyllSblNoipFm,"
				+" (sum(flow_total))  yyllSblFm, "
				+" round((sum(case when identified_type='1' then flow_total end)*100/sum(flow_total)),2) yyllSblPer "
				+" from   dm_d_identified_pandect t where t.date_id='"+dateId+"'  group by t.date_id,t.hour_id";
		return sql+" order by t.hour_id asc";

	}
}
