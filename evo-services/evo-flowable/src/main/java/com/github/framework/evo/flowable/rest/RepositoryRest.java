package com.github.framework.evo.flowable.rest;

import com.github.framework.evo.flowable.api.RepositoryApi;
import com.github.framework.evo.flowable.bizz.RepositoryBizz;
import com.github.framework.evo.flowable.model.ProcessDefinitionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2019-03-24 11:23
 */
@Slf4j
@RequestMapping("/repository")
@RestController
public class RepositoryRest implements RepositoryApi {
	@Autowired
	private RepositoryBizz repositoryBizz;

	@Override
	public List<ProcessDefinitionDto> findAllProcessDefinition() {
		for (int i = 0; i < 60; i++) {
			log.warn("{}", i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return repositoryBizz.findAllProcessDefinition();
	}
}
