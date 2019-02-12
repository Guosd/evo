package com.github.framework.evo.activiti.bizz;

import com.github.framework.evo.activiti.ActivitiConst;
import com.github.framework.evo.activiti.dto.StartDto;
import com.github.framework.evo.activiti.dto.VariableDto;
import com.github.framework.evo.activiti.exception.ProcessDefinitionNotFoundException;
import com.github.framework.evo.activiti.exception.ProcessInstanceStartException;
import com.github.framework.evo.activiti.exception.TaskNotFoundException;
import com.github.framework.evo.activiti.proxy.entity.ProcessDefinitionProxy;
import com.github.framework.evo.activiti.proxy.entity.ProcessInstanceProxy;
import com.github.framework.evo.activiti.proxy.entity.TaskProxy;
import com.github.framework.evo.activiti.proxy.entity.VariableProxy;
import com.github.framework.evo.activiti.proxy.service.RepositoryServiceProxy;
import com.github.framework.evo.activiti.proxy.service.RuntimeServiceProxy;
import com.github.framework.evo.activiti.proxy.service.TaskServiceProxy;
import com.github.framework.evo.activiti.util.ActivitiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-29 11:35
 */
@Slf4j
@Service
public class ProcessInstanceBizz {
	@Autowired
	private RepositoryServiceProxy repositoryServiceProxy;
	@Autowired
	private RuntimeServiceProxy runtimeServiceProxy;
	@Autowired
	private TaskServiceProxy taskServiceProxy;
	@Autowired
	private TaskBizz taskBizz;

	public void start(StartDto... startDtos) {
		for (StartDto startDto : startDtos) {
			start(startDto.getInitiator(), startDto.getProcessDefinitionKey(), startDto.getBusinessKey(), startDto.getVariableDtoList());
		}
	}

	public void start(String initiator, String processDefinitionKey, String businessKey, List<VariableDto> variableDtoList) {
		ProcessDefinitionProxy processDefinitionProxy = repositoryServiceProxy.getProcessDefinitionLatestVersion(processDefinitionKey);
		if (processDefinitionProxy == null) {
			throw new ProcessDefinitionNotFoundException(processDefinitionKey);
		}

		List<VariableProxy> variableProxyList = ActivitiUtil.toVariableProxy(variableDtoList);
		variableProxyList.add(new VariableProxy(ActivitiConst.DEFPV_INITIATOR, ActivitiConst.VARIABLE_TYPE_STRING, initiator));
		variableProxyList.add(new VariableProxy(ActivitiConst.DEFPV_PROCESSDEFINITION_NAME, ActivitiConst.VARIABLE_TYPE_STRING, processDefinitionProxy.getName()));
		variableProxyList.add(new VariableProxy(ActivitiConst.DEFPV_PROCESSINSTANCE_BUSINESSKEY, ActivitiConst.VARIABLE_TYPE_STRING, businessKey));
		variableProxyList.add(new VariableProxy(ActivitiConst.DEFPV_PREV_OUTGOING, ActivitiConst.VARIABLE_TYPE_STRING, ActivitiConst.DEFPV_PREV_OUTGOING_PASS));

	//	Authentication.setAuthenticatedUserId(initiator);
		ProcessInstanceProxy processInstanceProxy = runtimeServiceProxy.startByKey(processDefinitionKey, businessKey, variableProxyList);
		if (processInstanceProxy == null) {
			throw new ProcessInstanceStartException(processDefinitionKey);
		} else {
			String processInstanceId = processInstanceProxy.getId();
			TaskProxy startTaskProxy = taskServiceProxy.getStartTask(processInstanceId, initiator);
			if (startTaskProxy == null) {
				throw new TaskNotFoundException("起始任务建立失败");
			} else {
				taskBizz.complete(initiator, processInstanceId, startTaskProxy.getId(), ActivitiConst.START_TASK_COMMENT, variableProxyList, true);
			}
		}
	}
}
