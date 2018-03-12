package com.ritoinfo.framework.evo.sp.base.condition;

import com.ritoinfo.framework.evo.sp.base.model.Page;
import com.ritoinfo.framework.evo.sp.base.validate.group.ListGroup;
import com.ritoinfo.framework.evo.sp.base.validate.group.PageGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * User: Kyll
 * Date: 2018-02-12 14:07
 */
public abstract class BaseCondition<PK> {
	@Getter @Setter
	protected PK id;
	@Getter @Setter
	@Null(groups = ListGroup.class)
	@NotNull(groups = PageGroup.class)
	protected Integer pageNo;
	@Getter @Setter
	@Null(groups = ListGroup.class)
	@NotNull(groups = PageGroup.class)
	protected Integer pageSize;
	@Getter @Setter
	@Null(groups = ListGroup.class)
	@NotBlank(groups = PageGroup.class)
	protected String pageSort;
	@Getter @Setter
	@Null(groups = ListGroup.class)
	@NotBlank(groups = PageGroup.class)
	protected String pageOrder;

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
