package com.github.framework.evo.flowable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * User: Kyll
 * Date: 2019-03-24 15:08
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AssignmentDto {
	private Collection<String> actors;
	private String condition;
	/*
	{
		type: passCount
		weight: 正数:通过数, 负数:拒绝数
	}
	{
		type: passPercent
		weight: 正数:通过率, 负数:拒绝率
	}
	{
		type: weight
		weight: {
			pass: 正数:通过分数, 负数:拒绝分数
			weights: [{actor: weight}, {actor: weight}]
		}
	}
	*/
}
