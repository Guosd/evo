package com.ritoinfo.framework.evo.rest;

import com.ritoinfo.framework.evo.bizz.DirectMessageBizz;
import com.ritoinfo.framework.evo.bizz.DurableMessageBizz;
import com.ritoinfo.framework.evo.bizz.FanoutMessageBizz;
import com.ritoinfo.framework.evo.bizz.RpcClientMessageBizz;
import com.ritoinfo.framework.evo.bizz.SendMessageBizz;
import com.ritoinfo.framework.evo.bizz.TopicMessageBizz;
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
	@Autowired
	private FanoutMessageBizz fanoutMessageBizz;
	@Autowired
	private DirectMessageBizz directMessageBizz;
	@Autowired
	private TopicMessageBizz topicMessageBizz;
	@Autowired
	private RpcClientMessageBizz rpcClientMessageBizz;

	@GetMapping("/send")
	public ServiceResponse<String> send() {
		return ServiceResponse.ok(sendMessageBizz.process());
	}

	@GetMapping("/durable")
	public ServiceResponse<String> durable() {
		return ServiceResponse.ok(durableMessageBizz.process());
	}

	@GetMapping("/fanout")
	public ServiceResponse<String> fanout() {
		return ServiceResponse.ok(fanoutMessageBizz.process());
	}

	@GetMapping("/direct")
	public ServiceResponse<String> direct() {
		return ServiceResponse.ok(directMessageBizz.process());
	}

	@GetMapping("/topic")
	public ServiceResponse<String> topic() {
		return ServiceResponse.ok(topicMessageBizz.process());
	}

	@GetMapping("/rpc")
	public ServiceResponse<String> rpc() {
		return ServiceResponse.ok(rpcClientMessageBizz.process());
	}
}
