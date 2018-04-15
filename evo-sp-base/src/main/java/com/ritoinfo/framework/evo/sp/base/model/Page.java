package com.ritoinfo.framework.evo.sp.base.model;

import lombok.Data;

/**
 * User: Kyll
 * Date: 2018-02-28 10:14
 */
@Data
public class Page {
	private Integer pageNo;
	private Integer pageSize;
	private String pageSort;
	private String pageOrder;
}
