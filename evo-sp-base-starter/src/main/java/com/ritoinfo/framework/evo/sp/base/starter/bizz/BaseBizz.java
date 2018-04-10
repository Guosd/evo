package com.ritoinfo.framework.evo.sp.base.starter.bizz;

import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.starter.condition.BaseCondition;
import com.ritoinfo.framework.evo.sp.base.starter.dao.BaseDao;
import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseEntity;
import com.ritoinfo.framework.evo.sp.base.starter.session.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 16:52
 */
public abstract class BaseBizz<D extends BaseDao<E, PK, C>, E extends BaseEntity<PK>, PK extends Serializable, C extends BaseCondition<PK>> {
	@Autowired
	protected D dao;

	public E get(PK id) {
		return dao.get(id);
	}

	public List<E> find() {
		return dao.find(null);
	}

	public List<E> find(C condition) {
		return dao.find(condition);
	}

	public PageList<E> findPage(C condition) {
		PageList<E> pageList = new PageList<>();

		int count = dao.count(condition.count());
		pageList.setTotalRecord(count);
		pageList.setPageNo(condition.getPageNo());
		pageList.setPageSize(condition.getPageSize());
		pageList.setPageSort(condition.getPageSort());
		pageList.setPageOrder(condition.getPageOrder());

		if (count > 0) {
			pageList.setDataList(dao.find(condition.page()));
		}

		return pageList;
	}

	public int count() {
		return dao.count(null);
	}

	public int count(C condition) {
		return dao.count(condition);
	}

	@Transactional
	public void create(E entity) {
		entity.setCreateBy(SessionHolder.getUserContext().getId(BeanUtil.getGenericClass(entity)));
		entity.setUpdateBy(entity.getCreateBy());
		entity.setCreateTime(DateUtil.now());
		entity.setUpdateTime(entity.getCreateTime());
		dao.insert(entity);
	}

	@Transactional
	public void update(E entity) {
		entity.setUpdateBy(SessionHolder.getUserContext().getId(BeanUtil.getGenericClass(entity)));
		entity.setUpdateTime(DateUtil.now());
		dao.update(entity);
	}

	@Transactional
	public void delete(PK id) {
		dao.delete(id);
	}
}
