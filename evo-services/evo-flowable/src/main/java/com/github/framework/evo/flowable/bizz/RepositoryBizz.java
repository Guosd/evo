package com.github.framework.evo.flowable.bizz;

import com.github.framework.evo.base.assist.BaseHelper;
import com.github.framework.evo.flowable.model.ProcessDefinitionDto;
import org.flowable.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Kyll
 * Date: 2019-03-24 11:05
 */
@Service
public class RepositoryBizz {
	@Autowired
	private RepositoryService repositoryService;

	public List<ProcessDefinitionDto> findAllProcessDefinition() {
		return BaseHelper.convertObject(
				repositoryService.createProcessDefinitionQuery().latestVersion().orderByProcessDefinitionCategory().asc().orderByProcessDefinitionKey().asc().list(),
				ProcessDefinitionDto.class);
	}
}
