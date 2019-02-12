package com.ritoinfo.framework.evo.sys.condition;

import com.ritoinfo.framework.evo.common.model.PageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-04-23 20:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MicroCondition extends PageDto {
	private Long id;
	private String name;
	private String code;
	private String prefix;
	private Integer sort;
}
