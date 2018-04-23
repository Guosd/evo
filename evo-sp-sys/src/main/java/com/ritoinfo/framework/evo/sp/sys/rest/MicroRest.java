package com.ritoinfo.framework.evo.sp.sys.rest;

import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.sys.bizz.MicroBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.MicroCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.MicroDao;
import com.ritoinfo.framework.evo.sp.sys.dto.MicroDto;
import com.ritoinfo.framework.evo.sp.sys.entity.Micro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-04-23 20:04
 */
@Slf4j
@RequestMapping("micro")
@RestController
public class MicroRest extends BaseRest<MicroBizz, MicroDao, Micro, Long, MicroCondition, MicroDto> {
}
