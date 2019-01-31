package com.ritoinfo.framework.evo.sp.auth.api;

import com.ritoinfo.framework.evo.sp.auth.api.model.UserDetailsDto;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2018-12-12 14:41
 */
@FeignClient("${evo.sp.auth.user-details.service-id}")
public interface UserDetailsApi {
	@GetMapping("${evo.sp.auth.user-details.username-uri}/{username}")
	ServiceResponse<UserDetailsDto> getByUsername(@PathVariable("username") String username);

	@GetMapping("${evo.sp.auth.user-details.mobile-number-uri}/{mobileNumber}")
	ServiceResponse<UserDetailsDto> getByMobileNumber(@PathVariable("mobileNumber") String mobileNumber);

	@PutMapping("${evo.sp.auth.user-details.update-login-info-uri}/{id}")
	ServiceResponse updateLoginInfo(@PathVariable("id") String id, @RequestBody String loginIp);
}
