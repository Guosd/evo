package com.ritoinfo.framework.evo.sp.demo.condition;

import com.ritoinfo.framework.evo.sp.base.starter.dto.PageDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-07-12 16:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CountryCondition extends PageDto {
	private Long id;
	private String countryCode;
	private String countryName;
}
