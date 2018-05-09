package com.ritoinfo.framework.evo.sp.datadict.rest;

import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.datadict.bizz.DataDictBizz;
import com.ritoinfo.framework.evo.sp.datadict.condition.DataDictCondition;
import com.ritoinfo.framework.evo.sp.datadict.dao.DataDictDao;
import com.ritoinfo.framework.evo.sp.datadict.dto.DataDictDto;
import com.ritoinfo.framework.evo.sp.datadict.entity.DataDict;
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
public class DataDictRest extends BaseRest<DataDictBizz, DataDictDao, DataDict, Long, DataDictCondition, DataDictDto> {
	@GetMapping("/code/{code}")
	public ServiceResponse<List<DataDictDto>> code(@PathVariable String code) {
		return ServiceResponse.ok(bizz.findByCode(code));
	}

	@GetMapping("/code/{code}/key/{key}")
	public ServiceResponse<DataDictDto> codeAndKey(@PathVariable String code, @PathVariable String key) {
		return ServiceResponse.ok(bizz.getByCodeAndKey(DataDictCondition.builder().code(code).key(key).build()));
	}

	@DeleteMapping("/id/{id}/code/{code}")
	public ServiceResponse delete(@PathVariable Long id, @PathVariable String code) {
		bizz.delete(id, code);
		return ServiceResponse.ok();
	}
}
