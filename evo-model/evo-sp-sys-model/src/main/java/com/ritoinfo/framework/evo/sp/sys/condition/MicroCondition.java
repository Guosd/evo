package com.ritoinfo.framework.evo.sp.sys.condition;

import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-04-23 20:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MicroCondition extends BaseCondition<Long> {
	private String name;
	private String code;
	private String prefix;
	private Integer sort;
}
