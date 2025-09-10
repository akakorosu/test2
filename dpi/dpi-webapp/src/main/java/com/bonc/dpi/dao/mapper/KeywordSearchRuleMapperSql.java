package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bonc.dpi.dao.entity.KeywordSearchRule;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.utils.DpiUtils;

/**
 * 关键字规则sql
 * dim_ia_keyword_search_rule
 * @author BONC-XUXL
 *
 */
public class KeywordSearchRuleMapperSql {
	
	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制
	
	public String selectListSimple(KeywordSearchRule vo) {
		String sql = this.getSql();
		if(!StringUtils.isBlank(vo.getChannelName())) {
			sql += " and t.channel_name like CONCAT(CONCAT('%', #{channelName}), '%')";
		}
		if(!StringUtils.isBlank(vo.getNoRuleType())) {
			sql += " and t.rule_type != #{noRuleType}";
		}
		return sql;
	}
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	public String selectDataNum(KeywordSearchRule vo) {
		String num = vo.getNum();
		String host = vo.getHost();
		String prodName = vo.getProdName();
		String channelName = vo.getChannelName();
		String groupName = vo.getGroupName();
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
		String batchFlag=vo.getBatchFlag();
		
		String sql = " select count(t1.id) from dim_ia_keyword_search_rule t1 where 1=1 ";

		if(!StringUtils.isBlank(num)) {
			sql += " and t1.num like '%"+num+"%' ";
		}
		if(!StringUtils.isBlank(host)) {
			sql += " and t1.host like '%"+host+"%' ";
		}
		if(!StringUtils.isBlank(prodName)) {
			sql += " and t1.prod_id in (select t2.prod_id from dim_ia_product_info t2 where ";
			//多条件查询
			if(prodName.contains("|")&&batchFlag.equals("0")){
				if("mysql".equals(database_type)){
					sql += "t2.prod_name regexp '"+prodName+"'";
				}else if("oracle".equals(database_type)){
					sql += " REGEXP_LIKE (t2.prod_name ,'("+prodName+")')";
				}
			}else if(prodName.contains("|")&&batchFlag.equals("1")){
				String []a=prodName.split("\\|");
				sql += " (";
				for(int i=0;i<a.length;i++){
					if(i!=a.length-1){
						sql += "  t2.prod_name = '"+a[i]+"' or ";
					}else{
						sql += "  t2.prod_name = '"+a[i]+"' ";
					}
				}
				sql += " ) ";
			}
			//单条件查询
			else{
				if(batchFlag.equals("0")&&!prodName.contains("|")){
					sql += " t2.prod_name like '%"+prodName+"%'";
				}
				if(batchFlag.equals("1")&&!prodName.contains("|")){
					sql += " t2.prod_name = '"+prodName+"'";
				}
							
			}
		
			sql += " )";
		}else{
			sql += " and t1.prod_id in (select t2.prod_id from dim_ia_product_info t2) " ;
		}
		if(!StringUtils.isBlank(channelName)) {
			sql += " and t1.channel_name like '%"+channelName+"%' ";
		}
		if(!StringUtils.isBlank(groupName)) {
			sql += " and t1.group_type in(select t3.group_type from dim_ia_keyword_group t3 where t3.group_name like '%"+groupName+"%') ";
		}else{
			sql += " and t1.group_type in(select t3.group_type from dim_ia_keyword_group t3 )";
		}
		if(!StringUtils.isBlank(author)) {
			sql += " and t1.author like '"+author+"'";
		}
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t1.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t1.op_time <= '"+endTime+"'";
		}		
		return sql;
	}
	
	
	/**
	 * 查询列表
	 * @param vo
	 * @return
	 */
	public String selectList(KeywordSearchRule vo) {
		String num = vo.getNum();
		String host = vo.getHost();
		String prodName = vo.getProdName();
		String channelName = vo.getChannelName();
		String groupName = vo.getGroupName();
		String author=vo.getAuthor();
		String startTime=vo.getStartTime();
		String endTime=vo.getEndTime();
		String batchFlag=vo.getBatchFlag();
//		String sql = this.getSql();
		String sql = "select "
				+ "	t1.host host," 
				+ "	t1.match_type matchType," 
				+ "	t1.prod_id prodid,"
				+ "	t1.rule_type ruleType,"
				+ "	t1.channel_name channelName,"
				+ "	t1.parse_rule_andriod parseRuleAndriod,"
				+ "	t1.get_index_andriod getIndexAndriod,"
				+ "	t1.parse_rule_ios parseRuleIos,"
				+ "	t1.get_index_ios getIndexIos,"
				+ "	t1.group_type groupType,"
				+ "	t1.content_label_code contentLabelCode,"
				+ "	t1.attr_id attrId,"
				+ "	t1.author author,"
				+ "	t1.op_time opTime,"
				+ " t1.num num,"
				+ "	t1.is_valid isValid,"
				+ " t1.bak bak,"
				//+ " t1.parse_rule_add parseRuleAdd,"
				//+ " t1.get_index_add getIndexAdd,"
				+ " t1.url_example urlExample,"
				+ "	t1.id id ";
//		 sql += " from dim_ia_keyword_search_rule t1, dim_ia_product_info t2, dim_ia_keyword_group t3 where t1.prod_id = t2.prod_id and t1.group_type = t3.group_type ";
		sql += " from dim_ia_keyword_search_rule t1 where 1=1 ";

		if(!StringUtils.isBlank(num)) {
			//sql += " and t1.num like '%"+num+"%' ";
			sql += " and t1.num = '"+num+"'";
		}
		if(!StringUtils.isBlank(host)) {
			sql += " and t1.host like '%"+host+"%' ";
		}
		if(!StringUtils.isBlank(prodName)) {
			sql += " and t1.prod_id in (select t2.prod_id from dim_ia_product_info t2 where ";
			//多条件查询
			if(prodName.contains("|")&&batchFlag.equals("0")){
				if("mysql".equals(database_type)){
					sql += "t2.prod_name regexp '"+prodName+"'";
				}else if("oracle".equals(database_type)){
					sql += " REGEXP_LIKE (t2.prod_name ,'("+prodName+")')";
				}
			}else if(prodName.contains("|")&&batchFlag.equals("1")){
				String []a=prodName.split("\\|");
				sql += " (";
				for(int i=0;i<a.length;i++){
					if(i!=a.length-1){
						sql += "  t2.prod_name = '"+a[i]+"' or ";
					}else{
						sql += "  t2.prod_name = '"+a[i]+"' ";
					}
				}
				sql += " ) ";
			}
			//单条件查询
			else{
				if(batchFlag.equals("0")&&!prodName.contains("|")){
					sql += " t2.prod_name like '%"+prodName+"%'";
				}
				if(batchFlag.equals("1")&&!prodName.contains("|")){
					sql += " t2.prod_name = '"+prodName+"'";
				}
							
			}
		
			sql += " )";
		}else{
			sql += " and t1.prod_id in (select t2.prod_id from dim_ia_product_info t2) " ;
		}
		if(!StringUtils.isBlank(channelName)) {
			sql += " and t1.channel_name like '%"+channelName+"%' ";
		}
		if(!StringUtils.isBlank(groupName)) {
			sql += " and t1.group_type in(select t3.group_type from dim_ia_keyword_group t3 where t3.group_name like '%"+groupName+"%') ";
		}else{
			sql += " and t1.group_type in(select t3.group_type from dim_ia_keyword_group t3 )";
		}
		if(!StringUtils.isBlank(author)) {
			sql += " and t1.author like '"+author+"'";
		}
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t1.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t1.op_time <= '"+endTime+"'";
		}		
		return sql+" order by t1.op_time desc ,t1.host desc";
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
		String sql = "delete from dim_ia_keyword_search_rule where id=#{id}";
		return sql;
	}
	
	/**
	 * 删除(根据主键)
	 * @param vo
	 * @return
	 */
	public String deleteVoByPrimaryKey(KeywordSearchRule vo) {
		String host=vo.getHost();//域名
		String prodid=vo.getProdid();//产品ID
		String parseRuleAndriod=vo.getParseRuleAndriod();//安卓截取规则
//		String parseRuleIos=vo.getParseRuleIos();//苹果截取规则
//		String sql = "delete from dim_ia_keyword_search_rule where host='"+host+"'  and prod_id='"+prodid+"' and parse_rule_andriod='"+parseRuleAndriod+"' and parse_rule_ios='"+parseRuleIos+"'";
		String sql = "delete from dim_ia_keyword_search_rule where host='"+host+"'  and prod_id='"+prodid+"' and parse_rule_andriod='"+parseRuleAndriod+"'";
		return sql;
	}
	
	/**
	 * 查询(根据主键)
	 * @param vo
	 * @return
	 */
	public String selectVoByPrimaryKey(KeywordSearchRule vo) {
		String host=vo.getHost();//域名
		String prodid=vo.getProdid();//产品ID
		String parseRuleAndriod=vo.getParseRuleAndriod();//安卓截取规则
//		String parseRuleIos=vo.getParseRuleIos();//苹果截取规则
//		String sql = "select * from dim_ia_keyword_search_rule where host='"+host+"'  and prod_id='"+prodid+"' and parse_rule_andriod='"+parseRuleAndriod+"' and parse_rule_ios='"+parseRuleIos+"'";
		String sql = "select * from dim_ia_keyword_search_rule where host='"+host+"'  and prod_id='"+prodid+"' and parse_rule_andriod='"+parseRuleAndriod+"'";
		return sql;
	}
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	public String insertVo(KeywordSearchRule vo) {
		String sql = "insert into dim_ia_keyword_search_rule("
				+ "	id,"
				+ "	host,"
				+ "	match_type,"
				+ "	prod_id,"
				+ "	rule_type ,"
				+ "	channel_name,"
				+ "	parse_rule_andriod,"
				+ "	get_index_andriod,"
				+ "	parse_rule_ios,"
				+ "	get_index_ios,"
				+ "	group_type,"
				+ "	content_label_code,"
				+ "	attr_id,"
				+ "	author,"
				+ "	op_time,"
				+ "	num,"
				+ "	is_valid,"
				+ " bak,"
				//+ "	parse_rule_add,"
				//+ " get_index_add,"
				+ " url_example) "
				+ "values("
				+ "	#{id}," 
				+ "	#{hostParam},"
				+ "	#{matchType}," 
				+ "	#{prodid}," 
				+ " #{ruleType},"
				+ " #{channelName},"
				+ " #{parseRuleAndriod},"
				+ " #{getIndexAndriod},"
				+ " #{parseRuleIos},"
				+ " #{getIndexIos},"
				+ " #{groupType},"
				+ " #{contentLabelCode},"
				+ " #{attrId},"
				+ " #{author},"
				+ " #{opTime},"
				+ " #{num},"
				+ " #{isValid},"
				+ " #{bak},"
				//+ " #{parseRuleAdd},"
				//+ " #{getIndexAdd},"
				+ " #{urlExample})";
		return sql;
	}
	
	/**
	 * 批量插入
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insertVoPl(Map map) {
		map=(Map) map.get("map");
		List<OperationFlow> voList=(List<OperationFlow>) map.get("list");
		String type=(String) map.get("type");
		Integer maxNum=null;
		if("2".equals(type)){//2 为txt格式
			
		}else{
			maxNum=(Integer) map.get("maxNum")+1;
		}
	
		
        StringBuilder sb = new StringBuilder();
        if("mysql".equals(database_type)){
            sb.append("insert into dim_ia_keyword_search_rule ");
            sb.append("(id,host,match_type, prod_id,rule_type,"
            		+ "channel_name,parse_rule_andriod,get_index_andriod,parse_rule_ios,get_index_ios,"
            		+ "group_type,content_label_code,attr_id,"
            		+ "author,op_time,num,is_valid,bak,url_example) ");
            sb.append("values ");
            MessageFormat mf = new MessageFormat(
    	      		  "(#'{'voList[{0}].id},"
    	      		+ "#'{'voList[{0}].host},"
            		+ "#'{'voList[{0}].matchType},"
            		+ "#'{'voList[{0}].prodid},"
            		+ "#'{'voList[{0}].ruleType},"
            		+ "#'{'voList[{0}].channelName},"
            		+ "#'{'voList[{0}].parseRuleAndriod},"
            		+ "#'{'voList[{0}].getIndexAndriod},"
            		+ "#'{'voList[{0}].parseRuleIos},"
            		+ "#'{'voList[{0}].getIndexIos},"
            		+ "#'{'voList[{0}].groupType},"
            		+ "#'{'voList[{0}].contentLabelCode},"
            		+ "#'{'voList[{0}].attrId},"
            		+ "#'{'voList[{0}].author},"
            		+ "#'{'voList[{0}].opTime},"
            		+ "{0}+maxNum,"
            		+ "#'{'voList[{0}].isValid},"
            		+ "#'{'voList[{0}].bak},"
            		//+ "#'{'voList[{0}].parseRuleAdd},"
            		//+ "#'{'voList[{0}].getIndexAdd},"
            		+ "#'{'voList[{0}].urlExample})");
            for (int i = 0; i < voList.size(); i++) {
                //sb.append(mf.format(new Integer[]{i}));//
            	//String id=voList.get(i).get
                //后添加
                KeywordSearchRule vo = (KeywordSearchRule) voList.get(i);
                String id = vo.getId();
				String host=vo.getHost();//域名
				String matchType=vo.getMatchType();//匹配类型
				String prodid=vo.getProdid();//产品ID
				String ruleType=vo.getRuleType();//规则匹配类型
				
				String channelName=vo.getChannelName();//动作目录名称
				String parseRuleAndriod=vo.getParseRuleAndriod();//安卓截取规则
				parseRuleAndriod=parseRuleAndriod.replaceAll("\\\\", "\\\\\\\\");
				String getIndexAndriod=vo.getGetIndexAndriod();//获取安卓截取结果分组序号
				String parseRuleIos=vo.getParseRuleIos();//苹果截取规则
				String getIndexIos=vo.getGetIndexIos();//获取苹果截取结果分组序号
				
				String groupType=vo.getGroupType();//应用分组编码
				String contentLabelCode=vo.getContentLabelCode();//标签ID
				String attrId=vo.getAttrId();//属性ID
				String author=vo.getAuthor();//作者
				String opTime=vo.getOpTime();//操作时间
				
				String isValid=vo.getIsValid();//是否有效
				String num="";
				if("2".equals(type)){
					num=vo.getNum();//是否有效
				}else{
					 int a=maxNum++;
					 num= String.valueOf(a);
				}
				
				String bak = vo.getBak();
				//String parseRuleAdd = vo.getParseRuleAdd();
				//String getIndexAdd = vo.getGetIndexAdd();
				String urlExample = vo.getUrlExample();
								
				
				String  sql = "('"+
						id+"','"+host+"','"+matchType+"','"+prodid+"','"+ruleType
						+"','"+channelName+"','"+parseRuleAndriod+"','"+getIndexAndriod+"','"+parseRuleIos+"','"+getIndexIos
						+"','"+groupType+"','"+contentLabelCode+"','"+attrId+"','"+author+"','"+opTime
						+"','"+num+"','"+isValid+"','"+bak+"','"+urlExample
						+"')";
				//end
				sb.append(sql);
                if (i < voList.size() - 1) {
                    sb.append(",");
                }  
            }
        }else if("oracle".equals(database_type)){
			String str = " into dim_ia_keyword_search_rule (id,host,match_type, prod_id,rule_type,"
            		+ "channel_name,parse_rule_andriod,get_index_andriod,parse_rule_ios,get_index_ios,"
            		+ "group_type,content_label_code,attr_id,"
            		+ "author,op_time,num,is_valid,bak,url_example) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				KeywordSearchRule vo = (KeywordSearchRule) operationFlow;
				
				String id = vo.getId();
				String host=vo.getHost();//域名
				String matchType=vo.getMatchType();//匹配类型
				String prodid=vo.getProdid();//产品ID
				String ruleType=vo.getRuleType();//规则匹配类型
				
				String channelName=vo.getChannelName();//动作目录名称
				String parseRuleAndriod=vo.getParseRuleAndriod();//安卓截取规则
				String getIndexAndriod=vo.getGetIndexAndriod();//获取安卓截取结果分组序号
				String parseRuleIos=vo.getParseRuleIos();//苹果截取规则
				String getIndexIos=vo.getGetIndexIos();//获取苹果截取结果分组序号
				
				String groupType=vo.getGroupType();//应用分组编码
				String contentLabelCode=vo.getContentLabelCode();//标签ID
				String attrId=vo.getAttrId();//属性ID
				String author=vo.getAuthor();//作者
				String opTime=vo.getOpTime();//操作时间
				
				String isValid=vo.getIsValid();//是否有效
//				String num=vo.getNum();
				//Integer num=maxNum++;
				String num="";
				if("2".equals(type)){
					num=vo.getNum();//是否有效
				}else{
					 int a=maxNum++;
					 num= String.valueOf(a);
				}
				
				String bak = vo.getBak();
				//String parseRuleAdd = vo.getParseRuleAdd();
				//String getIndexAdd = vo.getGetIndexAdd();
				String urlExample = vo.getUrlExample();
				
				sql += str+"('"+
						id+"','"+host+"','"+matchType+"','"+prodid+"','"+ruleType
						+"','"+channelName+"','"+parseRuleAndriod+"','"+getIndexAndriod+"','"+parseRuleIos+"','"+getIndexIos
						+"','"+groupType+"','"+contentLabelCode+"','"+attrId+"','"+author+"','"+opTime
						+"','"+num+"','"+isValid+"','"+bak+"','"+urlExample
						+"')";
			}
			sb.append(sql+" select 1 from dual");       	
        }

        return sb.toString();
    }

	/**
	 * 查询num最大值
	 * @return
	 */
	public String selectMaxNum(){
		String sql="";
		 if("mysql".equals(database_type)){
			 sql=" select ifnull(max(num+0),0)  from dim_ia_keyword_search_rule ";
		 }
		 if("oracle".equals(database_type)){
			 sql=" select NVL(max(num+0),0)  from dim_ia_keyword_search_rule ";
		 }
		
		return sql;
	}

	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	public String updateVo(KeywordSearchRule vo) {
		String sql = "update dim_ia_keyword_search_rule set "
				+"host=#{hostParam},"
				+"match_type=#{matchType},"
				+"prod_id=#{prodid},"
				+"rule_type=#{ruleType},"
				+"channel_name=#{channelName},"
				+"parse_rule_andriod=#{parseRuleAndriod},"
				+"get_index_andriod=#{getIndexAndriod},"
				+"parse_rule_ios=#{parseRuleIos},"
				+"get_index_ios=#{getIndexIos},"
				+"group_type=#{groupType},"
				+"content_label_code=#{contentLabelCode},"
				+"attr_id=#{attrId},"
				+"author=#{author},"
				+"op_time=#{opTime},"
				+"is_valid=#{isValid},"
				+"num=#{num},"
				+ "bak = #{bak},"
				//+ "parse_rule_add = #{parseRuleAdd},"
				//+ "get_index_add = #{getIndexAdd},"
				+ "url_example = #{urlExample}"
				+"  where id=#{id}";
		return sql;
	}
	
	/**
	 * 联合主键校验
	 * @param vo
	 * @return
	 */
	public String checkRepeat(KeywordSearchRule vo) {
		String sql = " select count(*) from dim_ia_keyword_search_rule t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.id != #{id} ";
		}
		if(!StringUtils.isBlank(vo.getHostParam())) {
			sql += " and t.host = #{hostParam} ";
		}
		if(!StringUtils.isBlank(vo.getProdid())) {
			sql += " and t.prod_id = #{prodid} ";
		}
		if(!StringUtils.isBlank(vo.getParseRuleAndriod())) {
			sql += " and t.parse_rule_andriod = #{parseRuleAndriod} ";
		}
//		if(!StringUtils.isBlank(vo.getParseRuleIos())) {
//			sql += " and t.parse_rule_ios = #{parseRuleIos} ";
//		}
		if(!StringUtils.isBlank(vo.getNum())) {
			sql += " and t.num = #{num} ";
		}
		return sql;
	}
	
	
	/**
	 * 校验groupType在dim_ia_keyword_group表中
	 * @param vo
	 * @return
	 */
	public String checkGroupType(KeywordSearchRule vo) {
		String sql = " select count(*) from dim_ia_keyword_group t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getGroupType())) {
			sql += " and t.group_type = #{groupType} ";
		}
		return sql;
	}
	
	/**
	 * 校验groupName在dim_ia_keyword_group表中
	 * @param vo
	 * @return
	 */
	public String checkGroupName(KeywordSearchRule vo) {
		String sql = " select count(*) from dim_ia_keyword_group t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getGroupNameParam())) {
			sql += " and t.group_name = #{groupNameParam} ";
		}
		return sql;
	}
	
	/**
	 * 不可为空，prod_id必须在产品表里
	 * @param vo
	 * @return
	 */
	public String prodIdCheck(KeywordSearchRule vo) {
		
		String sql = " select count(*) from dim_ia_product_info t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getId())) {
			sql += " and t.id != #{id} ";
		}
		if(!StringUtils.isBlank(vo.getProdid())) {
			sql += " and t.prod_id = #{prodid} ";
		}
		return sql;
	}
	
	/**
	 * 根据rule_type统计个数
	 * @return
	 */
	public String getNumByRuleType(){
		String sql = " select count(*) num from dim_ia_keyword_search_rule  where rule_type in('directory','content','action') group by rule_type order by rule_type";
		return sql;
	}
	
	/**
	 * 基础sql语句
	 * @return
	 */
	private String getSql() {
		String sql = "select "
				+ "	t.host host," 
				+ "	t.match_type matchType," 
				+ "	t.prod_id prodid,"
				+ "	t.rule_type ruleType,"
				+ "	t.channel_name channelName,"
				+ "	t.parse_rule_andriod parseRuleAndriod,"
				+ "	t.get_index_andriod getIndexAndriod,"
				+ "	t.parse_rule_ios parseRuleIos,"
				+ "	t.get_index_ios getIndexIos,"
				+ "	t.group_type groupType,"
				+ "	t.content_label_code contentLabelCode,"
				+ "	t.attr_id attrId,"
				+ "	t.author author,"
				+ " t.num num,"
				+ "	t.op_time opTime,"
				+ "	t.is_valid isValid,"
				+ " t.bak bak,"
				//+ " t.parse_rule_add parseRuleAdd,"
				//+ " t.get_index_add getIndexAdd,"
				+ " t.url_example urlExample,"
				+ "	t.id id"				
				+ " from "
				+ " dim_ia_keyword_search_rule t"
				+ " where 1=1  ";
		return sql;
	}
	
}
