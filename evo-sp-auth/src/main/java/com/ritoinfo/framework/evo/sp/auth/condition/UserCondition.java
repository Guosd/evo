package com.ritoinfo.framework.evo.sp.auth.condition;

import com.ritoinfo.framework.evo.sp.base.condition.BaseCondition;
import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-02-13 13:39
 */
public class UserCondition extends BaseCondition {
	@Getter @Setter private Long id;
	@Getter @Setter private String username;
	@Getter @Setter private String password;
}
