package com.ritoinfo.framework.evo.sp.sys.dto;

import com.ritoinfo.framework.evo.sp.base.starter.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-03-08 11:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleDto extends BaseDto<Long> {
	private String name;
	private String code;
}
