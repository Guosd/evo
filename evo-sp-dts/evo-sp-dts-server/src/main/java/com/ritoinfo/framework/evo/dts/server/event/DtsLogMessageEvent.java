package com.ritoinfo.framework.evo.dts.server.event;

import com.ritoinfo.framework.evo.dts.common.model.DtsLogMessageDto;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * User: Kyll
 * Date: 2018-10-19 15:37
 */
public class DtsLogMessageEvent extends ApplicationEvent {
	@Getter
	private DtsLogMessageDto dtsLogMessageDto;

	public DtsLogMessageEvent(Object source, DtsLogMessageDto dtsLogMessageDto) {
		super(source);
		this.dtsLogMessageDto = dtsLogMessageDto;
	}
}
