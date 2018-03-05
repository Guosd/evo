package com.ritoinfo.framework.evo.sp.base.dao;

import com.ritoinfo.framework.evo.sp.base.condition.BaseCondition;
import com.ritoinfo.framework.evo.sp.base.entity.BaseEntity;

import java.io.Serializable;

/**
 * User: Kyll
 * Date: 2018-02-09 16:54
 */
public interface MyBatisDao<E extends BaseEntity<PK>, PK extends Serializable, C extends BaseCondition<PK>> extends BaseDao<E, PK, C> {
}
