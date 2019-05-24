package com.github.framework.evo.tomcat;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: Kyll
 * Date: 2019-05-24 09:19
 */
@Slf4j
public class GracefulShutdown implements TomcatConnectorCustomizer, ApplicationListener<ContextClosedEvent> {
	private volatile Connector connector;
	private final int waitTime = 30;

	@Override
	public void customize(Connector connector) {
		this.connector = connector;
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
		log.warn("this.connector.pause();");
		this.connector.pause();

		Executor executor = this.connector.getProtocolHandler().getExecutor();
		log.warn("0");
		if (executor instanceof ThreadPoolExecutor) {
			log.warn("1");
			try {
				ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executor;
				log.warn("2");
				threadPoolExecutor.shutdown();
				log.warn("3");
				if (!threadPoolExecutor.awaitTermination(waitTime, TimeUnit.SECONDS)) {
					log.warn("Tomcat thread pool did not shut down gracefully within " + waitTime + " seconds. Proceeding with forceful shutdown");
				}
				log.warn("4");
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
		log.warn("5");
	}
}
