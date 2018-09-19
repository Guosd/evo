package com.ritoinfo.framework.evo.common;

import com.ritoinfo.framework.evo.common.uitl.AlgorithmUtil;
import com.ritoinfo.framework.evo.common.uitl.DateUtil;
import com.ritoinfo.framework.evo.common.uitl.StringUtil;

/**
 * User: Kyll
 * Date: 2018-09-19 16:40
 */
public class CommonUtil {
	private static int message_count = 0;

	public static String generateMessageKey() {
		return DateUtil.formatDatetimeCompact(DateUtil.now()) + "_" + AlgorithmUtil.randomNumber(8) + "_" + StringUtil.leftPad(String.valueOf(message_count++), 8, '0');
	}
}
