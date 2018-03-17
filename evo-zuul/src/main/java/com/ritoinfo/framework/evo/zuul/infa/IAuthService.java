package com.ritoinfo.framework.evo.zuul.infa;

import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: Kyll
 * Date: 2018-03-13 16:37
 */
@FeignClient(value = "evo-sp-auth", path = "/auth")
public interface IAuthService {
	@GetMapping("verify")
	ServiceResponse<Boolean> verify(@RequestParam("uri") String uri, @RequestParam("token") String token);
}
