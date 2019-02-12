package com.github.framework.evo.base.interceptor;

import com.github.framework.evo.common.uitl.CollectionUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2017-12-11 15:10
 */
public class OpenFeignRequestInterceptor implements RequestInterceptor {
	@Override
	public void apply(RequestTemplate requestTemplate) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		if (attributes != null) {
			HttpServletRequest request = attributes.getRequest();
			Enumeration<String> headerNames = request.getHeaderNames();
			if (headerNames != null) {
				Map<String, Collection<String>> map = requestTemplate.headers();

				while (headerNames.hasMoreElements()) {
					String name = headerNames.nextElement();

					if (!CollectionUtil.hasIgnoreCaseKey(map, name)) {
						requestTemplate.header(name, request.getHeader(name));
					}
				}
			}
		}
	}
}
