package com.ritoinfo.framework.evo.sp.auth.api;

import com.ritoinfo.framework.evo.sp.auth.dto.LoginDto;
import com.ritoinfo.framework.evo.sp.auth.dto.TokenDto;
import com.ritoinfo.framework.evo.sp.auth.dto.VerifyDto;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2018-03-08 14:33
 */
@Component
@FeignClient(value = "evo-sp-auth")
public interface AuthApi {
	@PostMapping("login")
	ServiceResponse<TokenDto> login(@RequestBody LoginDto loginDto);

	@GetMapping("logout/{username}")
	ServiceResponse logout(@PathVariable("username") String username);

	@PostMapping("verify")
	ServiceResponse<Boolean> verify(@RequestBody VerifyDto verifyDto);
}
