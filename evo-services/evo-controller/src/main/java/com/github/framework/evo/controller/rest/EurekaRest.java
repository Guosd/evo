package com.github.framework.evo.controller.rest;

import com.github.framework.evo.controller.bizz.EurekaBizz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2019-04-15 16:11
 */
@Slf4j
@RequestMapping("/eureka")
@RestController
public class EurekaRest {
	@Autowired
	private EurekaBizz eurekaBizz;

	@PostMapping("/startup/{instanceId}")
	public void startup(@PathVariable String instanceId) {
		eurekaBizz.startup(instanceId);
	}

	@PostMapping("/shutdown/{instanceId}")
	public void shutdown(@PathVariable String instanceId) {
		eurekaBizz.shutdown(instanceId);
	}

	@PostMapping("/online/{instanceId}")
	public void online(@PathVariable String instanceId) {
		eurekaBizz.online(instanceId);
	}

	@PostMapping("/offline/{serviceId}/{instanceId}")
	public void offline(@PathVariable String serviceId, @PathVariable String instanceId) {
		eurekaBizz.offline(serviceId, instanceId);
	}
}
