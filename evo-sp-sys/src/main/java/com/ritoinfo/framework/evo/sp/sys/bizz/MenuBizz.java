package com.ritoinfo.framework.evo.sp.sys.bizz;

import com.ritoinfo.framework.evo.common.jwt.model.UserContext;
import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseBizz;
import com.ritoinfo.framework.evo.sp.base.starter.exception.UserContextNotExistException;
import com.ritoinfo.framework.evo.sp.base.starter.session.SessionHolder;
import com.ritoinfo.framework.evo.sp.sys.condition.MenuCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.MenuDao;
import com.ritoinfo.framework.evo.sp.sys.dto.MenuDto;
import com.ritoinfo.framework.evo.sp.sys.dto.MyMenuDto;
import com.ritoinfo.framework.evo.sp.sys.entity.Menu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * User: Kyll
 * Date: 2018-04-23 21:13
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class MenuBizz extends BaseBizz<MenuDao, Menu, Long, MenuCondition, MenuDto> {
	public List<MyMenuDto> getByUsername() {
		UserContext userContext = SessionHolder.getUserContext();
		if (userContext == null) {
			throw new UserContextNotExistException();
		}
		return getByUsername(userContext.getUsername());
	}

	public List<MyMenuDto> getByUsername(String username) {
		return BaseHelper.mapToDto(recurParentMenu(dao.getByUsername(username)), MyMenuDto.class);
	}

	private List<Map<String, Object>> recurParentMenu(List<Map<String, Object>> mapList) {
		List<Map<String, Object>> resultList = new ArrayList<>(mapList);

		Set<Long> parentIdSet = new HashSet<>();
		for (Map<String, Object> map : mapList) {
			Long parentId = (Long) map.get("parent_id");
			if (parentId != null) {
				boolean exist = false;
				for (Map<String, Object> result : resultList) {
					Long id = (Long) result.get("id");
					if (parentId.longValue() == id.longValue()) {
						exist = true;
						break;
					}
				}

				if (!exist) {
					parentIdSet.add(parentId);
				}
			}
		}

		if (!parentIdSet.isEmpty()) {
			resultList.addAll(recurParentMenu(dao.getByIds(parentIdSet.toArray(new Long[0]))));
		}

		return resultList;
	}
}
