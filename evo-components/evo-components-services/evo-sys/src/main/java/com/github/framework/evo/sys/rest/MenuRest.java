package com.github.framework.evo.sys.rest;

import com.github.framework.evo.base.rest.BaseRest;
import com.github.framework.evo.common.Const;
import com.github.framework.evo.common.exception.BizzException;
import com.github.framework.evo.common.exception.RestException;
import com.github.framework.evo.common.model.PageList;
import com.github.framework.evo.common.model.ServiceResponse;
import com.github.framework.evo.common.uitl.StringUtil;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-04-23 21:14
 */
@Slf4j
@RequestMapping("/menu")
@RestController
public class MenuRest extends BaseRest<MenuBizz, Long, MenuDto, MenuCondition> {
	@GetMapping("/id/{id}/parent")
	public ServiceResponse<MenuDto> getWithParent(@PathVariable Long id) {
		return ServiceResponse.ok(bizz.getWithParent(id));
	}

	@PostMapping("/page/parent")
	public ServiceResponse<PageList<MenuDto>> findPageWithParent(@Validated(PageGroup.class) @RequestBody MenuCondition condition) {
		return ServiceResponse.ok(bizz.findPageWithParent(condition));
	}

	@GetMapping("/my")
	public ServiceResponse<List<MyMenuDto>> my() {
		try {
			return ServiceResponse.ok(bizz.findByUsername());
		} catch (BizzException e) {
			throw new RestException(Const.RC_SYS_MENU_MY, e);
		}
	}

	// TODO 兼容 SCFW
	@GetMapping("/scfw/my")
	public Map<String, Object> scfwMy() {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("isSuccess", true);
		resultMap.put("result", getScfwMenu(null, bizz.findByUsername()));

		return resultMap;
	}

	// TODO 兼容 SCFW
	private List<Map<String, Object>> getScfwMenu(Long parentId, List<MyMenuDto> myMenuDtoList) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (MyMenuDto myMenuDto : myMenuDtoList) {
			boolean flag = false;
			if (parentId == null) {
				if (myMenuDto.getParentId() == null) {
					flag = true;
				}
			} else {
				if (myMenuDto.getParentId() != null && parentId.longValue() == myMenuDto.getParentId()) {
					flag = true;
				}
			}

			if (flag) {
				Map<String, Object> map = new HashMap<>();
				map.put("id", myMenuDto.getId());
				map.put("parentId", myMenuDto.getParentId());
				map.put("name", myMenuDto.getName());
				map.put("code", myMenuDto.getCode());
				map.put("prefix", myMenuDto.getPrefix());
				map.put("uri", myMenuDto.getUri());
				map.put("method", myMenuDto.getMethod());
				map.put("icon", null);
				map.put("url", StringUtil.isBlank(myMenuDto.getUri()) ? "#" : myMenuDto.getUri());
				map.put("urlPrefix", myMenuDto.getPrefix());
				map.put("childMenus", getScfwMenu(myMenuDto.getId(), myMenuDtoList));
				resultList.add(map);
			}
		}
		return resultList;
	}
}
