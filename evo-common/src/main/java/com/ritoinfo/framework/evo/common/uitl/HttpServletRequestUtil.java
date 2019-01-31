package com.ritoinfo.framework.evo.common.uitl;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.model.UserContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * User: Kyll
 * Date: 2019-01-23 09:47
 */
public class HttpServletRequestUtil {
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
}
