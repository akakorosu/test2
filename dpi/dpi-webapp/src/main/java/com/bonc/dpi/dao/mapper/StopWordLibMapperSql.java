package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.StopWordLib;
import com.bonc.dpi.utils.DpiUtils;

/**
 * 分类词库
 * dim_ia_stop_word_lib
 * @author BONC-XUXL
 *
 */
public class StopWordLibMapperSql {

	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	public String selectDataNum(StopWordLib vo) {
		String stopWord = vo.getStopWord();
		String opTime =vo.getOpTime();
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
		String sql = " select count(*) from dim_ia_stop_word_lib t where 1=1 ";
		if(!StringUtils.isBlank(stopWord)) {
			sql += " and t.stop_word like '%"+stopWord+"%'";
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
	public String selectList(StopWordLib vo) {
		String stopWord = vo.getStopWord();
		String opTime =vo.getOpTime();
		String sql = this.getSql();
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
		if(!StringUtils.isBlank(stopWord)) {
			sql += " and t.stop_word like '%"+stopWord+"%'";
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
		return sql+" order by t.op_time desc,t.id desc";
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
		String sql = "delete from dim_ia_stop_word_lib where id=#{id}";
		return sql;
	}
	
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	public String deleteVoByPrimaryKey(StopWordLib vo) {
		String stopWord=vo.getStopWord();//stop_word,分类关键词
		String sql = "delete from dim_ia_stop_word_lib where stop_word ='"+stopWord+"'";
		return sql;
	}
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	public String selectVoByPrimaryKey(StopWordLib vo) {
		String stopWord=vo.getStopWord();//stop_word,分类关键词
		String sql = "select * from dim_ia_stop_word_lib where stop_word ='"+stopWord+"'";
		return sql;
	}
	
	
	/**
	 * 联合主键校验
	 * @param vo
	 * @return
	 */
	public String selectCheck(StopWordLib vo) {
		String sql = " select count(*) from dim_ia_stop_word_lib t where 1=1 ";
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.id != #{id} ";
		}		
		if(!StringUtils.isBlank(vo.getStopWord())) {
			sql += " and t.stop_word = #{stopWord} ";
		}
		return sql;
	}
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	public String insertVo(StopWordLib vo) {
		String sql = "insert into dim_ia_stop_word_lib("
				+ "	id,"
				+ "	stop_word,"
				+ "	author,"
				+ "	op_time)"
				+ "values("
				+ "	#{id}," 
				+ "	#{stopWord}," 
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
            sb.append("insert into dim_ia_stop_word_lib ");  
            sb.append("(id,stop_word,author,op_time) ");
            sb.append("values ");
            MessageFormat mf = new MessageFormat(
            		  "(#'{'list[{0}].id},"
            		+ "#'{'list[{0}].stopWord},"
            		+ "#'{'list[{0}].author},"
            		+ "#'{'list[{0}].opTime})");
            for (int i = 0; i < voList.size(); i++) {
                sb.append(mf.format(new Integer[]{i}));
                if (i < voList.size() - 1) {
                    sb.append(",");
                }  
            }
        }else if("oracle".equals(database_type)){
			String str = " into dim_ia_stop_word_lib (stop_word,author,op_time,id) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				StopWordLib vo = (StopWordLib) operationFlow;
				String stopWord=vo.getStopWord();//stop_word,分类关键词
				String author=vo.getAuthor();//作者
				String opTime=vo.getOpTime();//op_time,操作时间
				String id = vo.getId();
				
				sql += str+"('"+
						stopWord+"','"+author+"','"+opTime+"','"+id
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
	public String updateVo(StopWordLib vo) {
		String sql = "update dim_ia_stop_word_lib set "
				+"stop_word=#{stopWord},"
				+"author=#{author},"
				+"op_time=#{opTime}"
				+"  where id=#{id}";
		return sql;
	}
	
	/**
	 * 基础sql语句
	 * @return
	 */
	private String getSql() {
		String sql = "select "
				+ "	t.id id,"
				+ "	t.stop_word stopWord," 
				+ "	t.author author,"
				+ "	t.op_time opTime"
				+ " from "
				+ " dim_ia_stop_word_lib t"
				+ " where 1=1 ";
		return sql;
	}
}
