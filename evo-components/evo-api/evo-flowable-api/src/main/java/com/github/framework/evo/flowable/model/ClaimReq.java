package com.github.framework.evo.flowable.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2019-03-25 23:09
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ClaimReq {
	private String taskId;
	private String userId;
}
