package com.ritoinfo.framework.evo.sp.auth.dao;

import com.ritoinfo.framework.evo.sp.auth.condition.UserCondition;
import com.ritoinfo.framework.evo.sp.auth.entity.User;
import com.ritoinfo.framework.evo.sp.base.dao.MyBatisDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * User: Kyll
 * Date: 2018-02-09 16:58
 */
@Mapper
public interface UserDao extends MyBatisDao<User, Long, UserCondition> {
}
