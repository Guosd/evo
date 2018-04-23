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
	private Long microId;// 微服务ID
	private String uri;// 请求地址
	private String method;// GET,POST.PUT.DELETE方法
	private String menu;// 是否是菜单 YN01是， YN02否
	private Integer sort;// 排序号
}
