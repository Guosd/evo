package com.ritoinfo.framework.evo.sp.sys.bizz;

import com.ritoinfo.framework.evo.common.password.crypto.PasswordEncoder;
import com.ritoinfo.framework.evo.sp.base.bizz.BaseBizz;
import com.ritoinfo.framework.evo.sp.sys.condition.UserCondition;
import com.ritoinfo.framework.evo.sp.sys.dao.UserDao;
import com.ritoinfo.framework.evo.sp.sys.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 16:31
 */
@Slf4j
@Transactional(readOnly = true)
@Service
public class UserBizz extends BaseBizz<UserDao, User, Long, UserCondition> {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void create(User entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		super.create(entity);
	}

	public User getByUsername(String username) {
		UserCondition condition = new UserCondition();
		condition.setUsername(username);

		List<User> list = dao.find(condition);
		return list.isEmpty() ? null : list.get(0);
	}
}
