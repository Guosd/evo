package com.ritoinfo.framework.evo.sp.base.starter.dto;

import com.ritoinfo.framework.evo.sp.base.starter.validate.group.CreateGroup;
import com.ritoinfo.framework.evo.sp.base.starter.validate.group.UpdateGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * User: Kyll
 * Date: 2018-03-05 22:45
 */
public abstract class BaseDto<PK> {
	@Getter @Setter
	@Null(groups = CreateGroup.class)
	@NotNull(groups = UpdateGroup.class)
	protected PK id;
}
