package com.ritoinfo.framework.evo.common.uitl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-02-27 14:36
 */
@Slf4j
public class BeanUtil {
	private static final Map<String, BeanCopier> BEAN_COPIER_MAP = new HashMap<>();

	public static void copy(Object dest, Object orig) {
		String beanKey = dest.getClass().getName() + "_" + orig.getClass().getName();

		BeanCopier copier;
		if (BEAN_COPIER_MAP.containsKey(beanKey)) {
			copier = BEAN_COPIER_MAP.get(beanKey);
		} else {
			copier = BeanCopier.create(orig.getClass(), dest.getClass(), false);
			BEAN_COPIER_MAP.put(beanKey, copier);
		}

		copier.copy(orig, dest, null);
	}

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
