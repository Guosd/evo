package com.ritoinfo.framework.evo.sp.activiti.rest;

import com.ritoinfo.framework.evo.sp.activiti.bizz.ProcessDefinitionBizz;
import com.ritoinfo.framework.evo.sp.activiti.condition.ProcessDefinitionCondition;
import com.ritoinfo.framework.evo.sp.activiti.dto.ProcessDefinitionDto;
import com.ritoinfo.framework.evo.sp.activiti.dto.TaskDto;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-02 16:07
 */
@Slf4j
@RequestMapping("process-definition")
@RestController
public class ProcessDefinitionRest {
	@Autowired
	private ProcessDefinitionBizz processDefinitionBizz;

	@GetMapping("/all")
	public ServiceResponse<List<ProcessDefinitionDto>> find(@RequestBody ProcessDefinitionCondition processDefinitionCondition) {
		return ServiceResponse.ok(processDefinitionBizz.getProcessDefinitionList(processDefinitionCondition));
	}

	@GetMapping("/page")
	public ServiceResponse<PageList<ProcessDefinitionDto>> findPage(@RequestBody ProcessDefinitionCondition processDefinitionCondition) {
		return ServiceResponse.ok(processDefinitionBizz.getProcessDefinitionPage(processDefinitionCondition));
	}

	@GetMapping("/{processDefinitionId}/task")
	public ServiceResponse<List<TaskDto>> task(@PathVariable String processDefinitionId) {
		return ServiceResponse.ok(processDefinitionBizz.getTaskList(processDefinitionId));
	}
}
