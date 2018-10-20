package com.ritoinfo.framework.evo.dts.common;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * User: Kyll
 * Date: 2018-10-19 11:55
 */
public interface DtsTransaction {
	String getBusinessKey(String namesrvAddr, String group, String topic, String tags, String target, String applicationName);

	Object getArg(String namesrvAddr, String group, String topic, String tags, String target, String applicationName);

	LocalTransactionState checkLocal(MessageExt messageExt);
}
