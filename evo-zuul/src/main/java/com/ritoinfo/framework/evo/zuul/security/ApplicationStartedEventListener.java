package com.ritoinfo.framework.evo.zuul.security;

import com.ritoinfo.framework.evo.common.config.properties.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2018-12-28 15:37
 */
@Component
public class ApplicationStartedEventListener implements ApplicationListener<RemoteApplicationEvent> {
	@Autowired
	private ApplicationProperties applicationProperties;
	@Autowired
	private PathExcludeAccessDecisionVoter pathExcludeAccessDecisionVoter;

	@Override
	public void onApplicationEvent(RemoteApplicationEvent remoteApplicationEvent) {
		if (applicationProperties.getApplicationName().equalsIgnoreCase(remoteApplicationEvent.getOriginService().split(":")[0])
				&& "**".equals(remoteApplicationEvent.getDestinationService())) {
			pathExcludeAccessDecisionVoter.fetchPathList();
		}
	}
}
