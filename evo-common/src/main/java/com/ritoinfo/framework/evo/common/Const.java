package com.ritoinfo.framework.evo.common;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-02-28 14:33
 */
public class Const {
	public static final String SRC_OK = "200";
	public static final String SRC_BAD_REQUEST = "400";
	public static final String SRC_INTERNAL_SERVER_ERROR = "500";

	public static final String RC_BASE_SPECIFICATION = "BASE-0001";
	public static final String RC_AUTH_LOGIN = "AUTH-0001";
	public static final String RC_AUTH_LOGOUT = "AUTH-0002";

	public static final String JWT_TOKEN = "token";
	public static final String JWT_REFRESH_TOKEN = "refreshToken";

	private static final Map<String, String> SRM_MAP = new HashMap<>();
	private static final Map<String, String> RCM_MAP = new HashMap<>();

	static {
		SRM_MAP.put(SRC_OK, "OK");
		SRM_MAP.put(SRC_BAD_REQUEST, "Bad Request");
		SRM_MAP.put(SRC_INTERNAL_SERVER_ERROR, "Internal Server Error");

		RCM_MAP.put(RC_BASE_SPECIFICATION, "代码风格不符合框架规范");
		RCM_MAP.put(RC_AUTH_LOGIN, "用户名或密码无效");
		RCM_MAP.put(RC_AUTH_LOGOUT, "用户注销失败");
	}

	public static String getSrm(String src) {
		return SRM_MAP.get(src);
	}

	public static String getRcm(String rc) {
		return RCM_MAP.get(rc);
	}
}
