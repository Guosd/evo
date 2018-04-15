package com.ritoinfo.framework.evo.activiti.dto;

import lombok.Data;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-10 11:43
 */
@Data
public class CompleteDto {
	private String username;
	private String processInstanceId;
	private String taskId;
	private String message;
	private List<VariableDto> variableDtoList;
}
