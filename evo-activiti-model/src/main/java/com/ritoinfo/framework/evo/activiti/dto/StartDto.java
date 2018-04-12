package com.ritoinfo.framework.evo.activiti.dto;

import lombok.Data;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-10 13:22
 */
@Data
public class StartDto {
	private String initiator;
	private String processDefinitionKey;
	private String businessKey;
	private List<VariableDto> variableDtoList;
}
