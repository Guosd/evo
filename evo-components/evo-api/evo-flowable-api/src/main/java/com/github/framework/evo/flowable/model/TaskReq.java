package com.github.framework.evo.flowable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2019-03-25 16:03
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TaskReq {
	private String initiator;
	private String taskId;
	private String processInstanceId;
	private String message;
	private Map<String, Object> variables;
}
