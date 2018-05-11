package com.ritoinfo.framework.evo.sp.sys.dao;

import com.ritoinfo.framework.evo.sp.base.dao.MyBatisDao;
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
public interface FuncDao extends MyBatisDao<Func, Long, FuncCondition> {
	List<Map<String, Object>> getByUsername(String username);

	List<Func> getByRole(Long roleId);
}
