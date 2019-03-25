package com.github.framework.evo.flowable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2019-03-25 14:46
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderQueryCondition {
	private String property;
	private String direction;
}
