package com.ritoinfo.framework.evo.sp.dts.bizz;

import com.ritoinfo.framework.evo.sp.dts.model.DtsMessageDto;
import com.ritoinfo.framework.evo.sp.dts.dao.DtsMessageDao;
import com.ritoinfo.framework.evo.sp.dts.entity.DtsMessage;
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
