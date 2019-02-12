package com.ritoinfo.framework.evo.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.JsonUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.oauth2.util.OAuth2Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * User: Kyll
 * Date: 2019-01-31 09:20
 */
@Slf4j
@Component
public class SecurityContextHolderFilter extends ZuulFilter {
	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		if (authentication != null && !OAuth2Util.isAnonymous(authentication)) {
			RequestContext requestContext = RequestContext.getCurrentContext();
			requestContext.addZuulRequestHeader(Const.HTTP_HEADER_USER_CONTEXT, StringUtil.urlEncodeUTF8(JsonUtil.objectToJson(authentication.getPrincipal())));
		}
		return null;
	}
}
