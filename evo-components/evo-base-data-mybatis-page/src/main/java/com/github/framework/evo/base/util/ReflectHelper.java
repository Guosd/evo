package com.github.framework.evo.base.util;


import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class ReflectHelper {
    private static final Logger log = LoggerFactory.getLogger(ReflectHelper.class);

    private ReflectHelper() {
    }

    public static Object getFieldValue(Object obj, String fieldName) {
        if (obj == null) {
            return null;
        } else {
            Field targetField = getTargetField(obj.getClass(), fieldName);

            try {
                return FieldUtils.readField(targetField, obj, true);
            } catch (IllegalAccessException var4) {
                log.warn(var4.getMessage(), var4);
                return null;
            }
        }
    }

    public static Field getTargetField(Class<?> targetClass, String fieldName) {
        Field field = null;

        try {
            if (targetClass == null) {
                return field;
            }

            if (Object.class.equals(targetClass)) {
                return field;
            }

            field = FieldUtils.getDeclaredField(targetClass, fieldName, true);
            if (field == null) {
                field = getTargetField(targetClass.getSuperclass(), fieldName);
            }
        } catch (Exception var4) {
            log.warn(var4.getMessage(), var4);
        }

        return field;
    }

    public static void setFieldValue(Object obj, String fieldName, Object value) {
        if (null != obj) {
            Field targetField = getTargetField(obj.getClass(), fieldName);

            try {
                FieldUtils.writeField(targetField, obj, value);
            } catch (IllegalAccessException var5) {
                log.warn(var5.getMessage(), var5);
            }

        }
    }
}
