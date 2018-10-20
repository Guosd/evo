package com.ritoinfo.framework.evo.dts.server.event;

import com.ritoinfo.framework.evo.dts.common.model.DtsLogMessage;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * User: Kyll
 * Date: 2018-10-19 15:37
 */
public class DtsLogMessageEvent extends ApplicationEvent {
	@Getter
	private DtsLogMessage dtsLogMessage;

	public DtsLogMessageEvent(Object source, DtsLogMessage dtsLogMessage) {
		super(source);
		this.dtsLogMessage = dtsLogMessage;
	}
}
