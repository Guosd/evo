package com.ritoinfo.framework.evo.datadict.rest;

import com.ritoinfo.framework.evo.base.rest.BaseRest;
import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import com.ritoinfo.framework.evo.datadict.bizz.DataDictBizz;
import com.ritoinfo.framework.evo.datadict.condition.DataDictCondition;
import com.ritoinfo.framework.evo.datadict.dto.DataDictDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-13 09:57
 */
@Slf4j
@RestController
public class DataDictRest extends BaseRest<DataDictBizz, Long, DataDictDto, DataDictCondition> {
	@GetMapping("/code/{code}")
	public ServiceResponse<List<DataDictDto>> findByCode(@PathVariable String code) {
		return ServiceResponse.ok(bizz.findByCode(code));
	}

	@GetMapping("/code/{code}/key/{key}")
	public ServiceResponse<DataDictDto> findByCodeAndKey(@PathVariable String code, @PathVariable String key) {
		return ServiceResponse.ok(bizz.getByCodeAndKey(DataDictCondition.builder().code(code).key(key).build()));
	}

	@DeleteMapping("/id/{id}/code/{code}")
	public ServiceResponse delete(@PathVariable Long id, @PathVariable String code) {
		bizz.delete(id, code);
		return ServiceResponse.ok();
	}
}
