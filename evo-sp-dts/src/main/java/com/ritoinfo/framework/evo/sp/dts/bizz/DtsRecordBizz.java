package com.ritoinfo.framework.evo.sp.dts.bizz;

import com.ritoinfo.framework.evo.sp.dts.dao.DtsRecordDao;
import com.ritoinfo.framework.evo.sp.dts.model.DtsRecordDto;
import com.ritoinfo.framework.evo.sp.dts.entity.DtsRecord;
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
