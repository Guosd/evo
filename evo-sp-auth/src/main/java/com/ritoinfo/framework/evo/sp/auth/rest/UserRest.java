package com.ritoinfo.framework.evo.sp.auth.rest;

import com.ritoinfo.framework.evo.sp.auth.condition.UserCondition;
import com.ritoinfo.framework.evo.sp.auth.entity.User;
import com.ritoinfo.framework.evo.sp.auth.bizz.UserBizz;
import com.ritoinfo.framework.evo.sp.base.rest.BaseRest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: Kyll
 * Date: 2018-02-13 13:43
 */
@RequestMapping("user")
@RestController
public class UserRest extends BaseRest<UserBizz, User, Long, UserCondition> {
}
