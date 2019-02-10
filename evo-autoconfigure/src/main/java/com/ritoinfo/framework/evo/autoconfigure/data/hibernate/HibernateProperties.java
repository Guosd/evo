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
	private String dialect;
	private boolean showSql = true;
	private boolean formatSql = false;
	private String packages;
}
