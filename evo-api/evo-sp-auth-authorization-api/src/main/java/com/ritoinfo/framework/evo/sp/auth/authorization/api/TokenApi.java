package com.ritoinfo.framework.evo.sp.auth.authorization.api;

import com.ritoinfo.framework.evo.common.model.UserContext;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2019-01-16 13:40
 */
@FeignClient(name = "evo-sp-auth-authorization", path = "/token")
public interface TokenApi {
	@PostMapping("/check")
	ServiceResponse<UserContext> check(@RequestBody String accessToken);
}
