package com.bonc.dpi.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.bonc.common.des.DesUtil;
import com.bonc.common.utils.PropertiesLoader;
import com.bonc.common.utils.UUIDUtil;
import com.bonc.dpi.dao.entity.ContentLabel;
import com.bonc.dpi.dao.entity.DomainFuzzyRule;
import com.bonc.dpi.dao.entity.GroupClass;
import com.bonc.dpi.dao.entity.OperationFlow;
import com.bonc.dpi.dao.entity.ProductInfo;
import com.bonc.dpi.dao.entity.ProductLabel;
import com.bonc.dpi.dao.entity.SegWordLtb;
import com.bonc.dpi.dao.entity.UrlParameter;
import com.bonc.dpi.dao.entity.UrlPath;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;

/**
 * dpi工具方法
 * @author BONC-XUXL
 *
 */
public class DpiUtils {
	
	public static final String DATABASE_TYPE = (String)getMapConf().get("databaseType");//mysql,oracle等数据库控制
//	public static final String DATABASE_TYPE = "mysql";//mysql,oracle等数据库控制
	public static final String MAP_PROVINCE_JSON_NAME = "heilongjiang.json";//地图json文件名
	public static final String MAP_PROVINCE_MAPTYPE = "HLJ";//地图省份类型
	
	private static SysUser user ;
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	public static final String PRODID_HEAD ="C";//产品ID首字母设置为C
	public static final String MAX_DATANUM_FOR_EXCLE = (String)getMapConf().get("maxDataNumForExcle");//导入excle的最大数
	public static final Integer PL_NUMBERS_ONCE =1000;//批量导入每一次的导入条数
	public static final String PROD_NAME_SPLIT = ",";//PROD_NAME(产品名称)分隔符
	
	private static HashSet<String> hashSetInExcle = new HashSet<String>();//excle中的数据
	private static List<OperationFlow> listRepeatInExcle = new ArrayList<OperationFlow>(); //excle中重复的数据
	
	private static List<OperationFlow> list_true = new ArrayList<OperationFlow>(); //有效数据
	private static List<OperationFlow> list_false = new ArrayList<OperationFlow>(); //无效数
	
	//excle批量导入验证信息提示
	public static final String info_list_true_cache = "成功插入";//excle成功插入提示语
	
	//基本验证提示信息
	public static final String info_invalidDataInExcle_repeatPrimaryKey = "文件中主键重复";//文件中无效的数据--主键重复
	public static final String info_invalidDataInExcle_empty = "文件中非空字段存在空值";//文件中无效的数据--空值
	public static final String info_invalidDataInExcle_tooLong = "文件中数据过长";//文件中无效的数据--过长
	public static final String info_list_false_cache = "excle中与数据库中存在主键重复";//文件中无效的数据--主键重复
	//业务验证提示信息
	public static final String info_invalidDataInExcle_notTrue_prodId = "文件中产品id不在产品表中";//文件中无效的数据--产品表关联验证
	public static final String info_invalidDataInExcle_notTrue_prodLabelCode = "文件中标签编码不在标签表中";//文件中无效的数据--标签编码关联验证
	public static final String info_invalidDataInExcle_notTrue_groupType = "文件中应用分组编码不在标签表中";//文件中无效的数据--标签编码关联验证

	
	//excle批量导入tab页的名称
	//基本tab页名称
	public static final String tab_name_invalidDataInExcle_repeatPrimaryKey = "文件主键重复";//文件中无效的数据--主键重复
	public static final String tab_name_invalidDataInExcle_empty = "文件必填项为空";//文件中无效的数据--空值
	public static final String tab_name_invalidDataInExcle_tooLong = "文件属性过长";//文件中无效的数据--过长
	public static final String tab_name_list_false_cache = "文件中主键与库中重复";//文件中无效的数据--主键重复
	//业务tab页名称
	public static final String tab_name_invalidDataInExcle_notTrue_prodId = "文件产品id不真实";//文件中无效的数据--产品表关联验证
	public static final String tab_name_invalidDataInExcle_notTrue_prodLabelCode = "文件标签编码不真实";//文件中无效的数据--标签表关联验证
	public static final String tab_name_invalidDataInExcle_notTrue_groupType = "文件应用分组编码不真实";//文件中无效的数据--标签表关联验证
	
	//excle批量导入问题数据页面反回路径
	public static final String url_falseDataViewFromExcle_domainRule ="/domainRule/falseDataViewFromExcle";//domainRule
	public static final String url_falseDataViewFromExcle_urlPath ="/urlPath/falseDataViewFromExcle";//urlPath
	public static final String url_falseDataViewFromExcle_urlParameter ="/urlParameter/falseDataViewFromExcle";//urlParameter
	public static final String url_falseDataViewFromExcle_productInfo ="/productInfo/falseDataViewFromExcle";//productInfo
	public static final String url_falseDataViewFromExcle_productInfo_domain ="/productInfo/falseDataViewFromExcle_2";//productInfo
	public static final String url_falseDataViewFromExcle_keywordGroup ="/keywordGroup/falseDataViewFromExcle";//keywordGroup
	public static final String url_falseDataViewFromExcle_keywordSearchRule ="/keywordSearchRule/falseDataViewFromExcle";//keywordSearchRule
	public static final String url_falseDataViewFromExcle_cataWordLtb ="/cataWordLtb/falseDataViewFromExcle";//cataWordLtb
	public static final String url_falseDataViewFromExcle_stopWordLib ="/stopWordLib/falseDataViewFromExcle";//stopWordLib
	
	public static final String url_falseDataViewFromExcle_dimIaIpPortDynamic ="/ipport/falseDataViewFromExcle";//dimIaIpPortDynamic
	public static final String url_falseDataViewFromExcle_domainFuzzyRule ="/domainFuzzyRule/falseDataViewFromExcle";//domainFuzzyRule
	public static final String url_falseDataViewFromExcle_productLabel ="/productLabel/falseDataViewFromExcle";//productLabel
	public static final String url_falseDataViewFromExcle_groupClass ="/groupClass/falseDataViewFromExcle";//groupClass
	public static final String url_falseDataViewFromExcle_segWord ="/segWordLtb/falseDataViewFromExcle";//segWord
	public static final String url_falseDataViewFromExcle_dimIaUserAgentKey ="/uakey/falseDataViewFromExcle";//dimIaUserAgentKey
	public static final String url_falseDataViewFromExcle_dimIaUserAgentNoise ="/uanoise/falseDataViewFromExcle";//dimIaUserAgentNoise
	public static final String url_falseDataViewFromExcle_dimIaUserAgentRule ="/uarule/falseDataViewFromExcle";//dimIaUserAgentRule
	public static final String url_falseDataViewFromExcle_contentLabel ="/content/falseDataViewFromExcle";//contentLabel
	public static final String url_falseDataViewFromExcle_dimIaSensiWordLtb ="/sensi/falseDataViewFromExcle";//dimIaSensiWordLtb
	public static final String url_falseDataViewFromExcle_DepthRuleCheck="/depthRuleCheck/falseDataViewFromExcle";
	public static final String url_falseDataViewFromExcle_positionRule="/positionRule/falseDataViewFromExcle";//dimIaPostionRule
	/**
	 * 每一次获取要批量插入的数据
	 * @param list（有效数据）
	 * @param j（第j次）
	 * @return
	 */
	public static List<OperationFlow> getPlInsertDataOnce(List<OperationFlow> list,Integer j)throws Exception{
		//500条一组插入数据库
		List<OperationFlow> list_true_insert = new ArrayList<OperationFlow>(); //要插入的500条数
		list_true_insert.clear();
		if(j ==list.size()/PL_NUMBERS_ONCE){
			for(int i=(j*PL_NUMBERS_ONCE);i<(list.size());i++){
				list_true_insert.add(list.get(i));
			}
		}else{
			for(int i=(j*PL_NUMBERS_ONCE);i<((j+1)*PL_NUMBERS_ONCE);i++){
				list_true_insert.add(list.get(i));
			}
		}
		return list_true_insert;
	}
	public static List<ProductInfo> getPlInsertDataOnceJm(List<ProductInfo> list,Integer j)throws Exception{
		//500条一组插入数据库
		List<ProductInfo> list_true_insert = new ArrayList<ProductInfo>(); //要插入的500条数
		list_true_insert.clear();
		if(j ==list.size()/PL_NUMBERS_ONCE){
			for(int i=(j*PL_NUMBERS_ONCE);i<(list.size());i++){
				list_true_insert.add(list.get(i));
			}
		}else{
			for(int i=(j*PL_NUMBERS_ONCE);i<((j+1)*PL_NUMBERS_ONCE);i++){
				list_true_insert.add(list.get(i));
			}
		}
		return list_true_insert;
	}
	

	
	/**
	 * DomainFuzzyRule
	 * 检查excle中是否有重复数据
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<OperationFlow> checkRepeatInExcleDomainFuzzyRule(List<OperationFlow> list) throws Exception {
		hashSetInExcle.clear();//清空缓存
		listRepeatInExcle.clear();//清空缓存
		
		for (OperationFlow operationFlow : list) {
			DomainFuzzyRule vo = (DomainFuzzyRule) operationFlow;
			String prodid = vo.getProdid();//产品ID
			String parseRule = vo.getParseRule();//截取规则
			
			//没有重复则插入缓存中
			if(!hashSetInExcle.contains(prodid+"~"+parseRule)){
				hashSetInExcle.add(prodid+"~"+parseRule);
			}
			else{
				listRepeatInExcle.add(operationFlow);
			}
		}
		return listRepeatInExcle;
	}
	
	
	/**
	 * DomainFuzzyRule
	 * 过滤重构list
	 * @param list
	 * @param hashSetInDataBase
	 * @return
	 * @throws Exception
	 */
	public static Map<String,List<OperationFlow>> filterListDomainFuzzyRule(List<OperationFlow> list,HashSet<String> hashSetInDataBase) throws Exception {
		user = LoginAction.getLoginUser();
		
		list_true.clear();//清空缓存
		list_false.clear();//清空缓存
		Map<String,List<OperationFlow>> map = new HashMap<String,List<OperationFlow>>();
		
		for (OperationFlow operationFlow : list) {
			String UUID = UUIDUtil.createUUID();//uuid
			DomainFuzzyRule vo = (DomainFuzzyRule) operationFlow;
			String prodid = vo.getProdid();//产品ID
			String parseRule= vo.getParseRule();//截取规则
			if(hashSetInDataBase.contains(prodid+"~"+parseRule)){
				list_false.add(operationFlow);//无效数据（联合主键重复等原因）
			}else{
				vo.setId(UUID);
				vo.setAuthor(user.getUserName());
				vo.setOpTime(sdf.format(new Date()));//设置时间
				list_true.add(operationFlow);//有效数据
			}
		}
		map.put("list_true", list_true);
		map.put("list_false", list_false);
		return map;
	}
	
	/**
	 * GroupClass
	 * 检查excle中是否有重复数据
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<OperationFlow> checkRepeatInExcleGroupClass(List<OperationFlow> list) throws Exception {
		hashSetInExcle.clear();//清空缓存
		listRepeatInExcle.clear();//清空缓存
		
		for (OperationFlow operationFlow : list) {
			GroupClass vo = (GroupClass) operationFlow;
			String prodid = vo.getProdid();//产品ID
			String classCode = vo.getClassCode();
			String subClassCode=vo.getSubClassCode();
			//没有重复则插入缓存中
			if(!hashSetInExcle.contains(prodid+"~"+classCode+"~"+subClassCode)){
				hashSetInExcle.add(prodid+"~"+classCode+"~"+subClassCode);
			}
			else{
				listRepeatInExcle.add(operationFlow);
			}
		}
		return listRepeatInExcle;
	}
	
	
	/**
	 * GroupClass
	 * 过滤重构list
	 * @param list
	 * @param hashSetInDataBase
	 * @return
	 * @throws Exception
	 */
	public static Map<String,List<OperationFlow>> filterListGroupClass(List<OperationFlow> list,HashSet<String> hashSetInDataBase) throws Exception {
		user = LoginAction.getLoginUser();
		
		list_true.clear();//清空缓存
		list_false.clear();//清空缓存
		Map<String,List<OperationFlow>> map = new HashMap<String,List<OperationFlow>>();
		
		for (OperationFlow operationFlow : list) {
			String UUID = UUIDUtil.createUUID();//uuid
			GroupClass vo = (GroupClass) operationFlow;
			String prodid = vo.getProdid();//产品ID
			String classCode = vo.getClassCode();
			String subClassCode=vo.getSubClassCode();
			if(hashSetInDataBase.contains(prodid+"~"+classCode+"~"+subClassCode)){
				list_false.add(operationFlow);//无效数据（联合主键重复等原因）
			}else{
				vo.setId(UUID);
				vo.setAuthor(user.getUserName());
				vo.setOpTime(sdf.format(new Date()));//设置时间
				list_true.add(operationFlow);//有效数据
			}
		}
		map.put("list_true", list_true);
		map.put("list_false", list_false);
		return map;
	}
	/**
	 * ProductLabel
	 * 检查excle中是否有重复数据
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<OperationFlow> checkRepeatInExcleProductLabel(List<OperationFlow> list) throws Exception {
		hashSetInExcle.clear();//清空缓存
		listRepeatInExcle.clear();//清空缓存
		
		for (OperationFlow operationFlow : list) {
			ProductLabel vo = (ProductLabel) operationFlow;
			String labelCode=vo.getLabelCode();
			//没有重复则插入缓存中
			if(!hashSetInExcle.contains(labelCode)){
				hashSetInExcle.add(labelCode);
			}
			else{
				listRepeatInExcle.add(operationFlow);
			}
		}
		return listRepeatInExcle;
	}
	
	
	/**
	 * ProductLabel
	 * 过滤重构list
	 * @param list
	 * @param hashSetInDataBase
	 * @return
	 * @throws Exception
	 */
	public static Map<String,List<OperationFlow>> filterListProductLabel(List<OperationFlow> list,HashSet<String> hashSetInDataBase) throws Exception {
		user = LoginAction.getLoginUser();
		
		list_true.clear();//清空缓存
		list_false.clear();//清空缓存
		Map<String,List<OperationFlow>> map = new HashMap<String,List<OperationFlow>>();
		
		for (OperationFlow operationFlow : list) {
			String UUID = UUIDUtil.createUUID();//uuid
			ProductLabel vo = (ProductLabel) operationFlow;
			String labelCode=vo.getLabelCode();
			if(hashSetInDataBase.contains(labelCode)){
				list_false.add(operationFlow);//无效数据（联合主键重复等原因）
			}else{
				vo.setId(UUID);
				vo.setAuthor(user.getUserName());
				vo.setOpTime(sdf.format(new Date()));//设置时间
				list_true.add(operationFlow);//有效数据
			}
		}
		map.put("list_true", list_true);
		map.put("list_false", list_false);
		return map;
	}
	/**
	 * SegWordLtb
	 * 检查excle中是否有重复数据
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<OperationFlow> checkRepeatInExcleSegWordLtb(List<OperationFlow> list) throws Exception {
		hashSetInExcle.clear();//清空缓存
		listRepeatInExcle.clear();//清空缓存
		
		for (OperationFlow operationFlow : list) {
			SegWordLtb vo = (SegWordLtb) operationFlow;
			String segWord = vo.getSegWord();
			//没有重复则插入缓存中
			if(!hashSetInExcle.contains(segWord)){
				hashSetInExcle.add(segWord);
			}
			else{
				listRepeatInExcle.add(operationFlow);
			}
		}
		return listRepeatInExcle;
	}
	
	
	/**
	 * SegWordLtb
	 * 过滤重构list
	 * @param list
	 * @param hashSetInDataBase
	 * @return
	 * @throws Exception
	 */
	public static Map<String,List<OperationFlow>> filterListSegWordLtb(List<OperationFlow> list,HashSet<String> hashSetInDataBase) throws Exception {
		user = LoginAction.getLoginUser();
		
		list_true.clear();//清空缓存
		list_false.clear();//清空缓存
		Map<String,List<OperationFlow>> map = new HashMap<String,List<OperationFlow>>();
		
		for (OperationFlow operationFlow : list) {
			String UUID = UUIDUtil.createUUID();//uuid
			SegWordLtb vo = (SegWordLtb) operationFlow;
			String segWord = vo.getSegWord();
			if(hashSetInDataBase.contains(segWord)){
				list_false.add(operationFlow);//无效数据（联合主键重复等原因）
			}else{
				vo.setId(UUID);
				vo.setAuthor(user.getUserName());
				vo.setOpTime(sdf.format(new Date()));//设置时间
				list_true.add(operationFlow);//有效数据
			}
		}
		map.put("list_true", list_true);
		map.put("list_false", list_false);
		return map;
	}
	
	/**
	 * ContentLabel
	 * 检查excle中是否有重复数据
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static List<OperationFlow> checkRepeatInExcleContentLabel(List<OperationFlow> list) throws Exception {
		hashSetInExcle.clear();//清空缓存
		listRepeatInExcle.clear();//清空缓存
		
		for (OperationFlow operationFlow : list) {
			ContentLabel vo = (ContentLabel) operationFlow;
			String contentLabel=vo.getContentLabelCode()+"~"+vo.getContentLabelName();
					
			//没有重复则插入缓存中
			if(!hashSetInExcle.contains(contentLabel)){
				hashSetInExcle.add(contentLabel);
			}
			else{
				listRepeatInExcle.add(operationFlow);
			}
		}
		return listRepeatInExcle;
	}
	
	
	/**
	 * ContentLabel
	 * 过滤重构list
	 * @param list
	 * @param hashSetInDataBase
	 * @return
	 * @throws Exception
	 */
	public static Map<String,List<OperationFlow>> filterListContentLabel(List<OperationFlow> list,HashSet<String> hashSetInDataBase) throws Exception {
		user = LoginAction.getLoginUser();
		
		list_true.clear();//清空缓存
		list_false.clear();//清空缓存
		Map<String,List<OperationFlow>> map = new HashMap<String,List<OperationFlow>>();
		
		for (OperationFlow operationFlow : list) {
			String UUID = UUIDUtil.createUUID();//uuid
			ContentLabel vo = (ContentLabel) operationFlow;
			String contentLabel=vo.getContentLabelCode()+"~"+vo.getContentLabelName();
			if(hashSetInDataBase.contains(contentLabel)){
				list_false.add(operationFlow);//无效数据（联合主键重复等原因）
			}else{
				vo.setId(UUID);
				vo.setAuthor(user.getUserName());
				vo.setOpTime(sdf.format(new Date()));//设置时间
				list_true.add(operationFlow);//有效数据
			}
		}
		map.put("list_true", list_true);
		map.put("list_false", list_false);
		return map;
	}
	
	
	
	
	/**
	 * excle中问题数据提示信息
	 * @param request
	 * @throws Exception
	 */
	public static void excleFalseData_Info(HttpServletRequest request) throws Exception{
		//提示信息
		request.setAttribute("info_invalidDataInExcle_repeatPrimaryKey",info_invalidDataInExcle_repeatPrimaryKey);
		request.setAttribute("info_invalidDataInExcle_empty",info_invalidDataInExcle_empty);
		request.setAttribute("info_invalidDataInExcle_tooLong",info_invalidDataInExcle_tooLong);
		request.setAttribute("info_invalidDataInExcle_notTrue_prodId",info_invalidDataInExcle_notTrue_prodId);
		request.setAttribute("info_invalidDataInExcle_notTrue_prodLabelCode",info_invalidDataInExcle_notTrue_prodLabelCode);
		request.setAttribute("info_invalidDataInExcle_notTrue_groupType",info_invalidDataInExcle_notTrue_groupType);
		request.setAttribute("info_list_false_cache",info_list_false_cache);
	}
	
	/**
	 * excle中问题数据tab页名称
	 * @param request
	 * @throws Exception
	 */
	public static void excleFalseData_TabName(HttpServletRequest request) throws Exception{
		//tab页名称
		request.setAttribute("tab_name_invalidDataInExcle_repeatPrimaryKey",tab_name_invalidDataInExcle_repeatPrimaryKey);
		request.setAttribute("tab_name_invalidDataInExcle_empty",tab_name_invalidDataInExcle_empty);
		request.setAttribute("tab_name_invalidDataInExcle_tooLong",tab_name_invalidDataInExcle_tooLong);
		request.setAttribute("tab_name_invalidDataInExcle_notTrue_prodId",tab_name_invalidDataInExcle_notTrue_prodId);
		request.setAttribute("tab_name_invalidDataInExcle_notTrue_prodLabelCode",tab_name_invalidDataInExcle_notTrue_prodLabelCode);
		request.setAttribute("tab_name_invalidDataInExcle_notTrue_groupType",tab_name_invalidDataInExcle_notTrue_groupType);
		request.setAttribute("tab_name_list_false_cache",tab_name_list_false_cache);
	}
	
	//*****************实体类参数赋值*****************
	
	/**
	 * UrlPath(根据主键查找参数vo)
	 * 参数vo
	 * @param vo
	 * @return
	 */
	public static UrlPath getVoParam_UrlPath(UrlPath vo){
		
		UrlPath voParam = new UrlPath();//参数
		if(vo!=null){
			String host = vo.getHost();
			String regexpRule = vo.getRegexpRule();
			
			voParam.setHost(host);
			voParam.setRegexpRule(regexpRule);
		}
		return voParam;
	}
	
	/**
	 * UrlParameter(根据主键查找参数vo)
	 * 参数vo
	 * @param vo
	 * @return
	 */
	public static UrlParameter getVoParam_UrlParameter(UrlParameter vo){
		
		UrlParameter voParam = new UrlParameter();//参数
		if(vo!=null){
			String host = vo.getHost();
			String regexpRule = vo.getRegexpRule();
			
			voParam.setHost(host);
			voParam.setRegexpRule(regexpRule);
		}
		return voParam;
	}
	
	
	//****************************************公共方法start**************************************************************
	//****************************************公共方法start**************************************************************
	//****************************************公共方法start**************************************************************
	//****************************************公共方法start**************************************************************
	//****************************************公共方法start**************************************************************
	
	
	/**
	 * 将字符串分词进行加密
	 * @param name
	 * @return
	 */
	public static String strEncrypt(String name){
		String name_res = "";//将name参数分词加密
		if(name!=null && !"".equals(name)){
			//将字符换分词
			for(int i=0;i<name.length();i++){
				char str =name.charAt(i);//单个的字符
				String s = String.valueOf(str);
				s = DesUtil.encrypt(s);//加密
				name_res = name_res+PROD_NAME_SPLIT+s;
			}
			name_res = name_res.substring(1);//截去首字母~
		}
		return name_res;
	}
	
	/**
	 * 将字符串分词进行解密
	 * @param name
	 * @return
	 */
	public static String strDecrypt(String name){
		String name_res = "";//将name参数分词加密
		if(name!=null && !"".equals(name)){
			String[] name_array = name.split(PROD_NAME_SPLIT);
			for(int j=0;j<name_array.length;j++){
				String name_decrypt = DesUtil.decrypt(name_array[j]);//解密
				name_res = name_res+name_decrypt;
			}
		}
		return name_res;
	}
	
	/**
	 * 获取昨天的日期
	 * @return
	 */
	public static String getYesterday(){
		
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		String dateYesterday = sdf.format(date);
		
		return dateYesterday;
	}
	
	/**
	 * 获取昨天一个月前的日期
	 * @return
	 */
	public static String getLastMonth(){
		
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		date = calendar.getTime();
		String lastMonth = sdf.format(date);
		
		return lastMonth;
	}
	
	/**
	 * 获取当前月
	 * @return
	 */
	public static String getCurrentMonth(){
		
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 0);
		date = calendar.getTime();
		String currentMonth = sdf.format(date).substring(0,6);//201811,11月份
		
		return currentMonth;
	}
	
	/**
	 * 获取产品id的下一位
	 * @param prodId_max
	 * @return
	 */
	public static String getProdId_next(String prodId_max){
		
		String serialNumber = "";//序列号
		if(prodId_max!=null && !"".equals(prodId_max)){
			String head = prodId_max.substring(0,1);//头
			String num = prodId_max.substring(1);//数值
			Integer nextNum = Integer.parseInt(num)+1;
			serialNumber = head + String.valueOf(nextNum);
		}else{
			serialNumber = PRODID_HEAD +1;
		}
		
		return serialNumber;
	}
	
	/**
	 * 编辑json对象
	 * 当前主要是对产品名称加密
	 * @param jo
	 * @return
	 * @throws Exception
	 */
	public static JSONObject editJo(JSONObject jo)throws Exception{
		
		JSONObject joNew = jo;
		Set<String> set = joNew.keySet();//json对象的key集合
		String check=jo.getString("prodName");
		if(set.contains("prodName")&&!check.contains("~")){
			String prodName_value = jo.getString("prodName");
			if(prodName_value!=null && !"".equals(prodName_value)){
				prodName_value = strEncrypt(prodName_value);//加密
				joNew.put("prodName", prodName_value);
			}
		}
		if(set.contains("prodName")&&check.contains("~")){
			String prodName_value = jo.getString("prodName");
			String []a=prodName_value.split("~");
			String b="";
			for(int i=0;i<a.length;i++){
				if(a[i]!=null && !"".equals(a[i])){
					b+= strEncrypt(a[i]);//加密
					b+="|";
					
				}
			}
			b=b.substring(0, b.length()-1);
			joNew.put("prodName", b);
			
		}
		
		return joNew;
	}
	
	/**
	 * 编辑批量导入的参数
	 * @param entityName
	 * @param beanName
	 * @param type 导入类型（1：excel，2：txt）
	 * @param fieldNums
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap editDoImportParam(String entityName, String beanName,String type,String fieldNums) throws Exception{
		HashMap map = new HashMap();
		//excel
		if("1".equals(type)){
			
		}
		//txt
		else if("2".equals(type)){
			//产品单表的批量导入
			if("dimIaProductInfoService".equals(beanName)){
				entityName="com.bonc.dpi.dao.entity.ProductInfo";
				beanName="productInfoService";
				fieldNums="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21";
			}
		}
		
		map.put("entityName", entityName);
		map.put("beanName", beanName);
		map.put("fieldNums", fieldNums);
		
		return map;
	}
	
	/**
	 * 编辑批量导出的参数
	 * @param entityName
	 * @param beanName
	 * @param templateName
	 * @param fieldNums
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static HashMap editDoExportParam(String entityName, String beanName,String templateName,String fieldNums,String textFieldNums) throws Exception{
		HashMap map = new HashMap();
		
		//产品的批量导出
		if("dimIaProductInfoService".equals(beanName)){
			entityName="com.bonc.dpi.dao.entity.ProductInfo";
			beanName="productInfoService";
			templateName="dim_ia_product_info.xlsx";
			fieldNums="0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21";
		}
		
		map.put("entityName", entityName);
		map.put("beanName", beanName);
		map.put("templateName", templateName);
		map.put("fieldNums", fieldNums);
		map.put("textFieldNums", textFieldNums);
		return map;
	}
	
	
	/**
	 * 
	 * @param IP
	 * @return
	 */
	public static String trimIpSpaces(String IP) {
		// 去掉IP字符串前后所有的空格
		while (IP.startsWith(" ")) {
			IP = IP.substring(1, IP.length()).trim();
		}
		while (IP.endsWith(" ")) {
			IP = IP.substring(0, IP.length() - 1).trim();
		}
		return IP;
	}
	
	/**
	 * 判断是否是一个IP
	 * @param IP
	 * @return
	 */
	public static boolean isIp(String IP) {
		boolean b = false;
		IP = trimIpSpaces(IP);
		if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
			String s[] = IP.split("\\.");
			if (Integer.parseInt(s[0]) < 255)
				if (Integer.parseInt(s[1]) < 255)
					if (Integer.parseInt(s[2]) < 255)
						if (Integer.parseInt(s[3]) < 255)
							b = true;
		}
		return b;
	}
	
	/**
	 * 判断是否为整数 
	 * @param str 传入的字符串 
	 * @return 是整数返回true,否则返回false 
	 * @return
	 */
	public static boolean isInteger(String str) {  
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
		return pattern.matcher(str).matches();  
	}
	
	/**
	 * 判断是否是一个IP
	 * @param IP
	 * @return
	 */
	public static boolean isIpPort(String ipPort) {
		boolean b = false;
		if(ipPort!=null && !"".equals(ipPort)){
			if(ipPort.contains(":")){
				String[] ipPortArray = ipPort.split(":");
				String ip = ipPortArray[0];
				String port = ipPortArray[1];
				if(isIp(ip)){
					if(isInteger(port)){
						b = true;
					}
				}
			}
		}
		return b;
	}
	
	/**
	 * 是纯ip或者是ipPort
	 * @param str
	 * @return
	 */
	public static boolean isIpPortOrIp(String str) {
		boolean b = false;
		if(str!=null && !"".equals(str)){
			if(str.contains(":")){
				if(isIpPort(str)){
					b = true;
				}
			}else{
				if(isIp(str)){
					b = true;
				}
			}
		}
		return b;
	}
	
	
	/**
	 * 获取配置信息map
	 * @return
	 */
	public static Map<String,String> getMapConf(){
		
		Map<String,String> map = new HashMap<String,String>();
		PropertiesLoader pl = new PropertiesLoader("sysConfig.properties");
		
		String databaseType = pl.getString("database.type");
		String jsonName = pl.getString("map.province.json.name");
		String mapType = pl.getString("map.province.maptype");
		String maxDataNumForExcle = pl.getString("import.excle.maxDataNumForExcle");
		
		map.put("databaseType",databaseType);//数据库类型
		map.put("jsonName", jsonName);//地图json文件名
		map.put("mapType", mapType);//地图省份类型
		map.put("maxDataNumForExcle",maxDataNumForExcle);//导入excle的最大数
		
		return map;
	}

}