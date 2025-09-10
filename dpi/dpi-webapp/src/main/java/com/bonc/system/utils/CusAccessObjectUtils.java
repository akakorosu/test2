package com.bonc.system.utils;

import javax.servlet.http.HttpServletRequest;

import com.bonc.dpi.utils.StringUtils;

/**
 * 自定义访问对象工具类
 * 
 * 获取对象的IP地址等信息
 * 
 * @author X-rapido
 * 
 */
public class CusAccessObjectUtils {

	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
	 * 
	 * 用户真实IP为： 192.168.1.110
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
//		Enumeration<String> es = request.getHeaderNames();
//		while(es.hasMoreElements()) {
//			System.out.println(es.nextElement());
//		}
		String ip = "";
		try {
			ip = request.getHeader("x-forwarded-for");
			if (!StringUtils.isNull(ip)) {
			    // 多次反向代理后会有多个ip值，第一个ip才是真实ip
			    if (ip.indexOf(",") != -1) {
			        ip = ip.split(",")[0];
			        return ip;
			    }
			}
			
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}else {
				return ip;
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}else {
				return ip;
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}else {
				return ip;
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}else {
				return ip;
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}else {
				return ip;
			}
		} catch (Exception e) {
			System.out.println("获取客户端ip异常！");
			e.printStackTrace();
		}
		return ip;
	}

}