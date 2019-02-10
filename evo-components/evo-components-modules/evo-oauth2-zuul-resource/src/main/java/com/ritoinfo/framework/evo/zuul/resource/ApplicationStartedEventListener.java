package com.ritoinfo.framework.evo.zuul.resource;

import com.ritoinfo.framework.evo.common.config.ApplicationConfig;
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
	private ApplicationConfig applicationConfig;
	@Autowired
	private PathExcludeAccessDecisionVoter pathExcludeAccessDecisionVoter;

	@Override
	public void onApplicationEvent(RemoteApplicationEvent remoteApplicationEvent) {
		if (applicationConfig.getApplicationName().equalsIgnoreCase(remoteApplicationEvent.getOriginService().split(":")[0])
				&& "**".equals(remoteApplicationEvent.getDestinationService())) {
			pathExcludeAccessDecisionVoter.fetchPathList();
		}
	}
}
