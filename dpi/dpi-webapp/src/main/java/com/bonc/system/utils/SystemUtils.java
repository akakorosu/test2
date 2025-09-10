package com.bonc.system.utils;

import com.bonc.dpi.utils.PropertyUtil;

public class SystemUtils {

	private static String environmentInfo = PropertyUtil.getProperty("environment.info");
	
	private static final String ENV_TEST = "test"; // 测试环境
	private static final String ENV_PRO = "pro"; // 生产环境
	
	/**
	 * 判断当前工程是否为生产环境
	 * @return
	 */
	public static boolean isProEnv() {
		if(ENV_PRO.equals(environmentInfo)) {
			return true;
		}else if(ENV_TEST.equals(environmentInfo)) {
			return false;
		}else {
			return false;
		}
	}
	
	public static boolean printAllSqlInfo() {
		return Boolean.getBoolean(PropertyUtil.getProperty("all.sql.print"));
	}
	
}
