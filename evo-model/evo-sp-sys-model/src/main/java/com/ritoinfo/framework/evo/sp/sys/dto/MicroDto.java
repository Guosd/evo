package com.ritoinfo.framework.evo.sp.sys.dto;

import com.ritoinfo.framework.evo.sp.base.starter.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-04-23 19:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MicroDto extends BaseDto<Long> {
	private String name;
	private String code;
	private String prefix;
}
