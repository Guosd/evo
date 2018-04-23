package com.ritoinfo.framework.evo.sp.sys.condition;

import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-04-23 21:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuCondition extends BaseCondition<Long> {
	private Long parentId;
	private Long funcId;
	private String name;
	private String code;
	private Integer sort;
}
