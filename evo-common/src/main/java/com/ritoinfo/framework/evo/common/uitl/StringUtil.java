package com.ritoinfo.framework.evo.common.uitl;

import org.apache.commons.lang3.StringUtils;

/**
 * User: Kyll
 * Date: 2018-02-09 14:59
 */
public class StringUtil {
	public static boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}

	public static boolean isNotBlank(String str) {
		return StringUtils.isNotBlank(str);
	}

	public static String toEmpty(Object object) {
		return object == null ? "" : object.toString();
	}
}
