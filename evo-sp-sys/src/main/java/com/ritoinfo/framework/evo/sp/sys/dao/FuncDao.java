package com.ritoinfo.framework.evo.sp.sys.dao;

import com.ritoinfo.framework.evo.sp.base.starter.dao.BaseXmlDao;
import com.ritoinfo.framework.evo.sp.sys.condition.FuncCondition;
import com.ritoinfo.framework.evo.sp.sys.entity.Func;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-03-04 18:03
 */
@Mapper
public interface FuncDao extends BaseXmlDao<Func, Long, FuncCondition> {
	Map<String, Object> getWithMicro(Long id);

	int countWithMicro(FuncCondition condition);

	List<Map<String, Object>> findPageWithMicro(FuncCondition condition);

	List<Map<String, Object>> findByRoleWithMicro(Long roleId);

	List<Map<String, Object>> findByUsername(String username);
}
