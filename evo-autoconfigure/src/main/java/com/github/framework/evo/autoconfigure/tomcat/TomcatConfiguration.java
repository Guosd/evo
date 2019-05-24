package com.github.framework.evo.autoconfigure.tomcat;

import com.github.framework.evo.tomcat.GracefulShutdown;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;

import javax.annotation.PostConstruct;

/**
 * User: Kyll
 * Date: 2019-05-24 09:16
 *
 * 注意，此功能未完成
 */
@Slf4j
/*@ConditionalOnClass(TomcatServletWebServerFactory.class)
@Configuration*/
public class TomcatConfiguration {
	private final TomcatServletWebServerFactory factory;

	@Autowired
	public TomcatConfiguration(TomcatServletWebServerFactory factory) {
		this.factory = factory;
	}

	@PostConstruct
	public void init() {
		log.info("Graceful Shutdown Enabled");

		factory.addConnectorCustomizers(new GracefulShutdown());
	}
}
