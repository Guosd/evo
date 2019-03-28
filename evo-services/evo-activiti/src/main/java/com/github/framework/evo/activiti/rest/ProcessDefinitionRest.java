package com.github.framework.evo.activiti.rest;

import com.github.framework.evo.activiti.api.ProcessDefinitionApi;
import com.github.framework.evo.activiti.bizz.ProcessDefinitionBizz;
import com.github.framework.evo.activiti.condition.ProcessDefinitionCondition;
import com.github.framework.evo.activiti.dto.ProcessDefinitionDto;
import com.github.framework.evo.activiti.dto.TaskDto;
import com.github.framework.evo.common.model.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/process-definition")
@RestController
public class ProcessDefinitionRest implements ProcessDefinitionApi {
	@Autowired
	private ProcessDefinitionBizz processDefinitionBizz;

	@Override
	public List<ProcessDefinitionDto> find(@RequestBody ProcessDefinitionCondition processDefinitionCondition) {
		return processDefinitionBizz.getProcessDefinitionList(processDefinitionCondition);
	}

	@Override
	public PageList<ProcessDefinitionDto> findPage(@RequestBody ProcessDefinitionCondition processDefinitionCondition) {
		return processDefinitionBizz.getProcessDefinitionPage(processDefinitionCondition);
	}

	@Override
	public List<TaskDto> task(@PathVariable String processDefinitionId) {
		return processDefinitionBizz.getTaskList(processDefinitionId);
	}
}
