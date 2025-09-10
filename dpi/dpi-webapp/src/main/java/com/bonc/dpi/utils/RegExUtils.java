package com.bonc.dpi.utils;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class RegExUtils {

	private static final String isUrl = "^((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}))(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";

	private static final String isHost = "^((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)(\\.((25[0-5])|(2[0-4]\\d)|(1\\d\\d)|([1-9]\\d)|\\d)){3}$|^([a-zA-Z0-9]([a-zA-Z0-9\\-\\_]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}$";
	
	private static final String matchingHost = "^((http://)|(https://))?([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}(/)";
	
	private static final String isIp = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
	
	public static Boolean isHost(String s) {
		return doRegEx(s, isHost);
	}
	
	public static Boolean isUrl(String s) {
		return doRegEx(s, isUrl);
	}
	
	public static Boolean isIp(String s) {
		return doRegEx(s, isIp);
	}
	
	public static String getURLHost(String url) {
		String[] protocols = new String[] { "rtsp://" };
		String host = url;
		String protocol = "";
		host = host.replace("(PTR)(Domain name pointer)", "").trim();
		host = host.replace("(A)(Host address)", "").trim();
		if ("".equals(host)) {
			host = url;
			return host;
		}

		boolean flag = true;
		try {
			host = new URL(host).getHost();
		} catch (Exception e1) {
			flag = false;
		}
		boolean findProtocolFlag = false;
		if (flag) {
			return host;
		} else {
			for (String s : protocols) {
				if (host.indexOf(s) >= 0) {
					protocol = s;
					findProtocolFlag = true;
					break;
				}
			}
			host = host.replace(protocol, "");
			int end = host.indexOf("/", findProtocolFlag ? 5 : 8);
			if (end >= 0) {
				host = host.substring(0, end);
			}
			return "".equals(host) ? url : host;
		}
	}
	
	public static String getURLPort(String url) {
		try {
			Integer port = new URL(url).getPort();
			if(port != -1) {
				return port + "";
			}
			return "80";
		} catch (Exception e) {
			return "80";
		}
	}

	public static String getHost(String s) {
		String host = doRegEx(s, matchingHost, "0");
		if (!"".equals(host)) {
			return host.replaceAll("http:|https:|ftp:", "").replaceAll("/", "");
		}
		return "";
	}

	public static Boolean doRegEx(String s, String regEx) {
		if(StringUtils.isBlank(regEx)) {
			return false;
		}
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(s);
		return m.find();
	}

	public static String doRegEx(String s, String regEx, String group) {
		if(StringUtils.isBlank(group)) {
			return "";
		}
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(s);
		if (m.find()) {
			String returnStr = m.group(Integer.valueOf(group));
			return returnStr;
		}
		return "";
	}
	
	public static int countInnerStr(String str, String patternStr) {
		int count = 0;
		final Pattern r = Pattern.compile(patternStr);
		final Matcher m = r.matcher(str);
		while (m.find()) {
			count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
//		String s = "https://mp.weixin.qq.com/s?__biz=MzAxNDA3Mzk1OA==&mid=2652975350&idx=2&sn=a8e01d51a6d534f70dfd6671560f326d&chksm=804d79ffb73af0e9bc16e83d2cba6f66d9688c2e75f9d8e6c88afac328fcdb86e627492dacbe&mpshare=1&scene=2&srcid=0414OHfD8u5L1s5X6RV25amH&from=timeline&ascene=2&devicetype=android-27&version=2700033c&nettype=3gnet&abtest_cookie=BAABAAoACwASABMABQAjlx4AXJkeAMWZHgDVmR4A3JkeAAAA&lang=zh_CN&pa";
//		String regEx = "biz(%3D|=)(\\w+)==";
//		System.out.println(doRegEx(s, regEx, "2"));
		//String GreenXin="'99999'";
		String regexp = "'";
		
		String str = "'GreenXin''";
		System.out.println(str.contains(regexp));
//		str=str.replaceAll(regexp, ""); 
//		System.out.println("结果为======="+str);
	}
}
