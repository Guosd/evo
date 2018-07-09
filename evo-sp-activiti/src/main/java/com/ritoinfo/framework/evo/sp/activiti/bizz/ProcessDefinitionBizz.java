package com.ritoinfo.framework.evo.sp.activiti.bizz;

import com.ritoinfo.framework.evo.sp.activiti.condition.ProcessDefinitionCondition;
import com.ritoinfo.framework.evo.sp.activiti.dto.ProcessDefinitionDto;
import com.ritoinfo.framework.evo.sp.activiti.dto.TaskDto;
import com.ritoinfo.framework.evo.sp.activiti.proxy.service.RepositoryServiceProxy;
import com.ritoinfo.framework.evo.sp.activiti.util.ActivitiUtil;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-02 09:58
 */
@Slf4j
@Service
public class ProcessDefinitionBizz {
	@Autowired
	private RepositoryServiceProxy repositoryServiceProxy;

	public List<ProcessDefinitionDto> getProcessDefinitionList(ProcessDefinitionCondition processDefinitionCondition) {
		return BaseHelper.toAnyDto(repositoryServiceProxy.getProcessDefinitionList(processDefinitionCondition), ProcessDefinitionDto.class);
	}

	public PageList<ProcessDefinitionDto> getProcessDefinitionPage(ProcessDefinitionCondition condition) {
		PageList<ProcessDefinitionDto> pageList = new PageList<>();

		int count = repositoryServiceProxy.getProcessDefinitionCount(condition);
		pageList.setTotalRecord(count);
		pageList.setPageNo(condition.getPageNo());
		pageList.setPageSize(condition.getPageSize());
		pageList.setPageSort(condition.getPageSort());
		pageList.setPageOrder(condition.getPageOrder());

		if (count > 0) {
			pageList.setDataList(BaseHelper.toAnyDto(repositoryServiceProxy.getProcessDefinitionList(condition, ActivitiUtil.toActivitiPage(condition)), ProcessDefinitionDto.class));
		}

		return pageList;
	}

	public List<TaskDto> getTaskList(String processDefinitionId) {
		return BaseHelper.toAnyDto(repositoryServiceProxy.getTaskList(processDefinitionId), TaskDto.class);
	}
}
