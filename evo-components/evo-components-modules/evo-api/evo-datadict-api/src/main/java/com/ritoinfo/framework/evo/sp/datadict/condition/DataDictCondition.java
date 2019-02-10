package com.ritoinfo.framework.evo.sp.datadict.condition;

import com.ritoinfo.framework.evo.common.model.PageDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-04-13 09:48
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class DataDictCondition extends PageDto {
	private Long id;
	private String name;
	private String code;
	private String key;
	private String value;
	private Integer sort;
}
