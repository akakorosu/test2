package com.bonc.login.action;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bonc.common.annotation.ArchivesLog;
import com.bonc.common.cst.CST;
import com.bonc.common.filter.WebThread;
import com.bonc.common.utils.Ajax;
import com.bonc.dpi.dao.mapper.UserinfoMapper;
import com.bonc.dpi.utils.StringUtils;
import com.bonc.login.service.LoginService;
import com.bonc.system.dao.entity.SysUser;

@RequestMapping( value = "/")
@Controller
public class LoginAction {
	public static final String OPTION_MC_SJ = " --- 全省 --- ";
	private static final String ISOP_TICKET = "bonc-isop";
	public static final String ORG_CODE_SJ = "999";
	public static final String AREA_CODE = "area_code";
	public static final String AREA_NAME = "area_name";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginAction.class);
	
	@Resource(name="loginService")
	private LoginService loginService;
	
	@Resource
	private UserinfoMapper userinfoMapper;
	@ResponseBody
	@RequestMapping(value="/nologin1")
	public String nologin1(HttpSession session, HttpServletRequest request) {
		String userid = request.getParameter("userid");
		String ticket = request.getParameter("ticket");
		String debugFlag = request.getParameter("debugFlag");
		String clientip="";
		List<City> cityList = null;
		System.out.println("-userid-"+userid);
		System.out.println("-ticket-"+ticket);
		System.out.println("-debugFlag-"+debugFlag);
		
		// 存在homepage方法里，设置不了session的情况，此处重新设置session信息
				Object sessionUserid = request.getSession().getAttribute("userid");
				if(sessionUserid == null || "".equals(sessionUserid.toString()) || "null".equals(sessionUserid.toString())) {
					LOGGER.info("重新设置session信息，userid=" + userid + "， clientip=" + clientip);
					request.getSession().setAttribute("userid", userid);
					request.getSession().setAttribute("clientip", clientip);
				}else {
					Object sessionClientip = request.getSession().getAttribute("clientip");
					//LOGGER.info("session信息已存在，userid=" + sessionUserid.toString() + "， clientip=" + sessionClientip.toString());
				}
		
				
		if(ISOP_TICKET.equals(ticket)) {
			// 绕过用户身份认证
			LOGGER.info("cross用户身份认证及数据权限，用于访问dpi标签功能[ticket="+ ticket +"]");
			System.out.println("-----绕过身份认证哦-----");
			LOGGER.info("-----userid------"+userid+"");
			
			
					
			if(cityList == null || cityList.size() == 0){
				cityList = new ArrayList<City>();
				Map<String, String> paramMap = new HashMap<String, String>();
				paramMap.put("cityds_dm", null);
				cityList = this.userinfoMapper.selectByCitydm(paramMap);
				
				City totalcity = new City();
				totalcity.setCityds_dm(ORG_CODE_SJ);
				totalcity.setCityds_mc(OPTION_MC_SJ);
				totalcity.setIsSelected("1");
				List<City> tmplist = new ArrayList<City>();
				tmplist.add(totalcity);
				for(City c : cityList){
					tmplist.add(c);
				}
				cityList = tmplist;
			}
			
			
		
			String cityId=cityList.get(0).getCityds_dm().toString();
			
			
			System.out.println("---cityId::--"+cityId);
			SysUser vo = this.loginService.nologin1(cityId,userid);
			System.out.println("----"+JSON.toJSONString(vo));
			if(vo == null || StringUtils.isNull(vo.getUserId())) {
				return "-1";
			} else {
				session.setAttribute(CST.SESSION_SYS_USER_INFO, vo);
				return JSONObject.toJSON(vo).toString();
			}
			
			
			
			
		}else {
			try {
				// 由于没有经分系统权限，无法测试真实用户访问情况，以下代码纯粹为了测试！！！！！
				// 以下逻辑用于测试真实用户访问情况，生产环境不会调用以下逻辑！！！！！
				// 在传递参数时，ticket，clientip两个参数值可以在生产环境的日志(catalina.out)中获取，同时传递debugFlag=1/2
				if(!isNULL(debugFlag) && ("1".equals(debugFlag) || "2".equals(debugFlag))) {
					clientip = request.getParameter("clientip");
				
					LOGGER.info("test真实用户访问情况的参数："
							+ "debugFlag=" + debugFlag + ", ticket=" + ticket + ", clientip=" + clientip + ", userid=" + userid);
					if(!isNULL(userid)) {
						// 更敏捷的测试:直接传递userid，绕过身份认证
						// 由于绕过身份认证，需要进行用户信息缓存，以下魔数代码将省、市、区县三个测试用户的基本信息缓存
						//this.debugService.cacheTestUserInfo(testUserId);
						
			
						
						if(isNULL(debugFlag) || "1".equals(debugFlag)) { // 如果debugFlag=2，是本地调试，跳过访问数据权限接口
							try {
								if(ticket!=null && !ISOP_TICKET.equals(ticket)){
									if(userid == null || "".equals(userid) || "null".equals(userid)){
										userid = getUseridByTicket(ticket, clientip);
										if(userid != null){
											cityList = getCityListByUserid(userid);
										}
									}else{
										cityList = getCityListByUserid(userid);
									}
									LOGGER.info("通过亚信openapi获取用户信息[userid="+ userid +", cityList="+ cityList +"]");
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
						if(cityList == null || cityList.size() == 0){
							cityList = new ArrayList<City>();
							Map<String, String> paramMap = new HashMap<String, String>();
							paramMap.put("cityds_dm", null);
							cityList = this.userinfoMapper.selectByCitydm(paramMap);
							
							City totalcity = new City();
							totalcity.setCityds_dm(ORG_CODE_SJ);
							totalcity.setCityds_mc(OPTION_MC_SJ);
							totalcity.setIsSelected("1");
							List<City> tmplist = new ArrayList<City>();
							tmplist.add(totalcity);
							for(City c : cityList){
								tmplist.add(c);
							}
							cityList = tmplist;
						}
						
						
					
						String cityId=cityList.get(0).getCityds_dm().toString();
						
						
						System.out.println("---cityId::--"+cityId);
						SysUser vo = this.loginService.nologin1(cityId,userid);
						System.out.println("----"+JSON.toJSONString(vo));
						if(vo == null || StringUtils.isNull(vo.getUserId())) {
							return "-1";
						} else {
							session.setAttribute(CST.SESSION_SYS_USER_INFO, vo);
							return JSONObject.toJSON(vo).toString();
						}
						
						// 由于已经绕过身份认证，直接跳转
						/*forward = "redirect:/pages/jsp/login/homepage.jsp"
								+ "?ticket="+ticket+"&userid="+testUserId+"&clientip="+clientip+"&pageflag="+pageflag+"&debugFlag="+debugFlag;
						LOGGER.info("系统登录，用于测试异网号码识别功能");
						return forward;*/
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
		}
		
		
		//如果代码运行到此处，说明是生产环境正式调用
		//以下代码为实际生产用户调用逻辑
		
		if(isNULL(debugFlag)) {
			try {
				clientip = request.getHeader("x-forwarded-for"); // 获取header头重的真实客户端IP
				String[] aryClientIp = clientip.split(",");
				clientip = aryClientIp[0]; // 第一个是客户端的IP
			} catch (Exception e) {
				LOGGER.info("获取客户端ip异常！");
				e.printStackTrace();
			}
			
			userid = getUseridByTicket(ticket, clientip);
			if(userid != null){
				
				
				LOGGER.info("这里是正式环境获取userid");
				
				cityList = getCityListByUserid(userid);
				
				if(cityList == null || cityList.size() == 0){
					cityList = new ArrayList<City>();
					Map<String, String> paramMap = new HashMap<String, String>();
					paramMap.put("cityds_dm", null);
					cityList = this.userinfoMapper.selectByCitydm(paramMap);
					
					City totalcity = new City();
					totalcity.setCityds_dm(ORG_CODE_SJ);
					totalcity.setCityds_mc(OPTION_MC_SJ);
					totalcity.setIsSelected("1");
					List<City> tmplist = new ArrayList<City>();
					tmplist.add(totalcity);
					for(City c : cityList){
						tmplist.add(c);
					}
					cityList = tmplist;
				}
				
				
			
				String cityId=cityList.get(0).getCityds_dm().toString();
				
				
				System.out.println("---cityId::--"+cityId);
				SysUser vo = this.loginService.nologin1(cityId,userid);
				System.out.println("----"+JSON.toJSONString(vo));
				if(vo == null || StringUtils.isNull(vo.getUserId())) {
					return "-1";
				} else {
					session.setAttribute(CST.SESSION_SYS_USER_INFO, vo);
					return JSONObject.toJSON(vo).toString();
				}
				
			}
		}
		//将用户经分从账号和客户端ip存入session（生产时放开）
		request.getSession().setAttribute("userid", userid);
		request.getSession().setAttribute("clientip", clientip);
		LOGGER.info("系统登录，生产环境。设置session信息，userid=" + userid + "， clientip=" + clientip);
		
		}
		return null;
		
		
	
	}
	
	
	
	// 获取用户数据权限
	public List<City> getCityListByUserid(String userid){
			LOGGER.info("用户数据权限请求参数：userid=" + userid);
			List<City> cityList = new ArrayList<City>();
			String path = ConstantDpi.getcitydmurl + userid;
			System.out.println("---path::--"+path);
			JSONObject resultjo = getJSONByApi(path);
			LOGGER.info("用户数据权限信息["+ userid +"]：" + resultjo.toJSONString());
			
			try {
				loginService.putUserAuth(resultjo, userid);
			} catch (Exception e1) {
				LOGGER.info("缓存用户数据权限时报错！");
				e1.printStackTrace();
			}
			
			try {
				if (resultjo != null) {
					String status = resultjo.getString("status");
					if (status != null && "1".equals(status)) {
						JSONArray ja = resultjo.getJSONArray("result");
						for (int i = 0; i < ja.size(); i++) {
							JSONObject jo = ja.getJSONObject(i);
							String strParentId = jo.getString("strParentId");
							if ("-1".equals(strParentId)) {
								String cityid = jo.getString("strCityId");
								String cityname = jo.getString("strCityName");
								if(!"0".equals(cityid)){
									City city = new City();
									city.setCityds_dm(cityid);
									city.setCityds_mc(cityname);
									if(ORG_CODE_SJ.equals(cityid)){
										city.setCityds_dm(ORG_CODE_SJ);
										city.setCityds_mc(OPTION_MC_SJ);
										cityList.add(0, city);
									}else{									
										cityList.add(city);
									}
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return cityList;
		}
	private boolean isNULL(String str){
		if(str == null || "".equals(str.trim()) || "null".equals(str.trim())) {
			return true;
		}else {
			return false;
		}
	}
	// 获取用户身份信息
	public String getUseridByTicket(String ticket, String clientip) {
			LOGGER.info("用户身份认证请求参数：ticket=" + ticket + ", clientip=" + clientip);
			String userid = null;
			String path = ConstantDpi.getuserinfourl + ticket + "/" + clientip;
			JSONObject resultjo = getJSONByApi(path);
			LOGGER.info("用户身份信息：" + resultjo.toJSONString());
			try {
				if (resultjo != null) {
					String status = resultjo.getString("status");
					if (status != null && "1".equals(status)) {
						JSONObject userjo = resultjo.getJSONObject("result");
						userid = userjo.getString("strUserId");
						try {
							String strCityId = userjo.getString("strCityId");
							String strCityName = userjo.getString("strCityName");
							String strUserName = userjo.getString("strUserName");
							this.loginService.putUserInfo(userid, strCityId, strUserName, strCityName);
						} catch (Exception e) {
							LOGGER.info("缓存用户信息时报错！");
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return userid;
		}
		
		public static JSONObject getJSONByApi(String path){
			JSONObject resultjo = null;
			try {
				HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
				// conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("contentType", "utf-8");
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				int code = conn.getResponseCode();
				// 调用web服务
				if (code == 200) {
					InputStream is = conn.getInputStream(); // 以输入流的形式返回
					BufferedReader in = new BufferedReader(new InputStreamReader(is, "utf-8"));
					StringBuffer buffer = new StringBuffer();
					String line = "";
					while ((line = in.readLine()) != null) {
						buffer.append(line);
					}
					String jsonString = buffer.toString();
					resultjo = JSONObject.parseObject(jsonString);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return resultjo;
		}
	@ResponseBody
	@RequestMapping(value="/nologin")
	public String nologin(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "loginToken", required = true) String loginToken) {
		System.out.println(loginToken);
		SysUser vo = this.loginService.nologin(loginToken);
		System.out.println("----"+JSON.toJSONString(vo));
		if(vo == null || StringUtils.isNull(vo.getUserId())) {
			return "-1";
		} else {
			session.setAttribute(CST.SESSION_SYS_USER_INFO, vo);
			return JSONObject.toJSON(vo).toString();
		}
	}
	
	/**
	 * 登陆
	 * @param session
	 * @param request
	 * @param loginId
	 * @param password
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login")
	public String doLogin(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "loginId", required = true) String loginId,
			@RequestParam(value = "password", required = true) String password) {
		SysUser vo = this.loginService.doLogin(loginId);
		System.out.println(" ");
		System.out.println(" ");
		if(vo == null) {
			return "-2";
		} else if(!password.equals(vo.getPassword())) {
			return "-1";
		} else {
			session.setAttribute(CST.SESSION_SYS_USER_INFO, vo);
			return JSONObject.toJSON(vo).toString();
		}
	}
	
	public static SysUser getLoginUser() {
		SysUser user = (SysUser) WebThread.getRequest().getSession().getAttribute(CST.SESSION_SYS_USER_INFO);
		return user;
	}
	
	/**
	 * 登出
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/loginout")
	@ResponseBody
	public String doLoginOut(HttpSession session, HttpServletRequest request) {
		session.setAttribute(CST.SESSION_SYS_USER_INFO, null);//清空session中的当前登录用户
		return "1";
	}
	
	/**
	 * 检查session
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/checkSession")
	@ResponseBody
	public String checkSession(HttpSession session) {
		if (session.getAttribute(CST.SESSION_SYS_USER_INFO) == null) {
			return Ajax.responseString(CST.RES_SESSION_TIME_OUT, "用户已经过期，请重新登陆");
		}
		return Ajax.responseString(CST.RES_SECCESS);
	}
	
	/*@ArchivesLog(actionName="登录类",option="登录")
	@ResponseBody
	@RequestMapping(value="/login", method=RequestMethod.POST, produces="text/html;charset=utf-8")
	public String doLogin(@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "password", required = true) String password, HttpSession session, HttpServletRequest request){
		try {
			LoginEnum result = loginService.checkUserPassword(userName, password);
			if(LOGIN_SUCCESS.equals(result.value())){
				loginService.doLogin(session,userName,request);
			}
			return Ajax.responseString(result.value(), result.name());
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString("7", e.getMessage());
		}
	}*/

	@RequestMapping(value = "/noQx", method = RequestMethod.GET, produces = "text/html;charset=utf-8")
	@ResponseBody
	public String noQx(HttpSession session) {
		try {
			return Ajax.responseString(CST.CONN_TEST_FAIL2, "没有权限");
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
	/**
	 * 
	 * @Title: logOut
	 * @Description: 登出系统
	 * @param session
	 * @return
	 */
	@ArchivesLog(actionName="登录类",option="退出系统")
	@RequestMapping(value = "/loginOut", method = RequestMethod.POST, produces = "text/html;charset=utf-8")
	public @ResponseBody String logOut(HttpSession session, HttpServletRequest request) {
		try {
			String ip = request.getHeader("x-forwarded-for");
		    ip = request.getRemoteAddr();
			ip= ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
			session.invalidate();
			if (CST.LOGOUT_URL.isEmpty()) {
				// 登出URL准备
				String schema = request.getScheme();
				String serverName = request.getServerName();
				int port = request.getServerPort();
				String path = request.getContextPath();
				String baseUrl = schema + "://" + serverName + ":" + port + path+"/pages/jsp/login/login.jsp";
				return Ajax.responseString(CST.RES_SECCESS, null, baseUrl);
			} else {
				return Ajax.responseString(CST.RES_SECCESS, null, CST.LOGOUT_URL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Ajax.responseString(CST.RES_EXCEPTION, e.getMessage());
		}
	}
}
