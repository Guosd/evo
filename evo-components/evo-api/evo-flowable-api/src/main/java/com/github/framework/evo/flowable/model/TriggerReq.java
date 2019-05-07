package com.github.framework.evo.flowable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2019-05-07 08:45
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TriggerReq {
	private String executionId;
	private Map<String, Object> processVariables;
}
