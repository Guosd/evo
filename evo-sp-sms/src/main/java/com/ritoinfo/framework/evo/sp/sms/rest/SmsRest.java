package com.ritoinfo.framework.evo.sp.sms.rest;

import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.sms.dto.SmsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-05-17 15:23
 */
@Slf4j
@RestController
public class SmsRest {
	@PostMapping("/send")
	public ServiceResponse<String> send(@RequestBody SmsDto smsDto) {
		return ServiceResponse.ok();
	}
}
