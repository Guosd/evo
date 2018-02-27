package com.ritoinfo.framework.evo.common.uitl;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * User: Kyll
 * Date: 2018-02-27 14:36
 */
@Slf4j
public class BeanAssist {
	public static Field getField(Object object, String fieldName) {
		Field field = null;
		try {
			field = object.getClass().getField(fieldName);
		} catch (NoSuchFieldException ignored) {
		}
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
}
