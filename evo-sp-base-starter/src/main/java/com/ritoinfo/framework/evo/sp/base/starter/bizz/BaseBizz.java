package com.ritoinfo.framework.evo.sp.base.starter.bizz;

import com.ritoinfo.framework.evo.common.jwt.model.UserContext;
import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.starter.assist.BaseHelper;
import com.ritoinfo.framework.evo.sp.base.starter.assist.Converter;
import com.ritoinfo.framework.evo.sp.base.starter.dto.PageDto;
import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseEntity;
import com.ritoinfo.framework.evo.sp.base.starter.session.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 16:52
 */
public abstract class BaseBizz<Dao, E extends BaseEntity, PK extends Serializable, Dto> {
	@Autowired
	protected Dao dao;

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

	protected PK getUserContextId() {
		UserContext userContext = SessionHolder.getUserContext();
		return userContext == null ? null : userContext.getId(getPKClass());
	}
}
