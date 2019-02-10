package com.ritoinfo.framework.evo.common.uitl;

import com.ritoinfo.framework.evo.common.exception.StringOperateException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

	public static String defaultIfBlank(String str, String defaultStr) {
		return StringUtils.defaultIfBlank(str, defaultStr);
	}

	/**
	 * 首字母大写，其余字符不变
	 * @param str 输入字符串
	 * @return 首字母大写的字符串
	 */
	public static String toCapture(String str) {
		return Character.toUpperCase(str.charAt(0)) + str.substring(1);
	}

	public static String leftPad(String str, int size, char padChar) {
		return StringUtils.leftPad(str, size, padChar);
	}

	public static String leftPad(String str, int size, String padStr) {
		return StringUtils.leftPad(str, size, padStr);
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

	public static boolean antPathMatch(String pattern, String path) {
		return new AntPathMatcher().match(pattern, path);
	}

	public static String urlEncodeUTF8(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new StringOperateException("字符串编码 UTF-8 失败", e);
		}
	}

	public static String urlDecodeUTF8(String str) {
		try {
			return URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new StringOperateException("字符串解码 UTF-8 失败", e);
		}
	}
}
