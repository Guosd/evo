package com.ritoinfo.framework.evo.sp.dts.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-10-11 15:19
 */
@Data
public class DtsBizzMessageDto implements Serializable {
	private String messageKey;// 消息Key
	private String businessKey;// 业务Key(事务Key)
	private String producer;// 事务生产者
	private String consumer;// 事务消费者
	private String content;// 业务参数 JSON
}
