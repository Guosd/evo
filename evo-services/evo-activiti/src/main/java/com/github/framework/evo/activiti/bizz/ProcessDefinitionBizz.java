package com.github.framework.evo.activiti.bizz;

import com.github.framework.evo.base.assist.BaseHelper;
import com.github.framework.evo.common.model.PageList;
import com.github.framework.evo.activiti.condition.ProcessDefinitionCondition;
import com.github.framework.evo.activiti.dto.ProcessDefinitionDto;
import com.github.framework.evo.activiti.dto.TaskDto;
import com.github.framework.evo.activiti.proxy.service.RepositoryServiceProxy;
import com.github.framework.evo.activiti.util.ActivitiUtil;
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
		return BaseHelper.convertObject(repositoryServiceProxy.getProcessDefinitionList(processDefinitionCondition), ProcessDefinitionDto.class);
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
			pageList.setDataList(BaseHelper.convertObject(repositoryServiceProxy.getProcessDefinitionList(condition, ActivitiUtil.toActivitiPage(condition)), ProcessDefinitionDto.class));
		}

		return pageList;
	}

	public List<TaskDto> getTaskList(String processDefinitionId) {
		return BaseHelper.convertObject(repositoryServiceProxy.getTaskList(processDefinitionId), TaskDto.class);
	}
}
