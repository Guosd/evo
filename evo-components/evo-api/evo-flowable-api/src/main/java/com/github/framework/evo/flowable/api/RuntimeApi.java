package com.github.framework.evo.flowable.api;

import com.github.framework.evo.flowable.model.ProcessInstanceDto;
import com.github.framework.evo.flowable.model.StartDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2019-03-24 12:26
 */
@FeignClient(name = "evo-flowable", path = "/runtime")
public interface RuntimeApi {
	@PostMapping("/process-instance")
	ProcessInstanceDto startProcessInstanceByKey(@RequestBody StartDto startDto);
}
