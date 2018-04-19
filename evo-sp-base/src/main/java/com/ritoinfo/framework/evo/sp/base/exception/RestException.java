package com.ritoinfo.framework.evo.sp.base.exception;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.exception.BaseException;
import lombok.Getter;
import lombok.Setter;

/**
 * User: Kyll
 * Date: 2018-03-05 21:59
 *
 * 调用异常
 * 业务异常不要直接抛出，必须转换为此异常的子类
 */
public class RestException extends BaseException {
	@Getter @Setter protected String code;

	public RestException(String code) {
		this(code, null);
	}

	public RestException(String code, Throwable e) {
		this(code, null, e);
	}

	public RestException(String code, Object data, Throwable e) {
		super(Const.getRcm(code), data, e);

		this.code = code;
	}
}
