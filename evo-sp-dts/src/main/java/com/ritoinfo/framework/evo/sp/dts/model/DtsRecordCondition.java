package com.ritoinfo.framework.evo.sp.dts.model;

import com.ritoinfo.framework.evo.sp.base.dto.PageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-10-18 17:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DtsRecordCondition extends PageDto {
	private String businessKey;
	private String producerStep;
	private String consumerStep;
}
