package com.ritoinfo.framework.evo.sp.base.starter.assist;

import com.ritoinfo.framework.evo.common.uitl.BeanUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;
import com.ritoinfo.framework.evo.sp.base.model.PageList;
import com.ritoinfo.framework.evo.sp.base.starter.dto.PageDto;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-07-15 15:43
 */
public class BaseHelper {
	public static <T> void copyPage(PageList<T> pageList, int total, PageDto condition) {
		pageList.setTotalRecord(total);
		pageList.setPageNo(condition.getPageNo());
		pageList.setPageSize(condition.getPageSize());
		pageList.setPageSort(condition.getPageSort());
		pageList.setPageOrder(condition.getPageOrder());
	}

	public static <T> void copyPage(PageList<T> pageList, int total, PageDto condition, List<T> dataList) {
		copyPage(pageList, total, condition);
		pageList.setDataList(dataList);
	}

	public static <T> void copyPage(PageList<T> pageList, long total, PageDto condition, List<T> dataList) {
		copyPage(pageList, new Long(total).intValue(), condition, dataList);
	}

	public static <T, E> T convertObject(E orig, Class<T> clazz, Converter<T, E> converter) {
		T dest = BeanUtil.newInstance(clazz);
		BeanUtil.copy(dest, orig);

		if (converter != null) {
			converter.convert(dest, orig);
		}

		return dest;
	}

	public static <T, E> T convertObject(E orig, Class<T> clazz) {
		return convertObject(orig, clazz, null);
	}

	public static <T, E> List<T> convertObject(List<E> origList, Class<T> clazz, Converter<T, E> converter) {
		if (origList == null) {
			return null;
		}

		List<T> destList = new ArrayList<>();
		for (E orig : origList) {
			destList.add(convertObject(orig, clazz, converter));
		}
		return destList;
	}

	public static <T, E> List<T> convertObject(List<E> origList, Class<T> clazz) {
		return convertObject(origList, clazz, (Converter<T, E>) (t, e) -> {});
	}

	public static Map<String, Object> toMap(Object object) {
		Map<String, Object> map = null;
		if (object != null) {
			map = new HashMap<>();

			for (Field field : BeanUtil.getFields(object)) {
				map.put(field.getName(), BeanUtil.getFieldValue(object, field));
			}
		}
		return map;
	}

	public static Map<String, Object> toSqlMap(Object object) {
		Map<String, Object> map = null;
		if (object != null) {
			map = new HashMap<>();

			for (Field field : BeanUtil.getFields(object)) {
				map.put(StringUtil.camelStringInsertUnderline(field.getName()).toLowerCase(), BeanUtil.getFieldValue(object, field));
			}
		}
		return map;
	}

	public static <T> T toObject(Map<String, Object> map, Class<T> clazz, Converter<T, Map<String, Object>> converter) {
		T target = BeanUtil.newInstance(clazz);

		for (Field field : BeanUtil.getFields(target)) {
			BeanUtil.setFieldValue(target, field, map.get(field.getName()));
		}

		if (converter != null) {
			converter.convert(target, map);
		}

		return target;
	}

	public static <T> T toObject(Map<String, Object> map, Class<T> clazz) {
		return toObject(map, clazz, null);
	}

	public static <T> List<T> toObject(List<Map<String, Object>> list, Class<T> clazz, Converter<T, Map<String, Object>> converter) {
		List<T> targetList = new ArrayList<>();
		for (Map<String, Object> map : list) {
			targetList.add(toObject(map, clazz, converter));
		}
		return targetList;
	}

	public static <T> List<T> toObject(List<Map<String, Object>> list, Class<T> clazz) {
		return toObject(list, clazz, null);
	}

	public static <T> T sqlMapToObject(Map<String, Object> map, Class<T> clazz, Converter<T, Map<String, Object>> converter) {
		T target = BeanUtil.newInstance(clazz);

		for (Field field : BeanUtil.getFields(target)) {
			BeanUtil.setFieldValue(target, field, map.get(StringUtil.camelStringInsertUnderline(field.getName()).toLowerCase()));
		}

		if (converter != null) {
			converter.convert(target, map);
		}

		return target;
	}

	public static <T> T sqlMapToObject(Map<String, Object> map, Class<T> clazz) {
		return sqlMapToObject(map, clazz, null);
	}

	public static <T> List<T> sqlMapToObject(List<Map<String, Object>> mapList, Class<T> clazz, Converter<T, Map<String, Object>> converter) {
		List<T> targetList = new ArrayList<>();
		for (Map<String, Object> map : mapList) {
			targetList.add(sqlMapToObject(map, clazz, converter));
		}
		return targetList;
	}

	public static <T> List<T> sqlMapToObject(List<Map<String, Object>> mapList, Class<T> clazz) {
		return sqlMapToObject(mapList, clazz, null);
	}
}
