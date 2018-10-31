package com.ritoinfo.framework.evo.dts.server.bizz;

import com.ritoinfo.framework.evo.dts.server.model.DtsMessageDto;
import com.ritoinfo.framework.evo.dts.server.dao.DtsMessageDao;
import com.ritoinfo.framework.evo.dts.server.entity.DtsMessage;
import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseMapperBizz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2018-10-18 17:00
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class DtsMessageBizz extends BaseMapperBizz<DtsMessageDao, DtsMessage, Long, DtsMessageDto> {
}
