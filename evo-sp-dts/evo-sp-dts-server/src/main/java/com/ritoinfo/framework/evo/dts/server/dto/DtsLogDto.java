package com.ritoinfo.framework.evo.dts.server.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-10-18 17:01
 */
@Builder
@Data
public class DtsLogDto implements Serializable {
	private Long id;
	private String messageKey;
	private String businessKey;
	private String source;
	private String target;
	private String arg;
	private String sourceStatus;
	private String targetStatus;

	public DtsLogDto() {
	}

	public DtsLogDto(Long id, String messageKey, String businessKey, String source, String target, String arg, String sourceStatus, String targetStatus) {
		this.id = id;
		this.messageKey = messageKey;
		this.businessKey = businessKey;
		this.source = source;
		this.target = target;
		this.arg = arg;
		this.sourceStatus = sourceStatus;
		this.targetStatus = targetStatus;
	}
}
