package com.ritoinfo.framework.evo.zuul.infa;

import com.ritoinfo.framework.evo.sp.base.infa.model.ServiceResponseWrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: Kyll
 * Date: 2018-03-13 16:37
 */
@FeignClient(value = "evo-sp-auth")
public interface IAuthService {
	@PostMapping("verify")
	ServiceResponseWrapper<Boolean> verify(@RequestParam("uri") String uri, @RequestParam("token") String token);
}
