package com.github.framework.evo.base.bizz;

import com.github.framework.evo.base.assist.BaseHelper;
import com.github.framework.evo.base.entity.BaseJpaEntity;
import com.github.framework.evo.common.model.PageDto;
import com.github.framework.evo.common.model.PageList;
import com.github.framework.evo.common.uitl.BeanUtil;
import com.github.framework.evo.common.uitl.DateUtil;
import com.github.framework.evo.common.uitl.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-02-09 16:52
 */
public abstract class BaseJpaBizz<Dao extends JpaRepository, E extends BaseJpaEntity, PK extends Serializable, Dto> extends BaseBizz<E, PK, Dto> {
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
		E entity = (E) dao.getOne(getPKValue(dto));
		BeanUtil.copy(entity, dto);

		entity.setUpdateBy(getUserContextId());
		entity.setUpdateTime(DateUtil.now());

		dao.save(entity);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void delete(PK id) {
		dao.deleteById(id);
	}

	@SuppressWarnings("unchecked")
	protected <T> Example<T> toExample(Object condition) {
		return toExample(getEntityClass(), condition, null, false);
	}

	@SuppressWarnings("unchecked")
	protected <T> Example<T> toExample(Object condition, boolean like) {
		return toExample(getEntityClass(), condition, null, like);
	}

	protected <T> Example<T> toExample(Class<T> clazz, Object condition, ExampleCreater exampleCreater, boolean like) {
		T t = BeanUtil.newInstance(clazz);
		BeanUtil.copy(t, condition);

		ExampleMatcher exampleMatcher = ExampleMatcher.matching();
		if (exampleCreater == null) {
			Map<String, Object> map = BeanUtil.beanToMap(t);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				if (key.equals("pageNo") || key.equals("pageSize") || key.equals("pageSort") || key.equals("pageOrder")) {
					continue;
				}

				Object value = entry.getValue();
				if (value != null && StringUtil.isNotBlank(value.toString())) {
					if (like) {
						exampleMatcher = exampleMatcher.withMatcher(key, ExampleMatcher.GenericPropertyMatchers.ignoreCase().contains());
					}
				}
			}
		} else {
			exampleCreater.create(exampleMatcher, condition);
		}


		return Example.of(t, exampleMatcher);
	}

	protected Pageable toPageable(PageDto condition) {
		return PageRequest.of(condition.getPageNo(), condition.getPageSize(), Sort.by("asc".equals(condition.getPageOrder()) ? Sort.Order.asc(condition.getPageSort()) : Sort.Order.desc(condition.getPageSort())));
	}

	public static interface ExampleCreater {
		void create(ExampleMatcher exampleMatcher, Object condition);
	}
}
