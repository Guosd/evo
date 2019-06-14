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
	private static final String REGISTER_PATH = "/apps/{serviceId}";
	private static final String DE_REGISTER_PATH = "/apps/{serviceId}/{instanceId}";
	private static final String HEARTBEAT_PATH = "/apps/{serviceId}/{instanceId}";
	private static final String INSTANCES_PATH = "/apps";
	private static final String INSTANCES_OF_SERVICE_PATH = "/apps/{serviceId}";
	private static final String SERVICE_INSTANCE_PATH = "/apps/{serviceId}/{instanceId}";
	private static final String INSTANCE_PATH = "/instances/{instanceId}";
	private static final String OUT_OF_SERVICE_PATH = "/apps/{serviceId}/{instanceId}/status?value=OUT_OF_SERVICE";
	private static final String BACK_INTO_SERVICE_PATH = "/apps/{serviceId}/{instanceId}/status?value=UP";
	private static final String METADATA_PATH = "/apps/{serviceId}/{instanceId}/metadata?{key}={value}";
	private static final String VIPS_PATH = "/vips/{vipAddress}";
	private static final String SVIPS_PATH = "/vips/{svipAddress}";

	public void register(String serverUrl, String serviceId) {
		String url = serverUrl + REGISTER_PATH.replace("{serviceId}", serviceId);
		exchange(url, HttpMethod.POST);
	}

	public void deRegister(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + DE_REGISTER_PATH.replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		exchange(url, HttpMethod.DELETE);
	}

	public void heartbeat(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + HEARTBEAT_PATH.replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		exchange(url, HttpMethod.PUT);
	}

	public String instances(String serverUrl) {
		String url = serverUrl + INSTANCES_PATH;
		return exchange(url, HttpMethod.GET);
	}

	public String instancesOfService(String serverUrl, String serviceId) {
		String url = serverUrl + INSTANCES_OF_SERVICE_PATH.replace("{serviceId}", serviceId);
		return exchange(url, HttpMethod.GET);
	}

	public String serviceInstance(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + SERVICE_INSTANCE_PATH.replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		return exchange(url, HttpMethod.GET);
	}

	public String instance(String serverUrl, String instanceId) {
		String url = serverUrl + INSTANCE_PATH.replace("{instanceId}", instanceId);
		return exchange(url, HttpMethod.GET);
	}

	public void outOfService(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + OUT_OF_SERVICE_PATH.replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		exchange(url, HttpMethod.PUT);
	}

	public void backIntoService(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + BACK_INTO_SERVICE_PATH.replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		exchange(url, HttpMethod.DELETE);
	}

	public void metadata(String serverUrl, String serviceId, String instanceId, String key, String value) {
		String url = serverUrl + METADATA_PATH.replace("{serviceId}", serviceId).replace("{instanceId}", instanceId).replace("{key}", key).replace("{value}", value);
		exchange(url, HttpMethod.PUT);
	}

	public String vips(String serverUrl, String vipAddress) {
		String url = serverUrl + VIPS_PATH.replace("{vipAddress}", vipAddress);
		return exchange(url, HttpMethod.GET);
	}

	public String svips(String serverUrl, String svipAddress) {
		String url = serverUrl + SVIPS_PATH.replace("{svipAddress}", svipAddress);
		return exchange(url, HttpMethod.GET);
	}
}
