package com.ritoinfo.framework.evo.common.uitl;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.model.UserContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * User: Kyll
 * Date: 2019-01-23 09:47
 */
public class HttpServletUtil {
	public static String extractAccessToken(HttpServletRequest request) {
		String token = request.getHeader(Const.HTTP_HEADER_TOKEN);

		if (StringUtil.isBlank(token)) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals(Const.HTTP_HEADER_TOKEN)) {
						token = cookie.getValue();
						break;
					}
				}
			}
		}

		if (StringUtil.isBlank(token)) {
			token = request.getParameter(Const.HTTP_HEADER_TOKEN);
		}

		return StringUtil.isNotBlank(token) && token.startsWith(Const.JWT_TOKEN_PREFIX) ? token.substring(Const.JWT_TOKEN_PREFIX.length()) : null;
	}

	public static UserContext extractUserContext(HttpServletRequest request) {
		String json = request.getHeader(Const.HTTP_HEADER_USER_CONTEXT);
		return StringUtil.isBlank(json) ? null : JsonUtil.jsonToObject(StringUtil.urlDecodeUTF8(json), UserContext.class);
	}

	public static String extractRemoteAddr(HttpServletRequest request) {
		String ip = request.getHeader(Const.HTTP_HEADER_X_FORWARDED_FOR);
		if (StringUtil.isNotBlank(ip) && !Const.PN_UNKNOWN.equalsIgnoreCase(ip)) {
			int index = ip.indexOf(',');// 处理反向代理
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}

		ip = request.getHeader(Const.HTTP_HEADER_X_REAL_IP);
		if (StringUtil.isNotBlank(ip) && !Const.PN_UNKNOWN.equalsIgnoreCase(ip)) {
			return ip;
		}

		return request.getRemoteAddr();
	}

	public static boolean isRequestFromBrowser(HttpServletRequest request) {
		String userAgent = request.getHeader(Const.HTTP_HEADER_USER_AGENT);

		if (StringUtil.isBlank(userAgent)) {
			return false;
		}

		userAgent = userAgent.toLowerCase();
		return (userAgent.contains("chrome") && userAgent.contains("safari"))
				|| userAgent.contains("firefox")
				|| (userAgent.contains("safari") && !userAgent.contains("chrome"))
				|| userAgent.contains("edge")
				|| (userAgent.contains("compatible") && userAgent.contains("msie"))
				|| userAgent.contains("opera")
				|| userAgent.contains("trident");
	}
}
