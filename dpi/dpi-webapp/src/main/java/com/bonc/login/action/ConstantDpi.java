package com.bonc.login.action;

public class ConstantDpi {

	//按用户数排序
	public final static String ORDER_BY_PEOPLES = "peoples";
	//按访问量排序
	public final static String ORDER_BY_NUMS = "nums";
	//按流量排序
	public final static String ORDER_BY_FLOWS = "flows";
	
	//按天查询
	public final static String SEARCH_BY_DAY = "day";
	//按月查询
	public final static String SEARCH_BY_MONTH = "month";
	
	//上升
	public final static String FLAG_UP = "up";
	//下降
	public final static String FLAG_DOWN = "down";
	//持平
	public final static String FLAG_H = "h";
	
	//内容标签
	public final static String LABEL_TYPE_0 = "0";
	//应用标签
	public final static String LABEL_TYPE_1 = "1";
	//终端标签
	public final static String LABEL_TYPE_2 = "2";
	
	//标签的最顶级的parentCode
	public final static String LABEL_CODE_TOP = "0";
	
	//搜索引擎关键字
	public final static String KEYWORD_TYPE_0 = "0";
	//电商关键字
	public final static String KEYWORD_TYPE_1 = "1";
	
	public final static String USR_USERID = "userid";
	public final static String USR_CITYID = "cityid";
	public final static String USR_TICKET = "ticket";
	
	public final static String SEPARATOR1 = "_";
	public final static String SEPARATOR2 = ":";
	
	// public final static String CLIENTIP = "10.68.41.73";
	
	// 获取用户身份认证信息url(请求示例： http://10.65.9.14:8081/biOpenApi/rest/UserService/getUserByTicket/ticket001/192.168.168.188)
	// public final static String GETUSERINFO_URL = "http://10.68.76.66/biOpenApi/rest/UserService/getUserByTicket/";
	
	// public final static String GETCITYDM_URL = "http://10.68.76.66/biOpenApi/rest/dataAuthorizationService/getResourceListForUser/";
	
	public static String clientip;
	public static String getuserinfourl;
	public static String getcitydmurl;
	
	public static String goldappCode;  	//4A金库模式业务系统编号
	public static String goldoperCode;	//4A金库模式敏感操作编号
	public static String goldserver;		//4A金库模式服务地址
	public static String menu_id;		//4A金库调用菜单ID
	public static String menu_name;		//4A金库调用菜单名称

	public static final String conditiontype = "1"; //前台日期查询条件(1-按月，2-按天、按月)
	
	public void setClientip(String clientip){
		ConstantDpi.clientip = clientip;
	}
	public void setGetuserinfourl(String getuserinfourl){
		ConstantDpi.getuserinfourl = getuserinfourl;
	}
	public void setGetcitydmurl(String getcitydmurl){
		ConstantDpi.getcitydmurl = getcitydmurl;
	}
	
	
	public static void setGoldappCode(String goldappCode) {
		ConstantDpi.goldappCode = goldappCode;
	}
	public static void setGoldoperCode(String goldoperCode) {
		ConstantDpi.goldoperCode = goldoperCode;
	}
	public static void setGoldserver(String goldserver) {
		ConstantDpi.goldserver = goldserver;
	}
	public static void setMenu_id(String menu_id) {
		ConstantDpi.menu_id = menu_id;
	}
	public static void setMenu_name(String menu_name) {
		ConstantDpi.menu_name = menu_name;
	}
	

	
}
