package com.ritoinfo.framework.evo.sp.base.starter.assist;

import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.starter.dto.BaseDto;
import com.ritoinfo.framework.evo.sp.base.starter.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-03-08 13:07
 */
public class BaseHelper {
	public static <T extends BaseDto, E extends BaseEntity> T toDto(E entity) {
		return toDto(entity, null);
	}

	public static <T extends BaseDto, E extends BaseEntity> T toDto(E entity, Converter<T, E> converter) {
		T dto = toEmptyDto(entity);
		BeanUtil.copy(dto, entity);

		if (converter != null) {
			converter.convert(dto, entity);
		}

		return dto;
	}

	public static <T extends BaseDto, E extends BaseEntity> List<T> toDto(List<E> entityList) {
		return toDto(entityList, null);
	}

	public static <T extends BaseDto, E extends BaseEntity> List<T> toDto(List<E> entityList, Converter<T, E> converter) {
		List<T> dtoList = new ArrayList<>();
		for (E entity : entityList) {
			dtoList.add(toDto(entity, converter));
		}
		return dtoList;
	}

	public static <T extends BaseDto, E extends BaseEntity> PageList<T> toDto(PageList<E> entityPageList) {
		return toDto(entityPageList, null);
	}

	public static <T extends BaseDto, E extends BaseEntity> PageList<T> toDto(PageList<E> entityPageList, Converter<T, E> converter) {
		PageList<T> dtoPageList = new PageList<>();
		dtoPageList.setPageNo(entityPageList.getPageNo());
		dtoPageList.setPageSize(entityPageList.getPageSize());
		dtoPageList.setTotalRecord(entityPageList.getTotalRecord());
		dtoPageList.setDataList(toDto(entityPageList.getDataList(), converter));
		return dtoPageList;
	}

	public static <T extends BaseDto, E extends BaseEntity> T toEmptyDto(E entity) {
		String className = BeanUtil.getClassName(entity);
		return BeanUtil.newInstance(className.replace(".entity.", ".dto.") + "Dto");
	}

	public static <E extends BaseEntity, T extends BaseDto> E toEntity(T dto) {
		E entity = toEmptyEntity(dto);
		BeanUtil.copy(entity, dto);
		return entity;
	}

	public static <E extends BaseEntity, T extends BaseDto> E toEmptyEntity(T dto) {
		String className = BeanUtil.getClassName(dto);
		className = className.replace(".dto.", ".entity.");
		return BeanUtil.newInstance(className.substring(0, className.length() - 3));
	}

	public static <T, E> T toAnyDto(E entity, Class<T> clazz) {
		return toAnyDto(entity, clazz, null);
	}

	public static <T, E> T toAnyDto(E entity, Class<T> clazz, Converter<T, E> converter) {
		T target = BeanUtil.newInstance(clazz);
		BeanUtil.copy(target, entity);

		if (converter != null) {
			converter.convert(target, entity);
		}

		return target;
	}

	public static <T, E> List<T> toAnyDto(List<E> entityList, Class<T> clazz) {
		return toAnyDto(entityList, clazz, (Converter<T, E>) (t, e) -> {});
	}

	public static <T, E> List<T> toAnyDto(List<E> entityList, Class<T> clazz, Converter<T, E> converter) {
		List<T> targetList = new ArrayList<>();
		for (E entity : entityList) {
			targetList.add(toAnyDto(entity, clazz, converter));
		}
		return targetList;
	}
}
