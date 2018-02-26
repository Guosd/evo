package com.ritoinfo.framework.evo.sp.auth.bizz;

import com.ritoinfo.framework.evo.sp.auth.condition.UserCondition;
import com.ritoinfo.framework.evo.sp.auth.dao.UserDao;
import com.ritoinfo.framework.evo.sp.auth.entity.User;
import com.ritoinfo.framework.evo.sp.base.bizz.BaseBizz;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2018-02-09 16:31
 */
@Transactional(readOnly = true)
@Service
public class UserBizz extends BaseBizz<UserDao, User, Long, UserCondition> {
}
