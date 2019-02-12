package com.ritoinfo.framework.evo.activiti.proxy.service;

import com.ritoinfo.framework.evo.activiti.condition.ProcessDefinitionCondition;
import com.ritoinfo.framework.evo.activiti.proxy.entity.ProcessDefinitionProxy;
import com.ritoinfo.framework.evo.activiti.proxy.entity.TaskProxy;
import com.ritoinfo.framework.evo.activiti.proxy.model.ActivitiPage;
import com.ritoinfo.framework.evo.activiti.util.ActivitiUtil;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.SqlUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class RepositoryServiceProxy {
	@Autowired
	private RepositoryService repositoryService;

	public ProcessDefinitionProxy getProcessDefinitionLatestVersion(String key) {
		log.info("查询流程定义: " + key);

		ProcessDefinitionProxy processDefinitionProxy = null;

		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(key).latestVersion().singleResult();
		if (processDefinition != null) {
			processDefinitionProxy = new ProcessDefinitionProxy();
			BeanUtil.copy(processDefinitionProxy, processDefinition);
		}

		return processDefinitionProxy;
	}

	public int getProcessDefinitionCount(ProcessDefinitionCondition condition) {
		log.info("查询流程定义: " + condition);
		return new Long(createProcessDefinitionQuery(condition, true).count()).intValue();
	}

	public List<ProcessDefinitionProxy> getProcessDefinitionList(ProcessDefinitionCondition condition) {
		log.info("查询流程定义: " + condition);
		return toProcessDefinitionProxyList(createProcessDefinitionQuery(condition, false).list());
	}

	public List<ProcessDefinitionProxy> getProcessDefinitionList(ProcessDefinitionCondition condition, ActivitiPage page) {
		log.info("查询流程定义: " + condition);
		return toProcessDefinitionProxyList(createProcessDefinitionQuery(condition, false).listPage(page.getStart(), page.getSize()));
	}

	public List<TaskProxy> getTaskList(String processDefinitionId) {
		log.info("查询任务节点: " + processDefinitionId);

		List<TaskProxy> list = new ArrayList<>();
		for (FlowElement flowElement : repositoryService.getBpmnModel(processDefinitionId).getMainProcess().getFlowElements()) {
			if (flowElement instanceof UserTask) {
				UserTask userTask = (UserTask) flowElement;
				TaskProxy taskProxy = new TaskProxy();
				taskProxy.setProcessDefinitionId(processDefinitionId);
				taskProxy.setTaskDefinitionKey(userTask.getId());
				taskProxy.setName(userTask.getName());
				taskProxy.setAssignee(userTask.getAssignee());
				list.add(taskProxy);
			}
		}

		list.sort(Comparator.comparingInt(ActivitiUtil::getTaskSort));

		return list;
	}

	private ProcessDefinitionQuery createProcessDefinitionQuery(ProcessDefinitionCondition condition, boolean isCount) {
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

		Integer version = condition.getVersion();
		if (version != null) {
			query.processDefinitionVersion(version);
		}
		String name = condition.getName();
		if (StringUtil.isNotBlank(name)) {
			query.processDefinitionName(name);
		}
		String nameLike = condition.getNameLike();
		if (StringUtil.isNotBlank(nameLike)) {
			query.processDefinitionNameLike(SqlUtil.toLike(nameLike));
		}
		String key = condition.getKey();
		if (StringUtil.isNotBlank(key)) {
			query.processDefinitionKey(key);
		}
		String keyLike = condition.getKeyLike();
		if (StringUtil.isNotBlank(keyLike)) {
			query.processDefinitionKeyLike(SqlUtil.toLike(keyLike));
		}
		String resourceName = condition.getResourceName();
		if (StringUtil.isNotBlank(resourceName)) {
			query.processDefinitionResourceName(resourceName);
		}
		String resourceNameLike = condition.getResourceNameLike();
		if (StringUtil.isNotBlank(resourceNameLike)) {
			query.processDefinitionResourceNameLike(SqlUtil.toLike(resourceNameLike));
		}
		String category = condition.getCategory();
		if (StringUtil.isNotBlank(category)) {
			query.processDefinitionCategory(category);
		}
		String categoryLike = condition.getCategoryLike();
		if (StringUtil.isNotBlank(categoryLike)) {
			query.processDefinitionCategoryLike(SqlUtil.toLike(categoryLike));
		}
		String categoryNotEquals = condition.getCategoryNotEquals();
		if (StringUtil.isNotBlank(categoryNotEquals)) {
			query.processDefinitionCategoryNotEquals(categoryNotEquals);
		}
		String deploymentId = condition.getDeploymentId();
		if (StringUtil.isNotBlank(deploymentId)) {
			query.deploymentId(deploymentId);
		}
		String startableByUser = condition.getStartableByUser();
		if (StringUtil.isNotBlank(startableByUser)) {
			query.startableByUser(startableByUser);
		}
		Boolean latest = condition.getLatest();
		if (latest != null && latest) {
			query.latestVersion();
		}
		Boolean suspended = condition.getSuspended();
		if (suspended != null && suspended) {
			query.suspended();
		}

		if (!isCount) {
			String pageOrder = condition.getPageOrder();
			if (StringUtil.isNotBlank(pageOrder)) {
				if ("deploymentId".equals(pageOrder)) {
					query.orderByDeploymentId();
				} else if ("processDefinitionCategory".equals(pageOrder)) {
					query.orderByProcessDefinitionCategory();
				} else if ("processDefinitionId".equals(pageOrder)) {
					query.orderByProcessDefinitionId();
				} else if ("processDefinitionKey".equals(pageOrder)) {
					query.orderByProcessDefinitionKey();
				} else if ("processDefinitionName".equals(pageOrder)) {
					query.orderByProcessDefinitionName();
				} else if ("processDefinitionVersion".equals(pageOrder)) {
					query.orderByProcessDefinitionVersion();
				} else if ("tenantId".equals(pageOrder)) {
					query.orderByTenantId();
				}

				if ("desc".equals(condition.getPageSort())) {
					query.desc();
				} else {
					query.asc();
				}
			}
		}

		return query;
	}

	private List<ProcessDefinitionProxy> toProcessDefinitionProxyList(List<ProcessDefinition> processDefinitionList) {
		List<ProcessDefinitionProxy> processDefinitionProxyList = new ArrayList<>();

		for (ProcessDefinition processDefinition : processDefinitionList) {
			ProcessDefinitionProxy processDefinitionProxy = new ProcessDefinitionProxy();
			BeanUtil.copy(processDefinitionProxy, processDefinition);

			processDefinitionProxyList.add(processDefinitionProxy);
		}

		return processDefinitionProxyList;
	}
}
