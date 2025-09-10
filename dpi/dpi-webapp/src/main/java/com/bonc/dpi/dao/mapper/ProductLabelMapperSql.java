package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bonc.dpi.dao.entity.DomainRule;
import com.bonc.dpi.dao.entity.GroupClass;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.ProductLabel;
import com.bonc.dpi.utils.DpiUtils;


public class ProductLabelMapperSql {
	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制
	
	public String selectListDistinct() {
		String sql = "SELECT DISTINCT(LABEL_CODE1) labelCode1, label_name1 labelName1 FROM dim_ia_product_label";
		return sql;
	}
	
	/**
	 * 查询数量
	 * @param vo
	 * @return
	 */
	public String selectDataNum(ProductLabel vo) {
		String startTime =vo.getStartTime();
		String endTime =vo.getEndTime();
		String sql = "select count(*) from dim_ia_product_label t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getLabelName())) {
			vo.setLabelName("%" + vo.getLabelName() + "%");
			sql += " and t.LABEL_NAME like #{labelName} ";
		}
		if(!StringUtils.isBlank(vo.getLabelName1())) {
			vo.setLabelName1("%" + vo.getLabelName1() + "%");
			sql += " and t.LABEL_NAME1 like #{labelName1} ";
		}
		if(!StringUtils.isBlank(vo.getLabelName2())) {
			vo.setLabelName2("%" + vo.getLabelName2() + "%");
			sql += " and t.LABEL_NAME2 like #{labelName2} ";
		}
		if(!StringUtils.isBlank(vo.getLabelCode1())) {
			vo.setLabelCode1("%" + vo.getLabelCode1() + "%");
			sql += " and t.label_code2 like #{labelCode1} ";
		}
		if(!StringUtils.isBlank(vo.getLabelCode())) {
			vo.setLabelCode("%" + vo.getLabelCode() + "%");
			sql += " and t.label_code like #{labelCode} ";
		}
		if(!StringUtils.isBlank(vo.getLabelNameLike())) {
			vo.setLabelNameLike("%" + vo.getLabelNameLike() + "%");
			sql += " and (t.label_name1 like #{labelNameLike} or t.label_name2 like #{labelNameLike})";
		}
		if(!StringUtils.isBlank(vo.getAuthor())) {
			vo.setAuthor("%" + vo.getAuthor() + "%");
			sql += " and t.author like #{author} ";
		}
		/*if(!StringUtils.isBlank(vo.getOpTime())) {
			sql += " and t.OP_TIME=#{opTime} ";
		}*/
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
	public String selectList(ProductLabel vo) {
		String sql = this.getSql();
		String startTime =vo.getStartTime();
		String endTime =vo.getEndTime();
		if(!StringUtils.isBlank(vo.getLabelName())) {
			vo.setLabelName("%" + vo.getLabelName() + "%");
			sql += " and t.LABEL_NAME like #{labelName} ";
		}
		if(!StringUtils.isBlank(vo.getLabelName1())) {
			vo.setLabelName1("%" + vo.getLabelName1() + "%");
			sql += " and t.LABEL_NAME1 like #{labelName1} ";
		}
		if(!StringUtils.isBlank(vo.getLabelName2())) {
			vo.setLabelName2("%" + vo.getLabelName2() + "%");
			sql += " and t.LABEL_NAME2 like #{labelName2} ";
		}
		if(!StringUtils.isBlank(vo.getLabelCode1())) {
			vo.setLabelCode1("%" + vo.getLabelCode1() + "%");
			sql += " and t.label_code2 like #{labelCode1} ";
		}
		if(!StringUtils.isBlank(vo.getLabelCode())) {
			vo.setLabelCode("%" + vo.getLabelCode() + "%");
			sql += " and t.label_code like #{labelCode} ";
		}
		if(!StringUtils.isBlank(vo.getAuthor())) {
			vo.setAuthor("%" + vo.getAuthor() + "%");
			sql += " and t.author like #{author} ";
		}
		if(!StringUtils.isBlank(vo.getLabelNameLike())) {
			vo.setLabelNameLike("%" + vo.getLabelNameLike() + "%");
			sql += " and (t.label_name1 like #{labelNameLike} or t.label_name2 like #{labelNameLike})";
		}
		/*if(!StringUtils.isBlank(vo.getOpTime())) {
			sql += " and t.OP_TIME=#{opTime} ";
		}*/
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t.op_time <= '"+endTime+"'";
		}
		sql+="order by OP_TIME desc,t.label_code desc";
		return sql;
	}

	/**
	 * 根据labelCode查找
	 * @param id
	 * @return
	 */
	public String selectVoByLabelCode(String labelCode) {
		String sql = this.getSql() + " and label_code=#{labelCode}";
		return sql;
	}
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public String selectVoById(String labelCode) {
		String sql = this.getSql() + " and id=#{id} ";
		return sql;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public String deleteVoById(ProductLabel vo) {
		String sql = "delete from dim_ia_product_label where id=#{id}";
		return sql;
	}
	
	public String deleteVoByContentLabelName(ProductLabel vo) {
		String sql = "delete from dim_ia_product_label where LABEL_CODE1=#{labelCode1}";
		return sql;
	}
	
	public String checkData(ProductLabel vo) {
		String sql = "select count(*) from dim_ia_product_label where LABEL_TYPE=#{lableType} and LABEL_CODE=#{labelCode} and JOIN_LABEL_NAME=#{joinLabelName}";
		return sql;
	}
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	public String insertVo(ProductLabel vo) {
		String sql = "insert into dim_ia_product_label("
				+ "	ID,"
				+ "	LABEL_TYPE,"
				+ "	LABEL_NAME,"
				+ "	LABEL_CODE1 ,"
				+ "	LABEL_NAME1,"
				+ "	LABEL_CODE2,"
				+ "	LABEL_NAME2,"
				+ "	LABEL_CODE,"
				+ "	JOIN_LABEL_NAME,"
				+ "	AUTHOR,"
				+ "	OP_TIME)"
				+ "values("
				+ "	#{id}," 
				+ "	#{lableType}," 
				+ "	#{labelName}," 
				+ "	#{labelCode1}," 
				+ "	#{labelName1Param}," 
				+ "	#{labelCode2}," 
				+ "	#{labelName2Param}," 
				+ "	#{labelCode}," 
				+ "	#{joinLabelName},"
				+ "	#{author}," 
				+ "	#{opTime})";
		return sql;
	}
	
	
	
	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	public String updateVo(ProductLabel vo) {
		String sql = "update dim_ia_product_label set "
				+"LABEL_TYPE=#{lableType},"
				+"LABEL_NAME=#{labelName},"
				+"LABEL_CODE1=#{labelCode1},"
				+"LABEL_NAME1=#{labelName1Param},"
				+"LABEL_CODE2=#{labelCode2},"
				+"LABEL_NAME2=#{labelName2Param},"
				+"LABEL_CODE=#{labelCode},"
				+"JOIN_LABEL_NAME=#{joinLabelName},"
				+"AUTHOR=#{author},"
				+"OP_TIME=#{opTime}"
				+"  where ID=#{id}";
		return sql;
	}
	
	public String updateContentLabelName(ProductLabel vo) {
		String sql = "update dim_ia_product_label set "
				+"LABEL_NAME1=#{labelName1Param} ,"
				+"JOIN_LABEL_NAME=#{labelName1Param} "
				+"  where LABEL_NAME1=#{joinLabelName}";
		return sql;
	}
	
	
	public String selectLabelName1(ProductLabel vo) {
		String sql = " select * from dim_ia_product_label t where 1=1 ";	
		if(!StringUtils.isBlank(vo.getLabelName1Param())) {
			sql += " and t.LABEL_NAME1 = #{labelName1Param} ";
		}
		sql += " and t.LABEL_NAME2 is null ";
		return sql;
	}

	public String selectMax(ProductLabel vo) {
		String sql="";
		if("mysql".equals(database_type)){
			sql="select max(substring(LABEL_CODE2,7))  from dim_ia_product_label  where 1=1 ";
			if(!StringUtils.isBlank(vo.getLabelCode1())) {
				sql += " and LABEL_CODE1 = #{labelCode1} ";
			}
		}else{
			sql="select max(substr(LABEL_CODE2,7))  from dim_ia_product_label  where 1=1 ";
			if(!StringUtils.isBlank(vo.getLabelCode1())) {
				sql += " and LABEL_CODE1 = #{labelCode1} ";
			}

		}
		return sql;
	}

	public String selectLetter(String letter) {
		String sql="";
		if("mysql".equals(database_type)){
			sql="select  max(DISTINCT(substring(LABEL_CODE1,2,1))) from DIM_IA_PRODUCT_LABEL  where LABEL_CODE1 like #{letter} ";
		}else{
			sql="select  max(DISTINCT(substr(LABEL_CODE1,2,1))) from DIM_IA_PRODUCT_LABEL  where LABEL_CODE1 like #{letter} ";

		}

		return sql;
	}



	public String checkLabelCode(ProductLabel vo) {
		String sql = " select count(*) from dim_ia_product_label t where  1=1 ";
		if(!StringUtils.isBlank(vo.getLabelCode1())) {
			sql += " and t.LABEL_CODE1 = #{labelCode1} ";
		}
		if(!StringUtils.isBlank(vo.getLabelCode2())) {
			sql += " and t.LABEL_CODE2 = #{labelCode2} ";
		}
		return sql;
	}
	
	/**
	 * 联合主键校验
	 * @param vo
	 * @return
	 */
	public String selectCheck(ProductLabel vo) {
		String sql = " select count(*) from dim_ia_product_label t where 1=1 ";
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.ID != #{id} ";
		}		
		if(!StringUtils.isBlank(vo.getLabelName1Param())) {
			sql += " and t.LABEL_NAME1 = #{labelName1Param} ";
		}
		if(!StringUtils.isBlank(vo.getLabelName2Param())) {
			sql += " and t.LABEL_NAME2 = #{labelName2Param} ";
		}
		return sql;
	}
	
	public String selectCheckLabel1(ProductLabel vo) {
		String sql = " select * from dim_ia_product_label t where 1=1 ";
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.ID != #{id} ";
		}		
		if(!StringUtils.isBlank(vo.getLabelName1Param())) {
			sql += " and t.LABEL_NAME1 = #{labelName1Param} ";
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
				+ "	t.LABEL_TYPE lableType,"
				+ "	t.LABEL_NAME labelName," 
				+ "	t.LABEL_CODE1 labelCode1," 
				+ "	t.LABEL_NAME1 labelName1,"
				+ "	t.LABEL_CODE2 labelCode2," 
				+ "	t.LABEL_NAME2 labelName2," 
				+ "	t.LABEL_CODE labelCode," 
				+ "	t.JOIN_LABEL_NAME joinLabelName," 
				+ "	t.AUTHOR author," 
				+ "	t.OP_TIME opTime" 
				+ " from "
				+ " dim_ia_product_label t"
				+ " where 1=1 ";
		return sql;
	}
	/**
	 * 查询VO(根据主键)
	 * @param id
	 * @return
	 */
	public String selectVoByPrimaryKey(ProductLabel vo) {
		String labelCode=vo.getLabelCode();
		String sql = this.getSql();
		sql = sql+" and t.LABEL_CODE = '"+labelCode+"'";
		return sql;
	}
	
	/**
	 * 删除VO(根据主键)
	 * @param id
	 * @return
	 */
	public String deleteVoByPrimaryKey(ProductLabel vo) {
		String labelCode=vo.getLabelCode();
		String sql = "";
		sql = "delete from dim_ia_product_label where LABEL_CODE = '"+labelCode+"'";		
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
        	 sb.append("insert into dim_ia_product_label ");  
             sb.append("(ID,LABEL_TYPE,LABEL_NAME,LABEL_CODE1,LABEL_NAME1,LABEL_CODE2,LABEL_NAME2,LABEL_CODE,JOIN_LABEL_NAME,AUTHOR,OP_TIME) ");  
             sb.append("values ");  
             MessageFormat mf = new MessageFormat(
             		  "(#'{'list[{0}].id},"
             		+ "#'{'list[{0}].lableType},"
             		+ "#'{'list[{0}].labelName},"
             		+ "#'{'list[{0}].labelCode1},"
             		+ "#'{'list[{0}].labelName1},"
             		+ "#'{'list[{0}].labelCode2},"
             		+ "#'{'list[{0}].labelName2},"
             		+ "#'{'list[{0}].labelCode},"
             		+ "#'{'list[{0}].joinLabelName},"
             		+ "#'{'list[{0}].author},"
             		+ "#'{'list[{0}].opTime})");  
             for (int i = 0; i < voList.size(); i++) {  
                 sb.append(mf.format(new Integer[]{i}));  
                 if (i < voList.size() - 1) {  
                     sb.append(",");  
                 }  
             }
        }else if("oracle".equals(database_type)){
			String str = " into dim_ia_product_label (ID,LABEL_TYPE,LABEL_NAME,LABEL_CODE1,LABEL_NAME1,LABEL_CODE2,LABEL_NAME2,LABEL_CODE,JOIN_LABEL_NAME,AUTHOR,OP_TIME) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				ProductLabel vo = (ProductLabel) operationFlow;
				sql += str+"('"+
						vo.getId()+"','"+vo.getLableType()+"','"+vo.getLabelName()+"','"+vo.getLabelCode1()+"','"+vo.getLabelName1()
						+"','"+vo.getLabelCode2()+"','"+vo.getLabelName2()+"','"+vo.getLabelCode()+"','"+vo.getJoinLabelName()+"','"+vo.getAuthor()+"','"+vo.getOpTime()
						+"')";
			}
			sb.append(sql+" select 1 from dual");
        }
        return sb.toString();  
    }
}
