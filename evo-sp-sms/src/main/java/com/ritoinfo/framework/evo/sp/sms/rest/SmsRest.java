package com.ritoinfo.framework.evo.sp.sms.rest;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.sp.base.exception.BizzException;
import com.ritoinfo.framework.evo.sp.base.exception.RestException;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.sms.bizz.SmsBizz;
import com.ritoinfo.framework.evo.sp.sms.dto.SmsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private SmsBizz smsBizz;

	@PostMapping("/send")
	public ServiceResponse<String> send(@RequestBody SmsDto smsDto) {
		try {
			smsBizz.send(smsDto);
			return ServiceResponse.ok();
		} catch (BizzException e) {
			throw new RestException(Const.RC_SMS);
		}
	}
}
