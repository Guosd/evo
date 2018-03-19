package com.ritoinfo.framework.evo.sp.sys.condition;

import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-02-13 13:39
 */
public class UserCondition extends BaseCondition<Long> {
	@Getter @Setter private String username;
	@Getter @Setter private String password;
	@Getter @Setter private String name;
	@Getter @Setter private String code;
	@Getter @Setter private String email;
	@Getter @Setter private String phone;
	@Getter @Setter private String freeze;
	@Getter @Setter private String lastLoginTime;
	@Getter @Setter private String lastLoginIp;
}
