package com.ritoinfo.framework.evo.sp.base.mybatis.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;
import java.util.Properties;

/**
 * User: Kyll
 * Date: 2018-02-26 23:12
 */
@Intercepts({
	@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})
})
public class PageInterceptor implements Interceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		// http://blog.csdn.net/moshenglv/article/details/52699976
		return null;
	}

	@Override
	public Object plugin(Object o) {
		return Plugin.wrap(o, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}
}
