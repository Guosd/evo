package com.ritoinfo.framework.evo.sp.sys.dao;

import com.ritoinfo.framework.evo.sp.base.starter.dao.BaseXmlDao;
import com.ritoinfo.framework.evo.sp.sys.condition.RoleCondition;
import com.ritoinfo.framework.evo.sp.sys.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-03-04 18:02
 */
@Mapper
public interface RoleDao extends BaseXmlDao<Role, Long, RoleCondition> {
	List<Role> findByUserId(Long userId);

	List<Role> findByUsername(String username);

	void insertWithFunc(Map<String, Object> map);

	void deleteWithFunc(Long id);

	void deleteByFunc(Long funcId);
}
