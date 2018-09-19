package com.ritoinfo.framework.evo.rest;

import com.ritoinfo.framework.evo.bizz.AsynchronouslyMessageBizz;
import com.ritoinfo.framework.evo.bizz.BroadcastMessageBizz;
import com.ritoinfo.framework.evo.bizz.OnewayMessageBizz;
import com.ritoinfo.framework.evo.bizz.OrderMessageBizz;
import com.ritoinfo.framework.evo.bizz.ScheduleMessageBizz;
import com.ritoinfo.framework.evo.bizz.SynchronouslyMessageBizz;
import com.ritoinfo.framework.evo.bizz.TransactionMessageBizz;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import org.apache.rocketmq.client.producer.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-09-19 16:20
 */
@RestController
public class MessageRest {
	@Autowired
	private SynchronouslyMessageBizz synchronouslyMessageBizz;
	@Autowired
	private AsynchronouslyMessageBizz asynchronouslyMessageBizz;
	@Autowired
	private OnewayMessageBizz onewayMessageBizz;
	@Autowired
	private BroadcastMessageBizz broadcastMessageBizz;
	@Autowired
	private OrderMessageBizz orderMessageBizz;
	@Autowired
	private ScheduleMessageBizz scheduleMessageBizz;
	@Autowired
	private TransactionMessageBizz transactionMessageBizz;

	@GetMapping("/sync")
	public ServiceResponse<SendResult> synchronously() {
		return ServiceResponse.ok(synchronouslyMessageBizz.send());
	}

	@GetMapping("/async")
	public ServiceResponse<SendResult> Asynchronously() {
		return ServiceResponse.ok(asynchronouslyMessageBizz.send());
	}

	@GetMapping("/oneway")
	public ServiceResponse<SendResult> oneway() {
		return ServiceResponse.ok(onewayMessageBizz.send());
	}

	@GetMapping("/broadcast")
	public ServiceResponse<SendResult> broadcast() {
		return ServiceResponse.ok(broadcastMessageBizz.send());
	}

	@GetMapping("/order")
	public ServiceResponse<SendResult> order() {
		return ServiceResponse.ok(orderMessageBizz.send());
	}

	@GetMapping("/schedule")
	public ServiceResponse<SendResult> schedule() {
		return ServiceResponse.ok(scheduleMessageBizz.send());
	}

	@GetMapping("/transaction")
	public ServiceResponse<SendResult> transaction() {
		return ServiceResponse.ok(transactionMessageBizz.send());
	}
}
