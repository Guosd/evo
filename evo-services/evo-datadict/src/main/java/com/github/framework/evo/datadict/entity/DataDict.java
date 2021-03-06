package com.github.framework.evo.datadict.entity;

import com.github.framework.evo.base.entity.BaseXmlEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2018-04-13 09:44
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DataDict extends BaseXmlEntity<Long> {
	private String name;// 名称
	private String code;// 编码
	private String key;// 键
	private String value;// 值
	private Integer sort;// 排序
}
