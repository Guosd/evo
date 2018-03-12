package com.ritoinfo.framework.evo.sp.auth.rest;

import com.ritoinfo.framework.evo.sp.auth.bizz.AuthBizz;
import com.ritoinfo.framework.evo.sp.auth.condition.AuthCondition;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.validate.group.LoginGroup;
import com.ritoinfo.framework.evo.sp.base.validate.group.LogoutGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-03-08 14:33
 */
@Slf4j
@RequestMapping("auth")
@RestController
public class AuthRest {
	@Autowired
	private AuthBizz authBizz;

	@GetMapping("login")
	public ServiceResponse login(@Validated(LoginGroup.class) AuthCondition authCondition) {
		return ServiceResponse.ok(authBizz.authorize(authCondition));
	}

	@GetMapping("logout")
	public ServiceResponse logout(@Validated(LogoutGroup.class) AuthCondition authCondition) {
		authBizz.clear(authCondition.getUsername());
		return ServiceResponse.ok();
	}
}
