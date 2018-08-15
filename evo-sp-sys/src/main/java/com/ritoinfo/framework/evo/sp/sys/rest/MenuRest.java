package com.ritoinfo.framework.evo.sp.sys.rest;

import com.ritoinfo.framework.evo.common.Const;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.sp.base.exception.BizzException;
import com.ritoinfo.framework.evo.sp.base.exception.RestException;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.model.ServiceResponse;
import com.ritoinfo.framework.evo.sp.base.starter.rest.BaseRest;
import com.ritoinfo.framework.evo.sp.base.validate.group.PageGroup;
import com.ritoinfo.framework.evo.sp.sys.bizz.MenuBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.MenuCondition;
import com.ritoinfo.framework.evo.sp.sys.dto.MenuDto;
import com.ritoinfo.framework.evo.sp.sys.dto.MyMenuDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/page/parent")
	public ServiceResponse<PageList<MenuDto>> findPageWithParent(@Validated(PageGroup.class) MenuCondition condition) {
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
