package com.ritoinfo.framework.evo.zuul.security.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.ritoinfo.framework.evo.common.jwt.token.JwtToken;
import com.ritoinfo.framework.evo.data.redis.service.RedisService;
import com.ritoinfo.framework.evo.zuul.config.AuthConfig;
import com.ritoinfo.framework.evo.zuul.infa.IAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * User: Kyll
 * Date: 2017-12-17 09:16
 */
@Slf4j
@Configuration
public class AuthFilter extends ZuulFilter {
	@Autowired
	private IAuthService authService;
	@Autowired
	private AuthConfig authConfig;
	@Autowired
	private JwtToken jwtToken;
	@Autowired
	private RedisService redisService;

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

		HttpServletRequest request = requestContext.getRequest();
		String uri = request.getRequestURI();

		boolean exclude = false;
		for (String path : authConfig.getExcludePaths()) {
			if (uri.matches(path.replace("**/*", ".*"))) {
				exclude = true;
				break;
			}
		}

		if (exclude) {
			log.info("忽略验证: " + uri);
		} else {
			String token = jwtToken.get(request);

			if (jwtToken.verify(token) && token.equals(redisService.getString(jwtToken.parse(token).getId()))) {
				if (!authService.verify(uri, token).getData()) {
					log.info("缺少权限: " + uri);

					requestContext.setSendZuulResponse(false);
					requestContext.setResponseStatusCode(403);
				}
			} else {
				log.info("缺少授权: " + uri);

				requestContext.setSendZuulResponse(false);
				requestContext.setResponseStatusCode(401);
			}
		}

		return null;
	}
}
