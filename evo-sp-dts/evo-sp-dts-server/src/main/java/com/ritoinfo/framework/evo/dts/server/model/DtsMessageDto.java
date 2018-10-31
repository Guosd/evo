package com.ritoinfo.framework.evo.dts.server.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-10-18 17:01
 */
@Builder
@Data
public class DtsMessageDto implements Serializable {
	private Long id;
	private String logMessageKey;
	private String messageKey;
	private String businessKey;
	private String content;
	private String producer;
	private String consumer;
	private String role;
	private String step;

	public DtsMessageDto() {
	}

	public DtsMessageDto(Long id, String logMessageKey, String messageKey, String businessKey, String content, String producer, String consumer, String role, String step) {
		this.id = id;
		this.logMessageKey = logMessageKey;
		this.messageKey = messageKey;
		this.businessKey = businessKey;
		this.content = content;
		this.producer = producer;
		this.consumer = consumer;
		this.role = role;
		this.step = step;
	}
}
