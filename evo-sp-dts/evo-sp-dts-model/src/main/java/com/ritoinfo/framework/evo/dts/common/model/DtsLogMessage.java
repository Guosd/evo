package com.ritoinfo.framework.evo.dts.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-10-11 15:19
 */
@Data
public class DtsLogMessage implements Serializable {
	private String messageKey;// 消息Key
	private String businessKey;// 业务Key(事务Key)
	private String source;// 事务生产者
	private String target;// 事务消费者
	private String content;// 业务参数 JSON
	private String role;// 角色 RE00: 事务生产者 RE01: 事务消费者
	private String step;// 阶段 SS00: 事务生产者处理中 SS01: 事务生产者已完成，ST00: 事务消费者处理中 ST01: 事务消费者已完成
}
