package com.ritoinfo.framework.evo.sp.base.model;

import com.ritoinfo.framework.evo.common.Const;
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

	/**
	 * 适用于无返回值
	 * @param <T> 数据泛型
	 * @return ServiceResponse
	 */
	public static <T> ServiceResponse<T> ok() {
		return of(Const.RC_SUCC, Const.getRcm(Const.RC_SUCC), null);
	}

	/**
	 * 适用于有返回值
	 * @param data 数据
	 * @param <T> 数据泛型
	 * @return ServiceResponse
	 */
	public static <T> ServiceResponse<T> ok(T data) {
		return of(Const.RC_SUCC, Const.getRcm(Const.RC_SUCC), data);
	}

	public static <T> ServiceResponse<T> of(String code) {
		return of(code, null, null);
	}

	public static <T> ServiceResponse<T> of(String code, String message) {
		return of(code, message, null);
	}

	public static <T> ServiceResponse<T> of(String code, String message, T data) {
		ServiceResponse<T> serviceResponse = new ServiceResponse<>();
		serviceResponse.setCode(code);
		serviceResponse.setMessage(message);
		serviceResponse.setData(data);
		return serviceResponse;
	}
}
