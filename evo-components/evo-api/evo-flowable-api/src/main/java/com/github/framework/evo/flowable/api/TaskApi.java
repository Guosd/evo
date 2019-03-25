package com.github.framework.evo.flowable.api;

import com.github.framework.evo.flowable.model.TaskDto;
import com.github.framework.evo.flowable.model.TaskInfoDto;
import com.github.framework.evo.flowable.model.TaskInfoQueryCondition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * User: Kyll
 * Date: 2019-03-25 14:53
 */
@FeignClient(name = "evo-flowable", path = "/task")
public interface TaskApi {
	@PostMapping("/page")
	List<TaskInfoDto> findPage(@RequestBody TaskInfoQueryCondition taskInfoQueryCondition);

	@PostMapping("/complete")
	void complete(@RequestBody TaskDto taskDto);
}
