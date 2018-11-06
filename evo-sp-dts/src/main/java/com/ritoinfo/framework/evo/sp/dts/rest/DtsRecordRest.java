package com.ritoinfo.framework.evo.sp.dts.rest;

import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.dts.bizz.DtsRecordBizz;
import com.ritoinfo.framework.evo.sp.dts.model.DtsRecordCondition;
import com.ritoinfo.framework.evo.sp.dts.model.DtsRecordDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-11-06 09:36
 */
@Slf4j
@RequestMapping("/record")
@RestController
public class DtsRecordRest extends BaseRest<DtsRecordBizz, Long, DtsRecordDto, DtsRecordCondition> {
}
