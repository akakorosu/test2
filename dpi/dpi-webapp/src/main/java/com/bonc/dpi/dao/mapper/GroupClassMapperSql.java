package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bonc.dpi.dao.entity.DimIaIpPortDynamic;
import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.GroupClass;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.ProductLabel;
import com.bonc.dpi.utils.DpiUtils;


public class GroupClassMapperSql {
	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制
	
	/**
	 * 查询列表
	 * @param vo
	 * @return
	 */
	public String selectDataNum(GroupClass vo) {
		String sql="";
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
		if(StringUtils.isBlank(vo.getProdName())) {
			sql="select count(*) from dim_ia_group_class t where 1=1";
			if(!StringUtils.isBlank(author)) {
				sql += " and t.author like '"+author+"'";
			}
			if(!StringUtils.isBlank(startTime)) {
				sql += " and t.op_time >= '"+startTime+"'";
			}
			if(!StringUtils.isBlank(endTime)) {
				sql += " and t.op_time <= '"+endTime+"'";
			}	
		}else {
			sql="select count(*) from dim_ia_group_class t1 "
					+ "left join dim_ia_product_info t2 on t1.prod_id = t2.prod_id where 1=1";
			if(!StringUtils.isBlank(author)) {
				sql += " and t1.author like '"+author+"'";
			}
			if(!StringUtils.isBlank(startTime)) {
				sql += " and t1.op_time >= '"+startTime+"'";
			}
			if(!StringUtils.isBlank(endTime)) {
				sql += " and t1.op_time <= '"+endTime+"'";
			}		
			vo.setProdName("%" + vo.getProdName() + "%");
			sql += " and t2.PROD_Name like #{prodName}";
		}
		return sql;
	}
	
	/**
	 * 查询列表
	 * @param vo
	 * @return
	 */
	public String selectList(GroupClass vo) {
		String sql="";
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
		if(StringUtils.isBlank(vo.getProdName())) {
			sql="select t.ID id,t.CLASS_CODE classCode,t.CLASS_NAME className,t.SUB_CLASS_CODE subClassCode,"
					+ "t.SUB_CLASS_NAME subClassName,t.PROD_ID prodid,t.AUTHOR author,t.OP_TIME opTime  "
					+ "from dim_ia_group_class t where 1=1 ";
			if(!StringUtils.isBlank(author)) {
				sql += " and t.author like '"+author+"'";
			}
			if(!StringUtils.isBlank(startTime)) {
				sql += " and t.op_time >= '"+startTime+"'";
			}
			if(!StringUtils.isBlank(endTime)) {
				sql += " and t.op_time <= '"+endTime+"'";
			}	
			sql += " order by t.OP_TIME desc ,t.ID desc";
		}else {
			sql="select t1.ID id,t1.CLASS_CODE classCode,t1.CLASS_NAME className,t1.SUB_CLASS_CODE subClassCode,"
					+ "t1.SUB_CLASS_NAME subClassName,t1.PROD_ID prodid,t1.AUTHOR author,t1.OP_TIME opTime,t2.prod_name prodName  from dim_ia_group_class t1 "
					+ "left join dim_ia_product_info t2 on t1.prod_id = t2.prod_id where 1=1 ";
			if(!StringUtils.isBlank(author)) {
				sql += " and t1.author like '"+author+"'";
			}
			if(!StringUtils.isBlank(startTime)) {
				sql += " and t1.op_time >= '"+startTime+"'";
			}
			if(!StringUtils.isBlank(endTime)) {
				sql += " and t1.op_time <= '"+endTime+"'";
			}			
			vo.setProdName("%" + vo.getProdName() + "%");
			sql += " and t2.PROD_Name like #{prodName}";
			sql += " order by t1.OP_TIME desc";
		}
		return sql;
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public String selectVoById(String id) {
		String sql = this.getSql() + " and t.ID=#{id} ";
		return sql;
	}
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public String selectVoByIdWithProdName(String id) {
		String sql = this.selectVoByIdWithProdName() + " and t.ID=#{id} ";
		return sql;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public String deleteVoById(String id) {
		String sql = "delete from dim_ia_group_class where ID=#{id}";
		return sql;
	}
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	public String insertVo(GroupClass vo) {
		String sql = "insert into dim_ia_group_class("
				+ "	ID,"
				+ "	CLASS_CODE,"
				+ "	CLASS_NAME,"
				+ "	SUB_CLASS_CODE ,"
				+ "	SUB_CLASS_NAME,"
				+ "	PROD_ID,"
				+ "	AUTHOR,"
				+ "	OP_TIME)"
				+ "values("
				+ "	#{id}," 
				+ "	#{classCode}," 
				+ "	#{className}," 
				+ "	#{subClassCode}," 
				+ "	#{subClassName}," 
				+ "	#{prodid}," 
				+ "	#{author}," 
				+ " #{opTime})";
		return sql;
	}
	
	
	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	public String updateVo(GroupClass vo) {
		String sql = "update dim_ia_group_class set "
				+"CLASS_CODE=#{classCode},"
				+"CLASS_NAME=#{className},"
				+"SUB_CLASS_CODE=#{subClassCode},"
				+"SUB_CLASS_NAME=#{subClassName},"
				+"PROD_ID=#{prodid},"
				+"AUTHOR=#{author},"
				+"OP_TIME=#{opTime}"
				+"  where ID=#{id}";
		return sql;
	}
	
	/**
	 * 联合主键校验
	 * @param vo
	 * @return
	 */
	public String selectCheck(GroupClass vo) {
		String sql = " select count(*) from dim_ia_group_class t where 1=1 ";
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.ID != #{id} ";
		}		
		if(!StringUtils.isBlank(vo.getProdid())) {
			sql += " and t.PROD_ID = #{prodid} ";
		}
		if(!StringUtils.isBlank(vo.getClassCode())) {
			sql += " and t.CLASS_CODE = #{classCode} ";
		}
		if(!StringUtils.isBlank(vo.getSubClassCode())) {
			sql += " and t.SUB_CLASS_CODE = #{subClassCode} ";
		}
		return sql;
	}
	/**验证数据的唯一性
	 * @param vo
	 * @return
	 */
	public String checkUnique(GroupClass vo) {
		String sql = " select count(*) from dim_ia_group_class t where 1=1 ";
			
		if(!StringUtils.isBlank(vo.getProdid())) {
			sql += " and t.PROD_ID = #{prodid} ";
		}
		if(!StringUtils.isBlank(vo.getClassCode())) {
			sql += " and t.CLASS_CODE = #{classCode} ";
		}
		if(!StringUtils.isBlank(vo.getSubClassCode())) {
			sql += " and t.SUB_CLASS_CODE = #{subClassCode} ";
		}
		return sql;
	}
	
	
	
	/**
	 * 基础sql语句
	 * @return
	 */
	private String getSql() {
		String sql = "select "
				+ "	t.ID id,"
				+ "	t.CLASS_CODE classCode," 
				+ "	t.CLASS_NAME className," 
				+ "	t.SUB_CLASS_CODE subClassCode,"
				+ "	t.SUB_CLASS_NAME subClassName," 
				+ "	t.PROD_ID prodid,"
				+ "	t.AUTHOR author," 
				+ "	t.OP_TIME opTime"
				+ " from "
				+ " dim_ia_group_class t "
				+ " where 1=1 ";
		return sql;
	}

	/**
	 * 基础sql语句
	 * @return
	 */
	private String selectVoByIdWithProdName() {
		String sql = "select "
				+ "	t.ID id,"
				+ "	t.CLASS_CODE classCode," 
				+ "	t.CLASS_NAME className," 
				+ "	t.SUB_CLASS_CODE subClassCode,"
				+ "	t.SUB_CLASS_NAME subClassName," 
				+ "	t.PROD_ID prodid,"
				+ "	t.AUTHOR author," 
				+ "	t.OP_TIME opTime," 
				+ "	p.PROD_NAME" 
				+ " from "
				+ "  dim_ia_group_class t LEFT JOIN  dim_ia_product_info p  on t.PROD_ID=p.PROD_ID  "
				+ " where 1=1 ";
		return sql;
	}

	/**
	 * 查询VO(根据主键)
	 * @param id
	 * @return
	 */
	public String selectVoByPrimaryKey(GroupClass vo) {
		String classCode=vo.getClassCode();
		String subClassCode=vo.getSubClassCode();
		String prodid=vo.getProdid();
		String sql = this.getSql();
		sql = sql+" and t.CLASS_CODE = '"+classCode+"' and t.SUB_CLASS_CODE='"+subClassCode+"' and t.PROD_ID='"+prodid+"'";
		return sql;
	}
	
	/**
	 * 删除VO(根据主键)
	 * @param id
	 * @return
	 */
	public String deleteVoByPrimaryKey(GroupClass vo) {
		String classCode=vo.getClassCode();
		String subClassCode=vo.getSubClassCode();
		String prodid=vo.getProdid();
		String sql = "";
		sql = "delete from dim_ia_group_class where CLASS_CODE = '"+classCode+"' and SUB_CLASS_CODE='"+subClassCode+"' and PROD_ID='"+prodid+"'";		
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
        	 sb.append("insert into dim_ia_group_class ");  
             sb.append("(ID,CLASS_CODE,CLASS_NAME,SUB_CLASS_CODE,SUB_CLASS_NAME,PROD_ID,AUTHOR,OP_TIME) ");  
             sb.append("values ");  
             MessageFormat mf = new MessageFormat(
             		  "(#'{'list[{0}].id},"
             		+ "#'{'list[{0}].classCode},"
             		+ "#'{'list[{0}].className},"
             		+ "#'{'list[{0}].subClassCode},"
             		+ "#'{'list[{0}].subClassName},"
             		+ "#'{'list[{0}].prodid},"
             		+ "#'{'list[{0}].author},"
             		+ "#'{'list[{0}].opTime})");  
             for (int i = 0; i < voList.size(); i++) {  
                 sb.append(mf.format(new Integer[]{i}));  
                 if (i < voList.size() - 1) {  
                     sb.append(",");  
                 }  
             }
        }else if("oracle".equals(database_type)){
        	String str = " into dim_ia_group_class (ID,CLASS_CODE,CLASS_NAME,SUB_CLASS_CODE,SUB_CLASS_NAME,PROD_ID,AUTHOR,OP_TIME) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				GroupClass vo = (GroupClass) operationFlow;
				sql += str+"('"+
						vo.getId()+"','"+vo.getClassCode()+"','"+vo.getClassName()+"','"+vo.getSubClassCode()+"','"+vo.getSubClassName()
						+"','"+vo.getProdid()+"','"+vo.getAuthor()+"','"+vo.getOpTime()
						+"')";
			}
			sb.append(sql+" select 1 from dual");
        }
        return sb.toString();  
    }  
	/**
	 * 根据产品id模糊查询
	 * @param prodName
	 * @return
	 */
	public String getProdIdListByProdId(GroupClass vo){
		String sql = " select * from dim_ia_product_info t where 1=1 ";
		if(!StringUtils.isBlank(vo.getProdid())) {
			vo.setProdid("%" + vo.getProdid() + "%");
			sql += " and t.prod_name like #{prodid} "; 
		}
		return sql;
	}
	/**
	 * 根据产品名称模糊查询
	 * @param prodName
	 * @return
	 */
	public String getProdIdListByProdName(GroupClass vo){
		String sql = " select prod_id prodid,prod_name prodName  from dim_ia_product_info t where 1=1 ";
		if(!StringUtils.isBlank(vo.getProdName())) {
			vo.setProdName("%" + vo.getProdName() + "%");
			sql += " and t.prod_name like  #{prodName}"; 
		}
		return sql;
	}
}
