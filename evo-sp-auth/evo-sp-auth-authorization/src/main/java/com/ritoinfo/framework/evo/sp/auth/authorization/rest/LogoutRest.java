package com.ritoinfo.framework.evo.sp.auth.authorization.rest;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.sp.auth.authorization.bizz.LogoutBizz;
import com.ritoinfo.framework.evo.sp.base.exception.BizzException;
import com.ritoinfo.framework.evo.sp.base.exception.RestException;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
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
