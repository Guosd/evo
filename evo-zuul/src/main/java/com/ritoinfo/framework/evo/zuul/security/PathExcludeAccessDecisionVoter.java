package com.ritoinfo.framework.evo.zuul.security;

import com.ritoinfo.framework.evo.zuul.config.properties.PathProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-12-28 17:29
 */
@Slf4j
@Component
public class PathExcludeAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {
	private static final List<AntPathRequestMatcher> PATH_LIST = new ArrayList<>();

	@Autowired
	private PathProperties pathProperties;

	public void fetchPathList() {
		log.info("刷新白名单: {}", Arrays.toString(pathProperties.getExcludes()));

		PATH_LIST.clear();

		for (String path : pathProperties.getExcludes()) {
			PATH_LIST.add(new AntPathRequestMatcher(path));
		}
	}

	@Override
	public boolean supports(ConfigAttribute configAttribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	@Override
	public int vote(Authentication authentication, FilterInvocation filterInvocation, Collection<ConfigAttribute> collection) {
		if (PATH_LIST.isEmpty()) {
			fetchPathList();
		}

		for (AntPathRequestMatcher matcher : PATH_LIST) {
			if (matcher.matches(filterInvocation.getRequest())) {
				return ACCESS_GRANTED;
			}
		}
		return ACCESS_DENIED;
	}
}
