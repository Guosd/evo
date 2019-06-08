package com.github.framework.evo.autoconfigure.data.jpa;

import com.github.framework.evo.base.entity.BaseJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * User: Kyll
 * Date: 2019-05-24 16:45
 */
@ConditionalOnClass(BaseJpaEntity.class)
@EnableConfigurationProperties(JpaProperties.class)
@Configuration
public class JpaConfiguration {
	private final ApplicationContext applicationContext;
	private final DataSource dataSource;
	private final JpaProperties jpaProperties;


	@Autowired
	public JpaConfiguration(ApplicationContext applicationContext, DataSource dataSource, JpaProperties jpaProperties){
		this.applicationContext = applicationContext;
		this.dataSource = dataSource;
		this.jpaProperties = jpaProperties;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabasePlatform(jpaProperties.getDatabasePlatform());
		vendorAdapter.setGenerateDdl(jpaProperties.isGenerateDdl());
		vendorAdapter.setShowSql(jpaProperties.isShowSql());

		Properties properties = new Properties();
		properties.setProperty("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(applicationContext.getBeansWithAnnotation(SpringBootApplication.class).values().stream().map(o -> o.getClass().getPackage().getName()).collect(Collectors.toList()).toArray(new String[]{}));
		factory.setDataSource(dataSource);
		factory.setJpaProperties(properties);
		return factory;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory);
		return txManager;
	}
}
