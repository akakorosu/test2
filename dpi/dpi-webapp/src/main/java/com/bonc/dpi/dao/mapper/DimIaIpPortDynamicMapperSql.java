package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import com.bonc.dpi.dao.entity.DimIaIpPortDynamic;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.utils.DpiUtils;

/**
 * 
 * @author BONC-XUXL
 *
 */
public class DimIaIpPortDynamicMapperSql {
	
	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制

	/**
	 * 查询VO(根据主键)
	 * @param id
	 * @return
	 */
	public String selectVoByPrimaryKey(DimIaIpPortDynamic vo) {
		String ipPort = vo.getIpPort();
		String sql = this.getSql();
		sql = sql+" and t.ip_port = '"+ipPort+"'";
		return sql;
	}
	
	/**
	 * 删除VO(根据主键)
	 * @param id
	 * @return
	 */
	public String deleteVoByPrimaryKey(DimIaIpPortDynamic vo) {
		
		String ipPort = vo.getIpPort();
		String sql = "";
		sql = "delete from dim_ia_ip_port_dynamic where ip_port = '"+ipPort+"'";		
		return sql;
	}
	
	/**
	 * 批量插入
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insertVoPl(Map map) {
		
        List<OperationFlow> voList = (List<OperationFlow>) map.get("list");
        StringBuilder sb = new StringBuilder();
        
		if("mysql".equals(database_type)){
	        sb.append("insert into dim_ia_ip_port_dynamic ");  
	        sb.append("(ip_port,ip,port,prod_id,author,op_time,id) ");  
	        sb.append("values ");  
	        MessageFormat mf = new MessageFormat(
	        		  "(#'{'list[{0}].ipPort},"
	        		+ "#'{'list[{0}].ip},"
	        		+ "#'{'list[{0}].port},"
	        		+ "#'{'list[{0}].prodid},"
	        		+ "#'{'list[{0}].author},"
	        		+ "#'{'list[{0}].opTime},"
	        		+ "#'{'list[{0}].id})");  
	        for (int i = 0; i < voList.size(); i++) {  
	            sb.append(mf.format(new Integer[]{i}));  
	            if (i < voList.size() - 1) {  
	                sb.append(",");  
	            }  
	        }
		}else if("oracle".equals(database_type)){
			String str = " into dim_ia_ip_port_dynamic (ip_port,ip,port,prod_id,author,op_time,id) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				DimIaIpPortDynamic vo = (DimIaIpPortDynamic) operationFlow;
				String ipPort = vo.getIpPort();
				String ip = vo.getIp();
				String port = vo.getPort();
				String prodID = vo.getProdid();
				String author = vo.getAuthor();
				String opTime = vo.getOpTime();
				String id = vo.getId();
				sql += str+"('"+
						ipPort+"','"+ip+"','"+port+"','"+prodID+"','"+author+"','"+opTime+"','"+id
						+"')";
			}
			sb.append(sql+" select 1 from dual");
		}
        return sb.toString();  
    }  
	
	/**
	 * 基础sql语句
	 * @return
	 */
	private String getSql() {
		String sql = "select "
				+ "	t.ip_port ipPort," 
				+ "	t.ip ip," 
				+ "	t.port port," 
				+ "	t.prod_id prodid," 
				+ "	t.author author,"
				+ "	t.op_time opTime,"
				+ "	t.id id"
				+ " from "
				+ " dim_ia_ip_port_dynamic t"
				+ " where 1=1 ";
		return sql;
	}
}
