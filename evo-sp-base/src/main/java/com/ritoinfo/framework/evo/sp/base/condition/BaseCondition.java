package com.ritoinfo.framework.evo.sp.base.condition;

import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-02-12 14:07
 */
public abstract class BaseCondition {
	@Getter @Setter private Integer pageNo;
	@Getter @Setter private Integer pageSize;
	@Getter @Setter private String pageSort;
	@Getter @Setter private String pageOrder;
}
