package com.ritoinfo.framework.evo.sp.base.starter.bizz;

import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import com.ritoinfo.framework.evo.sp.base.starter.dto.PageDto;
import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 16:52
 */
public abstract class BaseJpaBizz<Dao extends JpaRepository, E extends BaseJpaEntity, PK extends Serializable, Dto> extends BaseBizz<Dao, E, PK, Dto> {
	@Autowired
	protected Dao dao;

	@SuppressWarnings("unchecked")
	public Dto get(PK id) {
		return toDto((E) dao.findById(id).orElse(null));
	}

	@SuppressWarnings("unchecked")
	public Dto getOne(Object condition) {
		return toDto((E) dao.findOne(toExample(condition)).orElse(null));
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find() {
		return toDto(dao.findAll());
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find(Object condition) {
		return toDto(dao.findAll(toExample(condition)));
	}

	@SuppressWarnings("unchecked")
	public PageList<Dto> findPage(PageDto condition) {
		Page<E> page = dao.findAll(toExample(condition), toPageable(condition));

		PageList<Dto> pageList = new PageList<>();
		BaseHelper.copyPage(pageList, page.getTotalElements(), condition, toDto(page.getContent()));
		return pageList;
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return new Long(dao.count()).intValue();
	}

	public int count(Object condition) {
		return new Long(dao.count(toExample(condition))).intValue();
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
		E entity = toEntity(dto);

		entity.setUpdateBy(getUserContextId());
		entity.setUpdateTime(DateUtil.now());

		dao.save(entity);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void delete(PK id) {
		dao.deleteById(id);
	}

	protected Example toExample(Object condition) {
		return toExample(getEntityClass(), condition);
	}

	protected <T> Example<T> toExample(Class<T> clazz, Object condition) {
		T t = BeanUtil.newInstance(clazz);
		BeanUtil.copy(t, condition);
		return Example.of(t);
	}

	protected Pageable toPageable(PageDto condition) {
		return PageRequest.of(condition.getPageNo(), condition.getPageSize(), Sort.by("asc".equals(condition.getPageOrder()) ? Sort.Order.asc(condition.getPageSort()) : Sort.Order.desc(condition.getPageSort())));
	}
}
