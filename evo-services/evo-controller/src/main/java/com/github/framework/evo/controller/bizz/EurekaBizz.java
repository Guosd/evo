package com.github.framework.evo.controller.bizz;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.resolver.DefaultEndpoint;
import com.netflix.discovery.shared.transport.EurekaHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.http.RestTemplateTransportClientFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2019-04-15 16:08
 */
@Slf4j
@Service
public class EurekaBizz {
	@Autowired
	private EurekaClientConfigBean eurekaClientConfigBean;

	public List<String> findServerUrl() {
		return new ArrayList<>(eurekaClientConfigBean.getServiceUrl().values());
	}

	public void startup(String instanceId) {


	}

	public void shutdown(String instanceId) {

	}

	public void online(String instanceId) {

	}

	public void offline(String serviceId, String instanceId) {
		for (String serverUrl : findServerUrl()) {
			EurekaHttpClient eurekaHttpClient = new RestTemplateTransportClientFactory().newClient(new DefaultEndpoint(serverUrl));
			int statusCode = eurekaHttpClient.statusUpdate(serviceId, instanceId, InstanceInfo.InstanceStatus.OUT_OF_SERVICE, eurekaHttpClient.getInstance(instanceId).getEntity()).getStatusCode();
			log.info("serverUrl:{}, offline({}): {}({}), statusCode: {}", serverUrl, InstanceInfo.InstanceStatus.OUT_OF_SERVICE, serviceId, instanceId, statusCode);
		}
	}
}
