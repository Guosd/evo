package com.ritoinfo.framework.evo.sys.rest;

import com.ritoinfo.framework.evo.base.rest.BaseRest;
import com.ritoinfo.framework.evo.sys.bizz.MicroBizz;
import com.ritoinfo.framework.evo.sys.condition.MicroCondition;
import com.ritoinfo.framework.evo.sys.dto.MicroDto;
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
public class MicroRest extends BaseRest<MicroBizz, Long, MicroDto, MicroCondition> {
}
