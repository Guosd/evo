package com.ritoinfo.framework.evo.sp.sys.bizz;

import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.MicroCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.MicroDao;
import com.ritoinfo.framework.evo.sp.sys.dto.MicroDto;
import com.ritoinfo.framework.evo.sp.sys.entity.Micro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2018-04-23 20:03
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class MicroBizz extends BaseBizz<MicroDao, Micro, Long, MicroCondition, MicroDto> {
}