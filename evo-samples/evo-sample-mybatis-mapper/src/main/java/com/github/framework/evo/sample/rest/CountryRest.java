package com.github.framework.evo.sample.rest;

import com.github.framework.evo.base.rest.BaseRest;
import com.github.framework.evo.common.model.ServiceResponse;
import com.github.framework.evo.sample.bizz.CountryBizz;
import com.github.framework.evo.sample.condition.CountryCondition;
import com.github.framework.evo.sample.dto.CountryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping("/demo")
	public ServiceResponse<List<Map<String, Object>>> get() {
		return ServiceResponse.ok(bizz.selectDemo());
	}
}
