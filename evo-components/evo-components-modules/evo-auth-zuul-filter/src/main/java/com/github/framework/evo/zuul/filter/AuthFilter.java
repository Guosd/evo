package com.github.framework.evo.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.github.framework.evo.auth.api.IamApi;
import com.github.framework.evo.auth.api.TokenApi;
import com.github.framework.evo.auth.config.PathConfig;
import com.github.framework.evo.auth.model.RbacDto;
import com.github.framework.evo.common.Const;
import com.github.framework.evo.common.model.ServiceResponse;
import com.github.framework.evo.common.model.UserContext;
import com.github.framework.evo.common.uitl.HttpServletUtil;
import com.github.framework.evo.common.uitl.JsonUtil;
import com.github.framework.evo.common.uitl.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Kyll
 * Date: 2017-12-17 09:16
 */
@Slf4j
@Component
public class AuthFilter extends ZuulFilter {
	private final PathConfig pathConfig;
	private final TokenApi tokenApi;
	private final IamApi iamApi;

	@Autowired
	public AuthFilter(PathConfig pathConfig, TokenApi tokenApi, IamApi iamApi) {
		this.pathConfig = pathConfig;
		this.tokenApi = tokenApi;
		this.iamApi = iamApi;
	}

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
		for (String path : pathConfig.getExcludes()) {
			if (StringUtil.antPathMatch(path.trim(), uri)) {
				exclude = true;
				break;
			}
		}

		if (exclude) {
			log.info("忽略验证 {}", uri);
		} else {
			String accessToken = HttpServletUtil.extractAccessToken(request);

			if (StringUtil.isBlank(accessToken)) {
				log.info("缺少 access token {}", uri);
				process401(requestContext);
			} else {
				ServiceResponse<UserContext> serviceResponse = tokenApi.check(accessToken);
				if (Const.RC_SUCC.equals(serviceResponse.getCode())) {// 如果检查 token 成功
					UserContext userContext = serviceResponse.getData();
					if (userContext == null) {
						log.info("缺少 UserContext {}", uri);
						process401(requestContext);
					} else {
						String newAccessToken = userContext.getAccessToken();
						if (StringUtil.isNotBlank(newAccessToken)) {// 如果刷新 token 成功
							HttpServletResponse response = requestContext.getResponse();
							response.setHeader("Cache-Control", "no-store");
							response.setHeader("Access-Control-Expose-Headers", Const.HTTP_HEADER_TOKEN);
							response.setHeader(Const.HTTP_HEADER_TOKEN, newAccessToken);

							userContext.setAccessToken(null);
						}

						if (StringUtil.isNotBlank(accessToken) && !iamApi.check(RbacDto.builder().userId(userContext.getId()).uri(uri).method(request.getMethod()).build()).getData()) {
							log.info("缺少权限 {}", uri);

							requestContext.setSendZuulResponse(false);
							requestContext.setResponseStatusCode(403);
						}

						requestContext.addZuulRequestHeader(Const.HTTP_HEADER_USER_CONTEXT, StringUtil.urlEncodeUTF8(JsonUtil.objectToJson(userContext)));
					}
				} else {
					log.info("access token 失效 {}", uri);
					process401(requestContext);
				}
			}
		}

		return null;
	}

	private void process401(RequestContext requestContext) {
		requestContext.setSendZuulResponse(false);

		if (HttpServletUtil.isRequestFromBrowser(requestContext.getRequest())) {
			requestContext.setResponseStatusCode(HttpStatus.SC_TEMPORARY_REDIRECT);
			requestContext.put(FilterConstants.FORWARD_TO_KEY, pathConfig.getLogin());
			try {
				requestContext.getResponse().sendRedirect(pathConfig.getLogin());
			} catch (IOException e) {
				log.error("重定向到登录页面失败", e);
			}
		} else {
			requestContext.setResponseStatusCode(401);
		}
	}
}
