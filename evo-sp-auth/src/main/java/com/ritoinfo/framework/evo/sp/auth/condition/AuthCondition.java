package com.ritoinfo.framework.evo.sp.auth.condition;

import com.ritoinfo.framework.evo.sp.auth.validate.group.LoginGroup;
import com.ritoinfo.framework.evo.sp.auth.validate.group.LogoutGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * User: Kyll
 * Date: 2018-03-08 14:38
 */
public class AuthCondition {
	@Getter @Setter
	@NotBlank(groups = {LoginGroup.class, LogoutGroup.class})
	private String username;
	@Getter @Setter
	@NotBlank(groups = LoginGroup.class)
	private String password;
}
