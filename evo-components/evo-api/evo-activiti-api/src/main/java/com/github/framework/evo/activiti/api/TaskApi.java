package com.github.framework.evo.activiti.api;

import com.github.framework.evo.activiti.condition.HistoricTaskInstanceCondition;
import com.github.framework.evo.activiti.condition.TaskCondition;
import com.github.framework.evo.activiti.dto.ClaimDto;
import com.github.framework.evo.activiti.dto.CompleteDto;
import com.github.framework.evo.activiti.dto.TaskDto;
import com.github.framework.evo.activiti.dto.WithdrawDto;
import com.github.framework.evo.common.model.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2018-03-29 11:30
 */
@FeignClient(name = "evo-activiti", path = "/task")
public interface TaskApi {
	@GetMapping("/page")
	PageList<TaskDto> findPage(@RequestBody TaskCondition condition);

	@GetMapping("/historic/page")
	PageList<TaskDto> findPage(@RequestBody HistoricTaskInstanceCondition condition);

	@PutMapping("/claim")
	void claim(@RequestBody ClaimDto... claimDtos);

	@PutMapping("/resolve")
	void resolve(@RequestBody String... taskIds);

	@PostMapping("/complete")
	void complete(@RequestBody CompleteDto... completeDtos);

	@PostMapping("/withdraw")
	void withdraw(@RequestBody WithdrawDto... withdrawDtos);
}
