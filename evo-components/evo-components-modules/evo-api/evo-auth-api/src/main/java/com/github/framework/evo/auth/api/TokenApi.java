package com.github.framework.evo.auth.api;

import com.github.framework.evo.common.model.ServiceResponse;
import com.github.framework.evo.common.model.UserContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2019-01-16 13:40
 */
@FeignClient(name = "evo-auth", path = "/token")
public interface TokenApi {
	@PostMapping("/check")
	ServiceResponse<UserContext> check(@RequestBody String accessToken);
}
