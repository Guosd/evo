package com.github.framework.evo.controller.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * User: Kyll
 * Date: 2019-06-09 03:36
 */
@FeignClient(value = "evo-config", path = "/actuator")
public interface ConfigApi {
	@PostMapping("/bus-refresh/{destination}")
	void busRefresh(@PathVariable("destination") String destination);
}
