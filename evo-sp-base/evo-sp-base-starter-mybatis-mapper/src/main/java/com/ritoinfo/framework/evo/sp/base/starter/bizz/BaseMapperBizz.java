package com.ritoinfo.framework.evo.sp.base.starter.bizz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import com.ritoinfo.framework.evo.sp.base.dto.PageDto;
import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseMapperEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-02-09 16:52
 */
public abstract class BaseMapperBizz<Dao extends Mapper, E extends BaseMapperEntity, PK extends Serializable, Dto> extends BaseBizz<Dao, E, PK, Dto> {
	@Autowired
	protected Dao dao;

	@SuppressWarnings("unchecked")
	public Dto get(PK id) {
		E e = (E) dao.selectByPrimaryKey(id);
		return e == null ? null : toDto(e);
	}

	@SuppressWarnings("unchecked")
	public Dto getOne(Object condition) {
		E e = (E) dao.selectOneByExample(condition);
		return e == null ? null : toDto(e);
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find() {
		return toDto(dao.selectAll());
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find(Object condition) {
		return toDto(dao.selectByExample(condition));
	}

	@SuppressWarnings("unchecked")
	public PageList<Dto> findPage(PageDto condition) {
		return findPage(condition, null);
	}

	public PageList<Dto> findPage(PageDto condition, ExampleCreater exampleCreater) {
		Page<Object> result = PageHelper.startPage(condition.getPageNo(), condition.getPageSize());
		List<E> list = dao.selectByExample(toExample(condition, exampleCreater));

		PageList<Dto> pageList = new PageList<>();
		BaseHelper.copyPage(pageList, result.getTotal(), condition, toDto(list));
		return pageList;
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return dao.selectCount(null);
	}

	public int count(Object condition) {
		return dao.selectCountByExample(condition);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public PK create(Dto dto) {
		E entity = toEntity(dto);

		entity.setCreateBy(getUserContextId());
		entity.setUpdateBy(entity.getCreateBy());
		entity.setCreateTime(DateUtil.now());
		entity.setUpdateTime(entity.getCreateTime());

		dao.insert(entity);

		return (PK) entity.getId();
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void update(Dto dto) {
		E entity = (E) dao.selectByPrimaryKey(getPKValue(dto));
		BeanUtil.copy(entity, dto);

		entity.setUpdateBy(getUserContextId());
		entity.setUpdateTime(DateUtil.now());

		dao.updateByPrimaryKey(entity);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public void delete(PK id) {
		dao.deleteByPrimaryKey(id);
	}

	protected Example toExample(PageDto condition, ExampleCreater exampleCreater) {
		return toExample(getEntityClass(), condition, exampleCreater);
	}

	protected Example toExample(PageDto condition) {
		return toExample(getEntityClass(), condition, null);
	}

	protected Example toExample(Class clazz, PageDto condition) {
		return toExample(clazz, condition, null);
	}

	protected Example toExample(Class clazz, PageDto condition, ExampleCreater exampleCreater) {
		Example example = new Example(clazz);

		if (exampleCreater == null) {
			Example.Criteria criteria = example.createCriteria();

			Map<String, Object> map = BeanUtil.beanToMap(condition);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				if (key.equals("pageNo") || key.equals("pageSize") || key.equals("pageSort") || key.equals("pageOrder")) {
					continue;
				}

				Object value = entry.getValue();
				if (value != null && StringUtil.isNotBlank(value.toString())) {
					criteria.andLike(key, "%" + value.toString() + "%");
				}
			}

			Example.OrderBy orderBy = example.orderBy(condition.getPageSort());
			if ("asc".equals(condition.getPageOrder())) {
				orderBy.asc();
			} else {
				orderBy.desc();
			}
		} else {
			exampleCreater.create(example, condition);
		}

		return example;
	}

	public static interface ExampleCreater {
		void create(Example example, PageDto condition);
	}
}
