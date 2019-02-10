package com.ritoinfo.framework.evo.sp.sys.entity;

import com.ritoinfo.framework.evo.base.starter.entity.BaseXmlEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2018-04-23 21:07
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Menu extends BaseXmlEntity<Long> {
	private Long parentId;// 上级菜单ID
	private Long funcId;// 功能ID
	private String name;// 菜单名称
	private String code;// 菜单编码
	private Integer sort;// 排序号
}
