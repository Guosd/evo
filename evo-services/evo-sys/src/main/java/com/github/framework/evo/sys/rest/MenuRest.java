package com.github.framework.evo.sys.rest;

import com.github.framework.evo.base.rest.BaseRest;
import com.github.framework.evo.common.model.PageList;
import com.github.framework.evo.common.validate.group.PageGroup;
import com.github.framework.evo.sys.bizz.MenuBizz;
import com.github.framework.evo.sys.condition.MenuCondition;
import com.github.framework.evo.sys.dto.MenuDto;
import com.github.framework.evo.sys.dto.MyMenuDto;
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
 * Date: 2018-04-23 21:14
 */
@Slf4j
@RequestMapping("/menu")
@RestController
public class MenuRest extends BaseRest<MenuBizz, Long, MenuDto, MenuCondition> {
	@GetMapping("/id/{id}/parent")
	public MenuDto getWithParent(@PathVariable Long id) {
		return bizz.getWithParent(id);
	}

	@PostMapping("/page/parent")
	public PageList<MenuDto> findPageWithParent(@Validated(PageGroup.class) @RequestBody MenuCondition condition) {
		return bizz.findPageWithParent(condition);
	}

	@GetMapping("/my")
	public List<MyMenuDto> my() {
		return bizz.findByUsername();
	}
}
