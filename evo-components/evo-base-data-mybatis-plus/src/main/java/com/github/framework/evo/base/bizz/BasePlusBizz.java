package com.github.framework.evo.base.bizz;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.framework.evo.base.assist.BaseHelper;
import com.github.framework.evo.base.entity.BasePlusEntity;
import com.github.framework.evo.common.model.PageDto;
import com.github.framework.evo.common.model.PageList;
import com.github.framework.evo.common.uitl.BeanUtil;
import com.github.framework.evo.common.uitl.DateUtil;
import com.github.framework.evo.common.uitl.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2019-03-28 16:41
 */
public abstract class BasePlusBizz<Dao extends BaseMapper, E extends BasePlusEntity, PK extends Serializable, Dto> extends BaseBizz<E, PK, Dto> {
	@Autowired
	protected Dao dao;

	@SuppressWarnings("unchecked")
	@Override
	public Dto get(PK id) {
		E e = (E) dao.selectById(id);
		return e == null ? null : toDto(e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Dto getOne(Object condition) {
		E e = (E) dao.selectOne(toWrapper(condition));
		return e == null ? null : toDto(e);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dto> find() {
		return toDto(dao.selectList(null));
	}

	@Override
	public List<Dto> find(Object condition) {
		return find(condition, null);
	}

	@SuppressWarnings("unchecked")
	public List<Dto> findLike(Object condition) {
		return findLike(condition, null);
	}

	@SuppressWarnings("unchecked")
	public List<Dto> find(Object condition, WrapperCreater wrapperCreater) {
		return toDto(dao.selectList(toWrapper(condition, wrapperCreater)));
	}

	@SuppressWarnings("unchecked")
	public List<Dto> findLike(Object condition, WrapperCreater wrapperCreater) {
		return toDto(dao.selectList(toWrapper(condition, wrapperCreater, true)));
	}

	@Override
	public PageList<Dto> findPage(PageDto condition) {
		return findPage(condition, null);
	}

	public PageList<Dto> findPageLike(PageDto condition) {
		return findPageLike(condition, null);
	}

	public PageList<Dto> findPage(PageDto condition, WrapperCreater wrapperCreater) {
		IPage<E> page = dao.selectPage(toIPage(condition), toWrapper(condition, wrapperCreater));

		PageList<Dto> pageList = new PageList<>();
		BaseHelper.copyPage(pageList, page.getTotal(), condition, toDto(page.getRecords()));
		return pageList;
	}

	public PageList<Dto> findPageLike(PageDto condition, WrapperCreater wrapperCreater) {
		IPage<E> page = dao.selectPage(toIPage(condition), toWrapper(condition, wrapperCreater, true));

		PageList<Dto> pageList = new PageList<>();
		BaseHelper.copyPage(pageList, page.getTotal(), condition, toDto(page.getRecords()));
		return pageList;
	}

	@Override
	public int count() {
		return dao.selectCount(null);
	}

	@Override
	public int count(Object condition) {
		return count(condition, null);
	}

	public int countLike(Object condition) {
		return countLike(condition, null);
	}

	public int count(Object condition, WrapperCreater exampleCreater) {
		return dao.selectCount(toWrapper(condition, exampleCreater));
	}

	public int countLike(Object condition, WrapperCreater wrapperCreater) {
		return dao.selectCount(toWrapper(condition, wrapperCreater, true));
	}

	@Override
	public PK create(Dto dto) {
		E entity = toEntity(dto);

		entity.setCreateBy(getUserContextId());
		entity.setUpdateBy(entity.getCreateBy());
		entity.setCreateTime(DateUtil.now());
		entity.setUpdateTime(entity.getCreateTime());

		dao.insert(entity);

		return (PK) entity.getId();
	}

	@Override
	public void update(Dto dto) {
		E entity = (E) dao.selectById(getPKValue(dto));
		BeanUtil.copy(entity, dto);

		entity.setUpdateBy(getUserContextId());
		entity.setUpdateTime(DateUtil.now());

		dao.updateById(entity);
	}

	@Override
	public void delete(PK id) {
		dao.deleteById(id);
	}

	protected Wrapper<E> toWrapper(Object condition) {
		return toWrapper(condition, null, false);
	}

	protected Wrapper<E> toWrapper(Object condition, WrapperCreater wrapperCreater) {
		return toWrapper(condition, wrapperCreater, false);
	}

	protected Wrapper<E> toWrapper(Object condition, WrapperCreater wrapperCreater, boolean like) {
		QueryWrapper<E> wrapper = new QueryWrapper<>();

		if (wrapperCreater == null) {
			Map<String, Object> map = condition == null ? new HashMap<>() : BeanUtil.beanToMap(condition);
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				if (key.equals("pageNo") || key.equals("pageSize") || key.equals("pageSort") || key.equals("pageOrder")) {
					continue;
				}

				Object value = entry.getValue();
				if (value != null && StringUtil.isNotBlank(value.toString())) {
					if (like) {
						wrapper.like(key, value);
					} else {
						wrapper.eq(key, value);
					}
				}
			}

			if (condition instanceof PageDto) {
				PageDto pageDto = (PageDto) condition;

				if ("asc".equals(pageDto.getPageOrder())) {
					wrapper.orderByAsc(pageDto.getPageSort());
				} else {
					wrapper.orderByDesc(pageDto.getPageSort());
				}
			}
		} else {
			wrapperCreater.create(wrapper, condition);
		}

		return wrapper;
	}

	protected IPage<E> toIPage(PageDto condition) {
		return new Page<>(condition.getPageNo(), condition.getPageSize());
	}

	public static interface WrapperCreater {
		void create(Wrapper wrapper, Object condition);
	}
}
