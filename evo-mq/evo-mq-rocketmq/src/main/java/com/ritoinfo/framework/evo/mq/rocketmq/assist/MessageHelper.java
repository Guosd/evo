package com.ritoinfo.framework.evo.mq.rocketmq.assist;

import com.ritoinfo.framework.evo.common.uitl.AlgorithmUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.mq.rocketmq.exception.RocketMQOperateException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

/**
 * User: Kyll
 * Date: 2018-09-28 16:51
 */
public class MessageHelper {
	private static int message_count = 0;

	public static String generateMessageKey() {
		return DateUtil.formatDatetimeCompact(DateUtil.now()) + "_" + AlgorithmUtil.randomNumber(8) + "_" + StringUtil.leftPad(String.valueOf(message_count++), 8, '0');
	}

	public static Message createMessage(String topic, String tags, String keys, String content) {
		try {
			return new Message(topic, tags, keys, content.getBytes(RemotingHelper.DEFAULT_CHARSET));
		} catch (UnsupportedEncodingException e) {
			throw new RocketMQOperateException("建立 Message 失败", e);
		}
	}
}
