package com.github.framework.evo.sys.entity;

import com.github.framework.evo.base.entity.BaseXmlEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2018-02-28 15:23
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseXmlEntity<Long> {
	private String name;// 名称
	private String code;// 编码
}
