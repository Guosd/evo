package com.ritoinfo.framework.evo.dts.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-10-11 15:19
 */
@Data
public class DtsMessage implements Serializable {
	private String messageKey;// 消息Key
	private String businessKey;// 业务Key(事务Key)
	private String source;// 事务生产者
	private String target;// 事务消费者
	private String arg;// 业务参数 JSON
}
