package com.ritoinfo.framework.evo.sp.base.mybatis.interceptor;

import com.ritoinfo.framework.evo.common.uitl.BeanAssist;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

/**
 * User: Kyll
 * Date: 2018-02-26 23:12
 */
@Component
@Intercepts({
	@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class PageInterceptor implements Interceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();
		if (target instanceof RoutingStatementHandler) {
			RoutingStatementHandler routingStatementHandler = (RoutingStatementHandler) target;
			BoundSql boundSql = routingStatementHandler.getBoundSql();

			String sql = boundSql.getSql();
			if (sql.matches("^\\s*(?!)select\\s+(?!)count\\(.+\\).+(?!)from\\s+")) {

			}

			Object parameterObject = boundSql.getParameterObject();

			Integer pageNo = (Integer) BeanAssist.getFieldValue(parameterObject, "pageNo");
			Integer pageSize = (Integer) BeanAssist.getFieldValue(parameterObject, "pageSize");
			if (pageNo != null && pageSize != null) {

				System.out.println(sql);
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object o) {
		return Plugin.wrap(o, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

	public static void main(String[] args) {
	//	String sql = "select count(1) count from ";
		String sql = " select count(*)  from";
	//	if (sql.matches("\\s*(?!)select\\s+(?!)count\\(.+\\)\\s*.+(?!)from\\s+")) {
		if (sql.matches("\\s*select\\s+count\\(.+\\)\\s+(\\w*|_*)\\s+from")) {
			System.out.println(true);
		} else {
			System.out.println(false);
		}
	}
}
