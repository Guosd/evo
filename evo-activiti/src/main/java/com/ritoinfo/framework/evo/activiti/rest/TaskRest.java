package com.ritoinfo.framework.evo.activiti.rest;

import com.ritoinfo.framework.evo.activiti.bizz.TaskBizz;
import com.ritoinfo.framework.evo.activiti.condition.HistoricTaskInstanceCondition;
import com.ritoinfo.framework.evo.activiti.condition.TaskCondition;
import com.ritoinfo.framework.evo.activiti.dto.ClaimDto;
import com.ritoinfo.framework.evo.activiti.dto.CompleteDto;
import com.ritoinfo.framework.evo.activiti.dto.TaskDto;
import com.ritoinfo.framework.evo.activiti.dto.WithdrawDto;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-03-29 11:30
 */
@Slf4j
@RequestMapping("task")
@RestController
public class TaskRest {
	@Autowired
	private TaskBizz taskBizz;

	@GetMapping("/page")
	public ServiceResponse<PageList<TaskDto>> findPage(@RequestBody TaskCondition condition) {
		return ServiceResponse.ok(taskBizz.findPage(condition));
	}

	@GetMapping("/historic/page")
	public ServiceResponse<PageList<TaskDto>> findPage(@RequestBody HistoricTaskInstanceCondition condition) {
		return ServiceResponse.ok(taskBizz.findPage(condition));
	}

	@PutMapping("/claim")
	public ServiceResponse claim(@RequestBody ClaimDto... claimDtos) {
		taskBizz.claim(claimDtos);
		return ServiceResponse.ok();
	}

	@PutMapping("/resolve")
	public ServiceResponse resolve(@RequestBody String... taskIds) {
		taskBizz.resolve(taskIds);
		return ServiceResponse.ok();
	}

	@PostMapping("/complete")
	public ServiceResponse complete(@RequestBody CompleteDto... completeDtos) {
		taskBizz.complete(completeDtos);
		return ServiceResponse.ok();
	}

	@PostMapping("/withdraw")
	public ServiceResponse withdraw(@RequestBody WithdrawDto... withdrawDtos) {
		taskBizz.withdraw(withdrawDtos);
		return ServiceResponse.ok();
	}
}
