package com.ritoinfo.framework.evo.sp.base.infa.model;

import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-02-13 13:57
 */
public class ServiceResponse<T> {
	@Getter @Setter private String code;
	@Getter @Setter private String message;
	@Getter @Setter private T data;
}
