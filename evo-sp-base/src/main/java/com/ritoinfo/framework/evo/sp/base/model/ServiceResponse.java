package com.ritoinfo.framework.evo.sp.base.model;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import lombok.Data;

/**
 * User: Kyll
 * Date: 2018-02-13 13:57
 */
@Data
public class ServiceResponse<T> {
	private String code;
	private String message;
	private T data;

	public static <T> ServiceResponse<T> ok() {
		return ok(null);
	}

	public static <T> ServiceResponse<T> ok(T data) {
		return of(Const.RC_SUCC, data);
	}

	public static <T> ServiceResponse<T> of(String code) {
		return of(code, null);
	}

	public static <T> ServiceResponse<T> of(String code, T data) {
		return of(code, null, data);
	}

	public static <T> ServiceResponse<T> of(String code, String message, T data) {
		ServiceResponse<T> serviceResponse = new ServiceResponse<>();
		serviceResponse.setCode(code);
		serviceResponse.setMessage(StringUtil.isBlank(message) ? Const.getRcm(code) : message);
		serviceResponse.setData(data);
		return serviceResponse;
	}
}
