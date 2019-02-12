package com.ritoinfo.framework.evo.base.bizz;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ritoinfo.framework.evo.base.assist.BaseHelper;
import com.ritoinfo.framework.evo.base.entity.BaseMapperEntity;
import com.ritoinfo.framework.evo.common.model.PageDto;
import com.ritoinfo.framework.evo.common.model.PageList;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
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
public abstract class BaseMapperBizz<Dao extends Mapper, E extends BaseMapperEntity, PK extends Serializable, Dto> extends BaseBizz<E, PK, Dto> {
	@Autowired
	protected Dao dao;

	@SuppressWarnings("unchecked")
	public Dto get(PK id) {
		E e = (E) dao.selectByPrimaryKey(id);
		return e == null ? null : toDto(e);
	}

	@SuppressWarnings("unchecked")
	public Dto getOne(Object condition) {
		E e = (E) dao.selectOneByExample(toExample(condition));
		return e == null ? null : toDto(e);
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find() {
		return toDto(dao.selectAll());
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find(Object condition) {
		return find(condition, null);
	}

	@SuppressWarnings("unchecked")
	public List<Dto> findLike(Object condition) {
		return findLike(condition, null);
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find(Object condition, ExampleCreater exampleCreater) {
		return toDto(dao.selectByExample(toExample(condition, exampleCreater)));
	}

	@SuppressWarnings("unchecked")
	public List<Dto> findLike(Object condition, ExampleCreater exampleCreater) {
		return toDto(dao.selectByExample(toExample(condition, exampleCreater, true)));
	}

	@SuppressWarnings("unchecked")
	public PageList<Dto> findPage(PageDto condition) {
		return findPage(condition, null);
	}

	@SuppressWarnings("unchecked")
	public PageList<Dto> findPageLike(PageDto condition) {
		return findPageLike(condition, null);
	}

	@SuppressWarnings("unchecked")
	public PageList<Dto> findPage(PageDto condition, ExampleCreater exampleCreater) {
		Page<Object> result = PageHelper.startPage(condition.getPageNo(), condition.getPageSize());
		List<E> list = dao.selectByExample(toExample(condition, exampleCreater));

		PageList<Dto> pageList = new PageList<>();
		BaseHelper.copyPage(pageList, result.getTotal(), condition, toDto(list));
		return pageList;
	}

	@SuppressWarnings("unchecked")
	public PageList<Dto> findPageLike(PageDto condition, ExampleCreater exampleCreater) {
		Page<Object> result = PageHelper.startPage(condition.getPageNo(), condition.getPageSize());
		List<E> list = dao.selectByExample(toExample(condition, exampleCreater, true));

		PageList<Dto> pageList = new PageList<>();
		BaseHelper.copyPage(pageList, result.getTotal(), condition, toDto(list));
		return pageList;
	}

	@SuppressWarnings("unchecked")
	public int count() {
		return dao.selectCount(null);
	}

	public int count(Object condition) {
		return count(condition, null);
	}

	public int countLike(Object condition) {
		return countLike(condition, null);
	}

	public int count(Object condition, ExampleCreater exampleCreater) {
		return dao.selectCountByExample(toExample(condition, exampleCreater));
	}

	public int countLike(Object condition, ExampleCreater exampleCreater) {
		return dao.selectCountByExample(toExample(condition, exampleCreater, true));
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

	protected Example toExample(Object condition, ExampleCreater exampleCreater) {
		return toExample(getEntityClass(), condition, exampleCreater);
	}

	protected Example toExample(Object condition, ExampleCreater exampleCreater, boolean like) {
		return toExample(getEntityClass(), condition, exampleCreater, like);
	}

	protected Example toExample(Object condition) {
		return toExample(getEntityClass(), condition, null);
	}

	protected Example toExample(Object condition, boolean like) {
		return toExample(getEntityClass(), condition, null, like);
	}

	protected Example toExample(Class clazz, Object condition) {
		return toExample(clazz, condition, null);
	}

	protected Example toExample(Class clazz, Object condition, boolean like) {
		return toExample(clazz, condition, null, like);
	}

	protected Example toExample(Class clazz, Object condition, ExampleCreater exampleCreater) {
		return toExample(clazz, condition, exampleCreater, false);
	}

	protected Example toExample(Class clazz, Object condition, ExampleCreater exampleCreater, boolean like) {
		Example example = new Example(clazz, false);

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
					if (like) {
						criteria.andLike(key, "%" + value.toString() + "%");
					} else {
						criteria.andEqualTo(key, value);
					}
				}
			}

			if (condition instanceof PageDto) {
				PageDto pageDto = (PageDto) condition;
				Example.OrderBy orderBy = example.orderBy(pageDto.getPageSort());
				if ("asc".equals(pageDto.getPageOrder())) {
					orderBy.asc();
				} else {
					orderBy.desc();
				}
			}
		} else {
			exampleCreater.create(example, condition);
		}

		return example;
	}

	public static interface ExampleCreater {
		void create(Example example, Object condition);
	}
}
