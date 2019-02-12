package com.ritoinfo.framework.evo.sample.rest;

import com.ritoinfo.framework.evo.base.rest.BaseRest;
import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import com.ritoinfo.framework.evo.sample.bizz.CountryBizz;
import com.ritoinfo.framework.evo.sample.condition.CountryCondition;
import com.ritoinfo.framework.evo.sample.dto.CountryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-07-12 14:33
 */
@Slf4j
@RequestMapping("/country")
@RestController
public class CountryRest extends BaseRest<CountryBizz, Long, CountryDto, CountryCondition> {
	@GetMapping("/code/{code}")
	public ServiceResponse<List<Map<String, Object>>> get(@PathVariable String code) {
		return ServiceResponse.ok(bizz.findByCode(code));
	}
}
