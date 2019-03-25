package com.github.framework.evo.flowable.rest;

import com.github.framework.evo.flowable.api.TaskApi;
import com.github.framework.evo.flowable.bizz.TaskBizz;
import com.github.framework.evo.flowable.model.TaskDto;
import com.github.framework.evo.flowable.model.TaskInfoDto;
import com.github.framework.evo.flowable.model.TaskInfoQueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2019-03-25 14:52
 */
@RequestMapping("/task")
@RestController
public class TaskRest implements TaskApi {
	@Autowired
	private TaskBizz taskBizz;

	@Override
	public List<TaskInfoDto> findPage(TaskInfoQueryCondition taskInfoQueryCondition) {
		return taskBizz.findPage(taskInfoQueryCondition);
	}

	@Override
	public void complete(TaskDto taskDto) {
		taskBizz.complete(taskDto);
	}
}
