package com.ritoinfo.framework.evo.dts.server.listener;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.dts.common.exception.DtsException;
import com.ritoinfo.framework.evo.dts.common.model.DtsLogMessage;
import com.ritoinfo.framework.evo.dts.server.bizz.DtsLogBizz;
import com.ritoinfo.framework.evo.dts.server.dto.DtsLogDto;
import com.ritoinfo.framework.evo.dts.server.event.DtsLogMessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-10-18 16:51
 */
@Slf4j
@Component
public class DtsLogMessageListener implements ApplicationListener<DtsLogMessageEvent> {
	@Autowired
	private DtsLogBizz dtsLogBizz;

	@Async
	@Override
	public void onApplicationEvent(DtsLogMessageEvent dtsLogMessageEvent) {
		DtsLogMessage dtsLogMessage = dtsLogMessageEvent.getDtsLogMessage();

		List<DtsLogDto> dtsLogDtoList = dtsLogBizz.find(DtsLogDto.builder().businessKey(dtsLogMessage.getBusinessKey()).build());
		if (dtsLogDtoList.size() > 1) {
			throw new DtsException(String.format("事务重复 %s", dtsLogMessage));
		}

		if (dtsLogDtoList.size() == 0) {
			DtsLogDto dtsLogDto = DtsLogDto.builder().build();
			BeanUtil.copy(dtsLogDto, dtsLogMessage);

			setStatus(dtsLogDto, dtsLogMessage);

			dtsLogBizz.create(dtsLogDto);
		} else {
			DtsLogDto dtsLogDto = dtsLogDtoList.get(0);
			setStatus(dtsLogDto, dtsLogMessage);

			dtsLogBizz.update(dtsLogDto);
		}
	}

	private void setStatus(DtsLogDto dtsLogDto, DtsLogMessage dtsLogMessage) {
		if (Const.DTS_ROLE_PRODUCER.equals(dtsLogMessage.getRole())) {// 事务生产者
			dtsLogDto.setSourceStatus(dtsLogMessage.getStep());
		} else {// 事务消费者
			dtsLogDto.setTargetStatus(dtsLogMessage.getStep());
		}
	}
}
