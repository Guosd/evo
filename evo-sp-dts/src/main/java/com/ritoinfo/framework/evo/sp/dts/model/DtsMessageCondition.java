package com.ritoinfo.framework.evo.sp.dts.model;

import com.ritoinfo.framework.evo.sp.base.dto.PageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-11-06 09:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DtsMessageCondition extends PageDto {
	private String logMessageKey;
	private String messageKey;
	private String businessKey;
	private String content;
	private String producer;
	private String consumer;
	private String role;
	private String step;
}
