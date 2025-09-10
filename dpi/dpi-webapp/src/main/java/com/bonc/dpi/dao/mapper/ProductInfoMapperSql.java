package com.bonc.dpi.dao.mapper;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.utils.DpiUtils;

/**
 * 产品表
 * dim_ia_product_info
 * @author BONC-XUXL
 *
 */
public class ProductInfoMapperSql {
	
	public static String database_type = DpiUtils.DATABASE_TYPE;//mysql,oracle等数据库控制
	
	/**
	 * 查询数据条数
	 * @param vo
	 * @return
	 */
	public String selectDataNum(ProductInfo vo) {
		String prodId =vo.getProdId();
		String prodName =vo.getProdName();
		String author =vo.getAuthor();
		String startTime =vo.getStartTime();
		String endTime =vo.getEndTime();
		String prodLabelCode =vo.getProdLabelCode();
		String opTime =vo.getOpTime();
		String sql= " select count(*) from dim_ia_product_info t where 1=1 ";
		if(!StringUtils.isBlank(prodId)) {
			sql += " and t.prod_id like '%"+prodId+"%'";
		}
		if(!StringUtils.isBlank(prodName)) {
			//多条件查询
			if(prodName.contains("|")){
				if("mysql".equals(database_type)){
					sql += " and t.prod_name regexp '"+prodName+"'";
				}else if("oracle".equals(database_type)){
					sql += " and  REGEXP_LIKE (t.prod_name ,'("+prodName+")')";
				}
			}
			//单条件查询
			else{
				sql += " and t.prod_name like '%"+prodName+"%'";
			}
		}
		if(!StringUtils.isBlank(prodLabelCode)) {
			sql += " and t.prod_label_code like '%"+prodLabelCode+"%'";
		}
		//操作时间，精确到天20180101
		/*if(!StringUtils.isBlank(opTime)) {
			sql += " and t.op_time = '"+opTime+"'";
		}	*/	
		if(!StringUtils.isBlank(author)) {
			sql += " and t.author like '%"+author+"%'";
		}
		//操作时间，精确到天20180101
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t.op_time <= '"+endTime+"'";
		}	
		sql = sql+" order by op_time desc ,prod_id desc";
		
		return sql;
	}
	
	
	/**
	 * 查询列表
	 * @param vo
	 * @return
	 */
	public String selectList(ProductInfo vo) {
		String prodId =vo.getProdId();
		String prodName =vo.getProdName();
		String prodLabelCode =vo.getProdLabelCode();
		String opTime =vo.getOpTime();
		String author =vo.getAuthor();
		String startTime =vo.getStartTime();
		String endTime =vo.getEndTime();
		String batchFlag=vo.getBatchFlag();
		String sql= this.getSql();
		if(!StringUtils.isBlank(prodId)) {
			sql += " and t.prod_id like '%"+prodId+"%'";
		}
		if(!StringUtils.isBlank(prodName)) {
			//多条件查询
			if(prodName.contains("|")&&batchFlag.equals("0")){
				if("mysql".equals(database_type)){
					sql += " and t.prod_name regexp '"+prodName+"'";
				}else if("oracle".equals(database_type)){
					sql += " and  REGEXP_LIKE (t.prod_name ,'("+prodName+")')";
				}
			}else if(prodName.contains("|")&&batchFlag.equals("1")){
				String []a=prodName.split("\\|");
				sql += " and (";
				for(int i=0;i<a.length;i++){
					if(i!=a.length-1){
						sql += "  t.prod_name = '"+a[i]+"' or ";
					}else{
						sql += "  t.prod_name = '"+a[i]+"' ";
					}
				}
				sql += " ) ";
			}
			//单条件查询
			else{
				if(batchFlag.equals("0")&&!prodName.contains("|")){
					sql += " and t.prod_name like '%"+prodName+"%'";
				}
				if(batchFlag.equals("1")&&!prodName.contains("|")){
					//sql += " and t.prod_name = "+prodName;
					sql += " and t.prod_name = '"+prodName+"'";
				}
					
				
				
			}
		}	
		if(!StringUtils.isBlank(prodLabelCode)) {
			sql += " and t.prod_label_code like '%"+prodLabelCode+"%'";
		}
		if(!StringUtils.isBlank(author)) {
			sql += " and t.author like '%"+author+"%'";
		}
		//操作时间，精确到天20180101
		if(!StringUtils.isBlank(startTime)) {
			sql += " and t.op_time >= '"+startTime+"'";
		}
		if(!StringUtils.isBlank(endTime)) {
			sql += " and t.op_time <= '"+endTime+"'";
		}		
		sql = sql+" order by op_time desc ,prod_id desc";
		
		return sql;
	}
	
	/**
	 * 查询VO(根据主键)
	 * @param id
	 * @return
	 */
	public String selectVoByPrimaryKey(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodName = vo.getProdName();
		String sql = "select * from dim_ia_product_info where 1=1 ";
		if(!"".equals(prodId) && prodId!=null){
			sql += " and prod_id = '"+prodId+"'";
		}
		if(!"".equals(prodName) && prodName!=null){
			sql += " and prod_name = '"+prodName+"'";
		}
		return sql;
	}
	
	public String selectRuleList(ProductInfo vo) {
		String prodId=vo.getProdId();
		String sql = "";
		 if("mysql".equals(database_type)){
			sql += "select * from (select 1 as tableName,prod_id,`host`,`REGEXP` as regRule from dim_ia_url_path ";   
			sql +=	" union all select 2,prod_id,`host`,`REGEXP` from dim_ia_url_parameter";  
			sql +=	" union all select 3,prod_id,PARSE_RULE,GET_INDEX from dim_ia_url_fuzzy_rule ";	 
			sql += " union all select 4,prod_id,DOMAIN_CODE,null from dim_ia_domain_rule ";
			sql += " union all select 5,prod_id,IP_PORT,null from dim_ia_ip_port_dynamic ";
			sql += " union all select 6,prod_id,CLASS_CODE,SUB_CLASS_CODE from dim_ia_group_class " ;
			sql += " union all select 7,prod_id,UA_KEY,null from dim_ia_useragent_key  ";
			sql += " union all select 8,prod_id,UA_KEY_RULE,GET_INDEX from dim_ia_useragent_rule  ";
			sql +=" union all select 9,prod_id,`HOST`,NUM from dim_ia_keyword_search_rule   ";
			sql +=	" ) t where prod_id= '"+prodId+"'";
		 }
		 if("oracle".equals(database_type)){
			sql += "select * from (select 1 as tableName ,prod_id,host,REGEXP as regRule from dim_ia_url_path ";   
			sql +=	" union all select 2,prod_id,host,REGEXP from dim_ia_url_parameter";  
			sql +=	" union all select 3,prod_id,PARSE_RULE,GET_INDEX from dim_ia_url_fuzzy_rule ";	 
			sql += " union all select 4,prod_id,DOMAIN_CODE,null from dim_ia_domain_rule ";
			sql += " union all select 5,prod_id,IP_PORT,null from dim_ia_ip_port_dynamic ";
			sql += " union all select 6,prod_id,CLASS_CODE,SUB_CLASS_CODE from dim_ia_group_class " ;
			sql += " union all select 7,prod_id,UA_KEY,null from dim_ia_useragent_key  ";
			sql += " union all select 8,prod_id,UA_KEY_RULE,GET_INDEX from dim_ia_useragent_rule  ";
			sql +=" union all select 9,prod_id,HOST,NUM from dim_ia_keyword_search_rule   ";
			sql +=	" ) t where prod_id= '"+prodId+"'";
		 }
		
		return sql;
	}
	
	/**
	 * 删除VO(根据主键)
	 * @param id
	 * @return
	 */
	public String deleteVoByPrimaryKey(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodName = vo.getProdName();
		String sql = "delete from dim_ia_product_info where 1=1 ";
		if(!"".equals(prodId) && prodId!=null){
			sql += " and prod_id = '"+prodId+"'";
		}
		if(!"".equals(prodName) && prodName!=null){
			sql += " and prod_name = '"+prodName+"'";
		}
		return sql;
	}
	public String deleteVoByPrimaryKeyJm(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodName = vo.getProdName();
		String sql = "delete from dim_ia_product_info_jm where 1=1 ";
		if(!"".equals(prodId) && prodId!=null){
			sql += " and prod_id = '"+prodId+"'";
		}
		if(!"".equals(prodName) && prodName!=null){
			sql += " and prod_name = '"+prodName+"'";
		}
		return sql;
	}
	public String deleteVoByUrlPath(ProductInfo vo) {
		String prodId = vo.getProdId();
		
		String sql="";
		if(!"".equals(prodId) && prodId!=null){
			sql = "delete from dim_ia_url_path where 1=1 ";
			sql += " and prod_id = '"+prodId+"'";
		}
		
		return sql;
	}
	public String deleteVoByUrlParameter(ProductInfo vo) {
		String prodId = vo.getProdId();
		
		String sql="";
		if(!"".equals(prodId) && prodId!=null){
			sql = "delete from dim_ia_url_parameter where 1=1 ";
			sql += " and prod_id = '"+prodId+"'";
		}
		
		return sql;
	}
	public String deleteVoByFuzzyRule(ProductInfo vo) {
		String prodId = vo.getProdId();
		
		String sql="";
		if(!"".equals(prodId) && prodId!=null){
			sql = "delete from dim_ia_url_fuzzy_rule where 1=1 ";
			sql += " and prod_id = '"+prodId+"'";
		}
		
		return sql;
	}
	public String deleteVoByDomainRule(ProductInfo vo) {
		String prodId = vo.getProdId();
		
		String sql="";
		if(!"".equals(prodId) && prodId!=null){
			sql = "delete from dim_ia_domain_rule where 1=1 ";
			sql += " and prod_id = '"+prodId+"'";
		}
		
		return sql;
	}
	public String deleteVoByIpPort(ProductInfo vo) {
		String prodId = vo.getProdId();
		
		String sql="";
		if(!"".equals(prodId) && prodId!=null){
			sql = "delete from dim_ia_ip_port_dynamic where 1=1 ";
			sql += " and prod_id = '"+prodId+"'";
		}
		
		return sql;
	}
	public String deleteVoByGroupClass(ProductInfo vo) {
		String prodId = vo.getProdId();
		
		String sql="";
		if(!"".equals(prodId) && prodId!=null){
			sql = "delete from dim_ia_group_class where 1=1 ";
			sql += " and prod_id = '"+prodId+"'";
		}
		
		return sql;
	}
	public String deleteVoByUseragentKey(ProductInfo vo) {
		String prodId = vo.getProdId();
		
		String sql="";
		if(!"".equals(prodId) && prodId!=null){
			sql = "delete from dim_ia_useragent_key where 1=1 ";
			sql += " and prod_id = '"+prodId+"'";
		}
		
		return sql;
	}
	public String deleteVoByUseragentRule(ProductInfo vo) {
		String prodId = vo.getProdId();
		
		String sql="";
		if(!"".equals(prodId) && prodId!=null){
			sql = "delete from dim_ia_useragent_rule where 1=1 ";
			sql += " and prod_id = '"+prodId+"'";
		}
		
		return sql;
	}
	
	public String updateUrlPath(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodIdOld = vo.getProdIdOld();
		
		String sql = "update  dim_ia_url_path set prod_id='"+prodId+"'  where prod_id = '"+prodIdOld+"'";
//		if(!"".equals(prodId) && prodId!=null){
//			sql += " and prod_id = '"+prodId+"'";
//		}
//		if(!"".equals(prodName) && prodName!=null){
//			sql += " and prod_name = '"+prodName+"'";
//		}
		return sql;
	}
	public String getAllProd() {
		String sql="";
		
		 sql += "select id id ,prod_id prodId,prod_name prodName ,prod_label_code prodLabelCode,add_label_list addLabelLis";
		 sql +=  ",father_prod_id fatherProdId ,prod_type_id prodTypeId , prod_type_name prodTypeName ,prod_catagory prodCatagory,";
		 sql +=	"is_main_prod isMainProd ,is_app isApp, is_operator isOperator ,sp_id spId, sp_name spName, short_name shortName,";
		 sql +="	label_check labelCheck,author author ,op_time opTime , PROD_DESC prodDesc ,IOS_PROD_NAME iosProdName , IS_BOCE isBoce, BOCE_DATE boceDate";
		 sql +=" from dim_ia_product_info";

		return sql;
	}
	public String delAllProd() {
		 String sql="delete from dim_ia_product_info_jm";
		
		 

		return sql;
	}
	public String updateUrlParameter(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodIdOld = vo.getProdIdOld();
		
		String sql = "update  dim_ia_url_parameter set prod_id='"+prodId+"'  where prod_id = '"+prodIdOld+"'";

		return sql;
	}
	public String updateUrlFuzzyRule(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodIdOld = vo.getProdIdOld();
		
		String sql = "update  dim_ia_url_fuzzy_rule set prod_id='"+prodId+"'  where prod_id = '"+prodIdOld+"'";

		return sql;
	}
	public String updateDomainRule(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodIdOld = vo.getProdIdOld();
		
		String sql = "update  dim_ia_domain_rule set prod_id='"+prodId+"'  where prod_id = '"+prodIdOld+"'";

		return sql;
	}
	public String updateIpPort(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodIdOld = vo.getProdIdOld();
		
		String sql = "update  dim_ia_ip_port_dynamic set prod_id='"+prodId+"'  where prod_id = '"+prodIdOld+"'";

		return sql;
	}
	public String updateGroupClass(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodIdOld = vo.getProdIdOld();
		
		String sql = "update  dim_ia_group_class set prod_id='"+prodId+"'  where prod_id = '"+prodIdOld+"'";

		return sql;
	}
	public String updateUseragentKey(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodIdOld = vo.getProdIdOld();
		
		String sql = "update  dim_ia_useragent_key set prod_id='"+prodId+"'  where prod_id = '"+prodIdOld+"'";

		return sql;
	}
	public String updateUseragentRule(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodIdOld = vo.getProdIdOld();
		
		String sql = "update  dim_ia_useragent_rule set prod_id='"+prodId+"'  where prod_id = '"+prodIdOld+"'";

		return sql;
	}
	public String updateDimIaKeywordSearchRule(ProductInfo vo) {
		String prodId = vo.getProdId();
		String prodIdOld = vo.getProdIdOld();
		
		String sql = "update  dim_ia_keyword_search_rule set prod_id='"+prodId+"'  where prod_id = '"+prodIdOld+"'";

		return sql;
	}
	public String deleteVoByPrimaryKeyDomain(String domainCode) {
		
		String sql = "delete from dim_ia_domain_rule where 1=1 ";
		
		if(!"".equals(domainCode) && domainCode!=null){
			sql += " and domain_code = '"+domainCode+"'";
		}
		return sql;
	}
   public String saveVoByPrimaryKeyDomain(String domainCode) {
		
		//String sql = "insert into dim_ia_domain_rule_bak  select * from dim_ia_domain_rule where dim_ia_domain_rule.DOMAIN_CODE='ceshi9.com' ";
		String sql =" ";
		if(!"".equals(domainCode) && domainCode!=null){
			sql =  "insert into dim_ia_domain_rule_bak  select * from dim_ia_domain_rule where dim_ia_domain_rule.DOMAIN_CODE= '"+domainCode+"'";
		}
		return sql;
	}
   public String deleteVoByPrimaryKeyIp(String domainCode) {
		
		String sql = "delete from dim_ia_ip_port_dynamic where 1=1 ";
		
		if(!"".equals(domainCode) && domainCode!=null){
			sql += " and ip_port = '"+domainCode+"'";
		}
		return sql;
	}
  public String saveVoByPrimaryKeyIp(String domainCode) {
		
		//String sql = "insert into dim_ia_domain_rule_bak  select * from dim_ia_domain_rule where dim_ia_domain_rule.DOMAIN_CODE='ceshi9.com' ";
		String sql =" ";
		if(!"".equals(domainCode) && domainCode!=null){
			sql =  "insert into dim_ia_ip_port_dynamic_bak  select * from dim_ia_ip_port_dynamic where dim_ia_ip_port_dynamic.ip_port= '"+domainCode+"'";
		}
		return sql;
	}
	
	/**
	 * 添加
	 * @param vo
	 * @return
	 */
	public String insertVo(ProductInfo vo) {
		String sql = "insert into dim_ia_product_info("
				+ "	id,"
				+ "	prod_id,"
				+ "	prod_name,"
				+ "	prod_label_code ,"
				+ "	add_label_list,"
				+ "	father_prod_id,"
				+ "	prod_type_id,"
				+ "	prod_type_name,"
				+ "	prod_catagory,"
				+ "	is_main_prod,"
				+ "	is_app,"
				+ "	is_operator,"
				+ "	sp_id,"
				+ "	sp_name,"
				+ "	short_name,"
				+ "	label_check,"
				+ "	author,"
				+ "	op_time,"
				+ " PROD_DESC,"
				+ " IOS_PROD_NAME,"
				+ " IS_BOCE,"
				+ " BOCE_DATE)"
				+ "values("
				+ "	#{id}," 
				+ "	#{prodId}," 
				+ "	#{prodName}," 
				+ "	#{prodLabelCode}," 
				+ "	#{addLabelList}," 
				+ "	#{fatherProdId}," 
				+ "	#{prodTypeId}," 
				+ "	#{prodTypeName}," 
				+ "	#{prodCatagory}," 
				+ "	#{isMainProd}," 
				+ "	#{isApp}," 
				+ "	#{isOperator}," 
				+ "	#{spId}," 
				+ "	#{spName}," 
				+ "	#{shortName}," 
				+ "	#{labelCheck}," 
				+ " #{author},"
				+ " #{opTime},"
				+ " #{prodDesc},"
				+ " #{iosProdName},"
				+ " #{isBoce},"
				+ " #{boceDate})";
		return sql;
	}
	
	public String insertVoJm(ProductInfo vo) {
		String sql = "insert into dim_ia_product_info("
				+ "	id,"
				+ "	prod_id,"
				+ "	prod_name,"
				+ "	prod_label_code ,"
				+ "	add_label_list,"
				+ "	father_prod_id,"
				+ "	prod_type_id,"
				+ "	prod_type_name,"
				+ "	prod_catagory,"
				+ "	is_main_prod,"
				+ "	is_app,"
				+ "	is_operator,"
				+ "	sp_id,"
				+ "	sp_name,"
				+ "	short_name,"
				+ "	label_check,"
				+ "	author,"
				+ "	op_time)"
				+ "values("
				+ "	#{id}," 
				+ "	#{prodId}," 
				+ "	#{prodName}," 
				+ "	#{prodLabelCode}," 
				+ "	#{addLabelList}," 
				+ "	#{fatherProdId}," 
				+ "	#{prodTypeId}," 
				+ "	#{prodTypeName}," 
				+ "	#{prodCatagory}," 
				+ "	#{isMainProd}," 
				+ "	#{isApp}," 
				+ "	#{isOperator}," 
				+ "	#{spId}," 
				+ "	#{spName}," 
				+ "	#{shortName}," 
				+ "	#{labelCheck}," 
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
            sb.append("insert into dim_ia_product_info ");  
            sb.append("(prod_id, prod_name,prod_label_code,add_label_list,"
            		+ "father_prod_id,prod_type_id,prod_type_name,prod_catagory,is_main_prod,"
            		+ "is_app,is_operator,sp_id,sp_name,short_name,label_check,author,op_time,id,PROD_DESC,IOS_PROD_NAME,IS_BOCE,BOCE_DATE) ");  
            sb.append("values ");  
            MessageFormat mf = new MessageFormat(
            		  "(#'{'list[{0}].prodId},"
            		+ "#'{'list[{0}].prodName},"
            		+ "#'{'list[{0}].prodLabelCode},"
            		+ "#'{'list[{0}].addLabelList},"
            		+ "#'{'list[{0}].fatherProdId},"
            		+ "#'{'list[{0}].prodTypeId},"
            		+ "#'{'list[{0}].prodTypeName},"
            		+ "#'{'list[{0}].prodCatagory},"
            		+ "#'{'list[{0}].isMainProd},"
            		+ "#'{'list[{0}].isApp},"
            		+ "#'{'list[{0}].isOperator},"
            		+ "#'{'list[{0}].spId},"
            		+ "#'{'list[{0}].spName},"
            		+ "#'{'list[{0}].shortName},"
            		+ "#'{'list[{0}].labelCheck},"
            		+ "#'{'list[{0}].author},"
            		+ "#'{'list[{0}].opTime},"
            		+ "#'{'list[{0}].id},"
            		+ "#'{'list[{0}].prodDesc},"
            		+ "#'{'list[{0}].iosProdName},"
            		+ "#'{'list[{0}].isBoce},"
            		+ "#'{'list[{0}].boceDate})");  
            for (int i = 0; i < voList.size(); i++) {  
                sb.append(mf.format(new Object[]{i}));  
                if (i < voList.size() - 1) {  
                    sb.append(",");  
                }  
            }  
        }else if("oracle".equals(database_type)){
			String str = " into dim_ia_product_info (prod_id, prod_name,prod_label_code,add_label_list,father_prod_id,"
						+ " prod_type_id,prod_type_name,prod_catagory,is_main_prod,is_app,"
						+ " is_operator,sp_id,sp_name,short_name,label_check,author,op_time,id,PROD_DESC,IOS_PROD_NAME,IS_BOCE,BOCE_DATE) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (OperationFlow operationFlow : voList) {
				ProductInfo vo = (ProductInfo) operationFlow;
				String prodId =vo.getProdId();//prod_id,产品ID
				String prodName =vo.getProdName();//prod_name,产品名称
				String prodLabelCode =vo.getProdLabelCode();//prod_label_code,标签ID
				String addLabelList =vo.getAddLabelList();//add_label_list,附属标签列表
				String fatherProdId =vo.getFatherProdId();//father_prod_id,父产品ID
				String prodTypeId =vo.getProdTypeId();//prod_type_id,产品归类ID 同产品ID
				String prodTypeName =vo.getProdTypeName();//prod_type_name,产品归类名称  默认归类为产品名称 建立产品间的关系 归属、关联性，默认归类为自己，有特殊需求的手工处理，如果微信公众平台、微信支付归类应该都为微信
				String prodCatagory =vo.getProdCatagory();//prod_catagory,产品类别
				String isMainProd =vo.getIsMainProd();//is_main_prod,是否主产品
				String isApp =vo.getIsApp();//is_app,是否APP
				String isOperator =vo.getIsOperator();//is_operator,是否运营商自有业务：0-非运营商 1-移动自有 2-联通自有 3-电信自有
				String spId =vo.getSpId();//sp_id,spid
				String spName =vo.getSpName();//sp_name,spname
				String shortName =vo.getShortName();//short_name,公司简称
				String labelCheck =vo.getLabelCheck();//label_check，标签是否稽核
				String author = vo.getAuthor();//作者
				String opTime = vo.getOpTime();//操作时间
				String id = vo.getId();//id
				String prodDesc=vo.getProdDesc();
				if(StringUtils.isEmpty(prodDesc)||"null".equals(prodDesc)){
					prodDesc="";
				}
				String iosProdName=vo.getIosProdName();
				if(StringUtils.isEmpty(iosProdName)||"null".equals(iosProdName)){
					iosProdName="";
				}
				String isBoce=vo.getIsBonc();
				if(StringUtils.isEmpty(isBoce)||"null".equals(isBoce)){
					isBoce="0";
				}
				String boceDate=vo.getBoceDate();
				if(StringUtils.isEmpty(boceDate)||"null".equals(boceDate)){
					boceDate="";
				}
				
				sql += str+"('"+
							prodId+"','"+prodName+"','"+prodLabelCode+"','"+addLabelList+"','"+fatherProdId+
							"','"+prodTypeId+"','"+prodTypeName+"','"+prodCatagory+"','"+isMainProd+"','"+isApp+
							"','"+isOperator+"','"+spId+"','"+spName+"','"+shortName+"','"+labelCheck+
							"','"+author+"','"+opTime+"','"+id+"','"+prodDesc+"','"+iosProdName+"','"+isBoce+"','"+boceDate
						+"')";
			}
			sb.append(sql+" select 1 from dual");
        }
        return sb.toString();  
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insertVoPlJm(Map map) {
        List<ProductInfo> voList =  (List<ProductInfo>) map.get("list");
        StringBuilder sb = new StringBuilder();
        
        if("mysql".equals(database_type)){
            sb.append("insert into dim_ia_product_info_jm ");  
            sb.append("(prod_id, prod_name,prod_label_code,add_label_list,"
            		+ "father_prod_id,prod_type_id,prod_type_name,prod_catagory,is_main_prod,"
            		+ "is_app,is_operator,sp_id,sp_name,short_name,label_check,author,op_time,id) ");  
            sb.append("values ");  
            MessageFormat mf = new MessageFormat(
            		  "(#'{'list[{0}].prodId},"
            		+ "#'{'list[{0}].prodName},"
            		+ "#'{'list[{0}].prodLabelCode},"
            		+ "#'{'list[{0}].addLabelList},"
            		+ "#'{'list[{0}].fatherProdId},"
            		+ "#'{'list[{0}].prodTypeId},"
            		+ "#'{'list[{0}].prodTypeName},"
            		+ "#'{'list[{0}].prodCatagory},"
            		+ "#'{'list[{0}].isMainProd},"
            		+ "#'{'list[{0}].isApp},"
            		+ "#'{'list[{0}].isOperator},"
            		+ "#'{'list[{0}].spId},"
            		+ "#'{'list[{0}].spName},"
            		+ "#'{'list[{0}].shortName},"
            		+ "#'{'list[{0}].labelCheck},"
            		+ "#'{'list[{0}].author},"
            		+ "#'{'list[{0}].opTime},"
            		+ "#'{'list[{0}].id})");  
            for (int i = 0; i < voList.size(); i++) {  
                sb.append(mf.format(new Object[]{i}));  
                if (i < voList.size() - 1) {  
                    sb.append(",");  
                }  
            }  
        }else if("oracle".equals(database_type)){
			String str = " into dim_ia_product_info_jm (prod_id, prod_name,prod_label_code,add_label_list,father_prod_id,"
						+ " prod_type_id,prod_type_name,prod_catagory,is_main_prod,is_app,"
						+ " is_operator,sp_id,sp_name,short_name,label_check,author,op_time,id,prod_desc,ios_prod_name,is_boce,boce_date) values ";
			sb.append(" insert  all ");
			String sql ="";
			for (ProductInfo vo : voList) {
				//ProductInfo vo = (ProductInfo) operationFlow;
				String prodId =vo.getProdId();//prod_id,产品ID
				if(prodId==null||"null".equals(prodId)){
					prodId="";
				}
				String prodName =vo.getProdName().replaceAll("'", "''");//prod_name,产品名称
				if(prodName==null||"null".equals(prodName)){
					prodName="";
				}
				String prodLabelCode =vo.getProdLabelCode();//prod_label_code,标签ID
				if(prodLabelCode==null||"null".equals(prodLabelCode)){
					prodLabelCode="";
				}
				String addLabelList =vo.getAddLabelList();//add_label_list,附属标签列表
				if(addLabelList==null||"null".equals(addLabelList)){
					addLabelList="";
				}
				String fatherProdId =vo.getFatherProdId();//father_prod_id,父产品ID
				if(fatherProdId==null||"null".equals(fatherProdId)){
					fatherProdId="";
				}
				String prodTypeId =vo.getProdTypeId();//prod_type_id,产品归类ID 同产品ID
				if(prodTypeId==null||"null".equals(prodTypeId)){
					prodTypeId="";
				}
				String prodTypeName =vo.getProdTypeName();//prod_type_name,产品归类名称  默认归类为产品名称 建立产品间的关系 归属、关联性，默认归类为自己，有特殊需求的手工处理，如果微信公众平台、微信支付归类应该都为微信
				if(prodTypeName==null||"null".equals(prodTypeName)){
					prodTypeName="";
				}
				String prodCatagory =vo.getProdCatagory();//prod_catagory,产品类别
				if(prodCatagory==null||"null".equals(prodCatagory)){
					prodCatagory="";
				}
				String isMainProd =vo.getIsMainProd();//is_main_prod,是否主产品
				if(isMainProd==null||"null".equals(isMainProd)){
					isMainProd="";
				}
				String isApp =vo.getIsApp();//is_app,是否APP
				if(isApp==null||"null".equals(isApp)){
					isApp="";
				}
				String isOperator =vo.getIsOperator();//is_operator,是否运营商自有业务：0-非运营商 1-移动自有 2-联通自有 3-电信自有
				if(isOperator==null||"null".equals(isOperator)){
					isOperator="";
				}
				String spId =vo.getSpId();//sp_id,spid
				if(spId==null||"null".equals(spId)){
					spId="";
				}
				String spName =vo.getSpName();//sp_name,spname
				if(spName==null||"null".equals(spName)){
					spName="";
				}
				String shortName =vo.getShortName();//short_name,公司简称
				if(shortName==null||"null".equals(shortName)){
					shortName="";
				}
				String labelCheck =vo.getLabelCheck();//label_check，标签是否稽核
				if(labelCheck==null||"null".equals(labelCheck)){
					labelCheck="";
				}
				String author = vo.getAuthor();//作者
				if(author==null||"null".equals(author)){
					author="";
				}
				String opTime = vo.getOpTime();//操作时间
				if(opTime==null||"null".equals(opTime)){
					opTime="";
				}
				String prodDesc = vo.getProdDesc();   //prodDesc,iosProdName,isBoce,boceDate
				if(prodDesc==null||"null".equals(prodDesc)){
					prodDesc="";
				}
				String iosProdName = vo.getIosProdName();
				if(iosProdName==null||"null".equals(iosProdName)){
					iosProdName="";
				}
				String isBoce = vo.getIsBonc();
				if(isBoce==null||"null".equals(isBoce)){
					isBoce="";
				}
				String boceDate = vo.getBoceDate();//操作时间
				if(boceDate==null||"null".equals(boceDate)){
					boceDate="";
				}
				String id = vo.getId();//id
				
				sql += str+"('"+
							prodId+"','"+prodName+"','"+prodLabelCode+"','"+addLabelList+"','"+fatherProdId+
							"','"+prodTypeId+"','"+prodTypeName+"','"+prodCatagory+"','"+isMainProd+"','"+isApp+
							"','"+isOperator+"','"+spId+"','"+spName+"','"+shortName+"','"+labelCheck+
							"','"+author+"','"+opTime+"','"+id+"','"+prodDesc+"','"+iosProdName+"','"+isBoce+"','"+boceDate
						+" ')";
			}
			sb.append(sql+" select 1 from dual");
        }
        return sb.toString();  
    }  
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String insertVoPlJmByone(ProductInfo vo) {
        
       String sql="insert into dim_ia_product_info_jm ";
       sql+=" (prod_id, prod_name,prod_label_code,add_label_list, ";
       sql+=" father_prod_id,prod_type_id,prod_type_name,prod_catagory,is_main_prod, ";
       sql+=" is_app,is_operator,sp_id,sp_name,short_name,label_check,author,op_time,id)  ";
       sql+=" values  ";
       String prodId =vo.getProdId();//prod_id,产品ID
		String prodName =vo.getProdName().replaceAll("'", "''");//prod_name,产品名称
		String prodLabelCode =vo.getProdLabelCode();//prod_label_code,标签ID
		String addLabelList =vo.getAddLabelList();//add_label_list,附属标签列表
		String fatherProdId =vo.getFatherProdId();//father_prod_id,父产品ID
		String prodTypeId =vo.getProdTypeId();//prod_type_id,产品归类ID 同产品ID
		String prodTypeName =vo.getProdTypeName();//prod_type_name,产品归类名称  默认归类为产品名称 建立产品间的关系 归属、关联性，默认归类为自己，有特殊需求的手工处理，如果微信公众平台、微信支付归类应该都为微信
		String prodCatagory =vo.getProdCatagory();//prod_catagory,产品类别
		String isMainProd =vo.getIsMainProd();//is_main_prod,是否主产品
		String isApp =vo.getIsApp();//is_app,是否APP
		String isOperator =vo.getIsOperator();//is_operator,是否运营商自有业务：0-非运营商 1-移动自有 2-联通自有 3-电信自有
		String spId =vo.getSpId();//sp_id,spid
		String spName =vo.getSpName();//sp_name,spname
		String shortName =vo.getShortName();//short_name,公司简称
		String labelCheck =vo.getLabelCheck();//label_check，标签是否稽核
		String author = vo.getAuthor();//作者
		String opTime = vo.getOpTime();//操作时间
		String id = vo.getId();//id
		
		sql += "('"+
					prodId+"','"+prodName+"','"+prodLabelCode+"','"+addLabelList+"','"+fatherProdId+
					"','"+prodTypeId+"','"+prodTypeName+"','"+prodCatagory+"','"+isMainProd+"','"+isApp+
					"','"+isOperator+"','"+spId+"','"+spName+"','"+shortName+"','"+labelCheck+
					"','"+author+"','"+opTime+"','"+id
				+"')"; 
        
           /* sb.append("insert into dim_ia_product_info_jm ");  
            sb.append("(prod_id, prod_name,prod_label_code,add_label_list,"
            		+ "father_prod_id,prod_type_id,prod_type_name,prod_catagory,is_main_prod,"
            		+ "is_app,is_operator,sp_id,sp_name,short_name,label_check,author,op_time,id) ");  
            sb.append("values ");  */
            
        
        return sql;  
    
	}
	
	/**
	 * 修改
	 * @param vo
	 * @return
	 */
	public String updateVo(ProductInfo vo) {
		String sql = "update dim_ia_product_info set "
				+"prod_id=#{prodId},"
				+"prod_name=#{prodName},"
				+"prod_label_code=#{prodLabelCode},"
				+"add_label_list=#{addLabelList},"
				+"father_prod_id=#{fatherProdId},"
				+"prod_type_id=#{prodTypeId},"
				+"prod_type_name=#{prodTypeName},"
				+"prod_catagory=#{prodCatagory},"
				+"is_main_prod=#{isMainProd},"
				+"is_app=#{isApp},"
				+"is_operator=#{isOperator},"
				+"sp_id=#{spId},"
				+"sp_name=#{spName},"
				+"short_name=#{shortName},"
				+"label_check=#{labelCheck},"
				+"author=#{author},"
				+"op_time=#{opTime}, "
				+"PROD_DESC=#{prodDesc},"
				+"IOS_PROD_NAME=#{iosProdName},"
				+"IS_BOCE=#{isBoce},"
				+"BOCE_DATE=#{boceDate}"
				+"  where prod_id=#{prodIdOld}";
		return sql;
	}
	
	public String updateVoJm(ProductInfo vo) {
		String sql = "update dim_ia_product_info_jm set "
				+"prod_id=#{prodId},"
				+"prod_name=#{prodName},"
				+"prod_label_code=#{prodLabelCode},"
				+"add_label_list=#{addLabelList},"
				+"father_prod_id=#{fatherProdId},"
				+"prod_type_id=#{prodTypeId},"
				+"prod_type_name=#{prodTypeName},"
				+"prod_catagory=#{prodCatagory},"
				+"is_main_prod=#{isMainProd},"
				+"is_app=#{isApp},"
				+"is_operator=#{isOperator},"
				+"sp_id=#{spId},"
				+"sp_name=#{spName},"
				+"short_name=#{shortName},"
				+"label_check=#{labelCheck},"
				+"author=#{author},"
				+"op_time=#{opTime}"
				+"  where prod_id=#{prodIdOld}";
		return sql;
	}
	
	/**
	 * 批量更新
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String updateVoPl(Map map) {
        List<ProductInfo> list = (List<ProductInfo>) map.get("list");  
        StringBuilder sb = new StringBuilder();  
        sb.append("update dim_ia_product_info set prod_name = ");
        MessageFormat mf = new MessageFormat("#'{'list[{0}].prodName'}'");
        for (int i = 0; i < list.size(); i++) {  
            sb.append(mf.format(new Object[]{i})); 
        }  
        sb.append(" where prod_id in(");
        for (int i = 0; i < list.size(); i++) {
        	sb.append("'");
            sb.append(list.get(i).getProdId()); 
        	sb.append("'");
            if (i < list.size() - 1) {  
                sb.append(",");  
            }  
        }
        sb.append(")");
        
        return sb.toString(); 
    }
	
	/**
	 * 主键校验
	 * @param vo
	 * @return
	 */
	public String selectCheck(ProductInfo vo) {
		String sql = " select count(*) from dim_ia_product_info t where 1=1 ";
		if(!StringUtils.isBlank(vo.getProdId())) {
			sql += " and t.prod_id = #{prodId} ";
		}
		return sql;
	}
	
	/**
	 * 不可为空，prod_id必须在产品表里
	 * @param vo
	 * @return
	 */
	public String prodIdCheck(ProductInfo vo) {
		
		String sql = " select count(*) from dim_ia_product_info t where 1=1 ";
		
		if(!StringUtils.isBlank(vo.getProdId())) {
			sql += " and t.prod_id = #{prodId} ";
		}
		return sql;
	}
	
	/**
	 * 根据产品id模糊查询
	 * @param prodName
	 * @return
	 */
	public String getProdIdListByProdId(String prodId){
		String sql = " select * from dim_ia_product_info t where 1=1 ";
		if(!StringUtils.isBlank(prodId)) {
			sql += " and t.prod_id like '%"+prodId+"%' ";
		}
		return sql;
	}
	
	/**
	 * 根据产品名称模糊查询
	 * @param prodName
	 * @return
	 */
	public String getProdIdListByProdName(String prodName){
		String sql = " select * from dim_ia_product_info t where 1=1 ";
		if(!StringUtils.isBlank(prodName)) {
			sql += " and t.prod_name like '%"+prodName+"%' ";
		}
		return sql;
	}
	
	/**
	 * 获取产品id最大的
	 * @param prodId_head :例如prodId='W12233' 则prodId_head='W'
	 * @return
	 */
	public String getProdId_Max(String prodId_head){
		String databaseType = DpiUtils.DATABASE_TYPE;//oracle,mysql
		String sql = "";
		if("mysql".equals(databaseType)){
			sql = "select t.prod_id from dim_ia_product_info t where t.prod_id like '%"+prodId_head+"%' order by substr(prod_id,2)+0 desc limit 1 ";
		}else if("oracle".equals(databaseType)){
			sql = "select * from (select t.prod_id from dim_ia_product_info t where t.prod_id like '%"+prodId_head+"%'  order by substr(prod_id,2)+0 desc) where rownum<2";
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
				+ "	t.prod_id prodId," 
				+ "	t.prod_Name prodName," 
				+ "	t.prod_label_code prodLabelCode,"
				+ "	t.add_label_list addLabelList," 
				+ "	t.father_prod_id fatherProdId," 
				+ "	t.prod_type_id prodTypeId," 
				+ "	t.prod_type_name prodTypeName," 
				+ "	t.prod_catagory prodCatagory," 
				+ "	t.is_main_prod isMainProd," 
				+ "	t.is_app isApp," 
				+ "	t.is_operator isOperator," 
				+ "	t.sp_id spId," 
				+ "	t.sp_name spName," 
				+ "	t.short_name shortName," 
				+ "	t.label_check labelCheck," 
				+ "	t.author author,"
				+ "	t.op_time opTime,"
				+ "	t.PROD_DESC prodDesc,"
				+ "	t.IOS_PROD_NAME iosProdName,"
				+ "	t.IS_BOCE isBoce,"
				+ "	t.BOCE_DATE boceDate"
				+ " from "
				+ " dim_ia_product_info t"
				+ " where 1=1 ";
		return sql;
	}
}
