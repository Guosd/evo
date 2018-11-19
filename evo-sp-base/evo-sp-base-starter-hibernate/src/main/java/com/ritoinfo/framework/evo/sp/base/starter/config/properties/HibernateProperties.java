package com.ritoinfo.framework.evo.sp.base.starter.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2018-11-18 10:42
 */
@Data
@Configuration
public class HibernateProperties {
	@Value("${hibernate.dialect}")
	private String dialect;
	@Value("${hibernate.show-sql:true}")
	private String showSql;
	@Value("${hibernate.format-sql:false}")
	private String formatSql;
	@Value("${hibernate.packages}")
	private String packages;
}
