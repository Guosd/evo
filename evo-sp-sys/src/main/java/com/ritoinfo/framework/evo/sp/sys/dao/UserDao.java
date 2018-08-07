package com.ritoinfo.framework.evo.sp.sys.dao;

import com.ritoinfo.framework.evo.sp.base.starter.dao.BaseXmlDao;
import com.ritoinfo.framework.evo.sp.sys.condition.UserCondition;
import com.ritoinfo.framework.evo.sp.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-02-09 16:58
 */
@Mapper
public interface UserDao extends BaseXmlDao<User, Long, UserCondition> {
	void insertWithRole(Map<String, Object> map);

	void deleteWithRole(Long id);

	void deleteByRole(Long roleId);
}
