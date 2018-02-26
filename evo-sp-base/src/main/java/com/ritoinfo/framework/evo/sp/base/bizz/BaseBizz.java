package com.ritoinfo.framework.evo.sp.base.bizz;

import com.ritoinfo.framework.evo.sp.base.condition.BaseCondition;
import com.ritoinfo.framework.evo.sp.base.dao.MyBatisDao;
import com.ritoinfo.framework.evo.sp.base.entity.BaseEntity;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 16:52
 */
public abstract class BaseBizz<D extends MyBatisDao<E, PK, C>, E extends BaseEntity<PK>, PK extends Serializable, C extends BaseCondition> {
	@Autowired
	private D dao;

	@Transactional
	public void create(E entity) {
		dao.create(entity);
	}

	public E read(PK id) {
		return dao.readByPK(id);
	}

	public List<E> read() {
		return dao.readAll();
	}

	public List<E> read(C condition) {
		return dao.readByCondition(condition);
	}

	public PageList<E> read(C condition, PageList<E> pageList) {
		return dao.readPage(condition, pageList);
	}

	@Transactional
	public void update(E entity) {
		dao.update(entity);
	}

	@Transactional
	public void delete(PK... ids) {
		for (PK id : ids) {
			dao.delete(id);
		}
	}

	public int count() {
		return dao.countAll();
	}

	public int count(C condition) {
		return dao.countByCondition(condition);
	}
}
