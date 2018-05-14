package com.ritoinfo.framework.evo.sp.sys.dto;

import com.ritoinfo.framework.evo.sp.base.starter.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-04-23 21:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuDto extends BaseDto<Long> {
	private Long parentId;
	private String parentName;
	private Long funcId;
	private String funcName;
	private String funcCode;
	private String funcUri;
	private String funcMethod;
	private String name;
	private String code;
	private Integer sort;
}
