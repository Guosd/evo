package com.github.framework.evo.auth.rest;

import com.github.framework.evo.common.model.ServiceResponse;
import com.github.framework.evo.common.Const;
import com.github.framework.evo.common.exception.BizzException;
import com.github.framework.evo.common.exception.RestException;
import com.github.framework.evo.auth.bizz.LogoutBizz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2019-01-16 13:45
 */
@Slf4j
@RestController
public class LogoutRest {
	@Autowired
	private LogoutBizz logoutBizz;

	@PostMapping("/logout")
	public ServiceResponse<Boolean> logout(@RequestHeader(Const.HTTP_HEADER_TOKEN) String token) {
		try {
			logoutBizz.logout(token);
			return ServiceResponse.ok();
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_LOGOUT, e);
		}
	}
}
