package com.ritoinfo.framework.evo.sp.auth.authorization.extend.filter;

import com.ritoinfo.framework.evo.common.uitl.JsonUtil;
import org.apache.catalina.connector.RequestFacade;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.savedrequest.Enumerator;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * User: Kyll
 * Date: 2018-12-24 10:25
 */
@Component
@Order(Integer.MIN_VALUE)
public class RequestBodyToUrlQueryStringFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		if (Objects.equals(servletRequest.getContentType(), "application/json") && servletRequest instanceof RequestFacade && Objects.equals(((RequestFacade) servletRequest).getServletPath(), "/oauth/token")) {
			InputStream is = servletRequest.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead;
			byte[] data = new byte[16384];// 16k
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();

			HashMap<String, String> jsonMap = buffer.size() > 0 ? JsonUtil.jsonToObject(buffer.toByteArray(), HashMap.class) : new HashMap<>();
			HashMap<String, String[]> paramMap = new HashMap<>();
			for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
				paramMap.put(entry.getKey(), new String[]{entry.getValue()});
			}

			paramMap.put("_method", new String[]{((RequestFacade) servletRequest).getMethod()});

			filterChain.doFilter(new RequestBodyToUrlQueryStringServletRequestWrapper(((HttpServletRequest) servletRequest), paramMap), servletResponse);
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
	}

	private static class RequestBodyToUrlQueryStringServletRequestWrapper extends HttpServletRequestWrapper {
		private final Map<String, String[]> paramMap;

		public RequestBodyToUrlQueryStringServletRequestWrapper(HttpServletRequest request, Map<String, String[]> paramMap) {
			super(request);
			this.paramMap = paramMap;
		}

		@Override
		public String getParameter(String name) {
			if (paramMap.containsKey(name)) {
				return paramMap.get(name)[0];
			}
			return "";
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			return this.paramMap;
		}

		@Override
		public Enumeration<String> getParameterNames() {
			return new Enumerator<>(paramMap.keySet());
		}

		@Override
		public String[] getParameterValues(String name) {
			return paramMap.get(name);
		}
	}
}
