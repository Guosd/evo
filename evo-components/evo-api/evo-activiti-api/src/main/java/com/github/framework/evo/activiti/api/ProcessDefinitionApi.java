package com.github.framework.evo.activiti.api;

import com.github.framework.evo.activiti.condition.ProcessDefinitionCondition;
import com.github.framework.evo.activiti.dto.ProcessDefinitionDto;
import com.github.framework.evo.activiti.dto.TaskDto;
import com.github.framework.evo.common.model.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-11 21:38
 */
@FeignClient(name = "evo-activiti", path = "/process-definition")
public interface ProcessDefinitionApi {
	@GetMapping("/all")
	List<ProcessDefinitionDto> find(@RequestBody ProcessDefinitionCondition processDefinitionCondition);

	@GetMapping("/page")
	PageList<ProcessDefinitionDto> findPage(@RequestBody ProcessDefinitionCondition processDefinitionCondition);

	@GetMapping("/{processDefinitionId}/task")
	List<TaskDto> task(@PathVariable String processDefinitionId);
}
