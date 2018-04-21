package com.ritoinfo.framework.evo.sms.api;

import com.ritoinfo.framework.evo.sms.dto.SmsDto;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2018-04-20 16:14
 */
@Component
@FeignClient(value = "evo-sms")
public interface SmsApi {
	@PostMapping("/sms")
	ServiceResponse send(@RequestBody SmsDto smsDto);
}
