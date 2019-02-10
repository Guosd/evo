package com.ritoinfo.framework.evo.sp.auth.authorization.api;

import com.ritoinfo.framework.evo.sp.auth.model.MobileNumberParam;
import com.ritoinfo.framework.evo.sp.auth.model.VerifyCodeDto;
import com.ritoinfo.framework.evo.sp.auth.model.VerifyCodeParam;
import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2018-04-20 11:47
 */
@FeignClient(name = "evo-auth", path = "/mobile-number")
public interface MobileNumberVerifyCodeApi {
	@PostMapping("/verify-code")
	ServiceResponse<VerifyCodeDto> get(@Validated @RequestBody VerifyCodeParam verifyCodeParam);

	@PostMapping("/verify-code/sign-in")
	ServiceResponse<String> getForSignIn(@Validated @RequestBody VerifyCodeParam verifyCodeParam);

	@PostMapping("/verify-code/sign-up")
	ServiceResponse<String> getForSignUp(@Validated @RequestBody VerifyCodeParam verifyCodeParam);

	@PostMapping("/check")
	ServiceResponse<Boolean> check(@Validated @RequestBody MobileNumberParam mobileNumberParam);

	@PostMapping("/login")
	ServiceResponse<String> login(@Validated @RequestBody MobileNumberParam mobileNumberParam);
}
