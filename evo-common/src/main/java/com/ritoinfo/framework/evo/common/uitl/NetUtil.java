package com.ritoinfo.framework.evo.common.uitl;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Kyll
 * Date: 2018-04-19 16:16
 */
@Slf4j
public class NetUtil {
	public static String extractRemoteAddr(HttpServletRequest request) {
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

	public static boolean isRequestFromBrowser(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		log.debug("Request User-Agent: {}", userAgent);

		if (StringUtil.isBlank(userAgent)) {
			return false;
		}

		userAgent = userAgent.toLowerCase();
		boolean result = (userAgent.contains("chrome") && userAgent.contains("safari"))
				|| userAgent.contains("firefox")
				|| (userAgent.contains("safari") && !userAgent.contains("chrome"))
				|| userAgent.contains("edge")
				|| (userAgent.contains("compatible") && userAgent.contains("msie"))
				|| userAgent.contains("opera")
				|| userAgent.contains("trident");

		log.debug("Request From Browser: {}", result);

		return result;
	}
}
