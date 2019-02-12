package com.ritoinfo.framework.evo.sys.bizz;

import com.ritoinfo.framework.evo.base.assist.BaseHelper;
import com.ritoinfo.framework.evo.base.bizz.BaseXmlBizz;
import com.ritoinfo.framework.evo.common.model.PageList;
import com.ritoinfo.framework.evo.sys.condition.FuncCondition;
import com.ritoinfo.framework.evo.sys.condition.PermissionCondition;
import com.ritoinfo.framework.evo.sys.dao.FuncDao;
import com.ritoinfo.framework.evo.sys.dto.FuncDto;
import com.ritoinfo.framework.evo.sys.dto.MenuDto;
import com.ritoinfo.framework.evo.sys.dto.PermissionDto;
import com.ritoinfo.framework.evo.sys.entity.Func;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-04 18:05
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class FuncBizz extends BaseXmlBizz<FuncDao, Func, Long, FuncDto> {
	@Autowired
	private RoleBizz roleBizz;
	@Autowired
	private MenuBizz menuBizz;

	public FuncDto getWithMicro(Long id) {
		return BaseHelper.sqlMapToObject(dao.getWithMicro(id), FuncDto.class);
	}

	public List<FuncDto> findByMicro(Long microId) {
		FuncCondition condition = new FuncCondition();
		condition.setMicroId(microId);
		return toDto(dao.find(condition));
	}

	public List<FuncDto> findByRoleWithMicro(Long roleId) {
		return BaseHelper.sqlMapToObject(dao.findByRoleWithMicro(roleId), FuncDto.class);
	}

	public List<PermissionDto> findByPermission(PermissionCondition condition) {
		return BaseHelper.sqlMapToObject(dao.findByPermission(condition), PermissionDto.class);
	}

	public List<PermissionDto> findByUsername(String username) {
		return BaseHelper.sqlMapToObject(dao.findByUsername(username), PermissionDto.class);
	}

	public PageList<FuncDto> findPageWithMicro(FuncCondition condition) {
		PageList<FuncDto> pageList = new PageList<>();

		int count = dao.countWithMicro(condition.count());
		BaseHelper.copyPage(pageList, count, condition);

		if (count > 0) {
			pageList.setDataList(BaseHelper.sqlMapToObject(dao.findPageWithMicro(condition.page()), FuncDto.class));
		}

		return pageList;
	}

	@Transactional
	@Override
	public void delete(Long id) {
		roleBizz.deleteByFunc(id);

		for (MenuDto menuDto : menuBizz.findByFunc(id)) {
			menuDto.setFuncId(null);
			menuBizz.update(menuDto);
		}

		super.delete(id);
	}
}
