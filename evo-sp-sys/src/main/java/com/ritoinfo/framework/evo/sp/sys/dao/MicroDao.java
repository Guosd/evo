package com.ritoinfo.framework.evo.sp.sys.dao;

import com.ritoinfo.framework.evo.sp.base.dao.MyBatisDao;
import com.ritoinfo.framework.evo.sp.sys.condition.MicroCondition;
import com.ritoinfo.framework.evo.sp.sys.entity.Micro;
import org.apache.ibatis.annotations.Mapper;

/**
 * User: Kyll
 * Date: 2018-04-23 20:02
 */
@Mapper
public interface MicroDao extends MyBatisDao<Micro, Long, MicroCondition> {
}
