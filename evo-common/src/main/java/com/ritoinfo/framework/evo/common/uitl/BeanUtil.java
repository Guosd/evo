package com.ritoinfo.framework.evo.common.uitl;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * User: Kyll
 * Date: 2018-02-27 14:36
 */
@Slf4j
public class BeanUtil {
	public static Field getField(Object object, String fieldName) {
		Class clazz = null;
		Field field = null;

		do {
			clazz = clazz == null ? object.getClass() : clazz.getSuperclass();

			if (clazz == null) {
				break;
			}

			try {
				field = clazz.getDeclaredField(fieldName);
			} catch (NoSuchFieldException ignored) {
			}
		} while (field == null);

		return field;
	}

	public static Object getFieldValue(Object object, String fieldName) {
		Object value = null;

		Field field = getField(object, fieldName);
		if (field != null) {
			field.setAccessible(true);

			try {
				value = field.get(object);
			} catch (IllegalAccessException e) {
				log.error("获取 Bean 中的属性值失败", e);
			}
		}

		return value;
	}

	public static void setFieldValue(Object object, String fieldName, Object fieldValue) {
		Field field = getField(object, fieldName);
		if (field != null) {
			field.setAccessible(true);

			try {
				field.set(object, fieldValue);
			} catch (IllegalAccessException e) {
				log.error("设置 Bean 中的属性值失败", e);
			}
		}
	}
}
