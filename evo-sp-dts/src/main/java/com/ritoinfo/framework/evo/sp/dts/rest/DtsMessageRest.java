package com.ritoinfo.framework.evo.sp.dts.rest;

import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.dts.bizz.DtsMessageBizz;
import com.ritoinfo.framework.evo.sp.dts.model.DtsMessageCondition;
import com.ritoinfo.framework.evo.sp.dts.model.DtsMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-11-06 09:30
 */
@Slf4j
@RequestMapping("/message")
@RestController
public class DtsMessageRest extends BaseRest<DtsMessageBizz, Long, DtsMessageDto, DtsMessageCondition> {
}
