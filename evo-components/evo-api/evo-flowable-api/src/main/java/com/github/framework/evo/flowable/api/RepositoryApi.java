package com.github.framework.evo.flowable.api;

import com.github.framework.evo.flowable.model.ProcessDefinitionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * User: Kyll
 * Date: 2019-03-24 11:23
 */
@FeignClient(name = "evo-flowable", path = "/repository")
public interface RepositoryApi {
	@GetMapping("/process-definition")
	List<ProcessDefinitionDto> findAllProcessDefinition();
}
