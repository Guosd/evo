package com.ritoinfo.framework.evo.auth.rest;

import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.exception.BizzException;
import com.ritoinfo.framework.evo.common.exception.RestException;
import com.ritoinfo.framework.evo.auth.bizz.PermissionBizz;
import com.ritoinfo.framework.evo.auth.model.PermissionParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2019-01-16 13:43
 */
@Slf4j
@RequestMapping("/permission")
@RestController
public class PermissionRest {
	@Autowired
	private PermissionBizz permissionBizz;

	@PostMapping("/check")
	public ServiceResponse<Boolean> check(@Validated @RequestBody PermissionParam permissionParam) {
		try {
			return ServiceResponse.ok(permissionBizz.check(permissionParam));
		} catch (BizzException e) {
			throw new RestException(Const.RC_AUTH_VERIFY, e);
		}
	}
}
