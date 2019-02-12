package com.github.framework.evo.sample.entity;

import com.github.framework.evo.base.entity.BaseXmlEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: Kyll
 * Date: 2018-07-12 13:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Country extends BaseXmlEntity<Long> {
	private String code;
	private String name;
}
