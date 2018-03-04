package com.ritoinfo.framework.evo.sp.sys.dao;

import com.ritoinfo.framework.evo.sp.base.dao.MyBatisDao;
import com.ritoinfo.framework.evo.sp.sys.condition.RoleCondition;
import com.ritoinfo.framework.evo.sp.sys.entity.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * User: Kyll
 * Date: 2018-03-04 18:02
 */
@Mapper
public interface RoleDao extends MyBatisDao<Role, Long, RoleCondition> {
}
