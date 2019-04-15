package com.github.framework.evo.controller.rest;

import com.github.framework.evo.controller.bizz.DiscoveryClientBizz;
import com.github.framework.evo.controller.model.ServiceInstanceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2019-04-15 16:50
 */
@Slf4j
@RequestMapping("/discovery-client")
@RestController
public class DiscoveryClientRest {
	@Autowired
	private DiscoveryClientBizz discoveryClientBizz;

	@GetMapping("/service-instance")
	public List<ServiceInstanceDto> findServiceInstance() {
		return discoveryClientBizz.findServiceInstance();
	}
}
