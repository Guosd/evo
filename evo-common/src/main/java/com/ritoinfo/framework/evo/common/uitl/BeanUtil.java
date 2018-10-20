package com.ritoinfo.framework.evo.common.uitl;

import com.ritoinfo.framework.evo.common.exception.BeanOperateException;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.beans.BeanCopier;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

	/**
	 * bean 转换为 map， 排除掉 org.slf4j.Logger 属性
	 * @param object bean 对象
	 * @return 属性 map
	 */
	public static Map<String, Object> beanToMap(Object object) {
		Map<String, Object> map = new HashMap<>();
		Arrays.stream(object.getClass().getDeclaredFields()).forEach(field -> {
			if (field.getType() != Logger.class) {
				map.put(field.getName(), getFieldValue(object, field));
			}
		});
		return map;
	}

	/**
	 * map 转换为 bean， 排除掉 org.slf4j.Logger 属性
	 * @param map bean属性键值对
	 * @param clazz 将要转换的对象类
	 * @param <T> 将要转换的对象类型
	 * @return 将要转换的对象
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
		T t = newInstance(clazz);
		Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
			if (field.getType() != Logger.class) {
				String fieldName = field.getName();
				setFieldValue(t, fieldName, map.get(fieldName));
			}
		});
		return t;
	}

	public static String getClassName(Object object) {
		return object.getClass().getName();
	}

	public static Class getClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new BeanOperateException("加载 Class 失败", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericClass(Object object) {
		return getClass(getGenericTypes(object)[0].getTypeName());
	}

	public static Type[] getGenericTypes(Object object) {
		return ((ParameterizedTypeImpl) object.getClass().getGenericSuperclass()).getActualTypeArguments();
	}

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(String className) {
		return (T) newInstance(getClass(className));
	}

	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new BeanOperateException("实例化对象失败", e);
		}
	}

	public static List<Field> getFields(Object object) {
		List<Field> fieldList = new ArrayList<>();

		Class clazz = object.getClass();
		do {
			fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
			clazz = clazz.getSuperclass();
		} while (clazz != null);

		return fieldList;
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
		return getFieldValue(object, getField(object, fieldName));
	}

	public static Object getFieldValue(Object object, Field field) {
		Object value = null;

		if (field != null) {
			field.setAccessible(true);

			try {
				value = field.get(object);
			} catch (IllegalAccessException e) {
				throw new BeanOperateException("获取对象中的属性值失败", e);
			}
		}

		return value;
	}

	public static void setFieldValue(Object object, String fieldName, Object fieldValue) {
		setFieldValue(object, getField(object, fieldName), fieldValue);
	}

	public static void setFieldValue(Object object, Field field, Object fieldValue) {
		if (field != null) {
			field.setAccessible(true);

			try {
				field.set(object, fieldValue);
			} catch (IllegalAccessException e) {
				throw new BeanOperateException("设置对象中的属性值失败", e);
			}
		}
	}

	public static Method getMethod(Object object, String methodName) {
		for (Method method : object.getClass().getMethods()) {
			if (methodName.equals(method.getName())) {
				return method;
			}
		}
		return null;
	}

	public static Method getMethod(Object object, String methodName, Class<?>... parameterTypes) {
		try {
			return object.getClass().getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			throw new BeanOperateException("获取方法失败", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Annotation> T getAnnotation(Method method, int paramIndex, Class<T> annotationClass) {
		T result = null;

		try {
			Annotation[] annotations = method.getParameterAnnotations()[paramIndex];
			for (Annotation annotation : annotations) {
				if (annotation.annotationType() == annotationClass) {
					result = (T) annotation;
					break;
				}
			}
		} catch (Exception e) {
			throw new BeanOperateException("获取注解失败", e);
		}

		return result;
	}

	public static <T extends Annotation> T getAnnotation(ProceedingJoinPoint proceedingJoinPoint, Class<T> clazz) {
		MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = methodSignature.getMethod();
		return method.getAnnotation(clazz);
	}

	public static Object invoke(Object object, Method method, Object... args) {
		try {
			return method.invoke(object, args);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new BeanOperateException("调用方法失败", e);
		}
	}
}
