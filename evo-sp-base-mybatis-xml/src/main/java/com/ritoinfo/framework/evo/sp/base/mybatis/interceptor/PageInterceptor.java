package com.ritoinfo.framework.evo.sp.base.mybatis.interceptor;

import com.ritoinfo.framework.evo.common.uitl.BeanAssist;
import com.ritoinfo.framework.evo.common.uitl.SqlAssist;
import com.ritoinfo.framework.evo.sp.base.model.Page;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
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
			BoundSql boundSql = ((RoutingStatementHandler) target).getBoundSql();

			Page page = (Page) BeanAssist.getFieldValue(boundSql.getParameterObject(), "page");
			if (page != null) {
				BeanAssist.setFieldValue(boundSql, "sql", getPageSql(boundSql.getSql(), getJdbcUrl(target), page));
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

	private String getJdbcUrl(Object target) {
		Configuration configuration = (Configuration) BeanAssist.getFieldValue(BeanAssist.getFieldValue(target, "delegate"), "configuration");
		return (String) BeanAssist.getFieldValue(configuration.getEnvironment().getDataSource(), "jdbcUrl");
	}

	private String getPageSql(String sql, String jdbcUrl, Page page) throws Exception {
		String pageSql;
		if (jdbcUrl.contains(":oracle:")) {
			pageSql = SqlAssist.toPageForOracle(sql, page.getPageNo(), page.getPageSize());
		} else if (jdbcUrl.contains(":mysql:")) {
			pageSql = SqlAssist.toPageForMySql(sql, page.getPageNo(), page.getPageSize());
		} else {
			throw new Exception("不支持的数据库");
		}
		return pageSql;
	}
}
