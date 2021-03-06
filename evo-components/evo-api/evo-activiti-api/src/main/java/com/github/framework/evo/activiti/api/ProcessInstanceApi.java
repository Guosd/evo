package com.github.framework.evo.activiti.api;

import com.github.framework.evo.activiti.dto.StartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2018-03-29 11:23
 */
@FeignClient(name = "evo-activiti", path = "/process-instance")
public interface ProcessInstanceApi {
	@PostMapping("/start")
	void start(@RequestBody StartDto... startDtos);
}
