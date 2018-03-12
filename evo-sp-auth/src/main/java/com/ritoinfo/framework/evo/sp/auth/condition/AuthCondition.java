package com.ritoinfo.framework.evo.sp.auth.condition;

import com.ritoinfo.framework.evo.sp.base.validate.group.LoginGroup;
import com.ritoinfo.framework.evo.sp.base.validate.group.LogoutGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

/**
 * User: Kyll
 * Date: 2018-03-08 14:38
 */
public class AuthCondition {
	@Getter @Setter
	@NotBlank(groups = LoginGroup.class)
	private String username;
	@Getter @Setter
	@Null(groups = LogoutGroup.class)
	@NotBlank(groups = LoginGroup.class)
	private String password;
}
