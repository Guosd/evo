package com.github.framework.evo.sms.api;

import com.github.framework.evo.sms.dto.SmsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * User: Kyll
 * Date: 2018-04-20 16:14
 */
@FeignClient(value = "evo-sms")
public interface SmsApi {
	@PostMapping("/send")
	void send(@RequestBody SmsDto smsDto);
}
