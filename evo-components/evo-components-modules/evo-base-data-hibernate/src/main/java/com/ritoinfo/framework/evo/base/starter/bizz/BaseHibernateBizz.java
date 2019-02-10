package com.ritoinfo.framework.evo.base.starter.bizz;

import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.common.model.PageDto;
import com.ritoinfo.framework.evo.common.model.PageList;
import com.ritoinfo.framework.evo.base.starter.assist.BaseHelper;
import com.ritoinfo.framework.evo.base.starter.dao.BaseHibernateDao;
import com.ritoinfo.framework.evo.base.starter.entity.BaseHibernateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 16:52
 */
public abstract class BaseHibernateBizz<Dao extends BaseHibernateDao, E extends BaseHibernateEntity, PK extends Serializable, Dto> extends BaseBizz<E, PK, Dto> {
	@Autowired
	protected Dao dao;

	@SuppressWarnings("unchecked")
	public Dto get(PK id) {
		return toDto((E) dao.get(id));
	}

	@SuppressWarnings("unchecked")
	public Dto getOne(Object condition) {
		return toDto((E) dao.getOne(condition));
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find() {
		return toDto(dao.findAll());
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find(Object condition) {
		return toDto(dao.find(condition));
	}

	@SuppressWarnings("unchecked")
	public PageList<Dto> findPage(PageDto condition) {
		int totalRecord = dao.count(condition);

		List<E> list;
		if (totalRecord > 0) {
			list = dao.find(condition, condition.getPageNo() * condition.getPageSize(), condition.getPageSize());
		} else {
			list = new ArrayList<>();
		}

		PageList<Dto> pageList = new PageList<>();
		BaseHelper.copyPage(pageList, totalRecord, condition, toDto(list));
		return pageList;
	}

	public int count() {
		return dao.count();
	}

	public int count(Object condition) {
		return dao.count(condition);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public PK create(Dto dto) {
		E entity = toEntity(dto);

		entity.setCreateBy(getUserContextId());
		entity.setUpdateBy(entity.getCreateBy());
		entity.setCreateTime(DateUtil.now());
		entity.setUpdateTime(entity.getCreateTime());

		dao.save(entity);

		return (PK) entity.getId();
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void update(Dto dto) {
		E entity = (E) dao.get(getPKValue(dto));
		BeanUtil.copy(entity, dto);

		entity.setUpdateBy(getUserContextId());
		entity.setUpdateTime(DateUtil.now());

		dao.update(entity);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void delete(PK id) {
		dao.delete(id);
	}
}
