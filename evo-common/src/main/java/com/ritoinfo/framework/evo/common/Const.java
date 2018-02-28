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

	private static final Map<String, String> SRM_MAP = new HashMap<>();

	static {
		SRM_MAP.put(SRC_OK, "OK");
		SRM_MAP.put(SRC_BAD_REQUEST, "Bad Request");
		SRM_MAP.put(SRC_INTERNAL_SERVER_ERROR, "Internal Server Error");
	}

	public static String getSrm(String src) {
		return SRM_MAP.get(src);
	}
}
