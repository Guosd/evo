package com.ritoinfo.framework.evo.dts.server.bizz;

import com.ritoinfo.framework.evo.dts.server.dao.DtsRecordDao;
import com.ritoinfo.framework.evo.dts.server.model.DtsRecordDto;
import com.ritoinfo.framework.evo.dts.server.entity.DtsRecord;
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
public class DtsRecordBizz extends BaseMapperBizz<DtsRecordDao, DtsRecord, Long, DtsRecordDto> {
}
