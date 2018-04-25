package com.ritoinfo.framework.evo.sp.sys.condition;

import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-03-04 17:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FuncCondition extends BaseCondition<Long> {
	private Long microId;
	private String name;
	private String code;
	private String uri;
	private String method;
	private Integer sort;
}
