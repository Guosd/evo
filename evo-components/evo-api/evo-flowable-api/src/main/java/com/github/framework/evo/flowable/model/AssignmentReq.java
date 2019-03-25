package com.github.framework.evo.flowable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2019-03-24 15:08
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AssignmentReq {
	private String processDefinitionKey;
	private String businessKey;
	private String taskDefinitionKey;
	private Map<String, Object> variables;
}
