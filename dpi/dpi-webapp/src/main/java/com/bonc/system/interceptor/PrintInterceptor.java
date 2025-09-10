package com.bonc.system.interceptor;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.bonc.dpi.utils.PropertyUtil;

/**
 * 打印拦截器
 *
 */
@Order(2)
@Component
@Aspect
public class PrintInterceptor {
	
	protected final Logger LOG = LoggerFactory.getLogger(PrintInterceptor.class);
	
	private Boolean debugPrintSign = Boolean.parseBoolean(PropertyUtil.getProperty("debug.print.sign"));

	/**
	 * 获取系统方法
	 *
	 */
	@Pointcut("execution(* com.bonc.dpi..*.*(..))")
	public void getServiceMethod() {
	}

	/**
	 * 方法执行前
	 *
	 */
	@Before("getServiceMethod()")
	public void serviceMethodBefore(JoinPoint joinPoint) {
		if(debugPrintSign) {
			String classType = joinPoint.getTarget().getClass().getName();
			try {
				Class<?>  clazz = Class.forName(classType);
				String clazzName = clazz.getName();
				String methodName = joinPoint.getSignature().getName();
				String args = Arrays.toString(joinPoint.getArgs());

				Logger logger = LoggerFactory.getLogger(clazzName);
				String message = ">>> "+methodName+"(String...) start";
				message = message.replace("String...", args.substring(1, args.length()-1));
				logger.info(message);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 方法执行后
	 *
	 */
	@After("getServiceMethod()")
	public void serviceMethodAfter(JoinPoint joinPoint) {
		if(debugPrintSign) {
			String classType = joinPoint.getTarget().getClass().getName();
			try {
				Class<?>  clazz = Class.forName(classType);
				String clazzName = clazz.getName();
				String methodName = joinPoint.getSignature().getName();
				String args = Arrays.toString(joinPoint.getArgs());

				Logger logger = LoggerFactory.getLogger(clazzName);
				String message = "<<< "+methodName+"(String...) end";
				message = message.replace("String...", args.substring(1, args.length()-1));
				logger.info(message);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
}
