package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.GroupClass;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.SegWordLtb;
import com.bonc.dpi.utils.DpiUtils;


public class SegWordLtbMapperSql {
	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制
	/**
	 * 查询数量
	 * @param vo
	 * @return
	 */
	public String selectDataNum(SegWordLtb vo) {
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
		String sql = "select count(*) from dim_ia_seg_word_lib t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getSegWord())) {
			vo.setSegWord("%" + vo.getSegWord() + "%");
			sql += " and t.SEG_WORD like #{segWord}";
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
	public String selectList(SegWordLtb vo) {
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
		String sql = this.getSql();
		
		if(!StringUtils.isBlank(vo.getSegWord())) {
			vo.setSegWord("%" + vo.getSegWord() + "%");
			sql += " and t.SEG_WORD like #{segWord}";
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
		sql+="order by OP_TIME desc,t.ID desc";
		return sql;
	}

	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public String selectVoById(String id) {
		String sql = this.getSql() + " and ID=#{id} ";
		return sql;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public String deleteVoById(String id) {
		String sql = "delete from dim_ia_seg_word_lib where ID=#{id}";
		return sql;
	}
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	public String insertVo(SegWordLtb vo) {
		String sql = "insert into dim_ia_seg_word_lib("
				+ "	ID,"
				+ "	SEG_WORD,"
				+ "	AUTHOR,"
				+ "	OP_TIME)"
				+ "values("
				+ "	#{id}," 
				+ "	#{segWord}," 
				+ "	#{author}," 
				+ "	#{opTime})";
		return sql;
	}
	
	
	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	public String updateVo(SegWordLtb vo) {
		String sql = "update dim_ia_seg_word_lib set "
				+"SEG_WORD=#{segWord},"
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
	public String selectCheck(SegWordLtb vo) {
		String sql = " select count(*) from dim_ia_seg_word_lib t where 1=1 ";
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.ID != #{id} ";
		}		
		if(!StringUtils.isBlank(vo.getSegWord())) {
			sql += " and t.SEG_WORD = #{segWord} ";
		}
		return sql;
	}
	
	/**
	 * 基础sql语句
	 * @return
	 */
	private String getSql() {
		String sql = "select "
				+ "	t.ID id,"     //查出来不用的话会导致前台点击时 串行
				+ "	t.SEG_WORD segWord," 
				+ "	t.AUTHOR author," 
				+ "	t.OP_TIME opTime"
				+ " from "
				+ " dim_ia_seg_word_lib t"
				+ " where 1=1 ";
		return sql;
	}
	
	/**
	 * 查询VO(根据主键)
	 * @param id
	 * @return
	 */
	public String selectVoByPrimaryKey(SegWordLtb vo) {
		String serWord=vo.getSegWord();
		String sql = this.getSql();
		sql = sql+" and t.SEG_WORD = '"+serWord+"'";
		return sql;
	}
	
	/**
	 * 删除VO(根据主键)
	 * @param id
	 * @return
	 */
	public String deleteVoByPrimaryKey(SegWordLtb vo) {
		String serWord=vo.getSegWord();
		String sql = "";
		sql = "delete from dim_ia_seg_word_lib where SEG_WORD = '"+serWord+"'";		
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
        	sb.append("insert into dim_ia_seg_word_lib ");  
            sb.append("(ID,SEG_WORD,AUTHOR,OP_TIME) ");  
            sb.append("values ");  
            MessageFormat mf = new MessageFormat(
            		  "(#'{'list[{0}].id},"
            		+ "#'{'list[{0}].segWord},"
            		+ "#'{'list[{0}].author},"
            		+ "#'{'list[{0}].opTime})");  
            for (int i = 0; i < voList.size(); i++) {  
                sb.append(mf.format(new Integer[]{i}));  
                if (i < voList.size() - 1) {  
                    sb.append(",");  
                }  
            }
        }else if("oracle".equals(database_type)){
			String str = " into dim_ia_seg_word_lib (ID,SEG_WORD,AUTHOR,OP_TIME) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				SegWordLtb vo = (SegWordLtb) operationFlow;			
				sql += str+"('"+
						vo.getId()+"','"+vo.getSegWord()+"','"+vo.getAuthor()+"','"+vo.getOpTime()
						+"')";
			}
			sb.append(sql+" select 1 from dual");
        }
        return sb.toString();  
    }  
}
