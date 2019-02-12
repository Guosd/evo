package com.github.framework.evo.base.dao;

import com.github.framework.evo.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-26 16:13
 */
public interface BaseDao<E extends BaseEntity, PK extends Serializable> {
	E get(PK id);

	E getOne(Object condition);

	List<E> find(Object condition);

	List<E> findLike(Object condition);

	int count(Object condition);

	int countLike(Object condition);

	int insert(E entity);

	int update(E entity);

	int delete(PK id);
}
