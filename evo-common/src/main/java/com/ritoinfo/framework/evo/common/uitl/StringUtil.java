package com.ritoinfo.framework.evo.common.uitl;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Kyll
 * Date: 2018-02-09 14:59
 */
public class StringUtil {
	public static boolean isBlank(String str) {
		return StringUtils.isBlank(str);
	}

	public static boolean isNotBlank(String str) {
		return StringUtils.isNotBlank(str);
	}

	public static String toEmpty(Object object) {
		return object == null ? "" : object.toString();
	}

	/**
	 * 将驼峰字符串中大写字母、数字前插入下划线
	 * @param camel 驼峰形式字符串
	 * @return 带有下划线的字符串
	 */
	public static String camelStringInsertUnderline(String camel) {
		List<Integer> indexList = new ArrayList<>();
		for (int i = 0, length = camel.length(); i < length; i++) {
			char c = camel.charAt(i);

			int size = indexList.size();
			if (size == 0) {
				indexList.add(i);
			} else {
				char pc = camel.charAt(i - 1);
				if (Character.isDigit(pc) == Character.isDigit(c)) {
					if (Character.isLowerCase(pc) && Character.isUpperCase(c)) {
						indexList.add(i);
					}
				} else {
					indexList.add(i);
				}
			}
		}

		StringBuilder result = new StringBuilder();
		for (int i = 0, size = indexList.size() - 1; i < size; i++) {
			result.append(camel, indexList.get(i), indexList.get(i + 1)).append("_");
		}
		result.append(camel.substring(indexList.get(indexList.size() - 1)));

		return result.toString();
	}
}
