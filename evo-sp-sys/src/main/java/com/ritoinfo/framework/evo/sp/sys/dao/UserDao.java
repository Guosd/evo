package com.ritoinfo.framework.evo.sp.sys.dao;

import com.ritoinfo.framework.evo.sp.sys.condition.UserCondition;
import com.ritoinfo.framework.evo.sp.sys.entity.User;
import com.ritoinfo.framework.evo.sp.base.dao.MyBatisDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-02-09 16:58
 */
@Mapper
public interface UserDao extends MyBatisDao<User, Long, UserCondition> {
	void insertUserRole(Map<String, Object> map);

	void deleteUserRole(Long userId);
}
