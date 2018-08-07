package com.ritoinfo.framework.evo.sp.sys.entity;

import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseXmlEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * User: Kyll
 * Date: 2018-04-23 19:37
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Micro extends BaseXmlEntity<Long> {
	private String name;// 微服务名称
	private String code;// 微服务编码 spring.application.name
	private String prefix;// 微服务在ZUUL映射的前缀 zuul.route.routes.XXXX.path
}
