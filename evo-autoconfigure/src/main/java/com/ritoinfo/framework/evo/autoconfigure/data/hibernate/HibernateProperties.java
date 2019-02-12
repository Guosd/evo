package com.ritoinfo.framework.evo.autoconfigure.data.hibernate;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * User: Kyll
 * Date: 2018-11-18 10:42
 */
@Data
@ConfigurationProperties(prefix = "evo.hibernate")
public class HibernateProperties {
	/**
	 * 数据库方言。默认org.hibernate.dialect.MySQLDialect
	 */
	private String dialect = "org.hibernate.dialect.MySQLDialect";
	/**
	 * 是否显示SQL。默认true
	 */
	private boolean showSql = true;
	/**
	 * 是否格式化SQL。默认false
	 */
	private boolean formatSql = false;
	/**
	 * 扫描实体包。默认com.ritoinfo.framework.evo.sample
	 */
	private String packages = "com.ritoinfo.framework.evo.sample";
}
