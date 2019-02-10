package com.ritoinfo.framework.evo.sp.activiti.bizz;

import com.ritoinfo.framework.evo.common.model.PageList;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.sp.activiti.ActivitiConst;
import com.ritoinfo.framework.evo.sp.activiti.condition.HistoricTaskInstanceCondition;
import com.ritoinfo.framework.evo.sp.activiti.condition.TaskCondition;
import com.ritoinfo.framework.evo.sp.activiti.dto.ClaimDto;
import com.ritoinfo.framework.evo.sp.activiti.dto.CompleteDto;
import com.ritoinfo.framework.evo.sp.activiti.dto.ProcessDefinitionDto;
import com.ritoinfo.framework.evo.sp.activiti.dto.ProcessInstanceDto;
import com.ritoinfo.framework.evo.sp.activiti.dto.TaskDto;
import com.ritoinfo.framework.evo.sp.activiti.dto.WithdrawDto;
import com.ritoinfo.framework.evo.sp.activiti.exception.HistoricTaskInstanceNotFoundException;
import com.ritoinfo.framework.evo.sp.activiti.exception.TaskNotFoundException;
import com.ritoinfo.framework.evo.sp.activiti.exception.WithdrawException;
import com.ritoinfo.framework.evo.sp.activiti.proxy.entity.HistoricTaskInstanceProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.entity.TaskProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.entity.VariableProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.model.ActivitiVariable;
import com.ritoinfo.framework.evo.sp.activiti.proxy.service.HistoryServiceProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.service.RuntimeServiceProxy;
import com.ritoinfo.framework.evo.sp.activiti.proxy.service.TaskServiceProxy;
import com.ritoinfo.framework.evo.sp.activiti.util.ActivitiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-02 14:43
 */
@Slf4j
@Service
public class TaskBizz {
	@Autowired
	private TaskServiceProxy taskServiceProxy;
	@Autowired
	private HistoryServiceProxy historyServiceProxy;
	@Autowired
	private RuntimeServiceProxy runtimeServiceProxy;

	public PageList<TaskDto> findPage(TaskCondition condition) {
		condition.setIncludeProcessVariables(true);

		PageList<TaskProxy> taskProxyPageList = getTaskProxyPage(condition);

		PageList<TaskDto> taskDtoPageList = new PageList<>();
		taskDtoPageList.copyPage(taskProxyPageList);

		List<TaskDto> taskDtoList = new ArrayList<>();
		taskDtoPageList.setDataList(taskDtoList);

		for (TaskProxy taskProxy : taskProxyPageList.getDataList()) {
			// Common
			TaskDto taskDto = new TaskDto();
			taskDto.setId(taskProxy.getId());
			taskDto.setUrl(taskProxy.getUrl());
			taskDto.setOwner(taskProxy.getOwner());
			taskDto.setAssignee(taskProxy.getAssignee());
			taskDto.setName(taskProxy.getName());
			taskDto.setDescription(taskProxy.getDescription());
			taskDto.setDueDate(taskProxy.getDueDate());
			taskDto.setPriority(taskProxy.getPriority());
			taskDto.setTaskDefinitionKey(taskProxy.getTaskDefinitionKey());
			taskDto.setTenantId(taskProxy.getTenantId());
			taskDto.setCategory(taskProxy.getCategory());
			taskDto.setFormKey(taskProxy.getFormKey());
			taskDto.setParentTaskId(taskProxy.getParentTaskId());
			taskDto.setExecutionId(taskProxy.getExecutionId());
			taskDto.setVariableDtoList(ActivitiUtil.toVariableDto(taskProxy.getVariables()));
			// Task
			taskDto.setDelegationState(taskProxy.getDelegationState());
			taskDto.setCreateTime(taskProxy.getCreateTime());
			taskDto.setSuspended(taskProxy.getSuspended());
			taskDto.setParentTaskUrl(taskProxy.getParentTaskUrl());
			taskDto.setExecutionUrl(taskProxy.getExecutionUrl());

			ActivitiVariable activitiVariable = new ActivitiVariable(taskProxy.getVariables());
			// WfProcessDefinition
			ProcessDefinitionDto wfProcessDefinition = new ProcessDefinitionDto();
			wfProcessDefinition.setId(taskProxy.getProcessDefinitionId());
			wfProcessDefinition.setName(activitiVariable.getProcessDefinitionName());
			taskDto.setProcessDefinitionDto(wfProcessDefinition);
			// WfProcessInstance
			ProcessInstanceDto wfProcessInstance = new ProcessInstanceDto();
			wfProcessInstance.setId(taskProxy.getProcessInstanceId());
			wfProcessInstance.setBusinessKey(activitiVariable.getBusinessKey());
			taskDto.setProcessInstanceDto(wfProcessInstance);
			// Extra Variable
			taskDto.setDefpvPrevOutgoing(activitiVariable.getPrevOutgoing());
			// Is Exception ?
			if (StringUtil.isNotBlank(activitiVariable.getFeedbackException())) {
				taskDto.setDefpvPrevOutgoing(ActivitiConst.DEFPV_PREV_OUTGOING_EXCEPTION);
			}

			taskDtoList.add(taskDto);
		}

		return taskDtoPageList;
	}

	public PageList<TaskDto> findPage(HistoricTaskInstanceCondition condition) {
		condition.setIncludeProcessVariables(true);
		condition.setIncludeTaskLocalVariables(true);

		PageList<HistoricTaskInstanceProxy> historicTaskInstanceProxyPageList = getHistoricTaskInstanceProxyPage(condition);

		PageList<TaskDto> taskDtoPageList = new PageList<>();
		taskDtoPageList.copyPage(historicTaskInstanceProxyPageList);

		List<TaskDto> taskDtoList = new ArrayList<>();
		taskDtoPageList.setDataList(taskDtoList);

		for (HistoricTaskInstanceProxy historicTaskInstanceProxy : historicTaskInstanceProxyPageList.getDataList()) {
			// Common
			TaskDto taskDto = new TaskDto();
			taskDto.setId(historicTaskInstanceProxy.getId());
			taskDto.setUrl(historicTaskInstanceProxy.getUrl());
			taskDto.setOwner(historicTaskInstanceProxy.getOwner());
			taskDto.setAssignee(historicTaskInstanceProxy.getAssignee());
			taskDto.setName(historicTaskInstanceProxy.getName());
			taskDto.setDescription(historicTaskInstanceProxy.getDescription());
			taskDto.setDueDate(historicTaskInstanceProxy.getDueDate());
			taskDto.setPriority(historicTaskInstanceProxy.getPriority());
			taskDto.setTaskDefinitionKey(historicTaskInstanceProxy.getTaskDefinitionKey());
			taskDto.setTenantId(historicTaskInstanceProxy.getTenantId());
			taskDto.setCategory(historicTaskInstanceProxy.getCategory());
			taskDto.setFormKey(historicTaskInstanceProxy.getFormKey());
			taskDto.setParentTaskId(historicTaskInstanceProxy.getParentTaskId());
			taskDto.setExecutionId(historicTaskInstanceProxy.getExecutionId());
			taskDto.setVariableDtoList(ActivitiUtil.toVariableDto(historicTaskInstanceProxy.getVariables()));
			// HistoricTask
			taskDto.setDeleteReason(historicTaskInstanceProxy.getDeleteReason());
			taskDto.setStartTime(historicTaskInstanceProxy.getStartTime());
			taskDto.setEndTime(historicTaskInstanceProxy.getEndTime());
			taskDto.setDurationInMillis(historicTaskInstanceProxy.getDurationInMillis());
			taskDto.setWorkTimeInMillis(historicTaskInstanceProxy.getWorkTimeInMillis());
			taskDto.setClaimTime(historicTaskInstanceProxy.getClaimTime());

			ActivitiVariable activitiVariable = new ActivitiVariable(historicTaskInstanceProxy.getVariables());
			// WfProcessDefinition
			ProcessDefinitionDto wfProcessDefinition = new ProcessDefinitionDto();
			wfProcessDefinition.setId(historicTaskInstanceProxy.getProcessDefinitionId());
			wfProcessDefinition.setName(activitiVariable.getProcessDefinitionName());
			taskDto.setProcessDefinitionDto(wfProcessDefinition);
			// WfProcessInstance
			ProcessInstanceDto wfProcessInstance = new ProcessInstanceDto();
			wfProcessInstance.setId(historicTaskInstanceProxy.getProcessInstanceId());
			wfProcessInstance.setBusinessKey(activitiVariable.getBusinessKey());
			taskDto.setProcessInstanceDto(wfProcessInstance);
			// Extra Variable
			taskDto.setDefpvPrevOutgoing(activitiVariable.getPrevOutgoing());
			// Is Withdraw ?
			if (ActivitiUtil.isWithdraw(taskDto.getId(), activitiVariable.getWithdrawMark())) {
				taskDto.setDefpvPrevOutgoing(ActivitiConst.DEFPV_PREV_OUTGOING_WITHDRAW);
			}
			// Is Exception ?
			if (StringUtil.isNotBlank(activitiVariable.getFeedbackException())) {
				taskDto.setDefpvPrevOutgoing(ActivitiConst.DEFPV_PREV_OUTGOING_EXCEPTION);
			}
			taskDto.setPvActorId(activitiVariable.getVariableStringValue("pv_actor_id"));
			taskDto.setPvActorIdText(activitiVariable.getVariableStringValue("pv_actor_id_text"));
			taskDto.setPvActorRoles(activitiVariable.getVariableStringValue("pv_actor_roles"));
			taskDto.setPvActorRolesText(activitiVariable.getVariableStringValue("pv_actor_roles_text"));

			taskDtoList.add(taskDto);
		}

		return taskDtoPageList;
	}

	public void claim(ClaimDto... claimDtos) {
		for (ClaimDto claimDto : claimDtos) {
			taskServiceProxy.claim(claimDto.getTaskId(), claimDto.getAssignee());
		}
	}

	public void resolve(String... taskIds) {
		for (String taskId : taskIds) {
			taskServiceProxy.resolve(taskId);
		}
	}

	public void complete(CompleteDto... completeDtos) {
		for (CompleteDto completeDto : completeDtos) {
			complete(completeDto.getUsername(), completeDto.getProcessInstanceId(), completeDto.getTaskId(), completeDto.getMessage(), ActivitiUtil.toVariableProxy(completeDto.getVariableDtoList()), true);
		}
	}

	public void complete(String username, String processInstanceId, String taskId, String message, List<VariableProxy> variableProxyList, boolean needClaimTask) {
		TaskProxy task = taskServiceProxy.getById(taskId);
		if (task == null) {
			throw new TaskNotFoundException(taskId);
		}

		if (needClaimTask && StringUtil.isBlank(task.getAssignee())) {
			taskServiceProxy.claim(taskId, username);
		}

		taskServiceProxy.saveTaskVariable(taskId, variableProxyList);
		taskServiceProxy.saveComment(processInstanceId, taskId, message);
		taskServiceProxy.complete(taskId, variableProxyList);
	}

	public void withdraw(WithdrawDto... withdrawDtos) {
		for (WithdrawDto withdrawDto : withdrawDtos) {
			withdraw(withdrawDto.getUsername(), withdrawDto.getProcessInstanceId(), withdrawDto.getHistoricTaskInstanceId(), withdrawDto.getComment());
		}
	}

	public void withdraw(String username, String processInstanceId, String historicTaskInstanceId, String comment) {
		HistoricTaskInstanceCondition condition = new HistoricTaskInstanceCondition();
		condition.setProcessInstanceId(processInstanceId);
		condition.setPageSort("startTime");
		condition.setPageOrder("asc");
		List<HistoricTaskInstanceProxy> historicTaskInstanceProxyList = historyServiceProxy.getHistoricTaskInstanceList(condition);

		int historicTaskInstanceIndex = -1;
		for (int i = 0, size = historicTaskInstanceProxyList.size(); i < size; i++) {
			HistoricTaskInstanceProxy historicTask = historicTaskInstanceProxyList.get(i);
			if (historicTaskInstanceId.equals(historicTask.getId())) {
				historicTaskInstanceIndex = i + 1;
				break;
			}
		}

		if (historicTaskInstanceIndex > -1) {
			List<HistoricTaskInstanceProxy> unfinishedHistoricTaskInstanceProxyList = historicTaskInstanceProxyList.subList(historicTaskInstanceIndex, historicTaskInstanceProxyList.size());
			boolean isValid = true;
			for (HistoricTaskInstanceProxy historicTaskInstanceProxy : unfinishedHistoricTaskInstanceProxyList) {
				if (historicTaskInstanceProxy.getEndTime() != null) {
					isValid = false;
					break;
				}
			}

			if (isValid) {
				for (HistoricTaskInstanceProxy historicTaskInstanceProxy : unfinishedHistoricTaskInstanceProxyList) {
					String tempHistoricTaskId = historicTaskInstanceProxy.getId();

					if (StringUtil.isNotBlank(historicTaskInstanceProxy.getAssignee())) {
						resolve(tempHistoricTaskId);
					}

					List<VariableProxy> variableProxyList = new ArrayList<>();
					variableProxyList.add(new VariableProxy(ActivitiConst.DEFPV_PREV_OUTGOING, ActivitiConst.DEFPV_PREV_OUTGOING_WITHDRAW));
					complete(username, processInstanceId, tempHistoricTaskId, comment, variableProxyList, false);
				}

				VariableProxy variableProxy = runtimeServiceProxy.getProcessInstancesVariable(processInstanceId, ActivitiConst.DEFPV_WITHDRAW_MARK);
				runtimeServiceProxy.saveProcessInstancesVariable(processInstanceId, new VariableProxy(ActivitiConst.DEFPV_WITHDRAW_MARK, variableProxy == null ? historicTaskInstanceId : (variableProxy.getValue() + "," + historicTaskInstanceId)));
			} else {
				throw new WithdrawException("要撤销的任务实例已经完成");
			}
		} else {
			throw new HistoricTaskInstanceNotFoundException(historicTaskInstanceId);
		}
	}

	private PageList<TaskProxy> getTaskProxyPage(TaskCondition condition) {
		PageList<TaskProxy> pageList = new PageList<>();

		int count = taskServiceProxy.getCount(condition);
		pageList.setTotalRecord(count);
		pageList.setPageNo(condition.getPageNo());
		pageList.setPageSize(condition.getPageSize());
		pageList.setPageSort(condition.getPageSort());
		pageList.setPageOrder(condition.getPageOrder());

		if (count > 0) {
			pageList.setDataList(taskServiceProxy.getList(condition, ActivitiUtil.toActivitiPage(condition)));
		} else {
			pageList.setDataList(new ArrayList<>());
		}

		return pageList;
	}

	private PageList<HistoricTaskInstanceProxy> getHistoricTaskInstanceProxyPage(HistoricTaskInstanceCondition condition) {
		PageList<HistoricTaskInstanceProxy> pageList = new PageList<>();

		int count = historyServiceProxy.getHistoricTaskInstanceCount(condition);
		pageList.setTotalRecord(count);
		pageList.setPageNo(condition.getPageNo());
		pageList.setPageSize(condition.getPageSize());
		pageList.setPageSort(condition.getPageSort());
		pageList.setPageOrder(condition.getPageOrder());

		if (count > 0) {
			pageList.setDataList(historyServiceProxy.getHistoricTaskInstanceList(condition, ActivitiUtil.toActivitiPage(condition)));
		}

		return pageList;
	}
}
