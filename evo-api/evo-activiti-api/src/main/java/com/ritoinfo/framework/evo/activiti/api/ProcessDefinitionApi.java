package com.ritoinfo.framework.evo.activiti.api;

import com.ritoinfo.framework.evo.activiti.condition.ProcessDefinitionCondition;
import com.ritoinfo.framework.evo.activiti.dto.ProcessDefinitionDto;
import com.ritoinfo.framework.evo.activiti.dto.TaskDto;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-11 21:38
 */
@FeignClient(value = "evo-activiti", path = "/process-definition")
public interface ProcessDefinitionApi {
	@GetMapping("/all")
	ServiceResponse<List<ProcessDefinitionDto>> find(@RequestBody ProcessDefinitionCondition processDefinitionCondition);

	@GetMapping("/page")
	ServiceResponse<PageList<ProcessDefinitionDto>> findPage(@RequestBody ProcessDefinitionCondition processDefinitionCondition);

	@GetMapping("/{processDefinitionId}/task")
	ServiceResponse<List<TaskDto>> task(@PathVariable String processDefinitionId);
}
