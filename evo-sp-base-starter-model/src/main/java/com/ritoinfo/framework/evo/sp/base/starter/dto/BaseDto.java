package com.ritoinfo.framework.evo.sp.base.starter.dto;

import com.ritoinfo.framework.evo.sp.base.starter.validate.group.UpdateGroup;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-03-05 22:45
 */
public abstract class BaseDto<PK> implements Serializable {
	@Getter @Setter
	@NotNull(groups = UpdateGroup.class)
	protected PK id;
}
