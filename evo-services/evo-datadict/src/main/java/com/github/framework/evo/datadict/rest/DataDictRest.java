package com.github.framework.evo.datadict.rest;

import com.github.framework.evo.base.rest.BaseRest;
import com.github.framework.evo.datadict.bizz.DataDictBizz;
import com.github.framework.evo.datadict.condition.DataDictCondition;
import com.github.framework.evo.datadict.dto.DataDictDto;
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
	public List<DataDictDto> findByCode(@PathVariable String code) {
		return bizz.findByCode(code);
	}

	@GetMapping("/code/{code}/key/{key}")
	public DataDictDto findByCodeAndKey(@PathVariable String code, @PathVariable String key) {
		return bizz.getByCodeAndKey(DataDictCondition.builder().code(code).key(key).build());
	}

	@DeleteMapping("/id/{id}/code/{code}")
	public void delete(@PathVariable Long id, @PathVariable String code) {
		bizz.delete(id, code);
	}
}
