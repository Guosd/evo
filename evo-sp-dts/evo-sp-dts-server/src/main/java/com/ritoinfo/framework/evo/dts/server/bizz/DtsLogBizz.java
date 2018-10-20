package com.ritoinfo.framework.evo.dts.server.bizz;

import com.ritoinfo.framework.evo.dts.server.dao.DtsLogDao;
import com.ritoinfo.framework.evo.dts.server.dto.DtsLogDto;
import com.ritoinfo.framework.evo.dts.server.entity.DtsLog;
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
public class DtsLogBizz extends BaseMapperBizz<DtsLogDao, DtsLog, Long, DtsLogDto> {
}
