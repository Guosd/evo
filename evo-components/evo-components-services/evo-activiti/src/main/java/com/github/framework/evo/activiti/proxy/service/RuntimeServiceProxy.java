package com.github.framework.evo.activiti.proxy.service;

import com.github.framework.evo.activiti.proxy.entity.ProcessInstanceProxy;
import com.github.framework.evo.activiti.proxy.entity.VariableProxy;
import com.github.framework.evo.activiti.util.ActivitiUtil;
import com.github.framework.evo.common.uitl.BeanUtil;
import com.github.framework.evo.common.uitl.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class RuntimeServiceProxy {
	@Autowired
	private RuntimeService runtimeService;

	public ProcessInstanceProxy startByKey(String processDefinitionKey, String businessKey, List<VariableProxy> variableProxyList) {
		log.info("启动流程实例: " + processDefinitionKey + ", " + businessKey + ", " + JsonUtil.objectToJson(variableProxyList));

		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, ActivitiUtil.listToMap(variableProxyList));

		ProcessInstanceProxy processInstanceProxy = new ProcessInstanceProxy();
		BeanUtil.copy(processInstanceProxy, processInstance);

		return processInstanceProxy;
	}

	public VariableProxy getProcessInstancesVariable(String processInstanceId, String variableName) {
		log.info("获取流程实例本地变量: " + processInstanceId + ", " + variableName);

		VariableInstance variableInstance = runtimeService.getVariableInstanceLocal(processInstanceId, variableName);

		VariableProxy variableProxy = null;
		if (variableInstance != null) {
			variableProxy = new VariableProxy();
			BeanUtil.copy(variableProxy, variableInstance);
		}

		return variableProxy;
	}

	public void saveProcessInstancesVariable(String processInstanceId, VariableProxy... variableProxies) {
		saveProcessInstancesVariable(processInstanceId, Arrays.asList(variableProxies));
	}

	public void saveProcessInstancesVariable(String processInstanceId, List<VariableProxy> variableProxyList) {
		log.info("设置流程实例本地变量: " + processInstanceId + ", " + JsonUtil.objectToJson(variableProxyList));

		runtimeService.setVariablesLocal(processInstanceId, ActivitiUtil.listToMap(variableProxyList));
	}
}
