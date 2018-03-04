package com.ritoinfo.framework.evo.sp.sys.condition;

import com.ritoinfo.framework.evo.sp.base.condition.BaseCondition;
import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-03-04 17:49
 */
public class RoleCondition extends BaseCondition {
	@Getter @Setter private String name;
	@Getter @Setter private String code;
}
