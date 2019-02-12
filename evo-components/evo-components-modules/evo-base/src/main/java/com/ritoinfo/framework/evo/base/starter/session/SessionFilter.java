package com.ritoinfo.framework.evo.base.starter.session;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.config.UserContextConfig;
import com.ritoinfo.framework.evo.common.uitl.HttpServletUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import lombok.extern.slf4j.Slf4j;
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
	private UserContextConfig userContextConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		if (userContextConfig.isEnabled() && !StringUtil.antPathMatch(Const.URI_ANT_ACTUATOR, request.getRequestURI())) {
			SessionHolder.getCurrentHolder().setUserContext(HttpServletUtil.extractUserContext(request));
			log.info("URI {}, Thread Name {}, UserContext {}", request.getRequestURI(), Thread.currentThread().getName(), SessionHolder.getUserContext());
		}

		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
