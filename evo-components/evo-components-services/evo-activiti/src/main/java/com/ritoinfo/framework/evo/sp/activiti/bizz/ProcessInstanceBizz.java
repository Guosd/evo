package com.ritoinfo.framework.evo.sp.activiti.bizz;

import com.ritoinfo.framework.evo.sp.activiti.ActivitiConst;
import com.ritoinfo.framework.evo.sp.activiti.dto.StartDto;
import com.ritoinfo.framework.evo.sp.activiti.dto.VariableDto;
import com.ritoinfo.framework.evo.sp.activiti.exception.ProcessDefinitionNotFoundException;
import com.ritoinfo.framework.evo.sp.activiti.exception.ProcessInstanceStartException;
import com.ritoinfo.framework.evo.sp.activiti.exception.TaskNotFoundException;
import com.ritoinfo.framework.evo.sp.activiti.proxy.entity.ProcessDefinitionProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.entity.ProcessInstanceProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.entity.TaskProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.entity.VariableProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.service.RepositoryServiceProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.service.RuntimeServiceProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.service.TaskServiceProxy;
import com.ritoinfo.framework.evo.sp.activiti.util.ActivitiUtil;
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
