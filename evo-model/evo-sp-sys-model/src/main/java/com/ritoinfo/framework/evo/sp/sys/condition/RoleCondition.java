package com.ritoinfo.framework.evo.sp.sys.condition;

import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-03-04 17:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleCondition extends BaseCondition<Long> {
	private String name;
	private String code;
}
