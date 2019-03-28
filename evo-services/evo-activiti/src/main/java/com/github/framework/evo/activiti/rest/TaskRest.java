package com.github.framework.evo.activiti.rest;

import com.github.framework.evo.activiti.api.TaskApi;
import com.github.framework.evo.activiti.bizz.TaskBizz;
import com.github.framework.evo.activiti.condition.HistoricTaskInstanceCondition;
import com.github.framework.evo.activiti.condition.TaskCondition;
import com.github.framework.evo.activiti.dto.ClaimDto;
import com.github.framework.evo.activiti.dto.CompleteDto;
import com.github.framework.evo.activiti.dto.TaskDto;
import com.github.framework.evo.activiti.dto.WithdrawDto;
import com.github.framework.evo.common.model.PageList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-03-29 11:30
 */
@Slf4j
@RequestMapping("/task")
@RestController
public class TaskRest implements TaskApi {
	@Autowired
	private TaskBizz taskBizz;

	@Override
	public PageList<TaskDto> findPage(@RequestBody TaskCondition condition) {
		return taskBizz.findPage(condition);
	}

	@Override
	public PageList<TaskDto> findPage(@RequestBody HistoricTaskInstanceCondition condition) {
		return taskBizz.findPage(condition);
	}

	@Override
	public void claim(@RequestBody ClaimDto... claimDtos) {
		taskBizz.claim(claimDtos);
	}

	@Override
	public void resolve(@RequestBody String... taskIds) {
		taskBizz.resolve(taskIds);
	}

	@Override
	public void complete(@RequestBody CompleteDto... completeDtos) {
		taskBizz.complete(completeDtos);
	}

	@Override
	public void withdraw(@RequestBody WithdrawDto... withdrawDtos) {
		taskBizz.withdraw(withdrawDtos);
	}
}
