package com.ritoinfo.framework.evo.zuul.resource;

import com.ritoinfo.framework.evo.sp.auth.config.properties.AuthProperties;
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
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: Kyll
 * Date: 2018-12-28 17:29
 */
@Slf4j
@Component
public class PathExcludeAccessDecisionVoter implements AccessDecisionVoter<FilterInvocation> {
	private static final List<AntPathRequestMatcher> PATH_LIST = new ArrayList<>();
	private static final ReadWriteLock lock = new ReentrantReadWriteLock();

	@Autowired
	private AuthProperties authProperties;

	public void fetchPathList() {
		String[] excludes = authProperties.getPath().getExcludes();
		log.info("刷新白名单 {}", Arrays.toString(excludes));

		lock.writeLock().lock();
		try {
			PATH_LIST.clear();

			for (String path : excludes) {
				PATH_LIST.add(new AntPathRequestMatcher(path));
			}
		} finally {
			lock.writeLock().unlock();
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

		lock.readLock().lock();
		try {
			for (AntPathRequestMatcher matcher : PATH_LIST) {
				if (matcher.matches(filterInvocation.getRequest())) {
					return ACCESS_GRANTED;
				}
			}
		} finally {
			lock.readLock().unlock();
		}

		return ACCESS_DENIED;
	}
}
