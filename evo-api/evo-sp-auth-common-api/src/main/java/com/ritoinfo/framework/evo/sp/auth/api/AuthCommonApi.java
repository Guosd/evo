package com.ritoinfo.framework.evo.sp.auth.api;

import com.ritoinfo.framework.evo.sp.auth.dto.VerifyDto;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2018-03-08 14:33
 */
@FeignClient(value = "evo-sp-auth-common")
public interface AuthCommonApi {
	@PostMapping("logout")
	ServiceResponse<Boolean> logout(@RequestBody String token);

	@PostMapping("try")
	ServiceResponse<String> tryRefresh(@RequestBody String token);

	@PostMapping("refresh")
	ServiceResponse<String> refresh(@RequestBody String token);

	@PostMapping("verify")
	ServiceResponse<Boolean> verify(@RequestBody VerifyDto verifyDto);
}
