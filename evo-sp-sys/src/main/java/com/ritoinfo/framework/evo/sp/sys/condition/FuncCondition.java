package com.ritoinfo.framework.evo.sp.sys.condition;

import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-03-04 17:59
 */
public class FuncCondition extends BaseCondition<Long> {
	@Getter @Setter private String name;
	@Getter @Setter private String code;
	@Getter @Setter private Long parentId;
	@Getter @Setter private String url;
	@Getter @Setter private String method;
	@Getter @Setter private Integer sort;
}
