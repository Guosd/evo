package com.ritoinfo.framework.evo.sp.base.condition;

import com.ritoinfo.framework.evo.sp.base.model.Page;
import com.ritoinfo.framework.evo.sp.base.validate.group.ListGroup;
import com.ritoinfo.framework.evo.sp.base.validate.group.PageGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * User: Kyll
 * Date: 2018-02-12 14:07
 */
public abstract class BaseCondition<PK> {
	@Getter @Setter
	private PK id;
	@Getter @Setter
	@Null(groups = ListGroup.class)
	@NotNull(groups = PageGroup.class)
	private Integer pageNo;
	@Getter @Setter
	@Null(groups = ListGroup.class)
	@NotNull(groups = PageGroup.class)
	private Integer pageSize;
	@Getter @Setter
	@Null(groups = ListGroup.class)
	@NotNull(groups = PageGroup.class)
	private String pageSort;
	@Getter @Setter
	@Null(groups = ListGroup.class)
	@NotNull(groups = PageGroup.class)
	private String pageOrder;

	private Page page;

	@SuppressWarnings("unchecked")
	public <C extends BaseCondition> C count() {
		page = null;
		return (C) this;
	}

	@SuppressWarnings("unchecked")
	public <C extends BaseCondition> C page() {
		page = new Page();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setPageSort(pageSort);
		page.setPageOrder(pageOrder);
		return (C) this;
	}
}
