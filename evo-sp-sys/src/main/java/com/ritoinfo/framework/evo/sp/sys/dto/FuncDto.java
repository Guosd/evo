package com.ritoinfo.framework.evo.sp.sys.dto;

import com.ritoinfo.framework.evo.sp.base.starter.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-03-08 11:43
 */
public class FuncDto extends BaseDto<Long> {
	@Getter @Setter private String name;
	@Getter @Setter private String code;
	@Getter @Setter private Long parentId;
	@Getter @Setter private String prefix;
	@Getter @Setter private String uri;
	@Getter @Setter private String method;
	@Getter @Setter private Integer sort;
}
