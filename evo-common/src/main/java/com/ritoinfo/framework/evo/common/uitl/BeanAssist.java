package com.ritoinfo.framework.evo.common.uitl;

import java.lang.reflect.Field;

/**
 * User: Kyll
 * Date: 2018-02-27 14:36
 */
public class BeanAssist {
	public Field getField(Object object, String fieldName) {
		Field field = null;
		try {
			field = object.getClass().getField(fieldName);
		} catch (NoSuchFieldException ignored) {
		}
		return field;
	}

	public Object getFieldValue(Object object, String fieldName) {
		return null;
	}
}
