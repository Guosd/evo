package com.ritoinfo.framework.evo.common.uitl;

import com.ritoinfo.framework.evo.common.Const;

import java.util.UUID;

/**
 * User: Kyll
 * Date: 2018-04-20 15:11
 */
public class AlgorithmUtil {
	/**
	 * 生成随机8位字符
	 * @return 字符串
	 */
	public static String random() {
		StringBuilder stringBuilder = new StringBuilder();
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < 8; i++) {
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
		String uuid = UUID.randomUUID().toString().replace("-", "");
		for (int i = 0; i < digit; i++) {
			stringBuilder.append(Const.NUMBER_CHARS[Integer.parseInt(uuid.substring(i * 4, i * 4 + 4), 16) % Const.NUMBER_CHARS.length]);
		}
		return stringBuilder.toString();
	}
}
