package com.ritoinfo.framework.evo.sp.activiti.rest;

import com.ritoinfo.framework.evo.sp.activiti.bizz.ProcessInstanceBizz;
import com.ritoinfo.framework.evo.sp.activiti.dto.StartDto;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
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
	public ServiceResponse start(@RequestBody StartDto... startDtos) {
		processInstanceBizz.start(startDtos);
		return ServiceResponse.ok();
	}
}
