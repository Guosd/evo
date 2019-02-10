package com.ritoinfo.framework.evo.autoconfigure.session;

import lombok.extern.slf4j.Slf4j;
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
	@PostConstruct
	public void init(SessionProperties sessionProperties) {
		log.info("SessionHolder.getUserContext {}", sessionProperties.getUserContext().isEnabled());
	}
}
