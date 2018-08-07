package com.ritoinfo.framework.evo.common;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-02-28 14:33
 */
public class Const {
	public static final int HTTP_STATUS_OK = 200;
	public static final int HTTP_STATUS_BAD_REQUEST = 400;
	public static final int HTTP_STATUS_INTERNAL_SERVER_ERROR = 500;

	public static final String VERIFY_CODE_SIGN_IN = "SIGN_IN";
	public static final String VERIFY_CODE_SIGN_UP = "SIGN_UP";

	public static final String JWT_TOKEN_HEADER = "X-Authorization";

	public static final String SPRING_APPLICATION_NAME_AUTH = "evo-sp-auth";

	public static final String RC_SUCC = "SUCC-0000";
	public static final String RC_FAIL_REQUEST_PARAM = "FAIL-0001";
	public static final String RC_FAIL_UNEXPECT = "FAIL-0002";

	public static final String RC_BASE_SPECIFICATION = "BASE-0001";
	public static final String RC_BASE_EXCEPTION = "BASE-0002";

	public static final String RC_AUTH_LOGIN = "AUTH-0001";
	public static final String RC_AUTH_LOGOUT = "AUTH-0002";
	public static final String RC_AUTH_REFRESH = "AUTH-0003";
	public static final String RC_AUTH_TRY_REFRESH = "AUTH-0004";
	public static final String RC_AUTH_VERIFY = "AUTH-0005";
	public static final String RC_AUTH_DEFAULT_TOKEN = "AUTH-0006";
	public static final String RC_AUTH_M_VERIFY_CODE = "AUTH-0007";
	public static final String RC_AUTH_M_VERIFY_CODE_SIGN_IN = "AUTH-0008";
	public static final String RC_AUTH_M_VERIFY_CODE_SIGN_UP = "AUTH-0009";
	public static final String RC_AUTH_M_LOGIN = "AUTH-0010";
	public static final String RC_AUTH_M_LOGIN_VERIFY_CODE = "AUTH-0012";
	public static final String RC_AUTH_M_VERIFY_CODE_SEND = "AUTH-0013";
	public static final String RC_SYS_MICRO = "SYS-0010";
	public static final String RC_SYS_FUNC = "SYS-0020";
	public static final String RC_SYS_ROLE_FUNC = "SYS-0030";
	public static final String RC_SYS_MENU_MY = "SYS-0040";
	public static final String RC_SYS_USER = "SYS-0050";
	public static final String RC_SYS_USER_EXIST = "SYS-0051";
	public static final String RC_SYS_USER_ROLE = "SYS-0052";
	public static final String RC_SMS = "SMS-0001";
	public static final String RC_ACTIVITI_ID_CREATE = "ACTI-0001";
	public static final String RC_ACTIVITI_ID_USER_DELETE = "ACTI-0002";
	public static final String RC_ACTIVITI_ID_GROUP_DELETE = "ACTI-0003";

	public static String[] NUMBER_CHARS = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	public static String[] WORD_CHARS = new String[] {
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
			"q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "_"
	};

	private static final Map<String, String> RCM_MAP = new HashMap<>();

	static {
		RCM_MAP.put(RC_SUCC, "成功");
		RCM_MAP.put(RC_FAIL_REQUEST_PARAM, "请求参数无效");
		RCM_MAP.put(RC_FAIL_UNEXPECT, "不期望的内部服务异常");

		RCM_MAP.put(RC_BASE_SPECIFICATION, "代码风格不符合框架规范");
		RCM_MAP.put(RC_BASE_EXCEPTION, "未按要求转换业务异常");

		RCM_MAP.put(RC_AUTH_LOGIN, "用户名或密码无效");
		RCM_MAP.put(RC_AUTH_LOGOUT, "用户注销失败");
		RCM_MAP.put(RC_AUTH_REFRESH, "刷新令牌失败");
		RCM_MAP.put(RC_AUTH_TRY_REFRESH, "尝试刷新令牌失败");
		RCM_MAP.put(RC_AUTH_VERIFY, "校验令牌失败");
		RCM_MAP.put(RC_AUTH_DEFAULT_TOKEN, "获取默认用户令牌失败");
		RCM_MAP.put(RC_AUTH_M_VERIFY_CODE, "获取验证码失败");
		RCM_MAP.put(RC_AUTH_M_VERIFY_CODE_SIGN_IN, "获取登录验证码失败");
		RCM_MAP.put(RC_AUTH_M_VERIFY_CODE_SIGN_UP, "获取注册验证码失败");
		RCM_MAP.put(RC_AUTH_M_LOGIN, "登录失败");
		RCM_MAP.put(RC_AUTH_M_LOGIN_VERIFY_CODE, "登录验证码错误");
		RCM_MAP.put(RC_AUTH_M_VERIFY_CODE_SEND, "发送验证码失败");
		RCM_MAP.put(RC_SYS_ROLE_FUNC, "功能数据无效");
		RCM_MAP.put(RC_SYS_MENU_MY, "当前用户无效");
		RCM_MAP.put(RC_SYS_USER, "用户操作失败");
		RCM_MAP.put(RC_SYS_USER_EXIST, "用户已经存在");
		RCM_MAP.put(RC_SYS_USER_ROLE, "角色数据无效");
		RCM_MAP.put(RC_SMS, "短信发送失败");
		RCM_MAP.put(RC_ACTIVITI_ID_CREATE, "用户与用户组建立失败");
		RCM_MAP.put(RC_ACTIVITI_ID_USER_DELETE, "删除用户失败");
		RCM_MAP.put(RC_ACTIVITI_ID_GROUP_DELETE, "删除用户组失败");
	}

	public static String getRcm(String rc) {
		return RCM_MAP.get(rc);
	}
}
