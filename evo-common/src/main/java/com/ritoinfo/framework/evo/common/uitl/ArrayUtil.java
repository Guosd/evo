package com.ritoinfo.framework.evo.common.uitl;

/**
 * User: Kyll
 * Date: 2018-05-11 09:23
 */
public class ArrayUtil {
	public static boolean isNull(Object[] values) {
		return values == null;
	}

	public static boolean isNotNull(Object[] values) {
		return !isNull(values);
	}

	public static boolean isEmpty(Object[] values) {
		return isNotNull(values) && values.length == 0;
	}

	public static boolean isNotEmpty(Object[] values) {
		return !isEmpty(values);
	}

	/**
	 * 检查数组元素有效性，其中任意元素为null，即非法
	 * 此方法并不会对数组本身进行检验
	 * @param values 待检验数组
	 * @return 如果待检验数组为null，或者检验通过为true， 否则为false
	 */
	public static boolean isValid(Long[] values) {
		boolean valid = true;
		if (values != null) {
			for (Long value : values) {
				if (value == null) {
					valid = false;
					break;
				}
			}
		}
		return valid;
	}
}
