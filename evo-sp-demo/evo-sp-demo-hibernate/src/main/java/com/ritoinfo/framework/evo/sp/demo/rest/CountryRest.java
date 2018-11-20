package com.ritoinfo.framework.evo.sp.demo.rest;

import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.demo.bizz.CountryBizz;
import com.ritoinfo.framework.evo.sp.demo.condition.CountryCondition;
import com.ritoinfo.framework.evo.sp.demo.dto.CountryDto;
import com.ritoinfo.framework.evo.sp.demo.entity.Country;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-07-12 14:33
 */
@Slf4j
@RequestMapping("/country")
@RestController
public class CountryRest extends BaseRest<CountryBizz, Long, CountryDto, CountryCondition> {
	@GetMapping("/code/{code}")
	public ServiceResponse<List<Country>> get(@PathVariable String code) {
		return ServiceResponse.ok(bizz.findByCode(code));
	}
}