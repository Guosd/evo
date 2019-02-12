package com.ritoinfo.framework.evo.activiti.api;

import com.ritoinfo.framework.evo.activiti.dto.StartDto;
import com.ritoinfo.framework.evo.common.model.ServiceResponse;
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
	ServiceResponse start(@RequestBody StartDto... startDtos);
}
