package com.ritoinfo.framework.evo.sp.auth.authorization.bizz;

import com.ritoinfo.framework.evo.sp.auth.authorization.dao.LoginDao;
import com.ritoinfo.framework.evo.sp.auth.authorization.entity.Login;
import com.ritoinfo.framework.evo.sp.auth.authorization.model.LoginDto;
import com.ritoinfo.framework.evo.sp.base.starter.bizz.BaseMapperBizz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: Kyll
 * Date: 2018-12-20 13:32
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class LoginBizz extends BaseMapperBizz<LoginDao, Login, Long, LoginDto> {
}
