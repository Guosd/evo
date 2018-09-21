package com.ritoinfo.framework.evo.rest;

import com.ritoinfo.framework.evo.bizz.DurableMessageBizz;
import com.ritoinfo.framework.evo.bizz.SendMessageBizz;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-09-20 09:17
 */
@RestController
public class MessageRest {
	@Autowired
	private SendMessageBizz sendMessageBizz;
	@Autowired
	private DurableMessageBizz durableMessageBizz;

	@GetMapping("/send")
	public ServiceResponse<String> send() {
		return ServiceResponse.ok(sendMessageBizz.process());
	}

	@GetMapping("/durable")
	public ServiceResponse<String> durable() {
		return ServiceResponse.ok(durableMessageBizz.process());
	}
}
