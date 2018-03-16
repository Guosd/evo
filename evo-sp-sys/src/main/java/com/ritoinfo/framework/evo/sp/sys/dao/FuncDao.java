package com.ritoinfo.framework.evo.sp.sys.dao;

import com.ritoinfo.framework.evo.sp.base.dao.MyBatisDao;
import com.ritoinfo.framework.evo.sp.sys.condition.FuncCondition;
import com.ritoinfo.framework.evo.sp.sys.entity.Func;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-04 18:03
 */
@Mapper
public interface FuncDao extends MyBatisDao<Func, Long, FuncCondition> {
	List<Func> getByUsername(String username);
}
