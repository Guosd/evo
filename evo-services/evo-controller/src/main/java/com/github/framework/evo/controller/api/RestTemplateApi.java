package com.github.framework.evo.controller.api;

import com.github.framework.evo.autoconfigure.controller.ControllerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * User: Kyll
 * Date: 2019-06-13 17:43
 */
@Slf4j
@Component
public abstract class RestTemplateApi {
	@Autowired
	protected ControllerProperties controllerProperties;
	@Autowired
	protected RestTemplate restTemplate;

	protected String exchange(String url, HttpMethod method) {
		return exchange(url, method, null);
	}

	protected String exchange(String url, HttpMethod method, String requestEntity) {
		log.info("Url: {}", url);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<String> response = this.restTemplate.exchange(url, method, requestEntity == null ? null : new HttpEntity<>(requestEntity, httpHeaders), String.class);
		log.info("StatusCode: {}, ReasonPhrase: {}", response.getStatusCodeValue(), response.getStatusCode().getReasonPhrase());

		return response.getBody();
	}
}
