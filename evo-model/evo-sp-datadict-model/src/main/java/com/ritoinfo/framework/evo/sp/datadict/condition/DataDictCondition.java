package com.ritoinfo.framework.evo.sp.datadict.condition;

import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
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
public class DataDictCondition extends BaseCondition<Long> {
	private String name;
	private String code;
	private String key;
	private String value;
	private Integer sort;
}
