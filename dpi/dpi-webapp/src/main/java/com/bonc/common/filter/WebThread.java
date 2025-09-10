package com.bonc.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebThread {

	//当前线程绑定HttpServletRequest
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	//当前线程绑定HttpServletResponse
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
	
	public static HttpServletRequest getRequest() {
		return requestLocal.get();
	}
	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}
	
	public static HttpServletResponse getResponse() {
		return responseLocal.get();
	}
	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}
	
	/**
	 * 获取HttpSession
	 * @return
	 */
	public static HttpSession getHttpSession() {
		HttpServletRequest request = WebThread.getRequest();
		if(request == null) {
			return null;
		}
		return request.getSession();
	}
	
	/**
	 * 获取tomcat本地路径
	 * @return
	 */
	public static String getTomcatPath() {
		String path = requestLocal.get().getServletContext().getRealPath("") + "\\";
		return path;
	}
	
	/**
	 * 获取web项目名称
	 * @return
	 */
	public static String getContextName() {
		String path = requestLocal.get().getServletContext().getContextPath().replace("/", "");
		return path;
	}
	
	/**
	 * 判断当前强求是否是ajax请求
	 * @return true 为ajax请求
	 */
	public static Boolean isAjax() {
		HttpServletRequest request = WebThread.getRequest();
		if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
                .getHeader("X-Requested-With") != null && request.getHeader(
                "X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
        	return false;
        } else {
        	return true;
        }
	}
}
