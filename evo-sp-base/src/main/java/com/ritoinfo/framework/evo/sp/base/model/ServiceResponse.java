package com.ritoinfo.framework.evo.sp.base.model;

import com.ritoinfo.framework.evo.common.Const;
import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-02-13 13:57
 */
public class ServiceResponse {
	@Getter @Setter private String code;
	@Getter @Setter private String message;
	@Getter @Setter private Object data;

	public static ServiceResponse ok() {
		return ok(null);
	}

	public static ServiceResponse ok(Object data) {
		return of(Const.SRC_OK, Const.getSrm(Const.SRC_OK), data);
	}

	public static ServiceResponse badRequest() {
		return badRequest(null);
	}

	public static ServiceResponse badRequest(Object data) {
		return of(Const.SRC_BAD_REQUEST, Const.getSrm(Const.SRC_BAD_REQUEST), data);
	}

	public static ServiceResponse of(String code, String message, Object data) {
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setCode(code);
		serviceResponse.setMessage(message);
		serviceResponse.setData(data);
		return serviceResponse;
	}
}
