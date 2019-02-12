package com.github.framework.evo.common.model;

import lombok.Data;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-12 14:02
 * 分页查询结果对象。用于分页查询返回值。
 */
@Data
public class PageList<T> {
	private Integer pageNo;
	private Integer pageSize;
	private String pageSort;
	private String pageOrder;
	private Integer totalRecord;
	private List<T> dataList;

	public Integer getTotalPage() {
		return totalRecord % pageSize == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
	}

	public void copyPage(PageList pageList) {
		setPageNo(pageList.getPageNo());
		setPageSize(pageList.getPageSize());
		setPageSort(pageList.getPageSort());
		setPageOrder(pageList.getPageOrder());
		setTotalRecord(pageList.getTotalRecord());
	}
}
