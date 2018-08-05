package com.ritoinfo.framework.evo.sp.demo.rest;

import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.demo.bizz.CountryBizz;
import com.ritoinfo.framework.evo.sp.demo.dto.CountryDto;
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
public class CountryRest extends BaseRest<CountryBizz, Long, CountryDto> {
	@GetMapping("/demo")
	public ServiceResponse<List<Map<String, Object>>> get() {
		return ServiceResponse.ok(bizz.selectDemo());
	}
}
