package com.github.framework.evo.autoconfigure.session;

import com.github.framework.evo.base.session.SessionFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * User: Kyll
 * Date: 2018-03-09 16:02
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(SessionProperties.class)
public class SessionConfiguration {
	private final SessionProperties sessionProperties;

	@Autowired
	public SessionConfiguration(SessionProperties sessionProperties) {
		this.sessionProperties = sessionProperties;
	}

	@PostConstruct
	public void init() {
		log.info("SessionHolder.getUserContext Enabled {}", sessionProperties.getUserContext().isEnabled());
	}

	@Bean
	public FilterRegistrationBean sessionFilterRegistration() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new SessionFilter());
		registration.addUrlPatterns("/*");
		registration.setName("SessionFilter");
		registration.setOrder(1);
		registration.setEnabled(sessionProperties.getUserContext().isEnabled());
		return registration;
	}
}
