package com.ritoinfo.framework.evo.sp.sys.dto;

import com.ritoinfo.framework.evo.sp.base.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-03-08 11:43
 */
public class RoleDto extends BaseDto<Long> {
	@Getter @Setter private String name;
	@Getter @Setter private String code;
}
