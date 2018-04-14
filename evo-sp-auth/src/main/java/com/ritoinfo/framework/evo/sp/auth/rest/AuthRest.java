package com.ritoinfo.framework.evo.sp.auth.rest;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.jwt.model.TokenInfo;
import com.ritoinfo.framework.evo.sp.auth.bizz.AuthBizz;
import com.ritoinfo.framework.evo.sp.auth.condition.AuthCondition;
import com.ritoinfo.framework.evo.sp.auth.validate.group.LoginGroup;
import com.ritoinfo.framework.evo.sp.auth.validate.group.LogoutGroup;
import com.ritoinfo.framework.evo.sp.base.exception.BizzException;
import com.ritoinfo.framework.evo.sp.base.exception.RestException;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-03-08 14:33
 */
@Slf4j
@RestController
public class AuthRest {
	@Autowired
	private AuthBizz authBizz;

	@PostMapping("login")
	public ServiceResponse<TokenInfo> login(@Validated(LoginGroup.class) @RequestBody AuthCondition authCondition) {
		try {
			return ServiceResponse.ok(authBizz.authorize(authCondition));
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_LOGIN, Const.getRcm(Const.RC_AUTH_LOGIN));
		}
	}

	@PostMapping("logout")
	public ServiceResponse logout(@Validated(LogoutGroup.class) @RequestBody AuthCondition authCondition) {
		authBizz.clear(authCondition.getUsername());
		return ServiceResponse.ok();
	}

	@PostMapping("verify")
	public ServiceResponse<Boolean> verify(@RequestParam String uri, @RequestParam String token) {
		return ServiceResponse.ok(authBizz.verify(uri, token));
	}
}
