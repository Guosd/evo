package com.github.framework.evo.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2019-05-23 10:48
 */
@Slf4j
@Component
public class EurekaApi extends RestTemplateApi {
	public void register(String serverUrl, String serviceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getDeRegister().replace("{serviceId}", serviceId);
		exchange(url, HttpMethod.POST);
	}

	public void deRegister(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getDeRegister().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		exchange(url, HttpMethod.DELETE);
	}

	public void heartbeat(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getHeartbeat().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		exchange(url, HttpMethod.PUT);
	}

	public String instances(String serverUrl) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getInstances();
		return exchange(url, HttpMethod.GET);
	}

	public String instancesOfService(String serverUrl, String serviceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getInstancesOfService().replace("{serviceId}", serviceId);
		return exchange(url, HttpMethod.GET);
	}

	public String serviceInstance(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getServiceInstance().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		return exchange(url, HttpMethod.GET);
	}

	public String instance(String serverUrl, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getInstance().replace("{instanceId}", instanceId);
		return exchange(url, HttpMethod.GET);
	}

	public void outOfService(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getOutOfService().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		exchange(url, HttpMethod.PUT);
	}

	public void backIntoService(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getBackIntoService().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		exchange(url, HttpMethod.DELETE);
	}

	public void metadata(String serverUrl, String serviceId, String instanceId, String key, String value) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getMetadata().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId).replace("{key}", key).replace("{value}", value);
		exchange(url, HttpMethod.PUT);
	}

	public String vips(String serverUrl, String vipAddress) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getVips().replace("{vipAddress}", vipAddress);
		return exchange(url, HttpMethod.GET);
	}

	public String svips(String serverUrl, String svipAddress) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getSvips().replace("{svipAddress}", svipAddress);
		return exchange(url, HttpMethod.GET);
	}
}
