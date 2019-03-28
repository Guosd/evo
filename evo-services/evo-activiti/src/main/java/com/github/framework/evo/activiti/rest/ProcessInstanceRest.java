package com.github.framework.evo.activiti.rest;

import com.github.framework.evo.activiti.api.ProcessInstanceApi;
import com.github.framework.evo.activiti.bizz.ProcessInstanceBizz;
import com.github.framework.evo.activiti.dto.StartDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-03-29 11:23
 */
@Slf4j
@RequestMapping("/process-instance")
@RestController
public class ProcessInstanceRest implements ProcessInstanceApi {
	@Autowired
	private ProcessInstanceBizz processInstanceBizz;

	@Override
	public void start(@RequestBody StartDto... startDtos) {
		processInstanceBizz.start(startDtos);
	}
}
