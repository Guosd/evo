package com.ritoinfo.framework.evo.common.uitl;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Kyll
 * Date: 2018-04-19 16:16
 */
public class NetUtil {
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtil.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");// 处理反向代理
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}

		ip = request.getHeader("X-Real-IP");
		if (StringUtil.isNotBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}

		return request.getRemoteAddr();
	}
}
