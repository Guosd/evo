package com.github.framework.evo.sms.rest;

import com.github.framework.evo.sms.api.SmsApi;
import com.github.framework.evo.sms.bizz.SmsBizz;
import com.github.framework.evo.sms.dto.SmsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-05-17 15:23
 */
@Slf4j
@RestController
public class SmsRest implements SmsApi {
	@Autowired
	private SmsBizz smsBizz;

	@Override
	public void send(@RequestBody SmsDto smsDto) {
		smsBizz.send(smsDto);
	}
}
