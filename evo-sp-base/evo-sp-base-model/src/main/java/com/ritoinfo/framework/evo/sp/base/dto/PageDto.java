package com.ritoinfo.framework.evo.sp.base.dto;

import com.ritoinfo.framework.evo.sp.base.model.Page;
import com.ritoinfo.framework.evo.sp.base.validate.group.PageGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-07-15 12:58
 */
public class PageDto implements Serializable {
	@Getter @Setter
	@NotNull(groups = PageGroup.class)
	protected Integer pageNo;
	@Getter @Setter
	@NotNull(groups = PageGroup.class)
	protected Integer pageSize;
	@Getter @Setter
	@NotBlank(groups = PageGroup.class)
	protected String pageSort;
	@Getter @Setter
	@NotBlank(groups = PageGroup.class)
	protected String pageOrder;

	private Page page;

	@SuppressWarnings("unchecked")
	public <D extends PageDto> D count() {
		page = null;
		return (D) this;
	}

	@SuppressWarnings("unchecked")
	public <D extends PageDto> D page() {
		page = new Page();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setPageSort(pageSort);
		page.setPageOrder(pageOrder);
		return (D) this;
	}
}