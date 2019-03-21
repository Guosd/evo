package com.github.framework.evo.sys.dao;

import com.github.framework.evo.base.dao.BaseXmlDao;
import com.github.framework.evo.sys.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-02-09 16:58
 */
@Mapper
public interface UserDao extends BaseXmlDao<User, Long> {
	void insertWithRole(Map<String, Object> map);

	void deleteWithRole(Long id);

	void deleteByRole(Long roleId);
}
