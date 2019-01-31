package com.ritoinfo.framework.evo.sp.base.starter.session;

import com.ritoinfo.framework.evo.common.model.UserContext;

/**
 * User: Kyll
 * Date: 2018-01-29 20:46
 */
public class SessionHolder extends SessionContext {
	private static final String USER_CONTEXT_KEY = "userContext";

	static {
		SessionContext.setContextClass(SessionHolder.class);
	}

	public SessionHolder() {
		super();
	}

	public static SessionHolder getCurrentHolder() {
		return (SessionHolder) SessionContext.threadLocal.get();
	}

	public void setUserContext(UserContext userContext) {
		set(USER_CONTEXT_KEY, userContext);
	}

	public static UserContext getUserContext() {
		return (UserContext) getCurrentHolder().get(USER_CONTEXT_KEY);
	}
}
