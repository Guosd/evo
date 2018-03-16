package com.ritoinfo.framework.evo.common.model;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
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

	public static <T> ServiceResponse<T> ok() {
		return ok(null, null);
	}

	public static <T> ServiceResponse<T> ok(String message) {
		return ok(message, null);
	}

	public static <T> ServiceResponse<T> ok(T data) {
		return ok(null, data);
	}

	public static <T> ServiceResponse<T> ok(String message, T data) {
		return of(Const.SRC_OK, StringUtil.isBlank(message) ? Const.getSrm(Const.SRC_OK) : message, data);
	}

	public static <T> ServiceResponse<T> badRequest() {
		return badRequest(null, null);
	}

	public static <T> ServiceResponse<T> badRequest(String message) {
		return badRequest(message, null);
	}

	public static <T> ServiceResponse<T> badRequest(T data) {
		return badRequest(null, data);
	}

	public static <T> ServiceResponse<T> badRequest(String message, T data) {
		return of(Const.SRC_BAD_REQUEST, StringUtil.isBlank(message) ? Const.getSrm(Const.SRC_BAD_REQUEST) : message, data);
	}

	public static <T> ServiceResponse<T> internalServerError() {
		return internalServerError(null, null);
	}

	public static <T> ServiceResponse<T> internalServerError(String message) {
		return internalServerError(message, null);
	}

	public static <T> ServiceResponse<T> internalServerError(T data) {
		return internalServerError(null, data);
	}

	public static <T> ServiceResponse<T> internalServerError(String message, T data) {
		return of(Const.SRC_INTERNAL_SERVER_ERROR, StringUtil.isBlank(message) ? Const.getSrm(Const.SRC_INTERNAL_SERVER_ERROR) : message, data);
	}

	public static <T> ServiceResponse<T> of(String code, String message, T data) {
		ServiceResponse<T> serviceResponse = new ServiceResponse<>();
		serviceResponse.setCode(code);
		serviceResponse.setMessage(message);
		serviceResponse.setData(data);
		return serviceResponse;
	}
}
