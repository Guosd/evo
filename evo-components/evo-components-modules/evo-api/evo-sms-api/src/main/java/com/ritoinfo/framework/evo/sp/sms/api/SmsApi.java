package com.ritoinfo.framework.evo.sp.sms.api;

import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.sms.dto.SmsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2018-04-20 16:14
 */
@FeignClient(value = "evo-sp-sms")
public interface SmsApi {
	@PostMapping("/send")
	ServiceResponse send(@RequestBody SmsDto smsDto);
}
