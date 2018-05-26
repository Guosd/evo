package com.ritoinfo.framework.evo.sp.activiti.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-04-10 14:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommentCondition extends ActivitiPageCondition {
	private String processInstanceId;
}
