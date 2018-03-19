package com.ritoinfo.framework.evo.sp.sys.entity;

import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2018-02-28 15:27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Func extends BaseEntity<Long> {
	private Long parentId;// 上级功能ID
	private String name;// 功能名称
	private String code;// 功能编码
	private String prefix;// 系统上下文前缀
	private String uri;// 请求地址
	private Integer sort;// 排序号
}
