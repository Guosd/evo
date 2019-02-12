package com.github.framework.evo.base.bizz;

import com.github.framework.evo.common.model.UserContext;
import com.github.framework.evo.common.uitl.BeanUtil;
import com.github.framework.evo.common.model.PageDto;
import com.github.framework.evo.common.model.PageList;
import com.github.framework.evo.base.assist.BaseHelper;
import com.github.framework.evo.base.assist.Converter;
import com.github.framework.evo.base.entity.BaseEntity;
import com.github.framework.evo.base.session.SessionHolder;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 16:52
 */
public abstract class BaseBizz<E extends BaseEntity, PK extends Serializable, Dto> {
	private Class entityClass;
	private Class pkClass;
	private Class dtoClass;

	public abstract Dto get(PK id);

	public abstract Dto getOne(Object condition);

	public abstract List<Dto> find();

	public abstract List<Dto> find(Object condition);

	public abstract <C extends PageDto> PageList<Dto> findPage(C condition);

	public abstract int count();

	public abstract int count(Object condition);

	public abstract PK create(Dto dto);

	public abstract void update(Dto dto);

	public abstract void delete(PK id);

	@SuppressWarnings("unchecked")
	protected Dto toDto(E entity, Converter<Dto, E> converter) {
		return (Dto) BaseHelper.convertObject(entity, getDtoClass(), converter);
	}

	protected Dto toDto(E entity) {
		return toDto(entity, null);
	}

	@SuppressWarnings("unchecked")
	protected List<Dto> toDto(List<E> entityList, Converter<Dto, E> converter) {
		return BaseHelper.convertObject(entityList, getDtoClass(), converter);
	}

	protected List<Dto> toDto(List<E> entityList) {
		return toDto(entityList, null);
	}

	@SuppressWarnings("unchecked")
	protected E toEntity(Dto dto, Converter<E, Dto> converter) {
		return (E) BaseHelper.convertObject(dto, getEntityClass(), converter);
	}

	protected E toEntity(Dto dto) {
		return toEntity(dto, null);
	}

	protected Class getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class) BeanUtil.getGenericTypes(this)[1];
		}
		return entityClass;
	}

	protected Class getPKClass() {
		if (pkClass == null) {
			pkClass = (Class) BeanUtil.getGenericTypes(this)[2];
		}
		return pkClass;
	}

	protected Class getDtoClass() {
		if (dtoClass == null) {
			dtoClass = (Class) BeanUtil.getGenericTypes(this)[3];
		}
		return dtoClass;
	}

	@SuppressWarnings("unchecked")
	protected PK getPKValue(Object object) {
		return (PK) BeanUtil.getFieldValue(object, "id");
	}

	protected PK getUserContextId() {
		UserContext userContext = SessionHolder.getUserContext();
		return userContext == null ? null : userContext.getId(getPKClass());
	}
}
