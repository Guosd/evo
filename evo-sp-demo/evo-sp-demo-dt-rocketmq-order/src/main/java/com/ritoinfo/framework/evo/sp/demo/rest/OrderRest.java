package com.ritoinfo.framework.evo.sp.demo.rest;

import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.demo.bizz.OrderBizz;
import com.ritoinfo.framework.evo.sp.demo.condition.CountryCondition;
import com.ritoinfo.framework.evo.sp.demo.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-07-12 14:33
 */
@Slf4j
@RequestMapping("/order")
@RestController
public class OrderRest extends BaseRest<OrderBizz, Long, OrderDto, CountryCondition> {
	@Autowired
	private OrderBizz orderBizz;

	@PostMapping("/name/{name}")
	public ServiceResponse save(@PathVariable("name") String name) {
		orderBizz.saveLocal(name, null, null);
		return ServiceResponse.ok();
	}
}
