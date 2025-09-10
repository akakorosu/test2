package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.utils.DpiUtils;

/**
 * 域名表
 * dim_ia_domain_rule
 * @author BONC-XUXL
 *
 */
public class DomainRuleMapperSql {
	
	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	public String selectDataNum(DomainRule vo) {
		String domainCode = vo.getDomainCode();
		String prodName = vo.getProdName();
		String opTime =vo.getOpTime();
		String startTime =vo.getStartTime();
		String endTime =vo.getEndTime();
		String author =vo.getAuthor();
		String sql = "";
		
		if(StringUtils.isBlank(domainCode) && StringUtils.isBlank(prodName)){
			sql = "select count(*) from dim_ia_domain_rule t1 where 1=1";
		}else {
			sql = "select count(*) from dim_ia_domain_rule t1 left join dim_ia_product_info t2 on t1.prod_id = t2.prod_id where 1=1";
		}
		
		if(!StringUtils.isBlank(domainCode)) {
			sql += " and t1.domain_code like '%"+domainCode+"%'";
		}
		//操作时间，精确到天20180101
		/*if(!StringUtils.isBlank(opTime)) {
			sql += " and t1.op_time = '"+opTime+"'";
		}*/
		if(!StringUtils.isBlank(author)) {
			sql += " and t1.author like '%"+author+"%'";
		}
		//操作时间，精确到天20180101
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t1.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t1.op_time <= '"+endTime+"'";
		}
		if(!StringUtils.isBlank(prodName)) {
			sql += " and t2.prod_name like '%"+prodName+"%'";
		}
		return sql+" order by t1.op_time desc";
	}
	

	/**
	 * 查询列表
	 * @param vo
	 * @return
	 */
	public String selectList(DomainRule vo) {
		String domainCode = vo.getDomainCode();
		String prodName = vo.getProdName();
		String opTime =vo.getOpTime();
		String startTime =vo.getStartTime();
		String endTime =vo.getEndTime();
		String author =vo.getAuthor();
		String prodId =vo.getProdId();
//		String sql = this.getSql();
		String sql = "";
//		String sql = "select t1.* from dim_ia_domain_rule t1 left join dim_ia_product_info t2 on t1.prod_id = t2.prod_id where 1=1";
		
		if( StringUtils.isBlank(prodName)){
			sql = "select t1.* from dim_ia_domain_rule t1 where 1=1";
		}else {
			//sql = "select t1.* from dim_ia_domain_rule t1 left join dim_ia_product_info t2 on t1.prod_id = t2.prod_id where 1=1";
			
			//sql = "select t1.* from dim_ia_domain_rule t1 left join (select t2.prod_id from dim_ia_product_info t2 where t2.prod_name like '%"+prodName+"%' ) t3 on t1.prod_id = t3.prod_id where 1=1";
			sql = "select t1.* from dim_ia_domain_rule t1 where  t1.prod_id=(select t2.prod_id from dim_ia_product_info t2 where t2.prod_name = '"+prodName+"' )  ";
			
		}
		
		if(!StringUtils.isBlank(domainCode)) {
			sql += " and t1.domain_code like '%"+domainCode+"%'";
		}
		if(!StringUtils.isBlank(author)) {
			sql += " and t1.author like '%"+author+"%'";
		}
		//操作时间，精确到天20180101
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t1.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t1.op_time <= '"+endTime+"'";
		}	
		/*if(!StringUtils.isBlank(startTime)) {
			sql += " and t1.op_time BETWEEN '"+startTime+"' and '"+endTime+"' ";
		}*/
		if(!StringUtils.isBlank(prodId)) {
			sql += " and t1.prod_id = '"+prodId+"'";
		}
		return sql+" order by t1.op_time desc,t1.domain_code desc";
	}
	
	public String selectVoByProdId(DomainRule vo) {
		String domainCode = vo.getDomainCode();
		String prodName = vo.getProdName();
		String opTime =vo.getOpTime();
		String startTime =vo.getStartTime();
		String endTime =vo.getEndTime();
		String author =vo.getAuthor();
		String prodId =vo.getProdId();
//		String sql = this.getSql();
		String sql = "";
//		String sql = "select t1.* from dim_ia_domain_rule t1 left join dim_ia_product_info t2 on t1.prod_id = t2.prod_id where 1=1";
		
		
			sql = "select t1.* from dim_ia_domain_rule t1 where 1=1";
	
			//sql = "select t1.* from dim_ia_domain_rule t1 left join dim_ia_product_info t2 on t1.prod_id = t2.prod_id where 1=1";
			//sql = "select t1.* from dim_ia_domain_rule t1 left join (select t2.prod_id from dim_ia_product_info t2 where t2.prod_name like '%"+prodName+"%' ) t3 on t1.prod_id = t3.prod_id where 1=1";
		
		if(!StringUtils.isBlank(prodId)) {
			sql += " and t1.prod_id = '"+prodId+"'";
	    }
			
		if(!StringUtils.isBlank(domainCode)) {
			sql += " and t1.domain_code like '%"+domainCode+"%'";
		}
		if(!StringUtils.isBlank(author)) {
			sql += " and t1.author like '%"+author+"%'";
		}
		//操作时间，精确到天20180101
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t1.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t1.op_time <= '"+endTime+"'";
		}	
		/*if(!StringUtils.isBlank(startTime)) {
			sql += " and t1.op_time BETWEEN '"+startTime+"' and '"+endTime+"' ";
		}*/
		/*if(!StringUtils.isBlank(prodName)) {
			sql += " and t2.prod_name like '%"+prodName+"%'";
		}*/
		return sql+" order by t1.op_time desc";
	}
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	public String selectVoByPrimaryKey(DomainRule vo) {
		String sql = "select * from dim_ia_domain_rule where domain_code=#{domainCode}";
		return sql;
	}
	
	public String selectListByProdName(DomainRule vo) {
		
		String prodName = vo.getProdName();
		String sql = "select prod_id prodId, prod_name prodName from dim_ia_product_info where 1=1 ";
		if(!"".equals(prodName) && prodName!=null){
			sql += " and prod_name like '%"+prodName+"%'";
		}
		return sql;
	}
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	public String deleteVoByPrimaryKey(DomainRule vo) {
		String sql = "delete from dim_ia_domain_rule where domain_code=#{domainCode}";
		return sql;
	}
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	public String insertVo(DomainRule vo) {
		String sql = "insert into dim_ia_domain_rule("
				+ "	id,"
				+ "	domain_code,"
				+ "	prod_id,"
				+ "	author,"
				+ "	op_time)"
				+ "values("
				+ "	#{id}," 
				+ "	#{domainCode}," 
				+ "	#{prodId}," 
				+ " #{author},"
				+ " #{opTime})";
		return sql;
	}
	
	/**
	 * 批量插入
	 * @param map
	 * @return
	 */
	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insertVoPl(Map map) {
		
        List<OperationFlow> voList = (List<OperationFlow>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        
        if("mysql".equals(database_type)){
            sb.append("insert into dim_ia_domain_rule ");  
            sb.append("(id,domain_code,prod_id,author,op_time) ");  
            sb.append("values ");  
            MessageFormat mf = new MessageFormat(
            		  "(#'{'list[{0}].id},"
    			    + "#'{'list[{0}].domainCode},"
            		+ "#'{'list[{0}].prodId},"
            		+ "#'{'list[{0}].author},"
            		+ "#'{'list[{0}].opTime})");  
            for (int i = 0; i < voList.size(); i++) {  
                sb.append(mf.format(new Integer[]{i}));  
                if (i < voList.size() - 1) {  
                    sb.append(",");  
                }  
            }
        }else if("oracle".equals(database_type)){
			String str = " into dim_ia_domain_rule (domain_code,prod_id,author,op_time,id) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				DomainRule vo = (DomainRule) operationFlow;
				String domainCode=vo.getDomainCode(); //域名编码
				String prodId=vo.getProdId();//产品ID
				String author=vo.getAuthor();//操作人
				String opTime=vo.getOpTime(); //操作时间
				String id = vo.getId();
				
				sql += str+"('"+
						domainCode+"','"+prodId+"','"+author+"','"+opTime+"','"+id
						+"')";
			}
			sb.append(sql+" select 1 from dual");
        }

        return sb.toString();  
    }*/  
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insertVoPl(Map map) {
		
        List<OperationFlow> voList = (List<OperationFlow>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        
        if("mysql".equals(database_type)){
            sb.append("insert into dim_ia_domain_rule ");  
            sb.append("(id,domain_code,prod_id,author,op_time) ");  
            sb.append("values ");  
            MessageFormat mf = new MessageFormat(
            		  "(#'{'list[{0}].id},"
    			    + "#'{'list[{0}].domainCode},"
            		+ "#'{'list[{0}].prodId},"
            		+ "#'{'list[{0}].author},"
            		+ "#'{'list[{0}].opTime})");  
            for (int i = 0; i < voList.size(); i++) {  
                sb.append(mf.format(new Integer[]{i}));  
                if (i < voList.size() - 1) {  
                    sb.append(",");  
                }  
            }
        }else if("oracle".equals(database_type)){
			String str = " into dim_ia_domain_rule (domain_code,prod_id,author,op_time,id) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				DomainRule vo = (DomainRule) operationFlow;
				String domainCode=vo.getDomainCode(); //域名编码
				String prodId=vo.getProdId();//产品ID
				String author=vo.getAuthor();//操作人
				String opTime=vo.getOpTime(); //操作时间
				String id = vo.getId();
				
				sql += str+"('"+
						domainCode+"','"+prodId+"','"+author+"','"+opTime+"','"+id
						+"')";
			}
			sb.append(sql+" select 1 from dual");
        }

        return sb.toString();  
    }
	
	
	
	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	public String updateVo(DomainRule vo) {
		String sql = "update dim_ia_domain_rule set "
				+"domain_code=#{domainCode},"
				+"prod_id=#{prodId},"
				+"author=#{author},"
				+"op_time=#{opTime}"
				+"  where domain_code=#{domainCodeOld}";
		return sql;
	}
	
	/**
	 * 主键校验
	 * @param vo
	 * @return
	 */
	public String selectCheck(DomainRule vo) {
		String sql = " select count(*) from dim_ia_domain_rule t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getDomainCode())) {
			sql += " and t.domain_code = #{domainCode} ";
		}
		return sql;
	}
	
	/**
	 * 基础sql语句
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getSql() {
		String sql = "select "
				+ "	t.domain_code domainCode," 
				+ "	t.prod_id prodId," 
				+ "	t.author author,"
				+ "	t.op_time opTime"
				+ " from "
				+ " dim_ia_domain_rule t"
				+ " where 1=1 ";
		return sql;
	}
}
