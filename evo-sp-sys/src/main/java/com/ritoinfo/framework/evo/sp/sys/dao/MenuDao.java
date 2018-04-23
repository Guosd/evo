package com.ritoinfo.framework.evo.sp.sys.dao;

import com.ritoinfo.framework.evo.sp.base.dao.MyBatisDao;
import com.ritoinfo.framework.evo.sp.sys.condition.MenuCondition;
import com.ritoinfo.framework.evo.sp.sys.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

/**
 * User: Kyll
 * Date: 2018-04-23 21:12
 */
@Mapper
public interface MenuDao extends MyBatisDao<Menu, Long, MenuCondition> {
}
