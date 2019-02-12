package com.ritoinfo.framework.evo.sys.entity;

import com.ritoinfo.framework.evo.base.entity.BaseXmlEntity;
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
public class Func extends BaseXmlEntity<Long> {
	private Long microId;// 微服务ID
	private String name;// 功能名称
	private String code;// 功能编码
	private String uri;// 请求地址
	private String method;// GET,POST.PUT.DELETE方法
}
