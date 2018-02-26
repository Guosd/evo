package com.ritoinfo.framework.evo.sp.auth.dao;

import com.ritoinfo.framework.evo.sp.auth.condition.UserCondition;
import com.ritoinfo.framework.evo.sp.auth.entity.User;
import com.ritoinfo.framework.evo.sp.base.dao.MyBatisDao;
import org.springframework.stereotype.Repository;

/**
 * User: Kyll
 * Date: 2018-02-09 16:58
 */
@Repository
public class UserDao extends MyBatisDao<User, Long, UserCondition> {
}
