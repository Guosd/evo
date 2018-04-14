package com.ritoinfo.framework.evo.sp.datadict.dto;

import com.ritoinfo.framework.evo.sp.base.starter.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-04-13 09:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataDictDto extends BaseDto<Long> {
	private String name;
	private String code;
	private String key;
	private String value;
	private Integer sort;
}
