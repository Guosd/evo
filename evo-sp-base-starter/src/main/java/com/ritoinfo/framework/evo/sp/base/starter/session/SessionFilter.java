package com.ritoinfo.framework.evo.sp.base.starter.session;

import com.ritoinfo.framework.evo.common.jwt.model.UserContext;
import com.ritoinfo.framework.evo.common.jwt.token.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: Kyll
 * Date: 2017-12-07 15:14
 */
@Slf4j
@Order(1)
@WebFilter(filterName = "SessionFilter", urlPatterns = "/*")
@Component
public class SessionFilter implements Filter {
	@Autowired
	private JwtToken jwtToken;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		String uri = request.getRequestURI();

		String token = jwtToken.get(request);
		if (StringUtils.isNotBlank(token)) {
			UserContext userContext = null;
			try {
				userContext = jwtToken.parse(token);
			} catch (Exception e) {
				log.warn("令牌解析失败: " + uri, e);
			}

			if (userContext != null) {
				SessionContext.getCurrentContext().unset();
				SessionContext.setContextClass(SessionHolder.class);
				SessionHolder.getCurrentHolder().setUserContext(userContext);
			}
		}

		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
