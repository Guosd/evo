package com.ritoinfo.framework.evo.dts.server.listener;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.dts.common.exception.DtsException;
import com.ritoinfo.framework.evo.dts.common.model.DtsLogMessageDto;
import com.ritoinfo.framework.evo.dts.server.bizz.DtsMessageBizz;
import com.ritoinfo.framework.evo.dts.server.bizz.DtsRecordBizz;
import com.ritoinfo.framework.evo.dts.server.event.DtsLogMessageEvent;
import com.ritoinfo.framework.evo.dts.server.model.DtsMessageDto;
import com.ritoinfo.framework.evo.dts.server.model.DtsRecordDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
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
	private DtsMessageBizz dtsMessageBizz;
	@Autowired
	private DtsRecordBizz dtsRecordBizz;

	/*@Async*/
	@Override
	public synchronized void onApplicationEvent(DtsLogMessageEvent dtsLogMessageEvent) {
		DtsLogMessageDto dtsLogMessageDto = dtsLogMessageEvent.getDtsLogMessageDto();

		List<DtsMessageDto> dtsMessageDtoList = dtsMessageBizz.find(DtsMessageDto.builder().logMessageKey(dtsLogMessageDto.getLogMessageKey()).build());
		if (dtsMessageDtoList.isEmpty()) {
			createDtsMessage(dtsLogMessageDto);
		} else {
			log.info("事务日志已经处理 {}", dtsLogMessageDto.getLogMessageKey());
			return;
		}

		List<DtsRecordDto> dtsRecordDtoList = dtsRecordBizz.find(DtsRecordDto.builder().businessKey(dtsLogMessageDto.getBusinessKey()).build());
		if (dtsRecordDtoList.size() > 1) {
			throw new DtsException(String.format("事务重复 %s", dtsLogMessageDto));
		}

		if (dtsRecordDtoList.size() == 0) {
			DtsRecordDto dtsRecordDto = DtsRecordDto.builder().build();
			dtsRecordDto.setBusinessKey(dtsLogMessageDto.getBusinessKey());
			setStatus(dtsRecordDto, dtsLogMessageDto);

			dtsRecordBizz.create(dtsRecordDto);
		} else {
			DtsRecordDto dtsRecordDto = dtsRecordDtoList.get(0);
			setStatus(dtsRecordDto, dtsLogMessageDto);

			dtsRecordBizz.update(dtsRecordDto);
		}
	}

	private void setStatus(DtsRecordDto dtsRecordDto, DtsLogMessageDto dtsLogMessageDto) {
		if (Const.DTS_ROLE_PRODUCER.equals(dtsLogMessageDto.getRole())) {// 事务生产者
			dtsRecordDto.setProducerStep(dtsLogMessageDto.getStep());
		} else {// 事务消费者
			dtsRecordDto.setConsumerStep(dtsLogMessageDto.getStep());
		}
	}

	private void createDtsMessage(DtsLogMessageDto dtsLogMessageDto) {
		DtsMessageDto dtsMessageDto = new DtsMessageDto();
		BeanUtil.copy(dtsMessageDto, dtsLogMessageDto);

		dtsMessageBizz.create(dtsMessageDto);
	}
}
