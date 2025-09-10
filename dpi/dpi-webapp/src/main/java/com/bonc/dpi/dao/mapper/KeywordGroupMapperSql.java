package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bonc.dpi.dao.entity.KeywordGroup;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.utils.DpiUtils;


/**
 * 关键词规则分组
 * dim_ia_keyword_group
 * @author BONC-XUXL
 *
 */
public class KeywordGroupMapperSql {
	
	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制

	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	public String selectDataNum(KeywordGroup vo) {
		
		String groupType = vo.getGroupType();
		String groupName = vo.getGroupName();
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
		String sql = "select count(*) from dim_ia_keyword_group t where 1=1 ";
		
		if(!StringUtils.isBlank(groupType)) {
			sql += " and t.group_type like '%"+groupType+"%' ";
		}
		if(!StringUtils.isBlank(groupName)) {
			sql += " and t.group_name like '%"+groupName+"%' ";
		}
		if(!StringUtils.isBlank(author)) {
			sql += " and t.author like '"+author+"'";
		}
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t.op_time <= '"+endTime+"'";
		}		
		return sql;
	}
	
	/**
	 * 查询列表
	 * @param vo
	 * @return
	 */
	public String selectList(KeywordGroup vo) {
		
		String groupType = vo.getGroupType();
		String groupName = vo.getGroupName();
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
		String sql = this.getSql();
		
		if(!StringUtils.isBlank(groupType)) {
			sql += " and t.group_type like '%"+groupType+"%' ";
		}
		if(!StringUtils.isBlank(groupName)) {
			sql += " and t.group_name like '%"+groupName+"%' ";
		}
		if(!StringUtils.isBlank(author)) {
			sql += " and t.author like '"+author+"'";
		}
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t.op_time <= '"+endTime+"'";
		}	
		return sql+" order by op_time desc,t.id desc";
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
		String sql = "delete from dim_ia_keyword_group where id=#{id}";
		return sql;
	}
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	public String deleteVoByPrimaryKey(KeywordGroup vo) {
		String sql = "delete from dim_ia_keyword_group where group_type=#{groupType}";
		return sql;
	}
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	public String selectVoByPrimaryKey(KeywordGroup vo) {
		String sql = "select * from dim_ia_keyword_group where group_type=#{groupType}";
		return sql;
	}
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	public String insertVo(KeywordGroup vo) {
		String sql = "insert into dim_ia_keyword_group("
				+ "	id,"
				+ "	group_type,"
				+ "	group_name,"
				+ "	group_level ,"
				+ "	author,"
				+ "	op_time)"
				+ "values("
				+ "	#{id}," 
				+ "	#{groupType}," 
				+ "	#{groupName}," 
				+ "	#{groupLevel}," 
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
            sb.append("insert into dim_ia_keyword_group ");  
            sb.append("(id,group_type, group_name,group_level,author,op_time) ");  
            sb.append("values ");  
            MessageFormat mf = new MessageFormat(
            		  "(#'{'list[{0}].id},"
            		+ "#'{'list[{0}].groupType},"
            		+ "#'{'list[{0}].groupName},"
            		+ "#'{'list[{0}].groupLevel},"
            		+ "#'{'list[{0}].author},"
            		+ "#'{'list[{0}].opTime})");  
            for (int i = 0; i < voList.size(); i++) {  
                sb.append(mf.format(new Integer[]{i}));
                if (i < voList.size() - 1) {
                    sb.append(",");
                }
            }
        }else if("oracle".equals(database_type)){
			String str = " into dim_ia_keyword_group (group_type,group_name,group_level,author,op_time,id) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				KeywordGroup vo = (KeywordGroup) operationFlow;
				String groupType=vo.getGroupType();//分组编码group_type
				String groupName=vo.getGroupName();//分组名称group_name
				String groupLevel=vo.getGroupLevel();//分组级别group_level
				String author=vo.getAuthor();//操作人
				String opTime=vo.getOpTime(); //操作时间
				String id = vo.getId();
				
				sql += str+"('"+
						groupType+"','"+groupName+"','"+groupLevel+"','"+author+"','"+opTime+"','"+id
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
	public String updateVo(KeywordGroup vo) {
		String sql = "update dim_ia_keyword_group set "
				+"group_type=#{groupType},"
				+"group_name=#{groupName},"
				+"group_level=#{groupLevel},"
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
	public String selectCheck(KeywordGroup vo) {
		String sql = " select count(*) from dim_ia_keyword_group t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.id != #{id} ";
		}
		if(!StringUtils.isBlank(vo.getGroupType())) {
			sql += " and t.group_type = #{groupType} ";
		}
		return sql;
	}
	
	/**
	 * 根据分组编码模糊查询
	 * @param prodName
	 * @return
	 */
	public String getGroupTypeListByGroupType(String groupType){
		String sql = " select * from dim_ia_keyword_group t where 1=1 ";
		if(!StringUtils.isBlank(groupType)) {
			sql += " and t.group_type like '%"+groupType+"%' ";
		}
		return sql;
	}
	
	/**
	 * 根据分组名称模糊查询
	 * @param prodName
	 * @return
	 */
	public String getGroupTypeListByGroupName(String groupName){
		String sql = " select * from dim_ia_keyword_group t where 1=1 ";
		if(!StringUtils.isBlank(groupName)) {
			sql += " and t.group_name like '%"+groupName+"%' ";
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
				+ "	t.group_type groupType," 
				+ "	t.group_name groupName," 
				+ "	t.group_level groupLevel," 
				+ "	t.author author,"
				+ "	t.op_time opTime"
				+ " from "
				+ " dim_ia_keyword_group t"
				+ " where 1=1 ";
		return sql;
	}
}
