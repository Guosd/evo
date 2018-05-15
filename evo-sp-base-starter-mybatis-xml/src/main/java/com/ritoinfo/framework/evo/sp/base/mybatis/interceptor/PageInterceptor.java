package com.ritoinfo.framework.evo.sp.base.mybatis.interceptor;

import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.SqlUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.sp.base.model.Page;
import lombok.extern.slf4j.Slf4j;
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

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * User: Kyll
 * Date: 2018-02-26 23:12
 */
@Slf4j
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

			Object parameterObject = boundSql.getParameterObject();
			if (parameterObject != null) {
				Page page = (Page) BeanUtil.getFieldValue(parameterObject, "page");
				if (page != null) {
					BeanUtil.setFieldValue(boundSql, "sql", getPageSql(boundSql.getSql(), getJdbcUrl(target), page));
				}
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
		Configuration configuration = (Configuration) BeanUtil.getFieldValue(BeanUtil.getFieldValue(target, "delegate"), "configuration");
		DataSource dataSource = configuration.getEnvironment().getDataSource();
		String jdbcUrl = (String) BeanUtil.getFieldValue(dataSource, "jdbcUrl");

		if (StringUtil.isBlank(jdbcUrl)) {
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
				jdbcUrl = connection.getMetaData().getURL();
			} catch (SQLException e) {
				log.error("分页查询失败，无法获取数据库连接", e);
			} finally {
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException e) {
						log.error("关闭数据库连接失败", e);
					}
				}
			}
		}

		return jdbcUrl;
	}

	private String getPageSql(String sql, String jdbcUrl, Page page) throws Exception {
		String pageSql;
		if (jdbcUrl.contains(":oracle:")) {
			pageSql = SqlUtil.toPageForOracle(sql, page.getPageNo(), page.getPageSize());
		} else if (jdbcUrl.contains(":mysql:")) {
			pageSql = SqlUtil.toPageForMySql(sql, page.getPageNo(), page.getPageSize());
		} else {
			throw new Exception("不支持的数据库");
		}
		return pageSql;
	}
}
