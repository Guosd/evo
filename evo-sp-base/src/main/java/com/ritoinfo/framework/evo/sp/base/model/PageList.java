package com.ritoinfo.framework.evo.sp.base.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-12 14:02
 */
public class PageList<T> {
	@Getter @Setter private Integer pageNo;
	@Getter @Setter private Integer pageSize;
	@Setter private Integer totalRecord;
	private Integer totalPage;
	@Getter @Setter private List<T> dataList;

	public Integer getTotalRecord() {
		return totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
	}
}
