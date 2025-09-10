package com.bonc.system.service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.alibaba.fastjson.JSON;
import com.bonc.login.action.LoginAction;
import com.bonc.system.dao.entity.SysUser;
import com.bonc.system.utils.SystemUtils;


@Intercepts(value = {
		@Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }),
		@Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) })
public class SqlInterceptor implements Interceptor {
	
	private static Log logger = LogFactory.getLog("getSqlLog");

	@SuppressWarnings("rawtypes")
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();
		Object result = null;
		if (target instanceof Executor) {
			long start = System.currentTimeMillis();
			Method method = invocation.getMethod();
			/** 执行方法 */
			result = invocation.proceed();
			long end = System.currentTimeMillis();
			final Object[] args = invocation.getArgs();

			// 获取原始的ms
			MappedStatement ms = (MappedStatement) args[0];
			String commandName = ms.getSqlCommandType().name();
			String name = method.getName();
			if (commandName.startsWith("INSERT")) {
				name += "=新增";
			} else if (commandName.startsWith("UPDATE")) {
				name += "=修改";
			} else if (commandName.startsWith("DELETE")) {
				name += "=删除";
			} else if (commandName.startsWith("SELECT")) {
				name += "=查询";
			}
			long takeTime = end - start;
			String message = "[SqlInterceptor] execute [" + name + "] 用时 [" + takeTime + "] ms";
			StringBuilder sb = new StringBuilder();
			sb.append(message);
			sb.append("\n");

			Object parameterObject = args[1];
			BoundSql boundSql = ms.getBoundSql(parameterObject);
			String sql = boundSql.getSql();
			List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
			String parameterObjects = JSON.toJSONString(boundSql.getParameterObject());

			String id = ms.getId();
			sb.append("################id = " + id);
			sb.append("\r\n");

			sb.append("sql = " + sql.replaceAll("\n", " ").replaceAll("\r", " "));
			sb.append("\n");
			
			if(SystemUtils.printAllSqlInfo()) {				
				sb.append("parameterMappings = " + parameterMappings);
				sb.append("\n");
				sb.append("parameterObjects = " + parameterObjects);
				sb.append("\n");
			}else {
				// 打印部分参数信息，防止日志过多
				if(parameterMappings != null && parameterMappings.size() > 100) {
					sb.append("parameterMappings = " + parameterMappings.subList(0, 100));
					sb.append("\n");
				}else {
					sb.append("parameterMappings = " + parameterMappings);
					sb.append("\n");
				}
				
				if(parameterObjects != null && parameterObjects.length() > 1000) {
					sb.append("parameterObjects = " + parameterObjects.substring(0, 1000));
					sb.append("\n");
				}else {
					sb.append("parameterObjects = " + parameterObjects);
					sb.append("\n");
				}
			}

//			sb.append("####");
			if (result != null) {
				if (result instanceof List) {
					sb.append("结果记录数 = " + ((List) result).size());
				} else if (result instanceof Collection) {
					sb.append("结果记录数 = " + ((Collection) result).size());
				} else {
					sb.append("结果记录数 = " + 1);
				}
			} else {
				sb.append("结果记录数 = NULL");
			}
			SysUser sysUser = null;
			try {
				sysUser = LoginAction.getLoginUser();
				sb.append("，操作人：" + (sysUser == null ? "null": sysUser.getLoginId()));
				logger.info(sb.toString());
				
				if(takeTime > 5000) {
					logger.info("!!!!!!!!!!!!!!!!!!!!" + id + " -> 耗时 ["+ takeTime +"] ms" );
				}
			} catch (Exception e) {
			}
			// 数组可能为空
			// ParameterMapping mapping =
			// boundSql.getParameterMappings().get(0);
			// Configuration configuration = ms.getConfiguration();
			// DynamicContext context = new DynamicContext(configuration,
			// parameterObject);
			// String originSql = context.getSql();
			// System.out.println("@@@@originSql:"+originSql);
		}
		return result;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}
}
