package com.ritoinfo.framework.evo.sp.auth.authorization.rest;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.model.UserContext;
import com.ritoinfo.framework.evo.sp.auth.authorization.bizz.TokenBizz;
import com.ritoinfo.framework.evo.sp.base.exception.BizzException;
import com.ritoinfo.framework.evo.sp.base.exception.RestException;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2019-01-16 13:40
 */
@Slf4j
@RequestMapping("/token")
@RestController
public class TokenRest {
	@Autowired
	private TokenBizz tokenBizz;

	@PostMapping("/try")
	public ServiceResponse<String> tryRefresh(@RequestBody String accessToken) {
		try {
			return ServiceResponse.ok(tokenBizz.tryRefresh(accessToken));
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_TRY_REFRESH, e);
		}
	}

	@PostMapping("/refresh")
	public ServiceResponse<String> refresh(@RequestBody String accessToken) {
		try {
			return ServiceResponse.ok(tokenBizz.refresh(accessToken));
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_REFRESH, e);
		}
	}

	@PostMapping("/check")
	public ServiceResponse<UserContext> check(@RequestBody String accessToken) {
		try {
			return ServiceResponse.ok(tokenBizz.check(accessToken));
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_CHECK, e);
		}
	}
}
