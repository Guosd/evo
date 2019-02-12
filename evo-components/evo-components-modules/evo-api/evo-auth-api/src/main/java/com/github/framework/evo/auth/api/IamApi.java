package com.github.framework.evo.auth.api;

import com.github.framework.evo.auth.model.RbacDto;
import com.github.framework.evo.auth.model.UserDetailsDto;
import com.github.framework.evo.common.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2019-02-11 12:34
 */
@FeignClient("${evo.auth.iam.service-id}")
public interface IamApi {
	@GetMapping("${evo.auth.iam.user-details.username-uri}/{username}")
	ServiceResponse<UserDetailsDto> getByUsername(@PathVariable("username") String username);

	@GetMapping("${evo.auth.iam.user-details.mobile-number-uri}/{mobileNumber}")
	ServiceResponse<UserDetailsDto> getByMobileNumber(@PathVariable("mobileNumber") String mobileNumber);

	@PutMapping("${evo.auth.iam.user-details.update-login-info-uri}/{id}")
	ServiceResponse updateLoginInfo(@PathVariable("id") String id, @RequestBody String loginIp);

	@PostMapping("${evo.auth.iam.rbac.uri}")
	ServiceResponse<Boolean> check(@RequestBody RbacDto rbacDto);
}
