package com.ritoinfo.framework.evo.sp.base.dao;

import com.ritoinfo.framework.evo.sp.base.condition.BaseCondition;
import com.ritoinfo.framework.evo.sp.base.entity.BaseEntity;
import com.ritoinfo.framework.evo.sp.base.model.PageList;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-26 16:13
 */
public interface BaseDao<E extends BaseEntity<PK>, PK extends Serializable, C extends BaseCondition> {
	PK create(E entity);

	E readById(PK id);

	List<E> readAll();

	List<E> readByCondition(C condition);

	PageList<E> readPage(C condition);

	int update(E entity);

	int delete(PK id);

	int countAll();

	int countByCondition(C condition);
}
