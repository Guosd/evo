package com.github.framework.evo.flowable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2019-03-25 13:18
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VariableQueryCondition {
	private String name;
	private Object value;
	private String operator;
}
