package com.ritoinfo.framework.evo.sp.sys.entity;

import com.ritoinfo.framework.evo.sp.base.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2018-02-28 15:23
 */
@Data
@NoArgsConstructor
public class Role extends BaseEntity<Long> {
	private String name;// 名称
	private String code;// 编码
}
