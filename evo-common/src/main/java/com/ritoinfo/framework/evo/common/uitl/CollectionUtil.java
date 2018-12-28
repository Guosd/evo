package com.ritoinfo.framework.evo.common.uitl;

import java.util.Iterator;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-11-18 11:47
 */
public class CollectionUtil {
	public static void trim(Map map) {
		Iterator it  = map.keySet().iterator();
		while (it.hasNext()) {
			if (map.get(it.next()) == null) {
				it.remove();
			}
		}
	}

	/**
	 * 查询 map 中是否存在指定的 key，key 比较会忽略大小写
	 * @param map Map<String, Object>
	 * @param key String
	 * @return 如果存在返回 true，否则返回 false
	 */
	public static boolean hasIgnoreCaseKey(Map<String, ?> map, String key) {
		for (String k : map.keySet()) {
			if (k.equalsIgnoreCase(key)) {
				return true;
			}
		}
		return false;
	}
}
