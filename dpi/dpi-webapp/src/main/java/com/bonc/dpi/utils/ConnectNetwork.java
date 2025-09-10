package com.bonc.dpi.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;

public class ConnectNetwork extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//验证域名备案
	// public static final String url = "http://icp.chinaz.com/";
	//验证域名Ip   
	//public static final String url = "http://ip.chinaz.com/";
	// https://mp.weixin.qq.com/s/                 g_9NwzvzCygP5HPgXXi9QA
	public static final String url = "https://mp.weixin.qq.com/s/";
	
	
	//链接网址，传入参数，获得返回结果
	public static String getresult(String text) {
		String INFO = null;
		StringBuffer sb = new StringBuffer();
		try {
			INFO = URLEncoder.encode(text, "UTF-8");
			String getUrl = url + INFO;
			URL queruUrl = new URL(getUrl);
			URLConnection connection = queruUrl.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String temp = "";
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println(text);
		} catch (MalformedURLException e) {
			System.out.println(text);
		} catch (IOException e) {
			System.out.println(text);
		}
		return sb.toString();
	}
	
	
	
	//获取域名是否备案
	public static boolean isValid(String domainCode) {
		boolean flag = false;
		String result = getresult(domainCode);
		if (result.indexOf("主办单位名称") != -1) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	
	//获取域名IP
	/* String url = "www.baidu.com";
		List<String> list = getIpWithDomainCode(url);
		for(String s:list){
			System.out.println(s);
		}
	 */
	public static List<String> getIpWithDomainCode(String url) {
		List<String> list = new ArrayList<>();
		String result = getresult(url);
		if (result.indexOf("域名解析失败") == -1) {
			String replace = "WhwtdWrap bor-b1s col-gray03";
			String[] results = result.split(replace);
			for (int i = 1; i < results.length; i++) {
				int start = results[i].indexOf(url + "</span>                    <span class=") + 57 + url.length();
				String newValue = results[i].substring(start, start + 33);
				int end = newValue.indexOf("</span>");
				list.add(newValue.substring(0, end));
			}
		}
		return list;
	}

	
	public static void main(String[] args) {
		String s = getresult("g_9NwzvzCygP5HPgXXi9QA");
		System.out.println(s);
	}

}
