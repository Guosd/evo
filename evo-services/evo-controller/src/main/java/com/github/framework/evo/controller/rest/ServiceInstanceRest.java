package com.github.framework.evo.controller.rest;

import com.github.framework.evo.controller.bizz.ServiceInstanceBizz;
import com.github.framework.evo.controller.model.ServiceInstanceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2019-04-15 16:11
 */
@Slf4j
@RequestMapping("/service-instance")
@RestController
public class ServiceInstanceRest {
	@Autowired
	private ServiceInstanceBizz serviceInstanceBizz;

	@GetMapping
	public List<ServiceInstanceDto> list() {
		return serviceInstanceBizz.find();
	}

	@PostMapping("/startup/{instanceId}")
	public void startup(@PathVariable String instanceId) {
		serviceInstanceBizz.startup(instanceId);
	}

	@PostMapping("/shutdown/{host}/{port}")
	public void shutdown(@PathVariable String host, @PathVariable int port) {
		serviceInstanceBizz.shutdown(host, port);
	}

	@PostMapping("/online/{serviceId}/{instanceId}")
	public void online(@PathVariable String serviceId, @PathVariable String instanceId) {
		serviceInstanceBizz.online(serviceId, instanceId);
	}

	@PostMapping("/offline/{serviceId}/{instanceId}")
	public void offline(@PathVariable String serviceId, @PathVariable String instanceId) {
		serviceInstanceBizz.offline(serviceId, instanceId);
	}
}
