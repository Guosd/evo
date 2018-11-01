package com.ritoinfo.framework.evo.sp.dts.event;

import com.ritoinfo.framework.evo.sp.dts.dto.DtsLogMessageDto;
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
