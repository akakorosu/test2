package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.UrlParameter;
import com.bonc.dpi.utils.DpiUtils;

/**
 * url参数规则表
 * dim_ia_url_parameter
 * @author BONC-XUXL
 *
 */
public class UrlParameterMapperSql {
	
	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	public String selectDataNum(UrlParameter vo) {
		String host = vo.getHost();
		String author =vo.getAuthor();
		String startTime =vo.getStartTime();
		String endTime =vo.getEndTime();
		String prodName = vo.getProdName();
		//String opTime =vo.getOpTime();
		String regexpRule = vo.getRegexpRule();

		String sql = " select count(t1.id) from dim_ia_url_parameter t1 where 1=1 ";
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
		if(!StringUtils.isBlank(author)) {
			sql += " and t1.author like '%"+author+"%'";
		}
		if(!StringUtils.isBlank(regexpRule)) {
			sql += " and t1.regexp like '%"+regexpRule+"%'";
		}
		//操作时间，精确到天20180101
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t1.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t1.op_time <= '"+endTime+"'";
		}
		return sql+" order by t1.op_time desc";
	}

	
	/**
	 * 查询列表
	 * @param vo
	 * @return
	 */
	public String selectList(UrlParameter vo) {
		String host = vo.getHost();
		String author =vo.getAuthor();
		String startTime =vo.getStartTime();
		String endTime =vo.getEndTime();
		String prodName = vo.getProdName();
		//String opTime =vo.getOpTime();
		String regexpRule = vo.getRegexpRule();
		String sql = "select "
				+ "	t1.id id,"
				+ "	t1.host host," 
				+ "	t1.parameter_code parameterCode," 
				+ "	t1.parameter_value parameterValue," 
				+ "	t1.prod_id prodId," 
				+ "	t1.regexp regexpRule," 
				+ "	t1.comments comments," 
				+ "	t1.url_example urlExample," 
				+ "	t1.author author,"
				+ "	t1.op_time opTime ";	
//		sql += " from dim_ia_url_parameter t1 left join dim_ia_product_info t2 on t1.prod_id = t2.prod_id where 1=1";
		sql += " from dim_ia_url_parameter t1 where 1=1 ";
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
		if(!StringUtils.isBlank(author)) {
			sql += " and t1.author like '%"+author+"%'";
		}
		if(!StringUtils.isBlank(regexpRule)) {
			sql += " and t1.regexp like '%"+regexpRule+"%'";
		}
		//操作时间，精确到天20180101
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t1.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t1.op_time <= '"+endTime+"'";
		}	
		return sql+" order by t1.op_time desc,t1.host desc";
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
		String sql = "delete from dim_ia_url_parameter where id=#{id}";
		return sql;
	}
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	public String deleteVoByPrimaryKey(UrlParameter vo) {
//		String sql = "delete from dim_ia_url_parameter where host=#{host} and regexp=#{regexpRule}";
		String host = vo.getHost();
		String regexp = vo.getRegexpRule();
		String sql = "";
		if("mysql".equals(database_type)){
			sql = "delete from dim_ia_url_parameter  where host='"+host+"' and `regexp`='"+regexp+"'";
		}else if("oracle".equals(database_type)){
			sql = "delete from dim_ia_url_parameter  where host='"+host+"' and regexp='"+regexp+"'";
		}
		return sql;
	}
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	public String selectVoByPrimaryKey(UrlParameter vo) {
//		String sql = "select * from dim_ia_url_parameter where host=#{host} and regexp=#{regexpRule}";
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
	 * 添加
	 * @param vo
	 * @return
	 */
	public String insertVo(UrlParameter vo) {
		
		String regexp = "";
		if("mysql".equals(database_type)){
			regexp = " `regexp`,";
		}else if("oracle".equals(database_type)){
			regexp = " regexp,";
		}
		
		String sql = "insert into dim_ia_url_parameter("
				+ "	id,"
				+ "	host,"
				+ "	parameter_code,"
				+ "	parameter_value,"
				+ "	prod_id,"
				+ regexp
				+ "	comments ,"
				+ "	url_example,"
				+ "	author,"
				+ "	op_time)"
				+ "values("
				+ "	#{id}," 
				+ "	#{hostParam}," 
				+ "	#{parameterCode}," 
				+ "	#{parameterValue},"
				+ "	#{prodId}," 
				+ "	#{regexpRuleParam}," 
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
        
        if("mysql".equals(database_type)){
            sb.append("insert into dim_ia_url_parameter ");  
            sb.append("(id,host, parameter_code,parameter_value,prod_id,`regexp`,comments,url_example,author,op_time) ");  
            sb.append("values ");  
            MessageFormat mf = new MessageFormat(
    	      		  "(#'{'list[{0}].id},"
    	      		+ "#'{'list[{0}].host},"
            		+ "#'{'list[{0}].parameterCode},"
            		+ "#'{'list[{0}].parameterValue},"
            		+ "#'{'list[{0}].prodId},"
            		+ "#'{'list[{0}].regexpRule},"
            		+ "#'{'list[{0}].comments},"
            		+ "#'{'list[{0}].urlExample},"
            		+ "#'{'list[{0}].author},"
            		+ "#'{'list[{0}].opTime})");  
            for (int i = 0; i < voList.size(); i++) {  
                sb.append(mf.format(new Integer[]{i}));  
                if (i < voList.size() - 1) {  
                    sb.append(",");  
                }  
            } 
        }else if("oracle".equals(database_type)){
			String str = " into dim_ia_url_parameter (host,parameter_code,parameter_value,prod_id,regexp,comments,url_example,author,op_time,id) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				UrlParameter vo = (UrlParameter) operationFlow;
				String host=vo.getHost(); //域名编码
				String parameterCode=vo.getParameterCode(); //参数名PARAMETER_CODE
				String parameterValue=vo.getParameterValue(); //参数值PARAMETER_VALUE
				String prodId=vo.getProdId();//产品ID
				String regexpRule = vo.getRegexpRule();//匹配规则REGEXP
				String comments = vo.getComments();//备注COMMENTS
				String urlExample = vo.getUrlExample();//URL样例URL_EXAMPLE
				String author = vo.getAuthor();//作者
				String opTime = vo.getOpTime();//操作时间
				String id = vo.getId();
				
				sql += str+"('"+
						host+"','"+parameterCode+"','"+parameterValue+"','"+prodId+"','"+regexpRule+"','"+comments+"','"+urlExample+"','"+author+"','"+opTime+"','"+id
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
	public String updateVo(UrlParameter vo) {
		String sql = "update dim_ia_url_parameter t set "
				+"host=#{hostParam},"
				+"parameter_code=#{parameterCode},"
				+"parameter_value=#{parameterValue},"
				+"prod_id=#{prodId},"
				+"t.regexp=#{regexpRuleParam},"
				+"comments=#{comments},"
				+"url_example=#{urlExample},"
				+"author=#{author},"
				+"op_time=#{opTime}"
				+"  where id=#{id}";
		return sql;
	}
	
	/**
	 * 联合主键校验
	 * @param vo
	 * @return
	 */
	public String selectCheck(UrlParameter vo) {
		String sql = " select count(*) from dim_ia_url_parameter t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.id != #{id} ";
		}
		if(!StringUtils.isBlank(vo.getHostParam())) {
			sql += " and t.host = #{hostParam} ";
		}
		if(!StringUtils.isBlank(vo.getRegexpRuleParam())) {
			sql += " and t.regexp = #{regexpRuleParam} ";
		}
		return sql;
	}
	
	/**
	 * 不可为空，prod_id必须在产品表里
	 * @param vo
	 * @return
	 */
	public String prodIdCheck(UrlParameter vo) {
		
		String sql = " select count(*) from dim_ia_product_info t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.id != #{id} ";
		}
		if(!StringUtils.isBlank(vo.getProdId())) {
			sql += " and t.prod_id = #{prodId} ";
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
				+ "	t.parameter_code parameterCode," 
				+ "	t.parameter_value parameterValue," 
				+ "	t.prod_id prodId," 
				+ "	t.regexp regexpRule," 
				+ "	t.comments comments," 
				+ "	t.url_example urlExample," 
				+ "	t.author author,"
				+ "	t.op_time opTime"				
				+ " from "
				+ " dim_ia_url_parameter t"
				+ " where 1=1 ";
		return sql;
	}
}
