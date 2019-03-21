package com.github.framework.evo.activiti.listener;

import com.github.framework.evo.activiti.util.ActivitiUtil;
import com.github.framework.evo.common.uitl.DateUtil;
import com.github.framework.evo.common.uitl.JsonUtil;
import com.github.framework.evo.common.uitl.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.impl.el.JuelExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-05-31 19:27
 */
@Slf4j
@Component
public class UserTaskListener implements TaskListener {
	@Autowired
	private RestTemplate restTemplate;

	private JuelExpression createUrl;
	private JuelExpression completeUrl;

	@Override
	public void notify(DelegateTask delegateTask) {
		String eventName = delegateTask.getEventName();
		String createUrlText = ActivitiUtil.getExpressionText(createUrl);
		String createUrlValue = StringUtil.toEmpty(ActivitiUtil.getExpressionValue(createUrl, delegateTask));
		String completeUrlText = ActivitiUtil.getExpressionText(completeUrl);
		String completeUrlValue = StringUtil.toEmpty(ActivitiUtil.getExpressionValue(completeUrl, delegateTask));

		Map<String, Object> map = new HashMap<>();
		map.put("assignee", delegateTask.getAssignee());
		map.put("category", delegateTask.getCategory());
		map.put("createTime", DateUtil.formatDatetime(delegateTask.getCreateTime()));
		map.put("description", delegateTask.getDescription());
		map.put("dueDate", DateUtil.formatDatetime(delegateTask.getDueDate()));
		map.put("executionId", delegateTask.getExecutionId());
		map.put("eventName", eventName);
		map.put("formKey", delegateTask.getFormKey());
		map.put("id", delegateTask.getId());
		map.put("name", delegateTask.getName());
		map.put("owner", delegateTask.getOwner());
		map.put("priority", String.valueOf(delegateTask.getPriority()));
		map.put("processDefinitionId", delegateTask.getProcessDefinitionId());
		map.put("processInstanceId", delegateTask.getProcessInstanceId());
		map.put("taskDefinitionKey", delegateTask.getTaskDefinitionKey());
		map.put("tenantId", delegateTask.getTenantId());
		map.put("variables", delegateTask.getVariables());
		map.put("variablesLocal", delegateTask.getVariablesLocal());
		map.put("createUrlText", createUrlText);
		map.put("createUrlValue", createUrlValue);
		map.put("completeUrlText", completeUrlText);
		map.put("completeUrlValue", completeUrlValue);

		log.info("任务监听器启动: " + JsonUtil.objectToJson(map));

		String url = null;
		if ("create".equals(eventName)) {
			url = createUrlValue;
		} else if ("complete".equals(eventName)) {
			url = completeUrlValue;
		} else {
			log.warn("忽略事件: " + eventName);
		}

		if (StringUtil.isNotBlank(url)) {
			log.info("任务监听器执行回调URL: " + url);

			HttpHeaders headers =new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			/*try {
				ServiceResponse serviceResponse = restTemplate.postForObject(url, new HttpEntity<>(map, headers), ServiceResponse.class);

				if (serviceResponse == null) {
					throw new TaskListenerCallbackResultException("任务监听器获取回调URL结果为null");
				}

				String code = serviceResponse.getCode();
				log.info("任务监听器获取回调URL结果: " + JsonUtil.objectToJson(serviceResponse));

				if (!"SUCC-0000".equals(code)) {
					throw new TaskListenerCallbackResultException("任务监听器获取回调URL结果非法: " + code);
				}
			} catch (Exception e) {
				log.error("任务监听器执行回调URL失败", e);

				throw new TaskListenerCallbackException(url);
			}*/
		}
	}
}
