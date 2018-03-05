package com.ritoinfo.framework.evo.sp.base.model;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
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
		return ok(null, null);
	}

	public static ServiceResponse ok(String message) {
		return ok(message, null);
	}

	public static ServiceResponse ok(Object data) {
		return ok(null, data);
	}

	public static ServiceResponse ok(String message, Object data) {
		return of(Const.SRC_OK, StringUtil.isBlank(message) ? Const.getSrm(Const.SRC_OK) : message, data);
	}

	public static ServiceResponse badRequest() {
		return badRequest(null, null);
	}

	public static ServiceResponse badRequest(String message) {
		return badRequest(message, null);
	}

	public static ServiceResponse badRequest(Object data) {
		return badRequest(null, data);
	}

	public static ServiceResponse badRequest(String message, Object data) {
		return of(Const.SRC_BAD_REQUEST, StringUtil.isBlank(message) ? Const.getSrm(Const.SRC_BAD_REQUEST) : message, data);
	}

	public static ServiceResponse internalServerError() {
		return internalServerError(null, null);
	}

	public static ServiceResponse internalServerError(String message) {
		return internalServerError(message, null);
	}

	public static ServiceResponse internalServerError(Object data) {
		return internalServerError(null, data);
	}

	public static ServiceResponse internalServerError(String message, Object data) {
		return of(Const.SRC_INTERNAL_SERVER_ERROR, StringUtil.isBlank(message) ? Const.getSrm(Const.SRC_INTERNAL_SERVER_ERROR) : message, data);
	}

	public static ServiceResponse of(String code, String message, Object data) {
		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setCode(code);
		serviceResponse.setMessage(message);
		serviceResponse.setData(data);
		return serviceResponse;
	}
}
