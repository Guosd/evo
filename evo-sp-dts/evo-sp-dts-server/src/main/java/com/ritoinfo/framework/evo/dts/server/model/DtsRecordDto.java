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
public class DtsRecordDto implements Serializable {
	private Long id;
	private String businessKey;
	private String producerStep;
	private String consumerStep;

	public DtsRecordDto() {
	}

	public DtsRecordDto(Long id, String businessKey, String producerStep, String consumerStep) {
		this.id = id;
		this.businessKey = businessKey;
		this.producerStep = producerStep;
		this.consumerStep = consumerStep;
	}
}
