package com.ritoinfo.framework.evo.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;

/**
 * User: Kyll
 * Date: 2017-12-17 09:16
 */
@Slf4j
@Configuration
public class AuthFilter extends ZuulFilter {
	/*@Autowired
	private AuthCommonApi authCommonApi;
	@Autowired
	private AuthConfig authConfig;
	@Autowired
	private JwtToken jwtToken;*/

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
	public Object run() {
		RequestContext requestContext = RequestContext.getCurrentContext();
		System.out.println(requestContext);

		return null;
	}
}
