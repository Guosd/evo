package com.ritoinfo.framework.evo.sp.demo.rest;

import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.demo.bizz.AccountBizz;
import com.ritoinfo.framework.evo.sp.demo.condition.CountryCondition;
import com.ritoinfo.framework.evo.sp.demo.dto.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-07-12 14:33
 */
@Slf4j
@RequestMapping("/account")
@RestController
public class AccountRest extends BaseRest<AccountBizz, Long, AccountDto, CountryCondition> {
}
