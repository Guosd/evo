package com.github.framework.evo.datadict.api;

import com.github.framework.evo.datadict.dto.DataDictDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-04-13 09:57
 */
@FeignClient(value = "evo-datadict")
public interface DataDictApi {
	@GetMapping("/code/{code}")
	List<DataDictDto> findByCode(@PathVariable("code") String code);

	@GetMapping("/code/{code}/key/{key}")
	DataDictDto findByCodeAndKey(@PathVariable("code") String code, @PathVariable("key") String key);

	@DeleteMapping("/id/{id}/code/{code}")
	void delete(@PathVariable("id") Long id, @PathVariable("code") String code);
}
