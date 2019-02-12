package com.github.framework.evo.base.session;

import java.util.concurrent.ConcurrentHashMap;

/**
 * User: Kyll
 * Date: 2017-12-07 14:59
 */
public class SessionContext extends ConcurrentHashMap<String, Object> {
	protected static Class<? extends SessionContext> holderClass = SessionContext.class;

	protected static final ThreadLocal<? extends SessionContext> threadLocal = ThreadLocal.withInitial(() -> {
		try {
			return holderClass.newInstance();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	});

	public SessionContext() {
		super();
	}

	public static void setContextClass(Class<? extends SessionContext> clazz) {
		holderClass = clazz;
	}

	public static SessionContext getCurrentContext() {
		return threadLocal.get();
	}

	public void unset() {
		threadLocal.remove();
	}

	public void set(String key, Object value) {
		if (value == null) {
			remove(key);
		} else {
			put(key, value);
		}
	}
}
