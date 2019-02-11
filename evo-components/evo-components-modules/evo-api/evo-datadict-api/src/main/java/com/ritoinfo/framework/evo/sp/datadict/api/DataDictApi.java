package com.ritoinfo.framework.evo.sp.datadict.api;

import com.ritoinfo.framework.evo.common.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.datadict.dto.DataDictDto;
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
	ServiceResponse<List<DataDictDto>> code(@PathVariable("code") String code);

	@GetMapping("/code/{code}/key/{key}")
	ServiceResponse<DataDictDto> codeAndKey(@PathVariable("code") String code, @PathVariable("key") String key);

	@DeleteMapping("/id/{id}/code/{code}")
	ServiceResponse delete(@PathVariable("id") Long id, @PathVariable("code") String code);
}
