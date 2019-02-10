package com.ritoinfo.framework.evo.common.model;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2018-02-28 10:14
 * 用于mybatis-xml方式分页查询拦截器中。不需要开发人员直接使用。
 */
@Data
public class Page {
	private Integer pageNo;
	private Integer pageSize;
	private String pageSort;
	private String pageOrder;
}
