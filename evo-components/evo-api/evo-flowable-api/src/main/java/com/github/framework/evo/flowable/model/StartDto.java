package com.github.framework.evo.flowable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2019-03-24 13:06
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StartDto {
	private String processDefinitionKey;
	private String businessKey;
	private Map<String, Object> variableMap;
}
