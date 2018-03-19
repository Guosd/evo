package com.ritoinfo.framework.evo.sp.base.starter.dao;

import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-26 16:13
 */
public interface BaseDao<E extends BaseEntity<PK>, PK extends Serializable, C extends BaseCondition<PK>> {
	E get(PK id);

	List<E> find(C condition);

	int count(C condition);

	int insert(E entity);

	int update(E entity);

	int delete(PK id);
}
