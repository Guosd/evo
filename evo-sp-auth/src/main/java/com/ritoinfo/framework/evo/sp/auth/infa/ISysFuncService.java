package com.ritoinfo.framework.evo.sp.auth.infa;

import com.ritoinfo.framework.evo.sp.auth.infa.dto.FuncDto;
import com.ritoinfo.framework.evo.sp.base.infa.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-15 13:49
 */
@FeignClient(value = "evo-sp-sys", path = "/func")
public interface ISysFuncService {
	@GetMapping("/user/{username}")
	ServiceResponse<List<FuncDto>> getByUsername(@PathVariable("username") String username);
}
