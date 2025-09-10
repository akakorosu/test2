package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bonc.dpi.dao.entity.CataWordLtb;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.utils.DpiUtils;

/**
 * 分类词库
 * dim_ia_cata_word_lib
 * @author BONC-XUXL
 *
 */
public class CataWordLtbMapperSql {

	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制
	
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	public String selectDataNum(CataWordLtb vo) {
		String cataLabel = vo.getCataLabel();
		String cataWord = vo.getCataWord();
		String opTime =vo.getOpTime();
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
//		String sql = this.getSql();
		String sql = "select count(*) from dim_ia_cata_word_lib t where 1=1";
		
		sql = sql+" and t.cata_label in (select t2.label_code as cata_label from dim_ia_product_label t2) ";
		if(!StringUtils.isBlank(cataLabel)) {
			sql += " and t.cata_label like '%"+cataLabel+"%'";
		}
		if(!StringUtils.isBlank(cataWord)) {
			sql += " and t.cata_word like '%"+cataWord+"%'";
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
	public String selectList(CataWordLtb vo) {
		String cataLabel = vo.getCataLabel();
		String cataWord = vo.getCataWord();
		String opTime =vo.getOpTime();
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
		String sql = this.getSql();
		
		sql = sql+" and t.cata_label in (select t2.label_code as cata_label from dim_ia_product_label t2) ";
		if(!StringUtils.isBlank(cataLabel)) {
			sql += " and t.cata_label like '%"+cataLabel+"%'";
		}
		if(!StringUtils.isBlank(cataWord)) {
			sql += " and t.cata_word like '%"+cataWord+"%'";
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
		String sql = "delete from dim_ia_cata_word_lib where id=#{id}";
		return sql;
	}
	
	/**
	 * 删除(根据主键)
	 * @param id
	 * @return
	 */
	public String deleteVoByPrimaryKey(CataWordLtb vo) {
		String cataLabel =  vo.getCataLabel();
		String cataWord =  vo.getCataWord();
		String sql = "delete from dim_ia_cata_word_lib where cata_label='"+cataLabel+"' and cata_word = '"+cataWord+"'";
		return sql;
	}
	
	/**
	 * 查询(根据主键)
	 * @param id
	 * @return
	 */
	public String selectVoByPrimaryKey(CataWordLtb vo) {
		String cataLabel =  vo.getCataLabel();
		String cataWord =  vo.getCataWord();
		String sql = "select * from dim_ia_cata_word_lib where cata_label='"+cataLabel+"' and cata_word = '"+cataWord+"'";
		return sql;
	}
	
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	public String insertVo(CataWordLtb vo) {
		String sql = "insert into dim_ia_cata_word_lib("
				+ "	id,"
				+ "	cata_label,"
				+ "	cata_word,"
				+ "	author,"
				+ "	op_time)"
				+ "values("
				+ "	#{id}," 
				+ "	#{cataLabel}," 
				+ "	#{cataWord}," 
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
            sb.append("insert into dim_ia_cata_word_lib ");
            sb.append("(id,cata_label,cata_word,author,op_time) ");
            sb.append("values ");
            MessageFormat mf = new MessageFormat(
            		  "(#'{'list[{0}].id},"
            		+ "#'{'list[{0}].cataLabel},"
            		+ "#'{'list[{0}].cataWord},"
            		+ "#'{'list[{0}].author},"
            		+ "#'{'list[{0}].opTime})");  
            for (int i = 0; i < voList.size(); i++) {
                sb.append(mf.format(new Integer[]{i}));
                if (i < voList.size() - 1) {  
                    sb.append(",");
                }  
            }  
        }else if("oracle".equals(database_type)){
			String str = " into dim_ia_cata_word_lib (cata_label,cata_word,author,op_time,id) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				CataWordLtb vo = (CataWordLtb) operationFlow;
				String cataLabel=vo.getCataLabel();//cata_label,分类标签
				String cataWord=vo.getCataWord();//cata_word,分类关键词
				String author=vo.getAuthor();//作者
				String opTime=vo.getOpTime();//op_time,操作时间
				String id = vo.getId();
				
				sql += str+"('"+
						cataLabel+"','"+cataWord+"','"+author+"','"+opTime+"','"+id
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
	public String updateVo(CataWordLtb vo) {
		String sql = "update dim_ia_cata_word_lib set "
				+"cata_label=#{cataLabel},"
				+"cata_word=#{cataWord},"
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
	public String selectCheck(CataWordLtb vo) {
		String sql = " select count(*) from dim_ia_cata_word_lib t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.id != #{id} ";
		}
		if(!StringUtils.isBlank(vo.getCataWord())) {
			sql += " and t.cata_label = #{cataLabel} ";
		}
		if(!StringUtils.isBlank(vo.getCataWord())) {
			sql += " and t.cata_word = #{cataWord} ";
		}
		return sql;
	}
	
	/**
	 * 基础sql语句
	 * @return
	 */
	private String getSql() {
		String sql = "select "
				+ "	t.cata_label cataLabel," 
				+ "	t.cata_word cataWord," 
				+ "	t.author author,"
				+ "	t.op_time opTime,"
				+ "	t.id id"
				+ " from "
				+ " dim_ia_cata_word_lib t"
				+ " where 1=1 ";
		return sql;
	}
}
