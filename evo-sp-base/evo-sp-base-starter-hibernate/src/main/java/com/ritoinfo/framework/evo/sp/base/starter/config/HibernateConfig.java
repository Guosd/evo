package com.ritoinfo.framework.evo.sp.base.starter.config;

import com.ritoinfo.framework.evo.sp.base.starter.config.properties.HibernateProperties;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * User: Kyll
 * Date: 2018-11-16 09:05
 */
@Configuration
public class HibernateConfig {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private HibernateProperties hibernateProperties;

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource(dataSource);

		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", hibernateProperties.getDialect());
		properties.setProperty("hibernate.show_sql", hibernateProperties.getShowSql());
		properties.setProperty("hibernate.format_sql", hibernateProperties.getFormatSql());
		localSessionFactoryBean.setHibernateProperties(properties);

		localSessionFactoryBean.setImplicitNamingStrategy(new SpringImplicitNamingStrategy());
		localSessionFactoryBean.setPhysicalNamingStrategy(new SpringPhysicalNamingStrategy());
		localSessionFactoryBean.setPackagesToScan(hibernateProperties.getPackages());
		return localSessionFactoryBean;
	}

	@Bean
	public HibernateTemplate hibernateTemplate(LocalSessionFactoryBean localSessionFactoryBean) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate();
		hibernateTemplate.setSessionFactory(localSessionFactoryBean.getObject());
		return hibernateTemplate;
	}

	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(sessionFactory);
		return hibernateTransactionManager;
	}
}
