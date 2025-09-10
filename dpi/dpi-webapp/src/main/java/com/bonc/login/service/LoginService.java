package com.bonc.login.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.bonc.dpi.cache.CacheService;
import com.bonc.dpi.config.Constant;
import com.bonc.dpi.utils.AES;
import com.bonc.dpi.utils.StringUtils;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysRoleUser;
import com.bonc.system.dao.entity.SysUser;
import com.bonc.system.service.SysRoleUserService;
import com.bonc.system.service.SysUserService;

@Service("loginService")
public class LoginService {
	public static final String KEY_SIGN = ":";
	public static final String _CACHE_KEY = "dpiUserAuth";
	public static final String CACHE_KEY_USERINFO = "userinfo";
	public static final String CACHE_KEY_USERAUTH_DS = "auth_ds";
	public static final String CACHE_KEY_USERAUTH_QX = "auth_qx";
	
	public static final String USERINFO_USERID = "userid";
	public static final String USERINFO_USERNAME = "username";
	public static final String USERINFO_CITYID = "cityid";
	public static final String USERINFO_CITYNAME = "cityname";
	public static final String USERINFO_LEVEL = "level";
	
	public static final String LEVEL_1 = "1";
	public static final String LEVEL_2 = "2";
	public static final String LEVEL_3 = "3";
	
    protected final Logger logger = LoggerFactory.getLogger(LoginService.class);
    
	
	@Resource
	private SysUserService sysUserService;
	
	@Resource
	private SysRoleUserService sysRoleUserService;
	
	@Resource
	private CacheService cacheService;
	
	
	@Resource
	private CacheManager cacheManager;
	
	
	@SuppressWarnings("unchecked")
	public boolean putUserAuth(JSONObject resultjo, String userId) {
		Cache cache = cacheManager.getCache(_CACHE_KEY);
		List<Map<String, String>> dsList = cache.get(CACHE_KEY_USERAUTH_DS + KEY_SIGN + userId, List.class);
		Map<String, List<Map<String, String>>> qxMap = cache.get(CACHE_KEY_USERAUTH_QX + KEY_SIGN + userId, Map.class);
		
		if((dsList != null && dsList.size() > 0) || (qxMap != null)) {
			logger.info("缓存中已经存在用户["+ userId +"]数据权限，不进行缓存操作");
			return true;
		}
		
		
		if (resultjo != null) {
			dsList = new ArrayList<Map<String, String>>();
			qxMap = new HashMap<String, List<Map<String, String>>>();
			String status = resultjo.getString("status");
			if (status != null && "1".equals(status)) {
				JSONArray ja = resultjo.getJSONArray("result");
				for (int i = 0; i < ja.size(); i++) {
					JSONObject jo = ja.getJSONObject(i);
					String strParentId = jo.getString("strParentId");
					String cityid = jo.getString("strCityId");
					String cityname = jo.getString("strCityName");

					Map<String, String> areaMap = new HashMap<String, String>();
					areaMap.put(LoginAction.AREA_CODE, cityid);
					areaMap.put(LoginAction.AREA_NAME, cityname);
					
					if ("-1".equals(strParentId)) {
						// parentId=-1的数据为地市（包括999）
						if(LoginAction.ORG_CODE_SJ.equals(cityid)){
							areaMap.put(LoginAction.AREA_NAME, LoginAction.OPTION_MC_SJ);
							dsList.add(0, areaMap);
						}else{
							dsList.add(areaMap);
						}
					}else {
						// 区县数据以地市编码（strParentId）做key进行分组
						List<Map<String, String>> qxlist = null;
						if(qxMap.containsKey(strParentId)) {
							qxlist = qxMap.get(strParentId);
							qxlist.add(areaMap);
						}else {
							qxlist = new ArrayList<Map<String, String>>();
							qxlist.add(areaMap);
							qxMap.put(strParentId, qxlist);
						}
					}
				}
			}
			
			cache.put(CACHE_KEY_USERAUTH_DS + KEY_SIGN + userId, dsList);
			cache.put(CACHE_KEY_USERAUTH_QX + KEY_SIGN + userId, qxMap);
			logger.info("缓存用户["+ userId +"]数据权限...");
		}
		return true;
	}
	
	public boolean putUserInfo(String userId, String cityId, String userName, String cityName) {
		Cache cache = cacheManager.getCache(_CACHE_KEY);
		
		@SuppressWarnings("unchecked")
		Map<String, String> userInfo = cache.get(CACHE_KEY_USERINFO + KEY_SIGN + userId, Map.class);
		if(userInfo != null && userId.equals(userInfo.get(USERINFO_USERID))) {
			logger.info("缓存中已存在用户["+ userId +"]信息："+ userInfo +"，不进行缓存操作");
			return true;
		}
		
		userInfo = new HashMap<String, String>();
		userInfo.put(USERINFO_USERID, userId);
		userInfo.put(USERINFO_USERNAME, userName);
		userInfo.put(USERINFO_CITYID, cityId);
		userInfo.put(USERINFO_CITYNAME, cityName);
		
		if(cityId!=null && cityId.length()==3) {
			if(LoginAction.ORG_CODE_SJ.equals(cityId)) {
				userInfo.put(USERINFO_LEVEL, LEVEL_1);
			}else {
				userInfo.put(USERINFO_LEVEL, LEVEL_2);
			}
		}else {
			userInfo.put(USERINFO_LEVEL, LEVEL_3);
		}
		cache.put(CACHE_KEY_USERINFO + KEY_SIGN + userId, userInfo);
		logger.info("缓存用户["+ userId +"]信息："+ userInfo +"...");
		
		return true;
	}
	
	public SysUser nologin1(String cityId,String userid) {
		SysUser vo = new SysUser();
		try {
		
				String desValue = userid+"|"+cityId;
				System.out.print("desValue---"+desValue);
				String[] values = desValue.split("\\|", -1);
				if(values.length == 2) {
					logger.info("查询缓存结果[解密后]:" + desValue);
					vo = createUser(values[0], values[1]);

					// 以DEFAULT_ROLE_USER_ID的角色为默认权限
					List<SysRoleUser> defaultRoleUserList = this.sysRoleUserService.selectListByUserId(Constant.DEFAULT_ROLE_USER_ID);
					List<SysRoleUser> sysRoleUserList = new ArrayList<>();
					for(SysRoleUser roleUser : defaultRoleUserList) {
						SysRoleUser targetUser = new SysRoleUser();
						BeanUtils.copyProperties(roleUser, targetUser);
						targetUser.setUserId(vo.getUserId());
						sysRoleUserList.add(targetUser);
					}
					if(sysRoleUserList.size() == 1) {
						vo.setLoginSysRoleUser(sysRoleUserList.get(0));
					}
					vo.setSysRoleUserList(sysRoleUserList);
				}else {
					logger.info("查询缓存结果格式不正确。");
				}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	public SysUser nologin(String loginToken) {
		SysUser vo = new SysUser();
		try {
			String cacheValue = cacheService.getStrCache(cacheService.obtainKey(Constant.CACHE_KEY_PREFIX, Constant._CACHE_KEY_LOGINTOKEN, loginToken));
			if(!StringUtils.isNull(cacheValue)) {
				
				String desValue = AES.decrypt(cacheValue);
				System.out.print("desValue---"+desValue);
				String[] values = desValue.split("\\|", -1);
				if(values.length == 2) {
					logger.info("查询缓存结果[解密后]:" + desValue);
					vo = createUser(values[0], values[1]);

					// 以DEFAULT_ROLE_USER_ID的角色为默认权限
					List<SysRoleUser> defaultRoleUserList = this.sysRoleUserService.selectListByUserId(Constant.DEFAULT_ROLE_USER_ID);
					List<SysRoleUser> sysRoleUserList = new ArrayList<>();
					for(SysRoleUser roleUser : defaultRoleUserList) {
						SysRoleUser targetUser = new SysRoleUser();
						BeanUtils.copyProperties(roleUser, targetUser);
						targetUser.setUserId(vo.getUserId());
						sysRoleUserList.add(targetUser);
					}
					if(sysRoleUserList.size() == 1) {
						vo.setLoginSysRoleUser(sysRoleUserList.get(0));
					}
					vo.setSysRoleUserList(sysRoleUserList);
				}else {
					logger.info("查询缓存结果格式不正确。");
				}
			}else {
				logger.info("查询缓存无结果，请登录后访问系统。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	
	private SysUser createUser(String userId, String cityId) {
		SysUser result = new SysUser();
		result.setUserId(userId);
		result.setLoginId(userId);
		result.setOrgId(cityId);
		return result;
	}
	
	@SuppressWarnings("unused")
	private void testToken() {
		String valueStr = "zhangmeinas|999";
		try {
			String cacheKey = cacheService.obtainKey(Constant.CACHE_KEY_PREFIX, Constant._CACHE_KEY_LOGINTOKEN, "test123");
			String cacheValue = AES.encrypt(valueStr);
			cacheService.setStrCacheWithExpireTime(cacheKey, cacheValue, 864000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SysUser doLogin(String loginId) {
		// TODO 去掉以下测试代码
		testToken();
		SysUser vo = this.sysUserService.selectListByLogin(loginId);
		if(vo != null) {
			List<SysRoleUser> sysRoleUserList = this.sysRoleUserService.selectListByUserId(vo.getUserId());
			if(sysRoleUserList.size() == 1) {
				vo.setLoginSysRoleUser(sysRoleUserList.get(0));
			}
			vo.setSysRoleUserList(sysRoleUserList);
		}
		return vo;
	}

}
