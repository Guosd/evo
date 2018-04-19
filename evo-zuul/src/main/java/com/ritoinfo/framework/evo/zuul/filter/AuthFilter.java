package com.ritoinfo.framework.evo.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.jwt.model.VerifyResult;
import com.ritoinfo.framework.evo.common.jwt.token.JwtToken;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.sp.auth.api.AuthApi;
import com.ritoinfo.framework.evo.sp.auth.dto.VerifyDto;
import com.ritoinfo.framework.evo.zuul.config.AuthConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: Kyll
 * Date: 2017-12-17 09:16
 */
@Slf4j
@Configuration
public class AuthFilter extends ZuulFilter {
	@Autowired
	private AuthApi authApi;
	@Autowired
	private AuthConfig authConfig;
	@Autowired
	private JwtToken jwtToken;

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
			String token = jwtToken.getToken(request);

			if (StringUtil.isBlank(token)) {
				log.info("缺少授权: " + uri);

				requestContext.setSendZuulResponse(false);
				requestContext.setResponseStatusCode(401);
			} else {
				VerifyResult verifyResult = jwtToken.verify(token);

				if (VerifyResult.SUCCESS == verifyResult || VerifyResult.EXPIRED == verifyResult) {
					if (VerifyResult.EXPIRED == verifyResult) {
						String newToken = authApi.tryRefresh(token).getData();
						if (StringUtil.isBlank(newToken)) {
							newToken = authApi.refresh(token).getData();
						}

						if (StringUtil.isBlank(newToken)) {
							log.info("缺少授权: " + uri);

							requestContext.setSendZuulResponse(false);
							requestContext.setResponseStatusCode(401);
						} else {
							requestContext.addZuulRequestHeader(Const.JWT_TOKEN_HEADER, newToken);

							HttpServletResponse response = requestContext.getResponse();
							response.setHeader("Cache-Control", "no-store");
							response.setHeader("Access-Control-Expose-Headers", Const.JWT_TOKEN_HEADER);
							response.setHeader(Const.JWT_TOKEN_HEADER, newToken);

							token = newToken;
						}
					}

					if (StringUtil.isNotBlank(token) && !authApi.verify(VerifyDto.builder().uri(uri).token(token).build()).getData()) {
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
		}

		return null;
	}
}
