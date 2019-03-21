package com.github.framework.evo.activiti.rest;

import com.github.framework.evo.activiti.bizz.ProcessInstanceBizz;
import com.github.framework.evo.activiti.dto.StartDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-03-29 11:23
 */
@Slf4j
@RequestMapping("process-instance")
@RestController
public class ProcessInstanceRest {
	@Autowired
	private ProcessInstanceBizz processInstanceBizz;

	@PostMapping("/start")
	public void start(@RequestBody StartDto... startDtos) {
		processInstanceBizz.start(startDtos);
	}
}
