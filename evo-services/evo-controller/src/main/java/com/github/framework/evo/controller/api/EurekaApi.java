package com.github.framework.evo.controller.api;

import com.github.framework.evo.autoconfigure.controller.ControllerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * User: Kyll
 * Date: 2019-05-23 10:48
 */
@Slf4j
@Component
public class EurekaApi {
	@Autowired
	private ControllerProperties controllerProperties;
	@Autowired
	private RestTemplate restTemplate;

	public void register(String serverUrl, String serviceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getDeRegister().replace("{serviceId}", serviceId);
		log.info("URL: {}", url);

		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.POST, null, String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());
	}

	public void deRegister(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getDeRegister().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		log.info("URL: {}", url);

		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());
	}

	public void heartbeat(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getHeartbeat().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		log.info("URL: {}", url);

		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());
	}

	public String instances(String serverUrl) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getInstances();
		log.info("URL: {}", url);

		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());

		return response.getBody();
	}

	public String instancesOfService(String serverUrl, String serviceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getInstancesOfService().replace("{serviceId}", serviceId);
		log.info("URL: {}", url);

		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());

		return response.getBody();
	}

	public String serviceInstance(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getServiceInstance().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		log.info("URL: {}", url);

		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());

		return response.getBody();
	}

	public String instance(String serverUrl, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getInstance().replace("{instanceId}", instanceId);
		log.info("URL: {}", url);

		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());

		return response.getBody();
	}

	public void outOfService(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getOutOfService().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		log.info("URL: {}", url);

		ResponseEntity<Void> response = this.restTemplate.exchange(url, HttpMethod.PUT, null, Void.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());
	}

	public void backIntoService(String serverUrl, String serviceId, String instanceId) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getBackIntoService().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId);
		log.info("URL: {}", url);

		ResponseEntity<Void> response = this.restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());
	}

	public void metadata(String serverUrl, String serviceId, String instanceId, String key, String value) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getMetadata().replace("{serviceId}", serviceId).replace("{instanceId}", instanceId).replace("{key}", key).replace("{value}", value);
		log.info("URL: {}", url);

		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.PUT, null, String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());
	}

	public void vips(String serverUrl, String vipAddress) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getVips().replace("{vipAddress}", vipAddress);
		log.info("URL: {}", url);

		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());
	}

	public void svips(String serverUrl, String svipAddress) {
		String url = serverUrl + controllerProperties.getEureka().getRest().getSvips().replace("{svipAddress}", svipAddress);
		log.info("URL: {}", url);

		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());
	}
}
