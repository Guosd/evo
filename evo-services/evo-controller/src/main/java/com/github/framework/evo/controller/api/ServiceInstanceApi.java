package com.github.framework.evo.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * User: Kyll
 * Date: 2019-05-23 16:24
 */
@Slf4j
@Component
public class ServiceInstanceApi {
	@Autowired
	private RestTemplate restTemplate;

	public void shutdown(String host, int port) {
		String url = "http://" + host + ":" + port + "/actuator/shutdown";
		log.info("URL: {}", url);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		ResponseEntity<String> response = this.restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<String>(null, headers), String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());
	}
}
