package com.github.framework.evo.common.uitl;

import com.github.framework.evo.common.Const;

import java.util.UUID;

/**
 * User: Kyll
 * Date: 2018-04-20 15:11
 */
public class AlgorithmUtil {
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 生成随机8位字符
	 * @return 字符串
	 */
	public static String random() {
		return random(8);
	}

	/**
	 * 生成随机字符串
	 * @param digit 1到8
	 * @return 字符串
	 */
	public static String random(int digit) {
		StringBuilder stringBuilder = new StringBuilder();
		String uuid = uuid();
		for (int i = 0; i < digit; i++) {
			stringBuilder.append(Const.WORD_CHARS[Integer.parseInt(uuid.substring(i * 4, i * 4 + 4), 16) % Const.WORD_CHARS.length]);
		}
		return stringBuilder.toString();
	}

	/**
	 * 生成随机数字字符串
	 * @param digit 1到8
	 * @return 字符串
	 */
	public static String randomNumber(int digit) {
		StringBuilder stringBuilder = new StringBuilder();
		String uuid = uuid();
		for (int i = 0; i < digit; i++) {
			stringBuilder.append(Const.NUMBER_CHARS[Integer.parseInt(uuid.substring(i * 4, i * 4 + 4), 16) % Const.NUMBER_CHARS.length]);
		}
		return stringBuilder.toString();
	}
}
