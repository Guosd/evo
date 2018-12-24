package com.ritoinfo.framework.evo.sp.auth.authorization.api;

import com.ritoinfo.framework.evo.sp.auth.authorization.api.model.UserDetailsDto;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * User: Kyll
 * Date: 2018-12-12 14:41
 */
@FeignClient("${evo.sp.auth.user-details-service-id}")
public interface UserDetailsApi {
	@GetMapping("${evo.sp.auth.user-details-password-uri}" + "/{username}")
	ServiceResponse<UserDetailsDto> getByUsername(@PathVariable("username") String username);

	@GetMapping("${evo.sp.auth.user-details-mnvc-uri}" + "/{mobileNumber}")
	ServiceResponse<UserDetailsDto> getByMobileNumber(@PathVariable("mobileNumber") String mobileNumber);
}
