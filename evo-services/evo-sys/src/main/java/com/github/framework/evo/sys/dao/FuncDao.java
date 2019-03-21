package com.github.framework.evo.sys.dao;

import com.github.framework.evo.base.dao.BaseXmlDao;
import com.github.framework.evo.sys.condition.FuncCondition;
import com.github.framework.evo.sys.condition.PermissionCondition;
import com.github.framework.evo.sys.entity.Func;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-03-04 18:03
 */
@Mapper
public interface FuncDao extends BaseXmlDao<Func, Long> {
	Map<String, Object> getWithMicro(Long id);

	int countWithMicro(FuncCondition condition);

	List<Map<String, Object>> findPageWithMicro(FuncCondition condition);

	List<Map<String, Object>> findByRoleWithMicro(Long roleId);

	List<Map<String, Object>> findByPermission(PermissionCondition condition);

	List<Map<String, Object>> findByUsername(String username);
}
