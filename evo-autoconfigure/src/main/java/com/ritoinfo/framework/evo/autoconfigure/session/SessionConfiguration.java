package com.ritoinfo.framework.evo.autoconfigure.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
}
