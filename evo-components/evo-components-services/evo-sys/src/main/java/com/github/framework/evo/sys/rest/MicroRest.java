package com.github.framework.evo.sys.rest;

import com.github.framework.evo.base.rest.BaseRest;
import com.github.framework.evo.sys.bizz.MicroBizz;
import com.github.framework.evo.sys.condition.MicroCondition;
import com.github.framework.evo.sys.dto.MicroDto;
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
