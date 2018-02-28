package com.ritoinfo.framework.evo.sp.base.model;

import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-02-28 10:14
 */
public class Page {
	@Getter @Setter private Integer pageNo;
	@Getter @Setter private Integer pageSize;
	@Getter @Setter private String pageSort;
	@Getter @Setter private String pageOrder;
}
