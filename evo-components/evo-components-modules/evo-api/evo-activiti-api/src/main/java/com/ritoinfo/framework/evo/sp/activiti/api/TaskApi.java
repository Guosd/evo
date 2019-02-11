package com.ritoinfo.framework.evo.sp.activiti.api;

import com.ritoinfo.framework.evo.sp.activiti.condition.HistoricTaskInstanceCondition;
import com.ritoinfo.framework.evo.sp.activiti.condition.TaskCondition;
import com.ritoinfo.framework.evo.sp.activiti.dto.ClaimDto;
import com.ritoinfo.framework.evo.sp.activiti.dto.CompleteDto;
import com.ritoinfo.framework.evo.sp.activiti.dto.TaskDto;
import com.ritoinfo.framework.evo.sp.activiti.dto.WithdrawDto;
import com.ritoinfo.framework.evo.common.model.PageList;
import com.ritoinfo.framework.evo.common.model.ServiceResponse;
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
	ServiceResponse<PageList<TaskDto>> findPage(@RequestBody TaskCondition condition);

	@GetMapping("/historic/page")
	ServiceResponse<PageList<TaskDto>> findPage(@RequestBody HistoricTaskInstanceCondition condition);

	@PutMapping("/claim")
	ServiceResponse claim(@RequestBody ClaimDto... claimDtos);

	@PutMapping("/resolve")
	ServiceResponse resolve(@RequestBody String... taskIds);

	@PostMapping("/complete")
	ServiceResponse complete(@RequestBody CompleteDto... completeDtos);

	@PostMapping("/withdraw")
	ServiceResponse withdraw(@RequestBody WithdrawDto... withdrawDtos);
}
