package com.github.framework.evo.flowable.bizz;

import com.github.framework.evo.base.assist.BaseHelper;
import com.github.framework.evo.flowable.model.ProcessInstanceDto;
import com.github.framework.evo.flowable.model.StartDto;
import org.flowable.common.engine.impl.identity.Authentication;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Kyll
 * Date: 2019-03-24 12:19
 */
@Service
public class RuntimeBizz {
	@Autowired
	private RuntimeService runtimeService;

	public ProcessInstanceDto startProcessInstanceByKey(StartDto startDto) {
		Authentication.setAuthenticatedUserId(startDto.getInitiator());
		return BaseHelper.convertObject(
				runtimeService.startProcessInstanceByKey(startDto.getProcessDefinitionKey(), startDto.getBusinessKey(), startDto.getVariables()),
				ProcessInstanceDto.class);
	}
}
