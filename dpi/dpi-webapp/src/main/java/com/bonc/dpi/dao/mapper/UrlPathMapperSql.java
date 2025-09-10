package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bonc.dpi.dao.entity.DimIaUserAgentRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.UrlPath;
import com.bonc.dpi.utils.DpiUtils;

/**
 * url路径规则表
 * dim_ia_url_path
 * @author BONC-XUXL
 *
 */
public class UrlPathMapperSql {
	
	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	public String selectDataNum(UrlPath vo) {
		String host = vo.getHost();
		String prodName = vo.getProdName();
		String opTime =vo.getOpTime();
		String startTime =vo.getStartTime();
		String endTime =vo.getEndTime();

		String sql = " select count(t1.id) from dim_ia_url_path t1 where 1=1 ";
		if(!StringUtils.isBlank(prodName)) {
			sql += " and t1.prod_id in (SELECT prod_id FROM dim_ia_product_info where PROD_NAME like '%"+prodName+"%' ) ";
		}else {
			sql += " and t1.prod_id in (SELECT prod_id FROM dim_ia_product_info ) ";
		}
		if(!StringUtils.isBlank(host)) {
			sql += " and t1.host like '%"+host+"%'";
		}
		//操作时间，精确到天20180101
		/*if(!StringUtils.isBlank(opTime)) {
			sql += " and t1.op_time = '"+opTime+"'";
		}*/	
		if(!StringUtils.isBlank(vo.getAuthor())) {
			vo.setAuthor("%" + vo.getAuthor() + "%");
			sql += " and t1.author like #{author} ";
		}
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t1.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t1.op_time <= '"+endTime+"'";
		}
		return sql+" order by t1.op_time desc,t1.host desc";
	}
	
	
	/**
	 * 查询列表
	 * @param vo
	 * @return
	 */
	public String selectList(UrlPath vo) {
		String host = vo.getHost();
		String prodName = vo.getProdName();
		String opTime =vo.getOpTime();
		String startTime =vo.getStartTime();
		String endTime =vo.getEndTime();
		String sql = "select "
				+ "	t1.id id," 
				+ "	t1.host host," 
				+ "	t1.prod_id prodId," 
				+ "	t1.regexp regexpRule," 
				+ "	t1.comments comments," 
				+ "	t1.url_example urlExample," 
				+ "	t1.author author,"
				+ "	t1.op_time opTime ";
//		 sql += "from dim_ia_url_path t1  join dim_ia_product_info t2 on t1.prod_id = t2.prod_id where 1=1";
		sql += " from dim_ia_url_path t1 where 1=1 ";
		if(!StringUtils.isBlank(prodName)) {
			sql += " and t1.prod_id in (SELECT prod_id FROM dim_ia_product_info where PROD_NAME like '%"+prodName+"%' ) ";
		}else {
			sql += " and t1.prod_id in (SELECT prod_id FROM dim_ia_product_info ) ";
		}
		if(!StringUtils.isBlank(host)) {
			sql += " and t1.host like '%"+host+"%'";
		}
		//操作时间，精确到天20180101
		/*if(!StringUtils.isBlank(opTime)) {
			sql += " and t1.op_time = '"+opTime+"'";
		}	*/	
		if(!StringUtils.isBlank(vo.getAuthor())) {
			vo.setAuthor("%" + vo.getAuthor() + "%");
			sql += " and t1.author like #{author} ";
		}
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t1.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t1.op_time <= '"+endTime+"'";
		}
		return sql+" order by t1.op_time desc,t1.host desc";
	}
	
	/**
	 * 查询VO(根据主键)
	 * @param id
	 * @return
	 */
	public String selectVoByPrimaryKey(UrlPath vo) {
		String host = vo.getHost();
		String regexp = vo.getRegexpRule();
		String sql = this.getSql();
		if("mysql".equals(database_type)){
			sql = sql+" and t.host='"+host+"' and t.`regexp`='"+regexp+"'";
		}else if("oracle".equals(database_type)){
			sql = sql+" and t.host='"+host+"' and t.regexp='"+regexp+"'";
		}
		
		return sql;
	}
	
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public String selectVoById(String id) {
		String sql = this.getSql() + " and id=#{id} ";
		return sql;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public String deleteVoById(String id) {
		String sql = "delete from dim_ia_url_path where id=#{id}";
		return sql;
	}
	
	
	/**
	 * 删除VO(根据主键)
	 * @param id
	 * @return
	 */
	public String deleteVoByPrimaryKey(UrlPath vo) {
		String host = vo.getHost();
		String regexp = vo.getRegexpRule();
		String sql = "";
		if("mysql".equals(database_type)){
			sql = "delete from dim_ia_url_path  where host='"+host+"' and `regexp`='"+regexp+"'";
		}else if("oracle".equals(database_type)){
			sql = "delete from dim_ia_url_path  where host='"+host+"' and regexp='"+regexp+"'";
		}
		
		return sql;
	}
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	public String insertVo(UrlPath vo) {
		
		String regexp = "";
		if("mysql".equals(database_type)){
			regexp = " `regexp`,";
		}else if("oracle".equals(database_type)){
			regexp = " regexp,";
		}
		
		String sql = "insert into dim_ia_url_path("
				+ "	id,"
				+ "	host,"
				+ "	prod_id,"
				+ regexp
				+ "	comments,"
				+ "	url_example,"
				+ "	author,"
				+ "	op_time)"
				+ "values("
				+ "	#{id}," 
				+ "	#{hostParam}," 
				+ "	#{prodId}," 
				+ "	#{regexpRule}," 
				+ "	#{comments}," 
				+ "	#{urlExample}," 
				+ " #{author},"
				+ " #{opTime})";
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
		
		String regexp = "";
		if("mysql".equals(database_type)){
			regexp = " `regexp`,";
	        sb.append("insert into dim_ia_url_path ");
	        sb.append("(host, prod_id,"+regexp+" comments,url_example,author,op_time,id) ");
	        sb.append("values ");
	        MessageFormat mf = new MessageFormat(
	        		  "(#'{'list[{0}].host},"
	        		+ "#'{'list[{0}].prodId},"
	        		+ "#'{'list[{0}].regexpRule},"
	        		+ "#'{'list[{0}].comments},"
	        		+ "#'{'list[{0}].urlExample},"
	        		+ "#'{'list[{0}].author},"
	        		+ "#'{'list[{0}].opTime},"
	        		+ "#'{'list[{0}].id})");  
	        for (int i = 0; i < voList.size(); i++) {
	        	//DimIaUserAgentRule a= (DimIaUserAgentRule) voList.get(i);
	        	//a.setUaKeyRule(a.getUaKeyRule().replaceAll("'", "''")); //后加的
	            sb.append(mf.format(new Integer[]{i}));  
	            if (i < voList.size() - 1) {  
	                sb.append(",");  
	            }  
	        }  
		}else if("oracle".equals(database_type)){
			String str = " into dim_ia_url_path (host,prod_id,regexp,comments,url_example,author,op_time,id) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				UrlPath vo = (UrlPath) operationFlow;
				String host=vo.getHost(); //域名编码
				String prodId=vo.getProdId();//产品ID
				String regexpRule = vo.getRegexpRule();//匹配规则REGEXP
				String comments = vo.getComments();//备注COMMENTS
				String urlExample = vo.getUrlExample();//URL样例URL_EXAMPLE
				String author = vo.getAuthor();//作者
				String opTime = vo.getOpTime();//操作时间
				String id = vo.getId();
				
				sql += str+"('"+
						host+"','"+prodId+"','"+regexpRule+"','"+comments+"','"+urlExample+"','"+author+"','"+opTime+"','"+id
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
	public String updateVo(UrlPath vo) {
		
		String aa=vo.getRegexpRule();
		String b=aa;
		
		
		String sql = "update dim_ia_url_path t set "
				+"host=#{hostParam},"
				+"prod_id=#{prodId},"
				+"t.regexp=#{regexpRule},"
				+"comments=#{comments},"
				+"url_example=#{urlExample},"
				+"author=#{author},"
				+"op_time=#{opTime}"
				//+"  where host=#{hostOld} and t.regexp=#{regexpRuleOld}";
				+"  where id=#{id} ";
		return sql;
	}
	
	/**
	 * 联合主键校验
	 * @param vo
	 * @return
	 */
	public String selectCheck(UrlPath vo) {
		String sql = " select count(*) from dim_ia_url_path t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.id != #{id} ";
		}
		if(!StringUtils.isBlank(vo.getHostParam())) {
			sql += " and t.host = #{hostParam} ";
		}
		if(!StringUtils.isBlank(vo.getRegexpRule())) {
			sql += " and t.regexp = #{regexpRule} ";
		}
		return sql;
	}
	
	/**
	 * 基础sql语句
	 * @return
	 */
	private String getSql() {
		String sql = "select "
				+ "	t.id id," 
				+ "	t.host host," 
				+ "	t.prod_id prodId," 
				+ "	t.regexp regexpRule," 
				+ "	t.comments comments," 
				+ "	t.url_example urlExample," 
				+ "	t.author author,"
				+ "	t.op_time opTime"
				+ " from "
				+ " dim_ia_url_path t"
				+ " where 1=1 ";
		return sql;
	}
}
