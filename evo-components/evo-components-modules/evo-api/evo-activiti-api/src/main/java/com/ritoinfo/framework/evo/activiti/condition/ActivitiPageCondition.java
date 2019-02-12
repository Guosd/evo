package com.ritoinfo.framework.evo.activiti.condition;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2016-07-25 11:29
 */
@Data
public class ActivitiPageCondition {
	private Integer pageNo;
	private Integer pageSize;
	private String pageSort;
	private String pageOrder;
}
