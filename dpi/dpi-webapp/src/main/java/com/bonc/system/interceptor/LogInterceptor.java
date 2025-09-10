package com.bonc.system.interceptor;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.bonc.common.cst.CST;
import com.bonc.datasource.mpp.system.SystemDao;
import com.bonc.dpi.config.Constant;
import com.bonc.dpi.utils.StringUtils;
import com.bonc.system.dao.entity.LogEntity;
import com.bonc.system.dao.entity.SysInterfaceInfo;
import com.bonc.system.dao.entity.SysUser;
import com.bonc.system.service.SysOptService;
import com.bonc.system.utils.CusAccessObjectUtils;
import com.bonc.system.utils.DateToolUtils;
import com.bonc.system.utils.SystemUtils;

/**
 * 日志拦截器
 *
 */
@Order(1)
@Component
@Aspect
public class LogInterceptor {

	protected final Logger LOG = LoggerFactory.getLogger(LogInterceptor.class);

    @Resource
    private SystemDao systemDao;

    @Resource
    private SysOptService sysOptService;

	public static String SUBTYPE;
	public static String SYSID;
	public static String VISITWAY;
	public static String SERVERTYPENUM;
	public static String IFAUTH;
	public static boolean ISSETUSERACCOUNT; // 是否关联用户信息表，设置主账号等日志信息

	public static boolean ISWRITEALL; // 是否保存所有日志，不过滤userid异常数据
	public static String SERVERIP;
	public static String SERVERPORT;
	public static int randomNumMin;
	public static int randomNumMax;

	static {
		Properties prop = new Properties();
		InputStream in;
		try {
			in = LogInterceptor.class.getResourceAsStream("/reported-log-details.properties");
			prop.load(in);
			in.close();

			SUBTYPE = prop.getProperty("subType");
			SYSID = prop.getProperty("sysId");
			VISITWAY = prop.getProperty("visitWay");
			SERVERTYPENUM = prop.getProperty("serverTypeNum");
			IFAUTH = prop.getProperty("ifAuth");
			ISSETUSERACCOUNT = Boolean.parseBoolean(prop.getProperty("isSetUserAccount"));

			ISWRITEALL = Boolean.parseBoolean(prop.getProperty("is.write.all"));
			SERVERIP = prop.getProperty("server.ip");
			SERVERPORT = prop.getProperty("server.port");
			randomNumMin = Integer.parseInt(prop.getProperty("operate.id.random.min"));
			randomNumMax = Integer.parseInt(prop.getProperty("operate.id.random.max"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取调用接口方法
	 *
	 */
	@Pointcut("execution(* com.bonc.dpi.action..*.*(..))")
	public void getInterfaceMethod() {
	}

	/**
	 * 保存调用接口日志
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Around("getInterfaceMethod()")
	public Object saveLog(ProceedingJoinPoint joinPoint) throws Throwable {
		LOG.info("saveLog");
		Object obj = null;
		LogEntity logEntity = new LogEntity();
		boolean isWrite = true;
		SysInterfaceInfo interfaceInfo = null;

		try {
			// 获取接口名及方法名
			String clazzName = joinPoint.getTarget().getClass().getName();
			String methodName = joinPoint.getSignature().getName();
			String interfaceName = clazzName + "." + methodName;
			logEntity.setClazz_name(clazzName);
			logEntity.setMethod_name(methodName);
			LOG.info(interfaceName);
			interfaceInfo = this.sysOptService.getInterfaceInfo(clazzName, methodName);
			LOG.info(JSON.toJSONString(interfaceInfo));
			if(interfaceInfo == null) {
				isWrite = false;
			}else {
				HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
				String userId = "";
				try {
					SysUser user = (SysUser) request.getSession().getAttribute(CST.SESSION_SYS_USER_INFO);
					userId = user.getUserId();
				} catch (Exception e) {
					e.printStackTrace();
				}
				if(StringUtils.isNull(userId)) {
					LOG.info("经分从帐号为空" + "(调用方法："+ interfaceName +")");
					isWrite = false;
				}else {
					logEntity.setUser_id(userId);
					// 获取参数名
					Signature sig = joinPoint.getSignature();
					if (sig instanceof MethodSignature) {
						MethodSignature msig = (MethodSignature) sig;
						Object target = joinPoint.getTarget();
						Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
						StringBuilder method_name_list_builder = new StringBuilder();
						List<String> paramterNameList = getParamterName(currentMethod);
						if(paramterNameList != null) {
							for(int i=0; i<paramterNameList.size(); i++) {
								method_name_list_builder.append(paramterNameList.get(i));
								if(i < (paramterNameList.size()-1)) {
									method_name_list_builder.append(Constant.COMMON_STRING_SEG);
								}
							}
							logEntity.setMethod_name_list(method_name_list_builder.toString());
						}
					}

					// 获取参数值
					Object[] args = joinPoint.getArgs();
					StringBuilder method_data_list_builder = new StringBuilder();
					if(args != null) {
						for(int i=0; i<args.length; i++) {
							method_data_list_builder.append(args[i]);
							if(i < (args.length-1)) {
								method_data_list_builder.append(Constant.COMMON_STRING_SEG);
							}
						}
						String methodDataListStr = method_data_list_builder.toString();
						logEntity.setMethod_data_list(methodDataListStr.length() > 1000 ? methodDataListStr.substring(0, 1000) : methodDataListStr);
					}

					setLogField(logEntity, interfaceInfo);
					logEntity.setLog_start_date(DateToolUtils.getCurrentDateTime(DateToolUtils.YYYY_MM_DD_HH_MM_SS_SSS));

					//////////////////// 以下是日志上报新增的字段 start ////////////////////
					// 设置日志字段信息（上报-默认值）
					setReportDefaultLogField(logEntity);

					// 设置日志字段信息（上报）
					setReportLogField(logEntity, interfaceInfo, request);
					////////////////////以下是日志上报新增的字段 end ////////////////////
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 接口具体调用
		obj = joinPoint.proceed();
		LOG.info(JSON.toJSONString(isWrite));
		// 如果与接口信息表不匹配，不保存，直接返回调用结果
		if(isWrite) {
			LOG.info("isWrite");
			try {
				logEntity.setLog_end_date(DateToolUtils.getCurrentDateTime(DateToolUtils.YYYY_MM_DD_HH_MM_SS_SSS));

				logEntity.setSubAccount(logEntity.getUser_id());// 从账号

				if(!ISWRITEALL) {
					// 如果userId等于空，或等于测试账号，不保存日志
					String userId = logEntity.getUser_id();
					if(StringUtils.isNull(userId)) {
						isWrite = false;
					}
				}

				// 这里的isWrite判断，主要是过滤userid异常数据
				if(isWrite) {
					if(SystemUtils.isProEnv()) {
//						this.systemDao.insertLog(logEntity); // 保存日志
						this.systemDao.insertLogReportSecond(logEntity); // 日志上报
						LOG.info("日志上报：" + "("+ logEntity.toString() +")");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return obj;
	}

	/**
	 * 设置日志字段信息
	 * @param logEntity
	 * @param interfaceInfo
	 */
	private void setLogField(LogEntity logEntity, SysInterfaceInfo interfaceInfo) {
		logEntity.setFunc_code(interfaceInfo.getFunc_code());
		logEntity.setFunc_name(interfaceInfo.getFunc_name());

		String method_name_list = logEntity.getMethod_name_list();
		String method_data_list = logEntity.getMethod_data_list();
		if(!StringUtils.isNull(method_name_list) && !StringUtils.isNull(method_data_list)) {
			String[] argsName = method_name_list.split(Constant.COMMON_STRING_SEG);
			String[] argsData = method_data_list.split(Constant.COMMON_STRING_SEG);
			if(argsName.length >= argsData.length) {
				for(int index=0; index<argsData.length; index++) {
					String fieldName = argsName[index];
					// 设置point_code
					if(fieldName.equals(interfaceInfo.getPoint_code())) {
						logEntity.setPoint_code(argsData[index]);
						continue;
					}
				}
			}
		}
	}

	/**
	 * 设置日志字段信息（上报-默认值）
	 * @param log
	 */
	private void setReportDefaultLogField(LogEntity log) {
		log.setSubType(SUBTYPE);//从账号类型
		log.setSubIfSensitive("1");//从帐号是否涉敏
		log.setSysId(SYSID);//系统编号
		log.setVisitWay(VISITWAY);//访问方式
		log.setServerIp(SERVERIP);//服务端IP
		log.setServer_port(SERVERPORT);
		log.setFuzzify("0");//是否模糊化
		log.setInvolvePhone("");//涉及手机号
		log.setServerTypeNum(SERVERTYPENUM);//业务类型编号
		log.setIfAuth(IFAUTH);//是否用户授权
		log.setAuthType("");//用户授权凭证类型
		log.setUserauthId("");//用户授权凭证ID
		// update at 2019-12-27 by luliang 客户要求，是否批量操作字段统一置为0
		log.setBatchOper("0");//是否批量操作
	}

	// 设置日志字段信息（上报）
	private void setReportLogField(LogEntity logEntity, SysInterfaceInfo interfaceInfo, HttpServletRequest request) {
		String nowStr = DateToolUtils.getCurrentDateTime(DateToolUtils.DATE_FORMAT);
		String operateId = "";// 操作ID如果是涉及金库就是金库的流水号，否则是时间戳加随机数
		String operDate = "";// 操作时间，敏感日志的操作时间为调用金库开始时间。
		String endOperTime = "";// 操作结束时间（只有敏感日志的操作结束时间和开始时间不一样，其他开始和结束为同一时间）
		String operTypeNum = ""; // 操作类型编号
		String ifsensitive = "";// 操作内容是否涉敏
		String goldbankId = "";// 金库申请ID
		String goldbank = "0";
		String senID = "G1307"+interfaceInfo.getFunc_code();// 敏感数据内容编码,如果操作内容不涉敏，该字段可为空

		// 非金库接口
		operateId = nowStr + creatRandomNum();
		operDate = nowStr;
		endOperTime = nowStr;
		operTypeNum = "4";// 4 - 查询
		ifsensitive = "0";// 0 – 不涉敏

		// 设置日志
		String clientIp = CusAccessObjectUtils.getIpAddress(request);
		logEntity.setClientIp(clientIp == null ? "" : clientIp);// 客户端IP
//		logEntity.setOperName(interfaceInfo.getModule_name() + Constant._COMMON_STRING_SEG + interfaceInfo.getFunc_name());// 操作业务名称及操作资源名称

		// 可以使用上一行注释的代码，interfaceInfo.getFunc_name()。
		// 由于点击一个页面只保存一次日志，所以统一为“xxx-查询”。
		logEntity.setOperName("新上网行为分析" + Constant._COMMON_STRING_SEG + interfaceInfo.getModule_name()+ "-查询");// 操作业务名称及操作资源名称
		logEntity.setOp_time(Integer.parseInt(DateToolUtils.getCurrentDateTime(DateToolUtils.yyMMdd)));// 抽取账期
		logEntity.setLogDate(DateToolUtils.getCurrentDateTime(DateToolUtils.yyMMdd));//日期

		logEntity.setOperateId(operateId);// 操作ID
		logEntity.setOperDate(operDate);// 操作时间
		logEntity.setEndOperTime(endOperTime);
		logEntity.setOperTypeNum(operTypeNum);// 操作类型编号
		logEntity.setIfsensitive(ifsensitive);// 操作内容是否涉敏

		logEntity.setGoldbankId(goldbankId);// 金库申请ID：即金库流水号
		logEntity.setGoldbank(goldbank);// 是否通过金库审批
		logEntity.setSenID(senID);// 敏感数据内容编码
	}

	private int creatRandomNum() {
		Random rand = new Random();
		int MAX = randomNumMax;
		int MIN = randomNumMin;
		int randNumber =rand.nextInt(MAX - MIN + 1) + MIN;
		return randNumber;
	}

	// 获取参数名称
	public static List<String> getParamterName(Method method) {
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] params = u.getParameterNames(method);
		if(params != null) {
			return Arrays.asList(params);
		}else {
			return null;
		}
	}

	public static void main(String[] args) {
		LogInterceptor utils = new LogInterceptor();
		LogEntity log = new LogEntity();
		log.setMethod_name_list("userId,kpiTypeCode,cityId,queryDate");
		log.setMethod_data_list("xuzeshuai,ZB00002,999,20190218");
		utils.setLogField(log, null);
		System.out.println(log.getUser_id());
		System.out.println(log.getPoint_code());
	}

}
