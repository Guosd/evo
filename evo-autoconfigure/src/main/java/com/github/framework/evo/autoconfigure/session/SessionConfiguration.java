package com.github.framework.evo.autoconfigure.session;

import com.github.framework.evo.common.config.UserContextConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * User: Kyll
 * Date: 2018-03-09 16:02
 */
@Slf4j
@EnableConfigurationProperties(SessionProperties.class)
@Configuration
public class SessionConfiguration {
	private final SessionProperties sessionProperties;

	@Autowired
	public SessionConfiguration(SessionProperties sessionProperties) {
		this.sessionProperties = sessionProperties;
	}

	@PostConstruct
	public void init() {
		log.info("SessionHolder.getUserContext {}", sessionProperties.getUserContext().isEnabled());
	}

	@Bean
	public UserContextConfig authorizationConfig() {
		return getUserContextConfig(sessionProperties.getUserContext());
	}

	private UserContextConfig getUserContextConfig(SessionProperties.UserContext userContext) {
		return UserContextConfig.builder().enabled(userContext.isEnabled()).build();
	}
}
