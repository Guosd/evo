package com.ritoinfo.framework.evo.sys.bizz;

import com.ritoinfo.framework.evo.base.assist.BaseHelper;
import com.ritoinfo.framework.evo.base.bizz.BaseXmlBizz;
import com.ritoinfo.framework.evo.base.session.SessionHolder;
import com.ritoinfo.framework.evo.common.exception.UserContextNotExistException;
import com.ritoinfo.framework.evo.common.model.PageList;
import com.ritoinfo.framework.evo.common.model.UserContext;
import com.ritoinfo.framework.evo.sys.condition.MenuCondition;
import com.ritoinfo.framework.evo.sys.dao.MenuDao;
import com.ritoinfo.framework.evo.sys.dto.MenuDto;
import com.ritoinfo.framework.evo.sys.dto.MyMenuDto;
import com.ritoinfo.framework.evo.sys.entity.Menu;
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
public class MenuBizz extends BaseXmlBizz<MenuDao, Menu, Long, MenuDto> {
	public MenuDto getWithParent(Long id) {
		return BaseHelper.sqlMapToObject(dao.getWithParent(id), MenuDto.class);
	}

	public List<MyMenuDto> findByUsername() {
		UserContext userContext = SessionHolder.getUserContext();
		if (userContext == null) {
			throw new UserContextNotExistException();
		}
		return findByUsername(userContext.getUsername());
	}

	public List<MyMenuDto> findByUsername(String username) {
		return BaseHelper.sqlMapToObject(recurParentMenu(dao.findByUsername(username)), MyMenuDto.class);
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
			resultList.addAll(recurParentMenu(dao.findByIds(parentIdSet.toArray(new Long[0]))));
		}

		return resultList;
	}

	public List<MenuDto> findByFunc(Long funcId) {
		MenuCondition condition = new MenuCondition();
		condition.setFuncId(funcId);
		return toDto(dao.find(condition));
	}

	public PageList<MenuDto> findPageWithParent(MenuCondition condition) {
		PageList<MenuDto> pageList = new PageList<>();

		int count = dao.countWithParent(condition.count());
		BaseHelper.copyPage(pageList, count, condition);

		if (count > 0) {
			pageList.setDataList(BaseHelper.sqlMapToObject(dao.findPageWithParent(condition.page()), MenuDto.class));
		}

		return pageList;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		MenuCondition condition = new MenuCondition();
		condition.setParentId(id);
		for (Menu menu : dao.find(condition)) {
			delete(menu.getId());
		}
		super.delete(id);
	}
}
