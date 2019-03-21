package com.github.framework.evo.sys.rest;

import com.github.framework.evo.base.rest.BaseRest;
import com.github.framework.evo.common.model.PageList;
import com.github.framework.evo.common.validate.group.PageGroup;
import com.github.framework.evo.sys.bizz.FuncBizz;
import com.github.framework.evo.sys.condition.FuncCondition;
import com.github.framework.evo.sys.dto.FuncDto;
import com.github.framework.evo.sys.dto.PermissionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-04 18:06
 */
@Slf4j
@RequestMapping("func")
@RestController
public class FuncRest extends BaseRest<FuncBizz, Long, FuncDto, FuncCondition> {
	@GetMapping("/id/{id}/micro")
	public FuncDto getWithMicro(@PathVariable Long id) {
		return bizz.getWithMicro(id);
	}

	@PostMapping("/page/micro")
	public PageList<FuncDto> findPageWithMicro(@Validated(PageGroup.class) @RequestBody FuncCondition condition) {
		return bizz.findPageWithMicro(condition);
	}

	@GetMapping("/user/username/{username}")
	public List<PermissionDto> findByUsername(@PathVariable String username) {
		return bizz.findByUsername(username);
	}
}
